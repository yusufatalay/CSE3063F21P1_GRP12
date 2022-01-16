from Tests.test_checkCourseQuota import TestcheckCourseQuota
from Tests.test_checkPreRequisite import TestcheckPreRequisite
from Tests.test_checkCollides import TestcheckCollides
from Tests.test_checkTotalCredits import TestcheckTotalCredits
import unittest



suite = unittest.TestLoader().loadTestsFromTestCase(TestcheckCourseQuota)
unittest.TextTestRunner(verbosity=2).run(suite)


suite = unittest.TestLoader().loadTestsFromTestCase(TestcheckPreRequisite)
unittest.TextTestRunner(verbosity=2).run(suite)

suite = unittest.TestLoader().loadTestsFromTestCase(TestcheckCollides)
unittest.TextTestRunner(verbosity=2).run(suite)


suite = unittest.TestLoader().loadTestsFromTestCase(TestcheckTotalCredits)
unittest.TextTestRunner(verbosity=2).run(suite)