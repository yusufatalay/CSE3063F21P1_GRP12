package iteration1.Models;

import java.util.ArrayList;

public class Course {
    private CourseCode courseCode;
    private int credit;
    private ArrayList<Course> preRequisiteCourse;
    private int courseQuota;
    private CourseSession courseSession;
    private int requiredCredits;
    private Semester courseSemester;

    public Course(CourseCode courseCode, int credit, ArrayList<Course> preRequisiteCourse, int courseQuota, CourseSession courseSession, int requiredCredits, Semester courseSemester) {
        this.courseCode = courseCode;
        this.credit = credit;
        this.preRequisiteCourse = preRequisiteCourse;
        this.courseQuota = courseQuota;
        this.courseSession = courseSession;
        this.requiredCredits = requiredCredits;
        this.courseSemester = courseSemester;
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

    public int getCourseQuota() {
        return courseQuota;
    }

    public CourseSession getCourseSession() {
        return courseSession;
    }

    public int getRequiredCredits() {
        return requiredCredits;
    }

    public Semester getCourseSemester() {
        return courseSemester;
    }
}
