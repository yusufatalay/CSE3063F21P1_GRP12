import json


def read_json(file_name):
    with open(f"python_project/src/JsonFiles/{file_name}", 'r', encoding="UTF-8") as file:
        data = json.load(file)
    return data


def createCourses(fileName: str, listName: str):
    courseJsonList = read_json(fileName)[listName]

    courseList = []

    for courseRaw in courseJsonList:
        course = Course(courseRaw["courseName"], courseRaw["courseCode"], courseRaw["credit"], courseRaw["requiredCredits"],
                        courseRaw["courseSemester"], courseRaw["courseType"], courseRaw["preRequisiteCourse"], courseRaw["courseSessions"])
        courseList.append(course)
    return courseList


createCourses("lectures.json", "courses")
