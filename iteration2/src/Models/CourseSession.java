package iteration2.src.Models;

import org.json.JSONArray;
import org.json.JSONObject;

// This class has the course sessions. It holds hours in a 7x10 matrices.
public class CourseSession {
    private boolean[][] startingHour = new boolean[7][10];

    // Each session has a quota.
    private final int COURSE_QUOTA;
    private int courseCurrentStudentNumber = 0;

    // CourseSession will be created according to courseSession object of course in json file.
    public CourseSession(JSONObject courseSessionJSONObject) {
        COURSE_QUOTA = courseSessionJSONObject.getInt("quota");
        JSONArray jObj = courseSessionJSONObject.getJSONArray("sessionHours");

        for (int i = 0; i < jObj.length(); i++) {
            int[] dayNHour = {((JSONObject) jObj.get(i)).getInt("day"), ((JSONObject) jObj.get(i)).getInt("hour")};
            setStartingHour(dayNHour);
        }
    }

    // These are regular getters.
    public int getCOURSE_QUOTA() {
        return COURSE_QUOTA;
    }

    public int getCourseCurrentStudentNumber() {
        return courseCurrentStudentNumber;
    }

    public boolean[][] getStartingHour() {
        return startingHour;
    }

    // When a student enrolls a session courseCurrentStudentNumber will be incremented by 1.
    public void incrementEnrolledStudentAmount() {
        courseCurrentStudentNumber++;
    }

    // If there is a lesson, the cell of that hour will be true.
    public void setStartingHour(int[] dayAndHour) {
        startingHour[dayAndHour[0]][dayAndHour[1]] = true;
    }

    @Override
    public String toString() {
        return "\n\tCourseSession" +
                "\n\tcourseQuota=" + COURSE_QUOTA +
                "\n\tcourseCurrentStudentNumber=" + courseCurrentStudentNumber;
    }
}
