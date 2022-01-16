# fmt: off
import sys
import unittest
sys.path.append("../")
from Models.CourseSession import *
from Models.Student import *
from Models.Transcript import *
from Models.Course import *
from Models.CourseCode import *
from Models.Advisor import *
# fmt: on


class TestcheckCourseQuota(unittest.TestCase):

    def test_correct(self):

        student = Student("John Doe", "123456789", 3,
                          Advisor("MCG"), Transcript())
        course = Course("TEST", CourseCode("CSE3055"),
                        3, 3, 1, "Lecture", [], [])

        # create a session with less students than the quota
        session = CourseSession({"quota": "10", "sessionHours": [
                                {"day": "1", "hour": "1"}, {"day": "2", "hour": "2"}]})
        # add 5 students
        for i in range(5):
            session.incrementNumberOfStudents()
        # check if checkCourseQuota returns True
        self.assertTrue(Advisor.checkCourseQuota(
            session, course, session, student))

    def test_wrong(self):
        student = Student("John Doe", "123456789", 3,
                          Advisor("MCG"), Transcript())
        course = Course("TEST", CourseCode("CSE3055"),
                        3, 3, 1, "Lecture", [], [])

        # create a session with less students than the quota
        session = CourseSession({"quota": "10", "sessionHours": [
                                {"day": "1", "hour": "1"}, {"day": "2", "hour": "2"}]})

        # create a session with more students than the quota
        session = CourseSession({"quota": "10", "sessionHours": [
                                {"day": "1", "hour": "1"}, {"day": "2", "hour": "2"}]})
        # add 11 students
        for i in range(11):
            session.incrementNumberOfStudents()
        # check if checkCourseQuota returns False
        self.assertFalse(Advisor.checkCourseQuota(
            session, course, session, student))
