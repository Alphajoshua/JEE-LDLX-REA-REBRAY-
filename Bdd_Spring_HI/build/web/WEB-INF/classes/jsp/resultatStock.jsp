<%-- 
    Document   : resultatStock
    Created on : 19 juin 2021, 16:37:35
    Author     : Malik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultat</title>
    </head>
    <body>

        <table border="1" cellpadding="10">
              <TR>
                <TH> Product Id </TH>
                <TH> Manufacturer Id </TH>
                <TH> Product Code</TH>
                <TH> Purchase Cost </TH>
                <TH> Quantity </TH> 
                <TH> Markup </TH>
                <TH> Available </TH>
                <TH> Description </TH>
            </TR>

           <c:forEach items="${liste}" var="product" varStatus="row" >
                <TR>
                    <c:forEach items="${product}" var="prd" >  
                        <TD>${prd}
                        </TD>
                    </c:forEach>
                    <TD>
                        <form name="Result" action="detailStock.htm"  method="POST">
                            <input type=hidden name="num"  value="${liste[row.index][0]}"/>
                            <input type=submit  value="Detail"  />
                        </form>
                    </TD>
                </TR>
            </c:forEach>

        </table>

    </body>
</html>
