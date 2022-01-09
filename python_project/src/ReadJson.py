import json
from Models.Course import Course
from Models.CourseSession import CourseSession


def read_json(file_name):
    with open(f"python_project/src/JsonFiles/{file_name}", 'r', encoding="UTF-8") as file:
        data = json.load(file)
    return data

def createNames(fileName: str):
    nameJsonList = read_json(fileName)
    nameList = nameJsonList["names"]
    return nameList

def createCourses(fileName: str, listName: str):
    courseJsonList = read_json(fileName)[listName]

    courseList = []

    for courseRaw in courseJsonList:
        courseSessions = []
        for session in courseRaw["courseSessions"]:
            courseSessions.append(CourseSession(session))
        course = Course(courseRaw["courseName"], courseRaw["courseCode"], courseRaw["credit"], courseRaw["requiredCredits"],
                        courseRaw["courseSemester"], courseRaw["courseType"], courseRaw["preRequisiteCourse"], courseSessions)
        courseList.append(course)
    return courseList


createCourses("lectures.json", "courses")
