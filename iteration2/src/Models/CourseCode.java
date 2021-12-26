package iteration2.src.Models;

// CourseCode class holds the code of course.
public class CourseCode {
    private String departmentCode;
    private int courseYear;
    private String courseID;

    // Complete code is the complete course code like CSE3055 and deconstructs it here.
    public CourseCode(String completeCode) {
        String[] part = completeCode.split("(?<=\\D)(?=\\d)");

        this.departmentCode = part[0];
        this.courseYear = Integer.parseInt(part[1].substring(0, 1));
        this.courseID = part[1].substring(1);
    }

    // General getters created for CourseCode.
    public String getDepartmentCode() {
        return departmentCode;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public String getCourseID() {
        return courseID;
    }

    // toString returns completeCode.
    @Override
    public String toString() {
        return departmentCode + courseYear + courseID;
    }
}

