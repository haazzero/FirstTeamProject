package hr.main;

import java.util.Scanner;

import hr.dao.EmployeeDAO;
import hr.dao.HistoryDAO;
import hr.dao.ReviewDAO;
import hr.dao.SalaryInfoDAO;
import hr.dao.SalaryPaymentDAO;
import hr.dao.WorkDAO;
import hr.dao.YearDAO;
import hr.vo.EmployeeVO;
import hr.vo.HRSystemVO;
import hr.vo.HistoryVO;
import hr.vo.WorkVO;
import hr.vo.YearVO;

public class HrAdminMain {
	
	private Scanner sc;
	public static String id;
	public static int input;
	private int avann;
	private int usedann;
	private EmployeeDAO edao;
	private HistoryDAO hdao;
	private WorkDAO wdao;
	private YearDAO ydao;
	private SalaryInfoDAO sdao;
	private SalaryPaymentDAO spdao;
	private ReviewDAO rdao;
	private HRSystemVO hrsysVO;
	
	public HrAdminMain() {
		sc = new Scanner(System.in);	// 멤버 필드 초기화
		edao = new EmployeeDAO();		// dao 객체 초기화
		hdao = new HistoryDAO();
		wdao = new WorkDAO();
		ydao = new YearDAO();
		sdao = new SalaryInfoDAO();
		spdao = new SalaryPaymentDAO();
		rdao = new ReviewDAO();
		hrsysVO = new HRSystemVO();
	}

	public void pmMenu() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║            MENU           ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 직원 관리       2. 근태 관리      ");
		System.out.println("  3. 급여 조회       4. 인사고과 관리  ");
		System.out.println("  5. 비밀번호 변경   6. 시스템 종료    ");
	    System.out.println();
	    System.out.print("  💡 선택(숫자 입력) >> ");
	    int num = sc.nextInt();
	    System.out.println();
	    System.out.println("--------------------------------");
	   	System.out.println("    1번 ~ 6번을 선택해주세요.   ");
	   	System.out.println("--------------------------------");
	   	
