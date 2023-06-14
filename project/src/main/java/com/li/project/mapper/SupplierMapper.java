package com.li.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.project.bean.Supplier;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupplierMapper  extends BaseMapper<Supplier> {
    List<Supplier> findList(String keyword);
    List<Supplier> findAll();
    String searchIDByName(String supplierName);
}
