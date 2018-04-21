package edu.illinois.cs.cs125.uiuc_assistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import java.util.Collection;

public class MyClassDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "classes.db";
    public static final String TABLE_CLASSES = "classes";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_COURSECODE = "code";
    public static final String COLUMN_CREDITHOURS = "credithours";
    public static final String COLUMN_INSTRUCTOR = "instructor";
    public static final String COLUMN_BUILDING = "building";
    public static final String COLUMN_ROOM = "room";
    public static final String COLUMN_SUNDAY = "sunday";
    public static final String COLUMN_MONDAY = "monday";
    public static final String COLUMN_TUESDAY = "tuesday";
    public static final String COLUMN_WEDNESDAY = "wednesday";
    public static final String COLUMN_THURSDAY= "thursday";
    public static final String COLUMN_FRIDAY = "friday";
    public static final String COLUMN_SATURDAY= "saturday";


    //columns



    public MyClassDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CLASSES + "(" +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_COURSECODE + " TEXT PRIMARY KEY, " +
                COLUMN_CREDITHOURS + " REAL, " +
                COLUMN_INSTRUCTOR + " TEXT, " +
                COLUMN_BUILDING + " TEXT, " +
                COLUMN_ROOM + " TEXT, " +
                COLUMN_SUNDAY + " INTEGER, " +
                COLUMN_MONDAY + " INTEGER, " +
                COLUMN_TUESDAY + " INTEGER, " +
                COLUMN_WEDNESDAY + " INTEGER, " +
                COLUMN_THURSDAY + " INTEGER, " +
                COLUMN_FRIDAY + " INTEGER, " +
                COLUMN_SATURDAY + " INTEGER " +
                ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSES);
        onCreate(db);
    }

    public void addClasses(Class new_class) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, new_class.title);
        values.put(COLUMN_COURSECODE, new_class.code);
        values.put(COLUMN_CREDITHOURS, new_class.creditHours);
        values.put(COLUMN_INSTRUCTOR, new_class.instructor);
        values.put(COLUMN_BUILDING, new_class.building);
        values.put(COLUMN_ROOM, new_class.room);
        values.put(COLUMN_SUNDAY, new_class.getDays()[0]);
        values.put(COLUMN_MONDAY, new_class.getDays()[1]);
        values.put(COLUMN_TUESDAY, new_class.getDays()[2]);
        values.put(COLUMN_WEDNESDAY, new_class.getDays()[3]);
        values.put(COLUMN_THURSDAY, new_class.getDays()[4]);
        values.put(COLUMN_FRIDAY, new_class.getDays()[5]);
        values.put(COLUMN_SATURDAY, new_class.getDays()[6]);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CLASSES, null, values);
        db.close();
    }


    public void deleateClasses(String code) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CLASSES + " WHERE " + "=\"" + code + "\";" );
    }

    public String dataBaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CLASSES + " WHERE 1";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("code")) != null) {
                dbString += c.getString(c.getColumnIndex("code"));
                dbString += "\n";
            }
        }
        db.close();
        return dbString;
    }

    public Class readClass(String code) {
//        //Class dbClass = new Class();
//        String title;
//        String courseCode;
//        float creditHours;
//        String instructor;
//        String building;
//        String room;
        int[] days = {0,0,0,0,0,0,0};
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT * FROM " + TABLE_CLASSES + " WHERE " + "=\"" + code + "\";";
//
//        Cursor c = db.rawQuery(query, null);
//        c.moveToFirst();
//
//        while (!c.isAfterLast()) {
//            if (c.getString(c.getColumnIndex("code")) != null) {
//                courseCode = c.getString(c.getColumnIndex("code"));
//            }
//            if (c.getString(c.getColumnIndex("code")) != null) {
//                courseCode = c.getString(c.getColumnIndex("code"));
//            }
//        }
//        db.close();
//        return dbString;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CLASSES, new String[] { COLUMN_TITLE,
                        COLUMN_COURSECODE,
                        COLUMN_CREDITHOURS,
                        COLUMN_INSTRUCTOR ,
                        COLUMN_BUILDING,
                        COLUMN_ROOM,
                        COLUMN_SUNDAY,
                        COLUMN_MONDAY,
                        COLUMN_TUESDAY,
                        COLUMN_WEDNESDAY,
                        COLUMN_THURSDAY,
                        COLUMN_FRIDAY ,
                        COLUMN_SATURDAY },  COLUMN_COURSECODE + "=?",
                new String[] { String.valueOf(code) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        days[0] = Integer.parseInt(cursor.getString(6));
        days[1] = Integer.parseInt(cursor.getString(7));
        days[2] = Integer.parseInt(cursor.getString(8));
        days[3] = Integer.parseInt(cursor.getString(9));
        days[4] = Integer.parseInt(cursor.getString(10));
        days[5] = Integer.parseInt(cursor.getString(11));
        days[6] = Integer.parseInt(cursor.getString(12));

        Class dbClass = new Class(cursor.getString(0),
                cursor.getString(1),
                Float.parseFloat(cursor.getString(2)),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5), days
                );
        // return Translate
        return dbClass;

    }


}
