from ReadJson import *
from Models.Semester import Semester
from Models.Course import Course
import random
from src.Models.Advisor import Advisor
from src.Models.Student import Student

from src.StudentCreator import StudentCreator

def getPastCourses(fullCourseList:list(Course), semester:Semester):
    takenlist = list()
    
    for course in fullCourseList:
        if course.semester.semesterNo < semester.semesterNo:
            takenlist.append(course)
            continue
        break
    return takenlist

def createStudents():
    nameArray = createNames("names.json")

    semesterName = read_json("input.json")["semester"]
    semesterSub = 0
    if semesterName == "FALL":
        semesterSub = 1

    #studentList = list()
    mandatoryCourses = createCourses("lectures.json", "courses")
    nteCourses = createCourses("electives.json", "technicalElectives")
    teCourses = createCourses("electives.json", "nonTechnicalElectives")
    fteCourses = createCourses("electives.json", "facultyTechnicalElectives")

    for i in range(4):
        advisorName = random.choice(nameArray)
        nameArray.remove(advisorName)

        semester = Semester((i + 1) * 2 - semesterSub)
        courseList = getPastCourses()

        advisor = Advisor(advisorName)
        studentCreator = StudentCreator(semester, advisor, mandatoryCourses, teCourses, nteCourses)

        for j in range(70):
            studentName = random.choice(nameArray)
            nameArray.remove(studentName)

            stu = studentCreator.createRandomStudent(j, studentName)
            #studentList.append(stu)
            advisor.addStudent(stu)
            enrollStudents(stu)

    #return studentList

def enrollStudents(student:Student):
    