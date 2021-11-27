package iteration1.src.Models;

import iteration1.src.Resources.CourseType;
import iteration1.src.Resources.SemesterName;

import java.util.ArrayList;
import java.util.Collections;

public class Advisor {

    private String name;
    private ArrayList<Student> advisees = new ArrayList<>();

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

            if (!checkCourseQuota(session, student, course)
                    || !checkPreRequisite(course, student)
                    || !checkCollides(course, session, selectedCourses, selectedSessions, student)
                    || !checkTotalCredits(course, student)) {
                selectedCourses.set(i, null);
                break;
            }
        }
        checkTELimitation(student, selectedCourses);
        checkFTELimitation(student, selectedCourses);

        //int limit = selectedCourses.size();

        for(int i = 0; i < selectedCourses.size(); i++) { // TODO: If it doesnt work check HERE!!!.
            if(selectedCourses.get(i) == null) {
                selectedCourses.remove(i);
                selectedSessions.remove(i);
                i--; //limit--;
            }
            else {
                selectedCourses.get(i).getCourseSessions().get(selectedSessions.get(i)).incrementEnrolledStudentAmount();
            }

        }
        return selectedCourses;
    }

    private boolean checkCourseQuota(CourseSession selectedSession, Student student, Course course) {
        if (selectedSession.getCourseCurrentStudentNumber() + 1 > selectedSession.getCOURSE_QUOTA()) {
            String denialMessage =  "The student couldn't register for " + course.getCourseCode() + " because of a quota problem.";
            student.getDenialMessages().add(denialMessage);
            return false;
        }

        return true;
    }

    private boolean checkPreRequisite(Course course, Student student) {
        for (Course _course : student.getTranscript().getFailedCourses()) {
            if (course.getPreRequisiteCourses().contains(_course.getCourseCode())) {
                String denialMessage = "The system didn't allow " + course.getCourseCode() + " student failed prereq. " + _course.getCourseCode();
                student.getDenialMessages().add(denialMessage);
                return false;
            }
        }
        return true;
    }

    private boolean checkCollides(Course course, CourseSession selectedSession, ArrayList<Course> selectedCourses, ArrayList<Integer> selectedSessions, Student student) {
        int collideCounter = 0;

        for (int k = 0; k < selectedCourses.size(); k++) {
            Course _course = selectedCourses.get(k);

            if (!_course.equals(course)) {
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (_course.getCourseSessions().get(selectedSessions.get(k)).getStartingHour()[i][j] == selectedSession.getStartingHour()[i][j]) {
                            collideCounter++;
                            if (collideCounter > 1) {
                                String denialMessage = "Advisor didn't approve " + course.getCourseCode() + " because of at least two hours collision with " + _course.getCourseCode() + " in schedule.";
                                student.getDenialMessages().add(denialMessage);
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
        if(course.getRequiredCredits() < student.getTranscript().getTotalCredits()){
            String denialMessage = "The advisor didn't approve " + course.getCourseType().toString() + " " + course.getCourseCode() + " because student completed credits < " +  student.getTranscript().getTotalCredits();
            student.getDenialMessages().add(denialMessage);
            return false;
        }
        return true;
    }

    private void checkTELimitation(Student student, ArrayList<Course> courseList) {
        int counter = 0;
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            if(course.getCourseType() == CourseType.TE) {
                counter++;
                if((student.getSemester().getSemesterName() == SemesterName.FALL && counter > 1)) {
                    courseList.set(i, null);
                    String denialMessage = "The advisor didn't approve TE " + course.getCourseCode() + " because student already took 1 TE and in FALL semester only 1 TE can be taken.";
                    student.getDenialMessages().add(denialMessage);
                }
                else if((student.getSemester().getSemesterName() == SemesterName.SPRING && counter > 3)){
                    courseList.set(i, null);
                    String denialMessage = "The advisor didn't approve TE " + course.getCourseCode() + " because student already took 3 TE and in SPRING semester only 3 TE can be taken.";
                    student.getDenialMessages().add(denialMessage);
                }
            }
        }
    }

    private void checkFTELimitation(Student student, ArrayList<Course> courseList) {
        for (int i = 0; i < courseList.size(); i++) {
            Course course = courseList.get(i);
            if(course.getCourseType() == CourseType.FTE) {
                if(student.getSemester().getSemesterName() == SemesterName.FALL) {
                    courseList.set(i, null);
                    String denialMessage = "The advisor didn't approve FTE " + course.getCourseCode() + " because students can't take FTE in fall semester unless they are graduating this semester.";
                    student.getDenialMessages().add(denialMessage);
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Student> getAdvisees() {
        return advisees;
    }
}
