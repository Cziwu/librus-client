package pl.librus.client.api;

import android.content.Context;

import com.google.common.collect.Lists;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.requery.Persistable;
import java8.util.stream.StreamSupport;
import pl.librus.client.datamodel.Identifiable;
import pl.librus.client.datamodel.PlainLesson;
import pl.librus.client.datamodel.Teacher;
import pl.librus.client.datamodel.lesson.ImmutableJsonLesson;
import pl.librus.client.datamodel.lesson.JsonLesson;
import pl.librus.client.datamodel.lesson.LessonSubject;
import pl.librus.client.datamodel.lesson.LessonTeacher;
import pl.librus.client.datamodel.lesson.Timetable;
import pl.librus.client.datamodel.subject.Subject;

import static com.google.common.collect.Lists.newArrayList;

public class APIClient implements IAPIClient {

    private final MockEntityRepository repository = new MockEntityRepository();
    private final EntityMocks templates = new EntityMocks();

    public APIClient(Context _context) {

    }

    public Single<String> login(String username, String password) {
        return Single.just(username);
    }

    public Single<Timetable> getTimetable(LocalDate weekStart) {
        Timetable result = new Timetable();
        for (int dayNo = DateTimeConstants.MONDAY; dayNo <= DateTimeConstants.FRIDAY; dayNo++) {
            List<List<JsonLesson>> schoolDay = new ArrayList<>();
            for (int lessonNo = 0; lessonNo < repository.getList(PlainLesson.class).size(); lessonNo++) {
                ImmutableJsonLesson lesson = templates.jsonLesson()
                        .withDayNo(dayNo);
                schoolDay.add(newArrayList(withLessonNumber(lesson, lessonNo)));
            }
            result.put(weekStart.plusDays(dayNo - 1), schoolDay);
        }
        //weekend
        result.put(weekStart.plusDays(5), newArrayList());
        result.put(weekStart.plusDays(6), newArrayList());

        result.get(weekStart).add(newArrayList(
                withLessonNumber(cancelledLesson(), 7)
        ));

        result.get(weekStart.plusDays(1)).add(newArrayList(
                withLessonNumber(substitutionLesson(), 7)
        ));

        //Empty lesson
        result.get(weekStart.plusDays(2)).remove(2);

        return Single.just(result);
    }

    private ImmutableJsonLesson withLessonNumber(ImmutableJsonLesson lesson, int lessonNo) {
        List<PlainLesson> plainLessons = repository.getList(PlainLesson.class);
        PlainLesson plainLesson = plainLessons.get(lessonNo % plainLessons.size());
        Subject subject = getById(Subject.class, plainLesson.subject()).blockingGet();
        Teacher teacher = getById(Teacher.class, plainLesson.teacher()).blockingGet();

        LocalTime startTime = LocalTime.parse("08:00");
        LocalTime lessonStart = startTime.plusHours(lessonNo);
        LocalTime lessonEnd = lessonStart.plusMinutes(45);

        return lesson
                .withSubject(LessonSubject.fromSubject(subject))
                .withTeacher(LessonTeacher.fromTeacher(teacher))
                .withLessonNo(lessonNo)
                .withHourFrom(lessonStart)
                .withHourTo(lessonEnd);
    }

    private ImmutableJsonLesson cancelledLesson() {
        return templates.jsonLesson()
                .withSubject(templates.lessonSubject().withName("Alchemia"))
                .withCancelled(true);
    }

    private ImmutableJsonLesson substitutionLesson() {
        return templates.jsonLesson()
                .withTeacher(templates.lessonTeacher()
                        .withFirstName("Michał")
                        .withLastName("Oryginalny"))
                .withSubject(templates.lessonSubject().withName("Marynowanie śledzi"))
                .withSubstitutionClass(true)
                .withSubstitutionNote("Zabrakło śledzi")
                .withOrgTeacher(repository.getList(Teacher.class).get(3).id())
                .withOrgSubject(repository.getList(Subject.class).get(3).id())
                .withOrgDate(LocalDate.parse("2017-01-01"));
    }

    public <T extends Persistable> Single<List<T>> getAll(Class<T> clazz) {
        EntityInfo info = EntityInfos.infoFor(clazz);
        if (info.single()) {
            //noinspection unchecked
            return getObject(info.endpoint(), info.topLevelName(), clazz)
                    .map(Lists::newArrayList);
        } else {
            return getList(info.endpoint(), info.topLevelName(), clazz);
        }
    }

    public <T> Single<T> getObject(String endpoint, String topLevelName, Class<T> clazz) {
        return Single.just(repository.getObject(clazz));
    }

    public <T> Single<List<T>> getList(String endpoint, String topLevelName, Class<T> clazz) {
        return Single.just(repository.getList(clazz));
    }

    @Override
    public <T extends Persistable & Identifiable> Single<T> getById(Class<T> clazz, String id) {
        return StreamSupport.stream(repository.getList(clazz))
                .filter(e -> e.id().equals(id))
                .findFirst()
                .map(Single::just)
                .get();
    }
}
