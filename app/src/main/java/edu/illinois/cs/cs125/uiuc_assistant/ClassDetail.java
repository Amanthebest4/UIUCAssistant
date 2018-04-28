package edu.illinois.cs.cs125.uiuc_assistant;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;

public class ClassDetail extends Activity {

    //private int[] days = {0, 0, 0, 0, 0, 0, 0};


    private MyClassDBHandler db;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.class_detail);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int) (dm.widthPixels * 0.8);
        int height = (int) (dm.heightPixels * 0.8);

        getWindow().setLayout(width, height);

        db = new MyClassDBHandler(this, null, null, 1);

        String class_code = getIntent().getStringExtra("Current_Class");

        final Class class_1 = db.readClass(class_code);


        CheckBox sun = (CheckBox) findViewById(R.id.sunDetail);
        CheckBox mon = (CheckBox) findViewById(R.id.monDetail);
        CheckBox tue = (CheckBox) findViewById(R.id.tueDetail);
        CheckBox wed = (CheckBox) findViewById(R.id.wedDetail);
        CheckBox thu = (CheckBox) findViewById(R.id.thuDetail);
        CheckBox fri = (CheckBox) findViewById(R.id.friDetail);
        CheckBox sat = (CheckBox) findViewById(R.id.satDetail);
        TextView title = (TextView) findViewById(R.id.classTitleDetail);
        TextView code = (TextView) findViewById(R.id.classCodeDetail);
        TextView creditHours = (TextView) findViewById(R.id.creditHoursDetail);
        TextView startHour = (TextView) findViewById(R.id.startHourDetail);
        TextView startMin = (TextView) findViewById(R.id.startMinDetail);
        TextView endHour = (TextView) findViewById(R.id.endHourDetail);
        TextView endMin = (TextView) findViewById(R.id.endMinDetail);
        TextView startMonth = (TextView) findViewById(R.id.startMonthDetail);
        TextView startDay = (TextView) findViewById(R.id.startDayDetail);
        TextView startYear = (TextView) findViewById(R.id.startYearDetail);
        TextView endMonth = (TextView) findViewById(R.id.endMonthDetail);
        TextView endDay = (TextView) findViewById(R.id.endDayDetail);
        TextView endYear = (TextView) findViewById(R.id.endYearDetail);
        TextView building = (TextView) findViewById(R.id.BuildingNameDetail);
        TextView room = (TextView) findViewById(R.id.roomNoDetail);
        TextView instructor = (TextView) findViewById(R.id.instructorDetail);
        title.setText(class_1.title);
        code.setText(class_1.code);
        creditHours.setText(class_1.creditHours + "");
        startHour.setText(class_1.getClassTime()[0] + "");
        startMin.setText(class_1.getClassTime()[1] + "");
        endHour.setText(class_1.getClassTime()[2] + "");
        endMin.setText(class_1.getClassTime()[3] + "");
        startMonth.setText((class_1.getTerm()[0] + 1) + "");
        startDay.setText(class_1.getTerm()[1] + "");
        startYear.setText(class_1.getTerm()[2] + "");
        endMonth.setText((class_1.getTerm()[3] + 1) + "");
        endDay.setText(class_1.getTerm()[4] + "");
        endYear.setText(class_1.getTerm()[5] + "");
        building.setText(class_1.building);
        room.setText(class_1.room);
        instructor.setText(class_1.instructor);
        sun.setChecked(class_1.getDays()[0] == 1);
        mon.setChecked(class_1.getDays()[1] == 1);
        tue.setChecked(class_1.getDays()[2] == 1);
        wed.setChecked(class_1.getDays()[3] == 1);
        thu.setChecked(class_1.getDays()[4] == 1);
        fri.setChecked(class_1.getDays()[5] == 1);
        sat.setChecked(class_1.getDays()[6] == 1);

//        sun.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("check", "clicked");
//                if (sun.isChecked()) {
//                    days[0] = 1;
//                    Log.d("check", "checked");
//                } else {
//                    days[0] = 0;
//                    Log.d("check", "unchecked");
//                }
//                Log.d("check",Arrays.toString(days));
//            }
//        });
//        mon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mon.isChecked()) {
//                    days[1] = 1;
//                } else {
//                    days[1] = 0;
//                }
//            }
//        });
//        tue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tue.isChecked()) {
//                    days[2] = 1;
//                } else {
//                    days[2] = 0;
//                }
//            }
//        });
//        wed.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (wed.isChecked()) {
//                    days[3] = 1;
//                } else {
//                    days[3] = 0;
//                }
//            }
//        });
//        thu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (thu.isChecked()) {
//                    days[4] = 1;
//                } else {
//                    days[4] = 0;
//                }
//            }
//        });
//        fri.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (fri.isChecked()) {
//                    days[5] = 1;
//                } else {
//                    days[5] = 0;
//                }
//            }
//        });
//        sat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (sat.isChecked()) {
//                    days[6] = 1;
//                } else {
//                    days[6] = 0;
//                }
//            }
//        });

        //db = new MyClassDBHandler(this,null,null,1);
        //db.onUpgrade(db.getWritableDatabase(),1,1);
        //Log.d("check", db.readClass("cs125").code);


        Button delete = (Button) findViewById(R.id.deleteButton);
        Button cancel = (Button) findViewById(R.id.cancelButtonDetail);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    createClass();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                db.deleteClasses(class_1.code);
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

