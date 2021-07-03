<%-- 
    Document   : recherche
    Created on : 7 juin 2019, 11:52:07
    Author     : faycal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recherche</title>
    </head>
    <body>
        <form name="recherche" action="find.htm" method="POST">
           <p>
                <label for="nom">Nom de l'entreprise</label>
                <input type="text" name="nom"  size="30" id="nom" />
            </p>
            <input type="submit" value="rechercher"  />
        </form>
        
    </body>
</html>
