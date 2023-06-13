package hr.vo;

public class YearVO {
	private String emid;		//	직원번호
	private String name;		//	직원이름
	private String yearDate;	//	연차사용일자
	private String yearCnt;		//	연차사용일수
	private String app;			//	연차승인자
	private String appDate;		//	연차승인일자
	private int yno;			//  연차승인내역번호
	
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
	public String getYearDate() {
		return yearDate;
	}
	public void setYearDate(String yearDate) {
		this.yearDate = yearDate;
	}
	public String getYearCnt() {
		return yearCnt;
	}
	public void setYearCnt(String yearCnt) {
		this.yearCnt = yearCnt;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	public int getYno() {
		return yno;
	}
	public void setYno(int yno) {
		this.yno = yno;
	}
	
}