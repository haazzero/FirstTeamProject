/* Create Sequences */

CREATE SEQUENCE SEQ_JOB_HISTORY_HIS_NO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_POSITION_PNO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_REVIEW_REVIEW_NO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_SALARY_PAYMENT_SALPAY_NO INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE SEQ_YEARS_YEARS_NO INCREMENT BY 1 START WITH 1;

/* Create Tables */

-- 부서
CREATE TABLE DEPARTMENTS
(
	-- 부서번호
	DNO varchar2(10) NOT NULL,
	-- 부서이름
	DNAME varchar2(30) NOT NULL UNIQUE,
	PRIMARY KEY (DNO)
);


-- 직원 정보
CREATE TABLE EMPLOYEES
(
	-- 직원번호
	EMID varchar2(10) NOT NULL,
	-- 직급이름
	POSITION varchar2(10) NOT NULL UNIQUE,
	-- 부서이름
	DNAME varchar2(30) NOT NULL UNIQUE,
	-- 직원이름
	NAME varchar2(30) NOT NULL,
	-- 생년월일
	BIRTH date NOT NULL,
	-- 전화번호
	TEL varchar2(30) NOT NULL,
	-- 성별
	GEN varchar2(10) NOT NULL,
	-- 입사일자
	JOIN date NOT NULL,
	PRIMARY KEY (EMID)
);


-- 로그인 계정
CREATE TABLE HR_SYSTEM
(
	-- 아이디
	ID varchar2(30) NOT NULL,
	-- 비밀번호
	PW varchar2(30) NOT NULL,
	PRIMARY KEY (ID)
);


-- 히스토리
CREATE TABLE JOB_HISTORY
(
	-- 히스토리 번호
	HIS_NO number NOT NULL,
	-- 부서이름
	DNAME varchar2(30) NOT NULL UNIQUE,
	-- 직급이름
	POSITION varchar2(10) NOT NULL UNIQUE,
	-- 직원번호
	EMID varchar2(10) NOT NULL,
	-- 시작일자
	LEAVESTART date NOT NULL,
	-- 종료일자
	LEAVEFIN date NOT NULL,
	-- 구분
	REMARK varchar2(100) NOT NULL,
	PRIMARY KEY (HIS_NO)
);


-- 직급
CREATE TABLE POSITION
(
	-- 직급번호
	PNO number NOT NULL,
	-- 직급이름
	POSITION varchar2(10) NOT NULL UNIQUE,
	PRIMARY KEY (PNO)
);


-- 인사고과 기록
CREATE TABLE REVIEW
(
	-- 인사고과번호
	REVIEW_NO number NOT NULL,
	-- 직원번호
	EMID varchar2(10) NOT NULL,
	-- 평가항목1
	EVAL1 number NOT NULL,
	-- 평가항목2
	EVAL2 number NOT NULL,
	-- 평가항목3
	EVAL3 number NOT NULL,
	-- 평가항목4
	EVAL4 number NOT NULL,
	-- 평가항목5
	EVAL5 number NOT NULL,
	-- 평가 총점수
	EVAL_TOT number NOT NULL,
	-- 평가 등급
	GRADE varchar2(10) NOT NULL,
	-- 비고
	REMARK varchar2(100),
	-- 평가일자
	EVAL_DATE date NOT NULL,
	PRIMARY KEY (REVIEW_NO)
);


-- 급여 정보
CREATE TABLE SALARY_INFO
(
	-- 직원번호
	EMID varchar2(10) NOT NULL,
	-- 은행명
	BANK varchar2(30) NOT NULL,
	-- 예금주
	DEPOSITOR varchar2(30) NOT NULL,
	-- 계좌번호
	ACCOUNT varchar2(30) NOT NULL,
	PRIMARY KEY (EMID)
);


-- 급여 지급 내역
CREATE TABLE SALARY_PAYMENT
(
	-- 급여지급번호
	SALPAY_NO number NOT NULL,
	-- 직원번호
	EMID varchar2(10) NOT NULL,
	-- 급여지급일자
	PAY_DATE date NOT NULL,
	-- 상여금
	BONUS number NOT NULL,
	-- 총급여
	TOTAL number NOT NULL,
	PRIMARY KEY (SALPAY_NO)
);


