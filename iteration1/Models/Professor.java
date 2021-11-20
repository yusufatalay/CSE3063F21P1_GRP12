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

    public void approveCourseList(ArrayList<Course> courseList, Student student) {

    }

    private boolean checkCourseQuota(ArrayList<Course> courseList) {
        //Todo
        return true;
    }

    private boolean checkCollides(ArrayList<Course> courseList) {
        //Todo
        return true;
    }

    private boolean checkPreRequisite(ArrayList<Course> courseList) {
        //Todo
        return true;
    }

    private boolean checkTotalCredits(ArrayList<Course> courseList) {
        //Todo
        return true;
    }

    private boolean checkTELimitation(ArrayList<Course> courseList) {
        //Todo
        return true;
    }
}