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


class TestcheckTotalCredits(unittest.TestCase):
    adv = Advisor("YTA")
    # creat a student who has enough credits for take the given course expected result is True

    def test_pass(self):
        trs = Transcript(totalCredits=30)
        course = Course("TEST COURSE", CourseCode("CSE3055"), 3,
                        3, 1, "Lecture", [CourseCode("TSE1011")], [])
        student = Student("John Doe", "123456789", 3, self.adv, trs)

        self.assertTrue(self.adv.checkTotalCredits(course, student))

    # creat a student who don't have enough credits for take the given course expected result is False
    def test_fail(self):
        trs = Transcript(totalCredits=30)
        course = Course("TEST COURSE", CourseCode("CSE3055"), 3,
                        300, 1, "Lecture", [CourseCode("TSE1011")], [])
        student = Student("John Doe", "123456789", 3, self.adv, trs)

        self.assertFalse(self.adv.checkTotalCredits(course, student))
