package hr.vo;

public class SalaryPaymentVO {

	private String emid; // 직원번호
	private String name; // 직원이름
	private String payDate; // 지급일자
	private int sal; // 기본급
	private int bonus; // 상여금
	private int total; // 총급여
	private int spno; // 급여지급내역번호
//
//	// 총 급여 계산 메서드
//	public int getTotal() {
//		return sal + bonus;
//	}
	
	// 총 급여 계산 메서드
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSpno() {
		return spno;
	}

	public void setSpno(int spno) {
		this.spno = spno;
	}

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

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
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
}
