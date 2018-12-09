drop database IF EXISTS SubmitMerger;
CREATE DATABASE SubmitMerger CHARACTER SET utf8 COLLATE utf8_general_ci;
use SubmitMerger;
create table admin(
	id int(11) PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
	username varchar(20) NOT NULL UNIQUE,
	password varchar(20) NOT NULL,
	nickname varchar(20),
	adminType int(3),
	status int(3)
);
insert into admin values (1,"admin","123","超级管理员",1,0);

INSERT INTO message(id, command, description, content, createDateTime, admin_id) VALUES (1, '新闻', '热点新闻', '中国足球队进入世界杯8强！', now(), 1);

create table customer(
	id int(11) primary key AUTO_INCREMENT comment '主键',
	tel varchar(11) NOT NULL UNIQUE comment '账号',
	password varchar(20) NOT NULL comment '密码',
	nickname varchar(20) comment '昵称',
	status int(3) comment '状态'
);