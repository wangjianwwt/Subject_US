/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.1.54-community 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_subject` (
	`ID` varchar (108),
	`S_CODE` varchar (108),
	`S_NAME` varchar (150),
	`PARENT_ID` varchar (108),
	`type` varchar (150)
); 
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('1','1001','库存现金',NULL,'100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('3','1002','银行存款',NULL,'100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('4','100201','诉讼费','1002','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('5','100202','国有资源(资产)有偿使用收入','1002','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('6','10020101','案件受理费','100201','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('7','10020102','申诉执行费','100201','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('8','10020103','其他诉讼费','100201','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('9','10020201','其他利息收入','100202','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('10','1002010301','诉讼保全费','10020103','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('11','1002010302','法医鉴定费','10020103','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('12','1002010303','其他诉讼费','10020103','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('13','100203','执行案款','1002','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('14','100204','其他','1002','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('15','10020301','执行局执行案款','100203','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('16','10020302','非诉讼执行案款','100203','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('17','10020303','民事履行案款','100203','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('18','10020401','业务费','100204','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('19','10020402','其他','100204','100');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('20','2001','应缴财政款',NULL,'200');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('21','2002','其他应付款',NULL,'200');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('22','200201','其他应付款(案款)','2002','200');
insert into `t_subject` (`ID`, `S_CODE`, `S_NAME`, `PARENT_ID`, `type`) values('23','200202','其他','2002','200');
