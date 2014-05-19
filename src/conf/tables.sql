drop table Transfer;
drop table TimeDepositAccount;
drop table MonneyMarketAccount;
drop table CheckingAccount;
drop table Account;
--drop table Role;
drop table Employee;
drop table Person;
drop table Postal;
--drop sequence--
drop sequence account_number_seq restrict;
drop sequence transfer_id_seq restrict;
-----------------------------------
create sequence account_number_seq as int start with 11110150 increment by 1;
create sequence transfer_id_seq as int start with 1100 increment by 1;

create table Postal(
code int primary key,
district varchar(40) not null
);

create table Person(
cpr varchar(11) primary key,
dtype varchar(20) not null,
title varchar(12),
firstName varchar(40) not null,
lastName varchar(40) not null,
postalCode int references Postal(code),
street varchar(60) not null,
phone varchar(11) not null,
email varchar(40),
password varchar(64)
);

create table Employee(
cpr varchar(11) primary key references Person(cpr),
salary decimal (8,2) not null,
dateOfEmployment date not null
);

--create table Role(
--cpr varchar(11) primary key references Person(cpr),
--name varchar (30));

create table Account(
acc_number varchar(40) primary key,
--acc_number = '4711-' || char(next value for account_number_seq) primary key,--
customer_cpr varchar(11) references Person(cpr),
manager_cpr varchar(11) references Employee(cpr),
dtype varchar(30) not null,
interest decimal(4,2) not null,
balance decimal(19,2) not null
);

create table CheckingAccount(
acc_number varchar(40) primary key references Account(acc_number)
);

create table MonneyMarketAccount(
acc_number varchar(40) primary key references Account(acc_number),
minimumBalance decimal(19,2) not null
);

create table TimeDepositAccount(
acc_number varchar(40) primary key references Account(acc_number),
releaseDate Date not null
);

create table Transfer(
transfer_id varchar(20) primary key,
Source_acc_number varchar(40) references Account(acc_number),
target_acc_number varchar(40) references Account(acc_number),
transfer_date date not null,
amount decimal(19,2) not null
);


