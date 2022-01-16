# fmt: off
import random
import sys
from traceback import print_tb
sys.path.append("..")
from Services.ReadJson import *
from Services.WriteJson import *
from Services.StudentCreator import StudentCreator
from Models.Advisor import Advisor
from Models.Semester import Semester
# fmt: on


def getPastCourses(fullCourseList, semester):
    takenlist = list()

    for course in fullCourseList:
        if course.courseSemester < semester.semesterNo:
            takenlist.append(course)
            continue
        break
    return takenlist


def createStudentFile(student):
    schedule = scheduleFormat(student)
    studentFile = {
        "studentID": student.studentID.__str__(),
        "advisor": student.advisor.name,
        "name": student.name,
        "GPA": student.transcript.gpa,
        "takenCourses": [course.__str__() for course in student.takenCourses],
        "passedCourses": [
            course.__str__() for course in student.transcript.passedCourses
        ],
        "denialMessages": student.denialMessages,
        "failedCourses": [
            course.__str__() for course in student.transcript.failedCourses
        ],
        "schedule": schedule,
    }

    if student.semester.semesterNo in (1, 2):
        write_json(studentFile, f"Freshman/{student.studentID}.json")
    elif student.semester.semesterNo in (3, 4):
        write_json(studentFile, f"Sophomore/{student.studentID}.json")
    elif student.semester.semesterNo in (5, 6):
        write_json(studentFile, f"Junior/{student.studentID}.json")
    elif student.semester.semesterNo in (7, 8):
        write_json(studentFile, f"Senior/{student.studentID}.json")


def createStudents():
    with open('python_project/src/simulation.log', 'w') as f:
        f.write('LOG FILE\n')

    nameArray = createNames("names.json")

    semesterName = read_json("input.json")["semester"]
    semesterSub = 0
    if semesterName == "FALL":
        semesterSub = 1

    mandatoryCourses = createCourses("lectures.json", "courses")
    nteCourses = createCourses("electives.json", "technicalElectives")
    teCourses = createCourses("electives.json", "nonTechnicalElectives")
    fteCourses = createCourses("electives.json", "facultyTechnicalElectives")

    createDirectories()
    numberOfStudents = read_json("input.json")["numberOfStudentsPerSemester"]
    failRate = read_json("input.json")["failRatePercent"]

    for i in range(4):
        advisorName = random.choice(nameArray)
        nameArray.remove(advisorName)

        semester = Semester((i + 1) * 2 - semesterSub)
        courseList = getPastCourses(mandatoryCourses, semester)

        advisor = Advisor(advisorName)
        studentCreator = StudentCreator(
            semester, advisor, failRate, courseList, teCourses, nteCourses
        )

        for j in range(numberOfStudents):
            studentName = random.choice(nameArray)
            nameArray.remove(studentName)

            stu = studentCreator.createRandomStudent(j + 1, studentName)
            stu.selectAndEnrollCourses(
                mandatoryCourses, teCourses, nteCourses, fteCourses
            )
            advisor.addStudent(stu)
            createStudentFile(stu)

    addStatisticsToLogFile()
