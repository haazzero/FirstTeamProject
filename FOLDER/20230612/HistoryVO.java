package hr.vo;

public class HistoryVO {
	private String emid;		//	직원번호
	private String name;		//	직원이름
	private String dno;			//	부서번호
	private String dname;		//  부서이름
	private String position;	//	직급	
	private String leaveStart; 	//	시작날짜
	private String leaveFin;	//	종료날짜
	private String remark;		//	구분
	private int hno;			//  히스토리 번호
	
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
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getLeaveStart() {
		return leaveStart;
	}
	public void setLeaveStart(String leaveStart) {
		this.leaveStart = leaveStart;
	}
	public String getLeaveFin() {
		return leaveFin;
	}
	public void setLeaveFin(String leaveFin) {
		this.leaveFin = leaveFin;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getHno() {
		return hno;
	}
	public void setHno(int hno) {
		this.hno = hno;
	}
	
}
