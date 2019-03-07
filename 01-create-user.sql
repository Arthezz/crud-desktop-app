CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT ALL PRIVILEGES ON * . * TO 'admin'@'localhost';

ALTER USER 'admin'@'localhost' IDENTIFIED WITH mysql_native_password BY 'admin';