<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/changeInfoEntrant.css"/>">
    <title>InfoEntrant</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>

<div class="container">
    <div class="row">

        <div class="col-md-offset-3 col-md-6" style="padding-top: 5%">
            <form class="form-horizontal" action="/entrant/changeInfoEntrant" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="newName" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.name")}" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="newSurname" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.surname")}" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="newMiddleName" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.middleName")}" required>
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="newCity" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.city")}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="newRegion" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.region")}" required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="newPlaceEducation" placeholder="${sessionScope.get("bundle").getString("changeInfoEntrant.input.placeEducation")}" required>
                </div>
                <div class="form-group">
                    <div style="display: -webkit-inline-flex">${sessionScope.get("bundle").getString("changeInfoEntrant.input.certificate")}:  <input type="file"  name="newDiplom">
                    </div>
                </div>
                <div>
                    <button type="submit" class="btn btn-default">${sessionScope.get("bundle").getString("changeInfoEntrant.btn.submit")}</button>
                    <p></p>
                    <c:if test="${param.error ne null}">
                        <p>${sessionScope.get("bundle").getString("changeInfoEntrant.message")}</p>
                    </c:if>
                </div>
            </form>
        </div>

    </div><!-- /.row -->
</div><!-- /.container -->
</body>
</html>