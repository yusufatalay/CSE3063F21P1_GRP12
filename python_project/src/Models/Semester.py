class Semester:
    def __init__(self, semesterNo):
        self.semesterNo = semesterNo

        if semesterNo % 2 == 1:
            self.semesterName = "FALL"
        elif semesterNo % 2 == 0:
            self.semesterName = "SPRING"
