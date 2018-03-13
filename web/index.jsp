<jsp:include page="/header.jsp">
    <jsp:param value="Categorie - ${id}" name="title"/>
</jsp:include>

<h1>Welkom</h1>

<h2>Aanbiedingen:</h2>
<jsp:include page="/deals"></jsp:include>

<jsp:include page="/footer.jsp"></jsp:include>