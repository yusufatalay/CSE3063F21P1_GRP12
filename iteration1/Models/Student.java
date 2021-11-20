package iteration1.Models;
import iteration1.Resources.JSONConverter;

import java.util.ArrayList;

public class Student implements JSONConverter {
    private String name;
    private StudentID studentID;
    private Semester semester;
    private ArrayList<Course> takenCourses  = new ArrayList<Course>();
    private Professor advisor;
    private Transcript transcript;


    public Student(String name, StudentID studentID, Semester semester, Professor advisor, Transcript transcript) {
        this.name = name;
        this.studentID = studentID;
        this.semester = semester;
        this.advisor = advisor;
        this.transcript = transcript;
    }

    public void selectAndEnrollCourses(){
        //call sendToApproval then make that return value equal to takencourses.
    }

    private ArrayList<Course> sendToApproval(ArrayList <Course> selectedCourses) {
        return new ArrayList<Course>();
    }

    public void toJSON(){

    }

    public void fromJSON(){

    }

    public String getName(){
        return name;
    }

    public StudentID getStudentID() {
        return studentID;
    }

    public Semester getSemester() {
        return semester;
    }

    public ArrayList<Course> getTakenCourses() {
        return takenCourses;
    }

    public Professor getAdvisor() {
        return advisor;
    }

    public Transcript getTranscript() {
        return transcript;
    }
}
