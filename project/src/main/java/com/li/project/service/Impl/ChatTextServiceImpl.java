package com.li.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.project.bean.ChatText;
import com.li.project.mapper.ChatTextMapper;
import com.li.project.service.ChatTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatTextServiceImpl extends ServiceImpl<ChatTextMapper, ChatText> implements ChatTextService {

    @Autowired
    private ChatTextMapper chatTextMapper;

    public void setChatTextMapper(ChatTextMapper chatTextMapper){
        this.chatTextMapper=chatTextMapper;
    }


    @Override
    public int InsertMessge(ChatText chatText) {
        return chatTextMapper.InsertMessge(chatText);
    }

    @Override
    public List<ChatText> findMessgeByID(String staffID, String customerID) {
        return chatTextMapper.findMessgeByID(staffID,customerID);
    }
}
