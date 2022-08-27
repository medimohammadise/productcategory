--liquibase formatted sql



--changeSet sara:1
create table ProductAttribute(
  id int NOT NULL  primary key,
  name varchar(100) NOT NULL,
  value varchar(100) NOT NULL,
  product_id Int ,
   productName  varchar(255),
   created_by varchar(255),
  created_date Date,
  last_modified_by varchar(255),
  last_modified_date Date



);

--changeSet sara:2
create sequence productAttribute_seq;


--changeSet sara:3
 ALTER TABLE ProductAttribute
  ADD CONSTRAINT fk_productAttribute_product FOREIGN KEY (product_id) REFERENCES Product(id);



