package iteration1.Models;

import iteration1.Resources.CourseType;

import java.util.ArrayList;

public class Course {
    private CourseCode courseCode;
    private int credit;
    private ArrayList<Course> preRequisiteCourse;
    private ArrayList<CourseSession> courseSessions;
    private int requiredCredits;
    private Semester courseSemester;
    private CourseType courseType;

    public Course(CourseCode courseCode, int credit, ArrayList<Course> preRequisiteCourse, ArrayList<CourseSession> courseSessions, int requiredCredits, Semester courseSemester, CourseType courseType) {
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

    public ArrayList<Course> getPreRequisiteCourse() {
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


}
