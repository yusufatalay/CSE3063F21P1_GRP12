package iteration1.src.Models;

public class CourseCode {
    private String departmentCode;
    private int courseYear;
    private String courseID;

    // complete code is the complete course code like CSE3055 and deconstructs it here
    public CourseCode(String completeCode) {


        String[] part = completeCode.split("(?<=\\D)(?=\\d)");
        this.departmentCode = part[0];
        this.courseYear = Integer.parseInt(part[1].substring(0, 1));
        this.courseID = part[1].substring(1);
    }

    public void setCourseYear(Semester semester) {
        this.courseYear = semester.getSemesterNo();
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public String getCourseID() {
        return courseID;
    }

    @Override
    public String toString() {
        StringBuilder courseCode = new StringBuilder();
        courseCode.append(departmentCode);
        courseCode.append(courseYear);
        courseCode.append(courseID);
        return courseCode.toString();
    }
}

