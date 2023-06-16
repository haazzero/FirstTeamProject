package hr.main;

import java.util.List;
import java.util.Scanner;

import hr.dao.ReviewDAO;
import hr.dao.SalaryInfoDAO;
import hr.dao.SalaryPaymentDAO;
import hr.vo.HRSystemVO;
import hr.vo.ReviewVO;
import hr.vo.SalaryInfoVO;
import hr.vo.SalaryPaymentVO;

public class AccountAdminMain {

	private Scanner sc;
	public static String id;
	private int input;
	private SalaryInfoDAO sdao;
	private SalaryPaymentDAO spdao;
	private ReviewDAO rdao;
	private HRSystemVO hrsysVO;

	
	 public AccountAdminMain() {
		sc = new Scanner(System.in); // 멤버 필드 초기화
		sdao = new SalaryInfoDAO(); //dao 객체 초기화
		spdao = new SalaryPaymentDAO();
		hrsysVO = new HRSystemVO();
	}

	//급여관리메뉴
	public void adManage() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║            MENU           ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 급여 정보        2. 급여 지급내역  ");
		System.out.println("  3. 메인메뉴         4. 비밀번호 변경  ");
		System.out.println("  5. 비밀번호 초기화  6. 시스템 종료");
	    System.out.println();
	    System.out.print("  💡 선택(숫자 입력) >> ");
	    input = sc.nextInt();
	    System.out.println();
	    
