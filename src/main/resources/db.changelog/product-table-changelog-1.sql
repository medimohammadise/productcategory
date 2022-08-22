--liquibase formatted sql



--changeSet sara:1
create table Product(
  id int  primary key,
  Name varchar(100),
  subProductCategory_id Int


);

--changeSet sara:2
create sequence product_seq;


--changeSet sara:3
--ALTER TABLE Product
--  ADD CONSTRAINT fk_product_subProductCategory FOREIGN KEY (subProductCategory_id) REFERENCES SubProductCategory(id);



