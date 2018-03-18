<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Webshop - ${title}</title>

    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/style/style.css">

    <script src="/js/utils/rest.js"></script>
    <script src="/js/controller/CartController.js"></script>
    <script src="/js/controller/CategoryController.js"></script>
    <script src="/js/main.js"></script>
</head>

<body>
<div class="ms-header">
    <ul>
        <li class="left"><a href="/" class="ms-logo"></a></li>
        <li class="ms-menu-categories"><a href="#">Assortiment</a></li>
        <li class="right ms-menu-cart"><a href="#">Mand</a></li>
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

<div class="ms-cart-container">

    <div class="ms-cart">
        <h2>Winkelmandje</h2>

        <table class="ms-cart-list">
            <c:forEach var="product" items="${cart}">
            <tr data-id="${product.key.id}">
                <td>${product.key.name}</td>
                <td><input type="text" class="ms-cart-amount" value="${product.value}"></td>
                <td>&euro; <span class="ms-cart-price">${product.key.price}</span></td>
                <td class="ms-cart-delete">X</td>
            </tr>
            </c:forEach>
        </table>

        <div class="ms-total-price">Totaal: &euro; <span></span></div>

        <span class="ms-button ms-checkout-button">
            Afrekenen
        </span>
    </div>
</div>

<div class="ms-content">
