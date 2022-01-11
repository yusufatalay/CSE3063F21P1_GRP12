import random


class Student:
    def __init__(
        self,
        name,
        studentID,
        semester,
        advisor,
        transcript,
        takenCourses=None,
        takenSessions=None,
        denialMessages=None,
    ):
        # Note to the reader, variables defined in the __init__ are belong to the object itself. The ones outside are belong to the class.
        self.name = name
        self.studentID = studentID
        self.semester = semester
        self.advisor = advisor
        self.transcript = transcript
        self.schedule = [[None for _ in range(10)] for _ in range(7)]

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

    # This method selects random courses for the current semester in a given course pool

    def selectCourses(self, allCourses, teCourses, nteCourses, fteCourses):
        courses = []
        for _course in allCourses:
            # If the selected course's courseSemester equals the student's current semester
            # we add the course.
            if self.semester.semesterNo == _course.courseSemester:
                courses.append(_course)

        tempTECourses = teCourses.copy()
        if self.semester.semesterNo == 7:
            rndCourseIndex = random.randint(0, len(teCourses) - 1)
            courses.append(teCourses[rndCourseIndex])

            rndCourseIndex = random.randint(0, len(nteCourses) - 1)
            courses.append(nteCourses[rndCourseIndex])

        # if the student is in the last semester, we choose 3 TEs, 1 NTE and 1 FTE.
        elif self.semester.semesterNo == 8:
            for _ in range(3):  # this loop will run 3 times
                rndIndex = random.randint(0, len(tempTECourses) - 1)
                courses.append(tempTECourses[rndIndex])
                tempTECourses.remove(tempTECourses[rndIndex])

            rndIndex = random.randint(0, len(nteCourses) - 1)
            courses.append(nteCourses[rndIndex])
            rndIndex = random.randint(0, len(fteCourses) - 1)
            courses.append(fteCourses[rndIndex])
        # If the student is his/her second semester: we can add single nte course.
        elif self.semester.semesterNo == 2:
            rndIndex = random.randint(0, len(nteCourses) - 1)
            courses.append(nteCourses[rndIndex])

        # Add the courses that student has failed.
        courses = self.transcript.failedCourses + courses

        return courses

    def selectSessions(self, courses):
        sessions = []

        for i in range(0, len(courses)):
            randomSession = courses[i].courseSessions[
                random.randint(0, len(courses[i].courseSessions) - 1)
            ]
            sessions.append(randomSession)
        return sessions

        # this method is not returning any value because the sessions are being added to the student object.

    # TODO: check every course for each condition one by one. add or not add them according to the result.
    # allCourses is all the courses in the semester.
    def selectAndEnrollCourses(self, allCourses, teCourses, nteCourses, fteCourses):
        # select courses from the json file
        selectedCourses = self.selectCourses(
            allCourses, teCourses, nteCourses, fteCourses
        )
        selectedSessions = self.selectSessions(selectedCourses)

        for course, session in zip(selectedCourses, selectedSessions):
            if self.sendToApproval(course, session):
                self.addCourse(course)
                self.addSession(session)

                for i in range(7):
                    for j in range(10):
                        if session.courseHour[i][j]:
                            self.schedule[i][j] = course.courseCode

    def sendToApproval(self, course, session):
        return self.advisor.approveCourse(self, course, session)
