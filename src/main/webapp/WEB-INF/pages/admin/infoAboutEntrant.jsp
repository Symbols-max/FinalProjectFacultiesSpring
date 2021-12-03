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
    <title>Entrant Info</title>
</head>
<body>
<div style="padding: 3%">
    <br>
    <p>ФИО: ${info.getSurname()} ${info.getName()} ${info.getMiddleName()}</p>
    <br>
    <p>Статус: ${entrant.getStatus()}</p>
    <form action="/admin/entrants/changeUserStatus" method="post">
        <button class="btn btn-outline-primary" type="submit" name="email" value="${entrant.getEmail()}">Изменить статус</button>
    </form>
    <br>
    <p>Поданые заявки: <c:forEach items="${id_faculties}" var="id">${id}  </c:forEach></p>
    <br>
    <p>Email: ${entrant.getEmail()}</p>
    <br>
    <p>Город: ${info.getCity()}</p>
    <br>
    <p>Регион: ${info.getRegion()}</p>
    <br>
    <p>Место учебы: ${info.getPlaceEducation()}</p>
    <br>
        <a href="/file">Download file.pdf</a>
    <br><br>
    <br>
    <div>
        <table class="table table-striped" style="text-align:center">
            <thead>
            <tr>
                <th scope="col">Предмет</th>
                <th scope="col">Бал</th>
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
    <form action="/admin/entrants/deleteEntrant" method="post" style="text-align: center">
        <button class="btn btn-outline-primary" type="submit" name="email" value="${entrant.getEmail()}">Удалить пользователя</button>
    </form>
    <br><br>
    <form action="/admin/entrants/" method="get" style="text-align: center">
        <button class="btn btn-outline-primary" type="submit">Вернуться к абитуриентам</button>
    </form>
    <br><br>
</div>
</body>
</html>
