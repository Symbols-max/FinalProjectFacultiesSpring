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
    <title>ProfileEntrant</title>
</head>
<body>
<div style="padding: 3%">
    <h1 style="text-align: center">${sessionScope.get("bundle").getString("profile.text.profile")}</h1>
    <br>
    <p>${sessionScope.get("bundle").getString("profile.text.fullName")}: ${info.getSurname()} ${info.getName()} ${info.getMiddleName()}</p>
        <br>
    <p>Email: ${user.getEmail()}</p>
    <br>
    <p>${sessionScope.get("bundle").getString("profile.text.city")}: ${info.getCity()}</p>
    <br>
    <p>${sessionScope.get("bundle").getString("profile.text.region")}: ${info.getRegion()}</p>
    <br>
    <p>${sessionScope.get("bundle").getString("profile.text.placeOfEducation")}: ${info.getPlaceEducation()}</p>
    <br>
        <a href="${pageContext.request.contextPath}/file">Download file.pdf</a>
    <br><br><br>
    <form action="/entrant/changeInfoEntrant">
        <button class="btn btn-outline-primary" type="submit">${sessionScope.get("bundle").getString("profile.btn.change")}</button>
    </form>
    <br>
    <div>
    <table class="table table-striped" style="text-align:center">
        <thead>
        <tr>
            <th scope="col">${sessionScope.get("bundle").getString("profile.table.subject")}</th>
            <th scope="col">${sessionScope.get("bundle").getString("profile.table.grade")}</th>
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
    </div>
    <br>
    <form action="/entrant/changeSubjectEntrant" method="get">
        <button class="btn btn-outline-primary" type="submit">${sessionScope.get("bundle").getString("profile.btn.change")}</button>
    </form>
    <br><br>

    <p>${sessionScope.get("bundle").getString("profile.text.submittedApplies")}:</p>
    <div>
    <table>
        <c:forEach items="${id_faculties}" var="id">
            <tr style="margin-bottom: 5%">
                <td>${id}</td>
                <td>
                    <form action="/entrant/deleteEntrantFaculty?id=${id}" method="post" style="padding-left: 10%">
                        <button class="btn btn-outline-primary" type="submit" name="btnDelete" value="${id}">${sessionScope.get("bundle").getString("profile.btn.delete")}</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
    <p style="text-align: center">${message}</p>
    <br>
    <div style="display: inline-flex; margin-left: 40%">
        <a href="/entrant/homePage" style="padding-right: 5%">
            <button class="btn btn-outline-primary" type="submit">${sessionScope.get("bundle").getString("profile.btn.faculties")}</button>
        </a>
        <form action="/logout" method="post">
                    <button class="btn btn-outline-success" type="submit">${sessionScope.get("bundle").getString("profile.btn.exit")}</button>
        </form>
    </div>
</div>
</body>
</html>
