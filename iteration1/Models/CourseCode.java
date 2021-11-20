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
    public String getCourseCode(){
        return  "temp";
    }
    public void setCourseYear(Semester semester){
        this.courseYear = semester.getSemesterNo();
    }
}
