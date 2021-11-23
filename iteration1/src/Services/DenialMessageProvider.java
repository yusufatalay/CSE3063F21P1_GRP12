package iteration1.src.Services;

import iteration1.src.Models.Course;


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
