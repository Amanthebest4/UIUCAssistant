package edu.illinois.cs.cs125.uiuc_assistant;

public class Class {
    public final String title;
    public final String code;
    public final float creditHours;
    public  final String instructor;
    public final String building;
    public final String room;
    private int[] days = {0, 0, 0, 0, 0, 0, 0};

    public Class(String name, String setCode, float setCreditHour, String setInstructor, String setBuilding, String setRoom, int[] setDays) {
        this.title = name;
        this.code = setCode;
        this.creditHours = setCreditHour;
        this.instructor = setInstructor;
        this.building = setBuilding;
        this.room = setRoom;
        this.days = setDays;
    }

    public int[] getDays() {
        return days;
    }
}
