<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/index.css"/>">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <title>Main</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" style="text-align: left">Университет Киева</a>
        <form class="d-flex" action="/authorization" method="get" style="text-align: right">
            <button class="btn btn-outline-primary" type="submit" name="btn" value="Sign in">${sessionScope.get("bundle").getString("index.btn.signIn")}</button>
            <button class="btn btn-outline-success" type="submit" name="btn" value="Sign Up">${sessionScope.get("bundle").getString("index.btn.signUp")}</button>
        </form>
    </div>
</nav>
<h1 style="text-align:center">${sessionScope.get("bundle").getString("index.text.faculties")}</h1>
<div class="container">
    <div class="dropdown">
        <button class="dropbtn">${sessionScope.get("bundle").getString("index.btn.sort")}</button>
        <div class="dropdown-content">
            <a href="/">${sessionScope.get("bundle").getString("index.btn.sort.sortByName")}</a>
            <a href="/?sort=byAllPlaces">${sessionScope.get("bundle").getString("index.btn.sort.sortByAllPlaces")}</a>
            <a href="/?sort=byFundedPlaces">${sessionScope.get("bundle").getString("index.btn.sort.sortByFundedPlaces")}</a>
        </div>
    </div>
    <br><br>
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">№</th>
            <th scope="col">${sessionScope.get("bundle").getString("index.table.nav.name")}</th>
            <th scope="col">${sessionScope.get("bundle").getString("index.table.nav.numberPlaces")}</th>
            <th scope="col">${sessionScope.get("bundle").getString("index.table.nav.numberFundedPlaces")}</th>
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
            <td><a href="/facultyInfo?id=${faculty.getId()}">${sessionScope.get("bundle").getString("index.table.href.moreInfo")}</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>

    <br><br>

    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${allPages ne null}">
                <c:forEach var="i" begin="1" end="${allPages}">
                    <li class="page-item"><a class="page-link" href="/?sort=${sessionScope.get("sort")}&page=<c:out value="${i - 1}"/>"><c:out value="${i}"/></a></li>
                </c:forEach>
            </c:if>
        </ul>
    </nav>


    <c:if test="${param.success ne null}">
        <p>${sessionScope.get("bundle").getString("index.message")}</p>
    </c:if>

</div>
<footer>
    <div style="text-align: right;margin-right: 3%">
        <a href="/changeLanguage?language=ru" style="padding-right: 2%;text-decoration: none">РУС</a>
        <a href="/changeLanguage?language=en" style="padding-right: 2%;text-decoration: none">ENG</a>
    </div>
</footer>
</body>
</html>
