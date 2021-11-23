package iteration1.src.Models;
import java.util.ArrayList;

public class Transcript {
    private Student student;
    private ArrayList<Course> passedCourses;
    private ArrayList<Course> failedCourses;


    private int totalCredits;
    private float gpa;

    public Transcript(Student student, ArrayList<Course> passedCourses,
                      ArrayList<Course> failedCourses, int totalCredits, float gpa) {
        this.student = student;
        this.passedCourses = passedCourses;
        this.failedCourses = failedCourses;
        this.totalCredits = totalCredits;
        this.gpa = gpa;
    }

    public ArrayList<Course> getPassedCourses() {
        return this.passedCourses;
    }

    public ArrayList<Course> getFailedCourses() {
        return this.failedCourses;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

}
