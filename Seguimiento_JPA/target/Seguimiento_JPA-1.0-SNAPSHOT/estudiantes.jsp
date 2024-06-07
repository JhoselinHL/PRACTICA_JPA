<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seguimiento</title>
        <link href="estilo.css" rel="stylesheet" type="text/css"/>
        
    </head>
    <body>
        <h1 class="titulos">Estudiantes</h1>
        <div class="contenido">
            <a  class="boton" href="MainController?action=nuevo">Nuevo Estudiante</a>
        </div>
        <br>
        <table  class="styled-table"border="1">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Email</th>
                <th>Fecha de Nacimiento</th>
                <th></th><th></th>
            </tr>
            
            <c:forEach var="estudiante" items="${estudiantes}">
                <tr>
                    <td>${estudiante.id}</td>
                    <td>${estudiante.nombre}</td>
                    <td>${estudiante.apellidos}</td>
                    <td>${estudiante.email}</td>
                    <td>
                        
                        <fmt:formatDate value="${estudiante.fechaNacimiento}" pattern="yyyy-MM-dd" var="fechaFormateada" />
                        ${fechaFormateada}
                    </td>
                    <td><a class="boton1" href="MainController?action=editar&id=${estudiante.id}">Editar</a></td>
                    <td><a class="boton2" href="MainController?action=eliminar&id=${estudiante.id}" onclick="return confirm('¿Está seguro?');">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
