-- 계층형 쿼리(Hierarchy Query)

SELECT * FROM t_dept2;

SELECT LPAD(dname, 10, '*') FROM t_dept2;

SELECT dname, LEVEL
FROM t_dept2
CONNECT BY PRIOR dcode = pdept
START WITH dcode = 0001
;


SELECT LPAD(dname, LEVEL*6, '*') 부서명
FROM t_dept2
CONNECT BY PRIOR dcode = pdept
START WITH dcode = 0001
;

SELECT LPAD(dname, LEVEL*6, '*') 부서명
FROM t_dept2
CONNECT BY dcode = PRIOR pdept
START WITH dcode = 1011
;

-- CONNECT BY 절에는 subQuery 사용불가!
-- 계층형 쿼리가 수행되는 순서
-- 1. START WITH 절의 시작조건 검색
-- 2. CONNECT BY 절의 연결 조건 검색
-- 3. WHERE 조건절 검색


-- 연습 1
SELECT * FROM t_emp2;
SELECT * FROM t_dept2;

SELECT LPAD(e.name || ' ' || d.dname || ' ' || NVL(e.post, '사원'), LEVEL*22, '-') "이름과 직급"
FROM t_emp2 e, t_dept2 d
WHERE e.deptno = d.dcode
CONNECT BY PRIOR e.empno = e.pempno
START WITH e.empno = 20000101 
;


SELECT LPAD(e.name || ' ' || d.dname || ' ' || NVL(e.post, '사원'), LEVEL*22, '-') "이름과 직급"
FROM t_emp2 e, t_dept2 d
WHERE e.deptno = d.dcode
CONNECT BY PRIOR e.empno = e.pempno
START WITH e.empno = 20066102 
;


SELECT LPAD(e.name || ' ' || d.dname || ' ' || NVL(e.post, '사원'), LEVEL*22, '-') "이름과 직급"
FROM t_emp2 e, t_dept2 d
WHERE e.deptno = d.dcode
CONNECT BY e.empno = PRIOR e.pempno
START WITH e.empno = 20100119 
;

