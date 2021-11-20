package iteration1.Models;
import java.util.ArrayList;
import java.util.Date;

public class CourseSession{
    private Date startHour;
    private Date endHour;
    private int courseQuota;
    private int courseCurrentStudentNumber = 0;

    public CourseSession(Date startHour, Date endHour, int courseQuota, int courseCurrentStudentNumber) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.courseQuota = courseQuota;
        this.courseCurrentStudentNumber = courseCurrentStudentNumber;
    }

    public Date getStartHour() {
        return this.startHour;
    }

    public Date getEndHour() {
        return this.endHour;
    }

    public int getCourseQuota() {
        return courseQuota;
    }

    public int getCourseCurrentStudentNumber()
    {
        return courseCurrentStudentNumber;
    }
}
