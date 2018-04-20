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
import android.widget.TextView;

import java.util.Calendar;

public class CalendarDay extends Fragment{

    private Calendar calendar;
    TextView view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.daily_schedule, container, false);
        Bundle date = this.getArguments();
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.set(date.getInt("year"), date.getInt("month"), date.getInt("day"));
            Log.d("check", "happens");
        } else {
            calendar = Calendar.getInstance();
        }

        //Log.d("calenderDay", "the day is " + calendar.get(Calendar.DAY_OF_WEEK));

        view = (TextView) rootView.findViewById(R.id.dayView);
        view.setText("the day is " + calendar.get(Calendar.DAY_OF_WEEK));
        return rootView;
    }
}