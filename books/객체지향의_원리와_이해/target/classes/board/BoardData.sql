CREATE TABLE BOARD (
                       seq INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       title VARCHAR(255) NOT NULL ,
                       content VARCHAR(1255) NOT NULL ,
                       writer VARCHAR(11) NOT NULL ,
                       password INT NOT NULL,
                       regDate TIMESTAMP NOT NULL,
                       cnt INT NOT NULL
);

INSERT INTO BOARD (title, content, writer, password, regDate, cnt)
VALUES('t1', 'c1', 'w1', 1234, '2014-09-09 14:21:12', 0);

INSERT INTO BOARD (title, content, writer, password, regDate, cnt)
VALUES('t2', 'c2', 'w2', 1234, '2014-09-09 14:21:12', 0);