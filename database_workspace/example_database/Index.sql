-- 인덱스 (Index)
-- 데이터(레코드)를 빠르게 접근하기 위해 <키, 주소> 쌍으로 구성괸 데이터

-- 인덱스가 없으면 테이블의 특정값을 찾기 위해 모~든 데이터를 다 뒤지는 TABLE SCAM  발생 

-- 기본키를 위한 인덱스 --> 기본 인덱스.
--      테이블에 기본키가 있으면, 기본키에 대한 기본 인덱스가 자동 생성된다.

-- 인덱스의 종류
-- m-원 검색트리, B-트리, B*-트리, B+-트리 등이 있다.

-- 인덱스를 잘 사용하면 성능이 좋아질 수 있지만, 잘못 설정하면 역효과!!


-----------------------------------------------------------
-- ORACLE
-- 기본적으로 INSERT 되는 순서대로 입력은 되지만, 정렬없이 저장됨.

SELECT empno, ROWID
FROM t_emp;

SELECT ename, ROWID     -- ROWID 나오는 순서. 오름차순으로 나옴.
FROM t_emp;

SELECT ename, ROWID
FROM t_emp ORDER BY ename DESC;

-------------------------------------------------------------
-- 1. B-tree 인덱스
--   (1) Unique Index
--   (2) Non Unique Index
--   (3) Function Based Index
--   (4) Descending Index
--   (5) 결합 인덱스 (Composite Index)


------------------------------------------------------------
-- Unique Index
--   key 값이 중복되는 데이터가 없다는 뜻.
--   이 경우 인덱스 성능 매우 좋아짐.

-- 인덱스 생성 구문
--   CREATE UNIQUE INDEX 인덱스명
--   ON 테이블이름(컬럼명1 ASC | DESC, 컬럼명2 ...)


-- 연습
-- t_dept2 테이블의 dname 컬럼에 UNIQUE INDEX 생성
-- 인덱스 이름은 idx_dept2_dname 으로 생성

SELECT * FROM t_dept2;

CREATE UNIQUE INDEX idx_dept2_dname
ON t_dept2(dname);

-- 일단 UNIQUE INDEX 지정되면 데이터값 중복으로 들어갈 수 없다.
INSERT INTO t_dept2 VALUES(9100, '임시매장', 1006, '서울지사');

-- 아래 INSERT 는 danme이 PK가 아님에도 에러 발생! --> UNIQUE INDEX ERROR!
INSERT INTO t_dept2 VALUES(9101, '임시매장', 1006, '부산지사');


-- Non UNIQUE INDEX
--   중복된 데이터가 들어와야 하는 경우 사용

-- 인덱스 생성 구문
--   CREATE UNIQUE INDEX 인덱스명
--   ON 테이블이름(컬럼명1 ASC | DESC, 컬럼명2 ...)

-- 예제
-- t_professor 테이블의 position 컬럼에 Non UNIQUE 인덱스를 내림차순 생성
CREATE INDEX idx_prof_position
ON t_professor(position DESC);

-------------------------------------------------------------------
/* 
    인덱스는 어느 칼럼에 만들어 두어야 하나?
    ★ WHERE절에 오는 조건 컬럼이나, JOIN조건절에 오는 조건 컬럼에 만들어 두는게 원칙 ★
    WHERE sal = 100    <--- sal 컬럼에 인덱스를 설정해두어야 한다.

    그러나!
    WHERE sal + 100 = 200 조건으로 검색하면 만들어 놓은 인덱스 활용 안됨. (Index Suppressing Erorr 라고 함)

    명심 ! : WHERE 조건절에 다른 형태로 가공하지 말자.

    그런데..
    꼭 그렇게 사용해야 한다면..?
    WHERE sal + 100 = 200 ...???

    그래서 이때는
    '함수 기반 인덱스(Function Based Index : FBI)'를 사용한다.
*/

-- 함수 기반 인덱스 예제
CREATE INDEX inx_prof_pay_fbi
ON t_professor(pay + 100);

-- FBI는 근본 해결책이 아니다.
-- 조건이 변경되면 다시 만들어야 되고...
-- FBI는 기존 인덱스를 활용할 수 없는 단점도 있다...

-----------------------------------------------------------------
-- DESCENDING INDEX
--   인덱스는 생성할 때, 기본적으로는 오름차순으로 생성되나,
--   내림차순 인덱스 만들 때 사용.
--     ex) 계좌조회, 최근날짜 (큰 날짜)
--     ex) 매출상위 매장순

-- 예제
CREATE INDEX idx_prof_pay
ON t_professor(pay DESC);

-----------------------------------------------------------------
-- 결합 인덱스 (Composite Index)
--   두개 이상의 컬럼의 합쳐서 만드는 인덱스.
--   WHERE 조건컬럼이 2개 이상의 AND 로 연결된 경우.

-- 예)
SELECT name, deptno1
FROM t_student
WHERE name = '서진수' AND deptno1 = '101';

-- 위와 같은 커리가 빈번하다면 + 데이터가 크다면.. 아래와 같은 결합 인덱스 만들면 좋다.
CREATE INDEX idx_student_name_deptno1
ON t_student(name, deptno1);

-------------------------------------------------------------------
-- 인덱스 사용시 주의 사항...
-- 인덱스가 항상 SQL 성능을 빠르게 할까?
-- SQL 성능이 느려졌다고, 빨라질 때까지 마구마구 인덱스 만들면 --> 매우 위험.

-- 인덱스 취약점) DML에 취약
--   INSERT
--     Index Split 발생 가능 : '인덱스 블럭' 용량 초과..  <-- 이 과정 시간 많이 걸림.
--   DELETE
--     DELETE 사용하면 데이터는 지워지지만, 인덱스는 안지워져요..
--       2만건 데이터중 1만건 DELETE해도 인덱스는 2만개 남아있다.
--   UPDATE
--     데이터 UPDATE해도 인덱스 업데이트 안됨...
--     UPDATE 시 기존의 INDEX 삭제되고, 새로운 데이터 + 인덱스 생성.
--     즉, UPDATE 작업은 두가지 작업이 동시 발생 --> 부하를 더 준다.

