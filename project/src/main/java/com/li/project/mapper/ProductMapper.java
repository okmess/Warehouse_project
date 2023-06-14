package com.li.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.project.bean.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    Product findByID(String productID);
    Product findByName(String productName);
    List<Product> findQuerry(String key);
    int findUnitPriceByName(String productName);
    int updateAmount(@Param("productName") String productName, @Param("amount") int amount, @Param("status") String status);
    int updateStatus(@Param("productID") String productID, @Param("status") String status);
    List<String> findNameByKinds(@Param("kinds") String kinds);
    int CountBySupplierID(String supplierID);
    List<Product> searchByWarehouseID(int warehouseID);
    int insertProduct(Product product);
}
