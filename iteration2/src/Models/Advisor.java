package iteration2.src.Models;

import iteration2.src.Models.Course;
import iteration2.src.Models.CourseSession;
import iteration2.src.Models.Person;
import iteration2.src.Models.Student;
import iteration2.src.Resources.CourseType;
import iteration2.src.Resources.SemesterName;

import java.util.ArrayList;

public class Advisor extends Person {

    public Advisor(String name) {
        super(name);
    }
    //This method checks the conditions of the courses that student has taken.
    public ArrayList<Course> approveCourseList(ArrayList<Course> selectedCourses, ArrayList<Integer> selectedSessions, Student student) {
        for (int i = 0; i < selectedCourses.size(); i++) {  //This loops gets a single course from the list.
            Course course = selectedCourses.get(i);
            CourseSession session = course.getCourseSessions().get(selectedSessions.get(i));
            //After getting a single course and session of this course we check conditions.
            if (!checkCourseQuota(session, student, course)
                    || !checkPreRequisite(course, student)
                    || !checkCollides(course, session, selectedCourses, selectedSessions, student)
                    || !checkTotalCredits(course, student)) {
                selectedCourses.set(i, null);   //We update the course list if there is an error on taking the course.

            }
        }
        checkTELimitation(student, selectedCourses);    //We check the number of elective courses taken separately since they have different conditions.
        checkFTELimitation(student, selectedCourses);

        for (int i = 0; i < selectedCourses.size(); i++) {  //We remove null elements from the courseList in this loop.
            if (selectedCourses.get(i) == null) {
                selectedCourses.remove(i);
                selectedSessions.remove(i);
                i--; //limit--;
            } else {    //We increment the current student number that has enrolled in the particular course.
                selectedCourses.get(i).getCourseSessions().get(selectedSessions.get(i)).incrementEnrolledStudentAmount();
            }

        }
        return selectedCourses;
    }

    private boolean checkCourseQuota(CourseSession selectedSession, Student student, Course course) {
        //If the number of students including current student who wants to register for the course is bigger than quota then we will give a denial message.
        if (selectedSession.getCourseCurrentStudentNumber() + 1 > selectedSession.getCOURSE_QUOTA()) {
            //We set the denial message.
            String denialMessage = "The student couldn't register for " + course.getCourseCode() + " because of a quota problem.";
            System.out.println(student.getStudentID().toString() + " - " + student.getName() + ": " + denialMessage);
            student.getDenialMessages().add(denialMessage); //We add the denial message to the student's denial message array.
            return false;
        }

        return true;
    }

    private boolean checkPreRequisite(Course course, Student student){
        /* In this method we get current course that student wants to register.
        *  Then we check if the course has a prerequisite course that is in student's failed courses.
        *  If so we provide a denial message to student. */

        for (Course _course : student.getTranscript().getFailedCourses()) {
            if (course.getPreRequisiteCourses().contains(_course.getCourseCode())) {
                String denialMessage = "The system didn't allow " + course.getCourseCode() + " student failed prereq. " + _course.getCourseCode();
                System.out.println(student.getStudentID().toString() + " - " + student.getName() + ": " + denialMessage);

                student.getDenialMessages().add(denialMessage);
                return false;
            }
        }
        return true;
    }

    private boolean checkCollides(Course course, CourseSession selectedSession, ArrayList<Course> selectedCourses, ArrayList<Integer> selectedSessions, Student student) {
        int collideCounter = 0; //We set a collide counter to hold number of collision hours.

        for (int k = 0; k < selectedCourses.size(); k++) {  //We get a course.
            Course _course = selectedCourses.get(k);

            if (_course != null && !_course.equals(course)) {   //We check courses hours by pairs.
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 10; j++) {
                        //If selected two courses from the loop has a course hour at the same time interval we increment the collideCounter.
                        if (_course.getCourseSessions().get(selectedSessions.get(k)).getStartingHour()[i][j] && selectedSession.getStartingHour()[i][j]) {
                            collideCounter++;
                            //If collideCounter is greater than 1 we deny the course since we don't allow collisions more than 1 hour.
                            if (collideCounter > 1) {
                                String denialMessage = "Advisor didn't approve " + course.getCourseCode() + " because of at least two hours collision with " + _course.getCourseCode() + " in schedule.";
                                System.out.println(student.getStudentID().toString() + " - " + student.getName() + ": " + denialMessage);

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
        //In this method if the course's required credits that student wants to take is greater than student's total credits we deny the course.
        if (course.getRequiredCredits() > student.getTranscript().getTotalCredits()) {
            String denialMessage = "The advisor didn't approve " + course.getCourseType().toString() + " " + course.getCourseCode() + " because student completed credits < " + course.getRequiredCredits();
            System.out.println(student.getStudentID().toString() + " - " + student.getName() + ": " + denialMessage);


            student.getDenialMessages().add(denialMessage);

            return false;
        }
        return true;
    }

    private void checkTELimitation(Student student, ArrayList<Course> courseList) {
        int counter = 0;    //We initialize a counter to hold the number of TE courses taken.

        for (int i = 0; i < courseList.size(); i++) {   //If current courseList is null we continue with the next iteration of the loop.
            if (courseList.get(i) == null) {
                continue;
            }

            Course course = courseList.get(i);     //We get a course and increment the counter if it is a TE.
            if (course.getCourseType() == CourseType.TE) {
                counter++;
                //We have two conditions on number of TEs can be taken. We check those on the given if-elseif conditions below.
                if ((student.getSemester().getSemesterName() == SemesterName.FALL && counter > 1)) {
                    courseList.set(i, null);
                    String denialMessage = "The advisor didn't approve TE " + course.getCourseCode() + " because student already took 1 TE and in FALL semester only 1 TE can be taken.";
                    System.out.println(student.getStudentID().toString() + " - " + student.getName() + ": " + denialMessage);


                    student.getDenialMessages().add(denialMessage);
                } else if ((student.getSemester().getSemesterName() == SemesterName.SPRING && counter > 3)) {
                    courseList.set(i, null);
                    String denialMessage = "The advisor didn't approve TE " + course.getCourseCode() + " because student already took 3 TE and in SPRING semester only 3 TE can be taken.";
                    System.out.println(student.getStudentID().toString() + " - " + student.getName() + ": " + denialMessage);

                    student.getDenialMessages().add(denialMessage);
                }
            }
        }
    }

    private void checkFTELimitation(Student student, ArrayList<Course> courseList) {
        //If course is not null in the given list we take it and check if its a FTE or not.
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i) == null) {
                continue;
            }

            Course course = courseList.get(i);
            //If the course is FTE we can only take it in the fall semester.
            if (course.getCourseType() == CourseType.FTE) {
                if (student.getSemester().getSemesterName() == SemesterName.FALL) {
                    courseList.set(i, null);
                    String denialMessage = "The advisor didn't approve FTE " + course.getCourseCode() + " because students can't take FTE in fall semester unless they are graduating this semester.";
                    System.out.println(student.getStudentID().toString() + " - " + student.getName() + ": " + denialMessage);

                    student.getDenialMessages().add(denialMessage);
                }
            }
        }
    }
}
