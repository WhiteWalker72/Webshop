window.onload = function() {

	document.querySelector(".ms-menu-categories").addEventListener("click", toggleCategories);
}

function toggleCategories(event) {

	var cElement = document.querySelector('.ms-categories');

	if (cElement.style.display === "none" || cElement.style.display === "") {
        cElement.style.display = "block";
		event.target.classList.add('ms-menu-active');
    } else {
        cElement.style.display = "none";
		event.target.classList.remove('ms-menu-active');
    }
}
