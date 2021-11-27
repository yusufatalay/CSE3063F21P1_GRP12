package iteration1.src.Models;

import org.json.JSONArray;
import org.json.JSONObject;

public class CourseSession {

    private boolean[][] startingHour = new boolean[7][10];

    private final int COURSE_QUOTA;
    private int courseCurrentStudentNumber = 0;

    public CourseSession(JSONObject courseSessionJSONObject) {
        COURSE_QUOTA = courseSessionJSONObject.getInt("quota");
        JSONArray jobj = courseSessionJSONObject.getJSONArray("sessionHours");

        for (int i = 0; i < jobj.length(); i++) {
            int[] dayNHour = {((JSONObject) jobj.get(i)).getInt("day"), ((JSONObject) jobj.get(i)).getInt("hour")};
            setStartingHour(dayNHour);
        }
    }

    public int getCOURSE_QUOTA() {
        return COURSE_QUOTA;
    }

    public int getCourseCurrentStudentNumber() {
        return courseCurrentStudentNumber;
    }

    public boolean[][] getStartingHour() {
        return startingHour;
    }

    public void incrementEnrolledStudentAmount() {
        courseCurrentStudentNumber++;
    }

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
