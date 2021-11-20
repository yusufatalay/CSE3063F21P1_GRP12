package iteration1.Models;

import java.util.ArrayList;

public class Professor {
    private String name;
    private ArrayList<Course> givenCourses = new ArrayList<Course>();
    private ArrayList<Student> advisee = new ArrayList<Student>();

    public Professor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getGivenCourses() {
        return givenCourses;
    }

    public ArrayList<Student> getAdvisee() {
        return advisee;
    }

    public ArrayList<Course> approveCourseList(ArrayList<Course> courseList, ArrayList<CourseSession> selectedSessions, Student student) {
        /*
        for each step if fails remove that course (make it NULL) and its session.
        1.checkCourseQuota: if true advance to step 2
        2.checkCollides: if true advance to step 3
        3.checkPreRequisite: if true advance to step 4
        4.checkTotalCredits: if true advance to step 5
        5.checkTELimitation: if true then finish
         */
        for(int i = 0; i < courseList.size(); i++){
            Course course = courseList.get(i);
            CourseSession session = selectedSessions.get(i);
            if(!checkCourseQuota(course,session)
                || !checkCollides(course,session)
                || !checkPreRequisite(course)
                || !checkTotalCredits(course)
                || !checkTELimitation(course)
                || !checkFTELimitation(course))
            {
                courseList.set(i,null);
                break;
            }
        }

        return new ArrayList<Course>();
    }

    private boolean checkCourseQuota(Course course, CourseSession selectedSession) {
        //Todo
        return true;
    }

    private boolean checkCollides(Course course, CourseSession selectedSession) {
        //Todo
        return true;
    }

    private boolean checkPreRequisite(Course course) {
        //Todo
        return true;
    }

    private boolean checkTotalCredits(Course course) {
        //Todo
        return true;
    }

    private boolean checkTELimitation(Course course) {
        //Todo
        return true;
    }

    private boolean checkFTELimitation(Course course) {
        //Todo
        return true;
    }
}