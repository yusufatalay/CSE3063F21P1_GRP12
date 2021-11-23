package iteration1.src;
import iteration1.src.Models.Course;
import iteration1.src.Models.CourseCode;
import iteration1.src.Models.CourseSession;
import org.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

    public static void main(String[] args) throws IOException {

        String filePath = "D:/PROJECTS/JAVATERMPROJECT/iteration1/src/lecture.json";
        JSONObject jsonObject = parseJSONFile(filePath);

        //CourseCode courseCode = new CourseCode(jsonObject.getString("courseCode"));

        JSONArray courseSessionsJSONObjects = new JSONArray(jsonObject.getJSONArray("courseSessions"));
        ArrayList<CourseSession> courses = new ArrayList<CourseSession>();

        for(int i = 0 ; i < courseSessionsJSONObjects.length() ; i++) {
        courses.add(new CourseSession((JSONObject)courseSessionsJSONObjects.get(i)));
        }

        //Course course = new Course(new CourseCode(jsonObject.getString("courseCode")),jsonObject.getInt("credit"), Arrays.asList(jsonObject.getJSONArray("preRequisiteCourse")),courses,)
    }
}
