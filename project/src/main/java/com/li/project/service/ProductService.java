package com.li.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.project.bean.Product;

import java.util.List;

public interface ProductService extends IService<Product> {
    Product findByID(String productID);
    Product findByName(String productName);
    List<Product> findQuerry(String key);
    int findUnitPriceByName(String product);
    int updateAmount(String productID,int amount,String status);
    int updateStatus(String productID,String status);
    List<String> findNameByKinds(String kinds);
    int CountBySupplierID(String supplierID);
    List<Product> searchByWarehouseID(int warehouseID);
    int insertProduct(Product product);
}
