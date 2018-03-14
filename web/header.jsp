<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Webshop - ${title}</title>

    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/style/style.css">

    <script src="/js/rest.js"></script>
    <script src="/js/main.js"></script>
    <script src="/js/cart.js"></script>

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
            <li><a href="#">Rieten manden</a></li>
            <li><a href="#">Stalen manden</a></li>
            <li><a href="#">Houten manden</a></li>
            <li><a href="#">Nieuw</a></li>
        </ul>
    </div>
</div>

<div class="ms-cart-container">

    <div class="ms-cart">
        <h2>Winkelmandje</h2>

        <table class="ms-cart-list">
            <tr>
                <td>Mand</td>
                <td><input type="text" class="ms-cart-amount" value="1"></td>
                <td>&euro; 21,99</td>
                <td class="ms-cart-delete">X</td>
            </tr>
        </table>

        <span class="ms-button">
            Afrekenen
        </span>
    </div>
</div>

<div class="ms-content">