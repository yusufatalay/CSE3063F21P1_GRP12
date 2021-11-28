package iteration1.src.Models;

import java.util.ArrayList;


public class Transcript {
    //We hold the passed courses, failed courses, total credits and GPA of a student.
    private ArrayList<Course> passedCourses;
    private ArrayList<Course> failedCourses;
    private int totalCredits;
    private float gpa;

    public Transcript(ArrayList<Course> passedCourses,
                      ArrayList<Course> failedCourses, int totalCredits, float gpa) {
        this.passedCourses = passedCourses;
        this.failedCourses = failedCourses;
        this.totalCredits = totalCredits;
        this.gpa = gpa;
    }

    //Getters created for the attributes defined in the class.
    public ArrayList<Course> getPassedCourses() {
        return this.passedCourses;
    }

    public ArrayList<Course> getFailedCourses() {
        return this.failedCourses;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public float getGpa() {
        return gpa;
    }

}
