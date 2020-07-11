DROP DATABASE IF EXISTS mobile_operators_db;
CREATE DATABASE IF NOT EXISTS mobile_operators_db;
USE mobile_operators_db;

CREATE TABLE operators (
id INT AUTO_INCREMENT PRIMARY KEY,
operator_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE admins (
id INT AUTO_INCREMENT PRIMARY KEY,
admin_name VARCHAR(50),
admin_pass VARCHAR(50)
);

CREATE TABLE clients (
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
phone VARCHAR(10) UNIQUE NOT NULL,
client_pass VARCHAR(50) NOT NULL
);

CREATE TABLE contracts (
id INT AUTO_INCREMENT PRIMARY KEY,
operator_id INT NOT NULL,
FOREIGN KEY (operator_id) REFERENCES operators(id) ON DELETE RESTRICT ON UPDATE CASCADE,
minutes DOUBLE NOT NULL,
megabytes DOUBLE NOT NULL,
sms INT NOT NULL,
bill DOUBLE NOT NULL
);

CREATE TABLE clients_contracts (
id INT AUTO_INCREMENT PRIMARY KEY,
contract_id INT NOT NULL,
FOREIGN KEY (contract_id) REFERENCES contracts(id) ON DELETE RESTRICT ON UPDATE CASCADE,
client_id INT UNIQUE NOT NULL,
FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE RESTRICT ON UPDATE CASCADE,
signing_date DATE NOT NULL,
expiry_date DATE NOT NULL,
payment_date INT NOT NULL,
minutes DOUBLE NOT NULL,
megabytes DOUBLE NOT NULL,
sms INT NOT NULL,
bill DOUBLE NOT NULL,
ad INT NOT NULL
);