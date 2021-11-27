package iteration1.src.Models;
import iteration1.src.Resources.JSONConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private StudentID studentID;
    private Semester semester;
    private ArrayList<Course> takenCourses;
    private ArrayList<Integer> takenSessions;
    private Advisor advisor;
    private Transcript transcript;
    private ArrayList<String> denialMessages;


    public Student(String name, StudentID studentID, Semester semester, Advisor advisor, Transcript transcript) {
        this.name = name;
        this.studentID = studentID;
        this.semester = semester;
        this.advisor = advisor;
        this.transcript = transcript;
    }

    public void selectAndEnrollCourses(){
        ArrayList<Course> selectedCourses = new ArrayList<Course>();
        //selectedCourses = random selected course list comes to here.

        ArrayList<Integer> selectedSessions = new ArrayList<>();
        //selectedSessions = random selected session list comes to here.

        takenCourses = sendToApproval(selectedCourses, selectedSessions);
    }

    private ArrayList<Course> sendToApproval(ArrayList <Course> selectedCourses, ArrayList <Integer> selectedSessions) {
        return advisor.approveCourseList(selectedCourses,selectedSessions,this);
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

    public ArrayList<Integer> getTakenSessions() {
        return takenSessions;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public ArrayList<String> getDenialMessages() {
        return denialMessages;
    }
}
