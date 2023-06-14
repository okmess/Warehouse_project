package com.li.project.service.Impl;

import com.li.project.mapper.DataTypeMapper;
import com.li.project.service.DataTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataTypeServiceImpl implements DataTypeService {
    @Autowired
    private DataTypeMapper dataTypeMapper;

    public void setDataTypeMapper(DataTypeMapper dataTypeMapper){
        this.dataTypeMapper=dataTypeMapper;
    }

    @Override
    public int getNumber(String dataType) {
        return dataTypeMapper.getNumber(dataType);
    }

    @Override
    public int updateNumber(int number, String dataType) {
        return dataTypeMapper.updateNumber(number,dataType);
    }
}
