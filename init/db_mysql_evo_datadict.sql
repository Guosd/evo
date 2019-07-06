CREATE DATABASE evo_datadict DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_datadict'@'%' IDENTIFIED BY 'evo_datadict';
GRANT ALL ON evo_datadict.* TO 'evo_datadict'@'%';

FLUSH PRIVILEGES;

use evo_datadict;
-- evo_datadict test
drop table if exists sys_data_dict;
create table sys_data_dict
(
  id          int auto_increment
    primary key,
  name        varchar(256) null,
  code        varchar(256) null,
  key_        varchar(256) null,
  value       varchar(256) null,
  sort        bigint       null,
  create_by   bigint       null,
  create_time datetime     null,
  update_by   bigint       null,
  update_time datetime     null
);
insert into sys_data_dict values (null, null, 'DTS_ROLE', 'RE00', '事务生产者', '1', '1', now(), '1', now());
insert into sys_data_dict values (null, null, 'DTS_ROLE', 'RE01', '事务消费者', '2', '1', now(), '1', now());

insert into sys_data_dict values (null, null, 'DTS_STEP', 'ST00', '处理中', '1', '1', now(), '1', now());
insert into sys_data_dict values (null, null, 'DTS_STEP', 'ST01', '已完成', '2', '1', now(), '1', now());
