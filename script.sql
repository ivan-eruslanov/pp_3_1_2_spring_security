create table users (
    id bigint primary key auto_increment,
    name varchar(30) not null,
    password varchar(80),
    email varchar(50) unique
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

insert into users (name, password, email) values ('user', '$2a$10$iJiWiFjKGu2pZx2FoIQyaOK.cRx3zyxAobFa.OfuMPSylMdzNYCo.', 'user@gmail.com');

insert into users_roles (user_id, role_id) values (1, 2);