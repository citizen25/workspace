-- Sub Query

-- #7101
SELECT sal FROM t_emp WHERE ename = 'SCOTT';

SELECT ename, sal
FROM t_emp
WHERE sal > ( SELECT sal FROM t_emp WHERE ename = 'SCOTT' )
;


-- #7102
-- 가장 키 큰 학생의 키
SELECT MAX(height) FROM t_student;

SELECT name, height
FROM t_student
WHERE height = ( SELECT MAX(height) FROM t_student )
;


-- #7103

SELECT s.name "학생이름", d.dname "제1전공"
FROM t_student s, t_department d
WHERE s.deptno1 = d.deptno
    AND s.deptno1 = ( SELECT deptno1 FROM t_student WHERE name = '이윤나' )
;


-- #7104

-- 송도권 교수의 입사일
SELECT hiredate FROM t_professor WHERE name = '송도권';

SELECT p.name "교수명", TO_CHAR(p.hiredate, 'YYYY-MM-DD') "입사일", d.dname "학과명"
FROM t_professor p, t_department d 
WHERE p.deptno = d.deptno
    AND p.hiredate > (SELECT hiredate FROM t_professor WHERE name = '송도권')
;


-- #7105

SELECT AVG(weight) FROM t_student WHERE deptno1 = 101;

SELECT name "이름", weight "몸무게"
FROM t_student
WHERE weight > ( SELECT AVG(weight) FROM t_student WHERE deptno1 = 101 )
;


-- #7016

-- 심슨 교수의 입사일
SELECT hiredate FROM t_professor WHERE name = '심슨';

-- 조인형 교수의 월급
SELECT pay FROM t_professor WHERE name = '조인형';

-- 완성형
SELECT name "이름", pay "급여", TO_CHAR(hiredate, 'YYYY-MM-DD') "입사일"
FROM t_professor
WHERE hiredate = (SELECT hiredate FROM t_professor WHERE name = '심슨')
    AND pay < (SELECT pay FROM t_professor WHERE name = '조인형')
;


-- #7107

SELECT dcode FROM t_dept2 WHERE area = '서울지사';

SELECT empno, name, deptno
FROM t_emp2
WHERE deptno IN (SELECT dcode FROM t_dept2 WHERE area = '서울지사')
-- WHERE deptno IN (1000, 1001, 1002, 1010)과 같다.
;


-- #7108) 과장 직급의 최소 연봉자 보다 연봉이 높은 사람 골라내기
-- ANY : 큰거 찾을 때는 작은거 기준, 작은거 찾을 때는 큰거 기준 (하나하나로 보는 느낌?)
SELECT * FROM t_emp2;
SELECT pay FROM t_emp2 WHERE post = '과장';

SELECT name "이름", post "직급", TO_CHAR(pay, '999,999,999') || '원' "연봉"
FROM t_emp2
WHERE pay >ANY (SELECT pay FROM t_emp2 WHERE post = '과장');

-- #7109) 체중이 4학년 학생들의 체중에서 가장 적게 나가는 학생보다 몸무게가 적은 학생 골라내기
-- ALL : 모든 것 (그룹을 통째로 하나로 보는 느낌)
SELECT weight FROM t_student WHERE grade=4;

SELECT name "이름", grade "학년", weight "몸무게"
FROM t_student
WHERE weight <ALL (SELECT weight FROM t_student WHERE grade=4);



-- Sub Query 정리
-- IN : 같은 값을 찾음
-- >ANY : 최소값을 반환함(서브쿼리 결과중 가장작은것보다 큰)
-- <ANY : 최대값을 반환함(서브쿼리 결과중 가장큰것보다 작은)
-- <ALL : 최소값을 반환함(서브쿼리 결과중 가장작은것보다 작은)
-- >ALL : 최대값을 반환함(서브쿼리 결과중 가장큰것보다 큰)
-- EXIST : Sub Query 값이 있을 경우 반환




-- 다중칼럼 Sub Query

-- #7201) 각 학년 별로 최대 키를 가진 학생들 골라내기

SELECT grade "학년", MAX(height)
FROM t_student GROUP BY grade;

SELECT grade "학년", name "이름", height "키"
FROM t_student
WHERE (grade, height) IN
    (SELECT grade, MAX(height)
    FROM t_student GROUP BY grade)
ORDER BY "학년" ASC
;

-- #7202) 각 학과별로 입사일이 가장 오래된 교수의 교수번호와 이름, 학과명 출력

SELECT deptno, MIN(hiredate)
FROM t_professor GROUP BY deptno;


SELECT p.profno "교수번호", p.name "교수명", TO_CHAR(p.hiredate, 'YYYY-MM-DD') "입사일", d.dname "학과명"
FROM t_professor p, t_department d
WHERE p.deptno = d.deptno
    AND (p.deptno, p.hiredate) IN
        (SELECT deptno, MIN(hiredate) FROM t_professor GROUP BY deptno)
ORDER BY "학과명" ASC
;

-- #7203) 직급별로 해당 직급에서 최대 연봉을 받는 직원의 이름과 직급, 연봉을 출력(연봉 오름차순)

SELECT post, MAX(pay) FROM t_emp2 GROUP BY post;

SELECT name "사원명", post "직급", pay "연봉" 
FROM t_emp2
WHERE (post, pay) IN (SELECT post, MAX(pay) FROM t_emp2 GROUP BY post)
ORDER BY pay
;

-- #7204) 각 부서별 평균 연봉을 구하고 그 중에서 평균 연봉이 가장 적은 부서의 평균 연봉보다 적게 받는 직원들의 부서명, 직원명, 연봉을 출력

SELECT AVG(pay) FROM t_emp2 GROUP BY deptno;

SELECT d.dname "부서명", e.name "직원명", e.pay "연봉"
FROM t_emp2 e, t_dept2 d
WHERE e.deptno = d.dcode
    AND e.pay <ALL (SELECT AVG(pay) FROM t_emp2 GROUP BY deptno)
ORDER BY 3 ASC      -- e.pay 또는 "연봉"으로 써도 된다.
;


-- 상호연관 Sub Query

-- #7205) 직원들 중에서 자신의 직급의 평균연봉과 같거나 많이 맞는 사람들의 이름, 직급, 현재 연봉 출력

SELECT post, AVG(pay) FROM t_emp2 GROUP BY post;
SELECT AVG(pay) FROM t_emp2 WHERE post = '과장';
SELECT AVG(pay) FROM t_emp2 WHERE post = '부장';

SELECT a.name "사원이름", NVL(a.post, ' ') "직급", a.pay "급여"
FROM t_emp2 a
WHERE a.pay >= ( 
    SELECT AVG(b.pay) 
    FROM t_emp2 b WHERE NVL(a.post, ' ') = NVL(b.post, ' ') )
;


-- Scalar Sub Query (스칼라 서브쿼리)
-- SELECT 절에 오는 Sub Query

-- #7206
SELECT dname FROM  t_dept2 d WHERE e.deptno = d.dcode;

SELECT name "사원이름", 
    (SELECT dname FROM  t_dept2 d 
    WHERE e.deptno = d.dcode) "부서이름"
FROM t_emp2 e;


