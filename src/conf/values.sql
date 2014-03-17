delete from Person;
---
insert into Person(cpr, dtype, title, firstName, lastName, postalCode, street, phone, email, password)
values
('121212-2990', 'Person', 'Sir', 'Lance', 'Alot', 2990, 'Nivåhøj 74, 3, 1', '12345678', 'la@cphbank.dk', 'la'),
('010101-2000', 'Person', 'Mr', 'Thomas', 'Svenningsen', 2000, 'Somewhere', '98765432', 'ts@cphbank.dk', 'ts'),
('020202-2635', 'Person', 'Mr', 'Chris', 'Petersen', 2635, 'Ishøjvej 29', '13579113', 'cp@cphbank.dk', 'cp');


SELECT * FROM Person;