package iteration1.Models;

import iteration1.Resources.SemesterName;

public class Semester {
    private SemesterName semesterName;
    private int semesterNo;

    public Semester(SemesterName semesterName, int semesterNo) {
        this.semesterName = semesterName;
        this.semesterNo = semesterNo;
    }

    public SemesterName getSemesterName() {
        // TODO: PASS
    return semesterName;
    }

    public int getSemesterNo() {
        return semesterNo;
    }
    private void semesterName(SemesterName semesterName) {
        // TODO: implement this, if semester no is even then semester name is SPRING othervise its FALL
        this.semesterName = semesterName;
    }




}
