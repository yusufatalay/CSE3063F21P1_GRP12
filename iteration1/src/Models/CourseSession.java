package iteration1.src.Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class CourseSession {

    private boolean[][] startingHour = new boolean[7][10];

    private final int courseQuota;
    private int courseCurrentStudentNumber = 0;

    public CourseSession(JSONObject courseSessionJSONObject) {
        this.courseQuota = courseSessionJSONObject.getInt("quota");
        JSONArray jobj = courseSessionJSONObject.getJSONArray("sessionHours");

        for (int i = 0; i < jobj.length(); i++) {
            int[] dayNHour = {((JSONObject) jobj.get(i)).getInt("day"), ((JSONObject) jobj.get(i)).getInt("hour")};
            setStartingHour(dayNHour);
        }
    }

    public int getCourseQuota() {
        return courseQuota;
    }

    public int getCourseCurrentStudentNumber() {
        return courseCurrentStudentNumber;
    }

    public boolean[][] getStartingHour() {
        return startingHour;
    }

    public void incrementEnrolledStudentAmount() {
        this.courseCurrentStudentNumber++;
    }

    public void setStartingHour(int[] dayAndHour) {
        this.startingHour[dayAndHour[0]][dayAndHour[1]] = true;
    }

    @Override
    public String toString() {
        return "\n\tCourseSession" +
                "\n\tcourseQuota=" + courseQuota +
                "\n\tcourseCurrentStudentNumber=" + courseCurrentStudentNumber;
    }
}
