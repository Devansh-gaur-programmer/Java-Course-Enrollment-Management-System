package com.edu.courses;

public class LectureCourse extends Course {
    public LectureCourse(String id, String name, int maxSeats, double minGPA) {
        super(id, name, maxSeats, minGPA);
    }

    @Override
    public void displayDetails() {
        System.out.println("Lecture Course: " + getName() + " [" + getId() + "]");
    }
}
