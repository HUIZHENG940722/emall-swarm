-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
drop table if exists `ums_admin`;
create table `ums_admin`(
    `id` bigint(20) not null comment '记录id' auto_increment primary key,
    `username` varchar(64) not null comment '用户名',
    `password` varchar(64) not null comment '密码',
    `icon` varchar(500) comment '头像',
    `email` varchar(100) comment '邮箱',
    `nick_name` varchar(200) comment '昵称',
    `note` varchar(500) comment '备注信息',
    `create_time` datetime not null comment '创建时间',
    `login_time` datetime comment '最后登录时间',
    `status` int(1) default 1 comment '账号启用状态：0->禁用；1->启用'
) engine=InnoDB auto_increment=1 default charset=utf8 comment='后台用户表';