CREATE DATABASE evo_datadict DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_datadict'@'%' IDENTIFIED BY 'evo_datadict';
ALTER USER 'evo_datadict'@'%' IDENTIFIED WITH mysql_native_password BY 'evo_datadict';
GRANT ALL ON evo_datadict.* TO 'evo_datadict'@'%';

FLUSH PRIVILEGES;

drop table if exists sys_data_dict;
create table sys_data_dict
(
  id bigint auto_increment primary key,
  name varchar(255) null,
  code varchar(255) null,
  key_ varchar(255) null,
  value varchar(255) null,
  sort int null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);
