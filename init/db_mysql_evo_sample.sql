CREATE DATABASE evo_sample DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_sample'@'%' IDENTIFIED BY 'evo_sample';

GRANT ALL ON evo_sample.* TO 'evo_sample'@'%';

FLUSH PRIVILEGES;

use evo_sample;


create table counry
(
  id bigint auto_increment primary key,
  code varchar(255) null,
  name varchar(255) null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

insert into counry values (null, 'CODE_0001', 'CN', '1', now(), '1', now());
insert into counry values (null, 'CODE_0002', 'US', '1', now(), '1', now());
