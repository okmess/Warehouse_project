package com.li.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.project.bean.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
    Customer findCustomer(String ID);
    List<Customer> findList(String ID);
    List<Customer> findAll();
    int modifyPermit(@Param("customerID") String customerID, @Param("permit") String permit);
}
