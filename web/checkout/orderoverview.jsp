<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/header">
    <jsp:param value="Categorie - ${id}" name="title"/>
</jsp:include>

<h1>Besteloverzicht</h1>

<table class="ms-checkout-products-list">
    <c:forEach var="product" items="${cart}">
        <tr>
            <td>${product.key.name}</td>
            <td>${product.value}</td>
            <td>&euro; ${product.key.price}</td>
        </tr>
    </c:forEach>
</table>

<div class="ms-total-price">Totaal: &euro; <span></span></div>

<a href="/checkout/success.jsp" class="ms-button">Bestelling plaatsen</a>

<jsp:include page="/footer.jsp"></jsp:include>