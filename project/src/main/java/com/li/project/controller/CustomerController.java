package com.li.project.controller;

import com.alibaba.fastjson.JSON;
import com.li.project.bean.Customer;
import com.li.project.bean.Order;
import com.li.project.bean.Product;
import com.li.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/Customer")
public class CustomerController {
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;
    @Autowired
    DataTypeService dataTypeService;


    @RequestMapping("/Login")
    @ResponseBody
    public String CustomerLogin(HttpSession session, String username, String password) {
        Customer user = customerService.findCustomer(username);
        if (user.getPassword().equals(password) && "允许访问".equals(user.getPermit())) {//匹配密码是否正确
            session.setAttribute("customerID", user.getCustomerID());
            return "ok";
        } else {
            return "error";
        }
    }

    @RequestMapping("/AddOrder")
    @ResponseBody
    public String AddOrder(Order order, String username) {
        Product product = productService.findByName("'" + order.getProductName() + "'");
        if (product==null){
            return "error";
        }
        if (!"停止出库".equals(product.getStatus())) {
            if (product.getAmount() - order.getAmount() > 0) {
                int unitPrice = product.getUnitPrice();
                int data = dataTypeService.getNumber("'order'");
                order.setOrderID("2023" + data);
                order.setCustomerID(username);
                order.setCost(unitPrice * order.getAmount());
                order.setStatus("待审核");
                int Pamount=product.getAmount();
                int warningAmount=product.getWarningAmount();
                int amount=order.getAmount();
                String productName=order.getProductName();
                if ( Pamount- amount < warningAmount) {
                    productService.updateAmount(productName, Pamount -amount , "库存不足");
                } else {
                    productService.updateAmount(productName, Pamount - amount, "库存充足");
                }
                dataTypeService.updateNumber(++data, "order");
                if (orderService.addOrder(order) > 0) { return "ok"; }
                else { return "error"; }
            } else { return "false"; }
        }else { return "forbid"; }
    }

    //@RequestParam中的required表示这个变量是否必须，即是否可以为null
    @RequestMapping("/SearchOrder")
    @ResponseBody
    public String SearchOrder(String keyword, String selectType, String customerID) {
        String key1 = "customerID =" + customerID;
        List<Order> slist = new ArrayList<Order>();
        if (keyword != null) {//如果用户名、名称其中之一不为null，则表示非首次加载，则按条件模糊查询生产商信息
            if ("orderID".equals(selectType)) { //订单号关键字
                keyword = "orderID like '%" + keyword + "%' and " + key1;
            } else if ("kinds".equals(selectType)) {//产品类别
                keyword = "kinds like '%" + keyword + "%' and " + key1;
            } else if ("productName".equals(selectType)) {//产品名称
                keyword = "productName like '%" + keyword + "%' and " + key1;
            }
            slist = orderService.findQuerry(keyword);
        } else if ("已出库".equals(selectType)) {
            keyword = key1 + " and status='" + selectType + "'";
            slist = orderService.findQuerry(keyword);
        } else {
            slist = orderService.findQuerry(key1);
        }
        String data = "";//返回的json字符串
        data = JSON.toJSONString(slist);//将集合转换成json字符串
        return data;
    }

    @RequestMapping("/SearchOrderByType")
    @ResponseBody
    public String SearchOrderByType(String keyword, String selectType, String customerID) {
        String type = "type='入库' and status='已入库'";
        if (customerID != null) {
            type = "customerID=" + customerID + " and " + type;
        }
        if (keyword != null) {//如果用户名、名称其中之一不为null，则表示非首次加载，则按条件模糊查询生产商信息
            if ("orderID".equals(selectType)) { //订单号关键字
                type = "orderID like '%" + keyword + "%' and " + type;
            } else if ("kinds".equals(selectType)) {//产品类别
                type = "kinds like '%" + keyword + "%' and " + type;
            } else if ("productName".equals(selectType)) {//产品名称
                type = "productName like '%" + keyword + "%' and " + type;
            }
        }
        List<Order> slist = orderService.findQuerry(type);
        String data = "";//返回的json字符串
        data = JSON.toJSONString(slist);//将集合转换成json字符串
        return data;
    }

    @RequestMapping("/UpdateOrder")
    @ResponseBody
    public String UpdateOrder(String orderID) {
        Order order = orderService.finByID(orderID);

        String status = "待审核";
        String type = "出库";

        String result = "false";
        if (orderService.updateNameAndNumber(order.getOrderID(), order.getProductName(), order.getAmount(), order.getCost(),
                order.getAddress(), type, status) > 0) {
            result = "ok";
        }
        return result;
    }

    @RequestMapping("/ConfirmOrder")
    @ResponseBody
    public String ConfirmOrder(String orderID) {
        Order order = orderService.finByID(orderID);

        String status = "已完成";
        String result = "false";
        if (orderService.modifyStatus(orderID, status) > 0) {
            result = "ok";
        }
        return result;
    }

    @RequestMapping("/ShowProduct")
    @ResponseBody
    public String ShowProduct(String keyword, String selectType) {
        List<Product> slist = new ArrayList<Product>();
        if (keyword == null) {
            keyword = "where status != '停止出库'";
        } else {
            keyword = "where status != '停止出库' and " + keyword;
        }
        slist = productService.findQuerry(keyword);
        String data = "";//返回的json字符串
        data = JSON.toJSONString(slist);//将集合转换成json字符串
        return data;
    }


    @RequestMapping("/SearchProductByKinds")
    @ResponseBody
    public String SearchProductByKinds(String kinds) {
        List<String> slist;
        if (kinds == null) {
            kinds = "1";
        }
        slist = productService.findNameByKinds(kinds);
        String data = "";
        data = JSON.toJSONString(slist);
        return data;
    }

    @RequestMapping("/CancelOrder")
    @ResponseBody
    public String CancelOrder(String orderID,String status) {
        if ("待审核".equals(status)){
            if (orderService.modifyStatus(orderID,"已取消")>0){
                return "ok";
            }
        }else {
            if (orderService.modifyStatus(orderID,"取消订单待审核")>0){
                return "ok";
            }
        }
        return "error";
    }
}
