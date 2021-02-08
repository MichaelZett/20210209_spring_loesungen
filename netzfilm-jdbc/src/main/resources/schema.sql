create table movie
(
    id           integer      not null,
    uuid         varchar(36)  not null,
    title        varchar(255) not null,
    release_date date         not null,
    primary key (id)
);

create table customer
(
    id        integer      not null,
    uuid      varchar(36)  not null,
    name      varchar(255) not null,
    last_name varchar(255) not null,
    birthdate date         not null,
    primary key (id)
);