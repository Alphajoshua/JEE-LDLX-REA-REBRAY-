<%-- 
    Document   : common.jsp
    Created on : 3 juil. 2021, 20:56:27
    Author     : Malik
--%>

<%--pour insertion automatique dans toutes les jsp et éviter les oublis--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%-- JSTL librairie core pour pouvoir l'utiliser partout--%>
<div style="padding-bottom: 2em; padding-left: 2em; background-color: #26041A; margin-bottom: 10px; color: #a47810">
<p style="color: red;">  Bienvenue ${user.login}</p>
<input type="submit" formaction="logout.htm" value="Se déconnecter"  />
<style>
    body {
        background-color: #300a24;
        color: #b2c9db
    }
    table {
        border-color: #ffffff;
    }
    input[type="submit"] {
        height: 5em;
        margin-left: 2em;
        background-color: white;
        border-radius: 10px;
        margin-top: 1em;
        width: 10em;
    }
    input[type="reset"] {
        height: 5em;
        margin-left: 2em;
        background-color: white;
        border-radius: 10px;
        margin-top: 1em;
        width: 10em;
    }
</style>

</div>