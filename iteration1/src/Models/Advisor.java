package iteration1.src.Models;

import iteration1.src.Resources.CourseType;
import iteration1.src.Resources.SemesterName;

import java.util.ArrayList;
import java.util.Collections;

public class Advisor {

    private String name;
    private ArrayList<Student> advisee = new ArrayList<>();

    public Advisor(String name) {
        this.name = name;
    }

    public ArrayList<Course> approveCourseList(ArrayList<Course> selectedCourses, ArrayList<Integer> selectedSessions, Student student) {
        /*
        for each step if fails remove that course (make it NULL) and its session.
        1.checkCourseQuota: if true advance to step 2
        2.checkCollides: if true advance to step 3
        3.checkPreRequisite: if true advance to step 4
        4.checkTotalCredits: if true advance to step 5
        5.checkTELimitation: if true then finish
         */

        for (int i = 0; i < selectedCourses.size(); i++) {
            Course course = selectedCourses.get(i);
            CourseSession session = course.getCourseSessions().get(selectedSessions.get(i));

            if (!checkCourseQuota(session)
                    || !checkPreRequisite(course, student)
                    || !checkCollides(course, session, selectedCourses, selectedSessions)
                    || !checkTotalCredits(course, student)) {
                selectedCourses.set(i, null);
                break;
            }
        }
        checkTELimitation(student, selectedCourses);
        checkFTELimitation(student, selectedCourses);

        selectedCourses.removeAll(Collections.singleton(null));
        return selectedCourses;
    }

    private boolean checkCourseQuota(CourseSession selectedSession) {
        if (selectedSession.getCourseCurrentStudentNumber() + 1 > selectedSession.getCOURSE_QUOTA()) {
            return false;
        }
        return true;
    }

    private boolean checkPreRequisite(Course course, Student student) {
        for (Course _course : student.getTranscript().getFailedCourses()) {
            if (course.getPreRequisiteCourses().contains(_course.getCourseCode())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkCollides(Course course, CourseSession selectedSession, ArrayList<Course> selectedCourses, ArrayList<Integer> selectedSessions) {
        int collideCounter = 0;

        for (int k = 0; k < selectedCourses.size(); k++) {
            Course _course = selectedCourses.get(k);

            if (!_course.equals(course)) {
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (_course.getCourseSessions().get(selectedSessions.get(k)).getStartingHour()[i][j] == selectedSession.getStartingHour()[i][j]) {
                            collideCounter++;
                            if (collideCounter > 1) {
                                //Provide error message.
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean checkTotalCredits(Course course, Student student) {
        return course.getRequiredCredits() <= student.getTranscript().getTotalCredits();
    }

    private void checkTELimitation(Student student, ArrayList<Course> courseList) {
        int counter = 0;
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            if(course.getCourseType() == CourseType.TE) {
                counter++;
                if((student.getSemester().getSemesterName() == SemesterName.FALL && counter > 1) ||
                        (student.getSemester().getSemesterName() == SemesterName.SPRING && counter > 3)) {
                    courseList.set(i, null);
                    //Error message send
                }
            }
        }
    }

    private void checkFTELimitation(Student student, ArrayList<Course> courseList) {
        int counter = 0;
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            if(course.getCourseType() == CourseType.FTE) {
                counter++;
                if((student.getSemester().getSemesterName() == SemesterName.FALL) ||
                        (student.getSemester().getSemesterName() == SemesterName.SPRING && counter > 1)) {
                    courseList.set(i, null);
                    //Error message send
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Student> getAdvisee() {
        return advisee;
    }
}
