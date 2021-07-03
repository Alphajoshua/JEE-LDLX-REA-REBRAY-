<%-- 
    Document   : menuStock
    Created on : 19 juin 2021, 16:22:04
    Author     : Malik
--%>

<%-- 
    Document   : menu
    Created on : 4 mars 2018, 16:59:09
    Author     : faycal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    <body>

        <h1> Opérations pour la gestion des stocks : </h1>

        <form name="form"  method="POST">

            <input type="submit" formaction="listProduct.htm"  value="Lister"  />
            <input type="submit" formaction="addStock.htm" value="Ajouter"  />

        </form>
    </body>
</html>

