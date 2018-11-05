CREATE DATABASE evo_demo_account DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE evo_demo_order DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE evo_demo_storage DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_demo_account'@'%' IDENTIFIED BY 'evo_demo_account';
CREATE USER 'evo_demo_order'@'%' IDENTIFIED BY 'evo_demo_order';
CREATE USER 'evo_demo_storage'@'%' IDENTIFIED BY 'evo_demo_storage';

GRANT ALL ON evo_demo_account.* TO 'evo_demo_account'@'%';
GRANT ALL ON evo_demo_order.* TO 'evo_demo_order'@'%';
GRANT ALL ON evo_demo_storage.* TO 'evo_demo_storage'@'%';

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
