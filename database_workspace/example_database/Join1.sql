SELECT name, profno FROM t_student;     -- 20명
SELECT profno, name FROM t_professor;     -- 16명

-- 카티션 곱 (Cartesian Product)
-- 컬럼명 앞에 테이블명을 명시적으로 붙여서 SELECT
SELECT t_student.name 학생이름, t_professor.name 교수이름
FROM t_student, t_professor;   -- 320개
-- ? Dbeaver에서는 잘 되는데 VSC에서는 잘 안 된다;;
-- column의 이름이 같은게 있어서 그런거였음. 각각의 이름을 달리 해주면 해결된다.

-- 테이블에 별명 사용 가능. 별명을 사용하여 칼럼 SELECT하기
SELECT s.name, p.name
FROM t_student s, t_professor p;

-- 학생의 이름과 그 학생의 담당교수 이름.
SELECT s.name 학생이름, s.profno 학생profno, p.profno 교수profno, p.name 교수이름
FROM t_student s, t_professor p
WHERE s.profno = p.profno
;

SELECT s.name 학생이름, p.name 교수이름
FROM t_student s, t_professor p     -- JOIN에 참여하는 테이블들 FROM절에 기술
WHERE s.profno = p.profno      -- 카티션곱에서 추출해낼(걸러낼) 조건기술(JOIN조건)
;

-- #6101
-- ORACLE JOIN
SELECT s.name 학생이름, s.deptno1 학과번호, d.dname 학과이름
FROM t_student s, t_department d
WHERE s.deptno1 = d.deptno
;

-- ANSI JOIN (표준 JOIN문)
-- JOIN 키워드 사용, JOIN 조건이 곧바로 뒤에 붙음. ON ~
SELECT s.name 학생이름, s.deptno1 학과번호, d.dname 학과이름
FROM 
    t_student s JOIN t_department d ON s.deptno1 = d.deptno
;


-- #6102
-- ORACLE JOIN
SELECT s.name 학생이름, s.profno 지도교수, p.name 지도교수이름
FROM t_student s, t_professor p
WHERE s.profno = p.profno    -- join 조건
;

-- ANSI JOIN
SELECT s.name 학생이름, s.profno 지도교수, p.name 지도교수이름
FROM t_student s 
    JOIN t_professor p ON s.profno = p.profno    -- join 조건
;


-- #6103
-- ORACLE JOIN
SELECT s.name AS "학생이름", d.dname AS "학과이름", p.name AS "교수이름"
FROM t_student s, t_department d, t_professor p
WHERE s.deptno1 = d.deptno AND s.profno = p.profno
;

-- ANSI JOIN
SELECT s.name AS "학생이름", d.dname AS "학과이름", p.name AS "교수이름"
FROM
    t_student s JOIN t_department d ON s.deptno1 = d.deptno
                JOIN t_professor p ON s.profno = p.profno
;


-- #6104
SELECT * FROM t_emp2;
SELECT * FROM t_post;

-- ORACLE JOIN
SELECT e.name AS "사원이름", e.post AS "현재", e.pay AS "현재연봉", p.s_pay AS "하한금액", p.e_pay AS "상한금액"
FROM t_emp2 e, t_post p
WHERE e.post = p.post
;

-- ANSI JOIN
SELECT e.name AS "사원이름", e.post AS "현재", e.pay AS "현재연봉", p.s_pay AS "하한금액", p.e_pay AS "상한금액"
FROM
    t_emp2 e JOIN t_post p ON e.post = p.post
;


-- #6105
-- ORACLE JOIN
SELECT s.name "학생이름", p.name "교수이름"
FROM t_student s, t_professor p
WHERE s.profno = p.profno   -- join 조건
    AND s.deptno1 = 101;    -- 검색 조건
    -- 단! 위의 JOIN 조건보다 검색조건을 먼저 수행한다.

-- ANSI JOIN
SELECT s.name "학생이름", p.name "교수이름"
FROM t_student s JOIN t_professor p
    ON s.profno = p.profno
WHERE s.deptno1 = 101;

SELECT s.name "학생이름", p.name "교수이름"
FROM t_student s JOIN t_professor p
    ON s.profno = p.profno AND s.deptno1 = 101;
-- 이 방식(AND)은 카티션 곱을 먼저 하고 검색하기 때문에 수행시간이 더 오래 걸린다. 따라서 위의 WHERE 방식으로 하는게 더 좋다.

