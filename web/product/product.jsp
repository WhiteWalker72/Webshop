<jsp:include page="/header">
    <jsp:param value="Product - ${id}" name="title"/>
</jsp:include>

<div class="ms-breadcrumb">
    <a href="#">Products</a> > <a href="#">Mandje van Tichelaar</a>
</div>

<div class="ms-product-details-container">


        <img class="ms-product-details-image" src="/images/products/mand.jpg" alt="Mand">

        <div class="ms-product-details">

            <span class="ms-product-title">Mandje van Tichelaar</span>

            <div class="ms-product-description">
                Dit unieke mandje van Tichelaar, mandje tichelaar, MAND, is een one of a kind kunstobject.
            </div>

            <div class="ms-product-actions">
                <div class="ms-product-info">
                    <span>&euro; 2199,99</span>
                    <span>Op voorraad</span>
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