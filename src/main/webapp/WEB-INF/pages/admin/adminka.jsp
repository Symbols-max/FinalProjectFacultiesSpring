<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/adminka.css"/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <title>Admin</title>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" style="text-align: left">Университет Киева</a>
        <form class="d-flex" action="/admin/entrants/" method="get" style="text-align: right">
            <button class="btn btn-outline-success" type="submit" title="Посмотреть абитуриент">Абитуренты</button>
        </form>
    </div>
</nav>
<h1 style="text-align:center">Факультеты</h1>
<div class="container">
    <div class="dropdown">
        <button class="dropbtn">Сортировать</button>
        <div class="dropdown-content">
            <a href="/admin/adminka">По названию</a>
            <a href="/admin/adminka?sort=byAllPlaces">По количеству общих мест</a>
            <a href="/admin/adminka?sort=byFundedPlaces">По количеству бюджетных мест</a>
        </div>
    </div>
    <br><br>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">№</th>
            <th scope="col">Название</th>
            <th scope="col">Количество мест</th>
            <th scope="col">Количество бюджетных мест</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${faculties}" var="faculty">
            <tr>
                <td>${faculty.getId()}</td>
                <td>${faculty.getNameFaculty()}</td>
                <td>${faculty.getAllPlaces()}</td>
                <td>${faculty.getFundedPlaces()}</td>
                <td><a href="/admin/facultyInfo?id=${faculty.getId()}">Узнать больше</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <form action="/admin/addNewFaculty" method="get" style="text-align: center">
        <button class="btn btn-outline-success" type="submit">Добавить факультет</button>
    </form>
    <br><br>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${allPages ne null}">
                <c:forEach var="i" begin="1" end="${allPages}">
                    <li class="page-item"><a class="page-link" href="/admin/adminka?sort=${sessionScope.get("sort")}&page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </c:if>
        </ul>
    </nav>
    <br>
    <p style="text-align: center">${message}</p>
    ${pageScope.remove("message")}
    <form action="/logout" method="post" style="text-align: center">
        <button class="btn btn-outline-success" type="submit">Выйти</button>
    </form>
</div>
<br><br><br>
</body>
</html>
