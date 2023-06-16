package vo;

public class SalVO {

	private String emid;		//	직원번호 emid : String
	private String name;		//	직원이름 name : String
	private int sal;			//	기본급 sal : int
	private String bank;		//	은행명 bank : String
	private String depositor;	//	예금주 depositor : String
	private String account;		//	계좌번호 account : String
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
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getDepositor() {
		return depositor;
	}
	public void setDepositor(String depositor) {
		this.depositor = depositor;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
}
