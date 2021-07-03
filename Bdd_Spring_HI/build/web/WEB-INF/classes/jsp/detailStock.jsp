<%-- 
    Document   : detailStock
    Created on : 3 juil. 2021, 16:08:03
    Author     : Malik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stock Detail</title>
    </head>
    <body>

        <form name="detail" method="POST">
            <p>
                <label for="productId">Product Id</label>
                <input type="number" name="productId" value="${product.productId}" size="30" id="productId" 
                       <c:if test="${user.isAdmin() == false}"> readonly </c:if> />
                </p>
                <p>
                    <label for="manufacturerId">Manufacturer Id</label>
                    <input type="text" name="manufacturerId" value="${product.manufacturerId}" size="30" id="manufacturerId" 
                        readonly />
                </p>
                <p>
                    <label for="productCode">Product Code</label>
                    <input type="text" name="productCode" value="${product.productCode}" size="30" id="productCode" />
            </p>
            <p>
                <label for="purchaseCost">Purchase Cost</label>
                <input type="text" name="purchaseCost" value="${product.purchaseCost}" size="30" id="purchaseCost" />
            </p>
            <p>
                <label for="quantity">Quantity</label>
                <input type="text" name="quantity" value="${product.quantityOnHand}" size="30" id="quantity"/>
            </p>
            <p>
                <label for="markup">Markup</label>
                <input type="text" name="markup" value="${product.markup}" size="30" id="markup"/>
            </p>
            <p>
                <label for="available">Available</label>
                <input type="text" name="available" value="${product.available}" size="30" id="available" 
                       readonly/>
            </p>
            <p>
                <label for="desription">Description</label>
                <input type="text" name="description" value="${product.description}" size="30" id="description"/>
            </p>

            <input type="submit" value="Modifier" formaction="updateStock.htm" />
            <input type="submit" value="Supprimer" formaction="deleteStock.htm" />
        </form>
    </body>
</html>
