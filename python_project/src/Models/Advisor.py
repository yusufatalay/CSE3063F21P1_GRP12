

class Advisor:

    def __init__(self, name, studentList=None):
        self.name = name
        if studentList is None:
            self.studentList = []
        else:
            self.studentList = studentList

    def addStudent(self, stu):
        if stu not in self.studentList:
            self.studentList.append(stu)

    def printStudents(self):
        for stu in self.studentList:
            print("-->", stu.name)

    # Checking the eligibility of course quota
    def checkCourseQuota(self, course, selectedSession, stu):
        if selectedSession.numberOfStudents < selectedSession.courseQuota:
            selectedSession.numberOfStudents += 1
            return True
        else:
            denialMessage = f"Student couldn't register for {course.courseCode}  because of the quota problem "
            print(f"{stu.studentID} - {stu.name} : {denialMessage}")
            stu.addDenialMessage(denialMessage)
            return False

    # Checking the eligibility of courses' prerequisite
    def checkPreRequisite(self,course, stu):
        for failedCourse in stu.transcript.failedCourses:
            if failedCourse.courseCode in course.preRequisisteCourses:
                denialMessage = f"The system didn't allow {course.courseCode} student failed prerequisites {failedCourse.courseCode}"
                print(f"{stu.studentID} - {stu.name} : {denialMessage}")
                stu.addDenialMessage(denialMessage)
                return False
        return True

    # Checking the eligibility of courses' session on students' schedule
    def checkCollides(self,selectedCourse, selectedSession, stu):
        collideCounter = 0

        for session in stu.takenSessions:
            for i in range(7):
                for j in range(10):
                    if selectedSession.courseHour[i][j] and session.courseHour[i][j]:
                        collideCounter += 1
                        if (collideCounter > 1):
                            denialMessage = f"Advisor didn't approve {selectedCourse.courseCode} because of at least two hours collision with other courses in schedule"
                            print(
                                f"{stu.studentID} - {stu.name} : {denialMessage}")
                            stu.addDenialMessage(denialMessage)
                            return False
        return True

    # Checking the eligibility of courses' session on students' schedule
    def checkTotalCredits(self,course, stu):
        if stu.transcript.totalCredits >= course.requiredCredits:
            return True
        else:
            denialMessage = f"Advisor didn't approve {course.courseType} {course.courseCode} because student completed credits < {course.requiredCredits}"
            print(f"{stu.studentID} - {stu.name} : {denialMessage}")
            stu.addDenialMessage(denialMessage)
            return False

    def checkTELimitation(self,course, stu):
        counter = 0
        for takenCourse in stu.takenCourses:
            if takenCourse.courseType == "TE":
                counter += 1

        if counter == 1 and stu.semester.semesterName == "FALL":
            denialMessage = f"Advisor didn't approve {course.courseType} {course.courseCode} because student already took 1 TE and in FALL semester only 1 TE can be taken."
            print(f"{stu.studentID} - {stu.name} : {denialMessage}")
            stu.addDenialMessage(denialMessage)
            return False
        elif counter == 3 and stu.semester.semesterName == "SPRING":
            denialMessage = f"Advisor didn't approve {course.courseType} {course.courseCode} because student already took 3 TEs and in SPRING semester only 3 TEs can be taken."
            print(f"{stu.studentID} - {stu.name} : {denialMessage}")
            stu.addDenialMessage(denialMessage)
            return False
        else:
            return True

    def checkFTELimitation(self,course, stu):
        if course.courseType == "FTE" and stu.semester.semesterName == "FALL":
            denialMessage = f"Advisor didn't approve FTE {course.courseCode} because students can't take FTE in fall semester unless they are graduating this semester."
            print(f"{stu.studentID} - {stu.name} : {denialMessage}")
            stu.addDenialMessage(denialMessage)
            return False
        else:
            return True

    def approveCourse(self, stu, selectedCourse, selectedSession):
        if self.checkCourseQuota(selectedCourse, selectedSession, stu) and self.checkPreRequisite(selectedCourse, stu) and self.checkCollides(selectedCourse, selectedSession, stu) and self.checkTotalCredits(selectedCourse, stu):
            if selectedCourse.courseType == "TE":
                if not self.checkTELimitation(selectedCourse, stu):
                    return False

            elif selectedCourse.courseType == "FTE":
                if not self.checkFTELimitation(selectedCourse, stu):
                    return False

            selectedSession.incrementNumberOfStudents()
            return True
        else:
            return False