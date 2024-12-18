# 数据库初始化

-- 创建库
create database if not exists oj_db;

-- 切换库
use oj_db;

-- 用户表
create table if not exists user
(
    `id`       bigint comment 'id' primary key,
    tel        varchar(16)                        not null comment '账号',
    pwd        varchar(64)                        not null comment '密码',
    name       varchar(64)                        null comment '用户昵称',
    profile    varchar(512)                       null comment '用户简介',
    role       tinyint  default 0                 not null comment '用户角色：user - 0/admin - 1/ban - -1',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '用户' collate = utf8mb4_unicode_ci;

-- 题目表
create table if not exists question
(
    `id`        bigint comment 'id' primary key,
    title       varchar(16)                        not null comment '标题',
    content     text                               not null comment '题干',
    level       tinyint                            null comment '难度 0 - noob 1 - normal 2 - hard',
    judgeCase   text                               null comment '测试点 - json数组',
    judgeConfig varchar(64)                        not null comment '判题限制 - json对象',
    acceptNum   int      default 0                 not null comment '通过人数',
    submitNum   int      default 0                 not null comment '提交人数',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
) comment '题目' collate = utf8mb4_unicode_ci;

-- 提交表
create table if not exists submit
(
    `id`        bigint comment 'id' primary key,
    language    varchar(16)                        not null comment '编程语言',
    code        text                               not null comment '提交代码',
    info        varchar(64)                        null comment '判题信息 - json',
    status      tinyint  default 0                 null comment '判题状态 0 - 队列中 1 - running 2 - ac -1 - wa',
    judgeConfig varchar(64)                        not null comment '判题限制 - json对象',
    questionId  bigint                             not null comment '题目id',
    userId      bigint                             not null comment '提交用户id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete    tinyint  default 0                 not null comment '是否删除'
) comment '提交表' collate = utf8mb4_unicode_ci;