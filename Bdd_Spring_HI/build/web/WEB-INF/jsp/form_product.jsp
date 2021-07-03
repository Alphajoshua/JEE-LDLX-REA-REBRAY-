<%-- 
    Document   : form_product
    Created on : 3 juil. 2021, 18:58:51
    Author     : Malik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create your own product</title>
    </head>
    <body>

        <form name="detail" method="POST">
            <p>
                <label for="productId">Product Id</label>
                <input type="number" name="productId" size="30" id="productId"/>            
            </p>
            <p>
                <label for="manufacturerId">Manufacturer Id</label>
                <select name="manufacturerId" id="mi">
                    <c:forEach items="${manufacturer}" var="mi">
                        <option>${mi}</option>
                    </c:forEach>
                </select>            
            </p>  
            <p>
                <label for="productCode">Product Code</label>
                <select name="productCode" id="pc">
                    <c:forEach items="${productCode}" var="pc">
                        <option>${pc}</option>
                    </c:forEach>
                </select>            
            </p>  
            <p>
                <label for="purchaseCost">Purchase Cost</label>
                <input type="text" name="purchaseCost" size="30" id="purchaseCost" />
            </p>
            <p>
                <label for="quantity">Quantity</label>
                <input type="text" name="quantity" size="30" id="quantity"/>
            </p>
            <p>
                <label for="markup">Markup</label>
                <input type="text" name="markup" size="30" id="markup"/>
            </p>
            <p>
                <label for="available">Available</label>
                <input type="hidden" name="available" size="30" id="available" />
            </p>
            <p>
                <label for="desription">Description</label>
                <input type="text" name="description" size="30" id="description"/>
            </p>

            <input type="submit" value="Créer" formaction="insertStock.htm" />

        </form>
    </body>
</html>