import json, os, shutil


def write_json(data, filename):
    with open(f"python_project/Students/{filename}", "w") as f:
        json.dump(data, f, indent=2)


def createDirectories():
    path = os.path.join(os.path.abspath("./python_project"), "Students")

    if os.path.exists(path):
        shutil.rmtree(path)

    os.mkdir(path)
    os.mkdir(os.path.join(os.path.abspath("./python_project/Students"), "Freshman"))
    os.mkdir(os.path.join(os.path.abspath("./python_project/Students"), "Sophomore"))
    os.mkdir(os.path.join(os.path.abspath("./python_project/Students"), "Junior"))
    os.mkdir(os.path.join(os.path.abspath("./python_project/Students"), "Senior"))


def scheduleFormat(student):
    schedule = []
    emptyStr = ""
    dash = "-"
    days = [
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday",
    ]
    hours = [
        "8:30",
        "9:30",
        "10:30",
        "11:30",
        "13:00",
        "14:00",
        "15:00",
        "16:00",
        "17:00",
        "18:00",
    ]

    schedule.append(
        f"|{emptyStr:>10}|{days[0]:>10}|{days[1]:>10}|{days[2]:>10}|{days[3]:>10}|{days[4]:>10}|{days[5]:>10}|{days[6]:>10}|"
    )

    for i in range(10):
        row = f"|{hours[i]:>10}|"
        for j in range(7):
            if student.schedule[j][i] is None:
                row += f"{emptyStr:>10}|"
            else:
                row += f"{student.schedule[j][i].__str__():>10}|"
        schedule.append(
            f"|{dash*10}|{dash*10}|{dash*10}|{dash*10}|{dash*10}|{dash*10}|{dash*10}|{dash*10}|"
        )
        schedule.append(row)

    return schedule
