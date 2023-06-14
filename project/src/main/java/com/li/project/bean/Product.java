package com.li.project.bean;

import lombok.Data;

@Data
public class Product {
    private String productID;
    private String kinds;
    private String productName;
    private int unitPrice;
    private String supplierID;
    private String supplierName;
    private int amount;
    private int warningAmount;
    private String status;
    private String image;
    private int warehouseID;
}
