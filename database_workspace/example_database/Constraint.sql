-- 제약조건(Constraint)

SELECT * FROM t_dept2;

-- #9001
-- 제약조건명을 명시하지 않는 방법
DROP TABLE t_emp4 CASCADE CONSTRAINT; -- t_emp4 테이블 제거, 관련 제약조건 같이 삭제
CREATE TABLE t_emp4 (
    no NUMBER(4) PRIMARY KEY,
    name VARCHAR2(10) NOT NULL,  -- DEFAULT는 기본적으로 NULL을 허용한다(=NULL)
    jumin VARCHAR2(13) NOT NULL UNIQUE,  -- 제약조건 여러개 설정 가능
    area NUMBER(1) CHECK(area < 5),
    deptno VARCHAR2(6) REFERENCES t_dept2(dcode) -- t_dept2 테이블의 dcode 컬럼을 참조하는 deptno : 외래키
);

-- 위 생성 쿼리를 다음과 같이 별도 항목으로도 정의 가능.
CREATE TABLE t_emp4 (
    no NUMBER(4),
    name VARCHAR2(10) NOT NULL, 
    jumin VARCHAR2(13) NOT NULL,
    area NUMBER(1),
    deptno VARCHAR2(6), 
    PRIMARY KEY(no),  -- 제약조건 따로 지정 가능
    UNIQUE(jumin),
    CHECK(area < 5),
    FOREIGN KEY (deptno) REFERENCES t_dept2(dcode)
);


-- #9002
-- 제약조건명을 명시하여 작성 : ORACLE은 제약조건을 관리할 때 제약조건명으로 관리한다.
DROP TABLE t_emp3 CASCADE CONSTRAINT;
CREATE TABLE t_emp3(
    no NUMBER(4) CONSTRAINT emp3_no_pk PRIMARY KEY,
    name VARCHAR2(10) CONSTRAINT emp_name_nn NOT NULL,
    jumin VARCHAR2(13)
        CONSTRAINT emp3_jumin_nn NOT NULL
        CONSTRAINT emp3_jumin_uk UNIQUE,
    area NUMBER(1) CONSTRAINT emp3_area_ck CHECK(area < 5),
    deptno VARCHAR2(6) CONSTRAINT emp3_deptno_fk REFERENCES t_dept2(dcode)
);

-- 위 생성 쿼리를 다음과 같이 별도 항목으로도 정의 가능.
CREATE TABLE t_emp3 (
    no NUMBER(4),
    name VARCHAR2(10) NOT NULL, 
    jumin VARCHAR2(13) NOT NULL,
    area NUMBER(1),
    deptno VARCHAR2(6), 
    CONSTRAINT emp3_no_pk PRIMARY KEY(no),  -- 제약조건 따로 지정 가능
    CONSTRAINT emp3_jumin_uk UNIQUE(jumin),
    CONSTRAINT emp3_area_ck CHECK(area < 5),
    CONSTRAINT emp3_deptno_fk FOREIGN KEY (deptno) REFERENCES t_dept2(dcode)
);


-- #9003) t_emp4
SELECT owner, constraint_name, constraint_type, status
FROM USER_CONSTRAINTS
WHERE table_name = 'T_EMP4'; -- 테이블명 대문자로

SELECT owner, constraint_name, constraint_type, status
FROM USER_CONSTRAINTS
WHERE table_name = 'T_EMP3';


-- #9005) t_emp3의 제약조건에 맞는/위배되는 DML 작성
INSERT INTO t_emp3 VALUES(
    1, '오라클', '1234561234567', 4, 1000
);  -- 두번 실행하면 오류 : pk

INSERT INTO t_emp3 VALUES(
    2, '오라클', '1234561234567', 4, 1000
);  -- jumin UNIQUE 오류

INSERT INTO t_emp3 VALUES(
    2, '오라클', '222222222222222222', 4, 1000
);  -- NUMBER(13) 자릿수 초과 오류

INSERT INTO t_emp3 VALUES(
    2, 'tigers', '2222222222222', 10, 1000
);  -- CHECK (  < 5 ) 오류

