prompt PL/SQL Developer import file
prompt Created on 2015年1月7日 by George
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
prompt Dropping CAD_DEMAND...
drop table CAD_DEMAND cascade constraints;
prompt Dropping CAD_DEMAND_ATTACH...
drop table CAD_DEMAND_ATTACH cascade constraints;
prompt Dropping CAD_DEMAND_HIS...
drop table CAD_DEMAND_HIS cascade constraints;
prompt Dropping CAD_DICT...
drop table CAD_DICT cascade constraints;
prompt Dropping CAD_DICT_DETAIL...
drop table CAD_DICT_DETAIL cascade constraints;
prompt Dropping CAD_GD...
drop table CAD_GD cascade constraints;
prompt Dropping CAD_GD_ATTACH...
drop table CAD_GD_ATTACH cascade constraints;
prompt Dropping CAD_GD_HIS...
drop table CAD_GD_HIS cascade constraints;
prompt Dropping CAD_REF_DEPT_DEMAND...
drop table CAD_REF_DEPT_DEMAND cascade constraints;
prompt Dropping CAD_REF_DEPT_GD...
drop table CAD_REF_DEPT_GD cascade constraints;
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
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
create unique index INDEX_4 on AP_ACCESS_PERM (NAME)
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create unique index INDEX_8 on AP_ACCESS_PERM (CODE)
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table AP_ACCESS_PERM
  add constraint PK_AP_ACCESS_PERM primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
create unique index INDEX_2 on AP_GROUP (NAME)
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table AP_GROUP
  add constraint PK_AP_GROUP primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
create index AP_MENU_IDX on AP_MENU (NAME)
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table AP_MENU
  add constraint PK_AP_MENU primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
create unique index INDEX_5 on AP_OPER_PERM (NAME)
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create unique index INDEX_9 on AP_OPER_PERM (CODE)
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table AP_OPER_PERM
  add constraint PK_AP_OPER_PERM primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_ROLE_ACCESS_PERM...
