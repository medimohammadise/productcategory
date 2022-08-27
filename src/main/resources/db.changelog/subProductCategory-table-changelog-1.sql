--liquibase formatted sql



--changeSet sara:1
create table SubProductCategory(
  id int NOT NULL  primary key,
  Name varchar(100) UNIQUE NOT NULL,
  productCategory_id Int ,
  productCategoryName  varchar(255),
  created_by varchar(255),
  created_date Date,
  last_modified_by varchar(255),
  last_modified_date Date


);

--changeSet sara:2
create sequence subProductCategory_seq;


--changeSet sara:3
 ALTER TABLE SubProductCategory
  ADD CONSTRAINT fk_subProductCategory_productCategory FOREIGN KEY (productCategory_id) REFERENCES ProductCategory(id);



