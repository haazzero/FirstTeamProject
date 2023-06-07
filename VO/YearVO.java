package vo;

import java.util.Date;

public class YearVO {
	private String emid;		//	직원번호 emid : String
	private String name;		//	직원이름 name : String
	private Date yearDate;		//	연차사용날짜 yearDate : date
	private int yaerCnt;		//	연차사용일수 yearCnt : int
	private String app;			//	연차승인자 app : String
	private Date appDate;		//	연차승인일자 appDate : date
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
	public Date getYearDate() {
		return yearDate;
	}
	public void setYearDate(Date yearDate) {
		this.yearDate = yearDate;
	}
	public int getYaerCnt() {
		return yaerCnt;
	}
	public void setYaerCnt(int yaerCnt) {
		this.yaerCnt = yaerCnt;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public Date getAppDate() {
		return appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	
	
}
