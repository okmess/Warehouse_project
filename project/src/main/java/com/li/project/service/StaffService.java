package com.li.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.project.bean.Staff;

public interface StaffService extends IService<Staff> {
    Staff findPassword(String staffID);
    void updateStatus(String status,String staffID);
    int addStaff(Staff staff);
    String findStaffID(String staffID);
}
