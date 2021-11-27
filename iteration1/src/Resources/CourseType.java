package iteration1.src.Resources;

public enum CourseType {
// Course types in our university
    NTE,
    UE,
    TE,
    FTE,
    LAB,
    MANDATORY("");

    final String name;

    CourseType(String name) { this.name = name; }

    CourseType() { this(null); }

    @Override
    public String toString() {
        return name == null ? super.toString() : name;
    }

    public static CourseType getCourseType(String type) {
        if(type.equals("NTE"))
            return CourseType.NTE;
        else if (type.equals("UE"))
            return CourseType.UE;
        else if (type.equals("TE"))
            return CourseType.TE;
        else if (type.equals("FTE"))
            return CourseType.FTE;
        else if(type.equals("LAB"))
            return CourseType.LAB;
        else
            return CourseType.MANDATORY;
    }
}
