class StudentID:

    def __init__(self, studentDepartment, studentEntryYear, studentEntryOrder):
        self.studentDepartment = studentDepartment
        self.studentEntryYear = studentEntryYear
        self.studentEntryOrder = studentEntryOrder

    def __str__(self):
        return f"{self.studentDepartment}{self.studentEntryYear}{self.studentEntryOrder}"
