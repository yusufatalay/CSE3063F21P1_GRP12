class CourseCode:

    def __init__(self, courseCode):
        parts = courseCode.split("(?<=\\D)(?=\\d)")

        self.deptCode = parts[0]
        self.courseYear = parts[1]
        self.courseID = parts[2]

    def __str__(self):
        return f"{self.deptCode}{self.courseYear}{self.courseID}"
