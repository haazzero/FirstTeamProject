package memo.vo;

import java.sql.Date;

public class MemberVO {

	//1. private 2. 데이터 타입 3. 게터세터
	
	private String mid;		//ID
	private String mname;	//이름
	private int age;		//나이
	private String phone;	//전화번호
	private Date regDate;	//가입일자
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}

	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
}//class end
