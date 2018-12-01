/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/12/1 16:14:13                           */
/*==============================================================*/


drop table if exists device;

drop table if exists good;

drop table if exists good_batch;

drop table if exists good_type;

/*==============================================================*/
/* Table: device                                                */
/*==============================================================*/
create table device
(
   device_id            varchar(32) not null,
   secret_key           varchar(32),
   status               char(1),
   details              varchar(128),
   primary key (device_id)
);

/*==============================================================*/
/* Table: good                                                  */
/*==============================================================*/
create table good
(
   good_id              int not null auto_increment,
   batch_id             int not null,
   device_id            varchar(32),
   status               char(1),
   primary key (good_id)
);

/*==============================================================*/
/* Table: good_batch                                            */
/*==============================================================*/
create table good_batch
(
   batch_id             int not null auto_increment,
   type_id              int not null,
   manufacture_date     bigint,
   create_time          bigint,
   details              varchar(128),
   primary key (batch_id)
);

/*==============================================================*/
/* Table: good_type                                             */
/*==============================================================*/
create table good_type
(
   type_id              int not null auto_increment,
   title                varchar(32),
   expiration_date      smallint,
   details              varchar(128),
   price                float,
   primary key (type_id)
);

alter table good add constraint FK_r_good_batch foreign key (batch_id)
      references good_batch (batch_id) on delete restrict on update restrict;

alter table good add constraint FK_r_good_device foreign key (device_id)
      references device (device_id) on delete restrict on update restrict;

alter table good_batch add constraint FK_r_batch_type foreign key (type_id)
      references good_type (type_id) on delete restrict on update restrict;

