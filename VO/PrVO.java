package vo;

import java.util.Date;

public class PrVO {

	private String emid;	//	직원번호 emid : String
	private String name;	//	직원이름 name : String
	private int eval1;		//	평가항목1 eval1 : int
	private int eval2;		//	평가항목2 eval2 : int
	private int eval3;		//	평가항목3 eval3 : int
	private int eval4;		//	평가항목4 eval4 : int
	private int eval5;		//	평가항목5 eval5 : int
	private int evalTot;	//	평가총점 evalTot : int
	private char grade;		//	평가등급 grade : char
	private String remark;	//	비고 remark : String
	private Date evalDate;	//	평가실시날짜 evalDate : date
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
	public int getEval1() {
		return eval1;
	}
	public void setEval1(int eval1) {
		this.eval1 = eval1;
	}
	public int getEval2() {
		return eval2;
	}
	public void setEval2(int eval2) {
		this.eval2 = eval2;
	}
	public int getEval3() {
		return eval3;
	}
	public void setEval3(int eval3) {
		this.eval3 = eval3;
	}
	public int getEval4() {
		return eval4;
	}
	public void setEval4(int eval4) {
		this.eval4 = eval4;
	}
	public int getEval5() {
		return eval5;
	}
	public void setEval5(int eval5) {
		this.eval5 = eval5;
	}
	public int getEvalTot() {
		return evalTot;
	}
	public void setEvalTot(int evalTot) {
		this.evalTot = evalTot;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getEvalDate() {
		return evalDate;
	}
	public void setEvalDate(Date evalDate) {
		this.evalDate = evalDate;
	}
	
	
}
