# fmt: off
import datetime
import math
import sys
sys.path.append("..")
from Models.Student import Student
from Models.StudentID import StudentID
from Models.Transcript import Transcript
# fmt: on


class StudentCreator:
    def __init__(
        self,
        semester,
        advisor,
        failRate,
        takenCourses: None,
        teCourses: None,
        nteCourses: None,
    ):
        self.semester = semester
        self.advisor = advisor
        self.failRate = failRate

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
        entryYear = str(
            datetime.date.today().year - math.ceil(self.semester.semesterNo / 2)
        )[2:]
        entryOrder = str(index).zfill(3)

        return StudentID(department, entryYear, entryOrder)

    def createRandomStudent(self, index: int, name: str):
        studentID = self.generateStudentID(index)
        transcript = Transcript()
        transcript.generateTranscript(
            self.failRate,
            self.semester,
            self.takenCourses,
            self.teCourses,
            self.nteCourses,
        )

        return Student(name, studentID, self.semester, self.advisor, transcript)
