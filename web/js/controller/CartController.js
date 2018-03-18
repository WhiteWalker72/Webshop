class CartController {

    constructor() {
        this.calculateTotal();

        var cartButton = document.querySelectorAll(".ms-product-cart-button");

        var _self = this;

        cartButton.forEach(function(button) {

            button.addEventListener("click", (event) => {

                _self.addToCart(event);
            });

        });

        var amountField = document.querySelectorAll(".ms-cart-amount");

        amountField.forEach(function(button) {

            button.addEventListener("change", (event) => {

                _self.editCart(event);
            });

        });

        var deleteButton = document.querySelectorAll(".ms-cart-delete");

        deleteButton.forEach(function(button) {

            button.addEventListener("click", (event) => {

                _self.removeFromCart(event);
            });

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

        var amount = event.target.parentElement.querySelector('.ms-product-cart-amount').value;

        request('/api/cart', 'POST', {
            "id": event.target.parentElement.dataset.id,
            "amount": amount
        }, function (data, self) {

            var template = `
            <tr data-id="${data.id}">
                <td>${data.name}</td>
                <td><input type="text" class="ms-cart-amount" value="${amount}"></td>
                <td>&euro; <span class="ms-cart-price">${data.price}</span></td>
                <td class="ms-cart-delete">X</td>
            </tr>`;

            var product = document.querySelector(`.ms-cart-list tr[data-id='${data.id}']`);

            if(product == null)
                document.querySelector(".ms-cart-list tbody").insertAdjacentHTML('beforeend', template);
             else
                 product.querySelector('.ms-cart-amount').value = +product.querySelector('.ms-cart-amount').value + +amount;

        });

        this.calculateTotal();
    }

    editCart(event) {

        var result = request('/api/cart', 'PUT', {
            "id": event.target.parentElement.parentElement.dataset.id,
            "amount": event.target.value
        });

        this.calculateTotal();
    }

    removeFromCart(event) {

        var result = request('/api/cart/' + event.target.parentElement.dataset.id, 'DELETE');

        event.target.parentElement.parentElement.removeChild(event.target.parentElement);

        this.calculateTotal();
    }

    calculateTotal() {

        var products = document.querySelectorAll(".ms-cart-list tr");

        var price = 0;

        products.forEach(function(product) {

            price += +product.querySelector('.ms-cart-price').innerHTML
                        * +product.querySelector('.ms-cart-amount').value;
        });

        document.querySelector('.ms-cart .ms-total-price span').innerHTML = price.toFixed(2);
    }
}