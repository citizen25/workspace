-- PL/SQL : Procedurl Language / SQL
-- 오라클에서 제공하는 프로그래밍 언어
-- 기본 SQL만으로는 데이터 조작이 불편(부족)한 부분을
-- PL/SQL 과 함께 활용하면 효과적으로 다룰 수 있다.

-- ** PL/SQL은 dbeaver에서 사용 못함 **

-- PL/SQL은 기본적으로, 처리된 PL/SQL 결과를 화면에 출력안함.
-- 아래와 같은 사전 명령 필요
SET SERVEROUTPUT ON;    -- 화면 출력 기능 활성화

BEGIN
    DBMS_OUTPUT.PUT_LINE('Hello World');
END;

BEGIN
    DBMS_OUTPUT.PUT_LINE('hello' || ' ' || TO_CHAR(2000 + 19));
END;

DECLARE
    v_age INTEGER;      -- INTEGER 타입 변수 v_age 선언(declare)
    v_name VARCHAR2(10);    -- VARCHAR2(10) 타입 변수 v_name 선언
BEGIN
    v_age := 48;        -- 변수 v_age에 48 값을 담는다 (대입한다).
    v_name := 'John';   -- 변수 v_name에 'John' 문자열 값을 대입한다.

    DBMS_OUTPUT.PUT_LINE(v_name || ' ' || TO_CHAR(v_age));
END;

-- PL/SQL block 구성
--   1) DECLARE (선언부) : 변수나 상수 선언.
--   2) EXECUTABLE (실행부) : 제어, 반복, 출력, 함수정의 등... 로직 (ex: BEGIN ~ END).
--   3) EXCEPTION (예외처리) : 실행도중 발생된 에러 처리.


DECLARE
    v_age INTEGER;
    v_name VARCHAR2(10);
BEGIN
    -- SQL의 SELECT 결과를 변수에 대입 가능
    SELECT 10 INTO v_age FROM dual;
    SELECT 'Hong' INTO v_name FROM dual;
    DBMS_OUTPUT.PUT_LINE(v_name || '****' || TO_CHAR(v_age + 100));
END;


DECLARE
    vno NUMBER(4);
    vname VARCHAR2(10);
BEGIN
    SELECT empno, ename INTO vno, vname     -- SELECT 결과를 두 변수에 대입
    FROM t_emp
    WHERE empno = 7900;

    DBMS_OUTPUT.PUT_LINE(vno || '---' || vname);    -- 화면 출력
END;

/*
    PL/SQL 블럭 작성시 기본 규칙
    문장은 여러줄에 걸쳐 작성 가능하나 키워드는 분리 불가
    식별자(별명)는 " ~ " 로 작성
    문자, 날짜 리터럴은 ' ~ ' 로 작성

    일번적인 오라클 함수는 블럭 내에서 사용 가능..  그룹함수 안됨.

*/

/*
    t_professor 테이블에서 교수번호가 1001인 교수의 교수번호와 급여를 변수에 저장한 뒤, 화면에 출력하세요.
*/
DECLARE
    v_profno t_professor.profno%TYPE; -- t_professor 테이블의 profno 와 같은 타입으로 선언
    v_pay t_professor.pay%TYPE;
BEGIN
    SELECT profno, pay INTO v_profno, v_pay
    FROM t_professor
    WHERE profno=1001;

    DBMS_OUTPUT.PUT_LINE(v_profno || '번 교수의 급여는 ' || v_pay || '입니다');
END;


-- 입력이 가능하다!!
-- t_emp2 테이블, 사원번호를 입력받아서, 사번/이름/생일 출력하기

DECLARE
    v_empno t_emp2.empno%TYPE;
    v_name t_emp2.name%TYPE;
    v_birth t_emp2.birthday%TYPE;
BEGIN
    SELECT empno, name, birthday
    INTO v_empno, v_name, v_birth
    FROM t_emp2
    WHERE empno = '&empno'; -- 사용자에게 입력받아서 변수에 할당 기호(&) 사용

    DBMS_OUTPUT.PUT_LINE(v_empno || ' ' || v_name || ' ' || v_birth);
END;

-----------------------------------------------------------------------
-- PL/SQL 에서 DML 사용
-- INSERT / UPDATE / DELETE
CREATE TABLE p1_test(
    no NUMBER,
    name VARCHAR2(10)
);
CREATE SEQUENCE p1_seq;

BEGIN
    INSERT INTO p1_test
    VALUES(p1_seq.nextval, 'AAA');
END;

SELECT * FROM p1_test;


CREATE TABLE p1_test2(
    no NUMBER,
    name VARCHAR2(10),
    addr VARCHAR2(10)
);

DECLARE
    v_no NUMBER := '&no';
    v_name VARCHAR2(10) := '&name';
    v_addr VARCHAR2(10) := '&addr';
BEGIN
    INSERT INTO p1_test2 VALUES(v_no, v_name, v_addr);
END;

SET VERIFY OFF      -- & 을 이용한 변수의 자세한 값 변화 표시를 OFF 한다 (기본값 ON)

SELECT * FROM p1_test2;

-- UPDATE
BEGIN
    UPDATE p1_test SET name = 'BBB' WHERE no = 2;
END;

SELECT * FROM p1_test;

-- DELETE
BEGIN
    DELETE FROM p1_test WHERE no = 1;
END;
SELECT * FROM p1_test;



-- 중첩된 PL/SQL 블럭 사용
-- 프로시져 작성할 경우 블럭 안에 또 다른 블럭을 중첩 포함 가능

DECLARE
    v_first VARCHAR(5) := 'Outer';  -- 오라클은 다른 언어들과 달리 변수명이 앞에 오고 타입이 뒤로 간다.
BEGIN
    DECLARE
        v_second VARCHAR(5) := 'Inner';
    BEGIN
        DBMS_OUTPUT.PUT_LINE(v_first);  -- 안쪽 블럭에선 바깥쪽 블럭의 변수 사용 가능
        DBMS_OUTPUT.PUT_LINE(v_second);
    END;
    DBMS_OUTPUT.PUT_LINE(v_first);
    -- DBMS_OUTPUT.PUT_LINE(v_second);  -- 과연?  블럭이 끝나면 그 블럭 안의 변수는 더이상 사용 불가
END;

--------------------------------------------------------------------------------
-- PL/SQL 변수

-- 변수 작명 규칙
--   반드시 문자로 시작
--   문자, 숫자, 특수문자 포함 가능
--   변수명 30byte 이하
--   예약어 사용 물가. ex) SELECT, ...

-------------------------------------------------------------------------------
-- %TYPE 변수
DROP TABLE t_emp3 CASCADE CONSTRAINT;

CREATE TABLE t_emp3
AS
SELECT empno, ename, sal FROM t_emp;

DECLARE 
    vno t_emp3.empno%TYPE;
    vname t_emp3.ename%TYPE;
    vsal t_emp3.sal%TYPE;
BEGIN
    SELECT empno, ename, sal INTO vno, vname, vsal
    FROM t_emp3
    WHERE empno = 7900;
    DBMS_OUTPUT.PUT_LINE(vno || ' ' || vname || ' ' || vsal);
END;


-- ROWTYPE 활용!
-- 한개의 레코드 타입을 통째로!

DECLARE
    v_row t_emp3%ROWTYPE;
BEGIN
    SELECT * INTO v_row
    FROM t_emp3 WHERE empno=7900;

    DBMS_OUTPUT.PUT_LINE(v_row.empno || '**' || v_row.ename || '**' || v_row.sal);
END;

