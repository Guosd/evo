insert into sys_func values(null, null, '系统管理', 'SYS', '/sys', null, null, '1', null, now(), null, now());
insert into sys_func values(null, '1', '用户管理', 'SYS_USER', '/sys', '/user', 'GET', '1', null, now(), null, now());
insert into sys_func values(null, '1', '用户管理', 'SYS_USER_ALL', '/sys', '/user/all', 'GET', '1', null, now(), null, now());
insert into sys_func values(null, '1', '用户管理', 'SYS_USER_PAGE', '/sys', '/user/page', 'GET', '1', null, now(), null, now());
insert into sys_func values(null, '1', '用户管理', 'SYS_USER_CREATE', '/sys', '/user', 'POST', '1', null, now(), null, now());
insert into sys_func values(null, '1', '用户管理', 'SYS_USER_UPDATE', '/sys', '/user', 'PUT', '1', null, now(), null, now());
insert into sys_func values(null, '1', '用户管理', 'SYS_USER_DELETE', '/sys', '/user', 'DELETE', '1', null, now(), null, now());
insert into sys_func values(null, '1', '角色管理', 'SYS_ROLE', '/sys', '/role', null, '1', null, now(), null, now());
insert into sys_func values(null, '1', '角色管理', 'SYS_ROLE_ALL', '/sys', '/role/all', 'GET', '1', null, now(), null, now());
insert into sys_func values(null, '1', '角色管理', 'SYS_ROLE_PAGE', '/sys', '/role/page', 'GET', '1', null, now(), null, now());
insert into sys_func values(null, '1', '角色管理', 'SYS_ROLE_CREATE', '/sys', '/role', 'POST', '1', null, now(), null, now());
insert into sys_func values(null, '1', '角色管理', 'SYS_ROLE_UPDATE', '/sys', '/role', 'PUT', '1', null, now(), null, now());
insert into sys_func values(null, '1', '角色管理', 'SYS_ROLE_DELETE', '/sys', '/role', 'DELETE', '1', null, now(), null, now());
insert into sys_func values(null, '1', '功能管理', 'SYS_FUNC', '/sys', '/func', null, '1', null, now(), null, now());
insert into sys_func values(null, '1', '功能管理', 'SYS_FUNC_ALL', '/sys', '/func/all', 'GET', '1', null, now(), null, now());
insert into sys_func values(null, '1', '功能管理', 'SYS_FUNC_PAGE', '/sys', '/func/page', 'GET', '1', null, now(), null, now());
insert into sys_func values(null, '1', '功能管理', 'SYS_FUNC_CREATE', '/sys', '/func', 'POST', '1', null, now(), null, now());
insert into sys_func values(null, '1', '功能管理', 'SYS_FUNC_UPDATE', '/sys', '/func', 'PUT', '1', null, now(), null, now());
insert into sys_func values(null, '1', '功能管理', 'SYS_FUNC_DELETE', '/sys', '/func', 'DELETE', '1', null, now(), null, now());

insert into sys_role values(null, '管理员', 'ADMIN', null, now(), null, now());

insert into sys_user values(null, 'admin', '', '管理员', 'ADMIN', 'admin@ritoinfo.com', '043112345678', '0', null, null, null, now(), null, now());

insert into sys_role_func values ('1', '5');
insert into sys_role_func values ('1', '6');
insert into sys_role_func values ('1', '7');
insert into sys_role_func values ('1', '8');
insert into sys_role_func values ('1', '9');
insert into sys_role_func values ('1', '10');
insert into sys_role_func values ('1', '11');
insert into sys_role_func values ('1', '12');
insert into sys_role_func values ('1', '13');
insert into sys_role_func values ('1', '14');
insert into sys_role_func values ('1', '15');
insert into sys_role_func values ('1', '16');
insert into sys_role_func values ('1', '17');
insert into sys_role_func values ('1', '18');
insert into sys_role_func values ('1', '19');
insert into sys_role_func values ('1', '20');
insert into sys_role_func values ('1', '21');
insert into sys_role_func values ('1', '22');
insert into sys_role_func values ('1', '23');

insert into sys_user_role values ('1', '1');


