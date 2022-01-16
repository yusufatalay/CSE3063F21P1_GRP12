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


class TestcheckCollides(unittest.TestCase):
    adv = Advisor("MCG")

    # create 2 courses which does not collide with each other and make the studet take them, expected result is True
    def test_pass(self):
        coursesession1 = CourseSession({"quota": 70, "sessionHours": [
                                       {"day": 1, "hour": 2}, {"day": 1, "hour": 1}, {"day": 1, "hour": 4}]})
        course1 = Course("TEST", CourseCode("TSE1011"), 3, 3,
                         1, "Lecture", [], [coursesession1])
        coursesession2 = CourseSession(
            {"quota": 70, "sessionHours": [{"day": 2, "hour": 3}, {"day": 1, "hour": 4}]})
        course2 = Course("TEST", CourseCode("TSE1012"), 3, 3,
                         1, "Lecture", [], [coursesession2])
        student = Student("John Doe", "123456789", 3, self.adv, Transcript())
        student.addCourse(course1)
        student.addSession(coursesession1)
        self.assertTrue(self.adv.checkCollides(
            course2, coursesession2, student))

    # create 2 courses which collide with each other and make the studet take them, expected result is False
    def test_fail(self):
        coursesession1 = CourseSession({"quota": 70, "sessionHours": [
                                       {"day": 1, "hour": 2}, {"day": 1, "hour": 3}, {"day": 1, "hour": 4}]})
        course1 = Course("TEST", CourseCode("TSE1011"), 3, 3,
                         1, "Lecture", [], [coursesession1])
        course2 = Course("TEST", CourseCode("TSE1012"), 3, 3,
                         1, "Lecture", [], [coursesession1])
        student = Student("John Doe", "123456789", 3, self.adv, Transcript())
        student.addCourse(course1)
        student.addSession(coursesession1)
        self.assertFalse(self.adv.checkCollides(
            course2, coursesession1, student))
