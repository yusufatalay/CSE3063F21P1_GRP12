package iteration1.src.Services;

import iteration1.src.Models.*;
import java.util.ArrayList;

public class CourseSelector {


    public ArrayList<Course> selectCourses(Student student, ArrayList<Course> allCourses, ArrayList<Course> teCourses,
                                            ArrayList<Course> nteCourses,ArrayList<Course> fteCourses) {
        ArrayList<Course> courses = new ArrayList<>();

        for (int i = 0; i < allCourses.size(); i++) {
            if(student.getSemester().compareTo(allCourses.get(i).getCourseSemester()) == 0) {
                courses.add(allCourses.get(i));
            }
        }

        ArrayList<Course> tempTEList = new ArrayList<>(teCourses);

        int studentSemesterNo = student.getSemester().getSemesterNo();
        //TE loop
        if(studentSemesterNo == 7) {
            int random = (int) (Math.random() * teCourses.size());
            courses.add(teCourses.get(random));
            random = (int) (Math.random() * nteCourses.size());
            courses.add(nteCourses.get(random));
        }

        else if (studentSemesterNo == 8) {
            int random;
            for (int i = 0; i < 3; i++) {
                random = (int) (Math.random() * tempTEList.size());
                courses.add(tempTEList.get(random));
                tempTEList.remove(tempTEList.get(random));
            }
            random = (int) (Math.random() * nteCourses.size());
            courses.add(nteCourses.get(random));
            random = (int) (Math.random() * fteCourses.size());
            courses.add(fteCourses.get(random));
        }

        else if(studentSemesterNo == 2) {
            int random = (int) (Math.random() * nteCourses.size());
            courses.add(nteCourses.get(random));
        }


        //If the student
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
