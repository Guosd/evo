CREATE DATABASE evo_framework DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE USER 'evo_framework'@'%' IDENTIFIED BY 'evo_framework';
GRANT ALL ON evo_framework.* TO 'evo_framework'@'%';

FLUSH PRIVILEGES;

create table config_properties
(
  id int auto_increment
    primary key,
  label varchar(255) null,
  application varchar(255) null,
  profile varchar(255) null,
  key_ varchar(255) null,
  value text null,
  comment varchar(255) null,
  create_by int null,
  create_time datetime null,
  update_by int null,
  update_time datetime null
);

create table zuul_route
(
  id int auto_increment
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
  create_by int null,
  create_time datetime null,
  update_by int null,
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

insert into config_properties values (null, 'master', 'evo', 'default', 'jwt.algorithm', 'HS512', '加密方式', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'jwt.issuer', 'http://ritoinfo.com', '发行人', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'jwt.signingKey', '1580fcb1746e468095b1bc24b6fb94b8', '签名', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'jwt.expirationTime', '4320', '过期时间 分钟 60 * 24 * 3', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'jwt.refreshExpirationTime', '5760', '刷新过期时间 分钟 60 * 24 * 4', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'jwt.header', 'X-Authorization', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'password.salt', '8', '密码撒盐位数', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'auth.loginPath', '/ui/comm/login', '登录URI', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'auth.excludePaths', '/ui/comm/login', '排除令牌验证URI', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'auth.verifyCode.type', 'fix', 'random 随机; fix 固定', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'auth.verifyCode.length', '6', '当 type = random 时，验证码位数', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'auth.verifyCode.value', '123456', '当 type= fix 时， 验证码值', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'auth.verifyCode.expirationTime', '2', '过期时间 分钟', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'sms.address', 'http://10.21.111.71:9087/smserver/submitMessage', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'sms.comCode', 'ZBKG', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'sms.orgCode', '00000000', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'sms.businessNo', 'ZBKG00000000DJCFAPP001', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'sms.businessTypeCode', '04', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'sms.channel', '0', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'sms.ip', '10.20.133.30', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'redis.companyPrefix', 'ZBKG', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.redis.cluster.nodes', '10.20.133.85:7000, 10.20.133.85:7002, 10.20.133.85:7003, 10.20.133.85:7004, 10.20.133.85:7005', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.addresses', '127.0.0.1:5672', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.host', '127.0.0.1', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.port', '5672', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.virtual-host', 'evo_vhost', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.username', 'evo', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.password', 'evo', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.ribbon.NFLoadBalancerRuleClassName', 'com.netflix.loadbalancer.RoundRobinRule', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.framework.datasource.url', 'jdbc:mysql://127.0.0.1:3306/evo_framework?useUnicode=true&characterEncoding=UTF8&useSSL=false', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.framework.datasource.username', 'evo_framework', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.framework.datasource.password', 'evo_framework', null, '1', now(), '1', now());

