package edu.illinois.cs.cs125.uiuc_assistant;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.Date;

public class ClassesTab extends Fragment{

    private CalendarView mCalendarView;

    private FloatingActionButton addClass;
//    OnHeadlineSelectedListener mCallback;
//
//    public interface OnHeadlineSelectedListener {
//        public void OnArticleSelected(Calendar date);
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.classestab, container, false);
        mCalendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
//                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//                Log.d("dayOfweek",dayOfWeek + " ");

//                Intent intent = new Intent(getActivity(), CalendarDay.class);
//                intent.putExtra("day", dayOfWeek);
//                startActivity(intent);
                //mCallback.OnArticleSelected(calendar);

                Bundle date = new Bundle();
                date.putInt("year",year);
                date.putInt("month",month);
                date.putInt("day",dayOfMonth);

                CalendarDay calendarDay = new CalendarDay();
                getFragmentManager().beginTransaction().remove(calendarDay).commit();
                calendarDay.setArguments(date);
                getFragmentManager().beginTransaction().replace(R.id.fragment, calendarDay).commit();

                //Log.d("check", "happens" + date.getInt("day"));


//                CalendarDay calendarDay = new CalendarDay();
//                calendarDay.setupSchedule(calendar);
            }
        });

        addClass = (FloatingActionButton) rootView.findViewById(R.id.add_class_btn);
        addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddClass.class));
            }
        });
        return rootView;
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//
//        try {
//            mCallback = (OnHeadlineSelectedListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString() + " must implement OnHeadlineSelectedListener");
//        }
//
//    }
}
