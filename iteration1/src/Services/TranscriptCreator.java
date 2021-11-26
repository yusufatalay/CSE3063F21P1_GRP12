package iteration1.src.Services;

import iteration1.src.Models.Course;
import iteration1.src.Models.Semester;
import iteration1.src.Models.Transcript;

import java.util.ArrayList;

public class TranscriptCreator {

    public Transcript generateTranscript(ArrayList<Course> courseList) {
        ArrayList<Course> failedCourses = generateFailedCourses(courseList);
        ArrayList<Course> passedCourses = generatePassedCourses(courseList, failedCourses);
        int totalCredits = findGivenCredits(passedCourses);
        float gpa = calculateGPA(passedCourses, failedCourses);
        return new Transcript(passedCourses, failedCourses, totalCredits, gpa);
    }

    public ArrayList<Course> generateFailedCourses(ArrayList<Course> courseList) {
        int random;
        ArrayList<Course> failedCourses = new ArrayList<>();

        for (Course course : courseList) {
            random = (int) (Math.random() * 10 + 1);
            if (random == 1)
                failedCourses.add(course);
        }
        return failedCourses;
    }

    public ArrayList<Course> generatePassedCourses(ArrayList<Course> courseList, ArrayList<Course> failedCourses) {
        ArrayList<Course> passedCourses = new ArrayList<>();
        for (Course course : failedCourses) {
            if (!courseList.contains(course))
                passedCourses.add(course);
        }
        return passedCourses;
    }

    public int findGivenCredits(ArrayList<Course> courses) {
        int totalCredits = 0;
        for (Course course : courses) {
            totalCredits += course.getCredit();
        }
        return totalCredits;
    }


    public float calculateGPA(ArrayList<Course> passedCourses, ArrayList<Course> failedCourses) {
        int takenCredits = findGivenCredits(passedCourses) + findGivenCredits(failedCourses);
        float randomNote;
        float score = 0;

        for (Course pCourse : passedCourses) {
            randomNote = (float) ((int) (Math.random() * 7) / 2.0) + 1;
            score += randomNote * pCourse.getCredit();
        }
        return score / takenCredits;
    }
}
