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

import java.util.ArrayList;
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

        layout = (RelativeLayout) rootView.findViewById(R.id.layout_schedule);
        final float dp = rootView.getResources().getDisplayMetrics().density;
        //Button newButton = new Button(getActivity());
//        Button b1 = new Button(getActivity());
//        Button b2 = new Button(getActivity());
//        Button b3 = new Button(getActivity());
//        Button b4 = new Button(getActivity());
//        Button b5 = new Button(getActivity());
//        Button b6 = new Button(getActivity());
//        Button b7 = new Button(getActivity());
//        Button b8 = new Button(getActivity());
//        Button b9 = new Button(getActivity());
//        Button b10 = new Button(getActivity());
//        Button b11 = new Button(getActivity());
//        Button b12 = new Button(getActivity());









        //Log.d("check","happened");

        //Log.d("calenderDay", "the day is " + calendar.get(Calendar.DAY_OF_WEEK));

        List<Class> classes = CalendarUpdater.updateCalendar(calendar, this.getActivity());
        //Log.d("check", classes.size() + "");
        if (classes.size() == 0) {
            return rootView;
        }

        //Log.d("check", classes.size() + "");


        List<Button> schedule = new ArrayList<>();

//        View.OnClickListener btn = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                switch (v.getId()) {
//                    case 1:
//
//                        break;
//                    case 2:
//
//                        break;
//                    case 3:
//
//                        break;
//                    case 4:
//
//                        break;
//                    case 5:
//
//                        break;
//                    case 6:
//
//                        break;
//                    case 7:
//
//                        break;
//                    case 8:
//
//                        break;
//                    case 9:
//
//                        break;
//                    case 10:
//
//                        break;
//                    case 11:
//
//                        break;
//                    case 12:
//
//                        break;
//                }
//            }
//        };

        for (int i = 0; i < classes.size(); i++) {
            final Class temp = classes.get(i);
            //Log.d("check",temp.code);
            Button newButton = new Button(getActivity());
            newButton.setId(i+1);
            newButton.setText(temp.title + "\n" + temp.code);

            int top = temp.getClassTime()[0] * 60 + temp.getClassTime()[1];
            int bottom = temp.getClassTime()[2] * 60 + temp.getClassTime()[3];
            RelativeLayout.LayoutParams r1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) ((top - bottom) * dp));
            r1.topMargin = (int) (top * dp);
            newButton.setLayoutParams(r1);
            newButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("check", temp.title + " was clicked");
                }
            });
            ((RelativeLayout) rootView.findViewById(R.id.layout_schedule)).addView(newButton);
        }



        return rootView;
    }
}