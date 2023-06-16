package vo;

public class DnlVO {
	private String emid; 		//	직원번호 emid : String
	private String name;		//	직원이름 name : String
	private int cont;			//	근속년수 cont : int
	private int late;			//	지각일수 late : int
	private int early;			//	조퇴일수 early : int
	private int abs;			//	결근일수 abs : int
	private int avAnn;			//	사용가능연차일수 avAnn : int
	private int usedAnn;		//	사용연차일수 usedAnn : int
	private int unusedAnn;		//	잔여연차일수 unusedAnn : int
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
	public int getCont() {
		return cont;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}
	public int getLate() {
		return late;
	}
	public void setLate(int late) {
		this.late = late;
	}
	public int getEarly() {
		return early;
	}
	public void setEarly(int early) {
		this.early = early;
	}
	public int getAbs() {
		return abs;
	}
	public void setAbs(int abs) {
		this.abs = abs;
	}
	public int getAvAnn() {
		return avAnn;
	}
	public void setAvAnn(int avAnn) {
		this.avAnn = avAnn;
	}
	public int getUsedAnn() {
		return usedAnn;
	}
	public void setUsedAnn(int usedAnn) {
		this.usedAnn = usedAnn;
	}
	public int getUnusedAnn() {
		return unusedAnn;
	}
	public void setUnusedAnn(int unusedAnn) {
		this.unusedAnn = unusedAnn;
	}
	
	
	
}
