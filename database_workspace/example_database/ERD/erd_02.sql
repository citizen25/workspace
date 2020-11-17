DROP TABLE register CASCADE CONSTRIANTS;
DROP TABLE student CASCADE CONSTRAINTS;
DROP TABLE professor CASCADE CONSTRAINTS;
DROP TABLE subject CASCADE CONSTRIANTS;




CREATE TABLE professor(
    profno number NOT NULL,
    name varchar2(10) NOT NULL,
    deptno number,
    PRIMARY KEY (profno)
);

CREATE TABLE register(
    studno number NOT NULL,
    subjno number NOT NULL
);

CREATE TABLE student(
    studno number NOT NULL,
    name varchar2(10) NOT NULL,
    deptno number,
    profno number NOT NULL,
    PRIMARY KEY (studno)
);

CREATE TABLE subject(
    subjno number NOT NULL,
    name varchar2(10) NOT NULL,
    PRIMARY KEY (subjno)
);


