package com.li.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.project.bean.ChatText;
import com.li.project.bean.Supplier;
import com.li.project.controller.StaffController;
import com.li.project.mapper.ChatTextMapper;
import com.li.project.mapper.SupplierMapper;
import com.li.project.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {
    @Autowired
    SupplierMapper supplierMapper;


    public void setSupplierMapper(SupplierMapper supplierMapper){
        this.supplierMapper=supplierMapper;
    }

    @Override
    public List<Supplier> findList(String keyword) {
        return supplierMapper.findList(keyword);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierMapper.findAll();
    }

    @Override
    public String searchIDByName(String supplierName) {
        return supplierMapper.searchIDByName(supplierName);
    }
}
