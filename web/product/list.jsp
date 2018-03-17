<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="ms-products">

    <c:forEach var="product" items="${products}">
    <div class="ms-product">

        <a class="ms-product-info-link" href="/Product/${product.id}">
            <img class="ms-product-thumbnail" src="/images/products/${product.image}.jpg" alt="Mand">

            <span class="ms-product-title">${product.name}</span>

            <div class="ms-product-description">
                    ${product.description}
            </div>
        </a>
        <div class="ms-product-actions">
            <div class="ms-product-info">
                <span>&euro; ${product.price}</span>

                <span>
                <c:choose>
                    <c:when test="${product.amountStored > 0}">
                        Op voorraad
                    </c:when>
                    <c:otherwise>
                        Uitverkocht
                    </c:otherwise>
                </c:choose>
                </span>


            </div>

            <div class="ms-product-cart" data-id="1">
                <input type="text" class="ms-product-cart-amount" value="1">
                <span class="ms-button ms-product-cart-button">+ Mand</span>
            </div>

            <div class="ms-clear"></div>

        </div>

    </div>
    </c:forEach>

    <div class="ms-clear"></div>
</div>