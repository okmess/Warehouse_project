package com.li.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.project.bean.Customer;

import java.util.List;


public interface CustomerService extends IService<Customer> {
    Customer findCustomer(String ID);
    List<Customer> findList(String ID);
    List<Customer> findAll();
    int modifyPermit(String customerID,String permit);
}
