<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/changeEntrantSubject.css"/>">
    <title>EntrantSubject</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>

<div class="container">
    <div class="row">

        <div class="col-md-offset-3 col-md-6" style="padding-top: 5%">
            <form class="form-horizontal" action="/entrant/changeSubjectEntrant" method="post">
                <div>
                    <table class="table table-striped" style="text-align:center">
                        <thead>
                        <tr style="text-align: center">
                            <th scope="col">${sessionScope.get("bundle").getString("changeEntrantSubject.table.subject")}</th>
                            <th scope="col">${sessionScope.get("bundle").getString("changeEntrantSubject.table.grade")}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${subjects}" var="subject">
                            <tr>
                                <td>${subject.value()}: </td>
                                <td><input min="100" max="200" type="number"  class="form-control" name="${subject.toString()}"></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <br>
                <div>
                    <button type="submit" class="btn btn-default">${sessionScope.get("bundle").getString("changeEntrantSubject.btn.submit")}</button>
                    <p></p>
                    <p>${message}</p>
                </div>
            </form>
        </div>

    </div><!-- /.row -->
</div><!-- /.container -->
</body>
</html>