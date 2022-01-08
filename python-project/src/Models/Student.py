class Student:

    def __init__(self, name, studentID, semester, advisor, transcript, takenCourses=None, takenSessions=None, denialMessages=None):
        self.name = name
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

    def addSession(self, session):
        if session not in self.takenSessions:
            self.takenSessions.append(session)

    def addDenialMessage(self, denialMessage):
        if denialMessage not in self.denialMessages:
            self.denialMessages.append(denialMessage)

    # TODO: check every course for each condition one by one. add or not add them according to the result.

    def selectAndEnrollCourses(self):
        pass

    def sendToApproval(self):
        pass
