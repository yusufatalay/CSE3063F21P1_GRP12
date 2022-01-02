class CourseSession:

    numberOfStudents = 0

    def __init__(self, courseSessions=None):
        quota = courseSessions["quota"]

        self.courseHour = [[False for i in range(10)] for j in range(7)]
        for i in courseSessions["sessinHours"]:
            self.courseHour[int(i["day"])][int(i["hour"])] = True

    def incrementEnrolledStudents(self):
        self.numberOfStudents += 1
