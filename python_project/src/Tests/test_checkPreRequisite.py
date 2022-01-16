# fmt: off
import sys
import unittest
sys.path.append("..")
from Models.CourseSession import *
from Models.Student import *
from Models.Transcript import *
from Models.Course import *
from Models.CourseCode import *
from Models.Advisor import *
# fmt: on


class TestcheckPreRequisite(unittest.TestCase):
    # create a student who has compeleted the pre-requisites of given course exepected to be True
    def test_pass(self):
        prereqcourse = Course("TEST", CourseCode(
            "TSE1011"), 3, 3, 1, "Lecture", [], [])
        course = Course("TEST COURSE", CourseCode("CSE3055"), 3,
                        3, 1, "Lecture", [CourseCode("TSE1011")], [])
        adv = Advisor("MCG")
        student = Student("John Doe", "123456789", 3, adv,
                          Transcript(), takenCourses=[prereqcourse])

        self.assertTrue(adv.checkPreRequisite(course, student))
    # create a student who has not compeleted the pre-requisites of given course exepected to be False

    def test_fail(self):
        prereqcourse = Course("TEST", CourseCode(
            "TSE1011"), 3, 3, 1, "Lecture", [], [])
        course = Course("TEST COURSE", CourseCode("CSE3055"), 3,
                        3, 1, "Lecture", [CourseCode("TSE1011")], [])
        adv = Advisor("MCG")
        trs = Transcript()
        trs.addFailedCourse(prereqcourse)
        student = Student("John Doe", "123456789", 3, adv, trs)

        self.assertFalse(adv.checkPreRequisite(course, student))
