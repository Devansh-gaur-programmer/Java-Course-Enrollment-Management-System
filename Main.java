package com.edu.main;

import com.edu.students.Student;
import com.edu.courses.*;
import com.edu.enrollment.EnrollmentManager;
import com.edu.io.FileManager;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();

        // Load data at startup
        try {
            students = FileManager.loadStudents("students.txt");
            courses = FileManager.loadCourses("courses.txt");
            System.out.println(" Loaded " + students.size() + " students and " + courses.size() + " courses.");
        } catch (IOException e) {
            System.out.println(" Error loading files: " + e.getMessage());
        }

        while (true) {
            System.out.println("\n Menu:");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course (Lecture or Lab)");
            System.out.println("3. Enroll Student");
            System.out.println("4. View Courses");
            System.out.println("5. View Enrollment Status");
            System.out.println("6. View Student Report");
            System.out.println("7. Remove Student");
            System.out.println("8. View All Students");
            System.out.println("9. Exit and Save");
            System.out.print("Choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Student ID: ");
                        String sid = sc.nextLine();
                        boolean studentExists = students.stream().anyMatch(s -> s.getId().equals(sid));
                        if (studentExists) {
                            System.out.println(" Student ID already exists.");
                            break;
                        }
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("GPA (0–10): ");
                        double gpa = sc.nextDouble(); sc.nextLine();
                        students.add(new Student(sid, name, gpa));
                        System.out.println(" Student added.");
                        break;

                    case 2:
                        System.out.print("Course Type (L = Lecture, B = Lab): ");
                        String type = sc.nextLine().toUpperCase();
                        System.out.print("Course ID: ");
                        String cid = sc.nextLine();
                        boolean courseExists = courses.stream().anyMatch(c -> c.getId().equals(cid));
                        if (courseExists) {
                            System.out.println(" Course ID already exists.");
                            break;
                        }
                        System.out.print("Course Name: ");
                        String cname = sc.nextLine();
                        System.out.print("Max Seats: ");
                        int seats = sc.nextInt();
                        System.out.print("Min GPA (0–10): ");
                        double minGpa = sc.nextDouble(); sc.nextLine();

                        if (type.equals("L")) {
                            courses.add(new LectureCourse(cid, cname, seats, minGpa));
                        } else if (type.equals("B")) {
                            System.out.print("Lab Session Info: ");
                            String labInfo = sc.nextLine();
                            courses.add(new LabCourse(cid, cname, seats, minGpa, labInfo));
                        } else {
                            System.out.println(" Invalid course type.");
                            break;
                        }
                        System.out.println(" Course added.");
                        break;

                    case 3:
                        System.out.print("Student ID: ");
                        String esid = sc.nextLine();
                        System.out.print("Course ID: ");
                        String ecid = sc.nextLine();

                        Student student = students.stream()
                            .filter(s -> s.getId().equals(esid))
                            .findFirst().orElse(null);
                        Course course = courses.stream()
                            .filter(c -> c.getId().equals(ecid))
                            .findFirst().orElse(null);

                        if (student == null || course == null) {
                            System.out.println(" Invalid student or course ID.");
                            break;
                        }

                        if (student.getEnrolledCourses().contains(ecid)) {
                            System.out.println(" Student is already enrolled in this course.");
                            break;
                        }

                        EnrollmentManager.enroll(student, course);
                        System.out.println(" Enrollment successful.");
                        break;

                    case 4:
                        System.out.println("\n All Courses:");
                        for (Course c : courses) {
                            c.displayDetails();
                            System.out.println("Min GPA: " + c.getMinGPA() + " | Max Seats: " + c.getMaxSeats());
                        }
                        break;

                    case 5:
                        System.out.println("\n Enrollment Status:");
                        for (Course c : courses) {
                            System.out.println("\nCourse: " + c.getName() + " [" + c.getId() + "]");
                            System.out.println("Enrolled Students:");
                            for (String studentId : c.getEnrolledStudentIds()) {
                                Student s = students.stream()
                                    .filter(st -> st.getId().equals(studentId))
                                    .findFirst().orElse(null);
                                if (s != null) {
                                    System.out.println("- " + s.getName() + " (" + s.getId() + "), GPA: " + s.getGPA());
                                }
                            }
                        }
                        break;

                    case 6:
                        System.out.print("Enter Student ID: ");
                        String reportId = sc.nextLine();
                        Student reportStudent = students.stream()
                            .filter(s -> s.getId().equals(reportId))
                            .findFirst().orElse(null);

                        if (reportStudent == null) {
                            System.out.println(" Student not found.");
                            break;
                        }

                        System.out.println("\n Student Report:");
                        System.out.println("Name: " + reportStudent.getName());
                        System.out.println("GPA: " + reportStudent.getGPA());
                        System.out.println("Enrolled Courses:");
                        for (String courseId : reportStudent.getEnrolledCourses()) {
                            Course enrolledCourse = courses.stream()
                                .filter(c -> c.getId().equals(courseId))
                                .findFirst().orElse(null);
                            if (enrolledCourse != null) {
                                System.out.println("- " + enrolledCourse.getName() + " [" + enrolledCourse.getId() + "]");
                            }
                        }
                        break;

                    case 7:
                        System.out.print("Enter Student ID to remove: ");
                        String removeId = sc.nextLine();
                        Student toRemove = students.stream()
                            .filter(s -> s.getId().equals(removeId))
                            .findFirst().orElse(null);

                        if (toRemove == null) {
                            System.out.println(" Student not found.");
                            break;
                        }

                        for (Course c : courses) {
                            c.getEnrolledStudentIds().remove(removeId);
                        }

                        students.remove(toRemove);
                        System.out.println(" Student removed.");
                        break;

                    case 8:
                        System.out.println("\n All Students:");
                        if (students.isEmpty()) {
                            System.out.println("No students found.");
                        } else {
                            for (Student s : students) {
                                System.out.println("- " + s.getName() + " (" + s.getId() + "), GPA: " + s.getGPA());
                            }
                        }
                        break;

                    case 9:
                        FileManager.saveStudents("students.txt", students);
                        FileManager.saveCourses("courses.txt", courses);
                        System.out.println(" Data saved. Exiting...");
                        return;

                    default:
                        System.out.println(" Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println(" Error: " + e.getMessage());
            }
        }
    }
}
