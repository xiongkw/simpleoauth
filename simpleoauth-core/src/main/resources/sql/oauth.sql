/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/1/9 18:38:10                            */
/*==============================================================*/


drop table if exists oauth_access_token;

drop table if exists oauth_access_token_history;

drop table if exists oauth_app_info;

drop table if exists oauth_code;

drop table if exists oauth_code_history;

/*==============================================================*/
/* Table: oauth_access_token                                    */
/*==============================================================*/
create table oauth_access_token
(
   id                   bigint not null,
   access_token         varchar(256) not null,
   oauth_code           varchar(32) not null,
   app_key              varchar(32) not null,
   user_id              bigint not null,
   user_name            varchar(50),
   tenant_id            bigint not null,
   tenant_code          varchar(50) not null,
   token_type           varchar(20) not null,
   create_time          timestamp not null,
   expires_in           int not null,
   refresh_token        varchar(256) not null,
   refresh_token_expire_seconds int not null,
   has_been_refreshed   boolean,
   refresh_from         varchar(256),
   primary key (id)
);

/*==============================================================*/
/* Table: oauth_access_token_history                            */
/*==============================================================*/
create table oauth_access_token_history
(
   id                   bigint not null,
   access_token         varchar(256) not null,
   oauth_code           varchar(32) not null,
   app_key              varchar(32) not null,
   user_id              bigint not null,
   user_name            varchar(50) not null,
   tenant_id            bigint not null,
   tenant_code          varchar(50) not null,
   token_type           varchar(20) not null,
   create_time          timestamp not null,
   expires_in           int not null,
   refresh_token        varchar(256) not null,
   refresh_token_expire_seconds int not null,
   has_been_refreshed   boolean,
   refresh_from         varchar(256),
   refresh_or_expire    tinyint,
   primary key (id)
);

/*==============================================================*/
/* Table: oauth_app_info                                        */
/*==============================================================*/
create table oauth_app_info
(
   app_code             varchar(50) not null,
   app_name             varchar(100),
   app_key              varchar(32) not null,
   app_secret           varchar(64) not null,
   redirect_url         varchar(100) not null,
   image_url            varchar(100),
   create_time          timestamp not null,
   primary key (app_code)
);

alter table oauth_app_info comment '存放appkey，app_secret等应用相关的信息';

/*==============================================================*/
/* Table: oauth_code                                            */
/*==============================================================*/
create table oauth_code
(
   id                   bigint not null,
   code                 varchar(32) not null comment '使用code作为主键',
   app_key              varchar(32) comment '应用创建时生成的appkey',
   create_time          timestamp not null comment 'code生成的时间',
   expires_in           int comment '此code的过期时间',
   user_id              bigint comment '此code对应的用户标识',
   user_name            varchar(50) comment '此code对应的用户名',
   tenant_id            bigint not null,
   tenant_code          varchar(50) not null,
   has_been_used        boolean,
   primary key (id)
);

alter table oauth_code comment '客户端通过授权码来获取access_token';

/*==============================================================*/
/* Table: oauth_code_history                                    */
/*==============================================================*/
create table oauth_code_history
(
   id                   bigint not null,
   code                 varchar(32) not null comment '使用code作为主键',
   app_key              varchar(32) comment '应用创建时生成的appkey',
   create_time          timestamp not null comment 'code生成的时间',
   expires_in           int comment '此code的过期时间',
   user_id              bigint comment '此code对应的用户标识',
   user_name            varchar(50) comment '此code对应的用户名',
   tenant_id            bigint not null,
   tenant_code          varchar(50) not null,
   has_been_used        boolean,
   is_expired           boolean,
   primary key (id)
);

alter table oauth_code_history comment '存放oauth_code的历史数据';

