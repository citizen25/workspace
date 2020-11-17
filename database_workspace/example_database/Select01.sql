-- dual은 row 1개짜리 dummy table;
SELECT '안녕하세요' FROM dual;

SELECT 'ABCD' FROM phonebook;

SELECT 100 FROM dual;

SELECT * FROM t_emp;  -- emp 테이블에서 모든 컬럼(*) SELECT 한다

-- 원하는 컬럼만 조회 (SELECT)
-- t_emp 테이블에서 직원번호(empno)와 직원이름(ename)만 SELECT
SELECT empno, ename FROM t_emp;
SELECT ename, empno FROM t_emp;

SELECT name FROM t_professor;
SELECT name, '교수님 싸랑해요~' FROM t_professor;

-- 컬럼 별칭(alias) 사용하여 SELECT
SELECT studno, name FROM t_student;
SELECT studno 학번, name 이름 FROM t_student;

-- alias 에 띄어쓰기가 있으면 쌍따옴표로 묶어주기(AS 는 써도 그만 안 써도 그만)
SELECT studno AS "학번 번호", name AS 이름 FROM t_student;

-- 연습
SELECT empno AS 사원번호, ename AS 사원명, job AS 직업 FROM t_emp;
SELECT deptno AS "부서#", dname AS "부서명", loc AS "위치" FROM t_dept;

-- DISTINCT : 중복값 제거하고 출력
-- SELECT DISTINCT [칼럼명 또는 표현식] FROM [테이블, 뷰]
SELECT deptno FROM t_emp;
SELECT DISTINCT deptno FROM t_emp;

-- 연습
SELECT DISTINCT deptno1 FROM t_student;
SELECT DISTINCT job FROM t_emp;

-- DISTINCT 앞에 컬럼이 올 수 없다.
-- 다른 컬럼과 같이 출력시에는 최초값 하나만 추출됨.
SELECT DISTINCT deptno1, name FROM t_student;

-- || : 필드, 문자열 연결
SELECT name, position FROM t_professor;
SELECT name || '-' || position AS "교수님 목록" FROM t_professor;

-- 연습
SELECT '서진수의 키는 ' || height || 'cm, ' || '몸무게는 ' || weight || 'kg입니다' AS "학생의 키와 몸무게" FROM t_student;

-- 산술연산자
--  +, -, *, /

-- 직원테이블(t_emp) 직원이름(ename), 급여(sal)의 2.5% 상승분 SELECT
SELECT ename, sal, sal * 1.025 AS "급여 2.5% 인상분" FROM t_emp;

SELECT * FROM t_emp;

-- 일반 산술연산에 null 값과의 연산 결과는 --> null !!!
SELECT sal, comm, sal + comm FROM t_emp;

-- WHERE 조건절 : 원하는 조건에 맞는 레코드만 검색

-- SELECT [컬럼명, 표현식]
-- FROM [테이블, 뷰]
-- WHERE [조건식]

--- 직원 테이블에 직책(job)이 SALESMAN인 사람만 조회 (SALESMAN 은 대소문자 구문 - 문자열 안)
SELECT * FROM t_emp WHERE job = 'SALESMAN';
SELECT ename, sal, deptno FROM t_emp WHERE deptno = 10;
SELECT ename, sal FROM t_emp WHERE sal > 2000;
SELECT ename, empno, sal FROM t_emp WHERE ename = 'SCOTT';

-- 연습
-- OR, AND, NOT
SELECT name, grade FROM t_student WHERE grade = 2 OR grade = 3;

SELECT name, grade FROM t_student WHERE grade IN (2, 3);    -- IN (값1, 값2, ...  )

SELECT name, grade FROM t_student WHERE grade NOT IN (1, 4);

SELECT name, grade FROM t_student WHERE grade BETWEEN 2 AND 3;

SELECT name, grade FROM t_student WHERE grade > 1 AND grade < 4;


-- LIKE 연산자
SELECT ename FROM t_emp WHERE ename LIKE 'A%';

-- 교수님(t_professor) 중에서 '김' 씨 성을 가진 교수님만 이름(name) 출력(LIKE 사용)
SELECT name FROM t_professor WHERE name LIKE '김%';

-- 직원테이블(t_emp) 에서 직원이름(ename) 중에 NE가 포함된 직원만 출력
SELECT ename FROM t_emp WHERE ename LIKE '%NE%';

-- 직원테이블(t_emp)에서 직원이름(ename)의 두번쨰 글자가 'A'인 사람의 이름(ename) 출력
SELECT ename FROM t_emp WHERE ename LIKE '_A%';

-- 보너스(bonus)를 못 받는 교수님의 이름(name)과 직급(position)을 SELECT
SELECT * FROM t_professor;
SELECT name, position FROM t_professor WHERE bonus IS NULL; -- null 여부
SELECT name, position FROM t_professor WHERE bonus IS NOT NULL; -- null 여부

-- ORDER BY [컬럼, 컬럼번호] : SELECT 결과 정렬
-- 직원(t_emp) 중 이름(ename)에 'L'이 들어간 사람의 이름을 사전오름차순으로 정렬하여 SELECT 하기
SELECT ename FROM t_emp WHERE ename LIKE '%L%' ORDER BY ename ASC; -- 오름차순 (ASC 생략가능)

-- 내림차순 정렬
SELECT ename FROM t_emp WHERE ename LIKE '%L%' ORDER BY ename DESC; --내림차순


-- 직원(t_emp)의 이름, 직책, 급여를 SELECT 하되 우선은 직책(job) 내림차순으로, 그리고 급여(sal) 오름차순으로 SELECT 하기
SELECT ename, job, sal FROM t_emp ORDER BY job DESC, sal ASC;   -- 복수 정렬은 콤마(,)로 가능
