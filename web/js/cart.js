window.onload = function() {

    document.querySelector(".ms-product-cart-button").addEventListener("click", function() {

       var result = request('/cart', 'post', {
            "product": this.parentElement.dataset.id,
            "amount": this.parentElement.querySelector('.ms-product-cart-amount').value
       });

       if(result) {

           alert('Product toegevoegd aan mand');
       }
    });

    document.querySelector(".ms-menu-cart").addEventListener("click", function() {

        var cElement = document.querySelector('.ms-cart-container');

        if (cElement.style.display === "none" || cElement.style.display === "") {
            cElement.style.display = "block";
            event.target.classList.add('ms-menu-active');
        } else {
            cElement.style.display = "none";
            event.target.classList.remove('ms-menu-active');
        }
    });
}