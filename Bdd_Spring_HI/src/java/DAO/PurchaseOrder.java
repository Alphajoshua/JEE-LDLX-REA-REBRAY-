package DAO;
// Generated 19 mai 2020 23:06:09 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * PurchaseOrder generated by hbm2java
 */
public class PurchaseOrder  implements java.io.Serializable {


     private int orderNum;
     private int customerId;
     private int productId;
     private Short quantity;
     private BigDecimal shippingCost;
     private Date salesDate;
     private Date shippingDate;
     private String freightCompany;

    public PurchaseOrder() {
    }

	
    public PurchaseOrder(int orderNum, int customerId, int productId) {
        this.orderNum = orderNum;
        this.customerId = customerId;
        this.productId = productId;
    }
    public PurchaseOrder(int orderNum, int customerId, int productId, Short quantity, BigDecimal shippingCost, Date salesDate, Date shippingDate, String freightCompany) {
       this.orderNum = orderNum;
       this.customerId = customerId;
       this.productId = productId;
       this.quantity = quantity;
       this.shippingCost = shippingCost;
       this.salesDate = salesDate;
       this.shippingDate = shippingDate;
       this.freightCompany = freightCompany;
    }
   
    public int getOrderNum() {
        return this.orderNum;
    }
    
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    public int getCustomerId() {
        return this.customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getProductId() {
        return this.productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public Short getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getShippingCost() {
        return this.shippingCost;
    }
    
    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }
    public Date getSalesDate() {
        return this.salesDate;
    }
    
    public void setSalesDate(Date salesDate) {
        this.salesDate = salesDate;
    }
    public Date getShippingDate() {
        return this.shippingDate;
    }
    
    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }
    public String getFreightCompany() {
        return this.freightCompany;
    }
    
    public void setFreightCompany(String freightCompany) {
        this.freightCompany = freightCompany;
    }




}


