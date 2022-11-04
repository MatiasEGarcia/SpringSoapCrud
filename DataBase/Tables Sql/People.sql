use soap_test;

CREATE TABLE IF NOT EXISTS people(
	id_person int not null primary key auto_increment,
    name varchar(45) not null,
    lastname varchar(45) not null,
    email varchar(45),
    date_of_birth date 
);