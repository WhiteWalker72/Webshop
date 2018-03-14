<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Webshop - ${title}</title>

    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/style/style.css">
    <script src="/js/main.js"></script>

</head>

<body>
<div class="ms-header">
    <ul>
        <li class="left"><a href="/" class="ms-logo"></a></li>
        <li class="ms-menu-categories"><a href="#">Assortiment</a></li>
        <li class="right"><a href="#">Mand</a></li>
        <li class="right"><a href="#">Account</a></li>
    </ul>
</div>

<div class="ms-categories-container">
    <div class="ms-categories">
        <ul>
            <c:forEach var="category" items="${categories}">
                <li><a href="#">${category.name}</a></li>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="ms-content">
