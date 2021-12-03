<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/adminkaEntrant.css"/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <title>Entrants</title>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" style="text-align: left">Университет Киева</a>
        <form class="d-flex" action="/admin/adminka" method="get" style="text-align: right">
            <button class="btn btn-outline-success" type="submit" title="Посмотреть абитуриент">Факультеты</button>
        </form>
    </div>
</nav>
<h1 style="text-align:center">Абитуриенты</h1>
<div class="container">
    <div class="dropdown">
        <button class="dropbtn">Сортировать</button>
        <div class="dropdown-content">
            <a href="/admin/entrants/">Все абитуриенты</a>
            <a href="/admin/entrants/?sort=active">Активные абитуриенты</a>
            <a href="/admin/entrants/?sort=blocked">Заблокированые абитуриенты</a>
        </div>
    </div>
    <br><br>
    <div style="text-align: center">
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">ФИО</th>
            <th scope="col">Email</th>
            <th scope="col">Статус</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${entrants}" var="entrant">
            <tr>
                <td>${entrant.getId()}</td>
                <td>${entrant.getEmail()}</td>
                <td>${entrant.getStatus()}</td>
                <td><a href="/admin/entrants/infoAboutEntrant?email=${entrant.getEmail()}">Узнать больше</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>
    <br><br>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${allPages ne null}">
                <c:forEach var="i" begin="1" end="${allPages}">
                    <li class="page-item"><a class="page-link" href="/admin/entrants/?sort=${sessionScope.get("sort")}&page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </c:if>
        </ul>
    </nav>
    <p style="text-align: center">${message}</p>
    ${pageScope.remove("message")}
    <br>
    <form action="/logout" method="post" style="text-align: center">
        <button class="btn btn-outline-success" type="submit">Выйти</button>
    </form>
</div>
<br><br><br>
</body>
</html>
