package com.li.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.project.bean.Order;


import java.util.List;

public interface OrderService extends IService<Order> {
    List<Order> findQuerry(String key);
    List<Order> findAll();
    int modifyStatus(String orderID,  String status);
    int addOrder(Order order);
    int updateNameAndNumber( String orderID, String productName, int amount, int cost,String address,String type,String status);
    Order finByID(String orderID);
}
