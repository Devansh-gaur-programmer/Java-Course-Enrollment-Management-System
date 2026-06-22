package com.edu.courses;
import java.util.ArrayList;

public abstract class Course {
    protected String id;
    protected String name;
    private int maxSeats;
    private double minGPA;
    protected ArrayList<String> prerequisites;
    protected ArrayList<String> enrolledStudents;

    public Course(String id, String name, int maxSeats, double minGPA) {
        this.id = id;
        this.name = name;
        setMaxSeats(maxSeats);
        this.minGPA = minGPA;
        this.prerequisites = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getMaxSeats() { return maxSeats; }
    public double getMinGPA() { return minGPA; }
    public ArrayList<String> getPrerequisites() { return prerequisites; }
    public ArrayList<String> getEnrolledStudentIds() { return enrolledStudents; }

    public void setMaxSeats(int maxSeats) {
        if (maxSeats > 0) this.maxSeats = maxSeats;
    }

    public boolean isFull() {
        return enrolledStudents.size() >= maxSeats;
    }

    public void enrollStudent(String studentId) {
        enrolledStudents.add(studentId);
    }

    public abstract void displayDetails();

    public String toFileString() {
        return id + "," + name + "," + maxSeats + "," + minGPA;
    }

    public static Course fromFileString(String line) {
        String[] parts = line.split(",");
        return new LectureCourse(parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]));
    }
}
