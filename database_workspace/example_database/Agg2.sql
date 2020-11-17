-- 다양한 그룹함수들

-- 자동으로 소계 / 합계를 구해주는 함수
-- ROLLUP 함수 : 주어진 데이터들의 '소계'를 구해주고
-- CUBE 함수 : 주어지 데이터 들의 '총계'까지 구해주는 함수

SELECT deptno, position, count(*), sum(pay)
FROM t_professor
GROUP BY deptno, position
;

-- 위 결과에 ROLLUP 함수를 더하면?
SELECT deptno, position, count(*), sum(pay)
FROM t_professor
GROUP BY ROLLUP(deptno, position)
;

-- deptno 컬럼으로 ROLLUP
SELECT deptno, position, count(*), sum(pay)
FROM t_professor
GROUP BY position, ROLLUP(deptno)   -- position 별로 묶인 소계
;

SELECT deptno, position, count(*), sum(pay)
FROM t_professor
GROUP BY deptno, ROLLUP(position)   -- deptno 별로 묶인 소계
;


-- CUBE() 함수
-- ROLLUP 처럼 소계도 출력하고 + 전체 총계까지 출력
SELECT deptno, position, count(*), sum(pay)
FROM t_professor
GROUP BY CUBE(deptno, position)
;

SELECT deptno, position, count(*), sum(pay)
FROM t_professor
GROUP BY deptno, CUBE(position)    -- 그룹핑은 deptno 별로 하고 각 position 별로 계산
;

SELECT deptno, position, count(*), sum(pay)
FROM t_professor
GROUP BY position, CUBE(deptno)    -- 그룹핑은 position 별로 하고 각 deptno 별로 계산
;

-------------------------------------------------------------------
-- GROUPING 함수
--   ROLLUP, CUBE 함수와 함께 사용되는 함수로..
--   어떤 컬럼이 해당 그룹핑 작업에 사용되었느닞 아닌지를 구별해주는 역할
--   만약 어떤 컬럼이 그룹핑 작업에 사용되었으면 0을 반환하고, 사용되지 않았으면 1을 반환함.

SELECT deptno, SUM(pay)
FROM t_professor
GROUP BY deptno
;

SELECT deptno, SUM(pay)
FROM t_professor
GROUP BY ROLLUP(deptno)
;

SELECT deptno, SUM(pay), GROUPING(deptno)
FROM t_professor
GROUP BY ROLLUP(deptno)
;

SELECT deptno, position, SUM(pay),
                GROUPING(deptno) g_deptno,
                GROUPING(position) g_position   -- 부서별 소계 구할 때는 직급(postiion)은 사용되지 않았음을 확인!
FROM t_professor
GROUP BY ROLLUP(deptno, position)
;

-------------------------------------------------------------
-- LISTAGG() 함수
SELECT deptno, 
    LISTAGG(name, '**') WITHIN GROUP(ORDER BY hiredate) "LISTAGG"
FROM t_professor
GROUP BY deptno
;

-----------------------------------------------------------------
-- PIVOT() 함수 : row를 column 단위로 변경
-- UNPIVOT() 함수는 : column을 row 단위로 변경

SELECT * FROM t_cal;

-- pivot 기능을 사용하여 달력 만들기
SELECT * FROM (SELECT c_week "주", c_day, c_num_day FROM t_cal)
PIVOT(
    MAX(c_num_day)
    FOR c_day IN (
        '일' AS "일",
        '월' AS "월",
        '화' AS "화",
        '수' AS "수",
        '목' AS "목",
        '금' AS "금",
        '토' AS "토"
    )
)
ORDER BY "주"
;


SELECT * FROM (SELECT deptno, job, empno FROM t_emp)
PIVOT(
    COUNT(empno)    -- 수행할 그룹함수
    FOR job IN (    -- 수행할 컬럼
        'CLERK' AS "CLERK",
        'MANAGER' AS "MANAGER", 
        'PRESIDENT' AS "PRESIDENT",
        'ANALYST' AS "ANALYST", 
        'SALESMAN' AS "SALESMAN"
    )
)
ORDER BY deptno
;

CREATE TABLE t_unpivot
AS(
    SELECT * FROM (SELECT deptno, job, empno FROM t_emp)
    PIVOT(
        COUNT(empno)
        FOR job IN (
            'CLERK' AS "CLERK",
            'MANAGER' AS "MANAGER", 
            'PRESIDENT' AS "PRESIDENT",
            'ANALYST' AS "ANALYST", 
            'SALESMAN' AS "SALESMAN"
        )
    )
)
;

SELECT * FROM t_unpivot;

SELECT * FROM t_unpivot
UNPIVOT(
    empno FOR jop IN (CLERK, MANAGER, PRESIDENT, ANALYST, SALESMAN)
)
;

