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
import java.lang.reflect.Array;
import java.util.Arrays;
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

        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("check", "clicked");
                if (sun.isChecked()) {
                    days[0] = 1;
                    Log.d("check", "checked");
                } else {
                    days[0] = 0;
                    Log.d("check", "unchecked");
                }
                Log.d("check",Arrays.toString(days));
            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mon.isChecked()) {
                    days[1] = 1;
                } else {
                    days[1] = 0;
                }
            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tue.isChecked()) {
                    days[2] = 1;
                } else {
                    days[2] = 0;
                }
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wed.isChecked()) {
                    days[3] = 1;
                } else {
                    days[3] = 0;
                }
            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thu.isChecked()) {
                    days[4] = 1;
                } else {
                    days[4] = 0;
                }
            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fri.isChecked()) {
                    days[5] = 1;
                } else {
                    days[5] = 0;
                }
            }
        });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sat.isChecked()) {
                    days[6] = 1;
                } else {
                    days[6] = 0;
                }
            }
        });

        db = new MyClassDBHandler(this,null,null,1);
        //db.onUpgrade(db.getWritableDatabase(),1,1);
        Log.d("check", db.readClass("cs125").code);


        Button create = (Button) findViewById(R.id.CreateButton);
        Button cancel = (Button) findViewById(R.id.cancelButton);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createClass();
                    finish();
                } catch (IncorrectArgumentException ia) {
                    Toast.makeText(AddClass.this, ia.getMessage(), Toast.LENGTH_LONG).show();

                } catch (NumberFormatException n) {
                    Toast.makeText(AddClass.this, "date or time data fields are invalid", Toast.LENGTH_LONG).show();
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

    public void createClass() throws IncorrectArgumentException {
        EditText titleIn = (EditText) findViewById(R.id.classTitle);
        String title = titleIn.getText().toString();

        if (title == null || title.equals("")) {
           //Log.d("check", "empty title");
           throw new IncorrectArgumentException("empty title! the title is a required field");
        }
        //Log.d("check", "empty");
        EditText codeIn = (EditText) findViewById(R.id.classCode);
        String code = codeIn.getText().toString();

        if (code == null || code.equals("")) {
            //Log.d("check", "empty code");
            throw new IncorrectArgumentException("empty class code! the class code is a required field");
        }
        EditText credit = (EditText) findViewById(R.id.creditHours);
        if (credit.getText().toString().equals("")) {
            throw new IncorrectArgumentException("empty class credit hours! the class credit hours is a required field");
        }
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
        if (starthourIn.getText().toString().equals("") || startMinIn.getText().toString().equals("") ||
                endhourIn.getText().toString().equals("") || endMinIn.getText().toString().equals("")) {
            throw new IncorrectArgumentException("empty class times! the class times are required fields");
        }
        int[] time = {Integer.parseInt(starthourIn.getText().toString()),
                Integer.parseInt(startMinIn.getText().toString()),
                Integer.parseInt(endhourIn.getText().toString()),
                Integer.parseInt(endMinIn.getText().toString())};
        if (time[0] >= 24 || time[0] < 0 || time[2] >= 24 || time[2] < 0 || time[1] >= 60 || time[1] < 0 ||
                time[3] >= 60 || time[3] < 0) {
            throw new IncorrectArgumentException("invalid class times! the class times must be in 24-hour format");
        }
        EditText startmonthIn = (EditText) findViewById(R.id.startMonth);
        EditText startDayIn = (EditText) findViewById(R.id.StartDay);
        EditText startYearIn = (EditText) findViewById(R.id.StartYear);
        EditText endmonthIn = (EditText) findViewById(R.id.endMonth);
        EditText endDayIn = (EditText) findViewById(R.id.endDay);
        EditText endYearIn = (EditText) findViewById(R.id.endYear);
        if (startmonthIn.getText().toString().equals("") || startDayIn.getText().toString().equals("") ||
                startYearIn.getText().toString().equals("") || endmonthIn.getText().toString().equals("") ||
                endDayIn.getText().toString().equals("") || endYearIn.getText().toString().equals("")) {
            throw new IncorrectArgumentException("empty class term dates! the class term dates are required fields");
        }
        int[] term = {Integer.parseInt(startmonthIn.getText().toString()) - 1,
                Integer.parseInt(startDayIn.getText().toString()),
                Integer.parseInt(startYearIn.getText().toString()),
                Integer.parseInt(endmonthIn.getText().toString()) - 1,
                Integer.parseInt(endDayIn.getText().toString()),
                Integer.parseInt(endYearIn.getText().toString())};
//        if (term[0] >= 12 || term[0] < 0 || term[3] >= 12 || term[3] < 0 || term[1] >= 12 || term[0] < 0 ||) {
//            throw new IncorrectArgumentException("invalid class times! the class times must be in 24-hour format");
//        }
        Calendar start = Calendar.getInstance();
        start.setLenient(false);
        start.set(term[2], term[0], term[1]);
        try {
            start.getTime();
        } catch (Exception e) {
            throw new IncorrectArgumentException("invalid start date");
        }
        Calendar end = Calendar.getInstance();
        end.setLenient(false);
        end.set(term[5], term[3], term[4]);
        try {
            end.getTime();
        } catch (Exception e) {
            throw new IncorrectArgumentException("invalid end date");
        }

        //Log.d("check", Arrays.toString(term));

        Class class_1 = new Class(title, code, creditHours, instructor, building, room, days, time, term);

        //Log.d("check", Arrays.toString(class_1.getDays()));


        db.addClasses(class_1);

        //writeFile(class_1);

        //boolean isSame = class_1.code ==
        //Log.d("check",db.dataBaseToString());
        //UpdateSchedule();

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
