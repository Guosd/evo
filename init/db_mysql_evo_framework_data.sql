insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.jwt.issuer', 'http://evo.com', '发行人 [Default http://evo.com]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.jwt.signing-key', 'ec9f563404c511e9b8790a151ed9f971', '签名', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.jwt.secret-Key', '6q0SYc3w5Y28YqAtYi0XTT95rOWrybbH', 'Claims密钥，必须固定为16位或者32位', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.jwt.expiration-time', '1440', '访问令牌过期时间 单位分钟 [Default 60 * 24 * 1]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.jwt.refresh-expiration-time', '4320', '刷新令牌过期时间 单位分钟 [Default 60 * 24 * 3]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.jwt.old-expiration-time', '30', '新令牌期间，旧令牌作为临时过渡令牌的过期时间 单位秒 [Default 30]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.path.login', '/ui/comm/login', '登录页面', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.path.excludes', '/auth/username-password/login, /auth/mobile-number/verify-code, /auth/mobile-number/login, /oauth2/oauth/token', '排除访问控制URI数组', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.verify-code.expiration-time', '2', '过期时间 单位分钟 [Default 2]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.verify-code.type', 'random', 'random 随机; fix 固定 [Default random]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.verify-code.length', '6', '当 type = random 时，验证码位数 [Default 6]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.verify-code.value', '123456', '当 type= fix 时，验证码值，[Default 123456]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.authorization.service-id', 'evo-oauth2', '认证服务的spring.application.name [Default evo-oauth2]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.iam.service-id', 'evo-sys', '提供身份识别与访问管理服务的spring.application.name [Default evo-sys]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.iam.user-details.username-uri', '/user/username', '通过用户登录名获取用户信息的 HTTP REST URI [Default /user/username]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.iam.user-details.mobile-number-uri', '/user/mobile-number', '通过手机获取用户信息的 HTTP REST URI [Default /user/mobile-number]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.iam.user-details.update-login-info-uri', '/user/login-info', '更新登录用户信息的 HTTP REST URI [Default /user/login-info]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.auth.iam.rbac.uri', '/permission/check', '校验权限的 HTTP REST URI [Default /permission/check]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.hibernate.dialect', 'org.hibernate.dialect.MySQLDialect', '数据库方言 [Default org.hibernate.dialect.MySQLDialect]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.hibernate.show-sql', 'true', '是否显示SQL [Default true]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.hibernate.format-sql', 'false', '是否格式化SQL [Default false]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.hibernate.packages', 'com.github.framework.evo.demo', '扫描实体包 [Default com.github.framework.evo.demo]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.rabbitmq.addresses', '192.168.18.132:5672', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.rabbitmq.host', '192.168.18.132', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.rabbitmq.port', '5672', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.rabbitmq.virtual-host', 'evo_vhost', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.rabbitmq.username', 'evo', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.rabbitmq.password', 'evo', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.redis.company-prefix', 'EVO', '公司前缀 [Default EVO]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.redis.cluster.nodes', '192.168.18.129:7001, 192.168.18.129:7002, 192.168.18.129:7003, 192.168.18.129:7004, 192.168.18.129:7005, 192.168.18.129:7006', 'Redis集群', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.ribbon.NFLoadBalancerRuleClassName', 'com.netflix.loadbalancer.RoundRobinRule', '负载均衡策略', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.password.strength', '10', 'BCrypt强哈希函数强度。强度参数越大，散列密码所需的工作(以指数形式)就越多 [Default 10]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.sms.enabled', 'false', 'true 开启; false 关闭 [Default false]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.zuul.auth.client.id', 'client_1', 'OAuth2 表oauth_client_details.client_id', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.zuul.auth.client.secret', 'secret_1', 'OAuth2 表oauth_client_details.client_secret', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.session.user-context.enabled', 'true', '可以通过SessionHolder.getUserContext()获取当前用户 [Default true]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.controller.docker-swarm.host', '10.20.196.19', 'Docker Swarm Http REST API host [Default localhost]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.controller.docker-swarm.port', '2375', 'Docker Swarm Http REST API port [Default 2375]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.controller.docker-swarm.network.name', 'evo-overlay', 'Overlay网络名称 [Default evo-overlay]', '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.controller.config.service-id', 'evo-config', '配置中心的 Spring Application Name [Default evo-config]', '1', now(), '1', now());
# datasource
insert into config_property values (null, 'master', 'evo', 'default', 'evo.framework.datasource.url', 'jdbc:mysql://192.168.18.134:3306/evo_framework?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.framework.datasource.username', 'evo_framework', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.framework.datasource.password', 'evo_framework', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.sample.datasource.url', 'jdbc:mysql://192.168.18.134:3306/evo_sample?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.sample.datasource.username', 'evo_sample', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.sample.datasource.password', 'evo_sample', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.sys.datasource.url', 'jdbc:mysql://192.168.18.134:3306/evo_sys?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.sys.datasource.username', 'evo_sys', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.sys.datasource.password', 'evo_sys', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.datadict.datasource.url', 'jdbc:mysql://192.168.18.134:3306/evo_datadict?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.datadict.datasource.username', 'evo_datadict', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.datadict.datasource.password', 'evo_datadict', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.activiti.datasource.url', 'jdbc:mysql://192.168.18.134:3306/evo_activiti?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.activiti.datasource.username', 'evo_activiti', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.activiti.datasource.password', 'evo_activiti', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.flowable.datasource.url', 'jdbc:mysql://192.168.18.134:3306/evo_flowable?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.flowable.datasource.username', 'evo_flowable', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'default', 'evo.flowable.datasource.password', 'evo_flowable', null, '1', now(), '1', now());

