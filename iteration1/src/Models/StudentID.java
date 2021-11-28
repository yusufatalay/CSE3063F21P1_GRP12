package iteration1.src.Models;

public class StudentID {

    // Attributes in order to create student's id based on his/her entry year, department and entry order
    private String studentDepartment;
    private String studentEntryYear;
    private String studentEntryOrder;
    public StudentID(String studentDepartment, String getStudentEntryYear, String studentEntryOrder) {
        this.studentDepartment = studentDepartment;
        this.studentEntryYear = getStudentEntryYear;
        this.studentEntryOrder = studentEntryOrder;
    }


    @Override
    public String toString() {
        return studentDepartment + studentEntryYear + studentEntryOrder;
    }
}
