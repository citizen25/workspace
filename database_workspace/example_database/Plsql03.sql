/*
지금까지의 PL/SQL 블록은 '익명블록' 이라 함
    --> 저장 안됨.  다시 재사용도 불가.

자주 사용되는 PL/SQL 블록은 '이름'을 지정하여 생성/저장 해주었다가
필요할 때 마다 사용 (호출, 실행)

이를 '서브프로그램(sub program)'이라 하는데
오라클에는 아래와 같은 서브 프로그램들이 있다.
    - Procedure
    - Function
    - Package
    - Trigger
*/

1. PROCEDURE (프로시져)

-- 부서번호가 20인 사람들의 job을 'CLERK'로 변경하는 프로시져
SELECT * FROM t_emp;

-- 프로시져 생성(프로시져 실행이 아님!)
CREATE OR REPLACE PROCEDURE update_20
IS
BEGIN
    UPDATE t_emp SET job='CLERK' WHERE deptno = 20;
END;

DROP PROCEDURE update_20;

-- 파라미터 parameter
--   다음 3가지 모드에 따라 역할이 다르게 수행
--     1. IN    : (디폴트) 서브프로그램에 전달됨. 형식 파라미터가 상수로 동작
--     2. OUT   : 호출한 쪽으로 값이 리턴됨. 초기화 되지 않은 변수
--     3. INOUT : 서브프로그램으로 전달 + 호풀한 쪽으로 값이 리턴. 초기화된 변수

-- 사용자로부터 값을 입력받아 급여를 인상하는 프로시져
SELECT empno, ename, sal FROM t_emp WHERE empno = 7902;

CREATE OR REPLACE PROCEDURE up_sal
(v_empno IN  t_emp.empno%TYPE)  -- 입력값을 저장할 변수 v_empno 선언, IN 모드 파라미터
IS
BEGIN
    UPDATE t_emp SET sal = 5000
    WHERE empno = v_empno;
END;

-- OUT 모드 파라이터 사용 예
CREATE OR REPLACE PROCEDURE info_prof(
    v_profno IN t_professor.profno%TYPE,
    v_name OUT t_professor.name%TYPE,   -- 이름값이 저장될 변수 
    v_pay OUT t_professor.pay%TYPE      -- 급여를 저장할 변수
)
IS
BEGIN
    SELECT name, pay INTO v_name, v_pay
    FROM t_professor
    WHERE profno = v_profno;
END info_prof;


DECLARE
    v_name t_professor.name%TYPE;
    V_PAY t_professor.pay%TYPE;
BEGIN
    info_prof(1001, v_name, v_pay);
    DBMS_OUTPUT.PUT_LINE(v_name || ' 교수의 급여는 ' || v_pay || ' 입니다.');
END;

/*
다음과 같이 별도로 변수 선언해서 값을 수행해서 받은 후 출력도 가능 (sqlplus에서 한줄씩 실행)

VARIABLE name VARCHAR2(10)
VARIABLE pay NUMBER
EXEC info_prof(1001, :name, :pay);
PRINT name pay
*/


DECLARE
    v_name t_professor.name%TYPE;
    V_PAY t_professor.pay%TYPE;
BEGIN
    info_prof(      -- 아래와 같이 매개변수 이름을 명시하면 궅이 순서대로 호출부에 명시 안해도 됨.
        v_name => v_name, 
        v_profno => 1001,
        v_pay => v_pay
    );
    DBMS_OUTPUT.PUT_LINE(v_name || ' 교수의 급여는 ' || v_pay || ' 입니다.');
END;



