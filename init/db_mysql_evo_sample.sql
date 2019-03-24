CREATE DATABASE evo_sample DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_sample'@'%' IDENTIFIED BY 'evo_sample';

GRANT ALL ON evo_sample.* TO 'evo_sample'@'%';

FLUSH PRIVILEGES;

use evo_demo_account;

create table demo_account
(
  id bigint auto_increment
    primary key,
  name varchar(255) null,
  amount decimal(15,2) null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

use evo_demo_order;

create table `demo_order`
(
  id bigint auto_increment
    primary key,
  name varchar(255) null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

use evo_demo_storage;

create table demo_storage
(
  id bigint auto_increment
    primary key,
  name varchar(255) null,
  amount int null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

insert into demo_account values (null, '1', '1000000', '1', now(), '1', now());
