class CartController {

    constructor() {

        document.querySelector(".ms-product-cart-button").addEventListener("click", (event) => {

            this.addToCart(event)
        });

        var amountField = document.querySelector(".ms-cart-amount");

        amountField && amountField.addEventListener("change", (event) => {

            this.editCart(event)
        });

        var deleteButton = document.querySelector(".ms-cart-delete");

        deleteButton && deleteButton.addEventListener("click", (event) => {

            this.removeFromCart(event)
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

    addToCart(event) {

        var result = request('/api/cart', 'POST', {
            "id": event.target.parentElement.dataset.id,
            "amount": event.target.parentElement.querySelector('.ms-product-cart-amount').value
        });

        if(result) {

            alert('Product toegevoegd aan mand');
        }
    }

    editCart(event) {

        var result = request('/api/cart', 'PUT', {
            "id": event.target.parentElement.parentElement.dataset.id,
            "amount": event.target.value
        });

        if(result) {

            alert('Product toegevoegd aan mand');
        }
    }

    removeFromCart(event) {

        var result = request('/api/cart/' + event.target.parentElement.dataset.id, 'DELETE');
    }
}