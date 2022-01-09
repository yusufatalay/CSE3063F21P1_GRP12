class CourseSession:


    def __init__(self, courseSessions=None):
        self.numberOfStudents = 0
        self.courseQuota = int(courseSessions["quota"]) 
        self.courseHour = [[False for _ in range(10)] for _ in range(7)]
        for i in courseSessions["sessionHours"]:
            self.courseHour[int(i["day"])][int(i["hour"])] = True

    def incrementNumberOfStudents(self):
        self.numberOfStudents += 1
