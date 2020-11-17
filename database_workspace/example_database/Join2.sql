-- 비등가 JOIN (Non-Equi Join)
SELECT * FROM t_customer;
SELECT * FROM t_gift;

-- # 6201
-- ORACLE JOIN
SELECT c.c_name "고객명", c.c_point "POINT", g.g_name "상품명"
FROM t_customer c, t_gift g
WHERE c.c_point BETWEEN g.g_start AND g.g_end
;

-- ANSI JOIN
SELECT c.c_name "고객명", c.c_point "POINT", g.g_name "상품명"
FROM t_customer c JOIN t_gift g
    ON c.c_point BETWEEN g.g_start AND g.g_end
;


-- #6202
-- ORACLE JOIN
SELECT g.g_name "상품명", COUNT(*) "필요수량"
FROM t_customer c, t_gift g
WHERE c.c_point BETWEEN g.g_start AND g.g_end
GROUP BY g.g_name
;

-- ANSI JOIN
SELECT g.g_name "상품명", COUNT(*) "필요수량"
FROM t_customer c JOIN t_gift g
    ON c.c_point BETWEEN g.g_start AND g.g_end
GROUP BY g.g_name
;

-- #6203
SELECT * FROM t_student;
SELECT * FROM t_exam01;
SELECT * FROM t_credit;

-- ORACLE JOIN
SELECT  s.name "학생이름", e.total "점수", c.grade "학점"
FROM t_student s, t_exam01 e, t_credit c
WHERE s.studno = e.studno AND (e.total BETWEEN c.min_point AND c.max_point)
;

-- ANSI JOIN
SELECT  s.name "학생이름", e.total "점수", c.grade "학점"
FROM t_exam01 e
    JOIN t_student s ON s.studno = e.studno
    JOIN t_credit c ON e.total BETWEEN c.min_point AND c.max_point
;
SELECT  s.name "학생이름", e.total "점수", c.grade "학점"
FROM t_student s
    JOIN t_exam01 e ON s.studno = e.studno
    JOIN t_credit c ON e.total BETWEEN c.min_point AND c.max_point
;

-- #6204
-- ORACLE JOIN
SELECT c.c_name "고객명", c.c_point "POINT", g.g_name "상품명"
FROM t_customer c, t_gift g
WHERE c.c_point >= g.g_start AND g.g_no = 5
-- WHERE c.c_point >= g.g_start AND g.g_name = '산약용자전거'
;

-- ANSI JOIN
SELECT c.c_name "고객명", c.c_point "POINT", g.g_name "상품명"
FROM t_customer c
    JOIN t_gift g 
        ON c.c_point >= g.g_start AND g.g_no = 5
;

-- #6205
-- ORACLE VERSION
SELECT e.name "이름", (TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(e.birthday, 'YYYY')) + 1 "현재나이", NVL(e.post, ' ') "현재직급", p.post "예상직급"
FROM t_emp2 e, t_post p
WHERE (TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(e.birthday, 'YYYY')) + 1 BETWEEN p.s_age AND p.e_age
;

-- ANSI VERSION
SELECT e.name "이름", (TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(e.birthday, 'YYYY')) + 1 "현재나이", NVL(e.post, ' ') "현재직급", p.post "예상직급"
FROM t_emp2 e JOIN t_post p
    ON (TO_CHAR(SYSDATE, 'YYYY') - TO_CHAR(e.birthday, 'YYYY')) + 1 BETWEEN p.s_age AND p.e_age
;


---------------------------------------------
-- OUTER JOIN (ORACLE 구문은 INNER JOIN에서만 존재한다.)
-- #6206 - LEFT OUTER JOIN
SELECT s.name "학생이름", p.name "교수이름"
FROM 
    t_student s LEFT OUTER JOIN t_professor p
    ON s.profno = p.profno
;

-- #6207 - RIGHT OUTER JOIN
SELECT s.name "학생이름", p.name "교수이름"
FROM 
    t_student s RIGHT OUTER JOIN t_professor p
    ON s.profno = p.profno
;

-- #6208 - FULL OUTER JOIN
SELECT s.name "학생이름", p.name "교수이름"
FROM 
    t_student s FULL OUTER JOIN t_professor p
    ON s.profno = p.profno
;

--------------------------------------------------
-- SELF JOIN 

-- #6209
-- ORACLE JOIN
SELECT d1.dname "부서명", d2.dname "상위부서명"
FROM t_dept2 d1, t_dept2 d2
WHERE d1.pdept = d2.dcode
;

-- ANSI JOIN
SELECT d1.dname "부서명", d2.dname "상위부서명"
FROM t_dept2 d1 JOIN t_dept2 d2
    ON d1.pdept = d2.dcode
;


-- #6210
-- 중간 확인용
SELECT p1.profno "교수번호", p1.name "교수명", TO_CHAR(p1.hiredate, 'YYYY-MM-DD') "입사일", p2.hiredate "빠른사람"
FROM 
    t_professor p1 LEFT OUTER JOIN t_professor p2
    ON p1.hiredate > p2.hiredate
ORDER BY p1.profno ASC
;

-- 완성
SELECT p1.profno "교수번호", p1.name "교수명", TO_CHAR(p1.hiredate, 'YYYY-MM-DD') "입사일", COUNT(p2.hiredate) "빠른사람"
FROM 
    t_professor p1 LEFT OUTER JOIN t_professor p2
    ON p1.hiredate > p2.hiredate
GROUP BY
    p1.profno, p1.name, p1.hiredate     -- GROUP BY 에는 일반적인 요소들(그룹함수로 묶이지 않은 것들)이 모두 들어와야 한다.
ORDER BY "빠른사람" ASC     -- ORDER BY에는 별명을 쓸 수 있다. (가장 마지막에 쿼리구문이 처리를 하므로..)
-- ORDER BY 4     -- 이렇게 쿼리 순서를 넣어서도 가능하다
;
