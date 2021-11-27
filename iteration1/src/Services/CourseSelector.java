package iteration1.src.Services;

import iteration1.src.Models.*;
import java.util.ArrayList;

public class CourseSelector {


    public ArrayList<Course> selectCourses(Student student, ArrayList<Course> allCourses) {
        ArrayList<Course> courses = new ArrayList<Course>();

        for (int i = 0; i < allCourses.size(); i++) {
            if(student.getSemester().compareTo(allCourses.get(i).getCourseSemester()) == 0) {
                courses.add(allCourses.get(i));
            }
        }
        courses.addAll(student.getTranscript().getFailedCourses());

        return courses;
    }

    public ArrayList<Integer> selectSessions(ArrayList<Course> courses) {
        ArrayList<Integer> sessions = new ArrayList<>();

        for (int i = 0; i < courses.size(); i++) {
            int randomSession = (int) (Math.random() * courses.get(i).getCourseSessions().size());
            sessions.add(randomSession);
        }

        return sessions;
    }
}
