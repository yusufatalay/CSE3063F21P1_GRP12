class CourseSession:

    numberOfStudents = 0

    def __init__(self, courseSessions=None):
        self.courseQuota = courseSessions["quota"]

        self.courseHour = [[False for _ in range(10)] for _ in range(7)]
        for i in courseSessions["sessinHours"]:
            self.courseHour[int(i["day"])][int(i["hour"])] = True

    def incrementNumberOfStudents(self):
        self.numberOfStudents += 1
