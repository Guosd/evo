insert into config_properties values (null, 'master', 'evo', 'default', 'evo.common.password.salt', '8', '盐值 [Default 8]', '1', now(), '1', now());

insert into config_properties values (null, 'master', 'evo', 'default', 'evo.zuul.auth.client.id', 'client_1', 'OAuth2 表oauth_client_details.client_id', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.zuul.auth.client.secret', 'secret_1', 'OAuth2 表oauth_client_details.client_secret', '1', now(), '1', now());

insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.jwt.issuer', 'http://evo.com', '发行人 [Default http://evo.com]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.jwt.signing-key', 'ec9f563404c511e9b8790a151ed9f971', '签名', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.jwt.expirationTime', '1440', '过期时间 单位分钟 [Default 60 * 24 * 1]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.jwt.refreshExpirationTime', '4320', '刷新过期时间 [Default 60 * 24 * 3]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.path.login', '/ui/comm/login', '登录页面', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.path.excludes', '/auth/username-password/login, /auth/mobile-number/verify-code, /oauth2/oauth/token', '排除访问控制', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.verify-code.expiration-time', '2', '过期时间 单位分钟 [Default 2]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.verify-code.type', 'random', 'random 随机; fix 固定 [Default random]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.verify-code.length', '6', '当 type = random 时，验证码位数 [Default 6]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.verify-code.value', '123456', '当 type= fix 时，验证码值，[Default 123456]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.authorization.service-id', 'evo-sp-oauth2-authorization', '认证服务的spring.application.name [Default evo-sp-oauth2-authorization]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.user-details.service-id', 'evo-sp-sys', '用户明细服务的spring.application.name [Default evo-sp-sys]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.user-details.username-uri', '/user/username', '通过用户登录名获取用户信息的 HTTP REST URI [Default /user/username]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.user-details.mobile-number-uri', '/user/mobile-number', '通过手机获取用户信息的 HTTP REST URI [Default /user/mobile-number]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.user-details.update-login-info-uri', '/user/login-info', '更新登录用户信息的 HTTP REST URI [Default /user/login-info]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.rbac.service-id', 'evo-sp-sys', '提供RBAC权限验证服务的spring.application.name [Default evo-sp-sys]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.auth.rbac.uri', '/permission/check', '校验权限的 HTTP REST URI [Default /permission/check]', '1', now(), '1', now());

insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.framework.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_framework?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.framework.datasource.username', 'evo_framework', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.framework.datasource.password', 'evo_framework', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.demo.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_demo?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.demo.datasource.username', 'evo_demo', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.demo.datasource.password', 'evo_demo', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sys.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_sys?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sys.datasource.username', 'evo_sys', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sys.datasource.password', 'evo_sys', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.datadict.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_datadict?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.datadict.datasource.username', 'evo_datadict', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.datadict.datasource.password', 'evo_datadict', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.activiti.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_activiti?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.activiti.datasource.username', 'evo_activiti', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.activiti.datasource.password', 'evo_activiti', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sms.enabled', 'false', 'true 开启; false 关闭 [Default false]', '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sms.address', 'http://10.21.111.71:9087/smserver/submitMessage', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sms.comCode', 'ZBKG', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sms.orgCode', '00000000', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sms.businessNo', 'ZBKG00000000DJCFAPP001', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sms.businessTypeCode', '04', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sms.channel', '0', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.sp.sms.ip', '10.20.133.30', null, '1', now(), '1', now());

insert into zuul_route values (null, 'evo-sp-auth-authorization', '/auth/**', 'evo-sp-auth-authorization', null, '1', null, null, '1', null, '1', now(), '1', now());
insert into zuul_route values (null, 'evo-sp-oauth2-authorization', '/oauth2/**', 'evo-sp-oauth2-authorization', null, '1', null, null, '1', null, '1', now(), '1', now());




insert into config_properties values (null, 'master', 'evo', 'default', 'redis.companyPrefix', 'ZBKG', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.redis.cluster.nodes', '10.20.133.85:7000, 10.20.133.85:7002, 10.20.133.85:7003, 10.20.133.85:7004, 10.20.133.85:7005', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.addresses', '10.20.133.40:5672, 10.20.133.43:5672', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.host', '10.20.133.43', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.port', '5672', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.virtual-host', 'vhost_eco', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.username', 'eco', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.spring.rabbitmq.password', 'eco', null, '1', now(), '1', now());
insert into config_properties values (null, 'master', 'evo', 'default', 'evo.ribbon.NFLoadBalancerRuleClassName', 'com.netflix.loadbalancer.RoundRobinRule', null, '1', now(), '1', now());

