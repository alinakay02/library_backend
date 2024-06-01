create sequence seq_author start with 1 increment by 1;

create sequence seq_book start with 1 increment by 1;

create sequence seq_genre start with 1 increment by 1;

create sequence seq_lib_event start with 1 increment by 1;

create sequence seq_lib_item start with 1 increment by 1;

create sequence seq_lib_order_book start with 1 increment by 1;

create sequence seq_lib_role start with 1 increment by 1;

create sequence seq_lib_user start with 1 increment by 1;

create table author (
    id         bigint primary key,
    name       varchar(32) not null,
    patronymic varchar(32),
    surname    varchar(32) not null
);

create table book (
    id    bigint primary key,
    title varchar(64)
);

create table book_authors (
    author_id bigint not null,
    book_id   bigint not null
);

create table book_genres (
    book_id  bigint not null,
    genre_id bigint not null
);

create table event (
    id    bigint primary key,
    date  timestamp(6) with time zone,
    title varchar(128) not null,
    event varchar(2048) not null,
    photo varchar(255)
);

create table genre (
    id         bigint primary key,
    genre_name varchar(32) not null unique
);

create table item (
    id      bigint primary key,
    date    timestamp(6) with time zone,
    title   varchar(128) not null,
    news    varchar(2048) not null,
    photo   varchar(255)
);

create table lib_user (
    id       bigint primary key,
    role_id  bigint not null,
    login    varchar(32) not null unique,
    name     varchar(32) not null,
    surname  varchar(32) not null,
    password varchar(256) not null
);

create table order_book (
    id      bigint primary key,
    state   boolean,
    book_id bigint not null,
    user_id bigint
);

create table role (
    id        bigint primary key,
    role_name varchar(32) not null unique
);

create table saved_books (
    book_id bigint not null,
    user_id bigint not null
);

alter table if exists book_authors
    add constraint book_authors_fk_author
    foreign key (author_id)
    references author;

alter table if exists book_authors
    add constraint book_authors_fk_book
    foreign key (book_id)
    references book;

alter table if exists book_genres
    add constraint book_genres_fk_genre
    foreign key (genre_id)
    references genre;

alter table if exists book_genres
    add constraint book_genres_fk_book
    foreign key (book_id)
    references book;

alter table if exists lib_user
    add constraint lib_user_fk_role
    foreign key (role_id)
    references role;

alter table if exists order_book
    add constraint order_book_fk_book
    foreign key (book_id)
    references book;

alter table if exists order_book
    add constraint order_book_fk_lib_user
    foreign key (user_id)
    references lib_user;

alter table if exists saved_books
    add constraint saved_books_fk_book
    foreign key (book_id)
    references book;

alter table if exists saved_books
    add constraint saved_books_fk_lib_user
    foreign key (user_id)
    references lib_user;