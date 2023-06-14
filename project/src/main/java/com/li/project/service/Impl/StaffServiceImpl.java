package com.li.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.project.bean.Staff;
import com.li.project.service.StaffService;
import com.li.project.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper,Staff> implements StaffService {
    @Autowired
    private StaffMapper staffMapper;

    public void setStaffMapper(StaffMapper staffMapper){
        this.staffMapper=staffMapper;
    }

    @Override
    public Staff findPassword(String staffID) {
        return staffMapper.findPassword(staffID);
    }

    @Override
    public void updateStatus(String status, String staffID) {
            staffMapper.updateStatus(status, staffID);
    }

    @Override
    public int addStaff(Staff staff) {
        return staffMapper.addStaff(staff);
    }

    @Override
    public String findStaffID(String staffID) {
        return staffMapper.findStaffID(staffID);
    }
}
