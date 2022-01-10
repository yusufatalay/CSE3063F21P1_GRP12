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
