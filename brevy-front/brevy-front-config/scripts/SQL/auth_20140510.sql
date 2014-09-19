prompt PL/SQL Developer import file
prompt Created on 2014年5月10日 by dell
set feedback off
set define off
prompt Dropping AP_ACCESS_PERM...
drop table AP_ACCESS_PERM cascade constraints;
prompt Dropping AP_APPLICATION...
drop table AP_APPLICATION cascade constraints;
prompt Dropping AP_GROUP...
drop table AP_GROUP cascade constraints;
prompt Dropping AP_MENU...
drop table AP_MENU cascade constraints;
prompt Dropping AP_OPER_PERM...
drop table AP_OPER_PERM cascade constraints;
prompt Dropping AP_REF_GROUP_ROLE...
drop table AP_REF_GROUP_ROLE cascade constraints;
prompt Dropping AP_REF_ROLE_ACCESS_PERM...
drop table AP_REF_ROLE_ACCESS_PERM cascade constraints;
prompt Dropping AP_REF_ROLE_MENU...
drop table AP_REF_ROLE_MENU cascade constraints;
prompt Dropping AP_REF_ROLE_OPER_PERM...
drop table AP_REF_ROLE_OPER_PERM cascade constraints;
prompt Dropping AP_REF_USER_APP...
drop table AP_REF_USER_APP cascade constraints;
prompt Dropping AP_REF_USER_GROUP...
drop table AP_REF_USER_GROUP cascade constraints;
prompt Dropping AP_REF_USER_ROLE...
drop table AP_REF_USER_ROLE cascade constraints;
prompt Dropping AP_ROLE...
drop table AP_ROLE cascade constraints;
prompt Dropping AP_SEQ...
drop table AP_SEQ cascade constraints;
prompt Dropping AP_USER...
drop table AP_USER cascade constraints;
prompt Creating AP_ACCESS_PERM...
create table AP_ACCESS_PERM
(
  id                    NUMBER not null,
  app_id                NUMBER not null,
  name                  VARCHAR2(128) not null,
  code                  VARCHAR2(64) not null,
  url_pattern           VARCHAR2(512) not null,
  authentication_filter VARCHAR2(64),
  authorization_filter  VARCHAR2(64),
  status                CHAR(1) not null,
  sort                  NUMBER default -1 not null,
  creator               VARCHAR2(30),
  create_time           TIMESTAMP(6),
  updator               VARCHAR2(30),
  update_time           TIMESTAMP(6)
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_ACCESS_PERM
  is '访问权限表，匹配角色及操作权限';
comment on column AP_ACCESS_PERM.id
  is '访问权限编号';
comment on column AP_ACCESS_PERM.app_id
  is '应用编号';
comment on column AP_ACCESS_PERM.name
  is '访问权限名称';
comment on column AP_ACCESS_PERM.code
  is '访问权限代码';
comment on column AP_ACCESS_PERM.url_pattern
  is '匹配的URL';
comment on column AP_ACCESS_PERM.authentication_filter
  is 'anon,authcBasic,authc,user及自定义';
comment on column AP_ACCESS_PERM.authorization_filter
  is 'perms,roles,ssl,rest,port及自定义';
comment on column AP_ACCESS_PERM.status
  is '访问权限状态。0-无效/1-有效';
comment on column AP_ACCESS_PERM.sort
  is '顺序-应用于FIRST MATCH WINS原则，主要针对通配符的情况';
comment on column AP_ACCESS_PERM.creator
  is '创建人';
comment on column AP_ACCESS_PERM.create_time
  is '创建时间';
comment on column AP_ACCESS_PERM.updator
  is '更新人';
comment on column AP_ACCESS_PERM.update_time
  is '更新时间';
create unique index Index_4 on AP_ACCESS_PERM (NAME)
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create unique index Index_8 on AP_ACCESS_PERM (CODE)
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table AP_ACCESS_PERM
  add constraint PK_AP_ACCESS_PERM primary key (ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table AP_ACCESS_PERM
  add constraint UNI_AP_ACCESS_PERM unique (CODE);

prompt Creating AP_APPLICATION...
create table AP_APPLICATION
(
  id          NUMBER not null,
  name        VARCHAR2(50) not null,
  code        VARCHAR2(20),
  type        VARCHAR2(6),
  desc_       VARCHAR2(500),
  status      CHAR(1) not null,
  creator     VARCHAR2(30),
  create_time TIMESTAMP(6),
  updator     VARCHAR2(30),
  update_time TIMESTAMP(6)
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_APPLICATION
  is '应用系统';
comment on column AP_APPLICATION.id
  is '应用编号';
comment on column AP_APPLICATION.name
  is '应用名称';
comment on column AP_APPLICATION.code
  is '应用代码';
comment on column AP_APPLICATION.type
  is '应用类型';
comment on column AP_APPLICATION.desc_
  is '应用描述';
comment on column AP_APPLICATION.status
  is '应用状态。0-无效/1-有效';
comment on column AP_APPLICATION.creator
  is '创建人';
comment on column AP_APPLICATION.create_time
  is '创建时间';
comment on column AP_APPLICATION.updator
  is '更新人';
comment on column AP_APPLICATION.update_time
  is '更新时间';
alter table AP_APPLICATION
  add constraint PK_AP_APPLICATION primary key (ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_GROUP...
create table AP_GROUP
(
  id          NUMBER not null,
  app_id      NUMBER not null,
  name        VARCHAR2(128) not null,
  code        VARCHAR2(20),
  type        VARCHAR2(6),
  desc_       VARCHAR2(512),
  status      CHAR(1) not null,
  creator     VARCHAR2(30),
  create_time TIMESTAMP(6),
  updator     VARCHAR2(30),
  update_time TIMESTAMP(6)
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_GROUP
  is '组';
comment on column AP_GROUP.id
  is '组编号';
comment on column AP_GROUP.app_id
  is '应用编号';
comment on column AP_GROUP.name
  is '组名称';
comment on column AP_GROUP.code
  is '组代码';
comment on column AP_GROUP.type
  is '组类型';
comment on column AP_GROUP.desc_
  is '组描述';
comment on column AP_GROUP.status
  is '用户组状态。0-无效/1-有效';
comment on column AP_GROUP.creator
  is '创建人';
comment on column AP_GROUP.create_time
  is '创建时间';
comment on column AP_GROUP.updator
  is '更新人';
comment on column AP_GROUP.update_time
  is '更新时间';
create unique index Index_2 on AP_GROUP (NAME)
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table AP_GROUP
  add constraint PK_AP_GROUP primary key (ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_MENU...
create table AP_MENU
(
  id              NUMBER not null,
  app_id          NUMBER not null,
  parent_id       NUMBER default -1 not null,
  code            VARCHAR2(20),
  url             VARCHAR2(512),
  module_location VARCHAR2(512),
  name            VARCHAR2(64) not null,
  leaf            CHAR(1) not null,
  icon            VARCHAR2(30),
  hierarchy       NUMBER not null,
  status          CHAR(1) not null,
  sort            NUMBER not null,
  creator         VARCHAR2(30),
  create_time     TIMESTAMP(6),
  updator         VARCHAR2(30),
  update_time     TIMESTAMP(6)
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_MENU
  is '菜单表';
comment on column AP_MENU.id
  is '菜单编号';
comment on column AP_MENU.app_id
  is '应用编号';
comment on column AP_MENU.parent_id
  is '父菜单编号。值-1定为顶级菜单';
comment on column AP_MENU.code
  is '菜单代码';
comment on column AP_MENU.url
  is '以http链接方式加载';
comment on column AP_MENU.module_location
  is '以模块方式加载';
comment on column AP_MENU.name
  is '菜单名称';
comment on column AP_MENU.leaf
  is '叶子菜单。0-否/1-是';
comment on column AP_MENU.icon
  is '菜单图标';
comment on column AP_MENU.hierarchy
  is '菜单层级';
comment on column AP_MENU.status
  is '菜单状态。0-无效/1-有效';
comment on column AP_MENU.sort
  is '显示顺序';
comment on column AP_MENU.creator
  is '创建人';
comment on column AP_MENU.create_time
  is '创建时间';
comment on column AP_MENU.updator
  is '更新人';
comment on column AP_MENU.update_time
  is '更新时间';
alter table AP_MENU
  add constraint PK_AP_MENU primary key (ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_OPER_PERM...
create table AP_OPER_PERM
(
  id              NUMBER not null,
  app_id          NUMBER not null,
  name            VARCHAR2(128) not null,
  code            VARCHAR2(64) not null,
  authorized_oper VARCHAR2(256) not null,
  status          CHAR(1) not null,
  sort            NUMBER default -1 not null,
  creator         VARCHAR2(30),
  create_time     TIMESTAMP(6),
  updator         VARCHAR2(30),
  update_time     TIMESTAMP(6)
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_OPER_PERM
  is '操作权限表';
comment on column AP_OPER_PERM.id
  is '操作权限编号';
comment on column AP_OPER_PERM.app_id
  is '应用编号';
comment on column AP_OPER_PERM.name
  is '操作权限名称';
comment on column AP_OPER_PERM.code
  is '操作权限代码';
comment on column AP_OPER_PERM.authorized_oper
  is '授权操作';
comment on column AP_OPER_PERM.status
  is '操作权限状态。0-无效/1-有效';
comment on column AP_OPER_PERM.sort
  is '顺序-应用于FIRST MATCH WINS原则，主要针对通配符的情况';
comment on column AP_OPER_PERM.creator
  is '创建人';
comment on column AP_OPER_PERM.create_time
  is '创建时间';
comment on column AP_OPER_PERM.updator
  is '更新人';
comment on column AP_OPER_PERM.update_time
  is '更新时间';
create unique index Index_5 on AP_OPER_PERM (NAME)
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create unique index Index_9 on AP_OPER_PERM (CODE)
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table AP_OPER_PERM
  add constraint PK_AP_OPER_PERM primary key (ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table AP_OPER_PERM
  add constraint UNI_AP_OPER_PERM unique (CODE);

prompt Creating AP_REF_GROUP_ROLE...
create table AP_REF_GROUP_ROLE
(
  group_id NUMBER not null,
  role_id  NUMBER not null
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_REF_GROUP_ROLE
  is '组包含的角色';
comment on column AP_REF_GROUP_ROLE.group_id
  is '组编号';
comment on column AP_REF_GROUP_ROLE.role_id
  is '角色编号';
alter table AP_REF_GROUP_ROLE
  add constraint PK_AP_REF_GROUP_ROLE primary key (GROUP_ID, ROLE_ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_ROLE_ACCESS_PERM...
create table AP_REF_ROLE_ACCESS_PERM
(
  role_id        NUMBER not null,
  access_perm_id NUMBER not null
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_REF_ROLE_ACCESS_PERM
  is '角色所拥有的访问权限';
comment on column AP_REF_ROLE_ACCESS_PERM.role_id
  is '角色编号';
comment on column AP_REF_ROLE_ACCESS_PERM.access_perm_id
  is '访问权限编号';
alter table AP_REF_ROLE_ACCESS_PERM
  add constraint PK_AP_REF_ROLE_ACCESS_PERM primary key (ROLE_ID, ACCESS_PERM_ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_ROLE_MENU...
create table AP_REF_ROLE_MENU
(
  role_id NUMBER not null,
  menu_id NUMBER not null
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_REF_ROLE_MENU
  is '角色所拥有的菜单';
comment on column AP_REF_ROLE_MENU.role_id
  is '角色编号';
comment on column AP_REF_ROLE_MENU.menu_id
  is '菜单编号';
alter table AP_REF_ROLE_MENU
  add constraint PK_AP_REF_ROLE_MENU primary key (ROLE_ID, MENU_ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_ROLE_OPER_PERM...
create table AP_REF_ROLE_OPER_PERM
(
  role_id      NUMBER not null,
  oper_perm_id NUMBER not null
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_REF_ROLE_OPER_PERM
  is '角色所拥有的操作权限';
comment on column AP_REF_ROLE_OPER_PERM.role_id
  is '角色编号';
comment on column AP_REF_ROLE_OPER_PERM.oper_perm_id
  is '操作权限编号';
alter table AP_REF_ROLE_OPER_PERM
  add constraint PK_AP_REF_ROLE_OPER_PERM primary key (ROLE_ID, OPER_PERM_ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_USER_APP...
create table AP_REF_USER_APP
(
  app_id  NUMBER not null,
  user_id NUMBER not null
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_REF_USER_APP
  is '用户所属应用系统';
comment on column AP_REF_USER_APP.app_id
  is '应用编号';
comment on column AP_REF_USER_APP.user_id
  is '用户编号';
alter table AP_REF_USER_APP
  add constraint PK_AP_REF_USER_APP primary key (APP_ID, USER_ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_USER_GROUP...
create table AP_REF_USER_GROUP
(
  user_id  NUMBER not null,
  group_id NUMBER not null
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_REF_USER_GROUP
  is '用户拥有的组';
comment on column AP_REF_USER_GROUP.user_id
  is '用户编号';
comment on column AP_REF_USER_GROUP.group_id
  is 'v';
alter table AP_REF_USER_GROUP
  add constraint PK_AP_REF_USER_GROUP primary key (USER_ID, GROUP_ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_USER_ROLE...
create table AP_REF_USER_ROLE
(
  user_id NUMBER not null,
  role_id NUMBER not null
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_REF_USER_ROLE
  is '用户所拥有的权限';
comment on column AP_REF_USER_ROLE.user_id
  is '用户编号';
comment on column AP_REF_USER_ROLE.role_id
  is '角色编号';
alter table AP_REF_USER_ROLE
  add constraint PK_AP_REF_USER_ROLE primary key (USER_ID, ROLE_ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_ROLE...
create table AP_ROLE
(
  id          NUMBER not null,
  app_id      NUMBER not null,
  name        VARCHAR2(128) not null,
  code        VARCHAR2(64) not null,
  type        CHAR(1) not null,
  desc_       VARCHAR2(500),
  status      CHAR(1) not null,
  creator     VARCHAR2(30),
  create_time TIMESTAMP(6),
  updator     VARCHAR2(30),
  update_time TIMESTAMP(6)
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_ROLE
  is '角色表';
comment on column AP_ROLE.id
  is '角色编号';
comment on column AP_ROLE.app_id
  is '应用编号';
comment on column AP_ROLE.name
  is '角色名称';
comment on column AP_ROLE.code
  is '角色代码-shiro过滤器roles的参数';
comment on column AP_ROLE.type
  is '预留';
comment on column AP_ROLE.desc_
  is '角色描述';
comment on column AP_ROLE.status
  is '角色状态。0-无效/1-有效';
comment on column AP_ROLE.creator
  is '创建人';
comment on column AP_ROLE.create_time
  is '创建时间';
comment on column AP_ROLE.updator
  is '更新人';
comment on column AP_ROLE.update_time
  is '更新时间';
create unique index Index_10 on AP_ROLE (CODE, TYPE)
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
create unique index Index_6 on AP_ROLE (NAME, TYPE)
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table AP_ROLE
  add constraint PK_AP_ROLE primary key (ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_SEQ...
create table AP_SEQ
(
  seq_name  VARCHAR2(32) not null,
  seq_value NUMBER not null
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_SEQ
  is '基于Table的Sequence';
alter table AP_SEQ
  add constraint PK_AP_SEQ primary key (SEQ_NAME)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_USER...
create table AP_USER
(
  id            NUMBER not null,
  username      VARCHAR2(30) not null,
  password      VARCHAR2(32) not null,
  user_type     VARCHAR2(6),
  ch_name       NVARCHAR2(20),
  ch_spell_name VARCHAR2(60),
  first_name    VARCHAR2(20),
  last_name     VARCHAR2(20),
  gender        CHAR(1),
  id_type       VARCHAR2(6),
  id_no         VARCHAR2(30),
  status        CHAR(1) not null,
  creator       VARCHAR2(30),
  create_time   TIMESTAMP(6),
  updator       VARCHAR2(30),
  update_time   TIMESTAMP(6)
)
tablespace AUTH_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
comment on table AP_USER
  is '用户表';
comment on column AP_USER.id
  is '用户编号';
comment on column AP_USER.username
  is '用户名';
comment on column AP_USER.password
  is '密码。md5摘要';
comment on column AP_USER.user_type
  is '用户类型';
comment on column AP_USER.ch_name
  is '中文姓名';
comment on column AP_USER.ch_spell_name
  is '姓名拼音';
comment on column AP_USER.first_name
  is '英文名';
comment on column AP_USER.last_name
  is '英文姓';
comment on column AP_USER.gender
  is '性别。M-男/F-女';
comment on column AP_USER.id_type
  is '证件类型';
comment on column AP_USER.id_no
  is '证件号码';
comment on column AP_USER.status
  is '用户状态。0-无效/1-有效';
comment on column AP_USER.creator
  is '创建人';
comment on column AP_USER.create_time
  is '创建时间';
comment on column AP_USER.updator
  is '更新人';
comment on column AP_USER.update_time
  is '更新时间';
create unique index Index_1 on AP_USER (USERNAME)
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );
alter table AP_USER
  add constraint PK_AP_USER primary key (ID)
  using index 
  tablespace AUTH_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    minextents 1
    maxextents unlimited
  );

prompt Loading AP_ACCESS_PERM...
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (1, 1000, '管理员-AAP', 'AdminAAP', '/**/*.*', 'auth', 'roles', '1', 999999999, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (2, 1000, 'Pack访问', 'PackAccess', '/**/*.pack', 'anon', null, '1', 999999998, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (3, 1000, '首页访问', 'defaultAccess', '/default.html', 'anon', null, '1', 999999997, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (4, 1000, 'GIF访问', 'GIFAccess', '/**/*.gif', 'anon', null, '1', 999999996, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (5, 1000, '模块加载器访问', 'LoadModuleAccess', '/**/loadModule.jsp', 'anon', null, '1', 999999995, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (6, 1000, 'Js加载访问', 'JsLoadAccess', '/**/loadJs.jsp', 'anon', null, '1', 999999994, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (7, 1000, 'Css加载访问', 'CssLoadAccess', '/**/loadCss.jsp', 'anon', null, '1', 999999993, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (8, 1000, 'PNG访问', 'PNGAccess', '/**/*.png', 'anon', null, '1', 999999992, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (9, 1000, 'Captcha访问', 'CaptchaAccess', '/**/captcha.image', 'anon', null, '1', 999999991, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (10, 1000, '登录资源访问', 'LoginAccess', '/login/*.json', 'anon', null, '1', 999999990, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (29, 1000, '未授权页面访问', 'UnauthorizedAccess', '/authz/unauthorized.html', 'anon', null, '1', 999999988, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (30, 1000, 'JPEG访问', 'JPEGAccess', '/**/*.jpg', 'anon', null, '1', 999999987, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (11, 1000, '初始鉴权访问', 'AuthAccess', '/auth/*.json', 'anon', null, '1', 999999989, null, null, null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (31, 1000, '通用加载器访问', 'LoaderAccess', '/**/Loader.jsp', 'auth', 'roles', '1', 999999986, null, null, null, null);
commit;
prompt 14 records loaded
prompt Loading AP_APPLICATION...
insert into AP_APPLICATION (id, name, code, type, desc_, status, creator, create_time, updator, update_time)
values (998, '测试管理系统', 'TEST', null, null, '1', null, null, null, null);
insert into AP_APPLICATION (id, name, code, type, desc_, status, creator, create_time, updator, update_time)
values (999, '调试管理系统', 'DEBUG', null, null, '1', null, null, null, null);
insert into AP_APPLICATION (id, name, code, type, desc_, status, creator, create_time, updator, update_time)
values (1000, '本地应用系统', 'LOCAL', null, null, '1', null, null, null, null);
commit;
prompt 3 records loaded
prompt Loading AP_GROUP...
prompt Table is empty
prompt Loading AP_MENU...
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (1, 1000, -1, null, null, '/WEB-INF/views/', '系统管理', '0', 'icon-server', 1, '1', 2, null, null, 'admin', to_timestamp('24-04-2014 14:44:25.764000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (2, 1000, 1, null, null, '/WEB-INF/views/', '权限管理', '0', null, 2, '1', 1, null, null, 'admin', to_timestamp('28-03-2014 13:26:40.236000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (3, 1000, 115, null, null, '/WEB-INF/views/systemMGR/authCFG/funcMenu/module.xml', '功能菜单配置', '1', 'icon-script_gear', 4, '1', 1, null, null, 'admin', to_timestamp('09-05-2014 11:54:00.274000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (4, 1000, 115, null, null, '/WEB-INF/views/systemMGR/authCFG/accessAuth/module.xml', '访问权限配置', '1', 'icon-world', 4, '1', 2, null, null, 'admin', to_timestamp('09-05-2014 11:53:25.897000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (5, 1000, 115, null, null, '/WEB-INF/views/systemMGR/authCFG/operAuth/module.xml', '操作权限配置', '1', 'icon-lock', 4, '1', 3, null, null, 'admin', to_timestamp('09-05-2014 11:54:08.396000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (6, 1000, 2, null, null, '/WEB-INF/views/', '角色配置', '1', 'icon-chart_organisation', 3, '1', 2, null, null, 'admin', to_timestamp('09-05-2014 15:55:56.816000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (7, 1000, 2, null, null, '/WEB-INF/views/', '用户配置', '1', 'icon-user_edit', 3, '1', 4, null, null, 'admin', to_timestamp('09-05-2014 11:56:54.569000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (8, 1000, 2, null, null, '/WEB-INF/views/', '用户组配置', '1', 'icon-group_gear', 3, '1', 3, null, null, 'admin', to_timestamp('09-05-2014 11:56:54.569000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (9, 1000, 2, null, null, '/WEB-INF/views/', '应用系统配置', '1', 'icon-application_edit', 3, '1', 5, null, null, 'admin', to_timestamp('09-05-2014 11:56:54.522000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (10, 1000, -1, null, null, '/WEB-INF/views/', '控件展示', '0', 'icon-application_view_tile', 1, '1', 1, null, null, 'admin', to_timestamp('29-04-2014 14:45:40.512000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (11, 1000, 10, null, null, '/WEB-INF/views/', '网格(GRID)', '0', null, 2, '1', 1, null, null, null, null);
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (12, 1000, 11, null, null, '/WEB-INF/views/', '常规网格', '1', 'icon-application_form_edit', 3, '1', 1, null, null, 'admin', to_timestamp('24-04-2014 15:57:13.357000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (13, 1000, 11, null, null, '/WEB-INF/views/', '分页网格', '1', 'icon-application_delete', 3, '1', 2, null, null, 'admin', to_timestamp('24-04-2014 15:57:13.497000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (115, 1000, 2, null, null, '/WEB-INF/views/', '功能配置', '0', 'icon-brick', 3, '1', 1, 'admin', to_timestamp('14-03-2014 13:28:00.544000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('09-05-2014 11:56:11.705000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 14 records loaded
prompt Loading AP_OPER_PERM...
insert into AP_OPER_PERM (id, app_id, name, code, authorized_oper, status, sort, creator, create_time, updator, update_time)
values (1, 1000, '管理员-AOP', 'adminAOP', 'oper:all', '1', 99999999, null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading AP_REF_GROUP_ROLE...
prompt Table is empty
prompt Loading AP_REF_ROLE_ACCESS_PERM...
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 1);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 31);
commit;
prompt 2 records loaded
prompt Loading AP_REF_ROLE_MENU...
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 1);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 2);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 3);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 4);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 5);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 6);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 7);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 8);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 9);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 10);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 11);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 12);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 13);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 115);
commit;
prompt 14 records loaded
prompt Loading AP_REF_ROLE_OPER_PERM...
insert into AP_REF_ROLE_OPER_PERM (role_id, oper_perm_id)
values (1, 1);
commit;
prompt 1 records loaded
prompt Loading AP_REF_USER_APP...
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 1);
commit;
prompt 1 records loaded
prompt Loading AP_REF_USER_GROUP...
prompt Table is empty
prompt Loading AP_REF_USER_ROLE...
insert into AP_REF_USER_ROLE (user_id, role_id)
values (1, 1);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (1, 2);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (1, 3);
commit;
prompt 3 records loaded
prompt Loading AP_ROLE...
insert into AP_ROLE (id, app_id, name, code, type, desc_, status, creator, create_time, updator, update_time)
values (1, 1000, '管理员', 'Admin', '1', null, '1', null, null, null, null);
commit;
prompt 1 records loaded
prompt Loading AP_SEQ...
insert into AP_SEQ (seq_name, seq_value)
values ('AP_APPLICATION_SEQ', 1001);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_USER_SEQ', 2);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_ROLE_SEQ', 4);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_GROUP_SEQ', 1);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_ACCESS_PERM_SEQ', 32);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_OPER_PERM_SEQ', 4);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_MENU_SEQ', 120);
commit;
prompt 7 records loaded
prompt Loading AP_USER...
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time)
values (1, 'admin', '96e79218965eb72c92a549dd5a330112', null, null, null, null, null, null, null, null, '1', null, null, null, null);
commit;
prompt 1 records loaded
set feedback on
set define on
prompt Done.
