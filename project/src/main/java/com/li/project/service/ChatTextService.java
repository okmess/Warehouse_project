package com.li.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.project.bean.ChatText;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ChatTextService extends IService<ChatText> {
    int InsertMessge(ChatText chatText);
    List<ChatText> findMessgeByID(String staffID, String customerID);
}
