package iteration1.src.Models;

import iteration1.src.Resources.SemesterName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Professor {
    private String name;
    private ArrayList<Course> givenCourses = new ArrayList<Course>();
    private ArrayList<Student> advisee = new ArrayList<Student>();

    public Professor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Course> getGivenCourses() {
        return givenCourses;
    }

    public ArrayList<Student> getAdvisee() {
        return advisee;
    }

    public ArrayList<Course> approveCourseList(ArrayList<Course> courseList, ArrayList<CourseSession> selectedSessions, Student student) {
        /*
        for each step if fails remove that course (make it NULL) and its session.
        1.checkCourseQuota: if true advance to step 2
        2.checkCollides: if true advance to step 3
        3.checkPreRequisite: if true advance to step 4
        4.checkTotalCredits: if true advance to step 5
        5.checkTELimitation: if true then finish
         */

        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            CourseSession session = selectedSessions.get(i);

            if (!checkCourseQuota(session)
                    || !checkPreRequisite(course, student)
                    || !checkCollides(course, session, courseList, selectedSessions)
                    || !checkTotalCredits(course, student)
                    || !checkTELimitation(student, courseList)
                    || !checkFTELimitation(course)) {
                courseList.set(i, null);
                break;
            }
        }

        return new ArrayList<Course>();
    }

    private boolean checkCourseQuota(CourseSession selectedSession) {
        if (selectedSession.getCourseCurrentStudentNumber() + 1 > selectedSession.getCourseQuota()) {
            return false;
        }
        return true;
    }

    private boolean checkCollides(Course course, CourseSession session,
                                  ArrayList<Course> courseList, ArrayList<CourseSession> selectedSession) {

        for (Course _course : courseList) {
            if (!_course.getCourseCode().equals(course.getCourseCode())) {
                for (CourseSession _session : selectedSession) {
                    /*if (session.getStartHour().getTime() <= _session.getEndHour().getTime()
                            && _session.getStartHour().getTime() <= session.getEndHour().getTime()
                            && findTimeInterval(session, _session) >= 50) {

                        return false;
                    }*/
                }
            }
        }
        return true;
    }

  /*  private int findTimeInterval(CourseSession session1, CourseSession session2) {
        long interval1 = Math.abs(session1.getEndHour().getTime() - session2.getStartHour().getTime());
        long interval2 = Math.abs(session1.getStartHour().getTime() - session2.getEndHour().getTime());

        if (interval1 > interval2) {
            return (int) (interval2) / 600000;
        } else {
            return (int) (interval1) / 600000;
        }

    }*/

    private boolean checkPreRequisite(Course course, Student student) {
        for (Course _course : student.getTranscript().getFailedCourses()) {
            if(course.getPreRequisiteCourse().contains(_course)) {
                return false;
            }
        }
        return true;
    }


    private boolean checkTotalCredits(Course course, Student student) {
        return course.getRequiredCredits() <= student.getTranscript().getTotalCredits();
    }

    private boolean checkTELimitation(Student student, ArrayList<Course> courseList) {
        if(student.getSemester().getSemesterName().equals(SemesterName.FALL)) {

        }else{

        }
        return true;
    }

    private boolean checkFTELimitation(Course course) {
        //Todo
        return true;
    }
}