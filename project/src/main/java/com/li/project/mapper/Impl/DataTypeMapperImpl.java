package com.li.project.mapper.Impl;

import com.li.project.mapper.DataTypeMapper;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class DataTypeMapperImpl extends SqlSessionDaoSupport implements DataTypeMapper {
    @Override
    public int getNumber(String dataType) {
        return getSqlSession().getMapper(DataTypeMapper.class).getNumber(dataType);
    }

    @Override
    public int updateNumber( int number, String dataType) {
        return getSqlSession().getMapper(DataTypeMapper.class).updateNumber(number,dataType);
    }
}
