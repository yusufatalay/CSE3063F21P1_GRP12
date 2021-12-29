from Person import Person


class Advior(Person):

    def __init__(self, name, students=None):
        super().__init__(name)
        if students is None:
            self.students = []
        else:
            self.students = students

    def addStudent(self, stu):
        if stu not in self.students:
            self.students.append(stu)

    def removeStudent(self, stu):
        if stu in self.students:
            self.students.remove(stu)

    def printStudents(self):
        for stu in self.students:
            print("-->", stu.name)
