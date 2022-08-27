--liquibase formatted sql

--changeSet sara:1
CREATE TYPE visibility AS ENUM ('PUBLIC', 'PRIVATE');

--changeSet sara:2
CREATE CAST (CHARACTER VARYING as visibility) WITH INOUT AS IMPLICIT;

--changeSet sara:3
create table ProductCategory(
  id int NOT NULL UNIQUE primary key,
  name varchar(100) UNIQUE NOT NULL,
  visibility visibility NOT NULL,
  created_by varchar(255),
  created_date Date,
  last_modified_by varchar(255),
  last_modified_date Date




);

--changeSet sara:4
create sequence productCategory_seq;





