-- 그룹함수 (Aggregate Function)

-- COUNT() : 개수를 계산하는 그룹함수
SELECT COUNT(*), COUNT(hpage) FROM t_professor;     -- 그룹함수에서 NULL값은 계산에서 제외

-- SUM() : 합계를 계산하는 그룹함수
SELECT SUM(pay), SUM(bonus) FROM t_professor;
SELECT COUNT(pay), COUNT(bonus) FROM t_professor;

-- AVG() : 평균을 계산하는 그룹함수
SELECT AVG(pay), AVG(bonus) FROM t_professor;
        -- NULL 값이 배제되기 떄문에 평균 계산 등은 조심해야한다.
SELECT
    AVG(bonus),
    AVG(NVL(bonus, 0))
FROM t_professor;

-- t_professor 테이블에서 '학과별로' 교수들의 평균 보너스를 출력하기

-- 아래와 같은 쿼리 불가 : 그룹함수와 컬럼(혹은 값) 같이 SELECT 불가
SELECT deptno, AVG(bonus) FROM t_professor;     -- ERROR

SELECT deptno, AVG(bonus), COUNT(*) FROM t_professor GROUP BY deptno;   -- deptno 컬럼별로 그룹함수 수행

SELECT deptno, ROUND(AVG(NVL(bonus, 0)), 1) AS "보너스 평균" FROM t_professor GROUP BY deptno;   -- deptno 컬럼별로 그룹함수 수행

-- 학생테이블
-- '학년별' 학생의 수
SELECT grade, deptno1, COUNT(*) FROM t_student GROUP BY grade, deptno1;

-- #5101
SELECT deptno, position, AVG(pay) AS "평균 급여" 
FROM t_professor 
GROUP BY deptno, position 
ORDER BY deptno ASC, position ASC
;


-- 학과별 평균급여
SELECT deptno, AVG(pay)
FROM t_professor
WHHERE AVG(pay) > 450   -- 그룹함수는 WHERE 조건절에서 사용불가!!
GROUP BY deptno
;           -- ERROR


-- HAVING 을 사용하여 그룹함수 결과 조건 비교
SELECT deptno, AVG(pay)
FROM t_professor
GROUP BY deptno
HAVING AVG(pay) > 450
;

-- 순서 = SELECT - FROM - WHERE - GROUP BY - HAVING - ORDER BY    -> 반드시 기억해야 한다!


-- #5102

SELECT mgr AS "매니저", COUNT(*) AS "직원수", SUM(sal) AS "급여총액", AVG(sal) AS "급여평균", AVG(NVL(comm, 0)) AS "교통비 평균"
FROM t_emp
WHERE job <> 'PRESIDENT'
GROUP BY mgr
;

-- #5103
SELECT deptno, COUNT(*) 총인원, ROUND(AVG(sysdate-hiredate), 3) 근속평균, AVG(pay) 급여평균, AVG(NVL(bonus, 0)) 보너스평균
FROM t_professor
WHERE position IN ('정교수', '조교수')
-- WHERE position = '정교수' OR position = '조교수'
GROUP BY deptno
;

-- #5104
SELECT deptno1 학과, MAX(weight) - MIN(weight) "최대최소 몸무게차"
FROM t_student
GROUP BY deptno1
;


-- #5105 : 최대최소 몸무게차가 30 이상인 학과만 출력하려면?
SELECT deptno1 학과, MAX(weight) - MIN(weight) "최대최소 몸무게차"
FROM t_student
GROUP BY deptno1
HAVING (MAX(weight)-MIN(weight)) >= 30
;

