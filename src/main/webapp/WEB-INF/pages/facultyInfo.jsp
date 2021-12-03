<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <title>FacultyInfo</title>
</head>
<body>
<div style="padding: 3%;text-align: center">
    <h1>№${faculty.getId()},  ${faculty.getNameFaculty()}</h1>
    <br>
    <div style="text-align: center; word-wrap: break-word;width: 70%;margin-left: 15%"><h4>${faculty.getDescription()}</h4></div>
    <br>
    <h5>${sessionScope.get("bundle").getString("facultyInfo.text.numberAllPlaces")}: ${faculty.getAllPlaces()}</h5>
    <br>
    <h5>${sessionScope.get("bundle").getString("facultyInfo.text.numberFundedPlaces")}: ${faculty.getFundedPlaces()}</h5>
    <br>
    <h5>${sessionScope.get("bundle").getString("facultyInfo.text.numberSubmittedApplies")}: ${numberApplies}</h5>
    <br>
    <table class="table table-striped" style="text-align:center">
        <thead>
        <tr>
            <th scope="col">${sessionScope.get("bundle").getString("facultyInfo.table.subject")}</th>
            <th scope="col">${sessionScope.get("bundle").getString("facultyInfo.table.grade")}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${subjects}" var="subject">
            <tr>
                <td>${subject.getNameSubject()}</td>
                <td>${subject.getGrade()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <form action="/apply" method="post">
        <button class="btn btn-outline-primary" type="submit" name="id_faculty" value="${faculty.getId()}">
            ${sessionScope.get("bundle").getString("facultyInfo.btn.apply")}
        </button>
    </form>
</div>
</body>
</html>
