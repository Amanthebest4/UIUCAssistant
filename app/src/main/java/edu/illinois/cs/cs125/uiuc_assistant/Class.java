package edu.illinois.cs.cs125.uiuc_assistant;

public class Class {
    public final String title;
    public final String code;
    public final float creditHours;
    public  final String instructor;
    public final String building;
    public final String room;
    private boolean[] days = {false, false, false, false, false, false, false};

    public Class(String name, String setCode, float setCreditHour, String setInstructor, String setBuilding, String setRoom, boolean[] setDays) {
        this.title = name;
        this.code = setCode;
        this.creditHours = setCreditHour;
        this.instructor = setInstructor;
        this.building = setBuilding;
        this.room = setRoom;
        this.days = setDays;
    }

    public boolean[] getDays() {
        return days;
    }
}
