DROP TABLE IF EXISTS BASICINFO;
CREATE TABLE BASICINFO (
info_id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(50) NOT NULL,
contact VARCHAR(20) NOT NULL,
address_id INT NOT NULL
);

DROP TABLE IF EXISTS ADDRESS;
CREATE TABLE ADDRESS (
address_id INT AUTO_INCREMENT PRIMARY KEY,
address_line_1 VARCHAR(50) NOT NULL,
address_line_2 VARCHAR(50),
city VARCHAR(20) NOT NULL,
state VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL,
pin_code VARCHAR(10) NOT NULL
);