package com.edu.courses;

public class LabCourse extends Course {
    private String labInfo;

    public LabCourse(String id, String name, int maxSeats, double minGPA, String labInfo) {
        super(id, name, maxSeats, minGPA);
        this.labInfo = labInfo;
    }

    @Override
    public void displayDetails() {
        System.out.println("Lab Course: " + getName() + " [" + getId() + "] - Lab: " + labInfo);
    }
}
