package vo;

import java.util.Date;

public class HisVO {
	private String emid;		//	직원번호 emid : String
	private String name;		//	직원이름 name : String
	private String depart;		//	현 부서 depart : String
	private Date departStart;	//	부서등록날짜 departStart : date
	private Date departMove;	//	부서이동날짜 departMove : date
	private String position;	//	현 직급 position : String
	private Date revom;			//	승진 날짜 revom : date
	private Date leaveStart; 	//	휴직시작날짜 leaveStart : date
	private Date leaveFin;		//	휴직종료날짜 leaveFin : date
	private String remark;		//	비고 reamark : String
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
	public String getDepart() {
		return depart;
	}
	public void setDepart(String depart) {
		this.depart = depart;
	}
	public Date getDepartStart() {
		return departStart;
	}
	public void setDepartStart(Date departStart) {
		this.departStart = departStart;
	}
	public Date getDepartMove() {
		return departMove;
	}
	public void setDepartMove(Date departMove) {
		this.departMove = departMove;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getRevom() {
		return revom;
	}
	public void setRevom(Date revom) {
		this.revom = revom;
	}
	public Date getLeaveStart() {
		return leaveStart;
	}
	public void setLeaveStart(Date leaveStart) {
		this.leaveStart = leaveStart;
	}
	public Date getLeaveFin() {
		return leaveFin;
	}
	public void setLeaveFin(Date leaveFin) {
		this.leaveFin = leaveFin;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
