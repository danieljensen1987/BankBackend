delete from Person;
---
insert into Person(cpr, dtype, title, firstName, lastName, postalCode, street, phone, email, password)
values
('121212-2990', 'Person', 'Sir', 'Lance', 'Alot', 2990, 'Nivåhøj 74, 3, 1', '12345678', 'la@cphbank.dk', 'la'),
('010101-2000', 'Person', 'Mr', 'Thomas', 'Svenningsen', 2000, 'Somewhere', '98765432', 'ts@cphbank.dk', 'ts'),
('020202-2635', 'Person', 'Mr', 'Chris', 'Petersen', 2635, 'Ishøjvej 29', '13579113', 'cp@cphbank.dk', 'cp'),
('030303-5644', 'Employee', 'Mr', 'Mehemet', 'Yilmaz', 2770, 'Kastrupvej 32', '98765432', 'my@cphbank.dk', 'my');

insert into Employee(cpr, salary, dateOfEmployment)
values
('030303-5644', 10.00, '2014-03-17');

insert into Account (acc_number, customer_cpr, manager_cpr, dtype, interest, balance)
values
('1111-4222', '020202-2635', '030303-5644', 'CheckingAccount', 0.12, 0.00);

insert into CheckingAccount (acc_number)
values
('1111-4222');

SELECT * FROM Employee;

select * from Account where customer_cpr = '020202-2635';