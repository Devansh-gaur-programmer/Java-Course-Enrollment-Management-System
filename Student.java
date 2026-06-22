package com.edu.students;
import java.util.ArrayList;

public class Student {
    private String id;
    private String name;
    private double gpa;
    private ArrayList<String> enrolledCourses;

    public Student(String id, String name, double gpa) {
        this.id = id;
        this.name = name;
        setGPA(gpa);
        this.enrolledCourses = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getGPA() { return gpa; }
    public ArrayList<String> getEnrolledCourses() { return enrolledCourses; }

    public void setGPA(double gpa) {
        if (gpa >= 0.0 && gpa <= 10.0) this.gpa = gpa;
        else throw new IllegalArgumentException("GPA must be between 0.0 and 10.0");
    }

    public void enroll(String courseId) {
        enrolledCourses.add(courseId);
    }

    public String toFileString() {
        return id + "," + name + "," + gpa;
    }

    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        return new Student(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}
