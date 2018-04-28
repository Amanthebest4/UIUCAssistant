package edu.illinois.cs.cs125.uiuc_assistant;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CalendarUpdater {

    private static MyClassDBHandler db;

    public static List<Class> updateCalendar(Calendar calendar, Activity activity) {
        db = new MyClassDBHandler(activity, null, null, 1);
        //Log.d("check", db.readClass("cs125").code);
        List<Class> classList = db.getAllClasses();
        //Log.d("check",classList.size() + "db");

        //Log.d("check",db.readClass("cs125").code);
        //Log.d("check", "happens");
        List<Class> daySchedule = new ArrayList<>();
        for (int i = 0; i < classList.size(); i++) {
            Class temp = classList.get(i);
            //Log.d("check", Arrays.toString(db.getAllClasses().get(0).getClassTime()));
            //Log.d("check", Arrays.toString(db.readClass(temp.code).getTerm()));

            //Log.d("check",temp.code);
            //Log.d("check", temp.getDays()[calendar.get(Calendar.DAY_OF_WEEK)] + "days");
            //Log.d("check", Arrays.toString(temp.getDays()));
            if (temp.getDays()[calendar.get(Calendar.DAY_OF_WEEK) - 1] == 1) {
                //Log.d("check", temp.getStartDate().get(Calendar.MONTH) + "/" + temp.getStartDate().get(Calendar.DAY_OF_MONTH) + "/" + temp.getStartDate().get(Calendar.YEAR));
                if (calendar.compareTo(temp.getStartDate()) > 0 && calendar.compareTo(temp.getEndDate()) < 0) {
                    //Log.d("check", "happens");
                    daySchedule.add(temp);
                }
            }
        }

        //Log.d("check",daySchedule.size() + "sc");

        return daySchedule;
    }

    public static void clearCalendar(Activity activity) {
        db = new MyClassDBHandler(activity, null, null, 1);
        db.onUpgrade(db.getWritableDatabase(),1,1);
        Log.d("check",  "calendar cleared");
    }
}
