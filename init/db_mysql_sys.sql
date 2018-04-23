CREATE DATABASE evo_sys DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

insert into sys_func values(null, '1', null, '/user', 'GET', '1', null, now(), null, now());
insert into sys_func values(null, '1', null, '/user/all', 'GET', '2', null, now(), null, now());
insert into sys_func values(null, '1', null, '/user/page', 'GET', '3', null, now(), null, now());
insert into sys_func values(null, '1', null, '/user', 'POST', '4', null, now(), null, now());
insert into sys_func values(null, '1', null, '/user', 'PUT', '5', null, now(), null, now());
insert into sys_func values(null, '1', null, '/user', 'DELETE', '6', null, now(), null, now());
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
insert into sys_role_func values ('1', '24');
insert into sys_role_func values ('1', '25');
insert into sys_role_func values ('1', '26');
insert into sys_role_func values ('1', '27');
insert into sys_role_func values ('1', '28');
insert into sys_role_func values ('1', '29');
insert into sys_role_func values ('1', '30');
insert into sys_role_func values ('1', '31');
insert into sys_role_func values ('1', '32');
insert into sys_role_func values ('1', '33');
insert into sys_role_func values ('1', '34');
insert into sys_role_func values ('1', '35');

insert into sys_user_role values ('1', '1');


