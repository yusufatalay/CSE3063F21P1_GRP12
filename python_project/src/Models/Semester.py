class Semester:
    def __init__(self, semesterNo):
        self.semesterNo = semesterNo

        if semesterNo % 2 == 1:
            self.semesterName = "FALL"
        elif semesterNo % 2 == 0:
            self.semesterName = "SPRING"

    def __eq__(self, other):
        return self.semesterNo == other.semesterNo and self.semesterName == other.semesterName
