package iteration1.src.Models;
import iteration1.src.Resources.JSONConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student implements JSONConverter {
    private String name;
    private StudentID studentID;
    private Semester semester;
    private ArrayList<Course> takenCourses;
    private ArrayList<Integer> takenSessions;
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
        ArrayList<Course> selectedCourses = new ArrayList<Course>();
        //selectedCourses = random selected course list comes to here.

        ArrayList<CourseSession> selectedSessions = new ArrayList<CourseSession>();
        //selectedSessions = random selected session list comes to here.

        takenCourses = sendToApproval(selectedCourses, selectedSessions);
    }

    private ArrayList<Course> sendToApproval(ArrayList <Course> selectedCourses, ArrayList <CourseSession> selectedSessions) {
        return advisor.approveCourseList(selectedCourses,selectedSessions,this);
    }

    public void toJSON(){

    }

    public void fromJSON(){

    }

    // this map will be contain denied courses and their denial reasons of the student
    Map<Course,String> map=new HashMap<Course,String>();

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

    public Professor getAdvisor() {
        return advisor;
    }

    public Transcript getTranscript() {
        return transcript;
    }
}
