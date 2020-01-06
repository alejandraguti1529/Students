create database db_students;
use db_students;
create table Students(
id integer not null,
name varchar(40) not null, 
birthday date not null, 
address varchar(40) not null, 
curp varchar(20) not null, 
gender char not null, 
photo LONGBLOB, 
primary key(id)
);