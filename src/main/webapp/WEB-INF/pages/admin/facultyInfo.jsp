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
    <title>Faculty Info</title>
</head>
<body>
<div style="padding: 3%;text-align: center">
    <h2>№${faculty.getId()},  ${faculty.getNameFaculty()}</h2>
    <br>
    <h4 style="text-align: center">${faculty.getRecruitment()}</h4>
    <br>
    <form action="/admin/changeRecruitmentStatus" method="post">
        <button class="btn btn-outline-primary" type="submit">Изменить статус подачи заявок на курс</button>
    </form>
    <p style="text-align: center">${sessionScope.get("messageChange")}${sessionScope.remove("messageChange")}</p>
    <br>
    <div style="text-align: center; word-wrap: break-word;width: 70%;margin-left: 15%">
        <h4>${faculty.getDescription()}</h4>
    </div>
    <br>
    <h5>Общее количество мест: ${faculty.getAllPlaces()}</h5>
    <br>
    <h5>Количество бюджетных мест: ${faculty.getFundedPlaces()}</h5>
    <br>
    <form action="/admin/changeFaculty">
        <button class="btn btn-outline-primary" type="submit">Изменить</button>
    </form>
    <p>${message}</p>
    <br>
    <h5>Количество поданых заявок: ${numberEntrant}</h5>
    <br><br>
    <table class="table table-striped" style="text-align:center">
        <thead>
        <tr>
            <th scope="col">Предмет</th>
            <th scope="col">Проходной бал</th>
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
    <form action="/admin/changeSubjectFaculty" method="get">
        <button class="btn btn-outline-primary" type="submit">Изменить</button>
    </form>
    <br><br>
    <form action="/admin/entrantsOnFacultyList" method="get">
        <button class="btn btn-outline-primary" type="submit" name="btnList" value="${faculty.getId()}">Получить список заявок</button>
    </form>
    <br><br>
    <form action="/admin/deleteFaculty" method="post">
        <button class="btn btn-outline-primary" type="submit">Удалить факультет</button>
    </form>
    <br><br>
    <form action="/admin/adminka" method="get">
        <button class="btn btn-outline-primary" type="submit">Вернуться к факультетам</button>
    </form>
</div>
</body>
</html>
