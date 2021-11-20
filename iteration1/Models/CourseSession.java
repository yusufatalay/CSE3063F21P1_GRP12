package iteration1.Models;
import java.util.ArrayList;
import java.util.Date;

public class CourseSession extends Course{
    private Date startHour;
    private Date endHour;
    private int courseQuota;
    private int courseCurrentStudentNumber = 0;

    public CourseSession(CourseCode courseCode, int credit, ArrayList<Course> preRequisiteCourse, ArrayList<CourseSession> courseSessions, int requiredCredits, Semester courseSemester, Date startHour, Date endHour, int courseQuota, int courseCurrentStudentNumber) {
        super(courseCode, credit, preRequisiteCourse, courseSessions, requiredCredits, courseSemester);
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

    public int getCourseCurrentStudentNumber()
    {
        return courseCurrentStudentNumber;
    }
}
