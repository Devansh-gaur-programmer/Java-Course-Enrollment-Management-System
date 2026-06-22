package com.edu.enrollment;
import com.edu.students.Student;
import com.edu.courses.Course;

public class EnrollmentManager {
    public static boolean enroll(Student student, Course course) throws Exception {
        if (course.isFull()) throw new Exception("Course is full.");
        if (student.getGPA() < course.getMinGPA()) throw new Exception("GPA too low.");
        for (String prereq : course.getPrerequisites()) {
            if (!student.getEnrolledCourses().contains(prereq)) {
                throw new Exception("Missing prerequisite: " + prereq);
            }
        }
        course.enrollStudent(student.getId());
        student.enroll(course.getId());
        return true;
    }
}
