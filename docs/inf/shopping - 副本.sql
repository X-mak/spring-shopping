CREATE TABLE `user_role`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	role_name VARCHAR(20),
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `user_account`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	account VARCHAR(20) BINARY,
	pwd VARCHAR(130) BINARY,
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `account_role`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	role_id INT(10),
	account_id INT(10),
	PRIMARY KEY(id),
	FOREIGN KEY(role_id) REFERENCES user_role(id),
	FOREIGN KEY(account_id) REFERENCES user_account(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `user_info`(
	id INT(10),
	user_name VARCHAR(20),
	user_status INT(1),
	user_phone VARCHAR(11),
	PRIMARY KEY(id),
	FOREIGN KEY(id) REFERENCES user_account(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

create table `user_ex_property`(
	id int(10) AUTO_INCREMENT NOT NULL,
	property varchar(50),
	primary key(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `user_ex`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	user_id INT(10),
	property_id INT(10),
	value varchar(40),
	`status` int(1),
	PRIMARY KEY(id),
	foreign key(user_id) references user_info(id),
	foreign key(property_id) references user_ex_property(id)
 )ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `goods`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	goods_name VARCHAR(30),
	price DOUBLE,
	stock INT(10),
	goods_status INT(1),
	introduction VARCHAR(120),
	sales INT(10),
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

create table `user_goods_property`(
	id int(10) auto_increment not null,
	property varchar(40),
	primary key(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

create table `user_goods`(
	id int(10) auto_increment not null,
	user_id int(10),
	goods_id int(10),
	property_id int(10),
	property_value varchar(40),
	date datetime,
	primary key(id),
	foreign key(user_id) references user_info(id),
	foreign key(goods_id) references goods(id),
	foreign key(property_id) references  goods_ex_property(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

create table `goods_ex_property`(
	id int(10) auto_increment not null,
	property varchar(20),
	primary key(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

create table `goods_ex`(
	id int(10) auto_increment not null,
	goods_id int(10),
	property_id int(10),
	value varchar(10),
	status int(1),
	date datetime,
	primary key(id),
	foreign key(goods_id) references goods(id),
	foreign key(property_id) references goods_ex_property(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `orders`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	user_id INT(10),
	`date` DATETIME,
	`status` INT(1),
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES user_info(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `order_item`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	order_id INT(10),
	goods_id INT(10),
	num INT(10),
	price DOUBLE,
	PRIMARY KEY(id),
	FOREIGN KEY(order_id) REFERENCES orders(id),
	FOREIGN KEY(goods_id) REFERENCES goods(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `classes`(
	id VARCHAR(4),
	class_name VARCHAR(10),
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `goods_class`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	goods_id INT(10),
	class_id VARCHAR(4),
	PRIMARY KEY(id),
	FOREIGN KEY(goods_id)REFERENCES goods(id),
	FOREIGN KEY(class_id)REFERENCES classes(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `post_sale`(
	id INT(10)NOT NULL AUTO_INCREMENT,
	order_item_id INT(10),
	topic VARCHAR(40),
	introduction VARCHAR(200),
	picture VARCHAR(120),
	STATUS INT(1),
	PRIMARY KEY(id),
	FOREIGN KEY(order_item_id)REFERENCES order_item(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO user_role(role_name) VALUES ('buyer');
INSERT INTO user_role(role_name) VALUES ('seller');
INSERT INTO user_role(role_name) VALUES ('admin');
INSERT INTO classes VALUES ('01','食物');
INSERT INTO classes VALUES ('0101','水果');
insert into goods_ex_property(property) values ('stock');
insert into goods_ex_property(property) values ('price');
insert into goods_ex_property(property) values ('picture');
insert into user_ex_property(property) values('address');
insert into user_goods_property(property) values('collection');
insert into user_goods_property(property) values('cart');
insert into user_goods_property(property) values('publish');

CREATE VIEW goods_vw(id,goods_name,price,stock,`status`,introduction,sales,class_name,picture) AS
SELECT g.id,g.goods_name,g.price,g.stock,g.goods_status,g.introduction,g.sales,
	c.class_name,ge.value
	FROM goods g LEFT JOIN goods_class gc ON gc.goods_id=g.id
	LEFT JOIN classes c ON c.id=gc.class_id LEFT JOIN goods_ex ge ON
	ge.goods_id = g.id WHERE ge.property_id=3 AND ge.status=1



CREATE VIEW goods_picture_vw(id,goods_id,address,`status`) AS
	SELECT ge.id,ge.goods_id,ge.value,ge.status FROM goods_ex ge 
	LEFT JOIN goods_ex_property gep ON ge.property_id=gep.id
	WHERE gep.property="picture";

CREATE VIEW goods_stock_vw(id,goods_id,stock,`date`) AS
SELECT ge.id,ge.goods_id,ge.value,ge.date FROM
	goods_ex ge LEFT JOIN goods_ex_property gep ON ge.property_id=gep.id
	WHERE gep.property='stock';

CREATE VIEW goods_price_vw(id,goods_id,price,`date`) AS
SELECT ge.id,ge.goods_id,ge.value,ge.date FROM
	goods_ex ge LEFT JOIN goods_ex_property gep ON ge.property_id=gep.id
	WHERE gep.property='price';

CREATE VIEW user_address_vw(id,user_id,address,`status`) AS 
SELECT ue.id,ue.user_id,ue.value,ue.status FROM user_ex ue LEFT JOIN
user_ex_property uep ON uep.id = ue.property_id WHERE
uep.property='address';

CREATE VIEW cart_item_vw(id,user_id,goods_id,num) AS
SELECT ug.id,ug.user_id,ug.goods_id,ug.property_value FROM user_goods ug LEFT JOIN
	user_goods_property ugp ON ugp.id=ug.property_id WHERE ugp.property='cart';

CREATE VIEW collection_item_vw(id,user_id,goods_id) AS
SELECT ug.id,ug.user_id,ug.goods_id FROM user_goods ug LEFT JOIN
	user_goods_property ugp ON ugp.id=ug.property_id WHERE ugp.property='collection';


CREATE VIEW publish_vw(id,user_id,goods_id) AS
SELECT ug.id,ug.user_id,ug.goods_id FROM user_goods ug LEFT JOIN
	user_goods_property ugp ON ugp.id=ug.property_id WHERE ugp.property='publish';
