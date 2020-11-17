-- (1) INITCAP 함수
-- 첫 글자만 대문자로 변환하여 출력
-- 나머지는 전부 소문자로 출력

-- #4101
SELECT INITCAP('we are the champion') FROM DUAL;

-- #4102
SELECT id, INITCAP(id) FROM t_student WHERE deptno1 = 201;

-- #4103
SELECT name as "이름", id, LOWER(id) as "소문자", UPPER(id) as "대문자" FROM t_student WHERE deptno1 = 201;

-- #4104
--  WHERE 조건절에 단일행 함수 사용 가능
SELECT name as "이름", id, LENGTH(id) as "글자수" FROM t_student WHERE LENGTH(id) >= 9;

-- #4105
SELECT name as "이름", LENGTH(name) as "길이", LENGTHB(name) as "바이트" FROM t_student WHERE deptno1 = 201; 

-- #4106
SELECT CONCAT(name, position) as "교수님 명단" FROM t_professor WHERE deptno = 101;

SELECT name || position as "교수님 명단" FROM t_professor WHERE deptno = 101;

-- SUBSTR 사용 예
SELECT SUBSTR('ABCDE', 2, 3) FROM dual;
SELECT SUBSTR('ABCDE', 3, 10) FROM dual;
SELECT SUBSTR('ABCDE', 20, 3) FROM dual;
SELECT SUBSTR('ABCDE', -2, 2) FROM dual;    -- 음수 인덱싱 가능

-- #4107
SELECT name, SUBSTR(jumin, 1, 6) as "생년월일" FROM t_student WHERE deptno1 = 101;

-- #4108
SELECT name, SUBSTR(jumin, 1, 6) as "생년월일" FROM t_student WHERE SUBSTR(jumin, 3, 2) = '08';

SELECT name, SUBSTR(jumin, 1, 6) as "생년월일" FROM t_student WHERE jumin LIKE '__08%';

-- # 4109
SELECT name, jumin FROM t_student WHERE SUBSTR(jumin, 7, 1) = '2' AND grade = 4;

-- SUBSTRB 바이트 단위로 출력
SELECT SUBSTRB(name, 1, 3) FROM t_student WHERE deptno1 = 101;

-- INSTR() 함수
SELECT INSTR('A*B*C*', '*', 1, 1) FROM dual;
SELECT INSTR('A*B*C*', '*', 1, 2) FROM dual;
SELECT INSTR('A*B*C*', '*', 3, 2) FROM dual;
SELECT INSTR('A*B*C*', '*', -4, 1) FROM dual;   -- 음수인덱스는 음의 방향으로 검색 진행
SELECT INSTR('A*B*C*', '*', -4, 2) FROM dual;   -- 없으면 0 리턴
SELECT INSTR('A*B*C*', '*', -2, 2) FROM dual;

-- #4110
SELECT name, tel, INSTR(tel, ')', 1, 1) AS "위치" FROM t_student WHERE deptno1 = 101;

-- #4111
SELECT name, tel, SUBSTR(tel, 1, INSTR(tel, ')', 1, 1)-1) AS "지역번호" FROM t_student WHERE deptno1 = 101;

SELECT name, tel, SUBSTR(tel, 1, INSTR(tel, ')')-1) AS "지역번호" FROM t_student WHERE deptno1 = 101;   -- INSTR의 3, 4 번째 매개변수가 1인 경우에는 생략이 가능하다

-- LPAD /  RPAD 함수
SELECT LPAD('abcd', 10, '#-') AS "LPAD함수", RPAD('abcd', 10, '#-') AS "RPAD함수" FROM dual;

-- #4112
SELECT id, LPAD(id, 10, '$') AS "LPAD예제" FROM t_student;

-- #4115
SELECT dname, RPAD(dname, 10, '1234567890') AS "RPAD연습" FROM t_dept2;

-- LTRIM, RTRIM 함수
SELECT LTRIM('슈퍼슈퍼슈가맨', '슈퍼') AS "LTRIM", LTRIM('     좌측 공백들 제거', ' ') AS "LTRIM2", RTRIM('우측 공백들 제거     ') AS "RTRIM" FROM dual;

-- REPLACE 함수
SELECT REPLACE('슈퍼맨 슈퍼걸', '슈퍼', '파워') AS "REPLACE 예제" FROM dual;

-- #4118
SELECT name, SUBSTR(name, 1, 1) AS "성", REPLACE(name, SUBSTR(name, 1, 1), '#') AS "학생" FROM t_student WHERE deptno1 = 102;   -- 완벽하지 않다. ==> 김수김 -> #수#

-- #4119
SELECT REPLACE(name, SUBSTR(name, 2, 1), '#') 학생 FROM t_student WHERE deptno1 = 101;

-- #4120
SELECT name, REPLACE(jumin, SUBSTR(jumin, 7, 7), '*******') AS "주민번호" FROM t_student WHERE deptno1 = 101;

-- #4121
SELECT name, tel, REPLACE(tel, SUBSTR(tel, INSTR(tel, ')')+1, 3), '###') FROM t_student WHERE deptno1 = 102;