//    public void createClass() throws IOException {
//        EditText titleIn = (EditText) findViewById(R.id.classTitle);
//        String title = titleIn.getText().toString();
//        EditText codeIn = (EditText) findViewById(R.id.classCode);
//        String code = codeIn.getText().toString();
//
//        if (code == null || code == "") {
//            finish();
//        }
//        EditText credit = (EditText) findViewById(R.id.creditHours);
//        float creditHours = Float.valueOf(credit.getText().toString());
//        EditText instructorIn = (EditText) findViewById(R.id.Instructor);
//        String instructor = instructorIn.getText().toString();
//        EditText buildingIn = (EditText) findViewById(R.id.BuildingName);
//        String building = buildingIn.getText().toString();
//        EditText roomIn = (EditText) findViewById(R.id.roomNo);
//        String room = roomIn.getText().toString();
//        EditText starthourIn = (EditText) findViewById(R.id.startHour);
//        EditText startMinIn = (EditText) findViewById(R.id.startMin);
//        EditText endhourIn = (EditText) findViewById(R.id.endHour);
//        EditText endMinIn = (EditText) findViewById(R.id.endMinute);
//        int[] time = {Integer.parseInt(starthourIn.getText().toString()),
//                Integer.parseInt(startMinIn.getText().toString()),
//                Integer.parseInt(endhourIn.getText().toString()),
//                Integer.parseInt(endMinIn.getText().toString())};
//        EditText startmonthIn = (EditText) findViewById(R.id.startMonth);
//        EditText startDayIn = (EditText) findViewById(R.id.StartDay);
//        EditText startYearIn = (EditText) findViewById(R.id.StartYear);
//        EditText endmonthIn = (EditText) findViewById(R.id.endMonth);
//        EditText endDayIn = (EditText) findViewById(R.id.endDay);
//        EditText endYearIn = (EditText) findViewById(R.id.endYear);
//        int[] term = {Integer.parseInt(startmonthIn.getText().toString()) - 1,
//                Integer.parseInt(startDayIn.getText().toString()),
//                Integer.parseInt(startYearIn.getText().toString()),
//                Integer.parseInt(endmonthIn.getText().toString()) - 1,
//                Integer.parseInt(endDayIn.getText().toString()),
//                Integer.parseInt(endYearIn.getText().toString())};
//
//        //Log.d("check", Arrays.toString(term));
//
//        Class class_1 = new Class(title, code, creditHours, instructor, building, room, days, time, term);
//
//        //Log.d("check", Arrays.toString(class_1.getDays()));
//
//
//        db.addClasses(class_1);
//
//        //writeFile(class_1);
//
//        //boolean isSame = class_1.code ==
//        //Log.d("check",db.dataBaseToString());
//        //UpdateSchedule();
//
//        finish();
//    }
//
////    public void writeFile(Class temp) {
////        int fileNumber = 0;
////        while (fileNumber)
////        File new_class = new File(getFilesDir(), "class " + fileNumber);
////        FileOutputStream outputStream;
////
////        try {
////                outputStream = openFileOutput("class " + fileNumber, Context.MODE_APPEND);
////                outputStream.write(temp.title.getBytes());
////            outputStream.write(temp.code.getBytes());
////            outputStream.write((byte) temp.creditHours);
////            outputStream.write(temp.instructor.getBytes());
////            outputStream.write(temp.building.getBytes());
////            outputStream.write(temp.room.getBytes());
////            outputStream.write((byte) temp.getDays()[0]);
////            outputStream.write((byte) temp.getDays()[1]);
////            outputStream.write((byte) temp.getDays()[2]);
////            outputStream.write((byte) temp.getDays()[3]);
////            outputStream.write((byte) temp.getDays()[4]);
////            outputStream.write((byte) temp.getDays()[5]);
////            outputStream.write((byte) temp.getDays()[6]);
////            outputStream.flush();
////            outputStream.close();
////        } catch(Exception e) {
////            e.printStackTrace();
////            Toast.makeText(this, "Add class failed", Toast.LENGTH_LONG);
////        }
////    }
//}
