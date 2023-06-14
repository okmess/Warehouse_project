package com.li.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.project.bean.ChatText;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatTextMapper extends BaseMapper<ChatText> {
    int InsertMessge(ChatText chatText);
    List<ChatText> findMessgeByID(String staffID,String customerID);
}
