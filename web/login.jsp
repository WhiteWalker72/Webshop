<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/header" >
    <jsp:param value="Categorie - ${id}" name="title"/>
</jsp:include>

<h1>Welkom terug</h1>

<form method="post" action="/Login">

    <div class="ms-login">
        <label for="username"><b>Gebruikersnaam</b></label>
        <input type="text" placeholder="Voer gebruikersnaam in" name="username" id="username" required>

        <label for="pass"><b>Wachtwoord</b></label>
        <input type="password" placeholder="Voer wachtwoord in" name="pass" id="pass" required>

        <button class="ms-button" type="submit">Login</button>
        <a class="ms-link" href="/createaccount.jsp">Nog geen account? Registreer hier een account.</a>
    </div>

</form>

<jsp:include page="/footer.jsp"></jsp:include>