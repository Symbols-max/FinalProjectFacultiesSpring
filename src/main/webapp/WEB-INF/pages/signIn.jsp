<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/signIn.css"/>">
    <title>SignIn</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>

<div class="container">
    <div class="row">

        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal" action="/j_spring_security_check" method="post">
                <span class="heading">${sessionScope.get("bundle").getString("signIn.text.signIn")}</span>
                <div class="form-group">
                    <input type="email" class="form-control" name="j_login" placeholder="Email" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="j_password" placeholder="Password" required>
                </div>
                <div>
                    <button type="submit" class="btn btn-default">${sessionScope.get("bundle").getString("signIn.btn.signIn")}</button>
                    <br><br>
                    <c:if test="${param.error ne null}">
                        <p>${sessionScope.get("bundle").getString("signIn.message")}</p>
                    </c:if>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>
