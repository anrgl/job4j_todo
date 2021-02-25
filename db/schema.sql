create table users (
    id serial primary key,
    name varchar(255) not null,
    email varchar(255) unique,
    password varchar(255)
);

create table items (
    id serial primary key,
    description text,
    created timestamp,
    done boolean,
    user_id int references users(id)
);