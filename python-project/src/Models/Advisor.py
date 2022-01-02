class Advisor:

    def __init__(self, name, studentList=None):
        self.name = name
        if studentList is None:
            self.students = []
        else:
            self.studentList = studentList

    def addStudent(self, stu):
        if stu not in self.studentList:
            self.studentList.append(stu)

    def removeStudent(self, stu):
        if stu in self.studentList:
            self.studentList.remove(stu)

    def printStudents(self):
        for stu in self.studentList:
            print("-->", stu.name)
