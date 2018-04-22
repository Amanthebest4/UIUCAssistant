package edu.illinois.cs.cs125.uiuc_assistant;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarUpdater {

    public static List<Class> updateCalendar(Calendar calendar, Activity activity) {
        MyClassDBHandler db = new MyClassDBHandler(activity, null, null, 1);
        //Log.d("check", db.readClass("cs125").code);
        List<Class> classList = db.getAllClasses();
        //Log.d("check", "happens");
        List<Class> daySchedule = new ArrayList<>();
        for (int i = 0; i < classList.size(); i++) {
            Class temp = classList.get(i);
            if (temp.getDays()[calendar.get(Calendar.DAY_OF_WEEK) - 1] == 1 && calendar.compareTo(temp.getStartDate()) > 0 && calendar.compareTo(temp.getEndDate()) < 0) {
                daySchedule.add(temp);
            }
        }

        return daySchedule;
    }
}
