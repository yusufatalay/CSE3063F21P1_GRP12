package iteration1.src.Models;

import iteration1.src.Resources.CourseType;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private CourseCode courseCode;
    private int credit;
    private ArrayList<CourseCode> preRequisiteCourse;
    private ArrayList<CourseSession> courseSessions;
    private int requiredCredits;
    private Semester courseSemester;
    private CourseType courseType;

    public Course(String courseName, CourseCode courseCode, int credit, ArrayList<CourseCode> preRequisiteCourse, ArrayList<CourseSession> courseSessions, int requiredCredits, Semester courseSemester, CourseType courseType) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
        this.preRequisiteCourse = preRequisiteCourse;
        this.courseSessions = courseSessions;
        this.requiredCredits = requiredCredits;
        this.courseSemester = courseSemester;
        this.courseType = courseType;
    }

    public CourseCode getCourseCode() {
        return courseCode;
    }

    public int getCredit() {
        return credit;
    }

    public ArrayList<CourseCode> getPreRequisiteCourse() {
        return preRequisiteCourse;
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

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    @Override
    public String toString() {
        return "Course Name: " + courseName +
                "\ncourseCode=" + courseCode +
                "\ncredit=" + credit +
                "\npreRequisiteCourse=" + preRequisiteCourse +
                "\ncourseSessions=" + courseSessions +
                "\nrequiredCredits=" + requiredCredits +
                "\ncourseSemester=" + courseSemester +
                "\ncourseType=" + courseType + "\n";
    }
}
