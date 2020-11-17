-- t_student 테이블에서 4학년 학생중에 키가 170cm 이상인 사람의 이름과 학년과 키를 조회하세요
SELECT name, grade, height FROM  t_student WHERE grade = 4 AND height >= 170;

-- t_student 테이블에서 1학년이거나 또는 몸무게가 80kg 이상인 학생들의 이름과 키와 학년과 몸무게를 출력하세요
SELECT name, height, weight FROM t_student WHERE grade = 1 OR weight >= 80;

-- t_student 테이블을 사용해서 2학년 중에서 키가 180cm 보다 크면서 몸무게가 70kg 보다 큰 학생들의 이름과 학년과 키와 몸무게를 출력하세요
SELECT name, grade, height, weight FROM t_student WHERE grade = 2 AND height > 180 AND weight > 70;

-- t_student 테이블: 2학년 학생중에서 키가 180cm보다 크거나 또는 몸무게가 70kg 보다 큰 학생들의 이름과 학년과 키와 몸무게를 출력하세요
SELECT name, grade, height, weight FROM t_student WHERE height > 180 OR weight >70;

-- t_emp 테이블에서 고용일(hiredate)가 1992년 이전인 사람들의 이름(ename) 과 고용일을 출력하세요
SELECT ename, hiredate FROM t_emp WHERE hiredate < TO_DATE('1992-01-01', 'YYYY-MM-DD');


-- t_student 테이블: 1학년 학생의 이름과 생일과 키와 몸무게를 출력하세요, 단 생일이 빠른 사람 순서대로 정렬하세요.
SELECT name, birthday, height, weight FROM t_student ORDER BY birthday ASC;

-- t_student 테이블: 1학년 학생의 이름과 키를 출력하세요, 별명은 ‘이름’, ‘키’ 로 출력.  단 이름은 오름차순으로 정렬하세요
SELECT name AS "이름", height AS "키" FROM t_student ORDER BY name ASC;

-- t_emp2 직원 테이블에서 생일(birthday) 이 1980년대생인 사람들의 이름과 생일만 출력하세요- 즉 (1980/01/01 ~ 1989/12/31 사이 출생한 직원들)
SELECT name, birthday FROM t_emp2 WHERE TO_DATE('1980-01-01', 'YYYY-MM-DD') <= birthday AND birthday < TO_DATE('1990-01-01', 'YYYY-MM-DD');

