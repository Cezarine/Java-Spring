CREATE TABLE users(
    id bigint not null auto_increment,
    username varchar(255) not null unique,
    password varchar(255) not null,

    primary key(id)
);
