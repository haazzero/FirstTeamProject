package hr.main;

import java.util.List;
import java.util.Scanner;

import hr.dao.EmployeeDAO;
import hr.dao.HistoryDAO;
import hr.dao.ReviewDAO;
import hr.dao.SalaryInfoDAO;
import hr.dao.SalaryPaymentDAO;
import hr.dao.WorkDAO;
import hr.dao.YearDAO;
import hr.vo.SalaryInfoVO;
import hr.vo.SalaryPaymentVO;
import hr.vo.WorkVO;
import hr.vo.YearVO;

public class HrReadMain {

	private Scanner sc;
	private int input;
	private String inputString;
	private EmployeeDAO edao;
	private HistoryDAO hdao;
	private WorkDAO wdao;
	private YearDAO ydao;
	private SalaryInfoDAO sdao;
	private SalaryPaymentDAO spdao;
	private ReviewDAO rdao;

	public HrReadMain() {
		sc = new Scanner(System.in); // 멤버 필드 초기화 //keyboard로 받으니까 System.in
		sdao = new SalaryInfoDAO();
		spdao = new SalaryPaymentDAO();
	}

	public void pmReader() {
		System.out.println("  ╔═══════════════════════════╗"); // 2칸씩
		System.out.println("  ║            MENU           ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 직원 정보 조회   2. 근태 조회 ");
		System.out.println("  3. 급여 조회        4. 인사고과 조회 ");
		System.out.println("  5. 시스템 종료");
		System.out.println();
		System.out.println("  💡 선택(숫자 입력) >> ");
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("     1번 ~ 5번을 선택해주세요.  ");
		System.out.println("--------------------------------");
		System.out.println();

	}
	// 급여 조회 메뉴
	public void salMain() { // 급여 조회 메뉴
		System.out.println("  ╔═══════════════════════════╗"); // 2칸씩
		System.out.println("  ║       급여 조회 메뉴          ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1.급여 정보 전체 목록");
		System.out.println("  2.급여 지급 내역 전체 목록");
		System.out.println("  3.메인메뉴");
		System.out.println();
		System.out.print("  💡 선택(숫자 입력) >> ");
		System.out.println();

		input = sc.nextInt();
		switch (input) {
		case 1:
			salInfoList(); //급여 정보 전체 목록 호츌
			break;
		case 2:
			salPayAllList(); //급여 지급 내역 전체 목록 호출
			break;
		case 3:
			pmReader(); //급여 조회 메뉴로 재귀
			break;
		default:
			System.out.println("--------------------------------");
			System.out.println("     1번 ~ 3번을 선택해주세요.  ");
			System.out.println("--------------------------------");
			System.out.println();
			salMain(); // 급여 조회 메뉴 재출력
			break;
		}// END switch()
	}// salMain()end

