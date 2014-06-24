create SEQUENCE seq;

create table Event(
	id number primary key,
	aggregatetype varchar(255),
	class varchar(255),
	kryoeventdata BLOB,
	dataversion integer default 0
);

create table Saga(
	id varchar(255),
	clazz varchar(255),
	state tinyint,
	PRIMARY KEY(id, clazz)
);
