<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/registration.css"/>">
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>


<div class="container">
    <div class="row">

        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal" action="/registration" method="post" enctype="multipart/form-data">
                <span class="heading">${sessionScope.get("bundle").getString("signUp.text.signUp")}</span>
                <div class="form-group">
                    <input type="email" class="form-control" name="email" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="password" placeholder="${sessionScope.get("bundle").getString("signUp.input.password")}" required>
                </div>
                <br><br>
                <div class="form-group">
                    <input type="text" class="form-control" name="name" placeholder="${sessionScope.get("bundle").getString("signUp.input.name")}" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="surname" placeholder="${sessionScope.get("bundle").getString("signUp.input.surname")}" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="middleName" placeholder="${sessionScope.get("bundle").getString("signUp.input.middleName")}" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="city" placeholder="${sessionScope.get("bundle").getString("signUp.input.city")}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="region" placeholder="${sessionScope.get("bundle").getString("signUp.input.region")}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="placeEducation" placeholder="${sessionScope.get("bundle").getString("signUp.input.placeEducation")}" required>
                </div>
                <div class="form-group">
                    <div style="display: -webkit-inline-flex">${sessionScope.get("bundle").getString("signUp.input.certificate")}:  <input type="file"  name="diplom">
                    </div>
                </div>
                <div>
                    <button type="submit" class="btn btn-default">${sessionScope.get("bundle").getString("signUp.btn.signUp")}</button>
                    <br><br>
                        <p>${message}</p>
                </div>
            </form>
        </div>

    </div><!-- /.row -->
</div><!-- /.container -->
</body>
</html>