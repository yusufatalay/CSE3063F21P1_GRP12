package iteration1.Models;
import java.util.Date;

public class CourseSession {
    private Date startHour;
    private Date endHour;

    public CourseSession(Date startHour, Date endHour) {
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Date getStartHour() {
        return this.startHour;
    }

    public Date getEndHour() {
        return this.endHour;
    }
}
