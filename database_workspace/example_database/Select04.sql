SELECT studno, name, deptno1
FROM t_student
WHERE deptno1 = 101
;

SELECT profno, name, deptno
FROM t_professor
WHERE deptno = 101
;

-- 집합 연산자

-- #3201
-- UNION으로 합치기
SELECT studno, name, deptno1
FROM t_student
WHERE deptno1 = 101
UNION -- 합집합(중복값 제거, 정렬 수행)
SELECT profno, name, deptno
FROM t_professor
WHERE deptno = 101
;

-- UNION ALL로 합치기
SELECT studno, name, deptno1
FROM t_student
WHERE deptno1 = 101
UNION ALL -- 합집합(중복값 제거 X, 정렬 X)
SELECT profno, name, deptno
FROM t_professor
WHERE deptno = 101
;
-- 위 결과는 정렬없이 각각의 쿼리 결과를 그대로 합하여 출력


-- #3202) UNION을 사용하여 t_student에서 제 1전공(deptno1)이 101번 학과인 학생과 제 2전공(deptno2)이 201번 학과를 전공하는 학생들의 이름을 모두 출력 (결과 확인 후 UNION ALL과도 비교)

SELECT name FROM t_student WHERE deptno1=101
UNION
SELECT name FROM t_student WHERE deptno2=201
; -- UNION 중복 제거

SELECT name FROM t_student WHERE deptno1=101
UNION ALL -- 중복 나옴 : 서진수 2번 등장
SELECT name FROM t_student WHERE deptno2=201
; -- UNION 중복 제거 X


-- #3202) INTERSECT 를 사용 : t_student에서 제 1전공(deptno1)이 101번, 그리고 제 2전공(deptno2)이 201번을 복수 전공하는 학생들의 이름을 모두 출력

SELECT name FROM t_student WHERE deptno1=101
INTERSECT -- 교집합 : 서진수는 제 1전공과 제 2전공 모두에 포함되어 있음
SELECT name FROM t_student WHERE deptno2=201
;


-- #3203) MINUS 사용 : t_professor 교수님들의 급여를 20% 인상하기 위한 명당(이름, 직급)을 출력. 단, 직급이 전임강사인 사람은 제외.
-- 전체명단 - 전임강사

SELECT name, position FROM t_professor
MINUS -- 차집합 (A-B)
SELECT name, position FROM t_professor WHERE position='전임강사'
;

