package iteration1.src.Models;

import iteration1.src.Resources.SemesterName;

public class Semester implements Comparable<Semester> {
    private SemesterName semesterName;
    private int semesterNo;

    public Semester(int semesterNo) {
        this.semesterNo = semesterNo;
        setSemesterName();
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

    public SemesterName getSemesterName() {
        return semesterName;
    }

    public int getSemesterNo() {
        return semesterNo;
    }

    private void setSemesterName() {
        semesterName = semesterNo % 2 == 0 ? SemesterName.SPRING : SemesterName.FALL;
    }

    @Override
    public String toString() {
        return "semesterName=" + semesterName +
                ", semesterNo=" + semesterNo;
    }
}
