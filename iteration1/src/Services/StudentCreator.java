package iteration1.src.Services;

import iteration1.src.Models.*;
import java.util.ArrayList;
import java.util.Calendar;

public class StudentCreator {
    private Semester semester;
    private Advisor advisor;
    private ArrayList<Course> takenCourses;

    public StudentCreator(Semester semester, Advisor advisor, ArrayList<Course> takenCourses) {
        this.semester = semester;
        this.advisor = advisor;
        this.takenCourses = takenCourses;
    }


    public Student createRandomStudent(int index, String name) {
        StudentID studentID = generateStudentID(index);

        TranscriptCreator tc = new TranscriptCreator();
        Transcript transcript = tc.generateTranscript(takenCourses);

        return new Student(name, studentID, semester, advisor, transcript);
    }

    // semester, index | done
    private StudentID generateStudentID(int index) {
        String department = "1501";
        String entryYear = ((Calendar.getInstance().get(Calendar.YEAR) - (int) (Math.ceil(semester.getSemesterNo() / 2.0))) % 100) + "";
        String entryOrder;
        if (index < 10) {
            entryOrder = "00" + index;
        } else if (index < 100) {
            entryOrder = "0" + index;
        } else {
            entryOrder = "" + index;
        }

        return new StudentID(department, entryYear, entryOrder);
    }


}
