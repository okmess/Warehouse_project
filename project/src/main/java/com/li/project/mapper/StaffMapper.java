package com.li.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.project.bean.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StaffMapper extends BaseMapper<Staff> {
    Staff findPassword(String staffID);
    void updateStatus(@Param("status") String status, @Param("staffID") String staffID);
    int addStaff(Staff staff);
    String findStaffID(String staffID);
}
