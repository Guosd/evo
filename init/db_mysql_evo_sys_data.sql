insert into sys_user values (null, 'admin', '$2a$08$pdWJYgAsN6/3GT/I5zeSbeKChDXrNH/DrM5QkB1nSopsS8K0F1xIS', '管理员', 'EVO_ADMIN', 'admin@evo.com', '18643100000', 'YN00', null, null, null, null, '1', now(), '1', now());

insert into sys_role values (null, '管理员', 'EVO_ADMIN', '1', now(), '1', now());

insert into sys_micro values ('1', '基础服务', 'evo-sys', '/sys', '1', now(), '1', now());
insert into sys_micro values ('2', '示例服务', 'evo-sample', '/sample', '1', now(), '1', now());

insert into sys_func values ('1', '1', null, null, 'GET', '/**/*', '1', now(), '1', now());
insert into sys_func values ('2', '1', null, null, 'POST', '/**/*', '1', now(), '1', now());
insert into sys_func values ('3', '1', null, null, 'PUT', '/**/*', '1', now(), '1', now());
insert into sys_func values ('4', '1', null, null, 'DELETE', '/**/*', '1', now(), '1', now());
insert into sys_func values ('5', '2', null, null, 'GET', '/**/*', '1', now(), '1', now());
insert into sys_func values ('6', '2', null, null, 'POST', '/**/*', '1', now(), '1', now());
insert into sys_func values ('7', '2', null, null, 'PUT', '/**/*', '1', now(), '1', now());
insert into sys_func values ('8', '2', null, null, 'DELETE', '/**/*', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'GET', '/sys/user/*', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'POST', '/sys/user/page', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'POST', '/sys/user', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'PUT', '/sys/user', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'DELTET', '/sys/user/*', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'GET', '/sys/role/*', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'POST', '/sys/role/page', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'POST', '/sys/role', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'PUT', '/sys/role', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'DELTET', '/sys/role/*', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'GET', '/sys/func/*', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'POST', '/sys/func/page', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'POST', '/sys/func', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'PUT', '/sys/func', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'DELTET', '/sys/func/*', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'GET', '/sys/menu/*', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'POST', '/sys/menu/page', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'POST', '/sys/menu', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'PUT', '/sys/menu', '1', now(), '1', now());
insert into sys_func values (null, '2', null, null, 'DELTET', '/sys/menu/*', '1', now(), '1', now());

insert into sys_user_role values (1, 1);

insert into sys_role_func values (1, 1);
insert into sys_role_func values (1, 2);
insert into sys_role_func values (1, 3);
insert into sys_role_func values (1, 4);
insert into sys_role_func values (1, 5);
insert into sys_role_func values (1, 6);
insert into sys_role_func values (1, 7);
insert into sys_role_func values (1, 8);

insert into sys_dict values (null, '是', 'YN01', 'YES_NO', '1', '1', now(), '1', now());
insert into sys_dict values (null, '否', 'YN00', 'YES_NO', '2', '1', now(), '1', now());
insert into sys_dict values (null, '开启', 'ED01', 'ENABLE_DISABLED', '1', '1', now(), '1', now());
insert into sys_dict values (null, '关闭', 'ED00', 'ENABLE_DISABLED', '2', '1', now(), '1', now());
