<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/header" >
    <jsp:param value="Categorie - ${id}" name="title"/>
</jsp:include>

<html>
<head>
    <title>Creëer account</title>
    <style>* {box-sizing: border-box}

    /* Full-width input fields */
    input[type=text], input[type=password] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    input[type=text]:focus, input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }

    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
    }

    /* Set a style for all buttons */
    button {
        background-color: #ADD8E6;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
        opacity: 0.9;
    }

    button:hover {
        opacity:1;
    }

    /* Extra styles for the cancel button */
    .cancelbtn {
        padding: 14px 20px;
        background-color: #59cbf0;
    }

    /* Float cancel and signup buttons and add an equal width */
    .cancelbtn, .signupbtn {
        float: left;
        width: 40%;
    }

    /* Add padding to container elements */
    .container {
        padding: 16px;
    }

    /* Clear floats */
    .clearfix::after {
        content: "";
        clear: both;
        display: table;
    }

    /* Change styles for cancel button and signup button on extra small screens */
    @media screen and (max-width: 300px) {
        .cancelbtn, .signupbtn {
            width: 100%;
        }
    }</style>
</head>
<body>
<form method="post", action="/Account" style="border:1px solid #F9F9F9">
    <div class="container">

        <label for="naam"><b>Voor en achternaam</b></label>
        <input type="text" placeholder="Voer uw naam in" name="name" required>

        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter Email" name="email" required>

        <label for="straat"><b>Straat</b></label>
        <input type="text" placeholder="Straatnaam" name="street" required>

        <label for="huisnummer"><b>Huisnummer</b></label>
        <input type="text" placeholder="Huisnummer" name="number" required>

        <label for="Postcode"><b>Postcode</b></label>
        <input type="text" placeholder="Postcode" name="postalcode" required>

        <label for="plaatsnaam"><b>Plaatsnaam</b></label>
        <input type="text" placeholder="Plaatsnaam" name="city" required>

        <label for="land"><b>Land</b></label>
        <input type="text" placeholder="Land" name="country" required>

        <label for="inlognaam"><b>Inlognaam</b></label>
        <input type="text" placeholder="Kies een inlognaam" name="username" required>

        <label for="psw"><b>Wachtwoord</b></label>
        <input type="password" placeholder="Kies een wachtwoord" name="psw" required>

        <div class="clearfix">
            <button type="button" class="cancelbtn">Cancel</button>
            <button type="submit" class="signupbtn">Creëer account</button>
        </div>
    </div>
</form>

</body>
</html>

<jsp:include page="/footer.jsp"></jsp:include>