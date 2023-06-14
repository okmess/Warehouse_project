package com.li.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.project.bean.Product;
import com.li.project.mapper.ProductMapper;
import com.li.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper,Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    public void setProductMapper(ProductMapper productMapper){
        this.productMapper=productMapper;
    }

    @Override
    public Product findByID(String productID) {
        return productMapper.findByID(productID);
    }

    @Override
    public Product findByName(String productName) {
        return productMapper.findByName(productName);
    }

    @Override
    public List<Product> findQuerry(String key) {
        return productMapper.findQuerry(key);
    }

    @Override
    public int findUnitPriceByName(String product) {
        return productMapper.findUnitPriceByName(product);
    }

    @Override
    public int updateAmount(String productID, int amount,String status) {
        return productMapper.updateAmount(productID,amount,status);
    }

    @Override
    public int updateStatus(String productID, String status) {
        return productMapper.updateStatus(productID,status);
    }

    @Override
    public List<String> findNameByKinds(String kinds) {
        return productMapper.findNameByKinds(kinds);
    }

    @Override
    public int CountBySupplierID(String supplierID) {
        return productMapper.CountBySupplierID(supplierID);
    }

    @Override
    public List<Product> searchByWarehouseID(int warehouseID) {
        return productMapper.searchByWarehouseID(warehouseID);
    }

    @Override
    public int insertProduct(Product product) {
        return productMapper.insertProduct(product);
    }


}
