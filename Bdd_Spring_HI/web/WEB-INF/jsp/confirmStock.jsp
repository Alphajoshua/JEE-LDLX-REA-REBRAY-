<%-- 
    Document   : confirmStock
    Created on : 3 juil. 2021, 18:47:43
    Author     : Malik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmation</title>
    </head>
    <body>
        <h1>${confirm} </h1>
        <form action="listProduct.htm"  method="POST">
        <input type="submit" value="Afficher tous les enregistrements"  />
        </form>
    </body>
</html>