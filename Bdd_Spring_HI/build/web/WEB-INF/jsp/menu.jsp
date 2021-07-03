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
        
        <h1>choisissez une opération dans la liste suivante</h1>
        
        <form name="form"  method="POST">
        
            <input type="submit" formaction="list.htm"  value="Lister"  />
            <input type="submit" formaction="add.htm" value="Ajouter"  />            
            <input type="submit" formaction="formfind.htm" value="Rechercher"  />
        </form>
    </body>
</html>
