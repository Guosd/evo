CREATE DATABASE evo_sys DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_sys'@'%' IDENTIFIED BY 'evo_sys';
GRANT ALL ON evo_sys.* TO 'evo_sys'@'%';

FLUSH PRIVILEGES;

use evo_sys;

drop table if exists sys_user;
drop table if exists sys_role;
drop table if exists sys_func;
drop table if exists sys_micro;
drop table if exists sys_menu;
drop table if exists sys_user_role;
drop table if exists sys_role_func;
drop table if exists sys_dict;

create table sys_user  (
  id bigint auto_increment primary key,
  username varchar(32) null,
  password varchar(60) null,
  name varchar(32) null,
  code varchar(32) null,
  email varchar(32) null,
  mobile_number varchar(32) null,
  freeze varchar(4) null,
  login_time datetime null,
  login_ip varchar(32) null,
  last_login_time datetime null,
  last_login_ip varchar(32) null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

CREATE TABLE sys_role  (
  id bigint auto_increment primary key,
  name varchar(32) null,
  code varchar(32) null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

create table sys_func (
  id bigint auto_increment primary key,
  micro_id bigint null,
  name varchar(32) null,
  code varchar(32) null,
  method varchar(6) null,
  uri varchar(255) null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

create table sys_micro  (
  id bigint auto_increment primary key,
  name varchar(255) null,
  code varchar(255) null,
  prefix varchar(255) null,
  create_by bigint(20) null,
  create_time datetime(0) null,
  update_by bigint(20) null,
  update_time datetime(0) null
);

create table sys_menu  (
  id bigint auto_increment primary key,
  parent_id bigint null,
  func_id bigint null,
  name varchar(32) null,
  code varchar(32) null,
  sort int null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

CREATE TABLE sys_user_role  (
  user_id bigint,
  role_id bigint,
  primary key (user_id, role_id)
);

CREATE TABLE sys_role_func  (
  role_id bigint,
  func_id bigint,
  primary key (role_id, func_id)
);

create table sys_dict  (
  id bigint auto_increment primary key,
  label varchar(100) null,
  value varchar(100) null,
  type varchar(100) null,
  sort int(11) null,
  creator bigint(20) null,
  create_time timestamp(0) null,
  modifier bigint(20) null,
  modify_time timestamp(0) null
)