create table AP_REF_ROLE_ACCESS_PERM
(
  role_id        NUMBER not null,
  access_perm_id NUMBER not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_ROLE_MENU...
create table AP_REF_ROLE_MENU
(
  role_id NUMBER not null,
  menu_id NUMBER not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_ROLE_OPER_PERM...
create table AP_REF_ROLE_OPER_PERM
(
  role_id      NUMBER not null,
  oper_perm_id NUMBER not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_USER_APP...
create table AP_REF_USER_APP
(
  app_id  NUMBER not null,
  user_id NUMBER not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_USER_GROUP...
create table AP_REF_USER_GROUP
(
  user_id  NUMBER not null,
  group_id NUMBER not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_REF_USER_ROLE...
create table AP_REF_USER_ROLE
(
  user_id NUMBER not null,
  role_id NUMBER not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
create unique index INDEX_10 on AP_ROLE (CODE, TYPE)
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create unique index INDEX_6 on AP_ROLE (NAME, TYPE)
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table AP_ROLE
  add constraint PK_AP_ROLE primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating AP_SEQ...
create table AP_SEQ
(
  seq_name  VARCHAR2(32) not null,
  seq_value NUMBER not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table AP_SEQ
  is '基于Table的Sequence';
alter table AP_SEQ
  add constraint PK_AP_SEQ primary key (SEQ_NAME)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
  update_time   TIMESTAMP(6),
  position_id   NUMBER default -1 not null,
  dept_id       NUMBER default -1 not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
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
comment on column AP_USER.position_id
  is '职位';
comment on column AP_USER.dept_id
  is '部门';
create index AP_USER_IDX2 on AP_USER (CH_NAME)
  tablespace CADS_IDX_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create unique index INDEX_1 on AP_USER (USERNAME)
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table AP_USER
  add constraint PK_AP_USER primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CAD_DEMAND...
create table CAD_DEMAND
(
  id                  NUMBER not null,
  prj_name            VARCHAR2(256) not null,
  recv_date           TIMESTAMP(6) not null,
  priority            VARCHAR2(12),
  require_finish_time TIMESTAMP(6),
  estimate_dev        VARCHAR2(12),
  estimate_test       VARCHAR2(12),
  pre_cond_ids        VARCHAR2(64),
  pre_cond            VARCHAR2(256),
  impl_team_ids       VARCHAR2(64) not null,
  impl_team           VARCHAR2(256) not null,
  start_date          TIMESTAMP(6),
  status              VARCHAR2(12),
  attach_type         VARCHAR2(32),
  creator             VARCHAR2(30),
  create_time         TIMESTAMP(6),
  updator             VARCHAR2(30),
  update_time         TIMESTAMP(6),
  remark              VARCHAR2(512)
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table CAD_DEMAND
  is '技术中心需求评估表';
comment on column CAD_DEMAND.id
  is '编号';
comment on column CAD_DEMAND.prj_name
  is '项目名称';
comment on column CAD_DEMAND.recv_date
  is '接收日期';
comment on column CAD_DEMAND.priority
  is '优先级';
comment on column CAD_DEMAND.require_finish_time
  is '要求完成时间';
comment on column CAD_DEMAND.estimate_dev
  is '预估开发量';
comment on column CAD_DEMAND.estimate_test
  is '预估测试量';
comment on column CAD_DEMAND.pre_cond_ids
  is '前置条件编号';
comment on column CAD_DEMAND.pre_cond
  is '前置条件';
comment on column CAD_DEMAND.impl_team_ids
  is '对应团队编号';
comment on column CAD_DEMAND.impl_team
  is '对应团队';
comment on column CAD_DEMAND.start_date
  is '开始时间';
comment on column CAD_DEMAND.status
  is '目前状态';
comment on column CAD_DEMAND.attach_type
  is '附件类型';
comment on column CAD_DEMAND.creator
  is '创建人';
comment on column CAD_DEMAND.create_time
  is '创建时间';
comment on column CAD_DEMAND.updator
  is '更新人';
comment on column CAD_DEMAND.update_time
  is '更新时间';
comment on column CAD_DEMAND.remark
  is '备注';
alter table CAD_DEMAND
  add constraint PK_CAD_DEMAND primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CAD_DEMAND_ATTACH...
create table CAD_DEMAND_ATTACH
(
  id        NUMBER not null,
  demand_id NUMBER not null,
  path      VARCHAR2(512) not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table CAD_DEMAND_ATTACH
  is '技术中心需求评估附件表';
comment on column CAD_DEMAND_ATTACH.id
  is '编号';
comment on column CAD_DEMAND_ATTACH.demand_id
  is '需求评估编号';
comment on column CAD_DEMAND_ATTACH.path
  is '附件路径';
alter table CAD_DEMAND_ATTACH
  add constraint PK_CAD_DEMAND_ATTACH primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CAD_DEMAND_HIS...
create table CAD_DEMAND_HIS
(
  id                  NUMBER not null,
  prj_name            VARCHAR2(256) not null,
  recv_date           TIMESTAMP(6) not null,
  priority            VARCHAR2(12),
  require_finish_time TIMESTAMP(6),
  estimate_dev        VARCHAR2(12),
  estimate_test       VARCHAR2(12),
  pre_cond_ids        VARCHAR2(64),
  pre_cond            VARCHAR2(256),
  impl_team_ids       VARCHAR2(64) not null,
  impl_team           VARCHAR2(256) not null,
  start_date          TIMESTAMP(6),
  status              VARCHAR2(12),
  attach_type         VARCHAR2(32),
  creator             VARCHAR2(30),
  create_time         TIMESTAMP(6),
  updator             VARCHAR2(30),
  update_time         TIMESTAMP(6),
  remark              VARCHAR2(512)
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table CAD_DEMAND_HIS
  is '技术中心需求评估归档表';
comment on column CAD_DEMAND_HIS.id
  is '编号';
comment on column CAD_DEMAND_HIS.prj_name
  is '项目名称';
comment on column CAD_DEMAND_HIS.recv_date
  is '接收日期';
comment on column CAD_DEMAND_HIS.priority
  is '优先级';
comment on column CAD_DEMAND_HIS.require_finish_time
  is '要求完成时间';
comment on column CAD_DEMAND_HIS.estimate_dev
  is '预估开发量';
comment on column CAD_DEMAND_HIS.estimate_test
  is '预估测试量';
comment on column CAD_DEMAND_HIS.pre_cond_ids
  is '前置条件编号';
comment on column CAD_DEMAND_HIS.pre_cond
  is '前置条件';
comment on column CAD_DEMAND_HIS.impl_team_ids
  is '对应团队编号';
comment on column CAD_DEMAND_HIS.impl_team
  is '对应团队';
comment on column CAD_DEMAND_HIS.start_date
  is '开始时间';
comment on column CAD_DEMAND_HIS.status
  is '目前状态';
comment on column CAD_DEMAND_HIS.attach_type
  is '附件类型';
comment on column CAD_DEMAND_HIS.creator
  is '创建人';
comment on column CAD_DEMAND_HIS.create_time
  is '创建时间';
comment on column CAD_DEMAND_HIS.updator
  is '更新人';
comment on column CAD_DEMAND_HIS.update_time
  is '更新时间';
comment on column CAD_DEMAND_HIS.remark
  is '备注 ';
alter table CAD_DEMAND_HIS
  add constraint PK_CAD_DEMAND_HIS primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CAD_DICT...
create table CAD_DICT
(
  id    NUMBER not null,
  name  VARCHAR2(64) not null,
  code  VARCHAR2(20),
  desc_ VARCHAR2(512)
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column CAD_DICT.id
  is '字典编号';
comment on column CAD_DICT.name
  is '名称';
comment on column CAD_DICT.code
  is '代码';
comment on column CAD_DICT.desc_
  is '描述';
alter table CAD_DICT
  add primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CAD_DICT_DETAIL...
create table CAD_DICT_DETAIL
(
  id      NUMBER not null,
  dict_id NUMBER not null,
  name    VARCHAR2(64) not null,
  code    VARCHAR2(20),
  desc_   VARCHAR2(512)
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column CAD_DICT_DETAIL.id
  is '字典明细编号';
comment on column CAD_DICT_DETAIL.dict_id
  is '字典编号';
comment on column CAD_DICT_DETAIL.name
  is '名称';
comment on column CAD_DICT_DETAIL.code
  is '代码';
comment on column CAD_DICT_DETAIL.desc_
  is '描述';
alter table CAD_DICT_DETAIL
  add primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CAD_GD...
create table CAD_GD
(
  id                  NUMBER not null,
  name                VARCHAR2(256) not null,
  recv_date           TIMESTAMP(6) not null,
  exec_type           VARCHAR2(12),
  type                VARCHAR2(12) not null,
  brief_name          VARCHAR2(128) not null,
  priority            VARCHAR2(12) not null,
  require_finish_time TIMESTAMP(6),
  estimate_job        VARCHAR2(12),
  pre_cond            VARCHAR2(256),
  impl_team           VARCHAR2(256) not null,
  pm_name             VARCHAR2(256),
  start_date          TIMESTAMP(6),
  ini                 VARCHAR2(6),
  rdp                 VARCHAR2(6),
  ad                  VARCHAR2(6),
  scp                 VARCHAR2(6),
  sit                 VARCHAR2(6),
  uat                 VARCHAR2(6),
  pip                 VARCHAR2(6),
  smp                 VARCHAR2(6),
  progress            VARCHAR2(12),
  finish_date         TIMESTAMP(6),
  using_resource      VARCHAR2(12),
  using_time          VARCHAR2(12),
  attach_type         VARCHAR2(32),
  creator             VARCHAR2(30),
  create_time         TIMESTAMP(6),
  updator             VARCHAR2(30),
  update_time         TIMESTAMP(6),
  pre_cond_ids        VARCHAR2(64),
  impl_team_ids       VARCHAR2(64),
  pm_name_ids         VARCHAR2(64),
  remark              VARCHAR2(512)
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table CAD_GD
  is '技术中心工单表';
comment on column CAD_GD.id
  is '编号';
comment on column CAD_GD.name
  is '工单名称/号';
comment on column CAD_GD.recv_date
  is '接收日期';
comment on column CAD_GD.exec_type
  is '主/协';
comment on column CAD_GD.type
  is '类型';
comment on column CAD_GD.brief_name
  is '简称';
comment on column CAD_GD.priority
  is '优先级';
comment on column CAD_GD.require_finish_time
  is '要求完成时间';
comment on column CAD_GD.estimate_job
  is '预估工作量';
comment on column CAD_GD.pre_cond
  is '前置条件';
comment on column CAD_GD.impl_team
  is '对应团队';
comment on column CAD_GD.pm_name
  is '项目经理';
comment on column CAD_GD.start_date
  is '开始时间';
comment on column CAD_GD.attach_type
  is 'word,pdf,zip';
comment on column CAD_GD.remark
  is ' 备注';
alter table CAD_GD
  add constraint PK_CAD_GD primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CAD_GD_ATTACH...
create table CAD_GD_ATTACH
(
  id    NUMBER not null,
  gd_id NUMBER not null,
  path  VARCHAR2(512) not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table CAD_GD_ATTACH
  is '技术中心工单附件表';
comment on column CAD_GD_ATTACH.id
  is '附件编号';
comment on column CAD_GD_ATTACH.gd_id
  is '工单编号';
comment on column CAD_GD_ATTACH.path
  is '附件路径';
alter table CAD_GD_ATTACH
  add constraint PK_CAD_GD_ATTACH primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CAD_GD_HIS...
create table CAD_GD_HIS
(
  id                  NUMBER not null,
  name                VARCHAR2(256) not null,
  recv_date           TIMESTAMP(6) not null,
  exec_type           VARCHAR2(12),
  type                VARCHAR2(12) not null,
  brief_name          VARCHAR2(128) not null,
  priority            VARCHAR2(12) not null,
  require_finish_time TIMESTAMP(6),
  estimate_job        VARCHAR2(12),
  pre_cond            VARCHAR2(256),
  impl_team           VARCHAR2(256) not null,
  pm_name             VARCHAR2(256),
  start_date          TIMESTAMP(6),
  ini                 VARCHAR2(6),
  rdp                 VARCHAR2(6),
  ad                  VARCHAR2(6),
  scp                 VARCHAR2(6),
  sit                 VARCHAR2(6),
  uat                 VARCHAR2(6),
  pip                 VARCHAR2(6),
  smp                 VARCHAR2(6),
  progress            VARCHAR2(12),
  finish_date         TIMESTAMP(6),
  using_resource      VARCHAR2(12),
  using_time          VARCHAR2(12),
  attach_type         VARCHAR2(32),
  creator             VARCHAR2(30),
  create_time         TIMESTAMP(6),
  updator             VARCHAR2(30),
  update_time         TIMESTAMP(6),
  pre_cond_ids        VARCHAR2(64),
  impl_team_ids       VARCHAR2(64),
  pm_name_ids         VARCHAR2(64),
  remark              VARCHAR2(512)
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table CAD_GD_HIS
  is '技术中心工单归档表';
comment on column CAD_GD_HIS.id
  is '编号';
comment on column CAD_GD_HIS.name
  is '工单名称/号';
comment on column CAD_GD_HIS.recv_date
  is '接收日期';
comment on column CAD_GD_HIS.exec_type
  is '主/协';
comment on column CAD_GD_HIS.type
  is '类型';
comment on column CAD_GD_HIS.brief_name
  is '简称';
comment on column CAD_GD_HIS.priority
  is '优先级';
comment on column CAD_GD_HIS.require_finish_time
  is '要求完成时间';
comment on column CAD_GD_HIS.estimate_job
  is '预估工作量';
comment on column CAD_GD_HIS.pre_cond
  is '前置条件';
comment on column CAD_GD_HIS.impl_team
  is '对应团队';
comment on column CAD_GD_HIS.pm_name
  is '项目经理';
comment on column CAD_GD_HIS.start_date
  is '开始时间';
comment on column CAD_GD_HIS.attach_type
  is 'word,pdf,zip';
comment on column CAD_GD_HIS.remark
  is '备注';
alter table CAD_GD_HIS
  add constraint PK_CAD_GD_HIS primary key (ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CAD_REF_DEPT_DEMAND...
create table CAD_REF_DEPT_DEMAND
(
  dept_id   NUMBER not null,
  demand_id NUMBER not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table CAD_REF_DEPT_DEMAND
  is '技术中心需求评估与部门关联表';
comment on column CAD_REF_DEPT_DEMAND.dept_id
  is '部门编号';
comment on column CAD_REF_DEPT_DEMAND.demand_id
  is '需求评估编号';
alter table CAD_REF_DEPT_DEMAND
  add constraint PK_CAD_REF_DEPT_DEMAND primary key (DEPT_ID, DEMAND_ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CAD_REF_DEPT_GD...
create table CAD_REF_DEPT_GD
(
  dept_id NUMBER not null,
  gd_id   NUMBER not null
)
tablespace CADS_TABLESPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table CAD_REF_DEPT_GD
  is '部门工单表';
comment on column CAD_REF_DEPT_GD.dept_id
  is '部门编号';
comment on column CAD_REF_DEPT_GD.gd_id
  is '工单编号';
alter table CAD_REF_DEPT_GD
  add constraint PK_CAD_REF_DEPT_GD primary key (DEPT_ID, GD_ID)
  using index 
  tablespace CADS_TABLESPACE
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for AP_ACCESS_PERM...
alter table AP_ACCESS_PERM disable all triggers;
prompt Disabling triggers for AP_APPLICATION...
alter table AP_APPLICATION disable all triggers;
prompt Disabling triggers for AP_GROUP...
alter table AP_GROUP disable all triggers;
prompt Disabling triggers for AP_MENU...
alter table AP_MENU disable all triggers;
prompt Disabling triggers for AP_OPER_PERM...
alter table AP_OPER_PERM disable all triggers;
prompt Disabling triggers for AP_REF_GROUP_ROLE...
alter table AP_REF_GROUP_ROLE disable all triggers;
prompt Disabling triggers for AP_REF_ROLE_ACCESS_PERM...
alter table AP_REF_ROLE_ACCESS_PERM disable all triggers;
prompt Disabling triggers for AP_REF_ROLE_MENU...
alter table AP_REF_ROLE_MENU disable all triggers;
prompt Disabling triggers for AP_REF_ROLE_OPER_PERM...
alter table AP_REF_ROLE_OPER_PERM disable all triggers;
prompt Disabling triggers for AP_REF_USER_APP...
alter table AP_REF_USER_APP disable all triggers;
prompt Disabling triggers for AP_REF_USER_GROUP...
alter table AP_REF_USER_GROUP disable all triggers;
prompt Disabling triggers for AP_REF_USER_ROLE...
alter table AP_REF_USER_ROLE disable all triggers;
prompt Disabling triggers for AP_ROLE...
alter table AP_ROLE disable all triggers;
prompt Disabling triggers for AP_SEQ...
alter table AP_SEQ disable all triggers;
prompt Disabling triggers for AP_USER...
alter table AP_USER disable all triggers;
prompt Disabling triggers for CAD_DEMAND...
alter table CAD_DEMAND disable all triggers;
prompt Disabling triggers for CAD_DEMAND_ATTACH...
alter table CAD_DEMAND_ATTACH disable all triggers;
prompt Disabling triggers for CAD_DEMAND_HIS...
alter table CAD_DEMAND_HIS disable all triggers;
prompt Disabling triggers for CAD_DICT...
alter table CAD_DICT disable all triggers;
prompt Disabling triggers for CAD_DICT_DETAIL...
alter table CAD_DICT_DETAIL disable all triggers;
prompt Disabling triggers for CAD_GD...
alter table CAD_GD disable all triggers;
prompt Disabling triggers for CAD_GD_ATTACH...
alter table CAD_GD_ATTACH disable all triggers;
prompt Disabling triggers for CAD_GD_HIS...
alter table CAD_GD_HIS disable all triggers;
prompt Disabling triggers for CAD_REF_DEPT_DEMAND...
alter table CAD_REF_DEPT_DEMAND disable all triggers;
prompt Disabling triggers for CAD_REF_DEPT_GD...
alter table CAD_REF_DEPT_GD disable all triggers;
prompt Loading AP_ACCESS_PERM...
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (35, 1000, 'SWF访问', 'SWFAccess', '/**/*.swf', 'anon', null, '1', 999999984, 'admin', to_timestamp('31-12-2014 09:54:53.736000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
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
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (43, 1000, 'CADS_应用维护', 'cads_appMaintenance', '/biz/cads/appSettings/maintenance/**/*.json', 'auth', 'roles', '1', 8, 'admin', to_timestamp('06-01-2015 16:30:48.589000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (42, 1000, 'CADS_技术中心需求评估单', 'cads_demand', '/biz/cads/myTasks/demand/**/*.*', 'auth', 'roles', '1', 7, 'admin', to_timestamp('05-01-2015 16:33:15.822000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (39, 1000, 'CADS_数据字典删除', 'cads_dictDelete', '/biz/cads/appSettings/dict*/**/delete.json', 'auth', 'roles', '1', 4, null, null, 'admin', to_timestamp('05-01-2015 16:33:38.871000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (40, 1000, 'CADS_数据字典查询', 'cads_dictQuery', '/biz/cads/appSettings/dict*/**/get*.json', 'auth', 'roles', '1', 5, null, null, 'admin', to_timestamp('05-01-2015 16:33:30.734000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (41, 1000, 'CADS_用户查询', 'cads_userQuery', '/maintenance/user/get*.json', 'auth', 'roles', '1', 6, 'admin', to_timestamp('05-01-2015 10:52:42.104000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (44, 1000, 'CADS_归档记录查询', 'cads_archives', '/biz/cads/myTasks/archive/**/*.json', 'auth', 'roles', '1', 9, 'admin', to_timestamp('07-01-2015 10:20:08.984000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (36, 1000, 'CADS_数据字典保存与更新', 'cads_dictSaveOrUpd', '/biz/cads/appSettings/dict*/**/saveOrUpdate.json', 'auth', 'roles', '1', 1, null, null, 'admin', to_timestamp('05-01-2015 10:48:09.935000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (37, 1000, 'CADS_技术中心工单', 'cads_gd', '/biz/cads/myTasks/gd/**/*.*', 'auth', 'roles', '1', 2, null, null, 'admin', to_timestamp('05-01-2015 10:45:48.770000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_ACCESS_PERM (id, app_id, name, code, url_pattern, authentication_filter, authorization_filter, status, sort, creator, create_time, updator, update_time)
values (38, 1000, 'CADS_我的设置', 'cads_mySettings', '/biz/cads/mySettings/**/*.json', 'auth', 'roles', '1', 3, 'admin', to_timestamp('05-01-2015 09:26:11.571000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
commit;
prompt 24 records loaded
prompt Loading AP_APPLICATION...
insert into AP_APPLICATION (id, name, code, type, desc_, status, creator, create_time, updator, update_time)
values (1000, '综合管理平台', 'CADS', null, '综合管理部系统平台', '1', null, null, 'admin', to_timestamp('04-01-2015 09:17:20.809000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 1 records loaded
prompt Loading AP_GROUP...
insert into AP_GROUP (id, app_id, name, code, type, desc_, status, creator, create_time, updator, update_time)
values (1, 1000, '管理组', 'AdminGroup', null, null, '1', null, null, 'admin', to_timestamp('30-12-2014 15:29:07.654000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 1 records loaded
prompt Loading AP_MENU...
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (1, 1000, -1, null, null, '/WEB-INF/views/', '系统管理', '0', 'icon-server', 1, '1', 4, null, null, 'admin', to_timestamp('30-12-2014 16:24:52.844000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (2, 1000, 1, null, null, '/WEB-INF/views/', '权限管理', '0', null, 2, '1', 1, null, null, 'admin', to_timestamp('28-03-2014 13:26:40.236000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (3, 1000, 115, null, null, '/WEB-INF/views/systemMGR/authCFG/funcMenu/module.xml', '功能菜单配置', '1', 'icon-script_gear', 4, '1', 1, null, null, 'admin', to_timestamp('09-05-2014 11:54:00.274000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (4, 1000, 115, null, null, '/WEB-INF/views/systemMGR/authCFG/accessAuth/module.xml', '访问权限配置', '1', 'icon-world', 4, '1', 2, null, null, 'admin', to_timestamp('05-06-2014 13:45:04.636000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (5, 1000, 115, null, null, '/WEB-INF/views/systemMGR/authCFG/operAuth/module.xml', '操作权限配置', '1', 'icon-lock', 4, '1', 3, null, null, 'admin', to_timestamp('05-06-2014 13:45:04.582000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (6, 1000, 2, null, null, '/WEB-INF/views/systemMGR/authCFG/role/module.xml', '角色配置', '1', 'icon-chart_organisation', 3, '1', 3, null, null, 'admin', to_timestamp('23-12-2014 09:32:46.436000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (7, 1000, 2, null, null, '/WEB-INF/views/systemMGR/authCFG/user/module.xml', '用户配置', '1', 'icon-user', 3, '1', 5, null, null, 'admin', to_timestamp('23-12-2014 11:02:27.687000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (8, 1000, 2, null, null, '/WEB-INF/views/systemMGR/authCFG/userGroup/module.xml', '用户组配置', '1', 'icon-group_gear', 3, '1', 4, null, null, 'admin', to_timestamp('23-12-2014 09:32:46.436000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (9, 1000, 2, null, null, '/WEB-INF/views/systemMGR/authCFG/app/module.xml', '应用系统配置', '1', 'icon-application', 3, '1', 1, null, null, 'admin', to_timestamp('23-12-2014 09:32:46.421000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (135, 1000, 134, null, null, '/WEB-INF/views/biz/cads/mySettings/resetPwd/module.xml', '密码重置', '1', 'icon-key', 2, '1', 1, 'admin', to_timestamp('29-12-2014 15:14:18.421000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('29-12-2014 16:21:17.340000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (137, 1000, 136, null, null, '/WEB-INF/views/biz/cads/myTasks/gd/module.xml', '技术中心工单', '1', 'icon-page', 2, '1', 1, 'admin', to_timestamp('30-12-2014 16:24:58.167000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('31-12-2014 10:01:41.826000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (140, 1000, 139, null, null, '/WEB-INF/views/biz/cads/myTasks/archive/gd/module.xml', '技术中心归档工单', '1', 'icon-page_go', 3, '1', 1, 'admin', to_timestamp('30-12-2014 16:31:30.570000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('07-01-2015 10:18:19.530000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (125, 1000, 124, null, null, '/WEB-INF/views/biz/cads/appSettings/dictMgr/module.xml', '数据字典维护', '1', 'icon-book', 2, '1', 1, 'admin', to_timestamp('23-12-2014 10:05:33.483000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('30-12-2014 11:15:31.689000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (134, 1000, -1, null, null, '/WEB-INF/views/', '我的设置', '0', 'icon-user_edit', 1, '1', 2, 'admin', to_timestamp('29-12-2014 15:12:08.369000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('30-12-2014 16:24:52.844000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (136, 1000, -1, null, null, '/WEB-INF/views/', '我的任务', '0', 'icon-page_green', 1, '1', 1, 'admin', to_timestamp('30-12-2014 16:21:30.350000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('30-12-2014 16:26:19.007000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (141, 1000, 139, null, null, '/WEB-INF/views/biz/cads/myTasks/archive/demand/module.xml', '技术中心归档需求评估', '1', 'icon-page_go', 3, '1', 2, 'admin', to_timestamp('30-12-2014 16:31:38.763000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('07-01-2015 10:37:23.827000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (139, 1000, 136, null, null, '/WEB-INF/views/', '归档记录查询', '0', null, 2, '1', 3, 'admin', to_timestamp('30-12-2014 16:30:40.914000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('30-12-2014 16:31:26.549000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (138, 1000, 136, null, null, '/WEB-INF/views/biz/cads/myTasks/demand/module.xml', '技术中心需求评估', '1', 'icon-page', 2, '1', 2, 'admin', to_timestamp('30-12-2014 16:25:00.909000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('05-01-2015 16:30:46.600000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (115, 1000, 2, null, null, '/WEB-INF/views/', '功能配置', '0', 'icon-brick', 3, '1', 2, 'admin', to_timestamp('14-03-2014 13:28:00.544000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('23-12-2014 09:32:46.436000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (124, 1000, -1, null, null, '/WEB-INF/views/', '应用配置', '0', 'icon-application_view_icons', 1, '1', 3, 'admin', to_timestamp('23-12-2014 10:04:24.711000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('30-12-2014 16:24:52.844000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (142, 1000, 124, null, null, '/WEB-INF/views/', '应用维护', '0', null, 2, '1', 2, 'admin', to_timestamp('06-01-2015 14:58:47.760000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('06-01-2015 15:01:06.203000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_MENU (id, app_id, parent_id, code, url, module_location, name, leaf, icon, hierarchy, status, sort, creator, create_time, updator, update_time)
values (143, 1000, 142, null, null, '/WEB-INF/views/biz/cads/appSettings/maintenance/module.xml', '表重置', '1', 'icon-table_refresh', 3, '1', 1, 'admin', to_timestamp('06-01-2015 15:01:17.461000', 'dd-mm-yyyy hh24:mi:ss.ff'), 'admin', to_timestamp('06-01-2015 16:28:20.938000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 22 records loaded
prompt Loading AP_OPER_PERM...
insert into AP_OPER_PERM (id, app_id, name, code, authorized_oper, status, sort, creator, create_time, updator, update_time)
values (1, 1000, '管理员操作', 'adminOper', 'oper:all', '1', 99999999, null, null, 'admin', to_timestamp('05-01-2015 09:27:50.445000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_OPER_PERM (id, app_id, name, code, authorized_oper, status, sort, creator, create_time, updator, update_time)
values (4, 1000, '基本操作', 'baseOper', 'oper:base', '1', 99999998, null, null, 'admin', to_timestamp('05-01-2015 09:27:37.968000', 'dd-mm-yyyy hh24:mi:ss.ff'));
commit;
prompt 2 records loaded
prompt Loading AP_REF_GROUP_ROLE...
insert into AP_REF_GROUP_ROLE (group_id, role_id)
values (1, 1);
commit;
prompt 1 records loaded
prompt Loading AP_REF_ROLE_ACCESS_PERM...
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 1);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 31);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 36);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 37);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 38);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 39);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 40);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 41);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 42);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 43);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (1, 44);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (8, 31);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (8, 37);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (8, 38);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (8, 40);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (8, 41);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (8, 42);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (9, 31);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (9, 36);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (9, 37);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (9, 38);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (9, 39);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (9, 40);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (9, 41);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (9, 42);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (9, 43);
insert into AP_REF_ROLE_ACCESS_PERM (role_id, access_perm_id)
values (9, 44);
commit;
prompt 27 records loaded
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
values (1, 115);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 124);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 125);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 134);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 135);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 136);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 137);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 138);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 139);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 140);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 141);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 142);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (1, 143);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (2, 1);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (2, 2);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (2, 9);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (8, 134);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (8, 135);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (8, 136);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (8, 137);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (8, 138);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 124);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 125);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 134);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 135);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 136);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 137);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 138);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 139);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 140);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 141);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 142);
insert into AP_REF_ROLE_MENU (role_id, menu_id)
values (9, 143);
commit;
prompt 42 records loaded
prompt Loading AP_REF_ROLE_OPER_PERM...
insert into AP_REF_ROLE_OPER_PERM (role_id, oper_perm_id)
values (1, 1);
insert into AP_REF_ROLE_OPER_PERM (role_id, oper_perm_id)
values (1, 4);
insert into AP_REF_ROLE_OPER_PERM (role_id, oper_perm_id)
values (8, 4);
insert into AP_REF_ROLE_OPER_PERM (role_id, oper_perm_id)
values (9, 4);
commit;
prompt 4 records loaded
prompt Loading AP_REF_USER_APP...
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 1);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 2);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 25);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 26);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 27);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 28);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 29);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 30);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 31);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 32);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 33);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 34);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 35);
insert into AP_REF_USER_APP (app_id, user_id)
values (1000, 36);
commit;
prompt 14 records loaded
prompt Loading AP_REF_USER_GROUP...
insert into AP_REF_USER_GROUP (user_id, group_id)
values (2, 1);
commit;
prompt 1 records loaded
prompt Loading AP_REF_USER_ROLE...
insert into AP_REF_USER_ROLE (user_id, role_id)
values (1, 1);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (25, 9);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (26, 8);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (27, 8);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (28, 8);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (29, 1);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (30, 8);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (31, 8);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (32, 8);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (33, 8);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (34, 8);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (35, 8);
insert into AP_REF_USER_ROLE (user_id, role_id)
values (36, 8);
commit;
prompt 13 records loaded
prompt Loading AP_ROLE...
insert into AP_ROLE (id, app_id, name, code, type, desc_, status, creator, create_time, updator, update_time)
values (2, 1000, '访客', 'Guest', '1', null, '0', null, null, 'admin', to_timestamp('05-01-2015 09:28:12.828000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into AP_ROLE (id, app_id, name, code, type, desc_, status, creator, create_time, updator, update_time)
values (1, 1000, '管理员', 'Admin', '1', null, '1', null, null, null, null);
insert into AP_ROLE (id, app_id, name, code, type, desc_, status, creator, create_time, updator, update_time)
values (8, 1000, 'CADS_基本访问角色', 'cads_base', '1', null, '1', 'admin', to_timestamp('05-01-2015 09:32:07.869000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
insert into AP_ROLE (id, app_id, name, code, type, desc_, status, creator, create_time, updator, update_time)
values (9, 1000, 'CADS_高阶访问角色', 'cads_adv', '1', null, '1', 'admin', to_timestamp('05-01-2015 09:32:29.231000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null);
commit;
prompt 4 records loaded
prompt Loading AP_SEQ...
insert into AP_SEQ (seq_name, seq_value)
values ('AP_APPLICATION_SEQ', 1004);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_USER_SEQ', 37);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_ROLE_SEQ', 10);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_GROUP_SEQ', 9);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_ACCESS_PERM_SEQ', 45);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_OPER_PERM_SEQ', 5);
insert into AP_SEQ (seq_name, seq_value)
values ('AP_MENU_SEQ', 144);
insert into AP_SEQ (seq_name, seq_value)
values ('CAD_GD_SEQ', 45);
insert into AP_SEQ (seq_name, seq_value)
values ('CAD_GD_ATTACH_SEQ', 39);
insert into AP_SEQ (seq_name, seq_value)
values ('CAD_DICT_SEQ', 22);
insert into AP_SEQ (seq_name, seq_value)
values ('CAD_DICT_DETAIL_SEQ', 71);
insert into AP_SEQ (seq_name, seq_value)
values ('CAD_DEMAND_SEQ', 11);
insert into AP_SEQ (seq_name, seq_value)
values ('CAD_DEMAND_ATTACH_SEQ', 9);
commit;
prompt 13 records loaded
prompt Loading AP_USER...
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (2, 'guest', '96e79218965eb72c92a549dd5a330112', null, '访客', null, null, null, 'M', null, null, '0', null, null, 'admin', to_timestamp('06-01-2015 17:49:35.004000', 'dd-mm-yyyy hh24:mi:ss.ff'), 8, 15);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (1, 'admin', '9c51a67da4b5bed01897fe9f7cfe66fc', null, '系统管理员', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:18:54.687000', 'dd-mm-yyyy hh24:mi:ss.ff'), 16, 11);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (25, 'xhjiang', '96e79218965eb72c92a549dd5a330112', null, '蒋晓湖', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:18:59.916000', 'dd-mm-yyyy hh24:mi:ss.ff'), 1, 11);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (26, 'jieyao', '96e79218965eb72c92a549dd5a330112', null, '姚婕', null, null, null, 'F', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:05.132000', 'dd-mm-yyyy hh24:mi:ss.ff'), 2, 11);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (27, 'mikeshi', '96e79218965eb72c92a549dd5a330112', null, '施平安', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:13.445000', 'dd-mm-yyyy hh24:mi:ss.ff'), 3, 11);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (28, 'aabu', '96e79218965eb72c92a549dd5a330112', null, '卜安安', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:18.800000', 'dd-mm-yyyy hh24:mi:ss.ff'), 3, 11);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (29, 'bcao', '9fcb1c3b142f2321a7a5272f75c2a7de', null, '曹斌', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:23.083000', 'dd-mm-yyyy hh24:mi:ss.ff'), 4, 11);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (30, 'timxu', '96e79218965eb72c92a549dd5a330112', null, '许建民', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:27.118000', 'dd-mm-yyyy hh24:mi:ss.ff'), 4, 11);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (31, 'andrewkang', '96e79218965eb72c92a549dd5a330112', null, '康学兵', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:31.840000', 'dd-mm-yyyy hh24:mi:ss.ff'), 5, 11);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (32, 'carriezhang', '96e79218965eb72c92a549dd5a330112', null, '张佳玲', null, null, null, 'F', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:36.744000', 'dd-mm-yyyy hh24:mi:ss.ff'), 6, 11);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (33, 'davidzhang', '96e79218965eb72c92a549dd5a330112', null, '张德文', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:41.293000', 'dd-mm-yyyy hh24:mi:ss.ff'), 7, 12);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (34, 'rsun', '96e79218965eb72c92a549dd5a330112', null, '孙睿', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:45.497000', 'dd-mm-yyyy hh24:mi:ss.ff'), 8, 13);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (35, 'cwwan', '96e79218965eb72c92a549dd5a330112', null, '万成文', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:50.314000', 'dd-mm-yyyy hh24:mi:ss.ff'), 8, 14);
insert into AP_USER (id, username, password, user_type, ch_name, ch_spell_name, first_name, last_name, gender, id_type, id_no, status, creator, create_time, updator, update_time, position_id, dept_id)
values (36, 'stevenzhang', '96e79218965eb72c92a549dd5a330112', null, '章炜', null, null, null, 'M', null, null, '1', null, null, 'admin', to_timestamp('07-01-2015 09:19:54.416000', 'dd-mm-yyyy hh24:mi:ss.ff'), 8, 15);
commit;
prompt 14 records loaded
prompt Loading CAD_DEMAND...
prompt Table is empty
prompt Loading CAD_DEMAND_ATTACH...
prompt Table is empty
prompt Loading CAD_DEMAND_HIS...
prompt Table is empty
prompt Loading CAD_DICT...
insert into CAD_DICT (id, name, code, desc_)
values (13, '主/协', 'GD_A01', '工单-主协');
insert into CAD_DICT (id, name, code, desc_)
values (16, '前置条件', 'GD_A04', '工单-前置条件（部门）');
insert into CAD_DICT (id, name, code, desc_)
values (14, '类型', 'GD_A02', '工单-类型');
insert into CAD_DICT (id, name, code, desc_)
values (15, '优先级', 'GD_A03', '工单-优先级');
insert into CAD_DICT (id, name, code, desc_)
values (20, '各阶段进阶', 'GD_A05', '工单-各阶段进阶');
insert into CAD_DICT (id, name, code, desc_)
values (1, '职位', 'POSITION', null);
insert into CAD_DICT (id, name, code, desc_)
values (2, '部门(团队)', 'DEPT', null);
insert into CAD_DICT (id, name, code, desc_)
values (21, '目前状态', 'DEMAND_A01', '需求评估单-目前状态');
commit;
prompt 8 records loaded
prompt Loading CAD_DICT_DETAIL...
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (31, 13, '主', 'GD_A0101', '主办');
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (32, 13, '协', 'GD_A0102', '协办');
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (33, 13, '支持', 'GD_A0103', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (16, 1, 'CAD系统管理员', 'CAD ADMIN', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (37, 14, '正常', 'GD_A0201', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (39, 14, '特殊', 'GD_A0203', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (40, 14, '维护', 'GD_A0204', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (38, 14, '敏捷', 'GD_A0202', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (41, 15, '高', 'GD_A0301', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (42, 15, '中', 'GD_A0302', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (43, 15, '低', 'GD_A0303', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (44, 15, 'A', 'GD_A0304', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (45, 15, 'A+', 'GD_A0305', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (46, 15, 'A-', 'GD_A0306', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (47, 15, 'B', 'GD_A0307', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (48, 15, 'B+', 'GD_A0308', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (49, 15, 'B-', 'GD_A0309', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (50, 15, 'C', 'GD_A0310', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (51, 15, 'C+', 'GD_A0311', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (52, 15, 'C-', 'GD_A0312', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (53, 15, 'D', 'GD_A0313', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (54, 15, 'D+', 'GD_A0314', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (55, 15, 'D-', 'GD_A0315', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (56, 16, '战略', 'GD_A0401', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (57, 16, '技术中心', 'GD_A0402', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (61, 16, '产品架构', 'GD_A0405', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (59, 16, '总经办', 'GD_A0403', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (60, 16, '产品管理中心', 'GD_A0404', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (64, 20, '●', 'GD_A0501', '完成');
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (65, 20, '○', 'GD_A0502', '进行中');
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (1, 1, '综合管理部经理', 'CADM', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (2, 1, '综合管理经理', 'CAM', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (3, 1, '综合项目经理', 'CAPM', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (4, 1, '系统设计师', 'SDE', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (5, 1, '数据库开发工程师', 'DE', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (6, 1, '技术中心助理', 'TDDA', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (7, 1, '高级项目经理', 'SPM', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (8, 1, '项目经理', 'PM', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (9, 1, '高级软件工程师', 'SSE', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (10, 1, '软件工程师', 'SE', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (11, 2, '综合管理部', 'CAD', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (12, 2, '账务组', 'ACCOUNT', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (13, 2, '通道组', 'CHANNEL', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (14, 2, '应用组', 'APP', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (15, 2, 'POS线下组', 'POS', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (66, 21, '未评估', 'DEMAND_A0101', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (67, 21, '评估中', 'DEMAND_A0102', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (68, 21, '已上报', 'DEMAND_A0103', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (69, 21, '已转工单', 'DEMAND_A0104', null);
insert into CAD_DICT_DETAIL (id, dict_id, name, code, desc_)
values (70, 2, '移动应用组', 'MOBILE', null);
commit;
prompt 50 records loaded
prompt Loading CAD_GD...
prompt Table is empty
prompt Loading CAD_GD_ATTACH...
prompt Table is empty
prompt Loading CAD_GD_HIS...
prompt Table is empty
prompt Loading CAD_REF_DEPT_DEMAND...
prompt Table is empty
prompt Loading CAD_REF_DEPT_GD...
prompt Table is empty
prompt Enabling triggers for AP_ACCESS_PERM...
alter table AP_ACCESS_PERM enable all triggers;
prompt Enabling triggers for AP_APPLICATION...
alter table AP_APPLICATION enable all triggers;
prompt Enabling triggers for AP_GROUP...
alter table AP_GROUP enable all triggers;
prompt Enabling triggers for AP_MENU...
alter table AP_MENU enable all triggers;
prompt Enabling triggers for AP_OPER_PERM...
alter table AP_OPER_PERM enable all triggers;
prompt Enabling triggers for AP_REF_GROUP_ROLE...
alter table AP_REF_GROUP_ROLE enable all triggers;
prompt Enabling triggers for AP_REF_ROLE_ACCESS_PERM...
alter table AP_REF_ROLE_ACCESS_PERM enable all triggers;
prompt Enabling triggers for AP_REF_ROLE_MENU...
alter table AP_REF_ROLE_MENU enable all triggers;
prompt Enabling triggers for AP_REF_ROLE_OPER_PERM...
alter table AP_REF_ROLE_OPER_PERM enable all triggers;
prompt Enabling triggers for AP_REF_USER_APP...
alter table AP_REF_USER_APP enable all triggers;
prompt Enabling triggers for AP_REF_USER_GROUP...
alter table AP_REF_USER_GROUP enable all triggers;
prompt Enabling triggers for AP_REF_USER_ROLE...
alter table AP_REF_USER_ROLE enable all triggers;
prompt Enabling triggers for AP_ROLE...
alter table AP_ROLE enable all triggers;
prompt Enabling triggers for AP_SEQ...
alter table AP_SEQ enable all triggers;
prompt Enabling triggers for AP_USER...
alter table AP_USER enable all triggers;
prompt Enabling triggers for CAD_DEMAND...
alter table CAD_DEMAND enable all triggers;
prompt Enabling triggers for CAD_DEMAND_ATTACH...
alter table CAD_DEMAND_ATTACH enable all triggers;
prompt Enabling triggers for CAD_DEMAND_HIS...
alter table CAD_DEMAND_HIS enable all triggers;
prompt Enabling triggers for CAD_DICT...
alter table CAD_DICT enable all triggers;
prompt Enabling triggers for CAD_DICT_DETAIL...
alter table CAD_DICT_DETAIL enable all triggers;
prompt Enabling triggers for CAD_GD...
alter table CAD_GD enable all triggers;
prompt Enabling triggers for CAD_GD_ATTACH...
alter table CAD_GD_ATTACH enable all triggers;
prompt Enabling triggers for CAD_GD_HIS...
alter table CAD_GD_HIS enable all triggers;
prompt Enabling triggers for CAD_REF_DEPT_DEMAND...
alter table CAD_REF_DEPT_DEMAND enable all triggers;
prompt Enabling triggers for CAD_REF_DEPT_GD...
alter table CAD_REF_DEPT_GD enable all triggers;
set feedback on
set define on
prompt Done.
