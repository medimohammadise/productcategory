--liquibase formatted sql



--changeSet sara:1
create table Product(
  id int NOT NULL  primary key,
  Name varchar(100) UNIQUE NOT NULL,
  subProductCategory_id Int ,
  subProductCategoryName varchar(255),
  created_by varchar(255),
  created_date Date,
  last_modified_by varchar(255),
  last_modified_date Date


);

--changeSet sara:2
create sequence product_seq;


--changeSet sara:3
--ALTER TABLE Product
--  ADD CONSTRAINT fk_product_subProductCategory FOREIGN KEY (subProductCategory_id) REFERENCES SubProductCategory(id);



