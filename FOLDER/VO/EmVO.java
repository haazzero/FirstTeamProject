package vo;

import java.util.Date;

public class EmVO {

	private String emid;  		// 직원번호 emid : String
	private String name;		//	직원이름 name : String
	private String position;	//	부서 position : String
	private String dno;			//	부서번호 dno : String
	private Date birth;			//	생년월일 birth : date
	private String tel;			//	전화번호 tel : String
	private String gen;			//	성별 gen : String
	private Date join;			//	입사일자 join : date
	
	
	public String getEmid() {
		return emid;
	}
	public void setEmid(String emid) {
		this.emid = emid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public Date getJoin() {
		return join;
	}
	public void setJoin(Date join) {
		this.join = join;
	}
	
	
	
	
	
}
