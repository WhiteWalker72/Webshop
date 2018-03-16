class CategoryController {

    constructor() {

        document.querySelector(".ms-menu-categories").addEventListener("click", function (event) {

                var cElement = document.querySelector('.ms-categories-container');

                if (cElement.style.display === "none" || cElement.style.display === "") {
                    cElement.style.display = "block";
                    event.target.classList.add('ms-menu-active');
                } else {
                    cElement.style.display = "none";
                    event.target.classList.remove('ms-menu-active');
                }
            }
        );
    }
}