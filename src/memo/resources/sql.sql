//t_member 테이블 생성
	CREATE TABLE t_member ( 
    mid VARCHAR2(20) PRIMARY KEY, 
    mname VARCHAR2(30), 
	age NUMBER,
	phone vACHAR2(13)
    reg_date DATE 
	);


//t_memo 테이블 생성
	CREATE TABLE t_memo ( 
    mno NUMBER CONSTRAINT t_memo_pk PRIMARY KEY, 
    memo VARCHAR2(30), 
    mid VARCHAR2(20) REFERENCES t_member(mid) ON DELETE SET NULL, 
    reg_date DATE 
	);

//오류 발생 : sys에서 dev에 권한주기
	GRANT CREATE SEQUENCE TO dev

//create sequence
	CREATE SEQUENCE memo_seq
	INCREMENT BY 1
	START WITH 1
	MAXVALUE 99999
	NOCYCLE
	NOCACHE;
	
//삭제 SQL문
DROP SEQUENCE memo_seq; --시퀀스 삭제
DROP TABLE t_memo; --시퀀스 삭제

//login 메서드
SELECT COUNT(*) FROM t_member WHERE mid = 'aaa'
AND phone = '010-3333-2222';

//memo insert SQL문
INSERT INTO t_memo VALUES(memo_seq.NEXTVAL,'hi','aaa',SYSDATE)
	
//테이블 열 오름차순
ORDER BY mno ASC
query = " SELECT ROWNUM, mno, memo, mid, reg_date FROM t_memo ORDER BY mno ASC ";
query = " SELECT * FROM t_memo ORDER BY mno ASC ";


