package com.li.project.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataTypeMapper {
    int getNumber(String dataType);
    int updateNumber(@Param("number") int number, @Param("dataType") String dataType);
}
