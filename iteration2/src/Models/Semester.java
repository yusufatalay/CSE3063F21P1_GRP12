package iteration2.src.Models;

import iteration2.src.Resources.SemesterName;

// This class holds the semester.
public class Semester implements Comparable<Semester> {
    // Each semester has a name and number.
    private SemesterName semesterName;
    private int semesterNo;

    // Semesters will be created according to their number.
    public Semester(int semesterNo) {
        this.semesterNo = semesterNo;
        setSemesterName();
    }

    // Regular getters.
    public SemesterName getSemesterName() {
        return semesterName;
    }

    public int getSemesterNo() {
        return semesterNo;
    }

    // Sets the semesterName according to semesterNo.
    private void setSemesterName() {
        semesterName = semesterNo % 2 == 0 ? SemesterName.SPRING : SemesterName.FALL;
    }

    // equals method for control.
    public boolean equals(Semester obj) {
        return semesterNo == obj.semesterNo;
    }

    // compareTo method for comparison.
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

    // toString prints the semester's name and no.
    @Override
    public String toString() {
        return "semesterName=" + semesterName +
                ", semesterNo=" + semesterNo;
    }
}
