import random
from Semester import Semester


class Transcript:

    def __init__(self, totalCredits=0, gpa=0, passedCourses=None, failedCourses=None):
        self.totalCredits = totalCredits
        self.gpa = gpa

        if passedCourses is None:
            self.passedCourses = []
        elif passedCourses is not None:
            self.passedCourses = passedCourses

        if failedCourses is None:
            self.failedCourses = []
        elif failedCourses is not None:
            self.failedCourses = failedCourses

    def addPassedCourse(self, course):
        if course not in self.passedCourses:
            self.passedCourses.append(course)

    def addFailedCourse(self, course):
        if course not in self.failedCourses:
            self.failedCourses.append(course)

    def findGivenCredits(self):
        totalCredits = 0
        for course in self.passedCourses:
            totalCredits += course.credit

        for course in self.failedCourses:
            totalCredits += course.credit

        return totalCredits

    def calculateGPA(self):
        takenCredits = self.findGivenCredits()
        score = 0

        for course in self.passedCourses:
            randomGrade = random.randint(0, 7) / 2 + 1
            score += course.credit

        return score / takenCredits

    # mandatoryCourseList has past semesters' courses that are mandatory.
    def generateTranscript(self, semester: Semester, mandatoryCurseList=None, teCoCurseList=None, nteCoCurseList=None):
        localCourseList = mandatoryCurseList.copy()

        if semester.semesterNo > 2:
            localCourseList.append(random.choice(nteCoCurseList))

        if semester.semesterNo > 7:
            localCourseList.append(random.choice(teCoCurseList))
            localCourseList.append(random.choice(nteCoCurseList))
