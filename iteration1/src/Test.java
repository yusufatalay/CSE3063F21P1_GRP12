package iteration1.src;

import iteration1.src.Models.*;
import iteration1.src.Resources.CourseType;
import iteration1.src.Services.StudentCreator;
import org.json.*;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {
        //ArrayList<Student> students = createStudents();

        URL url = Test.class.getResource("input.json");
        System.out.println(url.getPath());
    }

    private static ArrayList<Student> createStudents() throws IOException {
        String filePath = "D:/Workspaces/CSE3063F21P1_GRP12/iteration1/src/names.json";
        String inputPath = "C:/Users/Eren/IdeaProjects/CSE3063F21P1_GRP12/iteration1/src/input.json";

        JSONArray namesArray = parseJSONFile(filePath).getJSONArray("names");
        ArrayList<String> nameList = new ArrayList<>();
        for (Object name : namesArray) {
            nameList.add(name.toString());
        }

        ArrayList<Student> studentsArrayList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            String advisorName = nameList.get((int) (Math.random() * nameList.size()));
            Advisor advisor = new Advisor(advisorName);
            nameList.remove(advisorName);

            Semester semester = new Semester((i + 1) * 2);

            ArrayList<Course> fullCourseList = createCourses();
            ArrayList<Course> courseArrayList = getPastCourses(fullCourseList, semester);
            // semester "fall" ise numaraya -1 ekle
            StudentCreator studentCreator = new StudentCreator(semester, advisor, courseArrayList);

            for (int j = 1; j <= 70; j++) {
                String name = nameList.get((int) (Math.random() * nameList.size()));
                nameList.remove(name);

                studentsArrayList.add(studentCreator.createRandomStudent(j, name));
            }
        }

        return studentsArrayList;
    }

    private static ArrayList<Course> getPastCourses(ArrayList<Course> fullCourseList, Semester semester) {
        ArrayList<Course> takenList = new ArrayList<>();
        for (Course course : fullCourseList) {
            if (course.getCourseSemester().compareTo(semester) < 0) {
                takenList.add(course);
                continue;
            }
            break;
        }

        return takenList;
    }

    private static ArrayList<Course> createCourses() throws IOException {
        String filePath = "D:/Workspaces/CSE3063F21P1_GRP12/iteration1/src/lectures.json";
        JSONArray courseJsonArray = parseJSONFile(filePath).getJSONArray("courses");
        ArrayList<JSONObject> courseJSON = new ArrayList<>();
        for (int i = 0; i < courseJsonArray.length(); i++) {
            courseJSON.add((JSONObject) (courseJsonArray.get(i)));
        }

        ArrayList<Course> courseList = new ArrayList<>();
        for (JSONObject course : courseJSON) {
            String courseName = course.getString("courseName");
            CourseCode courseCode = new CourseCode(course.getString("courseCode"));
            CourseType courseType = CourseType.getCourseType(course.getString("courseType"));
            int credit = course.getInt("credit");
            int requiredCredits = course.getInt("requiredCredits");
            Semester courseSemester = new Semester(course.getInt("courseSemester"));

            JSONArray preRequisiteJSON = new JSONArray(course.getJSONArray("preRequisiteCourse"));
            ArrayList<CourseCode> preRequisites = new ArrayList<>();

            for (int i = 0; i < preRequisiteJSON.length(); i++) {
                preRequisites.add(new CourseCode(preRequisiteJSON.get(i).toString()));
            }

            JSONArray courseSessionsJSONObjects = new JSONArray(course.getJSONArray("courseSessions"));
            ArrayList<CourseSession> courseSessions = new ArrayList<>();

            for (int i = 0; i < courseSessionsJSONObjects.length(); i++) {
                courseSessions.add(new CourseSession((JSONObject) courseSessionsJSONObjects.get(i)));
            }

            Course _course = new Course(courseName, courseCode, credit, preRequisites, courseSessions, requiredCredits, courseSemester, CourseType.MANDATORY);  // CourseType will be dynamic.
            courseList.add(_course);
        }

        return courseList;
    }

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        Path path = Paths.get(filename);

        String content = new String(Files.readAllBytes(path));
        return new JSONObject(content);
    }
}
