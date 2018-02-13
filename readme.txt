run DavisBase.java

Supported sql commands and their syntax are as follows :

show tables;
gives the list of all tables

select * from davisbase_columns;

metadata file having the column details for each file

Create
create table vyoma (id int primary key,name text [not null],age int);

Insert
insert into table (id,name,age) vyoma values (1,vyoma,22);

Select
select * from vyoma;
select name from vyoma;
select * from vyoma where row_id > 1;
select * from vyoma where row_id = 1;
select name from vyoma where age = 22;

Update
update vyoma set age = 24 where name = jugal;

Delete
delete from table vyoma where row_id = 3;

Drop
drop table vyoma;




Optional features:
ndx files implemented