insert into config_property values (null, 'master', 'evo', 'blue', 'evo.rabbitmq.addresses', '192.168.204.131:5672', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'blue', 'evo.rabbitmq.host', '192.168.204.131', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'blue', 'evo.redis.cluster.nodes', '192.168.204.132:7001, 192.168.204.132:7002, 192.168.204.132:7003, 192.168.204.132:7004, 192.168.204.132:7005, 192.168.204.132:7006', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'blue', 'evo.framework.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_framework?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'blue', 'evo.sample.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_sample?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'blue', 'evo.sys.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_sys?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'blue', 'evo.datadict.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_datadict?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'blue', 'evo.activiti.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_activiti?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'blue', 'evo.flowable.datasource.url', 'jdbc:mysql://192.168.204.130:3306/evo_flowable?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());

insert into config_property values (null, 'master', 'evo', 'sit', 'evo.rabbitmq.addresses', '10.21.127.29:5672', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'sit', 'evo.rabbitmq.host', '10.21.127.29', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'sit', 'evo.redis.cluster.nodes', '10.21.127.29:7001, 10.21.127.29:7002, 10.21.127.29:7003, 10.21.127.29:7004, 10.21.127.29:7005, 10.21.127.29:7006', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'sit', 'evo.framework.datasource.url', 'jdbc:mysql://10.21.127.29:3306/evo_framework?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'sit', 'evo.sample.datasource.url', 'jdbc:mysql://10.21.127.29:3306/evo_sample?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'sit', 'evo.sys.datasource.url', 'jdbc:mysql://10.21.127.29:3306/evo_sys?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'sit', 'evo.datadict.datasource.url', 'jdbc:mysql://10.21.127.29:3306/evo_datadict?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'sit', 'evo.activiti.datasource.url', 'jdbc:mysql://10.21.127.29:3306/evo_activiti?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());
insert into config_property values (null, 'master', 'evo', 'sit', 'evo.flowable.datasource.url', 'jdbc:mysql://10.21.127.29:3306/evo_flowable?useUnicode=true&characterEncoding=UTF8&useSSL=false&allowPublicKeyRetrieval=true', null, '1', now(), '1', now());


insert into zuul_route values (null, 'evo-controller', '/controller/**', 'evo-controller', null, '1', null, null, '1', null, '1', now(), '1', now());
insert into zuul_route values (null, 'evo-auth', '/auth/**', 'evo-auth', null, '1', null, null, '1', null, '1', now(), '1', now());
insert into zuul_route values (null, 'evo-oauth2', '/oauth2/**', 'evo-oauth2', null, '1', null, null, '1', null, '1', now(), '1', now());
insert into zuul_route values (null, 'evo-sys', '/sys/**', 'evo-sys', null, '1', null, null, '1', null, '1', now(), '1', now());


insert into oauth_client_details values ('client_1', null, '$08$ykt.Bl5oKvERSruMfI4LS.otlcTepWGPolPuuk4ke9yauObbOsKri', 'all', 'authorization_code,password,mnvc,refresh_token', null, null, '43200', '259200', null, null);


