--liquibase formatted sql



--changeSet sara:1
create table ProductCategory(
  id int  primary key,
  Name varchar(100)



);

--changeSet sara:2
create sequence productCategory_seq;





