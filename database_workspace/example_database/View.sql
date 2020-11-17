-- VIEW

CREATE VIEW v_prof
AS
SELECT profno, name, email, hpage 
FROM t_professor;

-- 있으면 덮어쓰고 없으면 만들기 : 언제든 성공한다.
CREATE OR REPLACE VIEW v_prof
AS
SELECT profno, name, email, hpage 
FROM t_professor;

SELECT * FROM v_prof;

SELECT tname FROM tab;

-- VIEW 삭제
DROP VIEW v_prof;

-- VIEW 생성시 컬럼 이름 지정 가능
CREATE OR REPLACE VIEW v_prof (pfno, nm, em, hp)
AS
SELECT profno, name, email, hpage 
FROM t_professor;

SELECT * FROM v_prof;

-- 특정 사용자가 소유한 VIEW 목록 확인용
SELECT owner AS schema_name, view_name
FROM sys.ALL_VIEWS
WHERE owner = 'SIMIN96'
ORDER BY owner, view_name;

-- #8101) t_professor, t_department테이블을 join하여 교수번호와 교수이름과 소속학과 이름을 조회하는 view를 생성하세요 (이름: v_prof_dept)
SELECT p.profno "교수번호", p.name "교수명", d.dname "소속학과명" 
FROM t_professor p, t_department d
WHERE p.deptno = d.deptno;

CREATE OR REPLACE VIEW v_prof_dept
AS
SELECT p.profno "교수번호", p.name "교수명", d.dname "소속학과명" 
FROM t_professor p, t_department d
WHERE p.deptno = d.deptno
;

SELECT * FROM v_prof_dept;


-- INLINE View (인라인 뷰) : 1회용으로만 사용할 경우 FROM절의 SubQuery형태로 만들 수 있다.(원래 View는 한번 만들어놓으면 계속 사용할 수 있다.) -> FROM절 내에서 table의 역할을 함

-- #8103) t_student, t_department 테이블 : 학과별로 학생들의 최대키와 최대 몸무게, 학과 이름을 출력

SELECT
    d.dname "학과명",
    s.max_height "최대키",
    s.max_weight "최대몸무게"
FROM
    ( SELECT deptno1, MAX(height) max_height, MAX(weight) max_weight
    FROM t_student GROUP BY deptno1 ) s , t_department d
WHERE
    s.deptno1 = d.deptno
;


-- #8104) t_student, t_department 테이블 : 학과별로 가장 키가 큰 학생들의 이름과 키, 학과이름을 인라인뷰를 사용하여 출력

SELECT
    d.dname "학과명", a.max_height "최대키", s.name "학생이름", s.height "키"
FROM 
    (SELECT deptno1, MAX(height) max_height FROM t_student GROUP BY deptno1) a, t_student s, t_department d
WHERE
    s.deptno1 = a.deptno1 AND s.height = a.max_height
    AND s.deptno1 = d.deptno
;


-- #8105) t_student 테이블 : 학생의 키가 동일 학년의 평균 키보다 큰 학생들의 학년과 이름과 키, 해당 학년의 평균키를 출력 (inline view 사용, 학년 칼럼은 오름 차순으로 정렬)

SELECT * FROM t_student;

SELECT
    s.grade "학년", s.name "이름", s.height "키", a.avg_height "평균키", s.height-a.avg_height "차이"
FROM
    (SELECT grade, AVG(height) avg_height FROM t_student GROUP BY grade) a, t_student s
WHERE
    a.grade = s.grade AND s.height > a.avg_height 
ORDER BY "차이" DESC
;