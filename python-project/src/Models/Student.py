from Person import Person


class Student(Person):

    def __init__(self, name, studentID, semester, advisor, transcript, takenCourses=None, takenSessions=None, denialMessages=None):
        super().__init__(name)
        self.studentID = studentID
        self.semester = semester
        self.advisor = advisor
        self.transcript = transcript

        if takenCourses is None:
            self.takenCourses = []
        elif takenCourses is not None:
            self.takenCourses = takenCourses

        if takenSessions is None:
            self.takenSessions = []
        elif takenSessions is not None:
            self.takenSessions = takenSessions

        if denialMessages is None:
            self.denialMessages = []
        elif denialMessages is not None:
            self.denialMessages = denialMessages

    def addCourse(self, course):
        if course not in self.takenCourses:
            self.takenCourses.append(course)

    def removeCourse(self, course):
        if course in self.takenCourses:
            self.takenCourses.remove(course)

    def addSession(self, session):
        if session not in self.takenSessions:
            self.takenSessions.append(session)

    def removeSession(self, session):
        if session in self.takenSessions:
            self.takenSessions.remove(session)

    def addDenialMessage(self, denialMessage):
        if denialMessage not in self.denialMessages:
            self.denialMessages.append(denialMessage)

    def removeDenialMessage(self, denialMessage):
        if denialMessage in self.denialMessages:
            self.denialMessages.remove(denialMessage)

    # TODO: check every course for each condition one by one. add or not add them according to the result.

    def selectAndEnrollCourses(self):
        pass

    def sendToApproval(self):
        pass
