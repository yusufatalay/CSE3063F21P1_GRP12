import random
from Advisor import Advisor
class Student:

    def __init__(self, name, studentID, semester, advisor:Advisor, transcript, takenCourses=None, takenSessions=None, denialMessages=None):
        # Note to the reader, variables defined in the __init__ are belong to the object itself. The ones outside are belong to the class.
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


    # This method selects random courses for the current semester in a given course pool
    def selectCourses(self, allCourses, teCourses, nteCourses, fteCourses):
        courses = []
        for i in range(0, len(allCourses)):
        # If the selected course's courseSemester equals the student's current semester
        # we add the course.
           if (self.semester == allCourses[i].semester):
               courses.append(allCourses[i])
        
        tempTECourses = teCourses.copy()
        if (self.semester == 7):
            rndCourseIndex = random.randint(0, len(teCourses)-1)
            courses.append(teCourses[rndCourseIndex])

            rndCourseIndex = random.randint(0, len(nteCourses)-1)
            courses.append(nteCourses[rndCourseIndex])
        elif(self.semester == 8): # if the student is in the last semester, we choose 3 TEs, 1 NTE and 1 FTE.
            for i in range(0,2):  # this loop will run 3 times
                rndIndex = random.randint(0, len(tempTECourses)-1)                
                courses.append(tempTECourses[rndIndex])
                tempTECourses.remove(tempTECourses[rndIndex])

            rndIndex = random.randint(0, len(nteCourses)-1)
            courses.append(nteCourses[rndIndex])
            rndIndex = random.randint(0, len(fteCourses)-1)
            courses.append(fteCourses[rndIndex])
        elif(self.semester == 2):   # If the student is his/her second semester: we can add single nte course.
            rndIndex = random.randint(0, len(nteCourses)-1)
            courses.append(nteCourses[rndIndex])
 
        # Add the courses that student has failed.
        courses.extend(self.failedCourses)

        return courses    

    def selectSessions(self, courses):
        sessions = []

        for i in range(0, len(courses)):
            randomSession = courses[i].sessions[random.randint(0, len(courses[i].sessions) - 1)]
            sessions.append(randomSession)
        return sessions

        # this method is not returning any value because the sessions are being added to the student object.
    # TODO: check every course for each condition one by one. add or not add them according to the result.
    # allCourses is all the courses in the semester.
    def selectAndEnrollCourses(self, allCourses, teCourses,nteCourses,fteCourses):
        # select courses from the json file
        selectedCourses =   self.selectCourses(allCourses, teCourses, nteCourses, fteCourses)
        selectedSessions = self.selectSessions(selectedCourses)

        # TODO: send Selected courses and sessions to the approval process. ONE BY ONE
        # call sendToApproval here and add the approved courses to the student's taken courses.

    def sendToApproval(self, course, session):
        self.advisor.approveCourse(self, course, session)
        
