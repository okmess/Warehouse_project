package com.li.project.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName("order_form")
public class Order  implements Serializable {
    private String orderID;//订单ID
    private String kinds;//存储物品类型
    private String productName;//存储物品名称
    private int amount;//存储物品数量
    private int cost;//存储开销
    private String customerID;
    private String address;
    private String type;
    private String status;//物品状态
}
