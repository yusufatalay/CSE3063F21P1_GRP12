package iteration1.Models;

public class CourseCode {
    private String departmentCode;
    private int courseYear;
    private String courseID;
    public CourseCode(String departmentCode, int courseYear, String courseID) {
        this.departmentCode = departmentCode;
        this.courseYear = courseYear;
        this.courseID = courseID;
    }

    public void setCourseYear(Semester semester){
        this.courseYear = semester.getSemesterNo();
    }

    @Override
    public String toString(){

        StringBuilder courseCode = new StringBuilder();
        courseCode.append(departmentCode);
        courseCode.append(courseYear);
        courseCode.append(courseID);
        return courseCode.toString();
    }
}
