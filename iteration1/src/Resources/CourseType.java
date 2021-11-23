package iteration1.src.Resources;

public enum CourseType {
// Course types in our university
    NTE,
    UE,
    TE,
    FTE,
    MANDATORY("");

    final String name;
    CourseType(String name) { this.name = name; }
    CourseType() { this(null); }
    @Override
    public String toString() {
        return name == null ? super.toString() : name;
    }
}
