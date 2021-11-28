package iteration1.src.Models;

import iteration1.src.Resources.CourseType;

import java.util.ArrayList;

// This class will store courses.
public class Course {
    private String courseName;
    private CourseCode courseCode;
    private int credit;
    private ArrayList<CourseCode> preRequisiteCourses;
    private ArrayList<CourseSession> courseSessions;
    private int requiredCredits;
    private Semester courseSemester;
    private CourseType courseType;

    // Course will be created according to the data in the JSON file.
    public Course(String courseName, CourseCode courseCode, int credit, ArrayList<CourseCode> preRequisiteCourses, ArrayList<CourseSession> courseSessions, int requiredCredits, Semester courseSemester, CourseType courseType) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
        this.preRequisiteCourses = preRequisiteCourses;
        this.courseSessions = courseSessions;
        this.requiredCredits = requiredCredits;
        this.courseSemester = courseSemester;
        this.courseType = courseType;
    }

    // These are general getter methods, there is nothing different.
    public String getCourseName() {
        return courseName;
    }

    public CourseCode getCourseCode() {
        return courseCode;
    }

    public int getCredit() {
        return credit;
    }

    public ArrayList<CourseCode> getPreRequisiteCourses() {
        return preRequisiteCourses;
    }

    public ArrayList<CourseSession> getCourseSessions() {
        return courseSessions;
    }

    public int getRequiredCredits() {
        return requiredCredits;
    }

    public Semester getCourseSemester() {
        return courseSemester;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    // toString returns course name, course code and credit.
    @Override
    public String toString() {
        return "Course Name: " + courseName + "---Course Code: " + courseCode + "---Credit: " + credit;
    }
}
