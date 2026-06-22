package com.edu.io;
import com.edu.students.Student;
import com.edu.courses.Course;
import java.io.*;
import java.util.ArrayList;

public class FileManager {
    public static ArrayList<Student> loadStudents(String filename) throws IOException {
        ArrayList<Student> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(Student.fromFileString(line));
        }
        reader.close();
        return list;
    }

    public static void saveStudents(String filename, ArrayList<Student> list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Student s : list) {
            writer.write(s.toFileString());
            writer.newLine();
        }
        writer.close();
    }
    

    public static ArrayList<Course> loadCourses(String filename) throws IOException {
        ArrayList<Course> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(Course.fromFileString(line));
        }
        reader.close();
        return list;
    }

    public static void saveCourses(String filename, ArrayList<Course> list) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Course c : list) {
            writer.write(c.toFileString());
            writer.newLine();
        }
        writer.close();
    }
}
