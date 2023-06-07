package memo.vo;

import java.util.Date;

// **회원**일 시 한 줄 남길 수 있는 **메모게시판**만들기
public class MemoVO {

	//1. private 2. 데이터 타입 3. 게터세터
	
	private int mno;		//번호
	private String memo;	//메모
	private String mid;		//작성자 - 공유변수 사용
	private Date regDate;	//가입일자
	private int rownum;
	
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}//class end

