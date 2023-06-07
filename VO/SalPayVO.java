package vo;

import java.util.Date;

public class SalPayVO {
	
	private String emid;	 	//	직원번호 emid : String
	private String name;		//	직원이름 name : String
	private Date patDate;		//	지급일시 payDate : date
	private int sal;			//	기본급 sal : int
	private int bonus;			//	상여금 bonus : int
	private int total;			//	총급여 total : int
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
	public Date getPatDate() {
		return patDate;
	}
	public void setPatDate(Date patDate) {
		this.patDate = patDate;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	
}
