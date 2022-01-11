import random


class Transcript:
    # Note to the future: We should probably set these passed and failed variables in the constructor with the methods below

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
            randomGrade = random.randint(0, 6) / 2 + 1
            score += randomGrade * course.credit

        return round(score / takenCredits, 2)

    # generateFailedCourses() is used to generate failed courses randomly and adds them to the locel list.
    def generateFailedCourses(self, failRate, localCourseList=None):
        for course in localCourseList:
            if random.randint(1, 100) <= failRate:
                self.addFailedCourse(course)

    def generatePassedCourses(self, localCourses):
        for course in localCourses:
            if course not in self.failedCourses:
                self.addPassedCourse(course)

    # mandatoryCourseList has past semesters' courses that are mandatory.
    def generateTranscript(
        self,
        failRate,
        semester,
        mandatoryCourseList=None,
        teCourseList=None,
        nteCourseList=None,
    ):
        localCourseList = mandatoryCourseList[:]
        if semester.semesterNo > 2:
            localCourseList.append(random.choice(nteCourseList))

        if semester.semesterNo > 7:
            localCourseList.append(random.choice(teCourseList))
            localCourseList.append(random.choice(nteCourseList))

        self.generateFailedCourses(failRate, localCourseList)
        self.generatePassedCourses(localCourseList)
        self.totalCredits = self.findGivenCredits()

        if semester.semesterNo == 1:
            self.gpa = 0
        else:
            self.gpa = self.calculateGPA()
