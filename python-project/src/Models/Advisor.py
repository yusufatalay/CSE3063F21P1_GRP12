import Student
import Course
import CourseSession

from _typeshed import Self


class Advisor:

    def __init__(self, name, studentList=None):
        self.name = name
        if studentList is None:
            self.students = []
        else:
            self.studentList = studentList

    def addStudent(self, stu):
        if stu not in self.studentList:
            self.studentList.append(stu)

    def printStudents(self):
        for stu in self.studentList:
            print("-->", stu.name)

    #Checking the eligibility of course quota
    def checkCourseQuota(course:Course, selectedSession:CourseSession, stu:Student):
        if selectedSession.numberOfStudents < selectedSession.courseQuota:
            selectedSession.numberOfStudents += 1
            return True
        else:
            #DenialMessage courseQuota is full!
            return False

    #Checking the eligibility of courses' prerequisite
    def checkPreRequisite(course:Course, stu:Student):
        for failedCourse in stu.transcript.failedCourses:
            if course.courseCode == failedCourse.courseCode:
                return False
        
        return True

    #Checking the eligibility of courses' session on students' schedule
    def checkCollides(course:Course, selectedSession:CourseSession, stu:Student):
        collideCounter = 0

        #SelectedCoursesNSessions must be added to Student as an CourseSession Array to control the students' schedule!
        for session in stu.takenSessions:
            #burayı tam hatırlayamadım
            if selectedSession == session:
                #DenialMessage course collides with another course!
                return False

        return True

    #Checking the eligibility of courses' session on students' schedule
    def checkTotalCredits(course:Course, stu:Student):
        if stu.transcript.totalCredits >= course.requiredCredits:
            return True
        else:
            #DenialMessage total credits not enough!
            return False

    def checkTELimitation(course:Course, stu:Student):
        pass

    def checkFTELimitation(course:Course, stu:Student):
        pass

    def approveCourse(self, selectedCourse:Course, selectedSession:CourseSession, stu:Student):
        if self.checkCourseQuota(selectedCourse, selectedSession, stu) and self.checkPreRequisite(selectedCourse, stu) and self.checkCollides(selectedCourse, selectedSession, stu) and self.checkTotalCredits(selectedCourse, stu) and self.checkTELimitation(selectedCourse, stu) and self.checkFTELimitation(selectedCourse, stu):
            return True
        else:
            return False