from Person import Person


class Student(Person):

    def __init__(self, name, studentID, semester, advisor, transcript):
        super().__init__(name)
        self.studentID = studentID
        self.semester = semester
        self.advisor = advisor
        self.transcript = transcript

    def selectAndEnrollCourses(self):
        pass

    def sendToApproval(self):
        pass
