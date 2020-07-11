INSERT INTO operators (operator_name)
VALUES ("Telenor");

INSERT INTO admins (admin_name, admin_pass)
VALUES ("admin1", "123456A"),
("admin2", "123456B"),
("admin3", "123456C");

INSERT INTO clients (first_name, last_name, phone, client_pass)
VALUES ("Dimitar", "Petkov", "0898433559", "123456H"),
("Georgi", "Ivanov", "0895684879", "987654M"),
("Ivan", "Todorov", "0896488246", "456789K"),
("Martin", "Petrov", "0895489829", "pass123");

INSERT INTO contracts (operator_id, minutes, megabytes, sms, bill)
VALUES (1, 2000, 5000, 20, 24.99),
(1, 3000, 8000, 25, 28.99),
(1, 500, 1000, 15, 18.99),
(1, 10000, 12000, 40, 35.99),
(1, 1000, 1500, 15, 22.99);

INSERT INTO clients_contracts (contract_id, client_id, signing_date, expiry_date, payment_date, minutes, megabytes, sms, bill, ad) 
	SELECT 2, 2, '2019-02-01', '2021-02-01', 10, minutes, megabytes, sms, bill, id FROM contracts WHERE id = 2;

INSERT INTO clients_contracts (contract_id, client_id, signing_date, expiry_date, payment_date, minutes, megabytes, sms, bill, ad) 
	SELECT 1, 3, "2020-05-08", "2022-05-08", 15, minutes, megabytes, sms, bill, id FROM contracts WHERE id = 1;
    
INSERT INTO clients_contracts (contract_id, client_id, signing_date, expiry_date, payment_date, minutes, megabytes, sms, bill, ad) 
	SELECT 3, 1, "2018-10-15", "2020-10-15", 8, minutes, megabytes, sms, bill, id FROM contracts WHERE id = 3;
    
INSERT INTO clients_contracts (contract_id, client_id, signing_date, expiry_date, payment_date, minutes, megabytes, sms, bill, ad) 
	SELECT 5, 4, "2020-06-24", "2022-06-24", 23, minutes, megabytes, sms, bill, id FROM contracts WHERE id = 5;