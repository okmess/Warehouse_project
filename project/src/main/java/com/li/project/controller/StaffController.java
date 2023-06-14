package com.li.project.controller;


import com.alibaba.fastjson.JSON;
import com.li.project.bean.*;
import com.li.project.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/Staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    DataTypeService dataTypeService;

    @RequestMapping("/Login")
    @ResponseBody
    public String StaffLogin(HttpSession session,String username , String password){
        Staff user = staffService.findPassword(username);
        if (user.getPassword().equals(password)) {//匹配密码是否正确
            session.setAttribute("staffID", user.getStaffID());
            return "ok";
        } else {
            return "error";
        }
    }


    @RequestMapping("/ShowStock")
    @ResponseBody
    public String ShowProduct(@RequestParam(value = "keyword",required = false) String keyword,
                              @RequestParam(value = "selectType",required = false) String selectType){
        List<Product> slist = new ArrayList<Product>();
        if(keyword!=null){//如果用户名、名称其中之一不为null，则表示非首次加载，则按条件模糊查询生产商信息
            if("productID".equals(selectType)){     //根据产品编号查询
                keyword=" where productID like '%"+keyword+"%'";
            }else if ("kinds".equals(selectType)){  //根据产品类别查询
                keyword="where kinds like '%"+keyword+"%'";
            }else if("productName".equals(selectType)){   //根据产品名称查询
                keyword="where productName like '%"+keyword+"%'";
            }else if("supplierName".equals(selectType)){
                keyword="where supplierName like '%"+keyword+"%'";
            }
        }
        slist= productService.findQuerry(keyword);
        String data="";//返回的json字符串
        data= JSON.toJSONString(slist);//将集合转换成json字符串
        return data;
    }

    @RequestMapping("/UpdateProductStatus")
    @ResponseBody
    public String  UpdateProductStatus(String productID,int selectType){
        String status="";
        if (selectType==2){
            status="停止出库";
        }else {
            Product product=productService.findByID(productID);
            if (product.getAmount()>=product.getWarningAmount()){
                status="库存充足";
            }else {
                status="库存不足";
            }
        }
        if (productService.updateStatus(productID,status)>0){
            return "ok";
        }else {
            return "error";
        }
    }


    @RequestMapping("/ShowOrder")
    @ResponseBody
    public String ShowOrder(String search, String ID, String selectType){
        String key1="status = '待审核' or status= '取消订单待审核'";
        if("1".equals(search)){
            key1="status != '待审核'";
        }
        List<Order> slist;
        if(ID!=null){//如果用户名、名称其中之一不为null，则表示非首次加载，则按条件模糊查询生产商信息
            if("orderID".equals(selectType)){
                ID="orderID like '%"+ID+"%' and "+key1;
            }else if ("customerID".equals(selectType)){
                ID="customerID like '%"+ID+"%' and "+key1;
            }else if ("type".equals(selectType)){
                ID="type like '%"+ID+"%' and "+key1;
            }else if ("0".equals(selectType)){
                ID=key1;
            }
            slist= orderService.findQuerry(ID);
        }else {
            slist= orderService.findQuerry(key1);
        }
        String data="";//返回的json字符串
        data= JSON.toJSONString(slist);//将集合转换成json字符串
        return data;

    }

    @RequestMapping("/ShowStockOrder")
    @ResponseBody
    public String ShowStockOrder(String orderID){
        List<Order> slist;
        if(orderID!=null){//如果用户名、名称其中之一不为null，则表示非首次加载，则按条件模糊查询生产商信息
            orderID="orderID like '%"+orderID+"%'";
            slist= orderService.findQuerry(orderID);
        }else {
            slist= orderService.findQuerry("customerID='000000' and status='运输中'");
        }
        String data="";//返回的json字符串
        data= JSON.toJSONString(slist);//将集合转换成json字符串
        return data;

    }


    @RequestMapping("/ExamineStockOrder")
    @ResponseBody
    public String ExamineStockOrder( String ID){
        String result="false";
        String status="已完成";
        Order order=orderService.finByID(ID);
        Product product=productService.findByName("'"+order.getProductName()+"'");
        productService.updateAmount(order.getProductName(),product.getAmount()+order.getAmount(),"库存充足");
        int flag=orderService.modifyStatus(ID,status);
        if (flag!=0){
            result="ok";
        }
        return result;
    }

    @RequestMapping("/ExamineCustomer")
    @ResponseBody
    public String ExamineCustomer(String ID,int type){
        String permit = null;
        String result="false";
        int flag=0;
        if(type==0){//type为0，表示审核不通过
            permit="禁止访问";
        }else if(type==1){//type为1，表示审核通过
            permit="允许访问";
        }
        flag=customerService.modifyPermit(ID,permit);
        if (flag!=0){
            result="ok";
        }
        return result;
    }

    @RequestMapping("/ShowCustomer")
    @ResponseBody
    public String ShowCustomer(String customerID){
        List<Customer> slist = null;
        if (customerID!=null) {
            slist = customerService.findList("\"%" + customerID + "%\"");
        }else{
            slist=customerService.findAll();
        }
        String data="";//返回的json字符串
        data= JSON.toJSONString(slist);//将集合转换成json字符串
        return data;
    }

    @RequestMapping("/ShowSupplier")
    @ResponseBody
    public String ShowSupplier(String supplierID){
        List<Supplier> slist = null;
        if (supplierID!=null) {
            slist = supplierService.findList("\"%" + supplierID + "%\"");
        }else{
            slist=supplierService.findAll();
        }
        String data="";//返回的json字符串
        data= JSON.toJSONString(slist);//将集合转换成json字符串
        return data;
    }

    @RequestMapping("/DeleteSupplier")
    @ResponseBody
    public String DeleteSupplier(String supplierID){
        int count=productService.CountBySupplierID(supplierID);
        if (count!=0){
            return "false";
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("supplierID",supplierID);
        if(supplierService.removeByMap(map)){
            return "ok";
        }else {
            return "error";
        }

    }

    @RequestMapping("/SearchProduct")
    @ResponseBody
    public String SearchProduct(){
        List<Product> slist=productService.findQuerry("where amount < warningAmount and status='库存不足'");
        String data="";//返回的json字符串
        data= JSON.toJSONString(slist);//将集合转换成json字符串
        return data;
    }

    @RequestMapping("/AddStockAmount")
    @ResponseBody
    public String AddStockAmount(String productID, int number){
        Product product=productService.findByID(productID);
        Order order=new Order();
        int orderID=dataTypeService.getNumber("'order'");
        dataTypeService.updateNumber(++orderID,"order");
        order.setOrderID("2023"+orderID);
        order.setCustomerID("000000");
        order.setKinds(product.getKinds());
        order.setProductName(product.getProductName());
        order.setAmount(number);
        order.setCost(product.getAmount()*product.getUnitPrice());
        order.setAddress(product.getSupplierName());
        order.setType("仓库补货");
        order.setStatus("运输中");
        orderService.addOrder(order);
        if (productService.updateStatus(productID,"正在补充库存")>0){
            return "ok";
        }else {
            return "error";
        }
    }

    @RequestMapping("/SearchWarehouse")
    @ResponseBody
    public String SearchWarehouse(int warehouseID){
        List<Product> slist=productService.searchByWarehouseID(warehouseID);
        String data="";//返回的json字符串
        data= JSON.toJSONString(slist);//将集合转换成json字符串
        return data;
    }

    @RequestMapping("/AddProduct")
    @ResponseBody
    public String AddProduct(Product product){
        int data = dataTypeService.getNumber("'product'");
        product.setProductID("2023" + data);
        dataTypeService.updateNumber(++data, "product");
        String supplierID=supplierService.searchIDByName(product.getSupplierName());
        product.setSupplierID(supplierID);
        product.setAmount(0);
        product.setStatus("库存不足");
        if (productService.insertProduct(product)>0){
            return "ok";
        }else {
         return "error";
        }
    }

    @RequestMapping("/ExamineOrder")
    @ResponseBody
    public String ExamineOrder(String orderID,int type){
        Order order=orderService.finByID(orderID);
        if (type==1){
         if ("待审核".equals(order.getStatus())){
             if ("入库".equals(order.getType())){
                 orderService.modifyStatus(orderID,"已入库");
             }else if ("出库".equals(order.getKinds())){
                 orderService.modifyStatus(orderID,"已出库");
             }
         }else if ("取消订单待审核".equals(order.getStatus())){
             orderService.modifyStatus(orderID,"已取消");
         }
        }else if (type==0){
            if ("待审核".equals(order.getStatus())){
                orderService.modifyStatus(orderID,"审核失败");
            }
            else if ("取消订单待审核".equals(order.getStatus())){
                orderService.modifyStatus(orderID,"已出库");
            }
        }
        return "ok";
    }
}