	   	switch (input) {
		case 1:
			salInfo();
			break; // 1을 선택하면 급여 정보 관련 salInfo() 호출
		case 2:
			salPayManage();
			break; // 2를 선택하면 급여 지급내역 관련 salPayManage() 호출
		case 3:
			HRSystemMain hrsystemMain = new HRSystemMain();
			hrsystemMain.dragonMain();
			break; // 3을 선택하면 초기 메인 호출
		case 4 : new HRSystemMain().pwChange(hrsysVO); break; 	
		case 5 : new HRSystemMain().pwReset(hrsysVO);  break;
		case 6 : new HRSystemMain().sysEnd();	
		default:
		    System.out.println("--------------------------------");
		   	System.out.println("    1번 ~ 6번을 선택해주세요.   ");
		   	System.out.println("--------------------------------");
		   	System.out.println();
			adManage(); // 경리팀 관리자 메뉴 재출력
			break;
		}// END switch()
	}// end adManage()
	   	
	
	//급여 정보 메뉴
	public void salInfo() {
		System.out.println("  ╔═══════════════════════════╗");  
		System.out.println("  ║       급여 정보 메뉴      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 급여 정보 등록  2. 급여 정보 전체");
		System.out.println("  3. 급여 관리 메뉴  ");
		System.out.println();
		System.out.print("  💡 선택(숫자 입력) >> ");
		input = sc.nextInt();
		System.out.println();
		
	   	switch (input) {
		case 1:
			salInfoWrite();
			break; // 1을 선택하면 급여 정보 등록 salInfoWrite() 호출
		case 2:
			HrReadMain hrreadmain = new HrReadMain();
			hrreadmain.salInfoList();
			break; // 2를 선택하면 급여 정보 전체 목록 호출
		case 3:
			adManage();
			break; // 3을 선택하면 급여관리 메뉴로 재귀
		default:
			System.out.println("--------------------------------");
		   	System.out.println("    1번 ~ 3번을 선택해주세요.   ");
		   	System.out.println("--------------------------------");
		   	System.out.println();
			salInfo(); // 급여 정보 메뉴 재출력
			break;
		}// END switch()
	}//salInfo()end
		
	//급여 정보 등록
	public void salInfoWrite() {
		System.out.println("  ╔═══════════════════════════╗"); 
		System.out.println("  ║       급여 정보 등록      ║");
		System.out.println("  ╠═══════════════════════════║");
		System.out.println("  ║(예시)                     ║");
		System.out.println("  ║직원번호 >> D0000          ║");
		System.out.println("  ║은행이름　>> OO            ║");
		System.out.println("  ║예금주  >> OOO             ║");
		System.out.println("  ║계좌번호 >> 000-0000-0000  ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  < 급여 정보 등록을 위한 정보를 입력해주세요.>");
		System.out.println();
		
		SalaryInfoVO svo = new SalaryInfoVO();
		System.out.print("  1.직원 번호 >>");
		svo.setEmid(sc.next());
		System.out.println();
		System.out.println("  2.은행 이름 >>");
		svo.setBank(sc.next());
		System.out.println();
		System.out.print("  3.예금주 >>");
		svo.setDepositor(sc.next());
		System.out.println();
		System.out.print("  4.계좌번호 >>");
		svo.setAccount(sc.next());
		System.out.println();
		
		System.out.println(" < 등록하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
		System.out.println();
		System.out.print("  💡 입력 >> ");
		String yes = sc.next();
		System.out.println();
		
		
		// 급여 정보 등록에 성공하면 "등록이 완료되었습니다."를 출력하고 
		// 그렇지 않으면 "등록에 실패했습니다."를 출력한 후
		boolean result = sdao.salInsert(svo);
		
		if(yes.equalsIgnoreCase("y"))	{
			if (result == true) {
				System.out.println("--------------------------------");
				System.out.println("      등록이 완료되었습니다.    ");
				System.out.println("--------------------------------");
				System.out.println();
				salInfo(); //salInfo() 급여정보메뉴 돌아가기
			} else {
				System.out.println("--------------------------------");
				System.out.println("      등록에 실패했습니다.    ");
				System.out.println("--------------------------------");
				System.out.println();
				salInfoWrite();  //salInfoWrite() 재출력
			}
		}else if (yes.equalsIgnoreCase("n")) {
			System.out.println("--------------------------------");
			System.out.println("      등록이 취소되었습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			salInfo(); //salInfo() 급여정보메뉴 돌아가기
		
		}else {
			System.out.println("--------------------------------");
			System.out.println("  다시 선택해주세요. ( Y | N )  ");
			System.out.println("--------------------------------");
			System.out.println();
			salInfoWrite();  //salInfoWrite() 재출력
		}	
	}// salInfoWrite end
	
	//급여 정보 수정
	public void salInfoModify(SalaryInfoVO svo) {
		System.out.println("  ╔═══════════════════════════╗"); 
		System.out.println("  ║       급여 정보 수정      ║");
		System.out.println("  ╠═══════════════════════════║");
		System.out.println("  ║(예시)                     ║");
		System.out.println("  ║은행이름　>> OO            ║");
		System.out.println("  ║예금주  >> OOO             ║");
		System.out.println("  ║계좌번호 >> 000-0000-0000  ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		System.out.println();
		
		System.out.println("  1.은행이름  >>");
		svo.setBank(sc.next());
		System.out.println("  2.예금주 >>");
		svo.setDepositor(sc.next());
		System.out.println("  3.계좌번호 >>");
		svo.setAccount(sc.next());
		System.out.println();
		
	    boolean result = sdao.salUpdate(svo.getEmid(), svo.getBank(), svo.getDepositor(), svo.getAccount());
		if (result) {
			System.out.println("--------------------------------");
			System.out.println("      수정이 완료되었습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			salInfo(); //salInfo() 급여정보메뉴 돌아가기
		} else {
		   	System.out.println("--------------------------------");
		   	System.out.println("      수정에 실패했습니다.      ");
		   	System.out.println("--------------------------------");
			System.out.println();
			salInfoModify(svo);  //salInfoModify() 재출력
		}	
	}// salInfoModify end
	
	//급여 정보 삭제
	public void salInfoRemove(String emid) {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       급여 정보 삭제      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println(" < 삭제하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
		System.out.println();
		System.out.print("  💡 입력 >> ");
		String stringinput = sc.next();
		System.out.println();
		
		if (stringinput.equalsIgnoreCase("Y")) { // Y를 선택했을 때 delete 실행
			if (sdao.salDelete(emid)) {
				System.out.println("--------------------------------");
				System.out.println("        삭제 완료되었습니다.    ");
				System.out.println("--------------------------------");
				System.out.println();
				salInfo(); //salInfo() 급여정보메뉴 돌아가기
			} else {
			   	System.out.println("--------------------------------");
			   	System.out.println("         삭제 실패했습니다.      ");
			   	System.out.println("--------------------------------");
			   	System.out.println();
				salInfo(); //salInfo() 급여정보메뉴 돌아가기
				}
		} else if (stringinput.equalsIgnoreCase("N")) {
			System.out.println("--------------------------------");
		   	System.out.println("        삭제 취소되었습니다.    ");
		   	System.out.println("--------------------------------");
		   	System.out.println();
		   	salInfo(); //salInfo() 급여정보메뉴 돌아가기
		} else {
			System.out.println("--------------------------------");
			System.out.println("  다시 선택해주세요. ( Y | N )  ");
			System.out.println("--------------------------------");
			System.out.println();
			salInfoRemove(emid);
		}
		
	}//salInfoRemove () end
	
	//급여 지급내역 메뉴
	public void salPayManage() {
			System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
			System.out.println("  ║    급여 지급 내역 메뉴    ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.println("  1. 급여 지급내역 등록  2. 급여 지급내역 전체 목록");
			System.out.println("  3. 급여 관리 메뉴  ");
			System.out.println();
			System.out.print("  💡 선택(숫자 입력) >> ");
			input = sc.nextInt();
			System.out.println();

		   	switch (input) {
			case 1:
				salPayWrite();
				break; // 1을 선택하면 급여 지급내역 등록 호출
			case 2:
				HrReadMain hrreadmain = new HrReadMain();
				hrreadmain.salPayAllList();
				break; // 2를 선택하면 급여 지급내역 전체 목록 호출
			case 3:
				adManage();
				break; // 3을 선택하면 급여관리 메뉴로 재귀
			default:
				System.out.println("--------------------------------");
			   	System.out.println("    1번 ~ 3번을 선택해주세요.   ");
			   	System.out.println("--------------------------------");
			   	System.out.println();
				salPayManage(); // 급여 지급내역 메뉴 재출력
				break;
			}// END switch()
		}//salPayManage()end
	
	
	// 급여 지급 내역 등록
	public void salPayWrite() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║    급여 지급 내역 등록    ║");
		System.out.println("  ╠═══════════════════════════║");
		System.out.println("  ║(예시)                     ║");
		System.out.println("  ║직원번호 >> D0000          ║");
		System.out.println("  ║지급일시 >> yyyy-mm-dd	  ║");
		System.out.println("  ║인사평가등급   >> A        ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  < 급여 정보 등록을 위한 정보를 입력해주세요.>");
		System.out.println();
		
		SalaryPaymentVO spvo = new SalaryPaymentVO();
		System.out.print("  1.직원 번호  >>");
		spvo.setEmid(sc.next());
		System.out.print("  2.지급일시  >>");
		spvo.setPayDate(sc.next());
		System.out.println();
		
		 // 경리팀 관리자가 콘솔 창을 통해 view를 확인하고 bonus를 입력
		 // View 데이터 가져오기
	    List<ReviewVO> getReviewVO = spdao.getReviewVO();
	    // 가져온 View 데이터 출력하기
	    System.out.println("  ╔═══════════════════════════════════╗");  // 2칸씩
	    System.out.println("  ║     << 인사평가 등급 및 비고 >>   ║");
	    System.out.println("  ╠═══════════════════════════════════╣");
	    for (ReviewVO rvo : getReviewVO) {
	        System.out.println("  ║  직원번호: " + String.format("%-7s", rvo.getEmid()) + "  ║");
	        System.out.println("  ║  인사평가등급: " + String.format("%-9s", rvo.getGrade()) + "  ║");
	        System.out.println("  ║  비고: " + String.format("%-22s", rvo.getRemark()) + "  ║");
	        System.out.println("  ╠═══════════════════════════════════╣");
	    }
	    System.out.println("  ╚═══════════════════════════════════╝");
		System.out.println();
	    System.out.print("  3. 인사평가등급 (A=30%, B=15%, C=5%, D=0) >>");
	    String bonusGrade = sc.next();
	    System.out.println();
	    
	    int bonus = 0;
	    switch (bonusGrade) {
	        case "A":
	            bonus = 30;
	            break;
	        case "B":
	            bonus = 15;
	            break;
	        case "C":
	            bonus = 5;
	            break;
	        case "D":
	            bonus = 0;
	            break;
	        default:
	        	System.out.println("----------------------------------------------------");
	            System.out.println("유효하지 않은 등급입니다. 상여금은 0으로 처리됩니다.");
	            System.out.println("----------------------------------------------------");
	            System.out.println();
	            salPayWrite();
	    }
	    spvo.setBonus(bonus);
	    System.out.println();
	    
	    System.out.println(" < 등록하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
		System.out.println();
		System.out.print("  💡 입력 >> ");
		String yes = sc.next();
		System.out.println();
	    
		// 급여 지급내역 등록에 성공하면 "등록이 완료되었습니다." 
	    // 그렇지 않으면 "등록에 실패했습니다."
		boolean result = spdao.salPayInsert(spvo);
		
		if(yes.equalsIgnoreCase("y")) {
			if (result == true) {
				System.out.println("--------------------------------");
				System.out.println("      등록이 완료되었습니다.    ");
				System.out.println("--------------------------------");
				System.out.println();
				salInfo(); //salInfo() 급여정보메뉴 돌아가기
			}else {
				System.out.println("--------------------------------");
				System.out.println("       등록에 실패했습니다.     ");
				System.out.println("--------------------------------");
				System.out.println();
				salPayWrite();  //salPayWrite() 재출력	
			}
		}else if (yes.equalsIgnoreCase("n")) {
			System.out.println("--------------------------------");
			System.out.println("      등록이 취소되었습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			salInfo(); //salInfo() 급여정보메뉴 돌아가기
		}else {
			System.out.println("--------------------------------");
			System.out.println("  다시 선택해주세요. ( Y | N )  ");
			System.out.println("--------------------------------");
			System.out.println();
			salPayWrite();  //salPayWrite() 재출력	
		}
			
	}//salPayWrite()end
	
	// 급여 지급내역 수정
	public void salPayModify(SalaryPaymentVO spvo) {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║     급여 지급 내역 수정   ║");
		System.out.println("  ╠═══════════════════════════║");
		System.out.println("  ║(예시)                     ║");
		System.out.println("  ║지급일시 >> yyyy-mm-dd	  ║");
		System.out.println("  ║인사평가등급   >> A        ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		System.out.println();
		
		System.out.print("  1.지급일시 >>");
		spvo.setPayDate(sc.next());
		System.out.println();
	    System.out.print("  2. 인사평가등급 (A=30%, B=15%, C=5%, D=0) >>");
	    String bonusGrade = sc.next();
	    System.out.println();
	    
	    int bonus = 0;
	    switch (bonusGrade) {
	        case "A":
	            bonus = 30;
	            break;
	        case "B":
	            bonus = 15;
	            break;
	        case "C":
	            bonus = 5;
	            break;
	        case "D":
	            bonus = 0;
	            break;
	        default:
	        	System.out.println("----------------------------------------------------");
	            System.out.println("유효하지 않은 등급입니다. 상여금은 0으로 처리됩니다.");
	            System.out.println("----------------------------------------------------");
	            System.out.println();
	            salPayWrite();
	    }
		spvo.setBonus(bonus);
		System.out.println();
		
		boolean result = spdao.salPayUpdate(spvo.getSpno(), spvo.getPayDate(), spvo.getBonus());
		
		System.out.println(" < 수정하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
		System.out.println();
		System.out.print("  💡 입력 >> ");
		String yes = sc.next();
		System.out.println();
		
		if(yes.equalsIgnoreCase("y")) {
			if (result == true) {
				System.out.println("--------------------------------");
				System.out.println("      수정이 완료되었습니다.    ");
				System.out.println("--------------------------------");
				System.out.println();
				salPayManage();  //salPayManage() 급여지급내역 메뉴 돌아가기
			} else {
			   	System.out.println("--------------------------------");
			   	System.out.println("      수정에 실패했습니다.      ");
			   	System.out.println("--------------------------------");
				System.out.println();
				salPayModify(spvo); //salPayModify() 재출력 
			}
		}else if (yes.equalsIgnoreCase("n")) {
			System.out.println("--------------------------------");
			System.out.println("      수정이 취소되었습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			salPayManage();  //salPayManage() 급여지급내역 메뉴 돌아가기
		
	}else {
		System.out.println("--------------------------------");
		System.out.println("  다시 선택해주세요. ( Y | N )  ");
		System.out.println("--------------------------------");
		System.out.println();
		salPayModify(spvo); //salPayModify() 재출력 
	}	
}//salPayModify() end
	
	
	//급여 지급 내역 삭제
	public void salPayRemove(int spno) {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║     급여 지급 내역 삭제   ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println(" < 삭제하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
		System.out.println();
		System.out.print("  💡 입력 >> ");
		String stringinput = sc.next();
		System.out.println();
		
		if (stringinput.equalsIgnoreCase("Y")) { // Y를 선택했을 때 delete 실행
			if (spdao.salPayDelete(spno)) {
				System.out.println("----------------------------------");
				System.out.println("       삭제가 완료되었습니다.     ");
				System.out.println("----------------------------------");
				System.out.println();
				salInfo(); //salInfo() 급여정보메뉴 돌아가기	
			} else {
				System.out.println("--------------------------------");
				System.out.println("       삭제에 실패했습니다.     ");
				System.out.println("--------------------------------");
				System.out.println();
			   	salPayRemove(spno);	
			}
		} else if (stringinput.equalsIgnoreCase("N")) {
			System.out.println("--------------------------------");
			System.out.println("      삭제가 취소되었습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			salPayManage();  //salPayManage() 급여지급내역 메뉴 돌아가기
		} else {
			System.out.println("--------------------------------");
			System.out.println("  다시 선택해주세요. ( Y | N )  ");
			System.out.println("--------------------------------");
			System.out.println();
			salPayRemove(spno);
		}
	   
	  	
	} //salPayRemove() end

	

} // class ene
