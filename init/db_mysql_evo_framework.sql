CREATE DATABASE evo_framework DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_framework'@'%' IDENTIFIED BY 'evo_framework';
GRANT ALL ON evo_framework.* TO 'evo_framework'@'%';

FLUSH PRIVILEGES;

create table config_properties
(
  id bigint auto_increment
    primary key,
  label varchar(255) null,
  application varchar(255) null,
  profile varchar(255) null,
  key_ varchar(255) null,
  value text null,
  comment varchar(255) null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

create table zuul_route
(
  id bigint auto_increment
    primary key,
  route_id varchar(255) null,
  path varchar(255) null,
  service_id varchar(255) null,
  url varchar(255) null,
  strip_prefix varchar(4) null,
  retryable varchar(4) null,
  sensitive_headers varchar(255) null,
  enabled varchar(4) null,
  comment varchar(255) null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

create table dts_message
(
  id bigint auto_increment
    primary key,
  log_message_key varchar(255) null,
  business_key varchar(255) null,
  message_key varchar(255) null,
  content varchar(512) null,
  producer varchar(255) null,
  consumer varchar(255) null,
  role varchar(4) null,
  step varchar(4) null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);

create table dts_record
(
  id bigint auto_increment
    primary key,
  business_key varchar(255) null,
  producer_step varchar(4) null,
  consumer_step varchar(4) null,
  create_by bigint null,
  create_time datetime null,
  update_by bigint null,
  update_time datetime null
);
