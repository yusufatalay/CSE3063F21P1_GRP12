package iteration1.src;

import iteration1.src.Models.Course;
import iteration1.src.Models.CourseCode;
import iteration1.src.Models.CourseSession;
import iteration1.src.Models.Semester;
import iteration1.src.Resources.CourseType;
import org.json.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        Path path = Paths.get(filename);

        String content = new String(Files.readAllBytes(path));
        return new JSONObject(content);
    }

    public static void main(String[] args) throws IOException {

        String filePath = "D:/Workspaces/CSE3063F21P1_GRP12/iteration1/src/lecture.json";
        JSONObject jsonObject = parseJSONFile(filePath);

        createCourses(jsonObject);
    }

    private static void createCourses(JSONObject jsonObject) {
        String courseName = jsonObject.getString("courseName");
        CourseCode courseCode = new CourseCode(jsonObject.getString("courseCode"));
        int credit = jsonObject.getInt("credit");
        int requiredCredits = jsonObject.getInt("requiredCredits");
        Semester courseSemester = new Semester(jsonObject.getInt("courseSemester"));

        JSONArray preRequisiteJSON = new JSONArray(jsonObject.getJSONArray("preRequisiteCourse"));
        ArrayList<CourseCode> preRequisites = new ArrayList<>();

        for (int i = 0; i < preRequisiteJSON.length(); i++) {
            preRequisites.add(new CourseCode(preRequisiteJSON.get(i).toString()));
        }

        JSONArray courseSessionsJSONObjects = new JSONArray(jsonObject.getJSONArray("courseSessions"));
        ArrayList<CourseSession> courseSessions = new ArrayList<>();

        for (int i = 0; i < courseSessionsJSONObjects.length(); i++) {
            courseSessions.add(new CourseSession((JSONObject) courseSessionsJSONObjects.get(i)));
        }

        Course course = new Course(courseName, courseCode, credit, preRequisites, courseSessions, requiredCredits, courseSemester, CourseType.MANDATORY);
        System.out.println(course);
    }
}
