
CREATE TABLE board_member(
	member_id NUMBER PRIMARY KEY
	, m_id VARCHAR(20)
	, m_pass VARCHAR(20)
	, m_name VARCHAR(20)
	, regdate DATE DEFAULT SYSDATE
);

CREATE SEQUENCE seq_board_member
INCREMENT BY 1
START WITH 1;