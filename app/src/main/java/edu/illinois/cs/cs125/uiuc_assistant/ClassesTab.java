package edu.illinois.cs.cs125.uiuc_assistant;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.Calendar;

public class ClassesTab extends Fragment{

    private CalendarView mCalendarView;
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
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                Log.d("dayOfweek",dayOfWeek + " ");

//                Intent intent = new Intent(getActivity(), CalendarDay.class);
//                intent.putExtra("day", dayOfWeek);
//                startActivity(intent);

            }
        });
        return rootView;
    }
}
