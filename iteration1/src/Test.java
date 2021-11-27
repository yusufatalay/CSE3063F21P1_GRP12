package iteration1.src;

import iteration1.src.Models.*;
import iteration1.src.Resources.CourseType;
import iteration1.src.Resources.SemesterName;
import iteration1.src.Services.StudentCreator;
import org.json.*;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {
        ArrayList<Course> fullCourseList = createCourses();
        ArrayList<Student> students = createStudents(fullCourseList);
        for (Student student : students) {
            student.selectAndEnrollCourses(fullCourseList);
        }
        File studentsFolder = new File("iteration1/Students");

        File freshmanFolder = new File("iteration1/Students/Freshman");
        File sophomoreFolder = new File("iteration1/Students/Sophomore");
        File juniorFolder = new File("iteration1/Students/Junior");
        File seniorFolder = new File("iteration1/Students/Senior");

        createDirectoriesWithCommonParent(studentsFolder, freshmanFolder, sophomoreFolder, juniorFolder, seniorFolder);

        // for each folder add appropriate student's json file to it also fill that file in the process
        File std = new File("");
        for (Student student : students) {
            switch (student.getSemester().getSemesterNo()) {
                case 1:
                case 2:
                    // create json file with this format -> <studentID>.json inside of approprite folder
                    std = new File("iteration1/Students/Freshman/" + student.getStudentID().toString() + ".json");
                    if (!std.exists()) {
                        std.createNewFile();
                    }
                    // fill that file
                    break;
                case 3:
                case 4:
                    std = new File("iteration1/Students/Sophomore/" + student.getStudentID().toString() + ".json");
                    if (!std.exists()) {
                        std.createNewFile();
                    }
                    break;
                case 5:
                case 6:
                    std = new File("iteration1/Students/Junior/" + student.getStudentID().toString() + ".json");
                    if (!std.exists()) {
                        std.createNewFile();
                    }
                    break;
                case 7:
                case 8:
                    std = new File("iteration1/Students/Senior/" + student.getStudentID().toString() + ".json");
                    if (!std.exists()) {
                        std.createNewFile();
                    }
                    break;
            }
            JSONObject obj = new JSONObject();
            obj.put("name", student.getName());
            obj.put("studentID", student.getStudentID().toString());
            obj.put("advisor", student.getAdvisor().getName());
            obj.put("GPA", student.getTranscript().getGpa() + "");
            JSONArray passedCoursesInfo = new JSONArray();
            for (Course pcourse : student.getTranscript().getPassedCourses()) {
                passedCoursesInfo.put(pcourse.toString());
            }
            obj.put("passedCourses", passedCoursesInfo);
            JSONArray failedCoursesInfo = new JSONArray();
            for (Course fcourse : student.getTranscript().getFailedCourses()) {
                failedCoursesInfo.put(fcourse.toString());
            }
            obj.put("failedCourses", failedCoursesInfo);

            obj.put("denialMessages", student.getDenialMessages().toArray());


            try (FileWriter file = new FileWriter(std.getAbsolutePath())) {
                //We can write any JSONArray or JSONObject instance to the file
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // implement trace logging for deny messages

    }


    public static boolean createDirectoriesWithCommonParent(
            File parent, File... subs) {

        parent.mkdirs();
        if (!parent.exists() || !parent.isDirectory()) {
            return false;
        }

        for (File sub : subs) {

            sub.mkdir();
            if (!sub.exists() || !sub.isDirectory()) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Student> createStudents(ArrayList<Course> fullCourseList) throws IOException {
        String namesPool = "names.json";
        String inputFile = "input.json";

        JSONArray namesArray = parseJSONFile(namesPool).getJSONArray("names");
        ArrayList<String> nameList = new ArrayList<>();
        for (Object name : namesArray) {
            nameList.add(name.toString());
        }

        String inputSemesterName = parseJSONFile(inputFile).getString("semester");
        int semesterSub = 0;
        if (inputSemesterName.equals("FALL")) {
            semesterSub = 1;
        }

        ArrayList<Student> studentsArrayList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            String advisorName = nameList.get((int) (Math.random() * nameList.size()));
            Advisor advisor = new Advisor(advisorName);
            nameList.remove(advisorName);

            Semester semester = new Semester((i + 1) * 2 - semesterSub);

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
        JSONArray courseJsonArray = parseJSONFile("lectures.json").getJSONArray("courses");
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

        Path pathOf = Paths.get("iteration1\\src\\" + filename).toAbsolutePath();
        String content = new String(Files.readAllBytes(pathOf));
        return new JSONObject(content);
    }
}
