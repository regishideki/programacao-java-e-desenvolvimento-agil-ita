<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Topico</title>
</head>
<body>
    <section>
        <h4>${topico.getLogin()} - ${topico.getTitulo()}</h4>
        <p>
            ${topico.getConteudo()}
        </p>
    </section>
    <section>
        <c:forEach items="${topico.getComentarios()}" var="comentario">
            <h5>${comentario.getLogin()}</h5>
            <p>
                ${comentario.getComentario()}
            </p>
        </c:forEach>
    </section>
    <section>
        <form action="inserirComentario", method="post">
            <textarea rows="4" name="comentario"></textarea>
            <input type="hidden" name="idTopico" value="${topico.getId()}" />
            <input type="submit" value="Adicionar comentário">
        </form>
    </section>
    <a href="/listagemTopicos">Voltar</a>
</body>
</html>