-- 호봉
CREATE TABLE SALARY_STEP
(
	-- 호봉
	SAL_GRADE number NOT NULL,
	-- 직급이름
	POSITION varchar2(10) NOT NULL UNIQUE,
	-- 기본급
	SAL number NOT NULL,
	PRIMARY KEY (SAL_GRADE)
);


-- 근태 기록
CREATE TABLE WORK
(
	-- 직원번호
	EMID varchar2(10) NOT NULL,
	-- 근속년수
	CONT number NOT NULL,
	-- 지각일수
	LATE number NOT NULL,
	-- 조퇴일수
	EARLY number NOT NULL,
	-- 결근일수
	ABS number NOT NULL,
	-- 사용가능연차일수
	AV_ANN number NOT NULL,
	-- 사용한연차일수
	USED_ANN number NOT NULL,
	-- 잔여연차일수
	UNUSED_ANN number NOT NULL,
	PRIMARY KEY (EMID)
);


-- 연차 승인 내역
CREATE TABLE YEARS
(
	-- 연차승인번호
	YEARS_NO number NOT NULL,
	-- 직원번호
	EMID varchar2(10) NOT NULL,
	-- 연차사용일자
	YEARS_DATE date NOT NULL,
	-- 연차사용일수
	YEARS_CNT number NOT NULL,
	-- 연차승인자
	APP varchar2(30) NOT NULL,
	-- 연차승인일자
	APP_DATE date NOT NULL,
	PRIMARY KEY (YEARS_NO)
);



/* Create Foreign Keys */

ALTER TABLE EMPLOYEES
	ADD FOREIGN KEY (DNAME)
	REFERENCES DEPARTMENTS (DNAME)
;


ALTER TABLE JOB_HISTORY
	ADD FOREIGN KEY (DNAME)
	REFERENCES DEPARTMENTS (DNAME)
;


ALTER TABLE JOB_HISTORY
	ADD FOREIGN KEY (EMID)
	REFERENCES EMPLOYEES (EMID)
;


ALTER TABLE REVIEW
	ADD FOREIGN KEY (EMID)
	REFERENCES EMPLOYEES (EMID)
;


ALTER TABLE SALARY_INFO
	ADD FOREIGN KEY (EMID)
	REFERENCES EMPLOYEES (EMID)
;


ALTER TABLE SALARY_PAYMENT
	ADD FOREIGN KEY (EMID)
	REFERENCES EMPLOYEES (EMID)
;


ALTER TABLE WORK
	ADD FOREIGN KEY (EMID)
	REFERENCES EMPLOYEES (EMID)
;


ALTER TABLE YEARS
	ADD FOREIGN KEY (EMID)
	REFERENCES EMPLOYEES (EMID)
;


ALTER TABLE EMPLOYEES
	ADD FOREIGN KEY (POSITION)
	REFERENCES POSITION (POSITION)
;


ALTER TABLE JOB_HISTORY
	ADD FOREIGN KEY (POSITION)
	REFERENCES POSITION (POSITION)
;


ALTER TABLE SALARY_STEP
	ADD FOREIGN KEY (POSITION)
	REFERENCES POSITION (POSITION)
;



/* Create Triggers */

CREATE OR REPLACE TRIGGER TRI_JOB_HISTORY_HIS_NO BEFORE INSERT ON JOB_HISTORY
FOR EACH ROW
BEGIN
	SELECT SEQ_JOB_HISTORY_HIS_NO.nextval
	INTO :new.HIS_NO
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_POSITION_PNO BEFORE INSERT ON POSITION
FOR EACH ROW
BEGIN
	SELECT SEQ_POSITION_PNO.nextval
	INTO :new.PNO
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_REVIEW_REVIEW_NO BEFORE INSERT ON REVIEW
FOR EACH ROW
BEGIN
	SELECT SEQ_REVIEW_REVIEW_NO.nextval
	INTO :new.REVIEW_NO
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_SALARY_PAYMENT_SALPAY_NO BEFORE INSERT ON SALARY_PAYMENT
FOR EACH ROW
BEGIN
	SELECT SEQ_SALARY_PAYMENT_SALPAY_NO.nextval
	INTO :new.SALPAY_NO
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_YEARS_YEARS_NO BEFORE INSERT ON YEARS
FOR EACH ROW
BEGIN
	SELECT SEQ_YEARS_YEARS_NO.nextval
	INTO :new.YEARS_NO
	FROM dual;
END;

/



