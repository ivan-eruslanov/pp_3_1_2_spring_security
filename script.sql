create table users (
    id bigint primary key auto_increment,
    username varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    age smallint,
    password varchar(255) not null
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

insert into roles values (1, 'ROLE_USER'),
                         (2, 'ROLE_ADMIN');

insert into users (id, username, first_name, last_name, age, password)
        values (1, 'user@mail.ru', 'Иван', 'Иванов', 23, '$2a$12$78Luzom5PgWltBn6GFnVGOhrd0KkCNjFZ8j8IPMpVRZDfHbmq8FzG'),
                         (2, 'admin@mail.ru', 'Алексей', 'Петров', 33, '$2a$12$vlrqg2VcBZz3lu5M8yMd/eyD5LbJAjlZGgOdUyx3CeY68CCYSUcX.');

insert into users_roles values (1, 1),
                               (2, 1),
                               (2, 2);