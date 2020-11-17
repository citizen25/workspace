-- 1. 조건문 IF ~ END IF ..

-- deptno 값이 10 --> '재무팀'
-- deptno      20 --> '연구개발팀'
--             30 --> '영업팀'
SELECT * FROM t_emp;

DECLARE
    v_empno t_emp.empno%TYPE;
    v_ename t_emp.ename%TYPE;
    v_deptno t_emp.deptno%TYPE;
    v_dname VARCHAR(20) := NULL;
BEGIN
    SELECT empno, ename, deptno
    INTO v_empno, v_ename, v_deptno
    FROM t_emp
    WHERE empno = 7900;

    -- IF(조건식 참/거짓) THEN [참일때 수행] END IF;
    IF(v_deptno = 10) THEN
        v_dname := '회계팀';
    END IF;     -- IF문이 끝나면 반드시 END IF 로 마무리

    IF(v_deptno = 20) THEN
        v_dname := '연구개발팀';
    END IF;

    IF(v_deptno = 30) THEN
        v_dname := '영업팀';
    END IF;

    DBMS_OUTPUT.PUT_LINE(v_empno || ' ' || v_ename || ' ' || v_deptno || v_dname);

END;

-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 

DECLARE
    v_empno t_emp.empno%TYPE;
    v_ename t_emp.ename%TYPE;
    v_deptno t_emp.deptno%TYPE;
    v_dname VARCHAR(20) := NULL;
BEGIN
    SELECT empno, ename, deptno
    INTO v_empno, v_ename, v_deptno
    FROM t_emp
    WHERE empno = 7566;

    -- IF(조건식 참/거짓) THEN [참일때 수행] ELSIF [ELSIF는 참이면 바로 조건문을 빠져 나옴] END IF;
    IF(v_deptno = 10) THEN
        v_dname := '회계팀';
    ELSIF(v_deptno = 20) THEN
        v_dname := '연구개발팀';
    ELSIF(v_deptno = 30) THEN
        v_dname := '영업팀';
    END IF;

    DBMS_OUTPUT.PUT_LINE(v_empno || ' ' || v_ename || ' ' || v_deptno || v_dname);

END;


--------------------------------------------------------------------
-- 2. 조건문 'CASE문' 과 'CASE식'

-- 'CASE식'
DECLARE
    v_empno t_emp.empno%TYPE;
    v_ename t_emp.ename%TYPE;
    v_deptno t_emp.deptno%TYPE;
    v_dname VARCHAR(20) := NULL;
BEGIN
    SELECT empno, ename, deptno
    INTO v_empno, v_ename, v_deptno
    FROM t_emp
    WHERE empno = 7566;

    -- 'CASE식'
    -- CASE 다음의 '값'에 따라 'CASE식'의 결과값이 결정됨.
    v_dname := CASE v_deptno
                WHEN 10 THEN '회계팀'
                WHEN 20 THEN '연구개발팀'
                WHEN 30 THEN '영업팀'
                END;

    DBMS_OUTPUT.PUT_LINE(v_empno || ' ' || v_ename || ' ' || v_deptno || v_dname);

END;

-- 'CASE문'
-- t_emp 에서 사용자로부터 사원번호 입력받아서 해당사원의 empno, ename, sal, deptno, 그리고 '인상후 연봉(up_sal)' 출력

-- 부서번호가 10번 부서이면 현재 연봉의 10% 인상
-- 부서번호가 20번 부서이면 현재 연봉의 20% 인상
-- 부서번호가 30번 부서이면 현재 연봉의 30% 인상

DECLARE
    v_empno t_emp.empno%TYPE;
    v_ename t_emp.ename%TYPE;
    v_sal t_emp.sal%Type;
    v_deptno t_emp.deptno%TYPE;
    v_up_sal t_emp.sal%TYPE;    -- 인상분을 담을 변수
BEGIN
    SELECT empno, ename, sal, deptno
    INTO v_empno, v_ename, v_sal, v_deptno
    FROM t_emp
    WHERE empno = &empno;   -- 사용자로부터 사원번호 입력받음

    v_up_sal := CASE
                    WHEN v_deptno = 10 THEN v_sal * 1.1
                    WHEN v_deptno = 20 THEN v_sal * 1.2
                    WHEN v_deptno = 30 THEN v_sal * 1.3
                    ELSE v_sal      -- 위의 어떤 경우도 아닌 경우
                END;

    DBMS_OUTPUT.PUT_LINE(v_empno || ' ' || v_ename || ' ' || v_sal || ' ' || v_deptno || ' ' || v_up_sal);

END;


--------------------------------------------------------------------
-- 3. 반복문
-- WHILE 반복문
DECLARE
    num NUMBER := 0;
BEGIN
    -- WHILE (조건식) LOOP ~~ END LOOP;   조건식이 참(true)인 동안 LOOP 블럭 반복 수행
    WHILE num < 6 LOOP
        DBMS_OUTPUT.PUT_LINE(num);
        num := num + 1;     -- 기존의 num값에 1 증가
    END LOOP;

END;

-- FOR 반복문
BEGIN
    FOR i IN 0..5 LOOP  -- FOR 반복문 내에 사용하는 변수는 DECLARE하지 않아도 사용가능!
        DBMS_OUTPUT.PUT_LINE(i);
    END LOOP;
END;


BEGIN
    -- 구구단 2단 출력
    FOR i IN 1..9 LOOP  -- FOR 반복문 내에 사용하는 변수는 DECLARE하지 않아도 사용가능!
        DBMS_OUTPUT.PUT_LINE('2 x ' || i || ' = ' || i*2);
    END LOOP;
END;


BEGIN
    -- 구구단 2단 출력
    FOR i IN REVERSE 1..9 LOOP  -- FOR 반복문 내에 사용하는 변수는 DECLARE하지 않아도 사용가능!
        DBMS_OUTPUT.PUT_LINE('2 x ' || i || ' = ' || i*2);
    END LOOP;
END;
