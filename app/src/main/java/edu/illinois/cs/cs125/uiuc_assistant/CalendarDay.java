package edu.illinois.cs.cs125.uiuc_assistant;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CalendarDay extends Fragment {

    TextView view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.daily_schedule, container, false);

        Intent incomingIntent = getActivity().getIntent();
        int dayOfWeek = incomingIntent.getIntExtra("day",0);

        view = (TextView) rootView.findViewById(R.id.dayView);
        view.setText("day is " + dayOfWeek);
        return rootView;
    }
}
