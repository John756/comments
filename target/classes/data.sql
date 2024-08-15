DROP TABLE IF EXISTS COMMENTS;


CREATE TABLE COMMENTS (
                         ID INT AUTO_INCREMENT PRIMARY KEY,
                         USERID INT,
                         TITLE VARCHAR(255),
                         BODY TEXT
);

INSERT INTO COMMENTS (USERID, TITLE, BODY) VALUES ('1', 'First Post', 'This is the body of the first postttttttt.');
INSERT INTO COMMENTS (USERID, TITLE, BODY) VALUES (2, 'Second Post', 'This is the body of the second post.');
INSERT INTO COMMENTS (USERID, TITLE, BODY) VALUES (3, 'Third Post', 'This is the body of the third post.');
INSERT INTO COMMENTS (USERID, TITLE, BODY) VALUES (1, 'Another Post', 'This is another post by user 1.');
INSERT INTO COMMENTS (USERID, TITLE, BODY) VALUES (2, 'Yet Another Post', 'This is another post by user 2.');



SHOW COLUMNS FROM COMMENTS;
SELECT * FROM COMMENTS WHERE id=1 ;


