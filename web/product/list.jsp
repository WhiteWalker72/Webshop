<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<div class="ms-products">

    <c:forEach var="product" items="${products}">
        <div class="ms-product">

            <a class="ms-product-info-link" href="/Product/${product.getId()}">
                <img class="ms-product-thumbnail" src="/images/products/${product.getImageName()}.jpg" alt="Mand">

                <span class="ms-product-title">${product.getName()}</span>

                <div class="ms-product-description">
                        ${product.getDescription()}
                </div>
            </a>
            <div class="ms-product-actions">
                <div class="ms-product-info">
                    <c:choose>
                        <c:when test="${empty product.getActiveOffer().getOfferPrice()}">
                            <span><fmt:formatNumber currencySymbol="&euro; " value="${product.getPrice()}" type="currency"/></span>
                        </c:when>
                        <c:otherwise>
                        <span class="ms-old-price">
                            <fmt:formatNumber currencySymbol="&euro; " value="${product.getPrice()}" type="currency"/>
                        </span>
                            <span><fmt:formatNumber currencySymbol="&euro; " value="${product.getActiveOffer().getOfferPrice()}" type="currency"/></span>
                        </c:otherwise>
                    </c:choose>
                    <span>
                    <c:choose>
                        <c:when test="${product.getAmountStored() > 0}">
                            Op voorraad
                        </c:when>
                        <c:otherwise>
                            Uitverkocht
                        </c:otherwise>
                    </c:choose>
                    </span>

                </div>

                <div class="ms-product-cart" data-id="${product.getId()}">
                    <input type="text" class="ms-product-cart-amount" value="1">
                    <span class="ms-button ms-product-cart-button">+ Mand</span>
                </div>

                <div class="ms-clear"></div>

            </div>

        </div>
    </c:forEach>

    <div class="ms-clear"></div>
</div>