package iteration1.src.Services;

import iteration1.src.Models.Course;
import iteration1.src.Models.Transcript;

import java.util.ArrayList;

public class TranscriptCreator {

    public Transcript generateTranscript(ArrayList<Course> courseList) {
        ArrayList<Course> failedCourses = generateFailedCourses(courseList);    //We get failed courses.
        ArrayList<Course> passedCourses = generatePassedCourses(courseList, failedCourses); //We get the passed courses.
        int totalCredits = findGivenCredits(passedCourses);     //We calculate total credits according to passed courses.
        float gpa = calculateGPA(passedCourses, failedCourses); //We calculate gpa according to all courses passed or failed.
        return new Transcript(passedCourses, failedCourses, totalCredits, gpa); //We return the transcript.
    }

    public ArrayList<Course> generateFailedCourses(ArrayList<Course> courseList) {
        int random;
        ArrayList<Course> failedCourses = new ArrayList<>();

        for (Course course : courseList) {  //We get a course from the list to decide fail/pass situation.
            random = (int) (Math.random() * 10 + 1);    //Students fail the course by %10 chance.
            if (random == 1)
                failedCourses.add(course);
        }
        return failedCourses;
    }

    public ArrayList<Course> generatePassedCourses(ArrayList<Course> courseList, ArrayList<Course> failedCourses) {
        //If a course in the courseList doesn't contain a course that is in the failedCourses we add it to passedCourses array.
        ArrayList<Course> passedCourses = new ArrayList<>();
        for (Course course : courseList) {
            if (!failedCourses.contains(course))
                passedCourses.add(course);
        }
        return passedCourses;
    }

    public int findGivenCredits(ArrayList<Course> courses) {
        //Calculates the total credits according to given course list.
        int totalCredits = 0;
        for (Course course : courses) {
            totalCredits += course.getCredit();
        }
        return totalCredits;
    }

    public float calculateGPA(ArrayList<Course> passedCourses, ArrayList<Course> failedCourses) {
        //We calculate total credits by summing up passedCourses' and failedCourses' total credits.
        int takenCredits = findGivenCredits(passedCourses) + findGivenCredits(failedCourses);
        float randomNote;
        float score = 0;

        for (Course pCourse : passedCourses) {
            randomNote = (float) ((int) (Math.random() * 7) / 2.0) + 1; //We calculate random grade for a course.
            score += randomNote * pCourse.getCredit();  //We get the score by multiplying course's credit with grade.
        }
        return score / takenCredits;    //We divide total score with total credits to calculate GPA.
    }
}
