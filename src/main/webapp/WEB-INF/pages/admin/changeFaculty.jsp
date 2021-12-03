<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/changeFaculty.css"/>">
    <title>ChangeFaculty</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>
<br><br>
<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal" action="/admin/changeFaculty" method="post">
                <div class="form-group">
                    <input type="number" min="1" class="form-control" name="id" placeholder="Номер факультета" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="Назввание" required>
                </div>
                <div class="form-group">
                    <input type="number" min="1" id="allPlaces" class="form-control" name="allPlaces" placeholder="Общее количество мест" required>
                </div>
                <div class="form-group">
                    <input type="number" min="1" class="form-control" name="fundedPlaces" placeholder="Бюджетные места" required>
                </div>
                <div>
                    <textarea name="description" rows="7" cols="50" placeholder="Описание"></textarea>
                </div>
                <br><br>
                <div>
                    <button type="submit" class="btn btn-default">Изменить</button>
                </div>
            </form>
        </div>
        <br>
        <c:if test="${param.error ne null}">
            <p>Ошибка. Факультет не был изменен</p>
        </c:if>
        <p>${message}</p>

    </div><!-- /.row -->
</div><!-- /.container -->

</body>
</html>