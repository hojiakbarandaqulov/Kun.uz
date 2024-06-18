CREATE TABLE book
(
    id          serial not null,
    description varchar(255) DEFAULT NULL,
    title       varchar(255) DEFAULT NULL,
    PRIMARY KEY (id)
);