	// 모든 직원의 급여 정보 전체 목록
	public void salInfoList() { // 모든 직원의 급여 정보 전체 목록
		System.out.println("  ╔═══════════════════════════╗");
		System.out.println("  ║     급여정보 전체 목록		  ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();

		List<SalaryInfoVO> savoList = sdao.salSelect();
		if (savoList.size() > 0) {// list의 사이즈가 0보다 크면 즉, list에 값이 들어있으면 목록 조회
			System.out.println("───────────────────────────────────────────");
			System.out.println(" NO. | 직원번호    | 직원이름   | 기본급(호봉)  ");
			System.out.println("───────────────────────────────────────────");
			for (SalaryInfoVO savo : savoList) { //list 끝까지 반복
				System.out.println(
						"  " + savo.getSno() + " | " + savo.getEmid() + " | " + savo.getName() + " | " + savo.getSal());
			}
			System.out.println("───────────────────────────────────────────");
			System.out.println();
			System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");
			System.out.println();
			System.out.println("   1. 개별 조회  2. 급여 조회 메뉴");
			System.out.println();
			System.out.print("  💡 선택(숫자 입력) >> ");
			int input = sc.nextInt(); // 키보드에서 메뉴 선택 받기

			if (input == 1) {
				salInfoDetail(); // 급여정보 개별조회
			} else if (input == 2) {
				salMain(); // 급여 조회 메뉴로 회귀
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		} else {
			System.out.println("급여 정보가 존재하지 않습니다.");
		}
	}
	
	// 한 명의 급여 정보 조회
	public void salInfoDetail() { 
		System.out.println("  ╔═══════════════════════════╗"); // 2칸씩
		System.out.println("  ║     급여정보 개별 조회		  ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  💡 직원번호를 입력하세요 (D0000) >>  ");
		String emid = sc.next();
		SalaryInfoVO savo = sdao.salSelect(emid);
		System.out.println();

		if (savo != null) {
			System.out.println("─────────────────────────────────────────────────────────────────────");
			System.out.println(" NO. | 직원번호 | 직원이름 | 기본급(호봉) | 은행명 | 예금주 | 계좌번호");
			System.out.println("─────────────────────────────────────────────────────────────────────");
			System.out.println(" " + savo.getSno() + " | " + savo.getEmid() + " | " + savo.getName() + " | "
					+ savo.getSal() + " | " + savo.getBank() + " | " + savo.getDepositor() + " | " + savo.getAccount());
			System.out.println("─────────────────────────────────────────────────────────────────────");
		}

		System.out.println();
		System.out.println(" < 원하시는 메뉴를 선택해주세요. >");
		System.out.println();
		System.out.println("  1. 급여 정보 수정  2. 급여 정보 삭제  3. 급여 조회 메뉴");
		System.out.println();
		System.out.println("  💡 선택(숫자 입력) >> ");
		System.out.println();

		input = sc.nextInt();
		AccountAdminMain accountadminmain = new AccountAdminMain();
		switch (input) {
		case 1:
			accountadminmain.salInfoModify(savo);
			break;
		case 2:
			accountadminmain.salInfoRemove(emid);
			break;
		case 3:
			salMain();
			break;
		default:
			System.out.println("--------------------------------");
			System.out.println("     1번 ~ 3번을 선택해주세요.  ");
			System.out.println("--------------------------------");
			System.out.println();
			salMain();
			break;
		}// END switch()
	}// salInfoDetail()end

	// 한 명의 급여 정보 조회 인사팀ver(수정삭제X)
	public void salInfoDetailforhr() { 
		System.out.println("  ╔═══════════════════════════╗"); // 2칸씩
		System.out.println("  ║     급여정보 개별 조회		  ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  💡 직원번호를 입력하세요 (D0000) >>  ");
		String emid = sc.next();
		SalaryInfoVO savo = sdao.salSelect(emid);
		System.out.println();

		if (savo != null) {
			System.out.println("─────────────────────────────────────────────────────────────────────");
			System.out.println(" NO. | 직원번호 | 직원이름 | 기본급(호봉) | 은행명 | 예금주 | 계좌번호");
			System.out.println("─────────────────────────────────────────────────────────────────────");
			System.out.println(" " + savo.getSno() + " | " + savo.getEmid() + " | " + savo.getName() + " | "
					+ savo.getSal() + " | " + savo.getBank() + " | " + savo.getDepositor() + " | " + savo.getAccount());
			System.out.println("─────────────────────────────────────────────────────────────────────");
		}

		System.out.println();
		System.out.println(" < 원하시는 메뉴를 선택해주세요. >");
		System.out.println();
		System.out.println("  1. 급여 조회 메뉴");
		System.out.println();
		System.out.println("  💡 선택(숫자 입력) >> ");
		System.out.println();

		input = sc.nextInt();
		AccountAdminMain accountadminmain = new AccountAdminMain();
		switch (input) {
		case 1:
			salMain();
			break;
		default:
			System.out.println("--------------------------------");
			System.out.println("     1번을 선택해주세요.  ");
			System.out.println("--------------------------------");
			System.out.println();
			salMain();
			break;
		}// END switch()
	}// salInfoDetail()end

	// 모든 직원의 급여 지급 내역 목록
	public void salPayAllList() { 
		System.out.println("╔═══════════════════════════╗"); // 2칸씩
		System.out.println("║   급여 지급 내역 전체 목록		║");
		System.out.println("╚═══════════════════════════╝");
		System.out.println();

		List<SalaryPaymentVO> spavoList = spdao.salPaySelect();
		if (spavoList.size() > 0) {
			System.out.println("─────────────────────────────────────────────────────────");
			System.out.println(" NO. |직원번호 |직원이름 | 지급일시 | 총급여 ");
			System.out.println("───-──────────────────────────────────────────────────────");
			for (SalaryPaymentVO spavo : spavoList) {
//			    spavo.calculateTotal(); // 총 급여 계산
			    System.out.printf(" %3d | %8s | %10s | %12s | %8d | %10d | %10d \n",
			            spavo.getSpno(), spavo.getEmid(), spavo.getName(), spavo.getPayDate(), spavo.getSal(), spavo.getBonus(), spavo.getTotal());
			}

			System.out.println("───────────────────────────────────────────");
			System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");
			System.out.println();
			System.out.println("  1. 개별 조회  2. 급여 조회 메뉴");
			System.out.println();
			System.out.print("  💡 선택(숫자 입력) >> ");

			int input = sc.nextInt(); //// 키보드에서 메뉴 선택 받기
//			sc.close(); // Scanner 닫기

			if (input == 1) { // 메인
				salPayList(); // 급여 지급 내역 개별조회
			} else if (input == 2) {
				salMain(); // 급여 조회 메뉴로 회귀
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		} else {
			System.out.println("급여 지급 내역이 존재하지 않습니다. ");
			salMain();
		}
	} // salPayAllList() end
	
	 // 한 명의 급여 지급 내역 조회
	public void salPayList() {
	    System.out.println("  ╔═════════════════════════════╗"); // 2칸씩
	    System.out.println("  ║   급여 지급 내역 개별 조회			║");
	    System.out.println("  ╚═════════════════════════════╝");
	    System.out.println();
	    System.out.print("  💡 직원번호를 입력하세요 (D0000) >>  ");
	    String emid = sc.next();
	    
	    SalaryPaymentVO spavo = spdao.salPaySelect(emid);

	    if (spavo != null) {
	        System.out.println("─────────────────────────────────────────────────────────");
	        System.out.println(" NO. |직원번호 | 직원이름 | 지급일시 | 기본급(호봉) | 상여금 | 총급여 ");
	        System.out.println("─────────────────────────────────────────────────────────");
	        System.out.printf(" %3d | %8s | %10s | %12s | %8d | %10d | %10d \n",
	                spavo.getSpno(), spavo.getEmid(), spavo.getName(), spavo.getPayDate(), spavo.getBonus(), spavo.getSal(), spavo.getTotal());
	        System.out.println("───────────────────────────────────────────");
	        System.out.println();
	        System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");
	        System.out.println();
	        System.out.println("  1. 상세 조회  2. 급여 조회 메뉴");
	        System.out.println();
	        System.out.print("  💡 선택(숫자 입력) >> ");

	        int input = sc.nextInt(); // 키보드에서 메뉴 선택 받기
//	        sc.close();

	        if (input == 1) { // 메인으로 이동
	            salPayDetail(); // 급여 지급 내역 개별조회
	        } else if (input == 2) { // 급여 조회 메뉴
	            salMain();
	        } else {
	            System.out.println("--------------------------------");
	            System.out.println("     1번 ~ 2번을 선택해주세요.  ");
	            System.out.println("--------------------------------");
	        }
	    } else {
	        System.out.println("해당 직원의 급여 지급 내역이 존재하지 않습니다.");
	    }
	}

	// 급여 지급 내역 상세 조회
	public void salPayDetail() {
		System.out.println("  ╔═════════════════════════════╗"); // 2칸씩
		System.out.println("  ║  급여 지급 내역 상세 조회			║");
		System.out.println("  ╚═════════════════════════════╝");
		System.out.println();
	    System.out.print("  💡 조회할 상세 지급내역 번호를 입력하세요 >>  ");
	    int spno = sc.nextInt();
	    SalaryPaymentVO spavo = spdao.salPaySelect(spno);
	 

	    if (spavo != null) {
		System.out.println("──────────────────────────────────────────────────────");
		System.out.println(" NO. |직원번호 | 직원이름 | 지급일시 | 기본급(호봉) | 상여금 | 총급여 ");
		System.out.println("──────────────────────────────────────────────────────");
		 System.out.printf(" %3d | %8s | %10s | %12s | %8d | %10d | %10d \n",
	                spavo.getSpno(), spavo.getEmid(), spavo.getName(), spavo.getPayDate(), spavo.getBonus(), spavo.getSal(), spavo.getTotal());
		System.out.println("──────────────────────────────────────────────────────");
		System.out.println();
        System.out.println(" < 메뉴를 선택해주세요. >");
		System.out.println("   1. 급여 지급 내역 수정  2. 급여 지급 내역 삭제  3. 급여 조회 메뉴");
		System.out.println();
		System.out.println("  💡 선택(숫자 입력) >> ");
		System.out.println();

		input = sc.nextInt();
		switch (input) {
		case 1:
			AccountAdminMain accountadminmain = new AccountAdminMain();
			accountadminmain.salPayModify(spavo);
			break;
		case 2:
			AccountAdminMain accountadminmain1 = new AccountAdminMain();
			accountadminmain1.salPayRemove(spno);
			break;
		case 3:
			salMain();
			break;
		default:
			System.out.println("--------------------------------");
			System.out.println("     1번 ~ 3번을 선택해주세요.  ");
			System.out.println("--------------------------------");
			System.out.println();
			salMain();
			break;
		}// END switch()
	    }//salPayDetail()end
	}// salPayDetail()end
	
	// 급여 지급 내역 상세 조회 인사팀ver(수정삭제X)
	public void salPayDetailforhr() {
		System.out.println("  ╔═════════════════════════════╗"); // 2칸씩
		System.out.println("  ║  급여 지급 내역 상세 조회			║");
		System.out.println("  ╚═════════════════════════════╝");
		System.out.println();
	    System.out.print("  💡 조회할 상세 지급내역 번호를 입력하세요 >>  ");
	    int spno = sc.nextInt();
	    SalaryPaymentVO spavo = spdao.salPaySelect(spno);
	 

	    if (spavo != null) {
		System.out.println("──────────────────────────────────────────────────────");
		System.out.println(" NO. |직원번호 | 직원이름 | 지급일시 | 기본급(호봉) | 상여금 | 총급여 ");
		System.out.println("──────────────────────────────────────────────────────");
		 System.out.printf(" %3d | %8s | %10s | %12s | %8d | %10d | %10d \n",
	                spavo.getSpno(), spavo.getEmid(), spavo.getName(), spavo.getPayDate(), spavo.getBonus(), spavo.getSal(), spavo.getTotal());
		System.out.println("──────────────────────────────────────────────────────");
		System.out.println();
        System.out.println(" < 메뉴를 선택해주세요. >");
		System.out.println("   1. 급여 조회 메뉴");
		System.out.println();
		System.out.println("  💡 선택(숫자 입력) >> ");
		System.out.println();

		input = sc.nextInt();
		switch (input) {
		case 1:
			salMain();
			break;
		default:
			System.out.println("--------------------------------");
			System.out.println("     1번을 선택해주세요.  ");
			System.out.println("--------------------------------");
			System.out.println();
			salMain();
			break;
		}// END switch()
	    }//salPayDetail()end
	}// salPayDetail()end

}
