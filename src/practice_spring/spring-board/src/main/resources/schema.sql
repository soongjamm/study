CREATE TABLE BOARD
(
    seq      INT PRIMARY KEY auto_increment,
    title    VARCHAR   NOT NULL,
    content  VARCHAR   NOT NULL,
    writer   VARCHAR   NOT NULL,
    password INT       NOT NULL,
    regDate  TIMESTAMP NOT NULL,
    cnt      INT       NOT NULL
);

-- INSERT INTO BOARD (title, content, writer, password, regDate, cnt)
-- values ('t5', 'ddd', 'me', '2020-12-12 12:32:45', 5);
-- INSERT INTO BOARD (title, content, writer, password, regDate, cnt)
-- values ('t1', 'bddrr', 'meno', '2020-12-15 02:32:45', 15);

CREATE TABLE city
(
    id      INT PRIMARY KEY auto_increment,
    name    VARCHAR,
    state   VARCHAR,
    country VARCHAR
);