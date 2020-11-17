-- SELECT의 결과물 중 맨 위의 5개만 출력해보고 싶다면?
-- ex) 게시판 목록 + 페이징

-- DBMS마다 구현방법 다름
-- MY SQL : LIMIT
-- MT SQL server : TOP
-- ORACLE : ROWNUM

-- 알게 모르게(?) 오라클이 붙여주는 행번호 객체(ROWNUM)
SELECT ROWNUM, empno, ename, sal FROM t_emp;

-- 직원번호 역순
SELECT ROWNUM, empno, ename, sal FROM t_emp
ORDER BY empno DESC;
-- 위 결과에서 상위 5개만 보여주기
SELECT ROWNUM, empno, ename, sal FROM t_emp
WHERE ROWNUM <= 5
ORDER BY empno DESC;

-- 심지어 SELECT 절에서 ROWNUM 없어도 동작! (ROWNUM은 오라클이 자동으로 붙여주기 때문에)
SELECT empno, ename, sal FROM t_emp
WHERE ROWNUM <= 5
ORDER BY empno DESC;


-- ROWNUM은 SELECT 이후에 붙여지는 객체
-- 이후에 ORDER BY를 하면 순서가 망가질 수도 있다.
SELECT ROWNUM, empno, ename, sal FROM t_emp
WHERE ROWNUM <= 5
ORDER BY sal DESC;


-- ROWNUM > 5 ??
SELECT ROWNUM, empno, ename, sal FROM t_emp
WHERE ROWNUM > 5
ORDER BY sal DESC;
-- ROWNUM 범위가 1부터 명시 안되면 안나옴


-- ex) 게시판 한 페이지에 5개의 글씩 표현. 1페이지의 경우
SELECT ROWNUM, empno, ename, sal FROM t_emp
WHERE ROWNUM >= 1 AND ROWNUM < 1+5
ORDER BY sal DESC;



-- 아래와 같은 순서로 접근

SELECT ROWNUM AS RNUM, T.*
FROM (SELECT empno, ename, sal FROM t_emp ORDER BY empno DESC) T;

SELECT * FROM
(
    SELECT ROWNUM AS RNUM, T.* 
    FROM (SELECT empno, ename, sal FROM t_emp ORDER BY empno DESC) T
)
WHERE RNUM >= 11 AND RNUM < 11+5
;


---------------------------------------------------------------------
-- ROW_NUMBER() OVER (ORDER BY 정렬칼럼1, ... )
-- 특정 컬럼의 값을 기준으로 정렬한 뒤 '순서' 매기기
-- 사용법
-- ROW_NUMBER() OVER (ORDER BY 정렬칼럼1, ... ), 서브쿼리컬럼 FROM 서브쿼리
SELECT ROW_NUMBER() OVER (ORDER BY sal DESC) AS RNUM, T.*
FROM (SELECT empno, ename, sal FROM t_emp ORDER BY empno DESC) T
;

------------------------------------------------------------------------------
-- RAMK() OVER (ORDER BY 정렬컬럼1, ...)

-- 학생점수
SELECT s.name, e.total
FROM t_student s, t_exam01 e
WHERE s.studno = e.studno
;


-- ROW_NUMBER와 RAMK()의 차이
SELECT
    ROW_NUMBER() OVER (ORDER BY total DESC) AS RNUM,
    RANK() OVER (ORDER BY total DESC) AS RNK,
    T.*
FROM (
    SELECT s.name, e.total
    FROM t_student s, t_exam01 e
    WHERE s.studno = e.studno
) T
;

