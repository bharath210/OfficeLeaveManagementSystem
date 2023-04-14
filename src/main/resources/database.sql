create database olms_db;
use olms_db;

create table Employees (
	employee_id int,
        name varchar(255),
        email varchar(255),
        phone_number char(10),
        department varchar(255),
        primary key (employee_id)
);

create table Leave_Requests(
	leave_request_id int,
        employee_id int,
        start_date date,
        end_date date,
        leave_type varchar(255),
        reason varchar(255),
        status varchar(255),
        primary key(leave_request_id),
        foreign key(employee_id) references employees(employee_id)
);

create table Leave_Balances (
	leave_balance_id int,
        employee_id int,
        leave_type varchar(255),
        balance int,
        primary key(leave_balance_id),
        foreign key(employee_id) references employees(employee_id)
);
