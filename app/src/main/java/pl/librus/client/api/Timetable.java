package pl.librus.client.api;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Timetable implements Serializable {
    private final String TAG = "librus-client-log";
    private final Map<LocalDate, SchoolDay> timetable = new HashMap<>();


    public Timetable() {
    }

//    private void parseJSON(JSONObject data, JSONObject events) throws JSONException {
//
//        Iterator iterator = data.keys();
//
//        while (iterator.hasNext()) {
//
//            String key = (String) iterator.next();
//            LocalDate date = LocalDate.parse(key);
//            SchoolDay schoolDay = new SchoolDay(data.getJSONArray(key), date);
//
//            if (!schoolDay.isEmpty()) {
//
//                if (events.has(key)) {
//
//                    for (int i = 0; i < events.getJSONArray(key).length(); i++) {
//
//                        JSONObject event = events.getJSONArray(key).getJSONObject(i);
//
//                        Lesson lesson = schoolDay.getLesson(event.getInt("LessonNo"));
//
//                        if (lesson != null) {
////                            lesson.setEvent(new Event(event.getString("Description"), event.getString("Category")));
//                        }
//
//                    }
//                }
//                timetable.put(date, schoolDay);
//            }
//        }
//    }

    public void addSchoolDay(SchoolDay schoolDay) {
        timetable.put(schoolDay.getDate(), schoolDay);
    }

    Map<LocalDate, SchoolDay> getTimetable() {
        return timetable;
    }

    public SchoolDay getSchoolDay(LocalDate date) {
        return timetable.get(date);
    }
}