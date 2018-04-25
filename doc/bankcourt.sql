/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : bankcourt

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-12-16 14:36:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_case_info
-- ----------------------------
DROP TABLE IF EXISTS `t_case_info`;
CREATE TABLE `t_case_info` (
  `ID` varchar(36) NOT NULL COMMENT '主键Id',
  `CASE_NUMBER` varchar(50) DEFAULT NULL COMMENT '案件号',
  `MONEY_NATURE_ID` varchar(36) DEFAULT NULL COMMENT '款项性质ID',
  `MONEY_NATURE_CODE` varchar(50) DEFAULT NULL COMMENT '款项性质编码',
  `MONEY_NATURE_NAME` varchar(50) DEFAULT NULL COMMENT '款项性质名称',
  `RECEIVABLE_AMOUNT` decimal(15,2) DEFAULT NULL COMMENT '应收金额',
  `ACTUAL_AMOUNT` decimal(15,2) DEFAULT NULL COMMENT '实收金额',
  `DIFF_AMOUNT` decimal(15,2) DEFAULT NULL COMMENT '差额',
  `PROCESS_TYPE_ID` varchar(36) DEFAULT NULL COMMENT '处理方式ID',
  `PROCESS_TYPE_NAME` varchar(50) DEFAULT NULL COMMENT '处理方式Name',
  `FORMMAKER_ID` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后修改时间',
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='个案档案表';

-- ----------------------------
-- Records of t_case_info
-- ----------------------------

-- ----------------------------
-- Table structure for t_court_pay_order
-- ----------------------------
DROP TABLE IF EXISTS `t_court_pay_order`;
CREATE TABLE `t_court_pay_order` (
  `ID` varchar(36) NOT NULL,
  `CASE_ID` varchar(36) DEFAULT NULL,
  `CASE_NUMBER` varchar(100) DEFAULT NULL,
  `CURRENCY` varchar(6) DEFAULT NULL,
  `DATE` varchar(50) DEFAULT NULL,
  `DETAIL_TITLE` varchar(100) DEFAULT NULL,
  `PAYMENT__DEPOSIT_BANK` varchar(50) DEFAULT NULL,
  `PAYMENT_NUMBER` varchar(50) DEFAULT NULL,
  `PAYMENT_NAME` varchar(50) DEFAULT NULL,
  `RECEIVABLES_DEPOSIT_BANK` varchar(50) DEFAULT NULL,
  `RECEIVABLES_PROVINCE` varchar(10) DEFAULT NULL,
  `RECEIVABLES_CITY` varchar(10) DEFAULT NULL,
  `RECEIVABLES_AREA_CODE` varchar(36) DEFAULT NULL,
  `RECEIVABLES_NUMBER` varchar(36) DEFAULT NULL,
  `RECEIVABLES_ACCOUNT_NAME` varchar(50) DEFAULT NULL,
  `AMOUNT` varchar(15) DEFAULT NULL,
  `REMITTANCE_PURPOSE` varchar(30) DEFAULT NULL,
  `REMARKS` varchar(200) DEFAULT NULL,
  `REMITTANCE_MODE` varchar(30) DEFAULT NULL,
  `RECEIVABLES_PHONE` varchar(15) DEFAULT NULL,
  `FORMMAKER_ID` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='法院支付单表';

-- ----------------------------
-- Records of t_court_pay_order
-- ----------------------------
INSERT INTO `t_court_pay_order` VALUES ('a6dsf', 'd5f6a78sdf7ads5f6a78sdf7', '测试用的案号00001', 'RMB', '20170804', '', '工行  ', '4402234029100075414', '成都市龙泉驿区人民法院', '成都农商银行', '四川', '成都', '0000', '021507030121010019047', '董万忠', '16,433.00', '退诉讼费', '', '0', '', '1111111111111111', 'admin', '2017-11-29 14:36:31', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for t_icbc_area_code
-- ----------------------------
DROP TABLE IF EXISTS `t_icbc_area_code`;
CREATE TABLE `t_icbc_area_code` (
  `ID` varchar(36) NOT NULL,
  `PROVINCE` varchar(10) DEFAULT NULL,
  `AREA` varchar(10) DEFAULT NULL,
  `AREA_CODE` varchar(10) DEFAULT NULL,
  `FORMMAKER_ID` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工行全国地区码表';

-- ----------------------------
-- Records of t_icbc_area_code
-- ----------------------------
INSERT INTO `t_icbc_area_code` VALUES ('1', '重庆', '重庆', '3100', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('10', '四川', '乐山', '2306', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('100', '贵州', '铜仁', '2408', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('101', '贵州', '兴义', '2409', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('102', '贵州', '遵义', '2403', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('103', '海南', '海南', '2201', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('104', '河北', '保定', '0409', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('105', '河北', '沧州', '0408', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('106', '河北', '承德', '0411', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('107', '河北', '邯郸', '0405', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('108', '河北', '衡水', '0407', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('109', '河北', '廊坊', '0410', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('11', '四川', '凉山', '2320', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('110', '河北', '秦皇岛', '0404', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('111', '河北', '省南', '0413', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('112', '河北', '省沙', '0414', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('113', '河北', '石家庄', '0402', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('114', '河北', '唐山', '0403', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('115', '河北', '邢台', '0406', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('116', '河北', '张家口', '0412', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('117', '河南', '安阳', '1706', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('118', '河南', '鹤壁', '1710', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('119', '河南', '焦作', '1709', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('12', '四川', '泸州', '2304', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('120', '河南', '开封', '1703', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('121', '河南', '洛阳', '1705', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('122', '河南', '漯河', '1711', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('123', '河南', '南阳', '1714', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('124', '河南', '平顶山', '1707', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('125', '河南', '濮阳', '1712', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('126', '河南', '三门峡', '1713', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('127', '河南', '商丘', '1716', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('128', '河南', '新乡', '1704', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('129', '河南', '信阳', '1718', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('13', '四川', '眉山', '2313', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('130', '河南', '许昌', '1708', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('131', '河南', '郑州', '1702', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('132', '河南', '周口', '1717', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('133', '河南', '驻马店', '1715', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('134', '黑龙江', '大庆', '0905', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('135', '黑龙江', '大兴安岭', '0914', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('136', '黑龙江', '哈尔滨', '3500', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('137', '黑龙江', '鹤岗', '0906', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('138', '黑龙江', '黑河', '0913', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('139', '黑龙江', '鸡西', '0907', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('14', '四川', '绵阳', '2308', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('140', '黑龙江', '佳木斯', '0904', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('141', '黑龙江', '牡丹江', '0903', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('142', '黑龙江', '七台河', '0910', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('143', '黑龙江', '齐齐哈尔', '0902', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('144', '黑龙江', '双鸭山', '0908', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('145', '黑龙江', '绥化', '0912', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('146', '黑龙江', '伊春', '0909', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('147', '湖北', '鄂州', '1811', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('148', '湖北', '恩施', '1817', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('149', '湖北', '黄冈', '1814', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('15', '四川', '南充', '2315', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('150', '湖北', '黄石', '1803', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('151', '湖北', '荆门', '1809', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('152', '湖北', '荆州', '1813', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('153', '湖北', '三峡', '1807', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('154', '湖北', '十堰', '1810', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('155', '湖北', '随州', '1805', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('156', '湖北', '武汉', '3202', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('157', '湖北', '咸宁', '1818', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('158', '湖北', '襄樊', '1804', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('159', '湖北', '孝感', '1812', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('16', '四川', '内江', '2307', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('160', '湖南', '常德', '1908', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('161', '湖南', '郴州', '1911', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('162', '湖南', '衡阳', '1905', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('163', '湖南', '怀化', '1914', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('164', '湖南', '娄底', '1913', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('165', '湖南', '邵阳', '1906', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('166', '湖南', '湘潭', '1904', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('167', '湖南', '湘西', '1915', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('168', '湖南', '益阳', '1912', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('169', '湖南', '永州', '1910', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('17', '四川', '攀枝花', '2302', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('170', '湖南', '岳阳', '1907', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('171', '湖南', '张家界', '1909', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('172', '湖南', '长沙', '1901', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('173', '湖南', '株洲', '1903', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('174', '吉林', '白城', '0805', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('175', '吉林', '白山', '0807', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('176', '吉林', '吉林', '0802', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('177', '吉林', '辽源', '0803', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('178', '吉林', '四平', '0804', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('179', '吉林', '松原', '0809', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('18', '四川', '遂宁', '2310', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('180', '吉林', '通化', '0806', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('181', '吉林', '延边', '0808', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('182', '吉林', '长春', '4200', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('183', '江苏', '常州', '1105', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('184', '江苏', '淮安', '1110', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('185', '江苏', '连云港', '1107', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('186', '江苏', '南京', '4301', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('187', '江苏', '南通', '1111', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('188', '江苏', '苏州', '1102', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('189', '江苏', '宿迁', '1116', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('19', '四川', '雅安', '2319', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('190', '江苏', '泰州', '1115', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('191', '江苏', '无锡', '1103', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('192', '江苏', '胥浦', '1117', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('193', '江苏', '徐州', '1106', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('194', '江苏', '盐城', '1109', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('195', '江苏', '扬州', '1108', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('196', '江苏', '镇江', '1104', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('197', '江西', '抚州', '1511', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('198', '江西', '赣州', '1510', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('199', '江西', '吉安', '1509', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('2', '重庆', '重庆市璧山县', '5001', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('20', '四川', '宜宾', '2314', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('200', '江西', '景德镇', '1503', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('201', '江西', '九江', '1507', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('202', '江西', '南昌', '1502', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('203', '江西', '萍乡', '1504', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('204', '江西', '上饶', '1512', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('205', '江西', '新余', '1505', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('206', '江西', '宜春', '1508', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('207', '江西', '鹰潭', '1506', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('208', '辽宁', '鞍山', '704', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('209', '辽宁', '本溪', '706', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('21', '四川', '自贡', '2303', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('210', '辽宁', '朝阳', '713', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('211', '辽宁', '丹东', '707', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('212', '辽宁', '抚顺', '705', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('213', '辽宁', '阜新', '710', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('214', '辽宁', '葫芦岛', '715', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('215', '辽宁', '锦州', '708', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('216', '辽宁', '辽阳', '711', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('217', '辽宁', '盘锦', '714', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('218', '辽宁', '沈阳', '3301', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('219', '辽宁', '铁岭', '712', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('22', '安徽', '安庆', '1309', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('220', '辽宁', '营口', '709', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('221', '内蒙', '阿盟', '614', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('222', '内蒙', '巴彦淖尔', '613', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('223', '内蒙', '包头', '603', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('224', '内蒙', '赤峰', '605', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('225', '内蒙', '鄂尔多斯', '612', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('226', '内蒙', '呼和浩特', '602', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('227', '内蒙', '呼伦贝尔', '607', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('228', '内蒙', '满洲里', '606', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('229', '内蒙', '通辽', '609', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('23', '安徽', '蚌埠', '1303', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('230', '内蒙', '乌海', '604', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('231', '内蒙', '乌兰察布', '611', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('232', '内蒙', '乌兰浩特', '608', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('233', '内蒙', '锡盟', '610', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('234', '宁波', '宁波', '3901', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('235', '宁夏', '固原', '2905', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('236', '宁夏', '宁夏本级', '2901', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('237', '宁夏', '石嘴山', '2904', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('238', '宁夏', '吴忠', '2903', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('239', '宁夏', '银川', '2902', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('24', '安徽', '巢湖', '1315', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('240', '青岛', '青岛', '3803', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('241', '青海', '海北', '2807', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('242', '青海', '海西', '2809', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('243', '青海', '黄南', '2810', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('244', '青海', '西宁', '2806', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('245', '青海', '玉树', '2811', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('246', '山东', '滨州', '1613', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('247', '山东', '德州', '1612', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('248', '山东', '东营', '1615', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('249', '山东', '菏泽', '1609', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('25', '安徽', '池州', '1316', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('250', '山东', '济南', '1602', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('251', '山东', '济宁', '1608', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('252', '山东', '莱芜', '1617', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('253', '山东', '聊城', '1611', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('254', '山东', '临沂', '1610', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('255', '山东', '日照', '1616', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('256', '山东', '泰安', '1604', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('257', '山东', '威海', '1614', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('258', '山东', '潍坊', '1607', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('259', '山东', '烟台', '1606', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('26', '安徽', '滁州', '1313', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('260', '山东', '枣庄', '1605', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('261', '山东', '淄博', '1603', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('262', '山西', '大同', '504', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('263', '山西', '晋城', '506', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('264', '山西', '离石', '509', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('265', '山西', '临汾', '510', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('266', '山西', '朔州', '507', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('267', '山西', '太原', '502', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('268', '山西', '忻州', '512', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('269', '山西', '阳泉', '503', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('27', '安徽', '阜阳', '1311', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('270', '山西', '榆次', '508', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('271', '山西', '运城', '511', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('272', '山西', '长治', '505', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('273', '陕西', '安康', '2607', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('274', '陕西', '宝鸡', '2603', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('275', '陕西', '汉中', '2606', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('276', '陕西', '商洛', '2608', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('277', '陕西', '铜川', '2602', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('278', '陕西', '渭南', '2605', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('279', '陕西', '西安', '3700', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('28', '安徽', '合肥', '1302', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('280', '陕西', '咸阳', '2604', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('281', '陕西', '延安', '2609', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('282', '陕西', '榆林', '2610', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('283', '上海', '上海', '1001', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('284', '深圳', '深圳', '4000', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('285', '天津', '天津', '302', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('286', '西藏', '西藏', '158', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('287', '西藏', '樟木口岸', '165', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('288', '厦门', '厦门', '4100', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('289', '新疆', '阿克苏', '3014', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('29', '安徽', '淮北', '1305', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('290', '新疆', '阿勒泰', '3008', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('291', '新疆', '巴州', '3010', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('292', '新疆', '博州', '3009', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('293', '新疆', '昌吉', '3004', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('294', '新疆', '哈密', '3011', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('295', '新疆', '和田', '3015', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('296', '新疆', '喀什', '3012', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('297', '新疆', '克拉玛依', '3003', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('298', '新疆', '马兰', '3017', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('299', '新疆', '石河子', '3016', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('3', '四川', '成都', '4402', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('30', '安徽', '淮南', '1304', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('300', '新疆', '塔城', '3007', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('301', '新疆', '吐鲁番', '3005', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('302', '新疆', '乌鲁木齐', '3002', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('303', '新疆', '伊犁', '3006', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('304', '云南', '版纳', '2509', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('305', '云南', '保山', '2510', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('306', '云南', '楚雄', '2516', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('307', '云南', '大理', '2515', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('308', '云南', '德宏', '2511', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('309', '云南', '红河', '2507', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('31', '安徽', '黄山', '1310', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('310', '云南', '昆明', '2502', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('311', '云南', '丽江', '2512', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('312', '云南', '临沧', '2518', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('313', '云南', '怒江', '2513', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('314', '云南', '普洱', '2508', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('315', '云南', '曲靖', '2505', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('316', '云南', '文山', '2506', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('317', '云南', '玉溪', '2517', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('318', '云南', '昭通', '2504', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('319', '云南', '中甸', '2514', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('32', '安徽', '六安', '1314', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('320', '浙江', '杭州', '1202', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('321', '浙江', '湖州', '1205', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('322', '浙江', '嘉兴', '1204', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('323', '浙江', '金华', '1208', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('324', '浙江', '丽水', '1210', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('325', '浙江', '衢州', '1209', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('326', '浙江', '绍兴', '1211', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('327', '浙江', '台州', '1207', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('328', '浙江', '温州', '1203', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('329', '浙江', '舟山', '1206', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('33', '安徽', '马鞍山', '1306', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('330', '浙江', '平湖', '5002', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('34', '安徽', '宿州', '1312', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('35', '安徽', '铜陵', '1308', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('36', '安徽', '芜湖', '1307', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('37', '安徽', '宣城', '1317', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('38', '北京', '北京', '0200', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('39', '大连', '大连', '3400', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('4', '四川', '巴中', '2318', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('40', '福建', '福州', '1402', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('41', '福建', '龙岩', '1410', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('42', '福建', '南平', '1406', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('43', '福建', '宁德', '1407', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('44', '福建', '莆田', '1405', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('45', '福建', '泉州', '1408', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('46', '福建', '三明', '1404', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('47', '福建', '漳州', '1409', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('48', '甘肃', '白银', '2704', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('49', '甘肃', '定西', '2707', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('5', '四川', '达州', '2317', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('50', '甘肃', '东风', '2720', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('51', '甘肃', '甘南', '2717', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('52', '甘肃', '合作', '2715', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('53', '甘肃', '嘉峪关', '2702', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('54', '甘肃', '金昌', '2705', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('55', '甘肃', '酒泉', '2713', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('56', '甘肃', '矿区', '2716', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('57', '甘肃', '兰州', '2703', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('58', '甘肃', '临夏', '2714', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('59', '甘肃', '陇南', '2711', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('6', '四川', '德阳', '2305', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('60', '甘肃', '平凉', '2708', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('61', '甘肃', '庆阳', '2709', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('62', '甘肃', '天水', '2706', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('63', '甘肃', '武威', '2710', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('64', '甘肃', '张掖', '2712', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('65', '广东', '潮州', '2004', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('66', '广东', '东莞', '2010', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('67', '广东', '佛山', '2013', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('68', '广东', '广东本级', '2001', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('69', '广东', '广州', '3602', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('7', '四川', '甘孜', '2321', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('70', '广东', '河源', '2006', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('71', '广东', '惠州', '2008', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('72', '广东', '江门', '2012', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('73', '广东', '揭阳', '2019', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('74', '广东', '茂名', '2016', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('75', '广东', '梅州', '2007', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('76', '广东', '清远', '2018', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('77', '广东', '汕头', '2003', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('78', '广东', '汕尾', '2009', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('79', '广东', '韶关', '2005', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('8', '四川', '广安', '2316', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('80', '广东', '阳江', '2014', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('81', '广东', '云浮', '2020', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('82', '广东', '湛江', '2015', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('83', '广东', '肇庆', '2017', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('84', '广东', '中山', '2011', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('85', '广东', '珠海', '2002', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('86', '广西', '百色', '2110', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('87', '广西', '北海', '2107', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('88', '广西', '桂林', '2103', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('89', '广西', '河池', '2114', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('9', '四川', '广元', '2309', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('90', '广西', '柳州', '2105', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('91', '广西', '南宁', '2102', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('92', '广西', '梧州', '2104', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('93', '广西', '玉林', '2111', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('94', '贵州', '安顺', '2404', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('95', '贵州', '毕节', '2406', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('96', '贵州', '都匀', '2405', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('97', '贵州', '贵阳', '2402', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('98', '贵州', '凯里', '2407', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');
INSERT INTO `t_icbc_area_code` VALUES ('99', '贵州', '六盘水', '2410', null, null, '2017-11-28 11:36:48', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for t_inter_pay
-- ----------------------------
DROP TABLE IF EXISTS `t_inter_pay`;
CREATE TABLE `t_inter_pay` (
  `ID` varchar(36) NOT NULL COMMENT '主键ID',
  `VOUCHER_NO` varchar(50) DEFAULT NULL COMMENT '凭证号',
  `LOCAL_NUMBER` varchar(50) DEFAULT NULL COMMENT '本方账号',
  `PARTY_NUMBER` varchar(50) DEFAULT NULL COMMENT '对方账号',
  `TRANSACTION_TIME` varchar(20) DEFAULT NULL COMMENT '交易时间',
  `BORROW_OR_LEND` varchar(5) DEFAULT NULL COMMENT '借/贷',
  `DEBIT_PRICE` varchar(20) DEFAULT NULL COMMENT '借方发生额',
  `CREDIT_PRICE` varchar(20) DEFAULT NULL COMMENT '贷方发生额',
  `PARTY_LINE_NUMBER` varchar(50) DEFAULT NULL COMMENT '对方行号',
  `ABSTRACT_INFO` varchar(200) DEFAULT NULL COMMENT '摘要',
  `PURPOSE` varchar(50) DEFAULT NULL COMMENT '用途',
  `CORPORATE_NAME` varchar(50) DEFAULT NULL COMMENT '对方单位名称',
  `BALANCE` varchar(20) DEFAULT NULL COMMENT '余额',
  `INDIVIDUATION` varchar(1000) DEFAULT NULL COMMENT '个性化信息',
  `CASE_ID` varchar(36) DEFAULT NULL COMMENT '个案ID',
  `CASE_NUMBER` varchar(50) DEFAULT NULL COMMENT '个案号',
  `SUBJECT_ID` varchar(36) DEFAULT NULL COMMENT '科目ID',
  `SUBJECT_NAME` varchar(50) DEFAULT NULL COMMENT '科目名称',
  `DEPARTMENT_NAME` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `UNDERTAKER` varchar(50) DEFAULT NULL COMMENT '承办人名',
  `IS_EDIT` int(1) DEFAULT '0' COMMENT '是否被编辑：\r\n            1是，\r\n            0否',
  `BATCH_NUMBER` varchar(36) DEFAULT NULL COMMENT '批次号',
  `FORMMAKER_ID` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL COMMENT '创建人名',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行网银支付流水信息';

-- ----------------------------
-- Records of t_inter_pay
-- ----------------------------
INSERT INTO `t_inter_pay` VALUES ('003f02e0b8ed45d0828cc7a608789c2c', '0', '4402234029100075414', '', '2017-09-22 14:43:28', '贷', '', '25.00', '00000', '诉讼费', '', '孙聃玫', '31,182,861.66', '贷方卡号： 9558854402005433654 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 孙聃玫 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('01dfb4a8143e43799c89eb933082e81e', '0', '4402234029100075414', '021507030121010019047', '2017-09-20 16:31:34', '借', '16,433.00', '', '00000', '退诉讼费', '', '董万忠', '30,245,055.67', '<附言>退诉讼费，转建行                                                                                                                                                                      </附言><支付交易序号>23545422</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-12</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', 'd5f6a78sdf7ads5f6a78sdf7', '测试用的案号00001', null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-29 15:42:00', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('040149cebd064c3990dd2f61560255cf', '0', '4402234029100075414', '6228480469742216978', '2017-09-20 16:31:32', '借', '130.00', '', '00000', '退诉讼费', '', '周洪琼', '30,265,792.67', '<附言>退诉讼费，转农行                                                                                                                                                                      </附言><支付交易序号>23545054</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-2</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('0523e38cb4e542998b26eb3351647e6d', '0', '4402234029100075414', '', '2017-09-22 14:43:29', '贷', '', '25.00', '00000', '诉讼费', '', '张文甫', '31,182,961.66', '贷方卡号： 9558854402005433753 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 张文甫 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('16719b58725946fb9e89aa57aabde2f4', '0', '4402234029100075414', '6228450460042435216', '2017-09-20 16:30:53', '借', '7,623.00', '', '00000', '退诉讼费', '', '曾贤忠', '30,305,194.67', '<附言>                                                                                                                                                                                      </附言><支付交易序号>23544434</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-21</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('17ebebf3019b478abd7d7544babdd4ab', '0', '4402234029100075414', '6212264402001530959', '2017-09-20 16:30:53', '借', '2,432.00', '', '00000', '退诉讼费', '退诉讼费', '成桂英', '30,312,817.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-20 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('180a52045ce148448efd54679227b957', '0', '4402234029100075414', '4402234019025920711', '2017-09-22 11:07:05', '贷', '', '345,658.00', '00000', '', '', '成都阳光投资建设有限公司', '31,067,196.66', '', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('19b8c1eeabae4ef28dc9ffcf566494ca', '0', '4402234029100075414', '6222084402008590003', '2017-09-20 16:30:53', '借', '1,789.00', '', '00000', '退诉讼费', '退诉讼费', '袁军', '30,335,086.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-13 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('19df9bf4077e41f0ba43d27e8be55ec0', '0', '4402234029100075414', '51001886508059181818', '2017-09-22 11:37:08', '贷', '', '9,128.00', '00000', '诉讼费', '', '四川华庭建设有限公司', '31,076,324.66', '<贷方卡号>9558854402005434017</贷方卡号><贷方卡名称>成都市龙泉驿区人民法院                                      </贷方卡名称><附言>诉讼费                                                                                                                                                                                </附言><支付交易序号>47562739</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-22</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('1c49be213c154c34bbe3c8eddff958e0', '0', '4402234029100075414', '', '2017-09-22 14:43:32', '贷', '', '25.00', '00000', '诉讼费', '', '邹太平', '31,183,211.66', '贷方卡号： 9558854402005433746 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 邹太平 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('1cc9f169b33242ec91338fd00953f5bc', '0', '4402234029100075414', '9153950019991001', '2017-09-22 17:12:37', '贷', '', '320,000.00', '00000', '龙海英放贷 有疑请查*', '', '临时存欠-支付业务/落地处理', '31,503,854.66', '贷方卡号： 9558854402000093487 贷方卡名称： 成都市龙泉驿区人民法院案款 付款户名： 临时存欠-支付业务/落地处理 收款户名： 成都市龙泉驿区人民法院案款 摘要： 龙海英放贷 有疑请查勿退', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('1d0d308345b24fadab2f1378037b1ee2', '0', '4402234029100075414', '51001556808050911849', '2017-09-20 16:30:52', '借', '4,063.00', '', '00000', '退诉讼费', '', '成都华气厚普机电设备股份有限公司', '30,342,575.67', '<附言>                                                                                                                                                                                      </附言><支付交易序号>23543464</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-4</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('1fc31a351a384c83b0d9afbb2d2bab0f', '0', '4402234029100075414', '6222024402004697086', '2017-09-20 16:30:54', '借', '4,848.00', '', '00000', '退诉讼费', '退诉讼费', '黄居彬', '30,285,262.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-23 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('262e380cbd1a402789071d5ba98323fe', '0', '4402234029100075414', '6217003810016277322', '2017-09-22 15:18:28', '贷', '', '10.00', '00000', '赵忠明5862', '', '张明亮', '31,183,516.66', '贷方卡号： 9558854402005149532 贷方卡名称： 成都市龙泉驿区人民法院 附言： 赵忠明5862 支付交易序号： 27001715 报文种类： IBP101网银贷记业务报文 委托日期： 2017-09-22 业务种类： 汇兑', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('288c11e1a8e046e3b0d4cfd7b2b16c0b', '0', '4402234029100075414', '1781387203', '2017-09-21 15:27:50', '贷', '', '12,171.00', '00000', '（2017）川0112民初56', '', '沃尔沃建筑设备投资（中国）有限公司', '30,478,316.53', '<贷方卡号>9558854402005152601</贷方卡号><贷方卡名称>成都市龙泉驿区人民法院                                      </贷方卡名称><附言>（2017）川0112民初5665号                                                                                                                                                                  </附言><支付交易序号>41634449</支付交易序号><报文种类>大额客户发起汇兑业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('295580e3850f41e8a0b74ee438e0d9da', '0', '4402234029100075414', '6217003810058770648', '2017-09-20 16:31:39', '借', '6,400.00', '', '00000', '退诉讼费', '', '张玉平', '30,238,655.67', '<附言>退诉讼费，转建行                                                                                                                                                                      </附言><支付交易序号>23545548</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-19</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('295fe3ab7f8c4121abf9ec84316199e3', '0', '4402234029100075414', '44022300112001431', '2017-09-20 01:57:33', '贷', '', '89,777.50', '00000', '0440292110028', '', '', '28,711,982.93', '贷方卡号： 9558854402000093495 卡名称： 成都市龙泉驿区人民法院诉讼费', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('2be1ced26bd14849845ed0fea0ed9440', '0', '4402234029100075414', '6217003810036131970', '2017-09-21 15:38:06', '借', '321,301.00', '', '00000', '付案款', '', '黄平', '30,147,216.53', '<附言>付案款，转建行                                                                                                                                                                            </附言><支付交易序号>28108140</支付交易序号><报文种类>大额客户发起汇兑业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322135718-5</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('2eec09483dae489bb0a3b19a8418e0c0', '0', '4402234029100075414', '6212264402032818720', '2017-09-20 14:49:06', '贷', '', '9,192.00', '00000', '网转', '', '谢枭', '30,345,902.17', '贷方卡号： 9558854402005152486 贷方卡名称： 成都市龙泉驿区人民法院 汇划种类： 汇兑 客户附言：  用途：', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('2f17fce512444d8e988142f049f753f5', '0', '4402234029100075414', '', '2017-09-22 14:43:32', '贷', '', '25.00', '00000', '诉讼费', '', '周强', '31,183,261.66', '贷方卡号： 9558854402005433787 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 周强 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('3036f67d613a4abdbeb3c5dd534c999a', '0', '4402234029100075414', '', '2017-09-22 14:43:33', '贷', '', '25.00', '00000', '诉讼费', '', '廖新', '31,183,336.66', '贷方卡号： 9558854402005433837 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 廖新 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('320c6592db6446c390c7d4753b8b7d43', '0', '4402234029100075414', '0215000001464188010001', '2017-09-21 15:37:30', '借', '14,790.00', '', '00000', '付案款', '', '成都农村商业银行股份有限公司龙泉驿支行', '30,468,517.53', '<附言>                                                                                                                                                                                      </附言><支付交易序号>25525274</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322135718-6</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('35e07affbf074b23baba8560c152964e', '0', '4402234029100075414', '', '2017-09-22 14:43:32', '贷', '', '25.00', '00000', '诉讼费', '', '梁树美', '31,183,286.66', '贷方卡号： 9558854402005433779 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 梁树美 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('364d4d4482864df6b37ab2fffee8aad8', '0', '4402234029100075414', '2014014430122524', '2017-09-21 17:26:18', '贷', '', '1,222.00', '00000', '司法扣划', '', '袁宏贵', '30,156,886.53', '<附言>司法扣划                                                                                                                                                                              </附言><支付交易序号>24021238</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-22</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('36ab95e1ee8542b58bb0219d08e3f667', '0', '4402234029100075414', '', '2017-09-22 14:43:29', '贷', '', '25.00', '00000', '诉讼费', '', '黄建国', '31,183,011.66', '贷方卡号： 9558854402005433563 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 黄建国 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('38af8e3ce01740989586c6cbdb6a3931', '0', '4402234029100075414', '6228480469613296976', '2017-09-20 16:32:37', '借', '50.00', '', '00000', '退诉讼费', '', '张嘉洪', '29,734,379.17', '<附言>退诉讼费，转农行                                                                                                                                                                      </附言><支付交易序号>23548956</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-6</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('494c0d24d3a3411fb56c851b7e369c4f', '0', '4402234029100075414', '', '2017-09-22 14:43:31', '贷', '', '25.00', '00000', '诉讼费', '', '刘丽', '31,183,186.66', '贷方卡号： 9558854402005433738 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 刘丽 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('49953df3ab404672a27a31b4e5a65748', '0', '4402234029100075414', '6215584402011919461', '2017-09-20 16:30:53', '借', '6,437.00', '', '00000', '退诉讼费', '退诉讼费', '杨杰', '30,298,757.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-18 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('4c5e9a44ce354743aa224796029f3c61', '0', '4402234029100075414', '', '2017-09-22 14:43:30', '贷', '', '25.00', '00000', '诉讼费', '', '李恭素', '31,183,061.66', '贷方卡号： 9558854402005433589 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 李恭素 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('4c61705a87b14ca0b3ba94188e5ddc41', '0', '4402234029100075414', '6222084402013071676', '2017-09-20 16:30:53', '借', '5,700.00', '', '00000', '退诉讼费', '退诉讼费', '巫霞', '30,336,875.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-8 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('4cbc16043eef4a669912711b90ad65eb', '0', '4402234029100075414', '', '2017-09-22 14:43:31', '贷', '', '25.00', '00000', '诉讼费', '', '周木根', '31,183,136.66', '贷方卡号： 9558854402005433712 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 周木根 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('4ef29731e1f74db38f403e4609a95f3a', '0', '4402234029100075414', '6222084402002382258', '2017-09-20 16:30:53', '借', '9,880.00', '', '00000', '退诉讼费', '退诉讼费', '章旭明', '30,322,306.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-10 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('4f0e4d29050c4dd1b8c3288289ec5bda', '0', '4402234029100075414', '', '2017-09-22 14:43:30', '贷', '', '25.00', '00000', '诉讼费', '', '张同良', '31,183,036.66', '贷方卡号： 9558854402005433571 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 张同良 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('4f6636ff85e9473aa44937ebe5cb7069', '0', '4402234029100075414', '20351011200100000023881', '2017-09-20 14:26:07', '贷', '', '5,000.00', '00000', '其他诉讼费', '', '中国农业发展银行成都市龙泉驿区支行', '30,336,710.17', '<贷方卡号>9558854402005433951</贷方卡号><贷方卡名称>成都市龙泉驿区人民法院                                      </贷方卡名称><附言>其他诉讼费                                                                                                                                                                            </附言><支付交易序号>15656181</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-20</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('50abb74d82e94cdf8005abc76cb4a29f', '0', '4402234029100075414', '6212264402053235218', '2017-09-20 16:31:49', '贷', '', '4,405.00', '00000', '网转', '', '张荣', '30,235,660.67', '贷方卡号： 9558854402005152833 贷方卡名称： 成都市龙泉驿区人民法院 汇划种类： 汇兑 客户附言：  用途：', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('54515487759e4492b09ec3de9e7dda7d', '0', '4402234029100075414', '', '2017-09-22 14:43:31', '贷', '', '25.00', '00000', '诉讼费', '', '杨子江', '31,183,161.66', '贷方卡号： 9558854402005433720 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 杨子江 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('5496ee5a4c9d4cd4a1b44bb515cea83c', '0', '4402234029100075414', '', '2017-09-22 14:43:35', '贷', '', '25.00', '00000', '诉讼费', '', '董镇辉', '31,183,486.66', '贷方卡号： 9558854402005433597 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 董镇辉 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('576173a20f5f4f228347f75fb14912de', '0', '4402234029100075414', '00000123937025636', '2017-09-21 12:34:19', '贷', '', '3,400.00', '00000', '跨行', '', '强制扣划 邓长丽', '30,426,019.03', '<附言>                                                                                                                                                                                      </附言><支付交易序号> 3000850</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('59e0335948344593b169983f606a5c92', '0', '4402234029100075414', '6222084402025156119', '2017-09-20 16:30:54', '借', '2,375.00', '', '00000', '退诉讼费', '退诉讼费', '邱修全', '30,265,922.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-28 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('5d9960342e3c499a8b1c765cc494c185', '0', '4402234029100075414', '', '2017-09-22 14:43:28', '贷', '', '25.00', '00000', '诉讼费', '', '广鑫', '31,182,886.66', '贷方卡号： 9558854402005433662 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 广鑫 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('617e92fb958d4261b35007556e26fd39', '0', '4402234029100075414', '6222024402052077553', '2017-09-20 16:30:54', '借', '1,850.00', '', '00000', '退诉讼费', '退诉讼费', '陈廷英', '30,268,297.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-26 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('67703801c7bb446c8939e43757ac0701', '0', '4402234029100075414', '', '2017-09-22 14:43:27', '贷', '', '25.00', '00000', '诉讼费', '', '岳启国', '31,182,836.66', '贷方卡号： 9558854402005433647 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 岳启国 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('67b37fa0a6f84e7b9bb6c94c2814003c', '0', '4402234029100075414', '9153800019991001', '2017-09-21 17:34:40', '贷', '', '1,230.00', '00000', '刘金国汽车分期逾期，', '', '临时存欠 - 支付业务 / 落地处理', '30,158,116.53', '<贷方卡号>9558854402005433860</贷方卡号><贷方卡名称>成都市龙泉驿区人民法院                                      </贷方卡名称><附言>刘金国汽车分期逾期，垫付诉讼费6259065105134488                                                                                                                                        </附言><支付交易序号> 3287527</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-22</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('685a264e2d054ed59f3598878410b6b6', '0', '4402234029100075414', '6217863100002331344', '2017-09-20 10:39:03', '贷', '', '11,723.24', '00000', '案款', '', '邓利平', '28,873,956.17', '附言： 案款 支付交易序号： 97086674 报文种类： IBP101网银贷记业务报文 委托日期： 2017-09-20 业务种类： 汇兑', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('6b8907f311dc40b2a094c95a4162dc76', '0', '4402234029100075414', '6221534820000438954', '2017-09-20 12:16:26', '贷', '', '1,484.00', '00000', '案号（2017）川0112执', '', '黄凯', '29,711,160.17', '附言： 案号（2017）川0112执恢302号 支付交易序号： 43211800 报文种类： IBP101网银贷记业务报文 委托日期： 2017-09-20 业务种类： 汇兑', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('6e023feccba24546bdb6511acfa29736', '0', '4402234029100075414', '', '2017-09-22 14:43:31', '贷', '', '25.00', '00000', '诉讼费', '', '许平', '31,183,111.66', '贷方卡号： 9558854402005433704 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 许平 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('6ef5c87c414f4dbf955e15ed5b135a30', '0', '4402234029100075414', '9558824402002059855', '2017-09-21 13:50:44', '贷', '', '4,000.00', '00000', '货款', '货款', '张佐明', '30,466,145.53', '汇划种类： 汇兑 客户附言：  用途： 货款', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('6f93d835aa154aae8c1ff1fc639e71bb', '0', '4402234029100075414', '', '2017-09-22 14:43:29', '贷', '', '25.00', '00000', '诉讼费', '', '黄关琼', '31,182,986.66', '贷方卡号： 9558854402005433555 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 黄关琼 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('7404fb51ba3e46c28645bfed69163ed9', '0', '4402234029100075414', '021501000120150000022', '2017-09-20 12:02:38', '贷', '', '2,720.00', '00000', '诉讼深圳洲际公司成都', '', '成都市龙泉驿区人民政府龙泉街道办事处', '29,709,676.17', '贷方卡号： 9558854402005152536 贷方卡名称： 成都市龙泉驿区人民法院 付款户名： 成都市龙泉驿区人民政府龙泉街道办事处 收款户名： 成都市龙泉驿区人民法院 摘要： 诉讼深圳洲际公司成都分公司经济纠纷其他诉讼费', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('757c7191ff7e4dd38d4126b1000c99ff', '0', '4402234029100075414', '44022300112001431', '2017-09-22 02:22:00', '贷', '', '67,296.00', '00000', '0440292110028', '', '', '30,615,412.53', '贷方卡号： 9558854402000093495 卡名称： 成都市龙泉驿区人民法院诉讼费', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('7ee3543d10c6436a90315ad7c9edfb04', '0', '4402234029100075414', '44022300112001431', '2017-09-21 02:22:06', '贷', '', '64,439.00', '00000', '0440292110028', '', '', '29,815,619.03', '贷方卡号： 9558854402000093495 卡名称： 成都市龙泉驿区人民法院诉讼费', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('7f4920076ebe42efbde5b82fecd53824', '0', '4402234029100075414', '', '2017-09-22 14:43:34', '贷', '', '25.00', '00000', '诉讼费', '', '张生', '31,183,461.66', '贷方卡号： 9558854402005433621 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 张生 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('7f6c13dbc07041a992ec134d688a1147', '0', '4402234029100075414', '6212264402059860431', '2017-09-20 16:30:53', '借', '2,900.00', '', '00000', '退诉讼费', '退诉讼费', '佘建军', '30,332,186.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-9 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('820717b8d3de440c95830385d451c809', '0', '4402234029100075414', '6222083002005559448', '2017-09-20 16:30:52', '借', '1,350.00', '', '00000', '退诉讼费', '退诉讼费', '田赵晨阳', '30,346,638.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0300200100 指令编号： HQF322128764-7 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('83073b0b11e44279b6ebb2f0564281f1', '40384362', '4402234029100075414', '1001281229006631066', '2017-09-22 09:29:12', '贷', '', '105,866.13', '01001', '暂未上牌30350042212', '货款', '快钱支付清算信息有限公司客户备付金', '30,721,278.66', '客户附言： 暂未上牌30350042212 用途： 货款 汇出行： 0100102812 汇出行名称： 工行上海浦东开发区支行 汇入行： 0440202340', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('83a7879a2abc410f8d50f90eaf759234', '0', '4402234029100075414', '', '2017-09-20 10:07:37', '贷', '', '250.00', '00000', '赡养费', '', '成惠菊', '28,862,232.93', '缴款人： 成惠菊 券别： 100.00 张数： 2 券别： 1.00 张数： 50', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('85bcc549fd5a4cd2a5f47dbd963dd1f2', '0', '4402234029100075414', '00000122602628611', '2017-09-21 12:34:19', '贷', '', '36,126.50', '00000', '(2017)川0112执恢148', '', '强制扣划 王德满', '30,462,145.53', '<附言>(2017)川0112执恢148号                                                                                                                                                                 </附言><支付交易序号> 2985896</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('8bf8d6f08c1149a68a4efba21d7ebf22', '0', '4402234029100075414', '9153980019991001', '2017-09-21 19:21:06', '贷', '', '390,000.00', '00000', '伍聂华', '', '临时存欠-支付业务/落地处理', '30,548,116.53', '贷方卡号： 9558854402000093487 贷方卡名称： 成都市龙泉驿区人民法院案款 付款户名： 临时存欠-支付业务/落地处理 收款户名： 成都市龙泉驿区人民法院案款 摘要： 伍聂华', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('8d6f8bc03287495c95c1020948e41faf', '0', '4402234029100075414', '1781387203', '2017-09-21 15:27:50', '贷', '', '12,991.00', '00000', '（2017）川0112民初56', '', '沃尔沃建筑设备投资（中国）有限公司', '30,491,307.53', '<贷方卡号>9558854402005152619</贷方卡号><贷方卡名称>成都市龙泉驿区人民法院                                      </贷方卡名称><附言>（2017）川0112民初5664号                                                                                                                                                                  </附言><支付交易序号>41634452</支付交易序号><报文种类>大额客户发起汇兑业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('8e28bc25b36949d7a22005481f4ed8e6', '0', '4402234029100075414', '6217003810016277322', '2017-09-22 15:11:08', '贷', '', '10.00', '00000', '胡刚5860', '', '张明亮', '31,183,496.66', '贷方卡号： 9558854402005149516 贷方卡名称： 成都市龙泉驿区人民法院 附言： 胡刚5860 支付交易序号： 26850442 报文种类： IBP101网银贷记业务报文 委托日期： 2017-09-22 业务种类： 汇兑', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('8edc16b0774d4fff956165d9a5ec59dd', '0', '4402234029100075414', '6217003810016277322', '2017-09-22 15:15:56', '贷', '', '10.00', '00000', '张天晴5861', '', '张明亮', '31,183,506.66', '贷方卡号： 9558854402005149524 贷方卡名称： 成都市龙泉驿区人民法院 附言： 张天晴5861 支付交易序号： 26949602 报文种类： IBP101网银贷记业务报文 委托日期： 2017-09-22 业务种类： 汇兑', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('8efde994aeff4e45b7abf76ca6a9aa94', '0', '4402234029100075414', '6222084402025150385', '2017-09-20 16:30:54', '借', '7,000.00', '', '00000', '退诉讼费', '退诉讼费', '谢谨隆', '30,278,262.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-24 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('90f57ac012644bd18038c8c10b23d83c', '0', '4402234029100075414', '', '2017-09-22 14:43:27', '贷', '', '25.00', '00000', '诉讼费', '', '苏博', '31,182,811.66', '贷方卡号： 9558854402005433613 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 苏博 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('927734ea4ad2433f9eec0b6ae6811379', '0', '4402234029100075414', '', '2017-09-22 14:43:28', '贷', '', '25.00', '00000', '诉讼费', '', '陈浩', '31,182,911.66', '贷方卡号： 9558854402005433670 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 陈浩 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('92964c68c8054ef6818764e4765608d8', '0', '4402234029100075414', '', '2017-09-22 14:43:34', '贷', '', '25.00', '00000', '诉讼费', '', '丁以全', '31,183,411.66', '贷方卡号： 9558854402005433803 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 丁以全 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('9ac3c2e8d06545b7b0b7b5dc33f8dca8', '0', '4402234029100075414', '6228460460012679619', '2017-09-20 16:30:54', '借', '8,115.00', '', '00000', '退诉讼费', '', '黄仁水', '30,270,147.67', '<附言>                                                                                                                                                                                      </附言><支付交易序号>23543476</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-27</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('9c3badfe2ac9408292aa3432a2bfb86b', '0', '4402234029100075414', '', '2017-09-22 14:43:33', '贷', '', '25.00', '00000', '诉讼费', '', '黄昀', '31,183,361.66', '贷方卡号： 9558854402005433829 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 黄昀 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('9e7cfb2dc86f40588e896e5ccf7ad7ba', '0', '4402234029100075414', '', '2017-09-22 14:43:27', '贷', '', '25.00', '00000', '诉讼费', '', '彭开', '31,182,786.66', '贷方卡号： 9558854402005433605 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 彭开 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('9f81218dc86a4d0f8ead44080a2d0602', '0', '4402234029100075414', '6217003810045417766', '2017-09-20 13:57:23', '贷', '', '550.00', '00000', '跨行转出', '', '陈诚', '30,331,710.17', '贷方卡号： 9558854402005433852 贷方卡名称： 成都市龙泉驿区人民法院 附言： 跨行转出 支付交易序号： 65465914 报文种类： IBP101网银贷记业务报文 委托日期： 2017-09-20 业务种类： 汇兑', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('a036c902cb434a9c91a8cc598e472929', '0', '4402234029100075414', '', '2017-09-22 14:43:29', '贷', '', '25.00', '00000', '诉讼费', '', '宇驰洋', '31,182,936.66', '贷方卡号： 9558854402005433688 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 宇驰洋 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('a3bfc6786376458ca0385c427bf66907', '0', '4402234029100075414', '6222024402041552450', '2017-09-21 15:37:29', '借', '8,000.00', '', '00000', '付案款', '付案款', '赖福光', '30,483,307.53', '用途： 付案款 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322135718-3 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('a40bcc201e0a4890a4f62f52e80852df', '0', '4402234029100075414', '6222084402024659568', '2017-09-20 16:30:53', '借', '7,057.00', '', '00000', '退诉讼费', '退诉讼费', '杨洪宣', '30,315,249.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-15 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('b13369b3ba784b04abe54c39e3e239c8', '0', '4402234029100075414', '6223450060000073644', '2017-09-20 16:31:49', '借', '1,630.00', '', '00000', '退诉讼费', '', '毛丽君', '30,231,255.67', '<附言>退诉讼费，转成都农商银行                                                                                                                                                              </附言><支付交易序号>23546474</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-14</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('b7e7bfa5a4ae4e2b8a0c336398aabc0e', '0', '4402234029100075414', '6217903100008835415', '2017-09-20 11:34:42', '贷', '', '833,000.00', '00000', '宋宇拍花香龙都5-1-14', '', '宋宇', '29,706,956.17', '贷方卡号： 9558854402000093487 贷方卡名称： 成都市龙泉驿区人民法院案款 付款户名： 宋宇 收款户名： 成都市龙泉驿区人民法院案款 摘要： 宋宇拍花香龙都5-1-1402号尾款', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('ba100d040a504a74ab3122c05dd2a72c', '0', '4402234029100075414', '6223450010049582659', '2017-09-21 15:39:08', '借', '6,500.00', '', '00000', '付案款', '', '何淑华', '30,139,864.53', '<附言>付案款，转成都农商银行                                                                                                                                                                </附言><支付交易序号>25549260</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322135718-2</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('bad32fbe27a047ef8045cee6db1d6a7f', '0', '4402234029100075414', '', '2017-09-22 10:35:01', '贷', '', '260.00', '00000', '川0112民初5844', '', '陈双全', '30,721,538.66', '缴款人： 陈双全 券别： 100.00 张数： 3 券别： 10.00 张数： -4.0', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('bb99b634bbc24bca99d1e3c9816af562', '0', '4402234029100075414', '6216663100001220567', '2017-09-20 16:09:52', '贷', '', '2,670.00', '00000', '保全费（有疑请查，勿', '', '李俊杰', '30,348,572.17', '<贷方卡号>9558854402005152759</贷方卡号><贷方卡名称>成都市龙泉驿区人民法院                                      </贷方卡名称><附言>保全费（有疑请查，勿退）                                                                                                                                                              </附言><支付交易序号> 2577267</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-20</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('bde6103118d84d78bdd25bcb0ef058cb', '0', '4402234029100075414', '', '2017-09-22 14:43:34', '贷', '', '25.00', '00000', '诉讼费', '', '付嘉', '31,183,386.66', '贷方卡号： 9558854402005433811 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 付嘉 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('bf6d836382b24457a635447b2fd6b745', '0', '4402234029100075414', '', '2017-09-21 02:09:44', '贷', '', '16,800.86', '00000', '利息', '', '', '29,751,180.03', '起息日期： 2017-06-21 止息日期： 2017-09-20 利率： 0.300000% 利息： 16', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('c0d7c7eef22e48ea92c711a101341a7d', '0', '4402234029100075414', '511620011018160054182', '2017-09-22 14:11:22', '贷', '', '3,137.00', '00000', '支诉讼费', '', '四川省医学科学院附属医院', '31,079,461.66', '<贷方卡号>9558854402005434116</贷方卡号><贷方卡名称>成都市龙泉驿区人民法院                                      </贷方卡名称><附言>支诉讼费                                                                                                                                                                              </附言><支付交易序号>92122242</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-22</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('c4398d3e8527435082b48a6016677ae9', '0', '4402234029100075414', '6232813800001063562', '2017-09-20 16:32:15', '借', '500,000.00', '', '00000', '付案款，2013武侯民初', '', '成都市武侯区人民法院诉讼费及案款专户', '29,735,660.67', '<附言>付案款，2013武侯民初字第1654号，转建行                                                                                                                                                    </附言><支付交易序号>26538660</支付交易序号><报文种类>大额客户发起汇兑业务</报文种类><委托日期>2017-09-20</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-1</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('c4945acc919a4ddda1e4b8e934a52e51', '0', '4402234029100075414', '580631550000015', '2017-09-21 15:46:33', '贷', '', '40,800.00', '00000', '案件受理费(网银汇款,', '', '成都恩德建筑工程有限公司', '30,155,664.53', '<贷方卡号>9558854402005434009</贷方卡号><贷方卡名称>成都市龙泉驿区人民法院                                      </贷方卡名称><附言>案件受理费(网银汇款', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('c9b40342f9ef43a5bb1059d97922f408', '0', '4402234029100075414', '6212264402016281259', '2017-09-20 16:30:54', '借', '3,650.00', '', '00000', '退诉讼费', '退诉讼费', '张华秀', '30,290,110.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-17 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('cc37b5b74d33425f904e2d823e2c3dce', '0', '4402234029100075414', '6228480468869141175', '2017-09-20 16:31:34', '借', '4,304.00', '', '00000', '退诉讼费', '', '江伟', '30,261,488.67', '<附言>退诉讼费，转农行                                                                                                                                                                      </附言><支付交易序号>23545778</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-25</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('d0a8900387c34afbb62ef1132bc002c9', '0', '4402234029100075414', '6228480461979415712', '2017-09-21 15:39:06', '借', '852.00', '', '00000', '退诉讼费', '', '刘安奎', '30,146,364.53', '<附言>退诉讼费，转农行                                                                                                                                                                      </附言><支付交易序号>25547598</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322135718-1</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('d2fb5d51c7b847b0a7bb5e2c54bb2fa7', '0', '4402234029100075414', '', '2017-09-22 14:43:33', '贷', '', '25.00', '00000', '诉讼费', '', '宁望秀', '31,183,311.66', '贷方卡号： 9558854402005433761 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 宁望秀 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('d34b95c4c8c046d69d78710154788c58', '0', '4402234029100075414', '6228450468032908076', '2017-09-20 12:32:37', '贷', '', '620,000.00', '00000', '跨行', '', '伍聂华', '30,331,160.17', '<贷方卡号>9558854402000093487</贷方卡号><贷方卡名称>成都市龙泉驿区人民法院案款                                  </贷方卡名称><附言>                                                                                                                                                                                          </附言><支付交易序号>23668330</支付交易序号><报文种类>大额客户发起汇兑业务</报文种类><委托日期>2017-09-20</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('d8dc65e7a5994e4bb9e2d65472e969c7', '0', '4402234029100075414', '6228450468013902478', '2017-09-22 15:28:55', '贷', '', '338.00', '00000', '初５５３４', '', '张文力', '31,183,854.66', '贷方卡号： 9558854402005154250 贷方卡名称： 成都市龙泉驿区人民法院 附言： 初５５３４ 支付交易序号： 27082842 报文种类： IBP101网银贷记业务报文 委托日期： 2017-09-22 业务种类： 其他', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('db11635fc2a143f9ab4d6e1536be4c1c', '0', '4402234029100075414', '6217993380001404937', '2017-09-20 16:31:47', '借', '2,975.00', '', '00000', '退诉讼费', '', '朱建刚', '30,232,885.67', '<附言>退诉讼费，转邮政储蓄银行                                                                                                                                                              </附言><支付交易序号>23546446</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-11</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('dc7bab27fcbf4935ada066f0af0e3cfa', '0', '4402234029100075414', '2000353822000128', '2017-09-22 14:32:04', '贷', '', '103,300.00', '00000', '扣留借款', '', '恒大地产集团南充有限公司', '31,182,761.66', '付款户名： 恒大地产集团南充有限公司 收款户名： 成都市龙泉驿区人民法院 摘要： 扣留借款', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('dd524dba29fd4f0f8d1ca5c68b9608c6', '0', '4402234029100075414', '6228480461543420818', '2017-09-20 16:32:30', '借', '1,231.50', '', '00000', '退诉讼费', '', '刘治林', '29,734,429.17', '<附言>退诉讼费，转农行                                                                                                                                                                      </附言><支付交易序号>23548880</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-5</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('e62548bf8ca34fd8b005166451ecaa3c', '0', '4402234029100075414', '6228480468291046075', '2017-09-20 16:31:43', '借', '2,795.00', '', '00000', '退诉讼费', '', '廖君成', '30,235,860.67', '<附言>退诉讼费，转农行                                                                                                                                                                      </附言><支付交易序号>23546404</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322128764-22</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('ed6a94b39197454bab276847980d15e7', '0', '4402234029100075414', '6222082402003849653', '2017-09-21 12:05:30', '贷', '', '607,000.00', '00000', '网转', '', '龙海英', '30,422,619.03', '贷方卡号： 9558854402000093487 贷方卡名称： 成都市龙泉驿区人民法院案款 汇划种类： 汇兑 客户附言： 龙海英，竟买号B4565，桂王桥东街2号1栋4单元4层7号 用途：', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('eef352e4966746ce872bd6525701ab28', '0', '4402234029100075414', '6222084402024659568', '2017-09-20 16:30:54', '借', '4,997.00', '', '00000', '退诉讼费', '退诉讼费', '杨洪宣', '30,293,760.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-16 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('efe15da5496d4c9d9a9b57c181f2c7b8', '0', '4402234029100075414', '', '2017-09-22 14:43:34', '贷', '', '25.00', '00000', '诉讼费', '', '李勇', '31,183,436.66', '贷方卡号： 9558854402005433795 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 李勇 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('f88d3f229ded454daa988a8b578147a6', '0', '4402234029100075414', '', '2017-09-22 14:43:30', '贷', '', '25.00', '00000', '诉讼费', '', '王兴瑜', '31,183,086.66', '贷方卡号： 9558854402005433696 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 王兴瑜 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('faeb3e25bb9d433f84f574376e03d7aa', '0', '4402234029100075414', '6217996580002309897', '2017-09-21 15:40:13', '借', '25,000.00', '', '00000', '付案款', '', '李光志', '30,114,864.53', '<附言>付案款，转邮政储蓄银行                                                                                                                                                                </附言><支付交易序号>25565436</支付交易序号><报文种类>小额客户发起普通贷记业务</报文种类><委托日期>2017-09-21</委托日期><业务类型(种类)>普通汇兑                      </业务类型(种类)><指令编号>HQF322135718-4</指令编号><提交人>LQFY01.c.4402</提交人><最终授权人>LQFY02.c.4402</最终授权人>', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('fbe0fb2dc05341958f1901a8b36fff7c', '0', '4402234029100075414', '', '2017-09-22 14:43:32', '贷', '', '25.00', '00000', '诉讼费', '', '梁鹏', '31,183,236.66', '贷方卡号： 9558854402005433639 贷方卡名称： 成都市龙泉驿区人民法院 缴款人： 梁鹏 券别： 100.00 张数： 7 券别： 10.00 张数： 2 券别： 5.00 张数： 1', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('fd086e1fb295426a86e2192ef342e591', '0', '4402234029100075414', '6212264402007502671', '2017-09-20 16:30:52', '借', '583.50', '', '00000', '退诉讼费', '退诉讼费', '刘万儒', '30,347,988.67', '用途： 退诉讼费 汇出行： 0440202680 汇出行名称： 工行四川省分行营业部业务处理中心 汇入行： 0440202680 指令编号： HQF322128764-3 提交人： LQFY01.c.4402 最终授权人： LQFY02.c.4402', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');
INSERT INTO `t_inter_pay` VALUES ('ff8f26c7efef4ddaa525479a9b461c6a', '82760828', '4402234029100075414', '1001278619005510123', '2017-09-20 06:02:49', '贷', '', '150,000.00', '01001', '张兴翱,2017川0', '往来款', '支付宝（中国）网络技术有限公司客户备付金', '28,861,982.93', '贷方卡号： 9558854402000093487 贷方卡名称： 成都市龙泉驿区人民法院案款 客户附言： 张兴翱', null, null, null, null, null, null, '0', '20171126152704', '111111111111111111', 'admin', '2017-11-26 15:27:04', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `ID` varchar(36) NOT NULL,
  `PERMISSION_NAME` varchar(30) DEFAULT NULL,
  `PERMISSION_CODE` varchar(30) DEFAULT NULL,
  `ENABLE_STATUS` int(1) DEFAULT NULL,
  `COMMENTS` varchar(100) DEFAULT NULL,
  `FORMMAKER_ID` varchar(36) DEFAULT NULL,
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('111111111111111111', '用户创建', '*:*', '1', '创建用户权限', 'admin', 'admin', '2017-12-16 13:59:12', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for t_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `t_permission_role`;
CREATE TABLE `t_permission_role` (
  `ID` varchar(36) NOT NULL,
  `PERMISSIOIN_ID` varchar(36) DEFAULT NULL,
  `ROLE_ID` varchar(36) DEFAULT NULL,
  `FORMMAKER_ID` varchar(36) DEFAULT NULL,
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限角色关系表';

-- ----------------------------
-- Records of t_permission_role
-- ----------------------------
INSERT INTO `t_permission_role` VALUES ('9a8s7df9as7df978asd7f', '111111111111111111', '111111111111111111', null, null, '2017-11-14 15:24:35', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for t_pos_pay
-- ----------------------------
DROP TABLE IF EXISTS `t_pos_pay`;
CREATE TABLE `t_pos_pay` (
  `ID` varchar(36) NOT NULL COMMENT '主键ID',
  `TERMINAL_NUMBER` varchar(50) DEFAULT NULL COMMENT '终端编号',
  `TRANSACTION_TIME` varchar(20) DEFAULT NULL COMMENT '交易时间',
  `BANK_NUMBER` varchar(50) DEFAULT NULL COMMENT '银行卡号',
  `TRANSATION_PRICE` varchar(20) DEFAULT NULL COMMENT '交易金额',
  `DIVIDEND_INCOME` varchar(20) DEFAULT NULL COMMENT '红利收入',
  `DIVIDEND_EXPENDITURE` varchar(20) DEFAULT NULL COMMENT '红利支出',
  `DUMBO` varchar(20) DEFAULT NULL COMMENT '小费',
  `SERVICE_CHARGE` varchar(20) DEFAULT NULL COMMENT '手续费',
  `INSTALLMENT_FEE` varchar(20) DEFAULT NULL COMMENT '分期付款手续费',
  `NET_PROCEEDS` varchar(20) DEFAULT NULL COMMENT '净收金额',
  `TRANSACTION_SEARCHE_NUMBER` varchar(50) DEFAULT NULL COMMENT '交易检索号',
  `TRANSACTION_TYPE` varchar(10) DEFAULT NULL COMMENT '交易类型',
  `TRANSACTION_DATE` varchar(20) DEFAULT NULL COMMENT '交易日期',
  `MONEY_NATURE` varchar(50) DEFAULT NULL COMMENT '款项性质',
  `RECEIVABLES_NUMBER` varchar(50) DEFAULT NULL COMMENT '收款账号',
  `CASE_ID` varchar(36) DEFAULT NULL COMMENT '案号ID',
  `CASE_NUMBER` varchar(50) DEFAULT NULL COMMENT '案号',
  `IS_EDIT` int(1) DEFAULT '0' COMMENT '是否被编辑：\r\n            1是，\r\n            0否',
  `BATCH_NUMBER` varchar(36) DEFAULT NULL COMMENT '批次号',
  `CHIKAR` varchar(50) DEFAULT NULL COMMENT '持卡人',
  `SUBJECT_ID` varchar(36) DEFAULT NULL COMMENT '科目ID',
  `SUBJECT_NAME` varchar(50) DEFAULT NULL COMMENT '科目名称',
  `DEPARTMENT_NAME` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `UNDERTAKER` varchar(50) DEFAULT NULL COMMENT '承办人名',
  `FORMMAKER_ID` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL COMMENT '创建人ID',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='银行POS机支付流水信息表';

-- ----------------------------
-- Records of t_pos_pay
-- ----------------------------
INSERT INTO `t_pos_pay` VALUES ('01b9d0071949418a81f680663b7bd09e', '=\"440292110028002\"', '16:14:19', '=\"622700******0037464\"', '17850.00', '0.00', '0.00', '0.00', '0.00', '0.00', '17850.00', '=\"03440272630865010003227\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433464', null, 'B3=(2017)川0112民初5805号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('06eb3904d0f245e6a2c5095540dc07e1', null, null, '621558******0232211', '720.00', '0.00', '0.00', '0.00', '0.00', '0.00', '720.00', '03440273320865010012353', '消费', '2017-11-27', '诉讼款', '9558854402005419158', null, '(2017)川0112财保89号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('09ba6a2ea68546809abbd64c3f7817f2', '=\"440292110028002\"', '15:41:09', '=\"458123******8433\"', '300.00', '0.00', '0.00', '0.00', '0.00', '0.00', '300.00', '=\"03440272630865010003185\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433480', null, 'B3=(2017)川0112民初5802号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('0d1363f819cc4066bcb84445270965ba', '=\"440292110028002\"', '09:59:47', '=\"627067******6170\"', '50.00', '0.00', '0.00', '0.00', '0.00', '0.00', '50.00', '=\"03440273320865010012361\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419141', null, 'B3=(2017)川0112行初188号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('0d47b0ce32d44e3096934926cfb7db5c', '=\"440292110028002\"', '09:09:35', '=\"622153******0101594\"', '50.00', '0.00', '0.00', '0.00', '0.00', '0.00', '50.00', '=\"03440272630865010002971\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005434033', null, 'B3=(2017)川0112民初3750号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('0d95832e9a6f4708a4682f4e6977b54b', null, null, '628288******7764', '4083.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4083.00', '03440273320865010012452', '消费', '2017-11-27', '诉讼款', '9558854402005428134', null, '(2017)川0112民初999999号\"', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('0deda1010a5c4d9cafdcbda6062ae00b', '=\"440292110028002\"', '15:58:00', '=\"621558******2761500\"', '5000.00', '0.00', '0.00', '0.00', '0.00', '0.00', '5000.00', '=\"03440273320865010012494\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005418978', null, 'B3=(2017)川0112财保90号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('0df8383c5c584e9c832520c265712f35', '=\"440292110028002\"', '15:32:23', '=\"621558******8622506\"', '2500.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2500.00', '=\"03440272630865010003151\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433514', null, 'B3=(2017)川0112民初5791号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('0ecddb71869d4c0286c33cf24d62132f', '=\"440292110028002\"', '13:13:15', '=\"621700******0271536\"', '9800.00', '0.00', '0.00', '0.00', '0.00', '0.00', '9800.00', '=\"03440272630865010003094\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433878', null, 'B3=(2017)川0112民初5746号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('0ee1f20be0fb4d60876c4b50f69ecf27', '=\"440292110028002\"', '16:00:56', '=\"436745******9542\"', '354.50', '0.00', '0.00', '0.00', '0.00', '0.00', '354.50', '=\"03440273320865010012502\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005418960', null, 'B3=(2017)川0112民初3393号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:40', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('15f83a6eef4b436e8af628e53b292dd0', '=\"440292110028002\"', '15:13:33', '=\"622700******0006862\"', '9679.00', '0.00', '0.00', '0.00', '0.00', '0.00', '9679.00', '=\"03440273320865010012460\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419000', null, 'B3=(2017)川0112民初7110号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:32', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('16684f4547234f30a31c4b4baf79d2bd', '=\"440292110028002\"', '11:08:32', '=\"622818******6595\"', '100.00', '0.00', '0.00', '0.00', '0.00', '0.00', '100.00', '=\"03440273320865010012387\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419117', null, 'B3=(2017)川0112民初7003号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('166e379f49654e2aa7c9a4a855322d60', null, null, '436745******9542', '354.50', '0.00', '0.00', '0.00', '0.00', '0.00', '354.50', '03440273320865010012502', '消费', '2017-11-27', '诉讼款', '9558854402005418960', null, '(2017)川0112民初3393号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('19a306389b57404187aa980bf50392f4', '=\"440292110028002\"', '09:09:35', '=\"622153******0101594\"', '50.00', '0.00', '0.00', '0.00', '0.00', '0.00', '50.00', '=\"03440272630865010002971\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005434033', null, 'B3=(2017)川0112民初3750号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('19d0ab5eee564ea09c71261b8689cfc9', null, null, '622918******7428', '25.00', '0.00', '0.00', '0.00', '0.00', '0.00', '25.00', '03440273320865010012437', '消费', '2017-11-27', '诉讼款', '9558854402005419034', null, '(2017)川0112民初7089号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('1bf0588163d945c8854809b2a7bb03e8', '=\"440292110028002\"', '15:13:33', '=\"622700******0006862\"', '9679.00', '0.00', '0.00', '0.00', '0.00', '0.00', '9679.00', '=\"03440273320865010012460\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419000', null, 'B3=(2017)川0112民初7110号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('20a4a56f459a4662b49f700b6fdbffa5', '=\"440292110028002\"', '13:11:00', '=\"622848******7123673\"', '3328.00', '0.00', '0.00', '0.00', '0.00', '0.00', '3328.00', '=\"03440272630865010003086\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433886', null, 'B3=(2017)川0112民初5747号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('255902463213423ba80d557aa7a66a09', null, null, '621558******2761500', '5000.00', '0.00', '0.00', '0.00', '0.00', '0.00', '5000.00', '03440273320865010012494', '消费', '2017-11-27', '诉讼款', '9558854402005418978', null, '(2017)川0112财保90号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('27d9e7c95b444506a533c03f0f196521', '=\"440292110028002\"', '11:44:07', '=\"622848******2639579\"', '130.00', '0.00', '0.00', '0.00', '0.00', '0.00', '130.00', '=\"03440272630865010003060\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433910', null, 'B3=(2017)川0112民初5743号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('288ee11c347b40aab49bf79cbe5eb065', '=\"440292110028002\"', '14:21:10', '=\"622918******7428\"', '25.00', '0.00', '0.00', '0.00', '0.00', '0.00', '25.00', '=\"03440273320865010012437\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419034', null, 'B3=(2017)川0112民初7089号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('2bde11c9e1fe4c4d816fb617811a6870', '=\"440292110028002\"', '11:34:07', '=\"621226******5310324\"', '550.00', '0.00', '0.00', '0.00', '0.00', '0.00', '550.00', '=\"03440272630865010003052\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433928', null, 'B3=(2017)川0112民初5742号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('2cb13ebbe6a54389bfecb37149f496b4', null, null, '622818******6595', '100.00', '0.00', '0.00', '0.00', '0.00', '0.00', '100.00', '03440273320865010012387', '消费', '2017-11-27', '诉讼款', '9558854402005419117', null, '(2017)川0112民初7003号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('2d8389a5d2284523a58d6a15666c61fa', '=\"440292110028002\"', '15:29:53', '=\"621700******2435858\"', '2070.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2070.00', '=\"03440272630865010003144\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433522', null, 'B3=(2017)川0112民初5595号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('3529be7fd04f48668fef957ad08939bd', '=\"440292110028002\"', '10:13:00', '=\"621723******3927035\"', '540.00', '0.00', '0.00', '0.00', '0.00', '0.00', '540.00', '=\"03440272630865010002997\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433993', null, 'B3=(2017)川0112民初5524号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('3982be0c416148c588da8fbc76f6b1db', '=\"440292110028002\"', '09:18:54', '=\"621700******7134887\"', '2991.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2991.00', '=\"03440272630865010002989\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005434025', null, 'B3=(2017)川0112民初5579号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('3b64020d82ad48e0af0ecf9ab053b767', '=\"440292110028002\"', '16:34:31', '=\"622208******1101978\"', '73.00', '0.00', '0.00', '0.00', '0.00', '0.00', '73.00', '=\"03440272630865010003235\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433449', null, 'B3=(2017)川0112民初5806号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('3dd46d67ad014c5bba2b9b98715a04e4', '=\"440292110028002\"', '11:08:32', '=\"622818******6595\"', '100.00', '0.00', '0.00', '0.00', '0.00', '0.00', '100.00', '=\"03440273320865010012387\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419117', null, 'B3=(2017)川0112民初7003号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:10', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('44588813b3304c99b183fbfcd649f541', '=\"440292110028002\"', '10:13:00', '=\"621723******3927035\"', '540.00', '0.00', '0.00', '0.00', '0.00', '0.00', '540.00', '=\"03440272630865010002997\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433993', null, 'B3=(2017)川0112民初5524号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('4667737f7c8847118989dbfbe32960b6', '=\"440292110028002\"', '16:14:19', '=\"622700******0037464\"', '17850.00', '0.00', '0.00', '0.00', '0.00', '0.00', '17850.00', '=\"03440272630865010003227\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433464', null, 'B3=(2017)川0112民初5805号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('48c0ae6277a54b9ea2f9a3ebcdbf01b2', '=\"440292110028002\"', '15:20:49', '=\"621725******6218446\"', '5022.00', '0.00', '0.00', '0.00', '0.00', '0.00', '5022.00', '=\"03440272630865010003110\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433548', null, 'B3=(2017)川0112执1144号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('4a3385645d1a4b5e97f4c09c080f841c', '=\"440292110028002\"', '11:07:10', '=\"621700******7134887\"', '578.00', '0.00', '0.00', '0.00', '0.00', '0.00', '578.00', '=\"03440272630865010003029\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433985', null, 'B3=(2017)川0112民初5737号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('4e2f1e26ed154b3ca7eee6e9fda58da3', '=\"440292110028002\"', '15:38:55', '=\"458123******8433\"', '550.00', '0.00', '0.00', '0.00', '0.00', '0.00', '550.00', '=\"03440272630865010003177\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433498', null, 'B3=(2017)川0112民初5801号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('4fefc14494d14f3883a4a0a00e56e27e', '=\"440292110028002\"', '15:20:49', '=\"621725******6218446\"', '5022.00', '0.00', '0.00', '0.00', '0.00', '0.00', '5022.00', '=\"03440272630865010003110\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433548', null, 'B3=(2017)川0112执1144号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('538235d904874053a9c5f6a54499e770', '=\"440292110028002\"', '14:17:17', '=\"621099******0064696\"', '4125.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4125.00', '=\"03440273320865010012429\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419042', null, 'B3=(2017)川0112执3324号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('55d9869c2d0d4502ad82741cfcefbc27', '=\"440292110028002\"', '15:50:51', '=\"622525******6891\"', '260.00', '0.00', '0.00', '0.00', '0.00', '0.00', '260.00', '=\"03440272630865010003201\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433472', null, 'B3=(2017)川0112民初5804号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('595fd28bfe724dd0937adead8310dd32', null, null, '436742******0075710', '5000.00', '0.00', '0.00', '0.00', '0.00', '0.00', '5000.00', '03440273320865010012379', '消费', '2017-11-27', '诉讼款', '9558854402005419133', null, '(2015)龙泉民初字第4248号\"', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('5aaab84ae42442e28956098ca3ac8d6d', '=\"440292110028002\"', '15:31:07', '=\"621700******2593261\"', '550.00', '0.00', '0.00', '0.00', '0.00', '0.00', '550.00', '=\"03440273320865010012478\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005418986', null, 'B3=(2017)川0112民初7005号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('5b6e55962fa84d79b579c26e92631ecd', '=\"440292110028002\"', '15:31:07', '=\"621700******2593261\"', '550.00', '0.00', '0.00', '0.00', '0.00', '0.00', '550.00', '=\"03440273320865010012478\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005418986', null, 'B3=(2017)川0112民初7005号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:36', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('5fc2a6e866f04208819ea661c1236b9a', '=\"440292110028002\"', '11:17:11', '=\"622848******0718974\"', '411.00', '0.00', '0.00', '0.00', '0.00', '0.00', '411.00', '=\"03440272630865010003037\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433969', null, 'B3=(2017)川0112民初5740号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('60158069404a4a9c8f43ddb94402fede', '=\"440292110028002\"', '14:59:49', '=\"622908******365015\"', '100.00', '0.00', '0.00', '0.00', '0.00', '0.00', '100.00', '=\"03440273320865010012445\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419018', null, 'B3=(2017)川0112民初2471号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('6084cc440dc5426ab091d7fb9e21258d', '=\"440292110028002\"', '09:18:54', '=\"621700******7134887\"', '2991.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2991.00', '=\"03440272630865010002989\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005434025', null, 'B3=(2017)川0112民初5579号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('6bc3f0a9bd514135866b86ff14a3632d', '=\"440292110028002\"', '15:36:51', '=\"458123******8433\"', '4668.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4668.00', '=\"03440272630865010003169\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433506', null, 'B3=(2017)川0112民初5803号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('716298fc062f4a58baba43ec07dbd2d5', '=\"440292110028002\"', '09:59:47', '=\"627067******6170\"', '50.00', '0.00', '0.00', '0.00', '0.00', '0.00', '50.00', '=\"03440273320865010012361\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419141', null, 'B3=(2017)川0112行初188号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('72280d19b7e74cffbd4ffd74bd4c679e', '=\"440292110028002\"', '15:36:51', '=\"458123******8433\"', '4668.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4668.00', '=\"03440272630865010003169\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433506', null, 'B3=(2017)川0112民初5803号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('73d8123cba0f4502ae7057e88c81fa50', '=\"440292110028002\"', '10:29:50', '=\"621226******9002787\"', '4920.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4920.00', '=\"03440272630865010003011\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433977', null, 'B3=(2017)川0112民初5738号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('7579773574f346f7981ff7748de0ac2f', '=\"440292110028002\"', '15:06:58', '=\"628288******7764\"', '4083.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4083.00', '=\"03440273320865010012452\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005428134', null, 'B3=(2017)川0112民初999999号\"', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:32', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('7e560a4f2ced4717be0ca5de6d8aa7da', '=\"440292110028002\"', '15:47:44', '=\"621499******1554\"', '1760.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1760.00', '=\"03440272630865010003193\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433936', null, 'B3=(2017)川0112民初5741号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('7f5a683138f74668a6f9578464160b2a', '=\"440292110028002\"', '13:11:00', '=\"622848******7123673\"', '3328.00', '0.00', '0.00', '0.00', '0.00', '0.00', '3328.00', '=\"03440272630865010003086\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433886', null, 'B3=(2017)川0112民初5747号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('842d53c443594cfaa45c8093d505e5ef', '=\"440292110028002\"', '14:17:17', '=\"621099******0064696\"', '4125.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4125.00', '=\"03440273320865010012429\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419042', null, 'B3=(2017)川0112执3324号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:22', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('864d447a7dfa42bd8a8697ae281e5b3c', '=\"440292110028002\"', '15:06:58', '=\"628288******7764\"', '4083.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4083.00', '=\"03440273320865010012452\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005428134', null, 'B3=(2017)川0112民初999999号\"', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('87ad37bc67284cfa8acfc19805cb6191', '=\"440292110028002\"', '15:41:09', '=\"458123******8433\"', '300.00', '0.00', '0.00', '0.00', '0.00', '0.00', '300.00', '=\"03440272630865010003185\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433480', null, 'B3=(2017)川0112民初5802号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('881db033af44412c835c75a8f11295e7', '=\"440292110028002\"', '11:54:51', '=\"622575******2238\"', '1021.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1021.00', '=\"03440272630865010003078\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433902', null, 'B3=(2017)川0112民初5744号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('89c25d43687042e9b12c5d57e39730b0', null, null, '627067******6170', '50.00', '0.00', '0.00', '0.00', '0.00', '0.00', '50.00', '03440273320865010012361', '消费', '2017-11-27', '诉讼款', '9558854402005419141', null, '(2017)川0112行初188号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('8d0007ef7c9f4cb1b2e0b287ed338ae9', '=\"440292110028002\"', '16:34:31', '=\"622208******1101978\"', '73.00', '0.00', '0.00', '0.00', '0.00', '0.00', '73.00', '=\"03440272630865010003235\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433449', null, 'B3=(2017)川0112民初5806号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('93e7ed016d604b8e8741ed7397453de5', '=\"440292110028002\"', '10:29:50', '=\"621226******9002787\"', '4920.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4920.00', '=\"03440272630865010003011\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433977', null, 'B3=(2017)川0112民初5738号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('949416bdbc3c40ab9260fcadc6f69c95', null, null, '621661******8362199', '25.00', '0.00', '0.00', '0.00', '0.00', '0.00', '25.00', '03440273320865010012403', '消费', '2017-11-27', '诉讼款', '9558854402005419091', null, '(2017)川0112民初7108号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('94ed33b66b9b4fb1a28b27abcc265f87', '=\"440292110028002\"', '14:59:49', '=\"622908******365015\"', '100.00', '0.00', '0.00', '0.00', '0.00', '0.00', '100.00', '=\"03440273320865010012445\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419018', null, 'B3=(2017)川0112民初2471号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:28', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('977c044c950048108d3ce7535c032220', '=\"440292110028002\"', '11:44:07', '=\"622848******2639579\"', '130.00', '0.00', '0.00', '0.00', '0.00', '0.00', '130.00', '=\"03440272630865010003060\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433910', null, 'B3=(2017)川0112民初5743号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('99a07ab19d884af9a984e66b38521e33', '=\"440292110028002\"', '14:21:10', '=\"622918******7428\"', '25.00', '0.00', '0.00', '0.00', '0.00', '0.00', '25.00', '=\"03440273320865010012437\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419034', null, 'B3=(2017)川0112民初7089号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:28', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('9d00890fbeba42a0ba9325a1d07e9c77', null, null, '621700******2498212', '3860.00', '0.00', '0.00', '0.00', '0.00', '0.00', '3860.00', '03440273320865010012395', '消费', '2017-11-27', '诉讼款', '9558854402005419109', null, '(2017)川0112民初7107号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('a14fcfcd46d14cfb8e1c29c22be41ff8', '=\"440292110028002\"', '11:34:07', '=\"621226******5310324\"', '550.00', '0.00', '0.00', '0.00', '0.00', '0.00', '550.00', '=\"03440272630865010003052\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433928', null, 'B3=(2017)川0112民初5742号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('a19f8adaef954765bda0dcaa9ca8e5f7', null, null, '421870******8257', '3970.00', '0.00', '0.00', '0.00', '0.00', '0.00', '3970.00', '03440273320865010012411', '消费', '2017-11-27', '诉讼款', '9558854402005419067', null, '(2017)川0112民初6962号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('a28d8f230dce48c093e5687495c615ef', null, null, '622700******0006862', '9679.00', '0.00', '0.00', '0.00', '0.00', '0.00', '9679.00', '03440273320865010012460', '消费', '2017-11-27', '诉讼款', '9558854402005419000', null, '(2017)川0112民初7110号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('a346de4e83e6493f87855296ce2e8711', '=\"440292110028002\"', '11:15:33', '=\"621661******8362199\"', '25.00', '0.00', '0.00', '0.00', '0.00', '0.00', '25.00', '=\"03440273320865010012403\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419091', null, 'B3=(2017)川0112民初7108号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('a4c90b0f6401445895f650947fc6722b', '=\"440292110028002\"', '11:29:06', '=\"621700******0698320\"', '100.00', '0.00', '0.00', '0.00', '0.00', '0.00', '100.00', '=\"03440272630865010003045\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433944', null, 'B3=(2017)川0112民初5453号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('a595c9a9354946888a46420e71f2af43', '=\"440292110028002\"', '15:38:55', '=\"458123******8433\"', '550.00', '0.00', '0.00', '0.00', '0.00', '0.00', '550.00', '=\"03440272630865010003177\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433498', null, 'B3=(2017)川0112民初5801号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('aa45d41bd3e744a6a7420a6439c69f71', '=\"440292110028002\"', '14:21:48', '=\"622848******4510175\"', '193.00', '0.00', '0.00', '0.00', '0.00', '0.00', '193.00', '=\"03440272630865010003102\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433845', null, 'B3=(2017)川0112民初5785号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('ab719def58064792a08ce0931c3912c6', '=\"440292110028002\"', '10:04:10', '=\"436742******0075710\"', '5000.00', '0.00', '0.00', '0.00', '0.00', '0.00', '5000.00', '=\"03440273320865010012379\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419133', null, 'B3=(2015)龙泉民初字第4248号\"', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:10', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('b107469ee7784429b9923f3b2a1824cd', '=\"440292110028002\"', '15:32:23', '=\"621558******8622506\"', '2500.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2500.00', '=\"03440272630865010003151\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433514', null, 'B3=(2017)川0112民初5791号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('b26ff3089bcf4031a6099d62990f67ae', '=\"440292110028002\"', '15:24:59', '=\"622208******5380636\"', '4260.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4260.00', '=\"03440272630865010003128\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433530', null, 'B3=(2017)川0112民初5735号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('b5bf8cfcdb9b4f09b4580b4a4e82d136', '=\"440292110028002\"', '11:28:29', '=\"421870******8257\"', '3970.00', '0.00', '0.00', '0.00', '0.00', '0.00', '3970.00', '=\"03440273320865010012411\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419067', null, 'B3=(2017)川0112民初6962号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:22', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('b7701face7b7437c9a367605afbf79e3', null, null, '621226******0898326', '2870.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2870.00', '03440273320865010012486', '消费', '2017-11-27', '诉讼款', '9558854402005418994', null, '(2017)川0112民初6987号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('ba530262a8304ca391c8b776a8607aaa', '=\"440292110028002\"', '11:54:51', '=\"622575******2238\"', '1021.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1021.00', '=\"03440272630865010003078\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433902', null, 'B3=(2017)川0112民初5744号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('be0cfba8ddcc437b923c76377035bce2', '=\"440292110028002\"', '15:50:51', '=\"622525******6891\"', '260.00', '0.00', '0.00', '0.00', '0.00', '0.00', '260.00', '=\"03440272630865010003201\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433472', null, 'B3=(2017)川0112民初5804号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('beeca0c1934946348527fb515c9604aa', '=\"440292110028003\"', '11:25:10', '=\"622576******3621\"', '2000.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2000.00', '=\"03440273320865020000661\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419075', null, 'B3=(2017)川0112民初5382号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('c08ed015245e4236b0b422456bd2df88', '=\"440292110028002\"', '15:47:44', '=\"621499******1554\"', '1760.00', '0.00', '0.00', '0.00', '0.00', '0.00', '1760.00', '=\"03440272630865010003193\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433936', null, 'B3=(2017)川0112民初5741号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('c2b381477fba4713b277f3c1d55be55c', '=\"440292110028002\"', '15:53:17', '=\"621226******0898326\"', '2870.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2870.00', '=\"03440273320865010012486\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005418994', null, 'B3=(2017)川0112民初6987号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('c7d574f983ad40359302d6b86b648dd5', '=\"440292110028002\"', '16:11:41', '=\"625996******1816\"', '114.00', '0.00', '0.00', '0.00', '0.00', '0.00', '114.00', '=\"03440272630865010003219\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433456', null, 'B3=(2016)川0112执2136号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('c83543c6d0de47c09f3e18cd7b045bab', '=\"440292110028002\"', '11:07:10', '=\"621700******7134887\"', '578.00', '0.00', '0.00', '0.00', '0.00', '0.00', '578.00', '=\"03440272630865010003029\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433985', null, 'B3=(2017)川0112民初5737号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('c8b7952442574421ade25db214f96a4f', '=\"440292110028002\"', '09:46:14', '=\"621558******0232211\"', '720.00', '0.00', '0.00', '0.00', '0.00', '0.00', '720.00', '=\"03440273320865010012353\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419158', null, 'B3=(2017)川0112财保89号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('c9af54947ef941e2a9e464a8a20ee52d', null, null, '621099******0064696', '4125.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4125.00', '03440273320865010012429', '消费', '2017-11-27', '诉讼款', '9558854402005419042', null, '(2017)川0112执3324号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('ca7dfb8df0d84880b3165a739fb37cde', '=\"440292110028002\"', '15:53:17', '=\"621226******0898326\"', '2870.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2870.00', '=\"03440273320865010012486\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005418994', null, 'B3=(2017)川0112民初6987号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:36', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('cd1a39cf6d8b4d3cb43fc6b1026391bd', '=\"440292110028002\"', '16:11:41', '=\"625996******1816\"', '114.00', '0.00', '0.00', '0.00', '0.00', '0.00', '114.00', '=\"03440272630865010003219\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433456', null, 'B3=(2016)川0112执2136号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('cf133bc11c6349228e5f6d8ad01140ae', '=\"440292110028002\"', '15:27:30', '=\"621700******2435858\"', '400.00', '0.00', '0.00', '0.00', '0.00', '0.00', '400.00', '=\"03440272630865010003136\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005152452', null, 'B3=(2017)川0112民初5685号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('cf6d06daace741208338d72df3e8666c', '=\"440292110028002\"', '10:04:10', '=\"436742******0075710\"', '5000.00', '0.00', '0.00', '0.00', '0.00', '0.00', '5000.00', '=\"03440273320865010012379\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419133', null, 'B3=(2015)龙泉民初字第4248号\"', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('cfbf0d8546f14ba9abd5b9924764a030', null, null, '622576******3621', '2000.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2000.00', '03440273320865020000661', '消费', '2017-11-27', '诉讼款', '9558854402005419075', null, '(2017)川0112民初5382号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('d17d40ec09564d2f9181860e0fec0fbb', '=\"440292110028002\"', '11:17:11', '=\"622848******0718974\"', '411.00', '0.00', '0.00', '0.00', '0.00', '0.00', '411.00', '=\"03440272630865010003037\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433969', null, 'B3=(2017)川0112民初5740号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('d3715492f21e44ce8795705a32f75976', '=\"440292110028002\"', '16:00:56', '=\"436745******9542\"', '354.50', '0.00', '0.00', '0.00', '0.00', '0.00', '354.50', '=\"03440273320865010012502\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005418960', null, 'B3=(2017)川0112民初3393号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('d6e042c7adde4bc790a89ea378032eea', null, null, '622908******365015', '100.00', '0.00', '0.00', '0.00', '0.00', '0.00', '100.00', '03440273320865010012445', '消费', '2017-11-27', '诉讼款', '9558854402005419018', null, '(2017)川0112民初2471号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('e14513fd51a84492889c47470d23c64f', '=\"440292110028002\"', '11:15:33', '=\"621661******8362199\"', '25.00', '0.00', '0.00', '0.00', '0.00', '0.00', '25.00', '=\"03440273320865010012403\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419091', null, 'B3=(2017)川0112民初7108号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:14', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('e3a3601981b64cff9c3691baaebd5712', '=\"440292110028002\"', '11:29:06', '=\"621700******0698320\"', '100.00', '0.00', '0.00', '0.00', '0.00', '0.00', '100.00', '=\"03440272630865010003045\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433944', null, 'B3=(2017)川0112民初5453号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('e4b8dbca92a7460eaf2f2fd24cb3ee6e', '=\"440292110028002\"', '13:13:15', '=\"621700******0271536\"', '9800.00', '0.00', '0.00', '0.00', '0.00', '0.00', '9800.00', '=\"03440272630865010003094\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433878', null, 'B3=(2017)川0112民初5746号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('e68be6e2b4d1485c9ed23ff7c00b486f', '=\"440292110028002\"', '11:13:10', '=\"621700******2498212\"', '3860.00', '0.00', '0.00', '0.00', '0.00', '0.00', '3860.00', '=\"03440273320865010012395\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419109', null, 'B3=(2017)川0112民初7107号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:14', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('e94888c1203243c3b05f8d6109e81065', '=\"440292110028002\"', '15:58:00', '=\"621558******2761500\"', '5000.00', '0.00', '0.00', '0.00', '0.00', '0.00', '5000.00', '=\"03440273320865010012494\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005418978', null, 'B3=(2017)川0112财保90号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:40', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('ecfc95d8e9434f2b9d858d9b804bd005', '=\"440292110028002\"', '09:46:14', '=\"621558******0232211\"', '720.00', '0.00', '0.00', '0.00', '0.00', '0.00', '720.00', '=\"03440273320865010012353\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419158', null, 'B3=(2017)川0112财保89号', '0', '20171129104457', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 10:45:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('f3c17fb1b12144aca639b57b25d70635', '=\"440292110028002\"', '15:24:59', '=\"622208******5380636\"', '4260.00', '0.00', '0.00', '0.00', '0.00', '0.00', '4260.00', '=\"03440272630865010003128\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433530', null, 'B3=(2017)川0112民初5735号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('f5b246f97393415b972ad101f76f18c4', null, null, '621700******2593261', '550.00', '0.00', '0.00', '0.00', '0.00', '0.00', '550.00', '03440273320865010012478', '消费', '2017-11-27', '诉讼款', '9558854402005418986', null, '(2017)川0112民初7005号', '0', 'POS_20171210123724', null, null, null, null, null, '111111111111111111', 'admin', '2017-12-10 12:37:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('f6d78de520314cc482c1171cb7aa225d', '=\"440292110028002\"', '14:21:48', '=\"622848******4510175\"', '193.00', '0.00', '0.00', '0.00', '0.00', '0.00', '193.00', '=\"03440272630865010003102\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433845', null, 'B3=(2017)川0112民初5785号', '0', '20171126164201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 16:42:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('f83459725dc548c394c926e3d94b2105', '=\"440292110028002\"', '11:28:29', '=\"421870******8257\"', '3970.00', '0.00', '0.00', '0.00', '0.00', '0.00', '3970.00', '=\"03440273320865010012411\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419067', null, 'B3=(2017)川0112民初6962号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('f875b4728a1c4e2ab0226e7285476033', '=\"440292110028002\"', '11:13:10', '=\"621700******2498212\"', '3860.00', '0.00', '0.00', '0.00', '0.00', '0.00', '3860.00', '=\"03440273320865010012395\"', '消费', '2017-11-27', '=\"B1=诉讼款', 'B2=9558854402005419109', null, 'B3=(2017)川0112民初7107号', '0', '20171129112201', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-29 11:22:01', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('f9ebc458437f482599d8dd79e95073f9', '=\"440292110028002\"', '15:27:30', '=\"621700******2435858\"', '400.00', '0.00', '0.00', '0.00', '0.00', '0.00', '400.00', '=\"03440272630865010003136\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005152452', null, 'B3=(2017)川0112民初5685号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');
INSERT INTO `t_pos_pay` VALUES ('ff6d37c093804d0cb818c0e0467e8316', '=\"440292110028002\"', '15:29:53', '=\"621700******2435858\"', '2070.00', '0.00', '0.00', '0.00', '0.00', '0.00', '2070.00', '=\"03440272630865010003144\"', '消费', '2017-09-19', '=\"B1=诉讼款', 'B2=9558854402005433522', null, 'B3=(2017)川0112民初5595号', '0', '20171126173449', null, null, null, null, null, '111111111111111111', 'admin', '2017-11-26 17:34:49', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for t_process_type
-- ----------------------------
DROP TABLE IF EXISTS `t_process_type`;
CREATE TABLE `t_process_type` (
  `ID` varchar(36) NOT NULL COMMENT '主键Id',
  `PROCESS_TYPE_NAME` varchar(50) DEFAULT NULL COMMENT '处理方式名称',
  `FORMMAKER_ID` varchar(36) DEFAULT NULL COMMENT '创建人ID',
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL COMMENT '创建人名',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='处理方式维表';

-- ----------------------------
-- Records of t_process_type
-- ----------------------------
INSERT INTO `t_process_type` VALUES ('d9sdfa98d7f98asdf', '催收', null, null, '2017-12-05 12:54:40', '0000-00-00 00:00:00', '0');
INSERT INTO `t_process_type` VALUES ('fa9sdf78a98sd7faa9s8d79fsfdf', '正在收款', null, null, '2017-12-05 12:54:25', '0000-00-00 00:00:00', '0');
INSERT INTO `t_process_type` VALUES ('idf8a68d7f687a6ds7df', '移送执行', null, null, '2017-12-05 12:54:54', '0000-00-00 00:00:00', '0');
INSERT INTO `t_process_type` VALUES ('odkf4567dafd5a5df8a7d', '坏账处理', null, null, '2017-12-05 12:55:07', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ID` varchar(36) NOT NULL,
  `ROLE_NAME` varchar(30) DEFAULT NULL,
  `ROLE_CODE` varchar(30) DEFAULT NULL,
  `ENABLE_STATUS` int(1) DEFAULT NULL,
  `COMMENTS` varchar(100) DEFAULT NULL,
  `FORMMAKER_ID` varchar(36) DEFAULT NULL,
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('111111111111111111', '超级管理员', 'admin', '1', '开发使用', 'admin', 'admin', '2017-11-14 15:23:09', '0000-00-00 00:00:00', '0');
INSERT INTO `t_role` VALUES ('111111111111111sdas', '超级管理员', 'admin', '1', '开发使用', 'admin', 'admin', '2017-12-15 22:37:17', '2017-12-15 22:37:17', '1');
INSERT INTO `t_role` VALUES ('5463sfghertyhdfgh45e', '超级管理员', 'admin', '1', '开发使用', 'admin', 'admin', '2017-11-14 15:23:09', '0000-00-00 00:00:00', '0');
INSERT INTO `t_role` VALUES ('9a8sd98jhda89oid98asdf', '超级管理员', 'admin', '1', '开发使用', 'admin', 'admin', '2017-11-14 15:23:09', '0000-00-00 00:00:00', '0');
INSERT INTO `t_role` VALUES ('9asd7f9a7s9df9hhjd987faihdf', '超级管理员', 'admin', '1', '开发使用', 'admin', 'admin', '2017-11-14 15:23:09', '0000-00-00 00:00:00', '0');
INSERT INTO `t_role` VALUES ('kusdfhgi8s9dfg9sdfug9', '超级管理员', 'admin', '1', '开发使用', 'admin', 'admin', '2017-11-14 15:23:09', '0000-00-00 00:00:00', '0');
INSERT INTO `t_role` VALUES ('sadfadsfasdfasdfasd', '超级管理员', 'admin', '1', '开发使用', 'admin', 'admin', '2017-11-14 15:23:09', '0000-00-00 00:00:00', '0');

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject` (
  `ID` varchar(108) DEFAULT NULL,
  `S_CODE` varchar(108) DEFAULT NULL,
  `S_NAME` varchar(150) DEFAULT NULL,
  `PARENT_ID` varchar(108) DEFAULT NULL,
  `type` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_subject
-- ----------------------------
INSERT INTO `t_subject` VALUES ('1', '1001', '库存现金', null, '100');
INSERT INTO `t_subject` VALUES ('3', '1002', '银行存款', null, '100');
INSERT INTO `t_subject` VALUES ('4', '100201', '诉讼费', '1002', '100');
INSERT INTO `t_subject` VALUES ('5', '100202', '国有资源(资产)有偿使用收入', '1002', '100');
INSERT INTO `t_subject` VALUES ('6', '10020101', '案件受理费', '100201', '100');
INSERT INTO `t_subject` VALUES ('7', '10020102', '申诉执行费', '100201', '100');
INSERT INTO `t_subject` VALUES ('8', '10020103', '其他诉讼费', '100201', '100');
INSERT INTO `t_subject` VALUES ('9', '10020201', '其他利息收入', '100202', '100');
INSERT INTO `t_subject` VALUES ('10', '1002010301', '诉讼保全费', '10020103', '100');
INSERT INTO `t_subject` VALUES ('11', '1002010302', '法医鉴定费', '10020103', '100');
INSERT INTO `t_subject` VALUES ('12', '1002010303', '其他诉讼费', '10020103', '100');
INSERT INTO `t_subject` VALUES ('13', '100203', '执行案款', '1002', '100');
INSERT INTO `t_subject` VALUES ('14', '100204', '其他', '1002', '100');
INSERT INTO `t_subject` VALUES ('15', '10020301', '执行局执行案款', '100203', '100');
INSERT INTO `t_subject` VALUES ('16', '10020302', '非诉讼执行案款', '100203', '100');
INSERT INTO `t_subject` VALUES ('17', '10020303', '民事履行案款', '100203', '100');
INSERT INTO `t_subject` VALUES ('18', '10020401', '业务费', '100204', '100');
INSERT INTO `t_subject` VALUES ('19', '10020402', '其他', '100204', '100');
INSERT INTO `t_subject` VALUES ('20', '2001', '应缴财政款', null, '200');
INSERT INTO `t_subject` VALUES ('21', '2002', '其他应付款', null, '200');
INSERT INTO `t_subject` VALUES ('22', '200201', '其他应付款(案款)', '2002', '200');
INSERT INTO `t_subject` VALUES ('23', '200202', '其他', '2002', '200');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ID` varchar(36) NOT NULL,
  `LOGIN_NAME` varchar(30) DEFAULT NULL,
  `PASSWORD` varchar(36) DEFAULT NULL,
  `REAL_NAME` varchar(30) DEFAULT NULL,
  `PHONE` varchar(30) DEFAULT NULL,
  `EMAIL` varchar(70) DEFAULT NULL,
  `ENABLE_STATUS` int(1) DEFAULT '1' COMMENT '1.启用\r\n            0.停用\r\n            默认启用',
  `FORMMAKER_ID` varchar(36) DEFAULT NULL,
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  `IS_ADMIN` int(1) DEFAULT '0' COMMENT '1是，0否',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('111111111111111111', 'admin', 'f6fdffe48c908deb0f4c3bd36c032e72', '管理员大人', '18180600848', 'wangjianwwt@live.com', '1', 'admin', 'admin', '2017-12-16 13:39:03', '2017-12-16 13:23:05', '0', '1');
INSERT INTO `t_user` VALUES ('dd5116da491e4875ba66989788c8471e', 'wangjian', 'e0f42e2eda649f65d5568225c3f8b8d4', '王坚', '15828549999', 'wangjian15@live.com', '1', '111111111111111111', 'admin', '2017-12-16 13:45:18', '2017-12-16 13:45:18', '0', '0');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `ID` varchar(36) NOT NULL,
  `USER_ID` varchar(36) DEFAULT NULL,
  `ROLE_ID` varchar(36) DEFAULT NULL,
  `FORMMAKER_ID` varchar(36) DEFAULT NULL,
  `FORMMAKER_NAME` varchar(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_MODIFIED_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `DEL_STATUS` int(1) DEFAULT '0' COMMENT '1.已删除\r\n            0.未删除\r\n            默认未删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1af7a26e253e43608a74e5cffc378a74', '111111111111111111', '111111111111111111', '111111111111111111', 'admin', '2017-12-15 21:10:26', '2017-12-16 13:22:41', '1');
INSERT INTO `t_user_role` VALUES ('26623daf1599485bb0e7f4e679085f2e', '111111111111111111', '111111111111111111', '111111111111111111', 'admin', '2017-12-15 21:09:07', '2017-12-15 21:10:26', '1');
INSERT INTO `t_user_role` VALUES ('33645f88c2a0432f9117a3be11a41091', 'dd5116da491e4875ba66989788c8471e', '111111111111111111', '111111111111111111', 'admin', '2017-12-16 13:45:18', '2017-12-16 13:45:18', '0');
INSERT INTO `t_user_role` VALUES ('3b3aaba314a843879f139121b2771521', '7ada9157e0554a17ad52017f4fab9360', 'kusdfhgi8s9dfg9sdfug9', null, null, '2017-12-16 12:42:57', '2017-12-16 12:42:57', '0');
INSERT INTO `t_user_role` VALUES ('68fcae9d11214e2990f5b4a63420bb54', '7ada9157e0554a17ad52017f4fab9360', 'kusdfhgi8s9dfg9sdfug9', '111111111111111111', 'admin', '2017-12-14 21:17:25', '2017-12-15 21:08:31', '1');
INSERT INTO `t_user_role` VALUES ('96a99c8775a6496e9cb7b961618b12ad', '7ada9157e0554a17ad52017f4fab9360', 'kusdfhgi8s9dfg9sdfug9', '111111111111111111', 'admin', '2017-12-15 21:08:31', '2017-12-16 12:42:03', '1');
INSERT INTO `t_user_role` VALUES ('a9df798a7df9adf', '111111111111111111', '111111111111111111', 'admin', 'admin', '2017-11-14 15:16:10', '2017-12-15 21:08:07', '1');
INSERT INTO `t_user_role` VALUES ('ade44ff59f064d07a78a86f303acd26d', 'bbbaf6ff1baa4814918c9626de6ee208', '111111111111111111', '111111111111111111', 'admin', '2017-12-15 21:12:16', '2017-12-15 21:12:16', '0');
INSERT INTO `t_user_role` VALUES ('b39207c5b3dc49d9a3c4f174900c4fca', '111111111111111111', '111111111111111111', '111111111111111111', 'admin', '2017-12-15 21:08:20', '2017-12-15 21:09:07', '1');
INSERT INTO `t_user_role` VALUES ('d1baa0bb400143ecb2d16c1a66572633', 'bbbaf6ff1baa4814918c9626de6ee208', '111111111111111111', '111111111111111111', 'admin', '2017-12-14 20:49:21', '2017-12-15 21:12:16', '1');
INSERT INTO `t_user_role` VALUES ('d8b311fef5d94569a3aecae5b3fd7f2a', '111111111111111111', '111111111111111111', '111111111111111111', 'admin', '2017-12-16 13:22:41', '2017-12-16 13:22:41', '0');
INSERT INTO `t_user_role` VALUES ('d9f750b661224fafaa25106778c03ac7', 'd2bdf34b2b004ef884e6ac70388ffe29', 'kusdfhgi8s9dfg9sdfug9', '111111111111111111', 'admin', '2017-12-14 20:57:58', '2017-12-14 20:57:58', '0');
INSERT INTO `t_user_role` VALUES ('e6cb9ecb0fbd49c795182b6da98488f7', '111111111111111111', '111111111111111111', '111111111111111111', 'admin', '2017-12-15 21:08:08', '2017-12-15 21:08:20', '1');
INSERT INTO `t_user_role` VALUES ('ecffdc1172474143bd67a9c6cc248afe', '7ada9157e0554a17ad52017f4fab9360', 'kusdfhgi8s9dfg9sdfug9', null, null, '2017-12-16 12:42:03', '2017-12-16 12:42:57', '1');
INSERT INTO `t_user_role` VALUES ('ef350de648a24504a0e1a50784698952', 'e74fc6eb66a043d0a7d93724147c832a', '9asd7f9a7s9df9hhjd987faihdf', '111111111111111111', 'admin', '2017-12-14 20:46:29', '2017-12-14 20:46:29', '0');
INSERT INTO `t_user_role` VALUES ('f352322a08b443a6964ade3e4a4f274d', 'e5860976f3a6452682a8a8fc589e8e06', '9asd7f9a7s9df9hhjd987faihdf', '111111111111111111', 'admin', '2017-12-16 13:19:09', '2017-12-16 13:19:09', '0');
