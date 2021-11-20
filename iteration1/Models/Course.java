package iteration1.Models;

import java.util.ArrayList;

public class Course {
    private CourseCode courseCode;
    private int credit;
    private ArrayList<Course> preRequisiteCourse;
    private ArrayList<CourseSession> courseSessions;
    private int requiredCredits;
    private Semester courseSemester;

    public Course(CourseCode courseCode, int credit, ArrayList<Course> preRequisiteCourse, ArrayList<CourseSession> courseSessions, int requiredCredits, Semester courseSemester) {
        this.courseCode = courseCode;
        this.credit = credit;
        this.preRequisiteCourse = preRequisiteCourse;
        this.courseSessions = courseSessions;
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

    public ArrayList<CourseSession> getCourseSession() {
        return courseSessions;
    }

    public int getRequiredCredits() {
        return requiredCredits;
    }

    public Semester getCourseSemester() {
        return courseSemester;
    }
}
