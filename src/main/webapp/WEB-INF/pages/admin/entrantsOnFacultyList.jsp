<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib uri="/WEB-INF/increment-tag.tld" prefix="inc" %>--%>
<!doctype html>
<html lang="ru">
<head>
    <!-- Обязательные метатеги -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <title>ListOfEntrant</title>
</head>
<body>
<br><br>
<h3 style="text-align: center">${faculty.getId()}, ${faculty.getNameFaculty()}</h3>
<p style="text-align: center">Общее количество мест: ${faculty.getAllPlaces()}; Количество бюджетных мест: ${faculty.getFundedPlaces()}</p>
<br><br>
<div>
    <%int i=0;%>
    <table class="table table-striped" style="text-align:center">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">ФИО</th>
            <th scope="col">Email</th>
            <th scope="col">Средний бал</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${entrants}" var="entrant">
            <tr>
                <td><%=++i%></td>
                <td>${entrant.getSurname()} ${entrant.getName()} ${entrant.getMiddleName()}</td>
                <td>${entrant.getEmail()}</td>
                <td>${entrant.getAvgGrade()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br><br>
    <form action="/admin/sendToEntrant" method="post" style="text-align: center">
        <button class="btn btn-outline-primary" type="submit" name="entrants" value="${entrants}">Сделать рассылку студентам</button>
    </form>
</div>
</body>
</html>
