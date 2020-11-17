CREATE TABLE test_emp_a (
    emp_id NUMBER,
    emp_name VARCHAR2(100)
);


CREATE TABLE test_emp_b (
    emp_id NUMBER,
    emp_name VARCHAR2(100)
);

-- 한 개의 row 씩 INSERT
INSERT INTO test_emp_a VALUES(101, '아이언맨');
INSERT INTO test_emp_b VALUES(201, '캡틴아메리카');

SELECT * FROM test_emp_a;
SELECT * FROM test_emp_b;

-- 동시에 여러 테이블에 INSERT 하기
INSERT ALL
    INTO test_emp_a VALUES(102, '블랙위도우')
    INTO test_emp_b VALUES(202, '비전')
SELECT * FROM dual;

-- SubQuery 로 INSERT
INSERT INTO test_emp_a (SELECT 400, '호크아이' FROM dual);

-- 순식간에 레코드 두배로 만들기
INSERT INTO test_emp_a (SELECT * FROM test_emp_a);

INSERT INTO test_emp_b(emp_name) (SELECT emp_name FROM test_emp_b);
SELECT * FROM test_emp_b;

-- INSERT 할 때 type 같아야 한다. 다르면 ERROR.
INSERT INTO test_emp_b(emp_id) (SELECT emp_name FROM test_emp_b);


CREATE TABLE test_emp_c AS (SELECT emp_name, emp_id FROM test_emp_a);

SELECT * FROM test_emp_c;


CREATE TABLE test_report1
AS
SELECT a.name "사원이름", NVL(a.post, ' ') "직급", a.pay "급여"
FROM t_emp2 a
WHERE a.pay >= ( 
    SELECT AVG(b.pay) 
    FROM t_emp2 b WHERE NVL(a.post, ' ') = NVL(b.post, ' ') )
;

SELECT * FROM test_report1;


