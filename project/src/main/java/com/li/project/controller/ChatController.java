package com.li.project.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.li.project.bean.ChatText;
import com.li.project.service.ChatTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/Chat")
public class ChatController {
    @Autowired
    ChatTextService chatTextService;

    @RequestMapping("/LoadingText")
    @ResponseBody
    public String LoadingText(String staffID,String customerID){
        List<ChatText> slist=null;
        slist=chatTextService.findMessgeByID(staffID,customerID);
        List<JSONObject> slist1=new ArrayList<JSONObject>();
       for (ChatText chatText:slist){
           JSONObject obj = JSONUtil.parseObj(chatText.getMessage());
            slist1.add(obj);
       }

        String data = "";//返回的json字符串
        data = JSON.toJSONString(slist1);//将集合转换成json字符串
        return data;
    }
}
