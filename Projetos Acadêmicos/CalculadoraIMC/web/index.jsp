<%-- 
    Document   : index
    Created on : 11 de abr. de 2024, 20:19:37
    Author     : 081210028
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Calculadora IMC</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <style>
            .menu {
                width: 200px;
                border: 2px solid black;
                padding: 20px;
                margin-top: 15%;
            }
        </style>
    </head>
    <body>
        <center>
            <div class="menu">
                <form action="IMCServlet" method="POST">
                    <br>
                    <c:out value="${'Bem vindo a calculadora de IMC!'}" />
                    <br>
                    <label id="numero1">Peso (kg): </label>
                    <input type="text" name="txtPeso" id="txtPeso">
                    <br>
                    <br>
                    <label id="numero2">Altura (metros): </label>
                    <input type="text" name="txtAltura" id="txtAltura">
                    <br>
                    <br>

                    <input type="submit" id="btnCalcular" value="Calcular">
                    <br>
                </form>
            </div>
        </center>
    </body>
</html>