SELECT * FROM t_dept2;
INSERT INTO t_emp3 VALUES(
    2, 'tigers', '2222222222222', 2, 2000
);  -- FK 오류

INSERT INTO t_emp3 (no, jumin, area, deptno) VALUES(
    2, '3333333333333', 4, 1001
);  -- NOT NULL 오류 : name의 제약조건이 NOT NULL인데 넣지 않았기 때문에 NULL이 들어간 것으로 됨

-- DML : INSERT, UPDATE, DELETE
-- INSERT 뿐 아니라, 모든 DML에 대해서 제약조건 동작
UPDATE t_emp3 SET area = 10 WHERE NO = 1;  -- CHECK 값 오류

-- #9005) ALTER 명령 사용해서 테이블이 제약조건 추가/수정 가능
ALTER TABLE t_emp4 
ADD CONSTRAINT emp4_name_uk UNIQUE(name);


-- #9006) t_emp4 테이블의 area 컬럼에 NOT NULL 제약조건 추가
ALTER TABLE t_emp4
ADD CONSTRAINT emp4_area_nn NOT NULL(area);
-- 이미 컬럼의 기본값이 NULL로 설정되어 있다. 그래서 ADD가 아닌 MODIFY로 해야한다.

ALTER TABLE t_emp4
MODIFY (area CONSTRAINT emp4_area_nn NOT NULL);


-- #9007) 외래키 추가 : t_emp4 테이블이 no 컬럼이 t_emp2 테입르의 empno 컬럼의 값을 참조하도록 참조기 제약조건 설정
-- 아래 문장 처음에는 에러
ALTER TABLE t_emp4
ADD CONSTRAINT emp4_name_fk FOREIGN KEY(name)
REFERENCES t_emp2(name);
-- ★이유 : 참조되는 부모 테이블의 컬럼은 PK이거나 UK이어야 한다.

-- 일단 부모 테이블의 name 컬럼을 UNIQUE로 바꾼 뒤 위의 쿼리를 실행해보자.
ALTER TABLE t_emp2
ADD CONSTRAINT emp2_name_uk UNIQUE(name);


-- 기출문제 실습) <요구사항>을 만족하는 테이블을 정의하는 SQL문을 작성하시오
-- * id(문자 20), name(문자 10), sex(문자 1), phone(문자 20) 속성을 가진다.
-- * id 속성은 기본키이다.
-- * sex 속성은 'f'또는 'm' 값만 갖도록 한다(제약조건명: sex_ck).
-- * id 는 t_emp2 테이블에 있는 name을 참조한다(제약조건명: id_fk).

CREATE TABLE patient
(
    id VARCHAR2(20) PRIMARY KEY,
    name CHAR(10),
    sex CHAR(1),
    phone CHAR(20),
    CONSTRAINT sex_ck CHECK(sex = 'f' OR sex = 'm'),
    CONSTRAINT id_fk FOREIGN KEY(id) REFERENCES t_emp2(name)
);


-- 복합키
-- 기본키(primary key) <-- 복수개의 컬럼으로 지정 가능
-- ORACLE로 복합키 제약조건 만들기
DROP TABLE test_member CASCADE CONSTRAINTS;

CREATE TABLE test_member(
    mb_uid NUMBER NOT NULL,
    mb_nick VARCHAR2(10) NOT NULL,
    mb_name VARCHAR2(10) NOT NULL,
    CONSTRAINT test_member_pk PRIMARY KEY(mb_uid, mb_nick)  -- 복합키 구성
);
INSERT INTO test_member VALUES(1, 'aaa', 'John');
INSERT INTO test_member VALUES(1, 'bbb', 'John');
INSERT INTO test_member VALUES(2, 'aaa', 'John');
INSERT INTO test_member VALUES(2, 'bbb', 'John');
-- 기본키가 두개이며 같은 기본키가 있지만, 두개의 조합이 다르면 INSERT 가능하다


SELECT * FROM test_member;



