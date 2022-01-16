import re


class CourseCode:

    def __init__(self, courseCode):
        parts = re.split("(?<=\\D)(?=\\d)", courseCode)

        self.deptCode = parts[0]
        self.courseYear = parts[1][0]
        self.courseID = parts[1][1:]

    def __eq__(self, other):
        return self.deptCode == other.deptCode and self.courseYear == other.courseYear and self.courseID == other.courseID

    def __str__(self):
        return f"{self.deptCode}{self.courseYear}{self.courseID}"
