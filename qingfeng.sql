/*
 Navicat Premium Data Transfer

 Source Server         : rott1
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : qingfeng

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 14/06/2023 17:39:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_text
-- ----------------------------
DROP TABLE IF EXISTS `chat_text`;
CREATE TABLE `chat_text`  (
  `staffID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `customerID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL,
  INDEX `staffID`(`staffID`) USING BTREE,
  INDEX `customerID`(`customerID`) USING BTREE,
  CONSTRAINT `chat_text_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `chat_text_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chat_text
-- ----------------------------
INSERT INTO `chat_text` VALUES ('202301', '100001', '{\"from\":\"100001\",\"to\":\"202301\",\"text\":\"你好\\n\",\"chatObject\":\"customer\"}', '2023-04-21 02:21:39');
INSERT INTO `chat_text` VALUES ('202301', '100001', '{\"from\":\"100001\",\"to\":\"202301\",\"text\":\"请问牛肉的价格会下降吗\",\"chatObject\":\"customer\"}', '2023-04-21 02:26:56');
INSERT INTO `chat_text` VALUES ('202301', '100001', '{\"from\":\"202301\",\"to\":\"100001\",\"text\":\"抱歉，近期牛肉的价格可能不会有太大变化\",\"chatObject\":\"staff\"}', '2023-04-21 02:27:29');
INSERT INTO `chat_text` VALUES ('202301', '100001', '{\"from\":\"100001\",\"to\":\"202301\",\"text\":\"你好\",\"chatObject\":\"customer\"}', '2023-04-22 09:47:24');
INSERT INTO `chat_text` VALUES ('202301', '100001', '{\"from\":\"202301\",\"to\":\"100001\",\"text\":\"你好\\n\",\"chatObject\":\"staff\"}', '2023-05-10 04:01:04');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customerID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `customerName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`customerID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('100001', '张明昊', '123456', '男', 30, '2423779636@qq.com', '清风市中山街道第十四号', '允许访问');
INSERT INTO `customer` VALUES ('100008', '文诗琪', '26369453', '女', 25, '36984568@qq.com', '清风市解放路12号', '允许访问');
INSERT INTO `customer` VALUES ('243694', '李晓华', '581564', '男', 27, '23546956@qq.com', '清风市解放路第32号', '允许访问');
INSERT INTO `customer` VALUES ('369752', '陈帧', '365159599', '男', 26, '2645865235@qq.com', '清风市解放路33号', '允许访问');
INSERT INTO `customer` VALUES ('884632', '刘嘉诚', '225588', '男', 34, '6858254@qq.com', '清风市中山街道第四四号', '允许访问');

-- ----------------------------
-- Table structure for data_type
-- ----------------------------
DROP TABLE IF EXISTS `data_type`;
CREATE TABLE `data_type`  (
  `dataType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `number` int(0) NOT NULL,
  PRIMARY KEY (`dataType`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data_type
-- ----------------------------
INSERT INTO `data_type` VALUES ('order', 145);
INSERT INTO `data_type` VALUES ('product', 102);

-- ----------------------------
-- Table structure for entitywarehouse
-- ----------------------------
DROP TABLE IF EXISTS `entitywarehouse`;
CREATE TABLE `entitywarehouse`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `confirmLink` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `staffID` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `kinds` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `meassge` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `visitNumber` int(0) NULL DEFAULT NULL,
  `date` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_form
-- ----------------------------
DROP TABLE IF EXISTS `order_form`;
CREATE TABLE `order_form`  (
  `orderID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `customerID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `kinds` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `productName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `amount` int(0) NOT NULL,
  `cost` int(0) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`orderID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_form
-- ----------------------------
INSERT INTO `order_form` VALUES ('100001', '100001', '牛肉', '清风牛肉', 100, 10000, '清风市中山街道第十四号', '出库', '已完成');
INSERT INTO `order_form` VALUES ('100002', '243694', '牛肉', '清风牛肉', 100, 10000, '清风市解放路第32号', '出库', '已出库');
INSERT INTO `order_form` VALUES ('100003', '100008', '羊肉', '新疆羔羊', 300, 60000, '清风市解放路12号', '出库', '已出库');
INSERT INTO `order_form` VALUES ('100004', '369752', '羊肉', '新疆羔羊', 200, 40000, '清风市解放路33号', '出库', '已出库');
INSERT INTO `order_form` VALUES ('100005', '100001', '羊肉', '新疆羔羊', 100, 20000, '清风市中山街道第十四号', '出库', '取消订单待审核');
INSERT INTO `order_form` VALUES ('100006', '100001', '半加工食品', '特制配料', 300, 100, '清风市中山街道第十四号', '出库', '待审核');
INSERT INTO `order_form` VALUES ('1026594', '100001', '羊肉', '清风羊肉', 100, 5500, '清风市中山街道第十四号', '出库', '已出库');
INSERT INTO `order_form` VALUES ('2023034', '100001', '半加工食品', '自制腊肉', 200, 300, '清风市中山街道第十四号', '入库', '审核失败');
INSERT INTO `order_form` VALUES ('2023117', '100001', '牛肉', '清风牛肉', 1000, 100000, '清风市解放路西101号', '出库', '待审核');
INSERT INTO `order_form` VALUES ('2023119', '000000', '牛肉', '清风牛肉', 10000, 10000, '清风肉类食品有限公司', '仓库补货', '已完成');
INSERT INTO `order_form` VALUES ('2023120', '000000', '羊肉', '新疆羔羊肉', 10000, 5580, '新疆源立食品有限公司', '仓库补货', '已完成');
INSERT INTO `order_form` VALUES ('2023121', '100001', '牛肉', '清风牛肉', 1000, 45000, '清风市解放路西101号', '出库', '审核失败');
INSERT INTO `order_form` VALUES ('2023132', '000000', '鸭肉', '道州灰鸭', 500, 1250, '清风农产品供销社', '仓库补货', '已完成');
INSERT INTO `order_form` VALUES ('2023142', '000000', '鸭肉', '道州灰鸭', 1000, 8750, '清风农产品供销社', '仓库补货', '运输中');
INSERT INTO `order_form` VALUES ('2023143', '100001', '羊肉', '清风羊肉', 40, 2200, '清风市解放路第1', '出库', '已取消');
INSERT INTO `order_form` VALUES ('2023145', '000000', '鹅肉', '清风灰鹅', 1000, 0, '清风肉类食品有限公司', '仓库补货', '运输中');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `productID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `kinds` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `productName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `unitPrice` int(0) NOT NULL,
  `supplierID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `supplierName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `amount` int(0) NOT NULL,
  `warningAmount` int(0) NOT NULL,
  `warehouseID` int(0) NOT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`productID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('10001', '牛肉', '清风牛肉', 45, '2023001', '清风肉类食品有限公司', 10500, 500, 1, '库存充足', 'http://localhost:8090/beef1.jpg');
INSERT INTO `products` VALUES ('10002', '羊肉', '清风羊肉', 55, '2023001', '清风肉类食品有限公司', 9910, 100, 1, '库存充足', 'http://localhost:8090/mutton1.jpg');
INSERT INTO `products` VALUES ('10003', '羊肉', '新疆羔羊肉', 60, '2023456', '新疆源立食品有限公司', 10093, 100, 1, '库存充足', 'http://localhost:8090/lamb.jpg');
INSERT INTO `products` VALUES ('10004', '鸭肉', '道州灰鸭', 25, '2023004', '清风农产品供销社', 350, 500, 1, '正在补充库存', 'http://localhost:8090/duck.jpg');
INSERT INTO `products` VALUES ('10007', '海鲜冻品', '大青虾', 50, '2023054', '青岛远洋海鲜食品公司', 10000, 500, 3, '库存充足', 'http://localhost:8090/prawn.jpg');
INSERT INTO `products` VALUES ('1004', '蔬菜', '本地番茄', 4, '2023004', '清风农产品供销社', 1000, 50, 2, '库存充足', 'http://localhost:8090/tomato.jpg');
INSERT INTO `products` VALUES ('2023100', '鹅肉', '清风灰鹅', 12, '2023001', '清风肉类食品有限公司', 0, 500, 1, '正在补充库存', '');

-- ----------------------------
-- Table structure for productsearch
-- ----------------------------
DROP TABLE IF EXISTS `productsearch`;
CREATE TABLE `productsearch`  (
  `productID` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `searchTimes` int(0) NULL DEFAULT NULL,
  `month` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `staffID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `staffName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(0) NULL DEFAULT NULL,
  `status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`staffID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('202301', '小明', '123456', '男', 21, '正常工作', '2423779636@qq.com');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplierID` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `supplierName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `supplierKinds` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`supplierID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('2023001', '清风肉类食品有限公司', '牛肉,羊肉', '清风市解放路第34号', '3261584@qq.com');
INSERT INTO `supplier` VALUES ('2023004', '清风农产品供销社', '蔬菜', '清风农产品供销市场', '5288569@qq.com');
INSERT INTO `supplier` VALUES ('2023054', '青岛远洋海鲜食品公司', '各类海鲜', '青岛市崂山区秦岭路', '235615@qq.com');
INSERT INTO `supplier` VALUES ('2023456', '新疆源立肉类食品有限公司', '牛肉,羊肉,奶制品', '新疆源立农场', '3546123@qq.com');

-- ----------------------------
-- Table structure for turnover
-- ----------------------------
DROP TABLE IF EXISTS `turnover`;
CREATE TABLE `turnover`  (
  `productID` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Month_turnover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `month` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `warehouseID` int(0) NOT NULL AUTO_INCREMENT,
  `warehouseName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `function` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `temperature` double(255, 0) NULL DEFAULT NULL,
  PRIMARY KEY (`warehouseID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (1, '肉类存储仓库', '清风仓库园区第10栋', '存储新鲜肉类，能够最大程度保存新鲜度', -3);
INSERT INTO `warehouse` VALUES (2, '果蔬存储仓库', '清风仓库园区第1栋', '及时存储果蔬，锁住水分和新鲜度', 3);
INSERT INTO `warehouse` VALUES (3, '海鲜冻品存储仓库', '清风仓库园区第5栋', '超低温存储海鲜冻品', -24);
INSERT INTO `warehouse` VALUES (11111, '肉类仓库', '大庸桥街道', '....', 2);

SET FOREIGN_KEY_CHECKS = 1;
