-- 오라클의 'JOIN'    <-- INNER JOIN

SELECT t_emp.deptno, t_emp.empno, t_emp.ename, t_dept.dname
FROM t_emp INNER JOIN t_dept
ON t_emp.deptno = t_dept.deptno
;

------------------------------------------------------------------

-- NATURAL JOIN (자연조인)
-- 두 테이블 간의 동일한 이름을 갖는 모든 컬럼에 대해 EQUI JOIN을 수행한다.
-- NATURAL JOIN 이 수행되면, ON 조건절, WHERE 조건절, USING 조건절에서 JOIN조건 정의 불가!

SELECT * FROM t_emp;
SELECT * FROM t_dept;

-- 사원번호, 사원이름, 소속부서 코드, 소속부서 이름
SELECT deptno, empno, ename, dname
FROM t_emp NATURAL JOIN t_dept
ORDER BY empno
;
-- 위의 코드 비록 별도의 'JOIN조건'을 주진 않았지만 (즉, WHERE, ON, USING 없음) 두 테이블의 deptno가 동일이름 칼럼으로 '자동으로' 인식하여 JOIN 처리

SELECT e.deptno, empno, e.ename, d.dname
FROM t_emp e, t_dept d
WHERE e.deptno = d.deptno
ORDER BY empno
;


-- #6302
-- 잘못된 예(natural join은 어차피 중복이 빠지기 때문에 별명을 줄 수 없도록 되어있다)
SELECT t_emp.deptno, empno, ename, dname
FROM t_emp NATURAL JOIN t_dept;

-- #6303
SELECT *
FROM t_emp NATURAL JOIN t_dept;

-- inner join은 중복되어 나온다. 때문에 별명이 반드시 필요하다.
SELECT *
FROM t_emp JOIN t_dept ON t_emp.deptno = t_dept.deptno;

-----------------------------------------------------------------------

-- #6305
CREATE TABLE dept_temp
AS
SELECT * FROM t_dept;

SELECT * FROM dept_temp;

-- #6306) dname 컬럼에서 RESEARCH -> R&D로 SALES -> MARKETING 으로 수정
UPDATE dept_temp SET dname = 'R&D' WHERE dname = 'RESEARCH';
UPDATE dept_temp SET dname = 'MARKETING' WHERE dname = 'SALES';

SELECT * FROM dept_temp;


-- #6307) 두개의 t_dept 와 depp_temp 테이블의 컬럼명은 같다. 그러나 데이터가 다르다. NATURAL JOIN에서 어떻게 동작?
SELECT * FROM t_dept NATURAL JOIN dept_temp;
-- 컬럼이 다 같으므로 데이터가 완전히 같은 행만 나올 수 있다. -> 2개 행
-- inner join이었으면 colunm이 6개 나왔을 거임.


-- USING 조건절
-- #6308
SELECT * FROM t_dept JOIN dept_temp USING (deptno);
-- USING을 사용한 deptno만 하나가 나오고 dname, loc는 INNER JOIN이기 때문에 2번씩 나온다.

-- #6309) USING 잘못된 경우
SELECT t_dept.deptno, t_dept.dname, t_dept.loc, dept_temp.dname, dept_temp.loc FROM t_dept JOIN dept_temp USING (deptno);

-- #6310) USING 바른 경우
SELECT deptno, t_dept.dname, t_dept.loc, dept_temp.dname, dept_temp.loc FROM t_dept JOIN dept_temp USING (deptno);

-- #6311) 이번에는 t_dept와 dept_temp 테이블의 일부 데이터 내용이 변경되었던 dname칼럼을 join 조건으로 [INNER]JOIN의 USING 조건절을 수행
SELECT *
FROM t_dept JOIN dept_temp USING (dname)
;
-- dname이 두개는 같고 두개는 달라서 같은 두개에 대한 값만 나오게 된다.


-- #6312) 세 개의 칼럼명이 모두 같은 dept와 dept_temp 테이블을 loc와 deptno 2개 칼럼을 이용한 [INNER] JOIN의 USING 조건절로 수행.
SELECT *
FROM t_dept JOIN dept_temp USING (loc, deptno)
;

-- 이렇게 하면 NATURAL JOIN과 똑같다.
SELECT *
FROM t_dept JOIN dept_temp USING (deptno, dname, loc)
;


