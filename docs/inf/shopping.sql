CREATE TABLE `userrole`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	role_name VARCHAR(20),
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `useraccount`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	account VARCHAR(20) BINARY,
	pwd VARCHAR(130) BINARY,
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `accountrole`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	role_id INT(10),
	account_id INT(10),
	PRIMARY KEY(id),
	FOREIGN KEY(role_id) REFERENCES userrole(id),
	FOREIGN KEY(account_id) REFERENCES useraccount(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `userinfo`(
	id INT(10),
	user_name VARCHAR(20),
	user_status INT(1),
	user_phone VARCHAR(11),
	PRIMARY KEY(id),
	FOREIGN KEY(id) REFERENCES useraccount(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `address`(
	id INT(10)AUTO_INCREMENT NOT NULL,
	user_id INT(10),
	content VARCHAR(100),
	address_status INT(1),
	PRIMARY KEY(id),
	FOREIGN KEY(user_id)REFERENCES userinfo(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `goods`(
	id INT(10)AUTO_INCREMENT NOT NULL,
	goods_name VARCHAR(30),
	price DOUBLE,
	stock INT(10),
	goods_status INT(1),
	introduction VARCHAR(120),
	sales INT(10),
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `shop`(
	id INT(10)AUTO_INCREMENT NOT NULL,
	shop_name VARCHAR(30),
	hot INT(10),
	date DATETIME,
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `usershop`(
	id INT(10)AUTO_INCREMENT NOT NULL,
	user_id INT(10),
	shop_id INT(10),
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES userinfo(id),
	FOREIGN KEY(shop_id) REFERENCES shop(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `shopgoods`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	shop_id INT(10),
	goods_id INT(10),
	date datetime,
	PRIMARY KEY(id),
	FOREIGN KEY(shop_id) REFERENCES shop(id),
	FOREIGN KEY(goods_id) REFERENCES goods(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


CREATE TABLE `pictures`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	goods_id INT(10),
	address VARCHAR(120),
	status INT(1),
	PRIMARY KEY(id),
	FOREIGN KEY(goods_id) REFERENCES goods(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `stock`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	goods_id INT(10),
	num INT(10),
	date DATETIME,
	PRIMARY KEY(id),
	FOREIGN KEY(goods_id) REFERENCES goods(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `price`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	goods_id INT(10),
	price DOUBLE,
	date DATETIME,
	PRIMARY KEY(id),
	FOREIGN KEY(goods_id) REFERENCES goods(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `orders`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	user_id INT(10),
	`date` DATETIME,
	`status` INT(1),
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES userinfo(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `orderitem`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	order_id INT(10),
	goods_id INT(10),
	num INT(10),
	price DOUBLE,
	PRIMARY KEY(id),
	FOREIGN KEY(order_id) REFERENCES orders(id),
	FOREIGN KEY(goods_id) REFERENCES goods(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `comments`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	user_id INT(10),
	goods_id INT(10),
	content VARCHAR(200),
	stars INT(1),
	date DATETIME,
	hot INT(10),
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES userinfo(id),
	FOREIGN KEY(goods_id) REFERENCES goods(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `classes`(
	id VARCHAR(4),
	class_name VARCHAR(10),
	PRIMARY KEY(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `goodsclass`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	goods_id INT(10),
	class_id VARCHAR(4),
	PRIMARY KEY(id),
	FOREIGN KEY(goods_id)REFERENCES goods(id),
	FOREIGN KEY(class_id)REFERENCES classes(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `collecteditem`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	user_id INT(10),
	goods_id INT(10),
	date DATETIME,
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES userinfo(id),
	FOREIGN KEY(goods_id) REFERENCES goods(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `cartitem`(
	id INT(10) AUTO_INCREMENT NOT NULL,
	user_id INT(10),
	goods_id INT(10),
	num INT(10),
	PRIMARY KEY(id),
	FOREIGN KEY(user_id) REFERENCES userinfo(id),
	FOREIGN KEY(goods_id) REFERENCES goods(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `postsale`(
	id INT(10)NOT NULL AUTO_INCREMENT,
	order_item_id INT(10),
	topic VARCHAR(40),
	introduction VARCHAR(200),
	picture VARCHAR(120),
	STATUS INT(1),
	PRIMARY KEY(id),
	FOREIGN KEY(order_item_id)REFERENCES orderitem(id)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO userrole(role_name) VALUES ('buyer');
INSERT INTO userrole(role_name) VALUES ('seller');
INSERT INTO userrole(role_name) VALUES ('admin');
INSERT INTO classes VALUES ('01','食物');
INSERT INTO classes VALUES ('0101','水果');

