package com.li.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.project.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> findQuerry(String key);
    Order findByID(String orderID);
    List<Order> findAll();
    int modifyStatus(@Param("orderID") String orderID, @Param("status") String status);
    int addOrder(Order order);
    int updateNameAndNumber(@Param("orderID") String orderID, @Param("productName") String productName, @Param("amount") int amount,
                            @Param("cost") int cost, @Param("address") String address, @Param("type") String type, @Param("status") String status);
}
