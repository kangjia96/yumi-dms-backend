# create database if not exists `db_yumi_dms_v1`;

-- 数据源表
drop table if exists t_datasource;
create table t_datasource (
    id int(11) not null auto_increment primary key,
    name varchar(20) not null unique comment '数据源名称',
    type varchar(20) not null comment '数据源类型',
    host varchar(20) not null comment '主机',
    port int(11) not null comment '端口',
    username varchar(20) not null comment '用户名',
    password varchar(255) not null comment '密码',
    description varchar(255) comment '描述',
    create_time datetime comment '创建时间',
    update_time datetime comment '更新时间',
    enabled tinyint(1) not null default 1 comment '是否启用',
    is_deleted tinyint(1) not null default 0 comment '是否删除'
) comment '数据源表';