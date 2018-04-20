package edu.illinois.cs.cs125.uiuc_assistant;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddClass extends Activity{

    private int[] days = {0, 0, 0, 0, 0, 0, 0};
    private CheckBox sun;
    private CheckBox mon;
    private CheckBox tue;
    private CheckBox wed;
    private CheckBox thu;
    private CheckBox fri;
    private CheckBox sat;


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

        Button create = (Button) findViewById(R.id.CreateButton);
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

        Button cancel = (Button) findViewById(R.id.cancelButton);
        create.setOnClickListener(new View.OnClickListener() {
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
        EditText credit = (EditText) findViewById(R.id.creditHours);
        float creditHours = Float.valueOf(credit.getText().toString());
        EditText instructorIn = (EditText) findViewById(R.id.Instructor);
        String instructor = instructorIn.getText().toString();
        EditText buildingIn = (EditText) findViewById(R.id.BuildingName);
        String building = buildingIn.getText().toString();
        EditText roomIn = (EditText) findViewById(R.id.roomNo);
        String room = roomIn.getText().toString();
        sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[0] = 1;
                }
            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[1] = 1;
                }
            }
        });
        tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[2] = 1;
                }
            }
        });
        wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[3] = 1;
                }
            }
        });
        thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[4] = 1;
                }
            }
        });
        fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[5] = 1;
                }
            }
        });
        sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sun.isChecked()) {
                    days[6] = 1;
                }
            }
        });
        Class class_1 = new Class(title, code, creditHours, instructor, building, room, days);

        writeFile(class_1);
        //UpdateSchedule();
    }

    public void writeFile(Class temp) {
        File new_class = new File(getFilesDir(), temp.code);
        FileOutputStream outputStream;

        try {
                outputStream = openFileOutput(temp.code, Context.MODE_APPEND);
                outputStream.write(temp.title.getBytes());
            outputStream.write(temp.code.getBytes());
            outputStream.write((byte) temp.creditHours);
            outputStream.write(temp.instructor.getBytes());
            outputStream.write(temp.building.getBytes());
            outputStream.write(temp.room.getBytes());
            outputStream.write((byte) temp.getDays()[0]);
            outputStream.write((byte) temp.getDays()[1]);
            outputStream.write((byte) temp.getDays()[2]);
            outputStream.write((byte) temp.getDays()[3]);
            outputStream.write((byte) temp.getDays()[4]);
            outputStream.write((byte) temp.getDays()[5]);
            outputStream.write((byte) temp.getDays()[6]);
            outputStream.flush();
            outputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Add class failed", Toast.LENGTH_LONG);
        }
    }
}
