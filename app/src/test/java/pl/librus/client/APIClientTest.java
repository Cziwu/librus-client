package pl.librus.client;

import com.google.common.io.Resources;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import pl.librus.client.api.APIClient;
import pl.librus.client.api.Event;
import pl.librus.client.api.EventCategory;
import pl.librus.client.datamodel.Attendance;
import pl.librus.client.datamodel.AttendanceType;
import pl.librus.client.datamodel.Grade;
import pl.librus.client.datamodel.GradeCategory;
import pl.librus.client.datamodel.GradeComment;
import pl.librus.client.datamodel.Me;
import pl.librus.client.datamodel.PlainLesson;
import pl.librus.client.datamodel.SchoolWeek;
import pl.librus.client.datamodel.Subject;
import pl.librus.client.datamodel.Teacher;


public class APIClientTest {
    @Test
    public void shouldParseTeachers() throws IOException {
        //given
        String fileName = "Teachers.json";
        List<Teacher> res = APIClient.parseList(readFile(fileName), "Users", Teacher.class);
    }

    @Test
    public void shouldParseMe() throws IOException {
        //given
        Me res = APIClient.parseObject(readFile("Me.json"), "Me", Me.class);
    }

    @Test
    public void shouldParseTimetable() throws IOException {
        //given
        SchoolWeek res = APIClient.parseObject(readFile("Timetable.json"), "Timetable", SchoolWeek.class);
    }

    @Test
    public void shouldParseGrades() throws IOException {
        //given
        List<Grade> res = APIClient.parseList(readFile("Grades.json"), "Grades",Grade.class);
    }

    @Test
    public void shouldParseCategories() throws IOException {
        //given
        List<GradeCategory> res = APIClient.parseList(readFile("GradeCategories.json"), "Categories",GradeCategory.class);
    }


    @Test
    public void shouldParseComment() throws IOException {
        //given
        List<GradeComment> res = APIClient.parseList(readFile("GradeComments.json"), "Comments",GradeComment.class);
    }

    @Test
    public void shouldParseLessons() throws IOException {
        //given
        List<PlainLesson> res = APIClient.parseList(readFile("Lessons.json"), "Lessons",PlainLesson.class);
    }

    @Test
    public void shouldParseHomeWorks() throws IOException {
        //given
        List<Event> res = APIClient.parseList(readFile("HomeWorks.json"), "HomeWorks",Event.class);
    }

    @Test
    public void shouldParseHomeWorkCategories() throws IOException {
        //given
        List<EventCategory> res = APIClient.parseList(readFile("HomeWorkCategories.json"), "Categories",EventCategory.class);
    }

    @Test
    public void shouldParseAttendances() throws IOException {
        //given
        List<Attendance> res = APIClient.parseList(readFile("Attendances.json"), "Attendances",Attendance.class);
    }

    @Test
    public void shouldParseAttendanceTypes() throws IOException {
        //given
        List<AttendanceType> res = APIClient.parseList(readFile("AttendanceTypes.json"), "Types",AttendanceType.class);
    }

    @Test
    public void shouldParseSubject() throws IOException {
        //given
        APIClient.parseList(readFile("Subjects.json"), "Subjects", Subject.class);
    }
    static String readFile(String fileName) {
        try {
            return Resources.toString(Resources.getResource(fileName), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
