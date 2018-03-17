<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/header">
    <jsp:param value="Product - ${id}" name="title"/>
</jsp:include>

<div class="ms-breadcrumb">
    <a href="javascript:history.back()">< Terug naar overzicht</a>
</div>

<div class="ms-product-details-container">


        <img class="ms-product-details-image" src="/images/products/${product.image}.jpg" alt="Mand">

        <div class="ms-product-details">

            <span class="ms-product-title">${product.name}</span>

            <div class="ms-product-description">
                ${product.description}
            </div>

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

            </div>

        </div>

    <div class="ms-clear"></div>
    </div>

</div>
<jsp:include page="/footer.jsp"></jsp:include>