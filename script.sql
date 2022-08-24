create table users (
    id bigint primary key auto_increment,
    username varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    age smallint,
    password varchar(255) not null,
    email varchar(255) not null unique
);

create table roles (
    id bigint primary key auto_increment,
    role varchar(50) not null
);

create table users_roles (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (role) values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, first_name, last_name, age, password, email)
    values
        ('user', 'name', 'last', 23, '$2a$10$iJiWiFjKGu2pZx2FoIQyaOK.cRx3zyxAobFa.OfuMPSylMdzNYCo.', 'user@gmail.com');

insert into users_roles (user_id, role_id) values (1, 2);