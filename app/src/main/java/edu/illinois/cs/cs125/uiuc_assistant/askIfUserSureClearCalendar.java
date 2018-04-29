package edu.illinois.cs.cs125.uiuc_assistant;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class askIfUserSureClearCalendar extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ask_if_sure_clear_calendar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int)(dm.widthPixels * 0.9);
        int height = (int)(dm.heightPixels * 0.3);

        getWindow().setLayout(width, height);

        Button yes = (Button) findViewById(R.id.yesBtn);
        Button cancel = (Button) findViewById(R.id.noBtn);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarUpdater.clearCalendar(askIfUserSureClearCalendar.this);
                finish();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
