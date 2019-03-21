CREATE DATABASE evo_framework DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE USER 'evo_framework'@'%' IDENTIFIED BY 'evo_framework';
ALTER USER 'evo_framework'@'%' IDENTIFIED WITH mysql_native_password BY 'evo_framework';
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

create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  token LONGBLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token LONGBLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication LONGBLOB,
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256),
  token LONGBLOB,
  authentication LONGBLOB
);

create table oauth_code (
  code VARCHAR(256), authentication LONGBLOB
);

create table oauth_approvals (
	userId VARCHAR(256),
	clientId VARCHAR(256),
	scope VARCHAR(256),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);
