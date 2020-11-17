
CREATE TABLE board_member(
	member_id NUMBER PRIMARY KEY
	, m_id VARCHAR(20)
	, m_pass VARCHAR(20)
	, m_name VARCHAR(20)
	, regdate DATE DEFAULT SYSDATE
);

CREATE TABLE board(
	board_id NUMBER PRIMARY KEY
	, title VARCHAR(100)
	, writer VARCHAR(20)
	, content CLOB
	, regdate DATE DEFAULT SYSDATE
	, hit NUMBER DEFAULT 0
);

CREATE SEQUENCE seq_board
INCREMENT BY 1
START WITH 1;


CREATE SEQUENCE seq_board_member
INCREMENT BY 1
START WITH 1;

SELECT * FROM board_member;