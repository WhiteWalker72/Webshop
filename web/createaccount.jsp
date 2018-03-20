<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/header" >
    <jsp:param value="Categorie - ${id}" name="title"/>
</jsp:include>

<form method="post", action="/Account" style="border:1px solid #F9F9F9">
    <div class="container">

        <label for="naam"><b>Voor en achternaam</b></label>
        <input type="text" placeholder="Voer uw naam in" name="name" id="naam" required>

        <label for="straat"><b>Straat</b></label>
        <input type="text" placeholder="Straatnaam" name="street" id="straat" required>

        <label for="huisnummer"><b>Huisnummer</b></label>
        <input type="text" placeholder="Huisnummer" name="number" id="huisnummer" required>

        <label for="postcode"><b>Postcode</b></label>
        <input type="text" placeholder="Postcode" name="postalcode" id="postcode" required>

        <label for="plaatsnaam"><b>Plaatsnaam</b></label>
        <input type="text" placeholder="Plaatsnaam" name="city" id="plaatsnaam" required>

        <label for="land"><b>Land</b></label>
        <input type="text" placeholder="Land" name="country" id="land" required>

        <label for="inlognaam"><b>Inlognaam</b></label>
        <input type="text" placeholder="Kies een inlognaam" name="username" id="inlognaam" required>

        <label for="psw"><b>Wachtwoord</b></label>
        <input type="password" placeholder="Kies een wachtwoord" name="psw" id="psw" required>

        <button type="submit" class="ms-button">Creëer account</button>
    </div>
</form>

<jsp:include page="/footer.jsp"></jsp:include>