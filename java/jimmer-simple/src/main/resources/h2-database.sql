drop table tree_node if exists;
drop table book_author_mapping if exists;
drop table book if exists;
drop table author if exists;
drop table book_store if exists;

create table book_store(
    id identity(100, 1) not null,
    name varchar(50) not null,
    website varchar(100)
);
alter table book_store
    add constraint business_key_book_store
        unique(name)
;

create table book(
    id identity(100, 1) not null,
    name varchar(50) not null,
    edition integer not null,
    price numeric(10, 2) not null,
    store_id bigint
);
alter table book
    add constraint business_key_book
        unique(name, edition)
;
alter table book
    add constraint fk_book__book_store
        foreign key(store_id)
            references book_store(id)
;

create table author(
    id identity(100, 1) not null,
    first_name varchar(25) not null,
    last_name varchar(25) not null,
    gender char(1) not null
);
alter table author
    add constraint business_key_author
        unique(first_name, last_name)
;
alter table author
    add constraint ck_author_gender
        check gender in ('M', 'F');

create table book_author_mapping(
    book_id bigint not null,
    author_id bigint not null
);
alter table book_author_mapping
    add constraint pk_book_author_mapping
        primary key(book_id, author_id)
;
alter table book_author_mapping
    add constraint fk_book_author_mapping__book
        foreign key(book_id)
            references book(id)
                on delete cascade
;
alter table book_author_mapping
    add constraint fk_book_author_mapping__author
        foreign key(author_id)
            references author(id)
                on delete cascade
;

insert into book_store(id, name) values
    (1, 'O''REILLY'),
    (2, 'MANNING')
;

insert into book(id, name, edition, price, store_id) values
    (1, 'Learning GraphQL', 1, 50, 1),
    (2, 'Learning GraphQL', 2, 55, 1),
    (3, 'Learning GraphQL', 3, 51, 1),

    (4, 'Effective TypeScript', 1, 73, 1),
    (5, 'Effective TypeScript', 2, 69, 1),
    (6, 'Effective TypeScript', 3, 88, 1),

    (7, 'Programming TypeScript', 1, 47.5, 1),
    (8, 'Programming TypeScript', 2, 45, 1),
    (9, 'Programming TypeScript', 3, 48, 1),

    (10, 'GraphQL in Action', 1, 80, 2),
    (11, 'GraphQL in Action', 2, 81, 2),
    (12, 'GraphQL in Action', 3, 80, 2)
;

insert into author(id, first_name, last_name, gender) values
    (1, 'Eve', 'Procello', 'F'),
    (2, 'Alex', 'Banks', 'M'),
    (3, 'Dan', 'Vanderkam', 'M'),
    (4, 'Boris', 'Cherny', 'M'),
    (5, 'Samer', 'Buna', 'M')
;

insert into book_author_mapping(book_id, author_id) values
    (1, 1),
    (2, 1),
    (3, 1),

    (1, 2),
    (2, 2),
    (3, 2),

    (4, 3),
    (5, 3),
    (6, 3),

    (7, 4),
    (8, 4),
    (9, 4),

    (10, 5),
    (11, 5),
    (12, 5)
;

create table tree_node(
    id identity(100, 1) not null,
    name varchar(20) not null,
    parent_id bigint
);
alter table tree_node
    add constraint business_key_tree_node
        unique(parent_id, name);
alter table tree_node
    add constraint fk_tree_node__parent
        foreign key(parent_id)
            references tree_node(id);

insert into tree_node(
    id, name, parent_id
) values
    (1, 'Home', null),
        (2, 'Food', 1),
            (3, 'Drinks', 2),
                (4, 'Coca Cola', 3),
                (5, 'Fanta', 3),
            (6, 'Bread', 2),
                (7, 'Baguette', 6),
                (8, 'Ciabatta', 6),
        (9, 'Clothing', 1),
            (10, 'Woman', 9),
                (11, 'Casual wear', 10),
                    (12, 'Dress', 11),
                    (13, 'Miniskirt', 11),
                    (14, 'Jeans', 11),
                (15, 'Formal wear', 10),
                    (16, 'Suit', 15),
                    (17, 'Shirt', 15),
            (18, 'Man', 9),
                (19, 'Casual wear', 18),
                    (20, 'Jacket', 19),
                    (21, 'Jeans', 19),
                (22, 'Formal wear', 18),
                    (23, 'Suit', 22),
                    (24, 'Shirt', 22)
;