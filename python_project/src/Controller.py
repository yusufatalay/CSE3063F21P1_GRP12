import random
import os
import shutil
from Models.Advisor import Advisor
from Models.Course import Course
from Models.Semester import Semester
from ReadJson import *
from StudentCreator import StudentCreator

def getPastCourses(fullCourseList , semester):
    takenlist = list()
    
    for course in fullCourseList:
        if course.courseSemester < semester.semesterNo:
            takenlist.append(course)
            continue
        break
    return takenlist

def createDirectories():
    path = os.path.join(os.path.abspath("./python_project"),"Students")
    
    if os.path.exists(path):
        shutil.rmtree(path) 
        
    os.mkdir(path)     
    os.mkdir(os.path.join(os.path.abspath("./python_project/Students"),"Freshman"))
    os.mkdir(os.path.join(os.path.abspath("./python_project/Students"),"Sophomore"))
    os.mkdir(os.path.join(os.path.abspath("./python_project/Students"),"Junior"))
    os.mkdir(os.path.join(os.path.abspath("./python_project/Students"),"Senior"))

def createStudentFile(student):
    studentFile = {
        "studentID": student.studentID.__str__(),
        "advisor": student.advisor.name,
        "name": student.name,
        "GPA": student.transcript.gpa,
        "takenCourses": [code.__str__() for code in student.takenCourses],
        "passedCourses": [code.__str__() for code in student.transcript.passedCourses],
        "denialMessages": student.denialMessages,
        "failedCourses": [code.__str__() for code in student.transcript.failedCourses],
    }

    if student.semester.semesterNo in (1,2):
        with open(f"python_project/Students/Freshman/{student.studentID}.json", 'w') as file:
            json.dump(studentFile, file)
    elif student.semester.semesterNo in (3,4):
        with open(f"python_project/Students/Sophomore/{student.studentID}.json", 'w') as file:
            json.dump(studentFile, file)
    elif student.semester.semesterNo in (5,6):
        with open(f"python_project/Students/Junior/{student.studentID}.json", 'w') as file:
            json.dump(studentFile, file)                
    elif student.semester.semesterNo in (7,8):
        with open(f"python_project/Students/Senior/{student.studentID}.json", 'w') as file:
            json.dump(studentFile, file)

def createStudents():
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

    for i in range(4):
        advisorName = random.choice(nameArray)
        nameArray.remove(advisorName)

        semester = Semester((i + 1) * 2 - semesterSub)
        courseList = getPastCourses(mandatoryCourses, semester)

        advisor = Advisor(advisorName)
        studentCreator = StudentCreator(semester, advisor, mandatoryCourses, teCourses, nteCourses)

        for j in range(70):
            studentName = random.choice(nameArray)
            nameArray.remove(studentName)

            stu = studentCreator.createRandomStudent(j + 1, studentName)
            stu.selectAndEnrollCourses(mandatoryCourses,teCourses,nteCourses,fteCourses)
            advisor.addStudent(stu)
            createStudentFile(stu)
        
