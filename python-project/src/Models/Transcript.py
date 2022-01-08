import random
from Semester import Semester


class Transcript:
# Note to the future: We should probably set these passed&failed variables in the constructor with the methods below

    def __init__(self, totalCredits=0, gpa=0, passedCourses=None, failedCourses=None):
        self.totalCredits = totalCredits
        self.gpa = gpa

        if passedCourses is None:
            self.passedCourses = []
        elif passedCourses is not None:
            self.passedCourses =    passedCourses 

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

        # To Yasin: Are we supposed to add failed courses' credits? Since the failed courses are not counted in the
        for course in self.failedCourses:
            totalCredits += course.credit

        return totalCredits

    def calculateGPA(self):
        takenCredits = self.findGivenCredits()
        score = 0

        for course in self.passedCourses:
            randomGrade = random.randint(0, 6) / 2 + 1
            score += randomGrade * course.credit

        return score / takenCredits

    # generateFailedCourses() is used to generate failed courses randomly and adds them to the locel list.
    def generateFailedCourses(self, semester: Semester, localCourseList=None):
        for course in localCourseList:
            if random.randint(0, 1) == 1:
                self.addFailedCourse(course)

    def generatePassedCourses(self, semester: Semester, localCourses,failedCourses):
        for course in localCourses:
            if course not in failedCourses:
                self.addPassedCourse(course)

    # mandatoryCourseList has past semesters' courses that are mandatory.
    def generateTranscript(self, semester: Semester, mandatoryCourseList=None, teCourseList=None, nteCourseList=None):
        localCourseList = mandatoryCourseList.copy()
        self.failedCourses = self.generateFailedCourses(semester, localCourseList)
        self.passedCourses = self.generatePassedCourses(semester, localCourseList,failedCourses=self.failedCourses)
        totalCredits = self.findGivenCredits()
        gpa = self.calculateGPA()

        if semester.semesterNo > 2:
            localCourseList.append(random.choice(nteCourseList))

        if semester.semesterNo > 7:
            localCourseList.append(random.choice(teCourseList))
            localCourseList.append(random.choice(nteCourseList))

        return Transcript(totalCredits, gpa, self.passedCourses, self.failedCourses)