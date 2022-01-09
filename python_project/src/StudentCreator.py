import datetime
from Models.Student import Student
from Models.StudentID import StudentID
from Models.Transcript import Transcript


class StudentCreator:

    def __init__(self, semester, advisor, takenCourses: None, teCourses: None, nteCourses: None):
        self.semester = semester
        self.advisor = advisor

        if takenCourses is None:
            self.takenCourses = []
        else:
            self.takenCourses = takenCourses

        if teCourses is None:
            self.teCourses = []
        else:
            self.teCourses = teCourses

        if nteCourses is None:
            self.nteCourses = []
        else:
            self.nteCourses = nteCourses

    def generateStudentID(self, index: int):
        department = "1501"
        entryYear = str(datetime.date.today().year)[2:]
        entryOrder = str(index).zfill(3)

        return StudentID(department, entryYear, entryOrder)

    def createRandomStudent(self, index: int, name: str):
        studentID = self.generateStudentID(index)
        transcript = Transcript()
        transcript.generateTranscript(
            self.semester, self.takenCourses, self.teCourses, self.nteCourses)

        return Student(name, studentID, self.semester, self.advisor, transcript)
