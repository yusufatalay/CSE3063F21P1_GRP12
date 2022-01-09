class Course:

    def __init__(self, courseName, courseCode, credit, requiredCredits, courseSemester, courseType, preRequisisteCourses=None, couseSessions=None):
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

        if couseSessions is None:
            self.couseSessions = []
        else:
            self.couseSessions = couseSessions

    def __str__(self):
        return f"CourseName: {self.courseName} --Course Code: {self.courseCode} --Credit: {self.credit}"
