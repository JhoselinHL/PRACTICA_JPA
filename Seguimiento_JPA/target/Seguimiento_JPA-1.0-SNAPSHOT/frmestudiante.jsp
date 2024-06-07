<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seguimiento</title>
        <link href="estilo.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <fmt:formatDate value="${estudiante.fechaNacimiento}" pattern="yyyy-MM-dd" var="fechaNacimientoFormateada" />
        <h1 class="titulos2">Formulario de Estudiante</h1>
        <form class="styled-form" action="MainController" method="post">
            <input type="hidden" name="id" value="${estudiante.id == null ? 0 : estudiante.id}" />
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" id="nombre" value="${estudiante.nombre}" required /><br />
            <label for="apellidos">Apellidos:</label>
            <input type="text" name="apellidos" id="apellidos" value="${estudiante.apellidos}" required /><br />
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" value="${estudiante.email}" required /><br />
            <label for="fechaNacimiento">Fecha de Nacimiento:</label>
            <input type="date" name="fechaNacimiento" id="fechaNacimiento" value="${fechaNacimientoFormateada}" required /><br />
            <input type="submit" value="Guardar"/>
            <a href="MainController">Volver</a>
        </form>
    </body>
</html>