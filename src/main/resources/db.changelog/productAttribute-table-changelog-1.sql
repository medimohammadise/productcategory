--liquibase formatted sql



--changeSet sara:1
create table ProductAttribute(
  id int  primary key,
  name varchar(100),
  value varchar(100),
  product_id Int


);

--changeSet sara:2
create sequence productAttribute_seq;


--changeSet sara:3
 ALTER TABLE ProductAttribute
  ADD CONSTRAINT fk_productAttribute_product FOREIGN KEY (product_id) REFERENCES Product(id);



