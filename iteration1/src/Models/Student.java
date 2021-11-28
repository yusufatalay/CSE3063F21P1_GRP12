package iteration1.src.Models;
import iteration1.src.Services.CourseSelector;

import java.util.ArrayList;

public class Student extends Person{
    // Student's attributes
    private StudentID studentID;
    private Semester semester;
    private ArrayList<Course> takenCourses;
    private ArrayList<CourseSession> takenSessions = new ArrayList<>();
    private Advisor advisor;
    private Transcript transcript;
    private ArrayList<String> denialMessages = new ArrayList<>();


    public Student(String name, StudentID studentID, Semester semester, Advisor advisor, Transcript transcript) {
        super(name);
        this.studentID = studentID;
        this.semester = semester;
        this.advisor = advisor;
        this.transcript = transcript;
    }
    // selectAndEnrollCourses select courses randomly and send them to advisor then student enrolls returned courses
    public void selectAndEnrollCourses(ArrayList<Course> allCourses, ArrayList<Course> teCourses,
                                        ArrayList<Course> nteCourses,ArrayList<Course> fteCourses){

        // select courses from the json files
        ArrayList<Course> selectedCourses = new CourseSelector().selectCourses(this, allCourses, teCourses, nteCourses, fteCourses);

        // assingn the student to course sessions randomly
        ArrayList<Integer> selectedSessions = new CourseSelector().selectSessions(selectedCourses);

        // send selected courses with their sessions to approval
        takenCourses = sendToApproval(selectedCourses, selectedSessions);
        for (int i = 0; i < takenCourses.size(); i++) {
            takenSessions.add(takenCourses.get(i).getCourseSessions().get(selectedSessions.get(i)));
        }
    }

    // sendToApproval returns approved courses as a list
    private ArrayList<Course> sendToApproval(ArrayList <Course> selectedCourses, ArrayList <Integer> selectedSessions) {
        return advisor.approveCourseList(selectedCourses,selectedSessions,this);
    }

    // getters and setters.
    public StudentID getStudentID() {
        return studentID;
    }

    public Semester getSemester() {
        return semester;
    }

    public ArrayList<Course> getTakenCourses() {
        return takenCourses;
    }

    public ArrayList<CourseSession> getTakenSessions() {
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
