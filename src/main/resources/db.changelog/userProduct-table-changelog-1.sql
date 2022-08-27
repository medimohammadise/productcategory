-- liquibase formatted sql

--changeSet sara:1
create table UserProduct(
  id int NOT NULL primary key,
  profileName varchar(100) UNIQUE NOT NULL,
  weekDay  varchar(100) NOT NULL ,
  start_time  TIMESTAMP(255) NOT NULL,
  end_time  TIMESTAMP(255) NOT NULL,
  created_by varchar(255),
  created_date TIMESTAMP,
  last_modified_by varchar(255),
  last_modified_date TIMESTAMP


);

--changeSet sara:2
create sequence userProduct_seq ;
