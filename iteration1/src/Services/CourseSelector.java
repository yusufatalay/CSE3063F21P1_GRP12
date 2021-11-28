package iteration1.src.Services;

import iteration1.src.Models.*;
import java.util.ArrayList;

public class CourseSelector {
    //This method selects random courses for current semester in a given course pool.
    public ArrayList<Course> selectCourses(Student student, ArrayList<Course> allCourses, ArrayList<Course> teCourses,
                                            ArrayList<Course> nteCourses,ArrayList<Course> fteCourses) {
        ArrayList<Course> courses = new ArrayList<>();

        for (int i = 0; i < allCourses.size(); i++) {   //We select a course
            //If the selected course's courseSemester equals the student's current semester we add the course.
            if(student.getSemester().compareTo(allCourses.get(i).getCourseSemester()) == 0) {
                courses.add(allCourses.get(i));
            }
        }

        ArrayList<Course> tempTEList = new ArrayList<>(teCourses);

        int studentSemesterNo = student.getSemester().getSemesterNo();
        //We choose electives if the conditions are satisfied.
        if(studentSemesterNo == 7) {
            int random = (int) (Math.random() * teCourses.size());  //We get a random number to choose randomly from the list.
            courses.add(teCourses.get(random));     //We get the course from the array list item of index "random".
            random = (int) (Math.random() * nteCourses.size());
            courses.add(nteCourses.get(random));
        }
        else if (studentSemesterNo == 8) {  //If the student is in the last semester we choose 3 TEs, 1 NTE and 1 FTE.
            int random;
            for (int i = 0; i < 3; i++) {   //We choose 3 TEs in this loop.
                random = (int) (Math.random() * tempTEList.size());
                courses.add(tempTEList.get(random));
                tempTEList.remove(tempTEList.get(random));
            }
            random = (int) (Math.random() * nteCourses.size());
            courses.add(nteCourses.get(random));
            random = (int) (Math.random() * fteCourses.size());
            courses.add(fteCourses.get(random));
        }
        else if(studentSemesterNo == 2) {   //If the semester number is equals to two we add a single nte course.
            int random = (int) (Math.random() * nteCourses.size());
            courses.add(nteCourses.get(random));
        }


        //Add the courses that student has failed.
        courses.addAll(student.getTranscript().getFailedCourses());

        return courses;
    }

    public ArrayList<Integer> selectSessions(ArrayList<Course> courses) {
        ArrayList<Integer> sessions = new ArrayList<>();
        //We select a random session for each course for a given courseList.
        for (int i = 0; i < courses.size(); i++) {
            int randomSession = (int) (Math.random() * courses.get(i).getCourseSessions().size());
            sessions.add(randomSession);
        }

        return sessions;
    }
}
