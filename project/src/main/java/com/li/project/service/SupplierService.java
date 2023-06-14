package com.li.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.project.bean.Supplier;

import java.util.List;

public interface SupplierService extends IService<Supplier> {
    List<Supplier> findList(String keyword);
    List<Supplier> findAll();
    String searchIDByName(String supplierName);
}
