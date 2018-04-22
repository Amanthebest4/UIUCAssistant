package edu.illinois.cs.cs125.uiuc_assistant;

import java.sql.Time;
import java.util.Calendar;

public class Class {
    public final String title;
    public final String code;
    public final float creditHours;
    public  final String instructor;
    public final String building;
    public final String room;
    private int[] days = {0, 0, 0, 0, 0, 0, 0};
    private int[] time;
    private int[] term;

    public Class(String name, String setCode, float setCreditHour, String setInstructor,
                 String setBuilding, String setRoom, int[] setDays, int[] setTime,
                 int[] setTerm) {
        this.title = name;
        this.code = setCode;
        this.creditHours = setCreditHour;
        this.instructor = setInstructor;
        this.building = setBuilding;
        this.room = setRoom;
        this.days = setDays;
        this.time = setTime;
        this.term = setTerm;
    }

    public int[] getDays() {
        return days;
    }

    public int[] getClassTime() {
        return time;
    }

    public int[] getTerm() {
        return term;
    }

    public Calendar getStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(term[2], term[0], term[1], 0, 0);
        return calendar;
    }

    public Calendar getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(term[5], term[3], term[4], 23, 59);
        return calendar;
    }
}
