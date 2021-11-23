package iteration1.src.Models;

import iteration1.src.Resources.JSONConverter;
import iteration1.src.Resources.SemesterName;

public class Semester implements JSONConverter {
    private SemesterName semesterName;
    private int semesterNo;

    public Semester(int semesterNo) {

        this.semesterNo = semesterNo;
        setSemesterName();
    }

    public SemesterName getSemesterName() {
        return semesterName;
    }

    public int getSemesterNo() {
        return semesterNo;
    }

    private void setSemesterName() {
        this.semesterName = this.semesterNo % 2 == 0 ? SemesterName.SPRING : SemesterName.FALL ;
    }

    @Override
    public void toJSON() {
        // TODO: Implement this
    }

    @Override
    public void fromJSON() {
        // TODO: Implement this
    }

    @Override
    public String toString() {
        return "semesterName=" + semesterName +
                ", semesterNo=" + semesterNo;
    }
}
