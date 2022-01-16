class Course:

    def __init__(self, courseName, courseCode, credit, requiredCredits, courseSemester, courseType, preRequisisteCourses=None, courseSessions=None):
        self.courseName = courseName
        self.courseCode = courseCode
        self.credit = credit
        self.requiredCredits = requiredCredits
        self.courseSemester = courseSemester
        self.courseType = courseType

        if preRequisisteCourses is None:
            self.preRequisisteCourses = []
        else:
            self.preRequisisteCourses = preRequisisteCourses

        if courseSessions is None:
            self.courseSessions = []
        else:
            self.courseSessions = courseSessions

    def __eq__(self, other):
        return self.courseName == other.courseName and self.courseCode == other.courseCode and self.credit == other.credit and self.requiredCredits == other.requiredCredits and self.courseSemester == other.courseSemester and self.courseType == other.courseType and self.preRequisisteCourses == other.preRequisisteCourses and self.courseSessions == other.courseSessions

    def __str__(self):
        return f"{self.courseName} --Course Code: {self.courseCode} --Credit: {self.credit}"
