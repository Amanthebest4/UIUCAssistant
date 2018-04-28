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
import android.content.Intent;
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
import java.util.Arrays;
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
//        Button newButton = new Button(getActivity());
//
//        newButton.setId(0);
//        newButton.setText("cs125" + "\n" + "cs125");
//
//        int top_ = 8 * 60 + 0;
//        int bottom_ = 8 * 60 + 50;
//        RelativeLayout.LayoutParams r2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) ((top_ - bottom_) * dp));
//        r2.topMargin = (int) (top_ * dp);
//        newButton.setLayoutParams(r2);
//        newButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Log.d("check", temp.title + " was clicked");
//                Intent intent = new Intent(getActivity(),ClassDetail.class);
//                intent.putExtra("Current_Class", "cs125");
//                startActivity(intent);
//            }
//        });
//        layout.addView(newButton);








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

        List<Button> buttons = new ArrayList<>();

        for (int i = 0; i < classes.size(); i++) {
            final Class temp = classes.get(i);
            //Log.d("check",temp.code);
            buttons.add(new Button(getActivity()));
            //Button newButton = new Button(getActivity());
//            newButton.setId(i+1);
//            newButton.setText(temp.title + "\n" + temp.code);
//
//            int top = temp.getClassTime()[0] * 60 + temp.getClassTime()[1];
//            int bottom = temp.getClassTime()[2] * 60 + temp.getClassTime()[3];
//            RelativeLayout.LayoutParams r1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) ((top - bottom) * dp));
//            r1.topMargin = (int) (top * dp);
//            newButton.setLayoutParams(r1);
//            newButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //Log.d("check", temp.title + " was clicked");
//                    Intent intent = new Intent(getActivity(),ClassDetail.class);
//                    intent.putExtra("Current_Class", temp.code);
//                    startActivity(intent);
//                }
//            });
//            layout.addView(newButton);
            //Log.d("check", i + ", " + temp.code);
            buttons.get(i).setId(i+1);
            buttons.get(i).setText(temp.title + "\n" + temp.code);

            //Log.d("check", Arrays.toString(temp.getClassTime()));

            int top = temp.getClassTime()[0] * 60 + temp.getClassTime()[1];
            int bottom = temp.getClassTime()[2] * 60 + temp.getClassTime()[3];
            //Log.d("check", top + " - " +  bottom);
            RelativeLayout.LayoutParams r1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) ((top - bottom) * dp));
            r1.topMargin = (int) (top * dp);
            buttons.get(i).setLayoutParams(r1);
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("check", temp.title + " was clicked");
                    Intent intent = new Intent(getActivity(),ClassDetail.class);
                    intent.putExtra("Current_Class", temp.code);
                    startActivity(intent);
                }
            });
            layout.addView(buttons.get(i));
            //buttons.add(newButton);
        }

//        try {
//            layout.addView(buttons.get(0));
//            Log.d("check", "0, " + buttons.get(0).getText());
//            //layout.addView(buttons.get(1));
//            Log.d("check", "1, " + buttons.get(1).getText());
//            layout.addView(buttons.get(2));
//            layout.addView(buttons.get(3));
//            layout.addView(buttons.get(4));
//            layout.addView(buttons.get(5));
//            layout.addView(buttons.get(6));
//            layout.addView(buttons.get(7));
//            layout.addView(buttons.get(8));
//            layout.addView(buttons.get(9));
//            layout.addView(buttons.get(10));
//            layout.addView(buttons.get(11));
//            layout.addView(buttons.get(12));
//
//        } catch (Exception e) {
//
//        }

//        for (int i = 0; i < buttons.size(); i++) {
//            ((RelativeLayout) rootView.findViewById(R.id.layout_schedule)).addView(buttons.get(i));
//        }



        return rootView;
    }
}