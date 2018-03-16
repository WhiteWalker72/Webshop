<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/header" >
    <jsp:param value="Categorie - ${id}" name="title"/>
</jsp:include>

<html>
<head>
    <title>Login</title>

    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        form {border: 3px solid #F9F9F9;}

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        button {
            background-color: #ADD8E6;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }

        .cancelbtn {
            width: auto;
            padding: 10px 18px;
            background-color: #ADD8E6;
        }

        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
        }

        img.avatar {
            width: 40%;
            border-radius: 50%;
        }

        .container {
            padding: 16px;
        }

        span.psw {
            float: right;
            padding-top: 16px;
        }

        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }
            .cancelbtn {
                width: 100%;
            }
        }
    </style>
</head>
<body>
<h1>Welkom terug</h1>

<form action="#">

    <div class="container">
        <label for="uname"><b>Gebruikersnaam</b></label>
        <input type="text" placeholder="Voer gebruikersnaam in" name="uname" required>

        <label for="psw"><b>Wachtwoord</b></label>
        <input type="password" placeholder="Voer wachtwoord in" name="psw" required>

        <button type="submit">Login</button>
        <button type="button" class="cancelbtn">Cancel</button>
    </div>

</form>


</body>
</html>

<jsp:include page="/footer.jsp"></jsp:include>