	   	switch(num) {
	   		case 1 : emManage();
	   			break;
	   		case 2 : workManage();
	   			break;
	   		case 3 : 
	   			break;
	   		case 4 : revManage();
	   			break;
	   		case 5 : new HRSystemMain().pwChange(hrsysVO);
	   			break;
	   		case 6 : new HRSystemMain().sysEnd();
	   	
	   	}
	}
	
	
	//직원관리 메뉴
	public void emManage() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║        직원 관리 메뉴         ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 직원 정보 등록  2. 직원 전체 목록");
		System.out.println("  3. 히스토리 등록   4. 히스토리 목록");
		System.out.println("  5. 메인 메뉴 ");
		System.out.println();
		System.out.println("  💡 선택(숫자 입력) >> ");
		 
	    int input = sc.nextInt();   //키보드에서 메뉴 선택 받기
		HrReadMain hrr = new HrReadMain();	// HrReadMain에 있는 목록 메서드 갖고오는 객체
	    switch (input) {
//	    case 1:
//	    	emWrite();
//	    	break;
//	    case 2:
//	    	emModify();
//	    	break;
	    case 3:
	    	historyWrite();
	    	break;
	    case 4:
	    	hrr.hisAllList();
	    	break;
	    case 5:
	    	pmMenu();
	    	break;
	    }
	}   
	
	//직원 정보 등록
	public void emWrite() {
		System.out.println("  ╔═══════════════════════════════════╗");  // 2칸씩
		System.out.println("  ║            직원 정보 등록             ║");
		System.out.println("  ╠═══════════════════════════════════║");
		System.out.println("  ║(예시)                              ║");
		System.out.println("  ║생년월일 >> YYYY-MM-DD                  ║");
		System.out.println("  ║연락처　>> 010-0000-0000              ║");
		System.out.println("  ║성별 >> 여성 | 남성                    ║");
		System.out.println("  ║입사일자 >> YYYY-MM-DD                  ║");
		System.out.println("  ║직급 >> 사원 | 대리 | 과장 | 부장         ║");
		System.out.println("  ╚═══════════════════════════════════╝");
		System.out.println();
		System.out.println("  < 등록할 직원의 정보를 입력해주세요> ");
		
		System.out.println();
		System.out.println("   1. 직원번호 >> ");
		String emid = sc.next();
		System.out.println("   2. 직원이름 >> ");
		String name = sc.next();
		System.out.println("   3. 생년월일 >> ");
		String birth = sc.next();
		System.out.println("   4. 연락처 >> ");
		String tel = sc.next();
		System.out.println("   5. 성별 >> ");
		String gen = sc.next();
		System.out.println("   6. 주소 >> ");
		String add = sc.next();
		System.out.println("   7. 입사일자 >> ");
		String join = sc.next();
		System.out.println("   8. 부서 번호 >> ");
		String dno = sc.next();
		System.out.println("   9. 직급 >> ");
		String position = sc.next();
		System.out.println("  10. 호봉 >> ");
		int hobong = sc.nextInt();

		
		EmployeeVO emvo = new EmployeeVO();
		emvo.setEmid(emid);
		emvo.setName(name);
		emvo.setBirth(birth);
		emvo.setTel(tel);
		emvo.setGen(gen);
		emvo.setAdd(add);
		emvo.setJoin(join);
		emvo.setDno(dno);
		emvo.setPosition(position);
		emvo.setHobong(hobong);
		
		System.out.println();
		
		boolean result = edao.emInsert(emvo);
		if (result == true) {
			System.out.println("----------------------------------");
			System.out.println("  입력하신 정보가 저장되었습니다. ");
			System.out.println("----------------------------------");
			System.out.println("----------------------------------");
			System.out.println("     직원관리 메뉴로 돌아갑니다.  ");
		   	System.out.println("----------------------------------");
		} else {
			System.out.println("----------------------------------");
		   	System.out.println("      정보 등록에 실패했습니다.  " );
		   	System.out.println("----------------------------------");
		   	System.out.println("----------------------------------");
		   	System.out.println("       정보 등록이 취소되었습니다.      ");
		   	System.out.println("----------------------------------");
		}
		
		pmMenu();		
	}
	
	public void emModify(String emid) {
		System.out.println("  ╔═══════════════════════════════════╗");  // 2칸씩
		System.out.println("  ║         직원 정보 수정            ║");
		System.out.println("  ╠═══════════════════════════════════║");
		System.out.println("  ║(예시)                             ║");
		System.out.println("  ║생년월일 >> YY.MM.DD               ║");
		System.out.println("  ║연락처　>> 010-0000-0000           ║");
		System.out.println("  ║성별 >> 여성 | 남성                ║");
		System.out.println("  ║입사일자 >> YY.MM.DD               ║");
		System.out.println("  ║직급 >> 사원 | 대리 | 과장 | 부장  ║");
		System.out.println("  ╚═══════════════════════════════════╝");
		System.out.println();
		System.out.println("        < 정보를 수정하세요.>");
		System.out.println();
		System.out.println("   1. 직원번호 >> ");
		System.out.println("   2. 직원이름 >> ");
		System.out.println("   3. 생년월일 >> ");
		System.out.println("   4. 연락처 >> ");
		System.out.println("   5. 성별 >> ");
		System.out.println("   6. 주소 >> ");
		System.out.println("   7. 입사일자 >> ");
		System.out.println("   8. 부서 번호 >> ");
		System.out.println("   9. 직급 >> ");
		System.out.println("  10. 호봉 >> ");
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("        정보가 수정되었습니다.       ");
		System.out.println("--------------------------------");
		System.out.println("--------------------------------");
		System.out.println("     직원관리 메뉴로 돌아갑니다.      ");
	   	System.out.println("--------------------------------");
	   	System.out.println("--------------------------------");
	   	System.out.println("         수정에 실패했습니다.       ");
	   	System.out.println("--------------------------------");
	   	System.out.println();
		
	}
	
	public void emRemove(String emid) {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       직원 정보 삭제      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println(" < 삭제하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
		System.out.println();
		System.out.println("  💡 입력 >> ");
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("        삭제되었습니다.        ");  //자동연결 메뉴불러오기
		System.out.println("------------------------------");
		System.out.println("------------------------------");
		System.out.println("        취소되었습니다.      "); //자동연결 메뉴불러오기
		System.out.println("------------------------------");
		System.out.println();
		
	}
	
	public void historyWrite() {
    	System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       히스토리 등록       ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		HistoryVO hvo = new HistoryVO();
		System.out.println("  1. 직원 번호 >>");
		hvo.setEmid(sc.next());	
		System.out.println("  2. 현재 부서 >> ");
		hvo.setDname(sc.next());	
		System.out.println("  3. 시작 날짜 >> ");
		hvo.setLeaveStart(sc.next());	
		System.out.println("  4. 종료 날짜 >> ");
		hvo.setLeaveFin(sc.next());	
		System.out.println("  5. 현재 직급 >> ");
		hvo.setPosition(sc.next());	
		System.out.println("  6. 비고 >> ");
		hvo.setRemark(sc.next());	
		System.out.println();
		
		if(hdao.hisInsert(hvo)) {		// hdao에 있는 insert 메서드 호출(boolean 반환)
		System.out.println("--------------------------------");
	   	System.out.println("        등록이 완료되었습니다.       ");
	   	System.out.println("--------------------------------");
	   	emManage();
		} else {
			System.out.println("--------------------------------");
			System.out.println("         등록에 실패했습니다.       ");
		 	System.out.println("--------------------------------");
		 	historyWrite();
		}
	}
	
	public void historyModify(int hno) {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║        히스토리 수정      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("     < 수정할 내용을 입력해주세요 > ");
		System.out.println();
		HistoryVO hvo = hdao.hisSelect(hno);		
		System.out.println("  1. 직원 번호 >>");
		hvo.setEmid(sc.next());	
		System.out.println("  2. 현재 부서 >> ");
		hvo.setDname(sc.next());	
		System.out.println("  3. 시작 날짜 >> ");
		hvo.setLeaveStart(sc.next());	
		System.out.println("  4. 종료 날짜 >> ");
		hvo.setLeaveFin(sc.next());	
		System.out.println("  5. 현재 직급 >> ");
		hvo.setPosition(sc.next());	
		System.out.println("  6. 비고 >> ");
		hvo.setRemark(sc.next());

		System.out.println();
		if(hdao.hisUpdate(hvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("     히스토리가 수정되었습니다.     ");
			System.out.println("--------------------------------");
			System.out.println();
			emManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
			System.out.println("--------------------------------");
			System.out.println("        수정에 실패했습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			historyModify(hno);
		}	// if end
	   
	}
	
	public void historyRemove(int hno) {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║        히스토리 삭제      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println(" < 삭제하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
		System.out.println();
		System.out.print("  💡 입력 >> ");
		String input = sc.next();
		System.out.println();

		if(input.equalsIgnoreCase("Y")) { 	// Y를 선택했을 때 delete 실행
			if(hdao.hisDelete(hno)) {	// delete 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
				System.out.println();
				System.out.println("------------------------------------");
				System.out.println("  삭제되었습니다. 직원관리 메뉴로 돌아갑니다.");  
				System.out.println("------------------------------------");
				emManage();
			} else {				// false 반환 시 실패 메시지 출력 후 다시 삭제 화면 출력
				System.out.println();
				System.out.println("--------------------------------");
			   	System.out.println("         삭제에 실패했습니다.          ");
			   	System.out.println("--------------------------------");
			   	historyRemove(hno);
			}
		} else if (input.equalsIgnoreCase("N")) {	// N을 선택했을 때 취소 메시지 출력 후 메인 메뉴로 돌아감
		   	System.out.println();
			System.out.println("------------------------------------");
			System.out.println("  취소되었습니다. 직원관리 메뉴로 돌아갑니다."); //자동연결 메뉴불러오기
			System.out.println("------------------------------------");
		   	emManage();
		} else {					// Y나 N을 입력하지 않은 경우 다시 삭제 화면 출력
		   	System.out.println();
		   	System.out.println("--------------------------------");
		   	System.out.println("         Y나 N을 입력해주세요          ");
		   	System.out.println("--------------------------------");	
			historyRemove(hno);
		}	// if end
		
	}
	
	
	public void workManage() {		// 근태에 관련된 내용 관리 메뉴
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║        근태 관리 메뉴      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 근태 기록 등록  2. 근태 기록 목록");
		System.out.println("  3. 연차 승인 등록  4. 연차 승인내역 목록 ");
		System.out.println("  5. 메인메뉴");
		System.out.println();
		System.out.print("  💡 선택(숫자 입력) >> ");
		
		int input = sc.nextInt();
		HrReadMain hrr = new HrReadMain();	// HrReadMain에 있는 목록 메서드 갖고오는 객체
		switch (input) {
		case 1:				// 1 입력 시 근태 기록 등록
			workWrite();		
			break;
		case 2:				// 2 입력 시 근태 기록 전체 목록
			hrr.workList();	
			break;
		case 3:				// 3 입력 시 연차 승인 내역 등록
			yearWrite();
			break;
		case 4:				// 4 입력 시 연차 승인 내역 목록
			hrr.yearAllList();
			break;
		case 5:				// 5 입력 시 메인메뉴로 돌아감
			pmMenu();
			break;
		default:			// 다른 값 입력 시 메시지 출력 후 다시 근태 관리 메뉴 메서드 호출
			System.out.println();
			System.out.println("--------------------------------");
		   	System.out.println("       1번 ~ 5번을 선택해주세요.     ");
		   	System.out.println("--------------------------------");
		   	System.out.println();
			workManage();
		} // switch-case end
		
	}
	
	public void workWrite() {	// 근태 기록 등록하는 메서드
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║        근태 기록 등록     ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  < 근태 기록을 위한 정보를 입력해주세요.>");
		System.out.println();
		WorkVO wvo = new WorkVO();
		System.out.print("  1.직원번호 >> ");
			wvo.setEmid(sc.next());				// 입력받은 값을 YearVO의 emid setter에 저장
		System.out.print("  2.근속년수 >> ");
			wvo.setCont(sc.nextInt());
		System.out.print("  3.지각일수 >> ");
			wvo.setLate(sc.nextInt());
		System.out.print("  4.조퇴일수 >> ");
			wvo.setEarly(sc.nextInt());
		System.out.print("  5.결근일수 >> ");
			wvo.setAbs(sc.nextInt());
		System.out.print("  6.사용가능 연차일수 >> ");
			avann = sc.nextInt();
			wvo.setAvAnn(avann);
		System.out.print("  7.사용연차일수 >> ");
			usedann = sc.nextInt();
			wvo.setUsedAnn(usedann);
		//System.out.print("  8.잔여연차일수 >> ");
			wvo.setUnusedAnn(avann - usedann);	// 잔여연차일수는 사용가능한 연차일수에서 사용한 연차일수 뺄셈 계산
		System.out.println();

		if(wdao.workInsert(wvo)) {		// wdao에 있는 insert 메서드 호출(boolean 반환)
			System.out.println("--------------------------------");
			System.out.println("        등록이 완료되었습니다.      ");
			System.out.println("--------------------------------");
			System.out.println();
			workManage();						// 성공하면 관리 메뉴로 돌아감
		} else {
			System.out.println("--------------------------------");
			System.out.println("       정보 등록에 실패했습니다.     ");
			System.out.println("--------------------------------");
			System.out.println();
			workWrite();					// 실패하면 등록화면 다시 출력
		}

	}
	
	public void workModify() {	// 근태 기록 수정 메인 메서드
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       근태 기록 수정      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용의 번호를 입력해주세요 >  ");
		System.out.println();
		System.out.println("  1. 근속년수  2. 지각일수  		 3. 조퇴일수");
		System.out.println("  4. 결근일수  5. 사용가능연차일수	 6. 사용연차일수");
		System.out.println();
		System.out.print("  💡 선택(숫자 입력) >> ");
		
		input = sc.nextInt();	// 공유변수 input을 사용하여 YearDAO에서도 input에 들어있는 숫자를 공유
		switch (input) {
		case 1:					// 1 입력 시 근속년수 수정
			workModify1(HrReadMain.id);		// 조회 메뉴에서 사용된 공유변수 id (직원번호) 사용
			break;
		case 2:					// 2 입력 시 지각일수 수정
			workModify2(HrReadMain.id);		
			break;
		case 3:					// 3 입력 시 조퇴일수 수정
			workModify3(HrReadMain.id);	
			break;
		case 4:					// 4 입력 시 결근일수 수정
			workModify4(HrReadMain.id);	
			break;
		case 5:					// 5 입력 시 사용가능연차일수 수정
			workModify5(HrReadMain.id);	
			break;
		case 6:					// 6 입력 시 사용연차일수 수정
			workModify6(HrReadMain.id);	
			break;
		default:			// 다른 값 입력 시 메시지 출력 후 다시 수정 메인 메서드 호출
			System.out.println();
			System.out.println("--------------------------------");
		   	System.out.println("       1번 ~ 6번을 선택해주세요.     ");
		   	System.out.println("--------------------------------");
		   	System.out.println();
			workModify();
		} // switch-case end
		
	}
	
	public void workModify1(String emid) {	// 근태 기록 근속년수 수정 메서드
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       근태 기록 수정      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		System.out.println();
		WorkVO wvo = wdao.WorkSelect(emid);		// 개별 조회한 데이터 wvo 객체에 담아 쓰기
		System.out.print("  1.근속년수 >> ");
			wvo.setCont(sc.nextInt());			// 수정할 내용 입력 받아서 setter로 받음
		System.out.println();
		
		if(wdao.WorkUpdate(wvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("       근태 기록이 수정되었습니다.    ");
			System.out.println("--------------------------------");
			workManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
		   	System.out.println("--------------------------------");
		   	System.out.println("         수정에 실패했습니다.       ");
		   	System.out.println("--------------------------------");
		   	workModify1(emid);
		}	// if end
		
	}
	
	public void workModify2(String emid) {	// 근태 기록 지각일수 수정 메서드
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       근태 기록 수정      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		System.out.println();
		WorkVO wvo = wdao.WorkSelect(emid);		// 개별 조회한 데이터 wvo 객체에 담아 쓰기
		System.out.print("  2.지각일수 >> ");
			wvo.setLate(sc.nextInt());			// 수정할 내용 입력 받아서 setter로 받음
		System.out.println();
		
		if(wdao.WorkUpdate(wvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("       근태 기록이 수정되었습니다.    ");
			System.out.println("--------------------------------");
			workManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
		   	System.out.println("--------------------------------");
		   	System.out.println("         수정에 실패했습니다.       ");
		   	System.out.println("--------------------------------");
		   	workModify1(emid);
		}	// if end
		
	}
	
	public void workModify3(String emid) {	// 근태 기록 조퇴일수 수정 메서드
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       근태 기록 수정      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		System.out.println();
		WorkVO wvo = wdao.WorkSelect(emid);		// 개별 조회한 데이터 wvo 객체에 담아 쓰기
		System.out.print("  3.조퇴일수 >> ");
			wvo.setEarly(sc.nextInt());			// 수정할 내용 입력 받아서 setter로 받음
		System.out.println();
		
		if(wdao.WorkUpdate(wvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("       근태 기록이 수정되었습니다.    ");
			System.out.println("--------------------------------");
			workManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
		   	System.out.println("--------------------------------");
		   	System.out.println("         수정에 실패했습니다.       ");
		   	System.out.println("--------------------------------");
		   	workModify1(emid);
		}	// if end
		
	}
	
	public void workModify4(String emid) {	// 근태 기록 결근일수 수정 메서드
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       근태 기록 수정      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		System.out.println();
		WorkVO wvo = wdao.WorkSelect(emid);		// 개별 조회한 데이터 wvo 객체에 담아 쓰기
		System.out.print("  4.결근일수 >> ");
			wvo.setAbs(sc.nextInt());			// 수정할 내용 입력 받아서 setter로 받음
		System.out.println();
		
		if(wdao.WorkUpdate(wvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("       근태 기록이 수정되었습니다.    ");
			System.out.println("--------------------------------");
			workManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
		   	System.out.println("--------------------------------");
		   	System.out.println("         수정에 실패했습니다.       ");
		   	System.out.println("--------------------------------");
		   	workModify1(emid);
		}	// if end
		
	}
	
	public void workModify5(String emid) {	// 근태 기록 사용가능한 연차일수 수정 메서드
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       근태 기록 수정      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		System.out.println();
		WorkVO wvo = wdao.WorkSelect(emid);		// 개별 조회한 데이터 wvo 객체에 담아 쓰기
		System.out.print("  5.사용가능 연차일수 >> ");
			avann = sc.nextInt();
			wvo.setAvAnn(avann);				// 수정할 내용 입력 받아서 setter로 받음
			wvo.setUnusedAnn(avann - usedann);	// 잔여연차일수 자동으로 수정
		System.out.println();
		
		if(wdao.WorkUpdate1(wvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("       근태 기록이 수정되었습니다.    ");
			System.out.println("--------------------------------");
			workManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
		   	System.out.println("--------------------------------");
		   	System.out.println("         수정에 실패했습니다.       ");
		   	System.out.println("--------------------------------");
		   	workModify1(emid);
		}	// if end
		
	}
	
	public void workModify6(String emid) {	// 근태 기록 사용연차일수 수정 메서드
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       근태 기록 수정      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		System.out.println();
		WorkVO wvo = wdao.WorkSelect(emid);		// 개별 조회한 데이터 wvo 객체에 담아 쓰기
		System.out.print("  6.사용연차일수 >> ");
			usedann = sc.nextInt();				
			wvo.setUsedAnn(usedann);			// 수정할 내용 입력 받아서 setter로 받음
			wvo.setUnusedAnn(avann - usedann); 	// 잔여연차일수 자동으로 수정
		System.out.println();
		
		if(wdao.WorkUpdate1(wvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("       근태 기록이 수정되었습니다.    ");
			System.out.println("--------------------------------");
			workManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
		   	System.out.println("--------------------------------");
		   	System.out.println("         수정에 실패했습니다.       ");
		   	System.out.println("--------------------------------");
		   	workModify1(emid);
		}	// if end
		
	}
	
	public void workRemove(String emid) {	// 근태 기록 삭제 메서드
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║        근태 기록 삭제     ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println(" < 삭제하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
		System.out.println();
		System.out.print("  💡 입력 >> ");
		String input = sc.next();
		if(input.equalsIgnoreCase("Y")) { 	// Y를 선택했을 때 delete 실행
			if(wdao.WorkDelete(emid)) {	// delete 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
				System.out.println();
				System.out.println("--------------------------------");
				System.out.println("           삭제되었습니다.          ");
				System.out.println("--------------------------------");
				workManage();
			} else {				// false 반환 시 실패 메시지 출력 후 다시 삭제 화면 출력
				System.out.println();
				System.out.println("--------------------------------");
			   	System.out.println("         삭제에 실패했습니다.          ");
			   	System.out.println("--------------------------------");
			   	workRemove(emid);
			}
		} else if (input.equalsIgnoreCase("N")) {	// N을 선택했을 때 취소 메시지 출력 후 메인 메뉴로 돌아감
		   	System.out.println();
		   	System.out.println("--------------------------------");
		   	System.out.println("           취소되었습니다.          ");
		   	System.out.println("--------------------------------");
		   	workManage();
		} else {					// Y나 N을 입력하지 않은 경우 다시 삭제 화면 출력
		   	System.out.println();
		   	System.out.println("--------------------------------");
		   	System.out.println("         Y나 N을 입력해주세요          ");
		   	System.out.println("--------------------------------");	
			workRemove(emid);
		}

	}
	
	public void yearWrite() {	// 연차 승인 내역 등록 메서드
    	System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       연차 승인 등록      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 등록할 내용을 입력해주세요 >  ");
		System.out.println();
		YearVO yvo = new YearVO();
		System.out.print("  1.직원번호 >> ");
			yvo.setEmid(sc.next());
		System.out.print("  2.연차사용일자 >> ");
			yvo.setYearDate(sc.next());
		System.out.print("  3.연차사용일수 >> ");
			yvo.setYearCnt(sc.next());
		System.out.print("  4.연차승인자 >> ");
			yvo.setApp(sc.next());
		System.out.print("  5.연차승인일자 >> ");
			yvo.setAppDate(sc.next());
		System.out.println();
			if(ydao.yearInsert(yvo)) {		// ydao에 있는 insert 메서드 호출(boolean 반환)
				System.out.println("--------------------------------");
				System.out.println("       등록되었습니다.          ");
				System.out.println("--------------------------------");
				System.out.println();
				workManage();						// 성공하면 관리 메뉴로 돌아감
			} else {
				System.out.println("--------------------------------");
				System.out.println("     정보등록에 실패했습니다.   ");
				System.out.println("--------------------------------");
				System.out.println();
				yearWrite();					// 실패하면 등록화면 다시 출력
			}
		
	}
	
	public void yearModify() {	// 개인의 연차 승인 내역 수정 메인 메뉴
    	System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║    연차 승인 내역 수정    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용의 번호를 입력해주세요 >  ");
		System.out.println();
		System.out.println("  1. 연차사용일자  	 2. 연차사용일수");
		System.out.println("  3. 연차승인자  	 4. 연차승인일자");
		System.out.println();
		System.out.print("  💡 선택(숫자 입력) >> ");
			
		input = sc.nextInt();
		switch (input) {
		case 1:				// 1 입력 시 연차사용날짜 수정
			yearModify1(HrReadMain.no);		
			break;
		case 2:				// 2 입력 시 연차사용일수 수정
			yearModify2(HrReadMain.no);		
			break;
		case 3:				// 3 입력 시 연차승인자 수정
			yearModify3(HrReadMain.no);	
			break;
		case 4:				// 4 입력 시 연차승인일자 수정
			yearModify4(HrReadMain.no);	
			break;
		default:			// 다른 값 입력 시 메시지 출력 후 다시 수정 메인 메서드 호출
			System.out.println();
			System.out.println("--------------------------------");
		   	System.out.println("       1번 ~ 4번을 선택해주세요.     ");
		   	System.out.println("--------------------------------");
		   	System.out.println();
			yearModify();
		} // switch-case end

	}
	
	public void yearModify1(int emno) {	// 개인의 연차 승인 내역 연차사용일자 수정 메서드
    	System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║    연차 승인 내역 수정    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		YearVO yvo = ydao.yearSelect(emno);
		System.out.println();
		System.out.print("  1.연차사용일자 >> ");
			yvo.setYearDate(sc.next());
		System.out.println();	
			
		if(ydao.yearUpdate(yvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("         수정되었습니다.        ");
			System.out.println("--------------------------------");
			System.out.println();
			workManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
			System.out.println("--------------------------------");
			System.out.println("        수정에 실패했습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			yearModify1(emno);
		}	// if end	

	}
	
	public void yearModify2(int emno) {	// 개인의 연차 승인 내역 수정 메서드
    	System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║    연차 승인 내역 수정    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		YearVO yvo = ydao.yearSelect(emno);
		System.out.println();
		System.out.print("  2.연차사용일수 >> ");
			yvo.setYearCnt(sc.next());
			
		if(ydao.yearUpdate(yvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("         수정되었습니다.        ");
			System.out.println("--------------------------------");
			System.out.println();
			workManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
			System.out.println("--------------------------------");
			System.out.println("        수정에 실패했습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			yearModify2(emno);
		}	// if end	

	}
	
	public void yearModify3(int emno) {	// 개인의 연차 승인 내역 수정 메서드
    	System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║    연차 승인 내역 수정    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		YearVO yvo = ydao.yearSelect(emno);
		System.out.println();
		System.out.print("  3.연차승인자 >> ");
			yvo.setApp(sc.next());
		System.out.println();	
			
		if(ydao.yearUpdate(yvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("         수정되었습니다.        ");
			System.out.println("--------------------------------");
			System.out.println();
			workManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
			System.out.println("--------------------------------");
			System.out.println("        수정에 실패했습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			yearModify3(emno);
		}	// if end	

	}
	
	public void yearModify4(int emno) {	// 개인의 연차 승인 내역 수정 메서드
    	System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║    연차 승인 내역 수정    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		YearVO yvo = ydao.yearSelect(emno);
		System.out.println();
		System.out.print("  4.연차승인일자 >> ");
			yvo.setAppDate(sc.next());
		System.out.println();	
			
		if(ydao.yearUpdate(yvo)) {		// update 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("         수정되었습니다.        ");
			System.out.println("--------------------------------");
			System.out.println();
			workManage();
		} else {					// false 반환 시 실패 메시지 출력 후 수정 화면 다시 출력
			System.out.println("--------------------------------");
			System.out.println("        수정에 실패했습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			yearModify4(emno);
		}	// if end	

	}
	
	public void yearRemove(int emno) {	// 개인의 연차 승인 내역 삭제 메서드
    	System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║    연차 승인 내역 삭제    ║");
		System.out.println("  ╚═══════════════════════════╝");
    	System.out.println();
    	System.out.println(" < 삭제하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
    	System.out.println();
		System.out.print("  💡 입력 >> ");
		String input = sc.next();
		if(input.equalsIgnoreCase("Y")) { 	// Y를 선택했을 때 delete 실행
			if(ydao.yeardelete(emno)) {		// delete 실행 후 true 반환 시 완료 메시지 출력 후 메인 메뉴로 돌아감
				System.out.println();
				System.out.println("--------------------------------");
				System.out.println("           삭제되었습니다.          ");
				System.out.println("--------------------------------");
				workManage();
			} else {				// false 반환 시 실패 메시지 출력 후 다시 삭제 화면 출력
				System.out.println();
				System.out.println("--------------------------------");
			   	System.out.println("         삭제에 실패했습니다.          ");
			   	System.out.println("--------------------------------");
			   	yearRemove(emno);
			}
		} else if (input.equalsIgnoreCase("N")) {	// N을 선택했을 때 취소 메시지 출력 후 메인 메뉴로 돌아감
		   	System.out.println();
		   	System.out.println("--------------------------------");
		   	System.out.println("           취소되었습니다.          ");
		   	System.out.println("--------------------------------");
		   	workManage();
		} else {					// Y나 N을 입력하지 않은 경우 다시 삭제 화면 출력
		   	System.out.println();
		   	System.out.println("--------------------------------");
		   	System.out.println("         Y나 N을 입력해주세요          ");
		   	System.out.println("--------------------------------");	
			yearRemove(emno);
		}
		
	}
	
	public void revManage() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║    인사 고과 관리 메뉴    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 평가 등록  2. 평가 기록 목록");
		System.out.println("  3. 메인메뉴"                         );  
		System.out.println();
		 System.out.println("  💡 선택(숫자 입력) >> ");
		System.out.println();
		System.out.println("--------------------------------");
	   	System.out.println("    1번 ~ 3번을 선택해주세요.   ");
	   	System.out.println("--------------------------------");
	   	System.out.println();
		
	}
	
	public void revWrite() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       인사고과 등록       ║");
		System.out.println("  ╠═══════════════════════════║");
		System.out.println("  ║ 각 항목당 점수 : 0 ~ 20점 ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("      < 점수를 입력해주세요 >  ");
		System.out.println();
		System.out.println("  1.직원번호 >> ");
		System.out.println("  2.관리능력 : 업무 우선순위 선정 및 위기상황 대처 능력의 정도");
		System.out.println("  💡 >> ");
		System.out.println("  3.유대관계: 구성원 및 타부서 간의 의사소통과 협업 여부");	
		System.out.println("  💡 >> ");
		System.out.println("  4.책임감 : 담당 일을 책임감 있게 수행하고 그 결과에 대하여 책임을 지는 태도");
		System.out.println("  💡 >> ");
		System.out.println("  5.근면성 : 성실 근면한 자세로 업무에 임하고 있는지의 여부 (지각,조퇴,결근 반영)");
		System.out.println("  💡 >> ");
		System.out.println("  6.업무지식 : 담당업무 수행에 필요한 전문지식의 정도");
		System.out.println("  💡 >> ");
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("         등록되었습니다.        ");
		System.out.println("--------------------------------");
		System.out.println("--------------------------------");
		System.out.println("       등록에 실패했습니다.     ");
		System.out.println("--------------------------------");
		System.out.println();
		
	}
	
	public void revModify(String emid) {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       인사 고과 수정      ║");
		System.out.println("  ╠═══════════════════════════║");
		System.out.println("  ║ 각 항목당 점수 : 0 ~ 20점 ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    < 수정할 내용을 입력해주세요 >  ");
		System.out.println();
		System.out.println("  1.관리능력 : 업무 우선순위 선정 및 위기상황 대처 능력의 정도");
		System.out.println("  💡 >> ");
		System.out.println("  2.유대관계: 구성원 및 타부서 간의 의사소통과 협업 여부");	
		System.out.println("  💡 >> ");
		System.out.println("  3.책임감 : 담당 일을 책임감 있게 수행하고 그 결과에 대하여 책임을 지는 태도");
		System.out.println("  💡 >> ");
		System.out.println("  4.근면성 : 성실 근면한 자세로 업무에 임하고 있는지의 여부 (지각,조퇴,결근 반영)");
		System.out.println("  💡 >> ");
		System.out.println("  5.업무지식 : 담당업무 수행에 필요한 전문지식의 정도");
		System.out.println("  💡 >> ");
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("         수정되었습니다.        ");
		System.out.println("--------------------------------");
		System.out.println("--------------------------------");
		System.out.println("        수정에 실패했습니다.    ");
		System.out.println("--------------------------------");
		System.out.println();
		
	}
	
	public void revRemove(String emid) {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       인사 고과 삭제      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
    	System.out.println(" < 삭제하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
    	System.out.println();
		System.out.println("  💡 입력 >> ");
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("         삭제되었습니다.         ");
		System.out.println("--------------------------------");
	   	System.out.println("--------------------------------");
	   	System.out.println("         취소되었습니다.          ");
	   	System.out.println("--------------------------------");
	   	System.out.println();
		
	}	
	

//	System.out.println("--------------------------------");
//	System.out.println("  💡 시스템을 종료하시겠습니까? ( Y | N )");
//	System.out.println("  💡 >> ");
//	System.out.println("--------------------------------");
//  System.out.println("       1번이나 2번을 선택하세요.     ");
//  System.out.println("--------------------------------");
	
	public static void main(String[] args) {	// 근태 관리 시험 메서드
	HrAdminMain hra = new HrAdminMain();
	hra.emManage();
	}
	
}
