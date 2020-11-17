-- 날짜 함수들
SELECT SYSDATE FROM dual;

-- 날짜와 관련된 기본적인 연산
SELECT
    SYSDATE AS "오늘",
    SYSDATE + 1 AS "내일(24시간 뒤)", 
    SYSDATE - 2 AS "그저께",
    SYSDATE + 1/24 AS "1시간 뒤"
FROM dual;

-- 일자 차이 계산
SELECT
    SYSDATE "오늘",
    SYSDATE -TO_DATE('2019-07-09', 'YYYY-MM-DD') "강의 촬영일로부터"
FROM dual;

-- MONTH_BERWEEN 함수 : 두 날짜 사이의 개월 수
SELECT 
    -- 두 날짜 중 큰 날짜(나중 날짜)를 먼저 사용해야 양수로 결과값 나옴
    MONTHS_BETWEEN(TO_DATE('2012-03-01', 'RRRR-MM-DD'), TO_DATE('2012-01-01', 'RRRR-MM-DD')) AS "양수값", 
    MONTHS_BETWEEN(TO_DATE('2012-01-01', 'RRRR-MM-DD'), TO_DATE('2012-03-01', 'RRRR-MM-DD')) AS "음수값",
    -- 두 날짜가 같은 달에 속해 있으면 특정 규치으로 계산된 값이 나옴
    MONTHS_BETWEEN(TO_DATE('2012-02-29', 'RRRR-MM-DD'), TO_DATE('2012-02-01', 'RRRR-MM-DD')) AS "2/29-2/01",
    MONTHS_BETWEEN(TO_DATE('2019-02-28', 'RRRR-MM-DD'), TO_DATE('2019-02-01', 'RRRR-MM-DD')) AS "2/28-2/01",
    MONTHS_BETWEEN(TO_DATE('2019-01-31', 'RRRR-MM-DD'), TO_DATE('2019-01-01', 'RRRR-MM-DD')) AS "1/31-1/01"
FROM dual;

-- #4501
SELECT
    name,
    TO_CHAR(SYSDATE, 'RRRR-MM-DD') AS "오늘",
    TO_CHAR(hiredate, 'RRRR-MM-DD') AS "입사일",
    TO_CHAR(SYSDATE, 'RRRR') - TO_CHAR(hiredate, 'RRRR') AS "근속연수",
    MONTHS_BETWEEN(SYSDATE, hiredate) AS "근속개월",
    SYSDATE - hiredate AS "근속일"
FROM t_professor;

--  ADD_MONTHS 함수 : 개월 수 추가
SELECT SYSDATE, ADD_MONTHS(SYSDATE, 3) "3개월 뒤" FROM dual;

-- LAST_DAY() : 해당 월의 마지막 날
-- NEXT_DAY() : 돌아오는 가장 최근 요일의 날짜
SELECT
    SYSDATE "오늘",
    LAST_DAY(SYSDATE) AS "이번달 마지막날", 
    NEXT_DAY(SYSDATE, 'fri') AS "돌아오는 금요일"
FROM dual;

--------------------------------------------------
-- 숫자 관련 단일행 함수들

-- ROUND() : 반올림 함수
SELECT
    'ROUND', 
    ROUND(12.34) AS "(12.34)",    -- 소숫점 1자리에서 반올림 (기본동작)
    ROUND(12.536) AS "(12.536)",
    ROUND(12.536, 2) AS "(12.536, 2)",    -- 소숫점 3의 자리에서 반올림
    ROUND(16.345, -1) AS "(16.345, -1)"    -- 1의 자리에서 반올림
FROM dual;
-- #4501 완성이 가능해졌다
SELECT
    name,
    TO_CHAR(SYSDATE, 'RRRR-MM-DD') AS "오늘",
    TO_CHAR(hiredate, 'RRRR-MM-DD') AS "입사일",
    TO_CHAR(SYSDATE, 'RRRR') - TO_CHAR(hiredate, 'RRRR') AS "근속연수",
    ROUND(MONTHS_BETWEEN(SYSDATE, hiredate), 1) AS "근속개월",
    ROUND(SYSDATE - hiredate, 1) AS "근속일"
FROM t_professor;

-- TRUNC()
SELECT 
    'TRUNC', 
    TRUNC(12.345) AS "12.345",     -- 소숫점 자름 (기본동작)
    TRUNC(12.345, 2) AS "(12.345, 2)",     -- 소숫점 3자리부터 자름
    TRUNC(12.345, -1) AS "(12.345, -1)"     -- 1의 자리부터 자름
FROM dual;

-- CEIL() : ~보다 큰 숫자 중에 가장 작은 정수
SELECT
    'CEIL', 
    CEIL(4.5) AS "(4.5)",   -- 4.5 보다 큰 숫자 중에 가장 작은 정수
    CEIL(-3.2) AS "(-3.2)"
FROM dual;

-- FLOOR() : ~보다 작은 숫자 중에 가장 큰 정수
SELECT
    'FLOOR',
    FLOOR(4.5) AS "(4.5)",
    FLOOR(-3.2) AS "(-3.2)"
FROM dual;

-- 위 4개 함수 비교
SELECT
    '12.5', 
    ROUND(12.5) AS "ROUND",
    TRUNC(12.5) AS "TRUNC",
    CEIL(12.5) AS "CEIL",
    FLOOR(12.5) AS "FLOOR"
FROM dual;

SELECT
    '-12.5', 
    ROUND(-12.5) AS "ROUND",
    TRUNC(-12.5) AS "TRUNC",
    CEIL(-12.5) AS "CEIL",
    FLOOR(-12.5) AS "FLOOR"
FROM dual;

-- MOD() : 나머지 연산
-- 오라클에는 % 연산자 없음!!
SELECT
    MOD(12, 10) AS "(12, 10)",
    MOD(42, 13) AS "(42, 13)",
    MOD(12.6, 4.1) AS "(12.6, 4.1)"    -- 4.1*3 + 0.3 = 12.6
FROM dual;

-- POWER() : 제곱
SELECT
    POWER(3, 2) AS "3의 2제곱",
    POWER(-3, 3) AS "-3의 3제곱",
    POWER(2, 1/2) AS "루트2", 
    POWER(27, 1/3) AS "27의 3제곱근"
FROM dual;







--------------------------------------------------

-- 날짜 ROUND() 함수 : 하루의 반은 정오 12:00:00 .. 이를 넘어서면 다음 날짜로 반올림
-- 날짜 TRUNC() 함수 : 무조건 당일 출력
-- 원서접수, 상품주문 마감.....
SELECT 
    SYSDATE AS "오늘",
    ROUND(SYSDATE) AS "ROUND(SYSDATE)",
    TRUNC(SYSDATE) AS "TRUNC(SYSDATE)"
FROM dual;