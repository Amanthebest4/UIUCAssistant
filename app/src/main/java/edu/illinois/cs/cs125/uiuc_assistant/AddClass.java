package edu.illinois.cs.cs125.uiuc_assistant;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class AddClass extends Activity{

    private int[] days = {0, 0, 0, 0, 0, 0, 0};
    private CheckBox sun;
    private CheckBox mon;
    private CheckBox tue;
    private CheckBox wed;
    private CheckBox thu;
    private CheckBox fri;
    private CheckBox sat;

    private MyClassDBHandler db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.classesadd);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels - 50;
        int height = dm.heightPixels;

        getWindow().setLayout(width, height);

        sun = (CheckBox) findViewById(R.id.sun);
        mon = (CheckBox) findViewById(R.id.mon);
        tue = (CheckBox) findViewById(R.id.tue);
        wed = (CheckBox) findViewById(R.id.wed);
        thu = (CheckBox) findViewById(R.id.thu);
        fri = (CheckBox) findViewById(R.id.fri);
        sat = (CheckBox) findViewById(R.id.sat);

        db = new MyClassDBHandler(this,null,null,1);
        db.onUpgrade(db.getWritableDatabase(),1,1);
        Log.d("check", db.readClass("cs125").code);


        Button create = (Button) findViewById(R.id.CreateButton);
        Button cancel = (Button) findViewById(R.id.cancelButton);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createClass();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void createClass() throws IOException {
        EditText titleIn = (EditText) findViewById(R.id.classTitle);
        String title = titleIn.getText().toString();
        EditText codeIn = (EditText) findViewById(R.id.classCode);
        String code = codeIn.getText().toString();

        if (code == null || code == "") {
            finish();
        }
        EditText credit = (EditText) findViewById(R.id.creditHours);
        float creditHours = Float.valueOf(credit.getText().toString());
        EditText instructorIn = (EditText) findViewById(R.id.Instructor);
        String instructor = instructorIn.getText().toString();
        EditText buildingIn = (EditText) findViewById(R.id.BuildingName);
        String building = buildingIn.getText().toString();
        EditText roomIn = (EditText) findViewById(R.id.roomNo);
        String room = roomIn.getText().toString();
        EditText starthourIn = (EditText) findViewById(R.id.startHour);
        EditText startMinIn = (EditText) findViewById(R.id.startMin);
        EditText endhourIn = (EditText) findViewById(R.id.endHour);
        EditText endMinIn = (EditText) findViewById(R.id.endMinute);
        int[] time = {Integer.parseInt(starthourIn.getText().toString()),
                Integer.parseInt(startMinIn.getText().toString()),
                Integer.parseInt(endhourIn.getText().toString()),
                Integer.parseInt(endMinIn.getText().toString())};
        EditText startmonthIn = (EditText) findViewById(R.id.startMonth);
        EditText startDayIn = (EditText) findViewById(R.id.StartDay);
        EditText startYearIn = (EditText) findViewById(R.id.StartYear);
        EditText endmonthIn = (EditText) findViewById(R.id.endMonth);
        EditText endDayIn = (EditText) findViewById(R.id.endDay);
        EditText endYearIn = (EditText) findViewById(R.id.endYear);
        int[] term = {Integer.parseInt(startmonthIn.getText().toString()),
                Integer.parseInt(startDayIn.getText().toString()),
                Integer.parseInt(startYearIn.getText().toString()),
                Integer.parseInt(endmonthIn.getText().toString()),
                Integer.parseInt(endDayIn.getText().toString()),
                Integer.parseInt(endYearIn.getText().toString())};
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[0] = 1;
                } else {
                    days[0] = 0;
                }
            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[1] = 1;
                } else {
                    days[1] = 0;
                }
            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[2] = 1;
                } else {
                    days[2] = 0;
                }
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[3] = 1;
                } else {
                    days[3] = 0;
                }
            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[4] = 1;
                } else {
                    days[4] = 0;
                }
            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[5] = 1;
                } else {
                    days[5] = 0;
                }
            }
        });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[6] = 1;
                } else {
                    days[6] = 0;
                }
            }
        });
        Class class_1 = new Class(title, code, creditHours, instructor, building, room, days, time, term);


        db.addClasses(class_1);

        //writeFile(class_1);

        //boolean isSame = class_1.code ==
        //Log.d("check",db.dataBaseToString());
        //UpdateSchedule();

        finish();
    }

//    public void writeFile(Class temp) {
//        int fileNumber = 0;
//        while (fileNumber)
//        File new_class = new File(getFilesDir(), "class " + fileNumber);
//        FileOutputStream outputStream;
//
//        try {
//                outputStream = openFileOutput("class " + fileNumber, Context.MODE_APPEND);
//                outputStream.write(temp.title.getBytes());
//            outputStream.write(temp.code.getBytes());
//            outputStream.write((byte) temp.creditHours);
//            outputStream.write(temp.instructor.getBytes());
//            outputStream.write(temp.building.getBytes());
//            outputStream.write(temp.room.getBytes());
//            outputStream.write((byte) temp.getDays()[0]);
//            outputStream.write((byte) temp.getDays()[1]);
//            outputStream.write((byte) temp.getDays()[2]);
//            outputStream.write((byte) temp.getDays()[3]);
//            outputStream.write((byte) temp.getDays()[4]);
//            outputStream.write((byte) temp.getDays()[5]);
//            outputStream.write((byte) temp.getDays()[6]);
//            outputStream.flush();
//            outputStream.close();
//        } catch(Exception e) {
//            e.printStackTrace();
//            Toast.makeText(this, "Add class failed", Toast.LENGTH_LONG);
//        }
//    }
}
