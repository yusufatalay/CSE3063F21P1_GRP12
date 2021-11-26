package iteration1.src.Models;

import iteration1.src.Resources.SemesterName;

public class Semester implements Comparable<Semester> {
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
        this.semesterName = this.semesterNo % 2 == 0 ? SemesterName.SPRING : SemesterName.FALL;
    }

    public boolean equals(Semester obj) {
        return semesterNo == obj.semesterNo;
    }

    @Override
    public int compareTo(Semester obj) {
        if (obj.semesterNo > semesterNo) {
            return -1;
        } else if (semesterNo > obj.semesterNo) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "semesterName=" + semesterName +
                ", semesterNo=" + semesterNo;
    }
}
