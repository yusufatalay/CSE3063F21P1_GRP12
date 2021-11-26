package iteration1.src.Services;

import iteration1.src.Models.*;

import java.util.ArrayList;

public class StudentCreator {
    private Semester semester;
    private Advisor advisor;
    private ArrayList<Course> takenCourses;

    public StudentCreator(Semester semester, Advisor advisor, ArrayList<Course> takenCourses) {
        this.semester = semester;
        this.advisor = advisor;
        this.takenCourses = takenCourses;
    }


    public Student createRandomStudent(int index) {
        String name = "Eren Akgül";
        StudentID studentID = generateStudentID(index);

        TranscriptCreator tc = new TranscriptCreator();
        Transcript transcript = tc.generateTranscript(takenCourses);

        return new Student(name, studentID, semester, advisor, transcript);
    }

    // semester, index
    private StudentID generateStudentID(int index) {

        return null;
    }


}
