package edu.illinois.cs.cs125.uiuc_assistant;

//import android.app.Fragment;
//import android.os.Bundle;
////import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
////import android.widget.TextView;
////
////import java.util.Calendar;
////import java.util.Date;
//
//public class CalendarDay extends Fragment {
//
////    private int dayOfWeek;
////    private Date date;
////    TextView view;
//    //@Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.daily_schedule, container, false);
//
////        Intent incomingIntent = getActivity().getIntent();
////        int dayOfWeek = incomingIntent.getIntExtra("day",0);
//
//        //view = (TextView) rootView.findViewById(R.id.dayView);
//
//        return rootView;
//    }
//
////    public View setupSchedule(Calendar calendar) {
////
////        view.setText("day is " + calendar.get(Calendar.DAY_OF_WEEK));
////        return null;
////    }
//}
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class CalendarDay extends Fragment{

    private Calendar calendar;
    RelativeLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.daily_schedule, container, false);
        Bundle date = this.getArguments();
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.set(date.getInt("year"), date.getInt("month"), date.getInt("day"));
            //Log.d("check", "happens");
        } else {
            calendar = Calendar.getInstance();
        }

        TextView dateView = (TextView) rootView.findViewById(R.id.dayView);
        TextView dayOfWeekView = (TextView) rootView.findViewById(R.id.dayOfWeekView);
        String dayOfWeek = "not found";
        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                dayOfWeek = "SUNDAY";
                break;
            case 2:
                dayOfWeek = "MONDAY";
                break;
            case 3:
                dayOfWeek = "TUESDAY";
                break;
            case 4:
                dayOfWeek = "WEDNESDAY";
                break;
            case 5:
                dayOfWeek = "THURSDAY";
                break;
            case 6:
                dayOfWeek = "FRIDAY";
                break;
            case 7:
                dayOfWeek = "SATURDAY";
                break;
        }
        dayOfWeekView.setText(dayOfWeek);

        String month = "not found";
        switch (calendar.get(Calendar.MONTH)) {
            case 0:
                month = "January";
                break;
            case 1:
                month = "February";
                break;
            case 2:
                month = "March";
                break;
            case 3:
                month = "April";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "June";
                break;
            case 6:
                month = "July";
                break;
            case 7:
                month = "August";
                break;
            case 8:
                month = "September";
                break;
            case 9:
                month = "October";
                break;
            case 10:
                month = "November";
                break;
            case 11:
                month = "December";
                break;
        }

        dateView.setText(month + " " + calendar.get(Calendar.DAY_OF_MONTH) + ", " + calendar.get(Calendar.YEAR));

        layout = (RelativeLayout) rootView.findViewById(R.id.layout);
        Button newButton = new Button(getActivity());
        newButton.setText("Intro to Computer Programming" + "\n" + "CS125");
        int top = 8 * 60 + 0;
        int bottom = 8 * 60 + 50;
        //newButton.setLayoutParams();
        newButton.setTop(top);
        newButton.setLeft(50);
        newButton.setHeight(bottom - top);

        Log.d("check","happened");

        //Log.d("calenderDay", "the day is " + calendar.get(Calendar.DAY_OF_WEEK));

        List<Class> classes = CalendarUpdater.updateCalendar(calendar, this.getActivity());
        if (classes.size() == 0) {
            return rootView;
        }

        Log.d("check", classes.size() + "");

        for (int i = 0; i < classes.size(); i++) {
//            Class temp = classes.get(i);
//            layout = (RelativeLayout) rootView.findViewById(R.id.layout);
//            Button newButton = new Button(getActivity());
//            newButton.setText(temp.title + "\n" + temp.code);
//            int top = temp.getClassTime()[0] * 60 + temp.getClassTime()[1];
//            int bottom = temp.getClassTime()[2] * 60 + temp.getClassTime()[3];
//            //newButton.setLayoutParams();
//            newButton.setTop(top);
//            newButton.setLeft(50);
//            newButton.setHeight(bottom - top);
        }


        return rootView;
    }
}