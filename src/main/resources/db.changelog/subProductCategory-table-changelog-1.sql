--liquibase formatted sql



--changeSet sara:1
create table SubProductCategory(
  id int  primary key,
  Name varchar(100),
  productCategory_id Int


);

--changeSet sara:2
create sequence subProductCategory_seq;


--changeSet sara:3
 ALTER TABLE SubProductCategory
  ADD CONSTRAINT fk_subProductCategory_productCategory FOREIGN KEY (productCategory_id) REFERENCES ProductCategory(id);



