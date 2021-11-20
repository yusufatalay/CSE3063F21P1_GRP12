package iteration1.Services;

import iteration1.Models.Course;
import iteration1.Resources.CourseType;

public class DenialMessageProvider {

    private String errorMessage;

    public  DenialMessageProvider(String denier, Course deniedCourse , String denyMessage){
        errorMessage = "The "+ denier+ " didn't approve "+  deniedCourse.getCourseType().name() +" "+deniedCourse.getCourseCode()+" because "+ denyMessage;
    }

    public  DenialMessageProvider(String denier, Course deniedCourse , String denyMessage,Course problemCourse){
        errorMessage = "The "+ denier+ " didn't approve "+  deniedCourse.getCourseType().name() +" "+deniedCourse.getCourseCode()+" because "+ denyMessage+" " +problemCourse.getCourseCode();
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
