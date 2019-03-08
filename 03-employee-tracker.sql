CREATE DATABASE  IF NOT EXISTS `employee_tracker`;
USE `employee_tracker`;

DROP TABLE IF EXISTS `employee`;

create table `employee` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(50),
	`last_name` VARCHAR(50),
	`email` VARCHAR(50),
	`city` VARCHAR(50),
	`street` VARCHAR(50),
	`salary` INT,
  PRIMARY KEY (id)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

LOCK TABLES `employee` WRITE;

insert into `employee` (id, first_name, last_name, email, city, street, salary) values (1, 'Erskine', 'Sheen', 'esheen0@gizmodo.com', 'Dongsheng', '1674 Shoshone Drive', 3022);
insert into `employee` (id, first_name, last_name, email, city, street, salary) values (2, 'Jared', 'Stormouth', 'jstormouth1@businessinsider.com', 'La Soledad', '76588 5th Pass', 3083);
insert into `employee` (id, first_name, last_name, email, city, street, salary) values (3, 'Trescha', 'Cowthart', 'tcowthart2@soup.io', 'Tamatam', '499 Vernon Street', 7219);
insert into `employee` (id, first_name, last_name, email, city, street, salary) values (4, 'Allina', 'Mixhel', 'amixhel3@twitpic.com', 'Soufrière', '4089 Hagan Avenue', 2984);
insert into `employee` (id, first_name, last_name, email, city, street, salary) values (5, 'Mikaela', 'Gillingham', 'mgillingham4@shutterfly.com', 'Sesheke', '64 Summer Ridge Terrace', 3481);
insert into `employee` (id, first_name, last_name, email, city, street, salary) values (6, 'Dyna', 'Dobble', 'ddobble5@goo.ne.jp', 'Sukamahi Satu', '20656 Schmedeman Parkway', 6350);
insert into `employee` (id, first_name, last_name, email, city, street, salary) values (7, 'Aura', 'Pickvance', 'apickvance6@yahoo.co.jp', 'San Pablo', '78750 High Crossing Road', 2215);
insert into `employee` (id, first_name, last_name, email, city, street, salary) values (8, 'Loleta', 'Lillistone', 'llillistone7@ovh.net', 'Safonovo', '879 Marcy Park', 7016);
