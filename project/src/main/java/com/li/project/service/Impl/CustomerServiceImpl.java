package com.li.project.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.project.bean.Customer;
import com.li.project.bean.Order;
import com.li.project.mapper.OrderMapper;
import com.li.project.service.CustomerService;
import com.li.project.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl  extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;


    public void setCustomerMapper(CustomerMapper customerMapper){
        this.customerMapper=customerMapper;
    }

    @Override
    public Customer findCustomer(String ID) {
        return customerMapper.findCustomer(ID);
    }

    @Override
    public List<Customer> findList(String ID) {
        return customerMapper.findList(ID);
    }

    @Override
    public List<Customer> findAll() {
        return customerMapper.findAll();
    }

    @Override
    public int modifyPermit(String customerID, String permit) {
        return customerMapper.modifyPermit(customerID,permit);
    }

}
