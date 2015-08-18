create table event(
	id bigint primary key auto_increment,
	aggregatetype varchar(255),
	class varchar(255),
	kryoeventdata BLOB,
	dataversion integer default 0
);

create table saga(
	id varchar(255),
	clazz varchar(255),
	state tinyint,
	PRIMARY KEY(id, clazz)
);