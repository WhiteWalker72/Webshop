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
}