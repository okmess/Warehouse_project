package com.li.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.project.bean.Order;
import com.li.project.service.OrderService;
import com.li.project.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public void setOrderMapper(OrderMapper orderMapper){
        this.orderMapper=orderMapper;
    }

    @Override
    public List<Order> findQuerry(String key) {
        return orderMapper.findQuerry(key);
    }

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public int modifyStatus(String orderID, String status) {
        return orderMapper.modifyStatus(orderID,status);
    }

    @Override
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public int updateNameAndNumber(String orderID, String productName, int amount, int cost,String address,String type,String status) {
        return orderMapper.updateNameAndNumber(orderID,productName,amount,cost,address,type,status);
    }

    @Override
    public Order finByID(String orderID) {
        return orderMapper.findByID(orderID);
    }
}
