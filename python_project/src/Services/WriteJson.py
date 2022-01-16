# fmt: off
from cmath import log
import imp
import json
import logging
import os
import shutil
import logging
import sys
sys.path.append("..")
from Models.Advisor import Advisor
#fmt: on


def write_json(data, filename):
    with open(f"python_project/Students/{filename}", "w") as f:
        json.dump(data, f, indent=2)


def createDirectories():
    path = os.path.join(os.path.abspath("./python_project"), "Students")

    if os.path.exists(path):
        shutil.rmtree(path)

    os.mkdir(path)
    os.mkdir(os.path.join(os.path.abspath(
        "./python_project/Students"), "Freshman"))
    os.mkdir(os.path.join(os.path.abspath(
        "./python_project/Students"), "Sophomore"))
    os.mkdir(os.path.join(os.path.abspath(
        "./python_project/Students"), "Junior"))
    os.mkdir(os.path.join(os.path.abspath(
        "./python_project/Students"), "Senior"))


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


def addStatisticsToLogFile():
    filePath = "python_project/src/simulation.log"

    with open(filePath, "a") as f:
        f.write("\nSTATISTICS\n")

    logging.basicConfig(filename=filePath, level=logging.INFO)
    logging.info(
        f"In the simulation {Advisor.courseQuotaProblems} course quota problems were encountered.")
    logging.info(
        f"In the simulation {Advisor.preRequisiteProblems} pre-requisite problems were encountered.")
    logging.info(
        f"In the simulation {Advisor.collisionProblems} collision problems were encountered.")
    logging.info(
        f"In the simulation {Advisor.creditProblems} credit problems were encountered.")
    logging.info(
        f"In the simulation {Advisor.teLimitationProblems} TE limitation problems were encountered.")
    logging.info(
        f"In the simulation {Advisor.fteLimitataionProblems} FTE limitation problems were encountered.")
