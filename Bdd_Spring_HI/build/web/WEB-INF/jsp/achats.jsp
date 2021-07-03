<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Achats</title>
    </head>
    <body>
        
        
        <H1>Achats du client n°<%=request.getParameter("numero")%></H1>
        <table border="1" cellpadding="10">
        
                
                  <c:forEach items="${achats}" var="cli"> 
                      <TR>
                      <TD>${cli.orderNum}</TD>
                      <TD>${cli.customerId}</TD>
                      <TD>${cli.productId}</TD>
                      <TD>${cli.quantity}</TD>
                      <TD>${cli.shippingCost}</TD>
                      <TD>${cli.salesDate}</TD>
                      <TD>${cli.shippingDate}</TD>
                      <TD>${cli.freightCompany}</TD>
                      </TR> 
                  </c:forEach>
                 
        </table>
       
        
    </body>
</html>
