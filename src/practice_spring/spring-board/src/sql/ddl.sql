create user 'soongjamm'@'localhost' identified by "S00ngj@mm";

create database spring5board character set=utf8;

grant all privileges on spring5board.* to 'soongjamm'@'localhost';

create table spring5board.MEMBER (
    ID int auto_increment primary key,
    EMAIL varchar(255),
    PASSWORD varchar(100),
    NAME varchar(100),
    REGDATE datetime,
    unique key (EMAIL)
)engine=INNODB character set=utf8;

create table spring5board.POSTS (
    ID int auto_increment primary key,
    TITLE varchar(255),
    CONTENT varchar(255),
    AUTHOR_EMAIL varchar(255),
    CREATED_AT datetime,
    FOREIGN KEY (AUTHOR_EMAIL) REFERENCES MEMBER(EMAIL)
)engine=INNODB character set=utf8;