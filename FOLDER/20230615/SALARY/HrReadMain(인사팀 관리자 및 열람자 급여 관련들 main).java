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
import hr.vo.EmployeeVO;
import hr.vo.HRSystemVO;
import hr.vo.HistoryVO;
import hr.vo.ReviewVO;
import hr.vo.SalaryInfoVO;
import hr.vo.SalaryPaymentVO;
import hr.vo.WorkVO;
import hr.vo.YearVO;

public class HrReadMain {

	private Scanner sc;
	public static String id;
	public static int no;
	private int input;
	private String inputString;
	private EmployeeDAO edao;
	private HistoryDAO hdao;
	private WorkDAO wdao;
	private YearDAO ydao;
	private SalaryInfoDAO sdao;
	private SalaryPaymentDAO spdao;
	private ReviewDAO rdao;
	private HRSystemVO hrsysVO;
	private ReviewVO revo;
	public static int rno;
	private List<EmployeeVO> list;
	HRSystemMain hrsys; 
	
	
	public HrReadMain() {
		sc = new Scanner (System.in);
		revo = new ReviewVO();
		wdao = new WorkDAO();
		ydao = new YearDAO();
		rdao = new ReviewDAO();
		edao = new EmployeeDAO();
		hdao = new HistoryDAO();
		sdao = new SalaryInfoDAO();
		spdao = new SalaryPaymentDAO();
		hrsysVO = new HRSystemVO();
		hrsys = new HRSystemMain();
	}

	// 인사팀  조회 메뉴
	public void pmReader() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║            MENU           ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 직원 정보 조회   2. 근태 조회 ");
		System.out.println("  3. 급여 조회        4. 인사고과 조회 ");
		System.out.println("  5. 비밀번호 변경    6. 비밀번호 초기화");
		System.out.println("  7. 시스템 종료");
		System.out.println();
	    System.out.print("  💡 선택(숫자 입력) >> ");
	    int input = sc.nextInt();
	    System.out.println();
		
	   	switch(input) {
	   	case 1 : emView();   break;  								//	 1. 직원 정보 조회 						
	   	case 2 : workView(); break;									//	 2. 근태 조회
	   	case 3 : salMain();  break;									//	 3. 급여 조회
	   	case 4 : revView();  break;									//   4. 인사고과 조회
	   	case 5 : new HRSystemMain().pwChange(hrsysVO); break; 		//   5. 비밀번호 변경 
	   	case 6 : new HRSystemMain().pwReset(hrsysVO); break;  		//   6. 비밀번호 초기화"
	   	case 7 : new HRSystemMain().sysEnd();						//   7. 시스템 종료
	   	default :
	   		System.out.println("--------------------------------");
	   		System.out.println("     1번 ~ 7번을 선택해주세요.  ");
	   		System.out.println("--------------------------------");
	   		System.out.println();
	   		pmReader();												// 다시 조회 메뉴
	   	}  
	   	
	} //  pmReader end
	
	// 직원 조회 메뉴
	public void emView() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       직원 조회 메뉴      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 직원 전체 목록  2. 히스토리 목록");
		System.out.println("  3. 메인 메뉴                       ");  
		System.out.println();
		System.out.print("  💡 선택(숫자 입력) >> ");
		int input = sc.nextInt();
		System.out.println();
	    
	    switch (input) {
	    case 1:
	    	emList();
	    	break;
	    case 2:
	    	hisAllList();
	    	break;
	    case 3:
	    	pmReader();
	    	break;
	    default:
			System.out.println("--------------------------------");
		   	System.out.println("     1번 ~ 3번을 선택해주세요.  ");
		   	System.out.println("--------------------------------");
		   	System.out.println();
	    emView();
	    }
		
		
	} // emView end
	
	// 직원 전체 목록 조회
	public void emList() {
		System.out.println("  ╔═══════════════════════════╗"); 
		System.out.println("  ║       직원 전체 목록      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		
		List <EmployeeVO> list = edao.emSelect();   //EmployeeVO에 들어있는 객체들 중에서 select한 edao를 List화 하여 list라고 이름붙임.
		
		System.out.println("────────────────────────────────────────────────────────");
        System.out.println(" 직원번호 | 직원이름 | 성별 | 부서이름 | 직급 ");
        System.out.println("────────────────────────────────────────────────────────");
        if (list.size() > 0) {
        	for(EmployeeVO evo: list) {   //EmployeeVO evo 는 list의 데이터타입임. list를 한줄씩 뿌리고, 이것을 반복!
        		System.out.println(" " + evo.getEmid() + " |  " + evo.getName() + " | " + evo.getGen() + " |  " + evo.getDname() + "  | " + evo.getPosition() );
                System.out.println("────────────────────────────────────────────────────────");
        	}
        } // if end
        
		System.out.println();
		System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");
		System.out.println();
		System.out.println(" 1. 부서별 조회  2. 직급별 조회");
		System.out.println(" 3. 개별 조회    4. 직원조회 메뉴");
	    System.out.println();
	    System.out.print("  💡 선택(숫자 입력) >>  ");
	    int input = sc.nextInt();
	    System.out.println();
	    		
	    switch (input) {
	    case 1:
	    	emList1();
	    	break;
	    case 2:
	    	emList2();
	    	break;
	    case 3:
	    	emDetail(id);
	    	break;
	    case 4:
	    	emView();
	    	break;
	    default: 
	    	System.out.println("--------------------------------");
		   	System.out.println("    1번 ~ 4번을 선택해주세요.   ");
		   	System.out.println("--------------------------------");
		   	emList();
	    }
	  } // emList() end
	    
	// 부서별 직원 조회    
	public void emList1() {
		System.out.println("  ╔═══════════════════════════╗");  
		System.out.println("  ║      부서별 직원 조회     ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 생산팀(D1)      2. 인사팀(D2)");
		System.out.println("  3. 품질관리팀(D3)  4. 경리팀(D4) ");
		System.out.println();
		System.out.print("  💡 선택(숫자 입력) >>  ");
		
	    String dn = "";
	    int input = sc.nextInt();

	    if(input == 1) {
	       dn = "생산팀";
	       list = edao.emSelectP(dn);
	    } else if(input == 2) {
	       dn = "인사팀";
	       list = edao.emSelectP(dn);
	    } else if(input == 3) {
	       dn = "품질팀";
	       list = edao.emSelectP(dn);
	    } else if(input == 4) {
	       dn = "경리팀";
	       list = edao.emSelectP(dn);
	    }
	    

        System.out.println();
		System.out.println("─────────────────────────────────────────────────────");
        System.out.println(" 직원번호 | 직원이름 | 부서이름 | 직급 ");
        System.out.println("─────────────────────────────────────────────────────");
        if(list.size() > 0) {
        	for(EmployeeVO evo: list) {
        		System.out.println(" " + evo.getEmid() +   " | "  + evo.getName()  +  "  |  "  + evo.getDname() + "  | " + evo.getPosition());
        		System.out.println("─────────────────────────────────────────────────────");
        	}
        }
        
		System.out.println();
        System.out.println();
        System.out.println("  1. 개별 조회  2. 직원 조회 메뉴"); 
        System.out.println();
        System.out.print("  💡 선택(숫자 입력) >> ");
        input = sc.nextInt();
        System.out.println();

        switch (input) {
	    case 1:
	    	emDetail(null);
	    	break;
	    case 2:
	    	pmReader();
	    	break;
	    default: 
	    	System.out.println("--------------------------------");
		   	System.out.println("   1번이나 2번을 선택해주세요.  ");
		   	System.out.println("--------------------------------");
		   	System.out.println();
	    emList1();
	    }	
		
	} // emList1() end

	// 직급별 직원 조회
	public void  emList2() {
		System.out.println("  ╔═══════════════════════════╗");  
		System.out.println("  ║      직급별 직원 조회     ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 사원   2. 대리");
		System.out.println("  3. 과장   4. 부장");
		System.out.println();
		System.out.print("  💡 선택(숫자 입력) >>  ");
		
		String gr;
        int input = sc.nextInt();
//        List<EmployeeVO> list;
//        list = null;
        
		System.out.println();

        if(input == 1) {
        	gr = "사원";
 	       list = edao.emSelectD(gr);
 	    } else if(input == 2) {
 	    	gr = "대리";
 	       list = edao.emSelectD(gr);
 	    } else if(input == 3) {
 	       gr = "과장";
 	       list = edao.emSelectD(gr);
 	    } else if(input == 4) {
 	       gr = "부장";
 	       list = edao.emSelectD(gr);
 	    }
        
//	        switch (input) {
//		    case 1:
//		    	gr = "사원";
//		    	list = edao.emSelectP(gr);
//		    	break;
//		    case 2:
//		    	gr = "대리";
//		    	list = edao.emSelectP(gr);
//		    	break;
//		    case 3:
//		    	gr = "과장";
//		    	list = edao.emSelectP(gr);
//		    	break;
//		    case 4:
//		    	gr = "부장";
//		    	list = edao.emSelectP(gr);
//		    	break;
//		    default: System.out.println(" 1~4번을 입력하세요. ");
//		    emList1();
//		    }
	        
		System.out.println("────────────────────────────────────────────────────────");
        System.out.println(" 직원번호 | 직원이름  |   부서이름  | 직급 ");
        System.out.println("────────────────────────────────────────────────────────");
        
        if(list.size() > 0) {
        	for(EmployeeVO evo: list) {
                System.out.println(" " + evo.getEmid() + " | " + evo.getName() + "    | " + evo.getDname() + "     | " + evo.getPosition() );
                System.out.println("────────────────────────────────────────────────────────");
        	}	
        }
        System.out.println();
        System.out.println("  1. 개별 조회  2. 직원 조회 메뉴"); 
        System.out.println();
        System.out.print("  💡 선택(숫자 입력) >> ");
		int qna = sc.nextInt();
        System.out.println();
        
		switch (qna) {
	    case 1:
	    	emDetail(id);
	    	break;
	    case 2:
	    	pmReader();
	    	break;
	    default:
	    	System.out.println("--------------------------------");
			System.out.println("    1번이나 2번을 선택해주세요. ");
			System.out.println("--------------------------------");
			System.out.println();
	    	emList1();
	    }
		
	} // emList2() end
	
	// 직원 개별 조회
	public void emDetail(String emid) {
		
		if (hrsys.sysid.equals("admin")) {   // 인사팀 관리자
			System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
			System.out.println("  ║      직원 개별 조회       ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.print("  💡 직원번호를 입력하세요 >>  ");
			  id = sc.next();
		      emid = id;
		      EmployeeVO evo = edao.emSelect(id);
		      System.out.println();
	
		      if(evo != null) {      
		         System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────");
		           System.out.println(" 직원번호 | 직원이름 | 성별 |   생년월일   |   연락처        |   입사일자   | 부서이름 | 직급 | 주소 ");
		           System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────");
		           System.out.println(" " + evo.getEmid() + " | " + evo.getName() + "  | " + evo.getGen() + " | " + evo.getBirth() + " | " + evo.getTel() + " | " + evo.getJoin() + " | " + evo.getDname() + " | " + evo.getPosition() + " | " + evo.getAddr() );
		           System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────");
		   
		         System.out.println("  1. 정보 수정  2. 정보 삭제  3. 직원 조회 메뉴");
		         System.out.println();
		         System.out.print("  💡 선택(숫자 입력) >> ");
		         int input = sc.nextInt();
		         System.out.println();
		   
		         HrAdminMain hra = new HrAdminMain();
		         
		         switch (input) {
		          case 1:
		             hra.emModify(emid);
		             break;
		          case 2:
		             hra.emRemove(emid);
		             break;
		          case 3:
		             pmReader();
		             break;
		          default:
		        	  	System.out.println("--------------------------------");
						System.out.println("     1번 ~ 3번을 선택해주세요.  ");
						System.out.println("--------------------------------");
						System.out.println();
		             emDetail(emid);
		         }
		      } else {
		    	  	System.out.println("---------------------------------");
					System.out.println("   존재하지 않는 직원번호입니다. ");
					System.out.println("---------------------------------");
					System.out.println();
		         emDetail(emid);
		      }

		}else if(hrsys.sysid.equals("admin2")) {
			System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
			System.out.println("  ║      직원 개별 조회       ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.print("  💡 직원번호를 입력하세요 >>  ");
			  id = sc.next();
		      emid = id;
		      EmployeeVO evo = edao.emSelect(id);
		      System.out.println();

		      if(evo != null) {      
		    	  System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────");
		           System.out.println(" 직원번호 | 직원이름 | 성별 |   생년월일   |   연락처        |   입사일자   | 부서이름 | 직급 | 주소 ");
		           System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────");
		           System.out.println(" " + evo.getEmid() + " | " + evo.getName() + "  | " + evo.getGen() + " | " + evo.getBirth() + " | " + evo.getTel() + " | " + evo.getJoin() + " | " + evo.getDname() + " | " + evo.getPosition() + " | " + evo.getAddr() );
		           System.out.println("─────────────────────────────────────────────────────────────────────────────────────────────"); 
		           System.out.println();
		           System.out.println("--------------------------------");
		           System.out.println("     조회 메뉴로 이동합니다.    ");
				   System.out.println("--------------------------------");
				   System.out.println();
		           emView();
		      }	
			
		} // if end
	} // emDetail(String emid) end
		
	
	// 히스토리 전체 목록
	public void hisAllList() {
		System.out.println("  ╔═══════════════════════════╗");  
		System.out.println("  ║     히스토리 전체 목록    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		List<HistoryVO> list = hdao.hisSelect();		// list에 히스토리 select한 데이터 담기
		
		if (list.size() > 0) { 				// list의 사이즈가 0보다 크면 즉, list에 값이 들어있으면 목록 조회	
		System.out.println("─────────────────────────────────────────────────");
		System.out.println(" 직원번호 | 부서이름 | 직급이름 |   구분    ");
		System.out.println("─────────────────────────────────────────────────");
		
			for (HistoryVO hvo : list) { 	// list를 끝까지 반복
				System.out.println("  " + hvo.getEmid() + " |  " + hvo.getDname() + "  | " 
						+ hvo.getPosition() + "  | " + hvo.getRemark());
				System.out.println("────────────────────────────────────────────────");
			}
			System.out.println();
			System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");
			System.out.println();
			System.out.println("  1. 개별 조회  2. 직원조회 메뉴");
			System.out.println();
			System.out.print("  💡 선택(숫자 입력) >>  ");
			
			int input = sc.nextInt(); 	// 번호 선택 입력값
			switch (input) {
			case 1: 					// 1 입력 시 히스토리 개별 조회
				hisList(id);			// 직원번호 자리에 들어갈 공유변수 id 사용
				break;
			case 2: 					// 2 입력 시 직원 조회 메뉴로 돌아감
				emView();
			} // switch end
			System.out.println();
		} else {							// list에 값이 없으면 메시지 출력 후 직원 조회 메뉴로 돌아감
			System.out.println("--------------------------------");
	   		System.out.println("        데이터가 없습니다.      ");
	   		System.out.println("--------------------------------");
	   		emView();
		}
		
	} //hisAllList() end
	
	// 한 명의 히스토리 목록 조회
	public void hisList(String emid) {
		System.out.println("  ╔═══════════════════════════╗");  
		System.out.println("  ║     히스토리 개별 조회    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  💡 직원번호를 입력하세요 >>  ");
		emid = sc.next(); 							 	// 개별 조회할 직원 번호 입력받고
		List<HistoryVO> list = hdao.hisSelect(emid); 	// select()메소드 호출해서 반환되는 값을 저장한 후 화면에 표시
		System.out.println();

		if (list.size() > 0) { 			 			 	// list에 값이 들어있으면 목록 조회
			System.out.println("────────────────────────────────────────────────────────────────────────");
			System.out.println(" NO.| 직원번호 | 직원이름 | 부서이름 | 직급이름 | 시작일자  |  종료일자  |  구분    ");
			System.out.println("─────────────────────────────────────────────────────────────────────────");
			for (HistoryVO hvo : list) { 			 	// list를 끝까지 반복
				System.out.println("  " + hvo.getHno() + "  | " + hvo.getEmid() + " |  " + hvo.getName() + "  | "
						+ hvo.getDname() + " |   " + hvo.getPosition() + " |   " + hvo.getLeaveStart() + "    |  " + hvo.getLeaveFin() + "   | "
						+ hvo.getRemark());
				System.out.println("──────────────────────────────────────────────────────────────────────────────────");
			}
			hisDetail();
		} else {										 // list에 값이 없으면 메시지 출력 후 전체 목록으로 돌아감
			System.out.println("---------------------------------");
			System.out.println("   존재하지 않는 직원번호입니다. ");
			System.out.println("---------------------------------");
			System.out.println();
			hisAllList();
		}
		
	} // hisList(String emid) end
	
	// 한 명의 히스토리 목록 중 한 개의 행 상세 조회
	public void hisDetail() {
		if (hrsys.sysid.equals("admin")) {   // 인사팀 관리자
			System.out.print("  💡 조회할 번호 선택(숫자 입력) >>  ");
			no = sc.nextInt();		// 조회할 히스토리번호 입력받기
			HistoryVO hvo = hdao.hisSelect(no);	// 입력받은 번호의 행만 select해서 hvo에 담기
			System.out.println();
			
			System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
			System.out.println("  ║     히스토리 상세 조회    ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			if (hvo != null) {		// hvo에 데이터가 들어있으면 조회
				System.out.println("────────────────────────────────────────────────────────────────────────");
				System.out.println(" NO.| 직원번호 | 직원이름 | 부서이름 | 직급이름 | 시작일자  |  종료일자  | 구분   ");
				System.out.println("─────────────────────────────────────────────────────────────────────────");
				System.out.println("  " + hvo.getHno() + " | " + hvo.getEmid() + " |  " + hvo.getName() + "  | "
						+ hvo.getDname() + " |   " + hvo.getPosition() + " |   " + hvo.getLeaveStart() + "    |  " + hvo.getLeaveFin() + "   | "
						+ hvo.getRemark());
				System.out.println("──────────────────────────────────────────────────────────────────────────────────");
				System.out.println();
				System.out.println("  1. 정보 수정  2. 정보 삭제  3. 직원 조회 메뉴");
				System.out.println();
				System.out.print("  💡 선택(숫자 입력) >> ");
				
				int num = sc.nextInt(); // 세부 메뉴 번호 받기
				HrAdminMain hra = new HrAdminMain();
				switch (num) {
				case 1:					// 1 입력 시 HrAdminMain에 있는 historyModify() 실행
					hra.historyModify();
					break; 
				case 2:					// 2 입력 시 HrAdminMain에 있는 historyRemove() 실행
					hra.historyRemove(no);
					break; 
				case 3:					// 3 입력 시 직원 조회 메뉴로 돌아감
					emView();
					break; 
				default: 				// 다른 값 입력 시 메시지 출력 후 다시 조회 메뉴 메서드 호출
					System.out.println();
					System.out.println("--------------------------------");
					System.out.println("     1번 ~ 3번을 선택해주세요.  ");
					System.out.println("--------------------------------");
					System.out.println();
					hisDetail();
				} // switch end
			} // if end
				}else if(hrsys.sysid.equals("admin2")) {   // 인사팀 열람자	
					System.out.print("  💡 조회할 번호 선택(숫자 입력) >> ");
					no = sc.nextInt();		// 조회할 히스토리번호 입력받기
					HistoryVO hvo = hdao.hisSelect(no);	// 입력받은 번호의 행만 select해서 hvo에 담기
					System.out.println();
					
					System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
					System.out.println("  ║     히스토리 상세 조회    ║");
					System.out.println("  ╚═══════════════════════════╝");
					System.out.println();
					if (hvo != null) {		// hvo에 데이터가 들어있으면 조회
						System.out.println("────────────────────────────────────────────────────────────────────────");
						System.out.println(" NO.| 직원번호 | 직원이름 | 부서이름 | 직급이름 | 시작일자  |  종료일자  | 구분   ");
						System.out.println("─────────────────────────────────────────────────────────────────────────");
						System.out.println("  " + hvo.getHno() + " | " + hvo.getEmid() + " |  " + hvo.getName() + "  | "
								+ hvo.getDname() + " |   " + hvo.getPosition() + " |   " + hvo.getLeaveStart() + "    |  " + hvo.getLeaveFin() + "   | "
								+ hvo.getRemark());
						System.out.println("──────────────────────────────────────────────────────────────────────────────────");
						System.out.println();
						System.out.println("--------------------------------");
				        System.out.println("     조회 메뉴로 이동합니다.    ");
						System.out.println("--------------------------------");
						System.out.println();
						emView();
					}
				} //큰 if end
	} // hisDetail() end
			
	
	// 근태에 관련된 내용 조회 메뉴
	public void workView() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║       근태 조회 메뉴      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 근태 기록 목록  ");
	    System.out.println("  2. 연차 승인 내역 목록");
		System.out.println("  3. 메인 메뉴                        ");  
		System.out.println();
		System.out.println("  💡 선택(숫자 입력) >> ");
		int input = sc.nextInt(); // 번호 선택 입력값
		switch (input) {
		case 1: 				// 1 입력 시 근태 기록 전체 목록
			workList();
			break;
		case 2: 				// 2 입력 시 연차 승인 내역 전체 목록
			yearAllList();
			break;
		case 3: 				// 3 입력 시 메인 메뉴로 돌아감
			pmReader();
			break;
		default: 				// 다른 값 입력 시 메시지 출력 후 다시 근태 조회 메뉴 메서드 호출
			System.out.println();
			System.out.println("--------------------------------");
			System.out.println("     1번 ~ 3번을 선택해주세요.  ");
			System.out.println("--------------------------------");
			System.out.println();
			workView();
		} // switch end
	
		
	} // workView() end
	
	// // 모든 직원의 근태 기록 목록
	public void workList() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║     근태기록 전체 목록    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		
		List<WorkVO> list = wdao.WorkSelect();
		if (list.size() > 0) { 			// list의 사이즈가 0보다 크면 즉, list에 값이 들어있으면 목록 조회
			System.out.println("─────────────────────────────────────────────────────────");
			System.out.println(" 직원번호 | 직원이름 | 지각일수 | 조퇴일수 | 결근일수 | 잔여연차일수  ");
			System.out.println("─────────────────────────────────────────────────────────");
			for (WorkVO wvo : list) { 	// list를 끝까지 반복
				System.out.println("  " + wvo.getEmid() + " | " + wvo.getName() + "  |   " + wvo.getLate() + "   |   "
						+ wvo.getEarly() + "    |   " + wvo.getAbs() + "   |   " + wvo.getUnusedAnn());
				System.out.println("─────────────────────────────────────────────────────────");
			} // for end
		} else {						// list에 값이 없으면 메시지 출력 후 근태 조회 메뉴로 돌아감
			System.out.println("--------------------------------");
   			System.out.println("        데이터가 없습니다.       ");
   			System.out.println("--------------------------------");
   			workView(); 
   		}	// if end
		System.out.println();
		System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");
		System.out.println();
		System.out.println("  1. 개별 조회  2. 근태조회 메뉴");
		System.out.println();
		System.out.print("  💡 선택(숫자 입력) >> ");

		int input = sc.nextInt(); 	// 번호 선택 입력값
		switch (input) {
		case 1: 					// 1 입력 시 근태 기록 개별 조회
			workDetail();
			break;
		case 2: 					// 2 입력 시 근태 조회 메뉴로 돌아감
			workView();
		} // switch end
		System.out.println();
		  
	} // workList() end
	
	// // 한 명의 근태 기록 조회
	public void workDetail() {
		if (hrsys.sysid.equals("admin")) {   // 인사팀 관리자
			System.out.println("  ╔═══════════════════════════╗");  
			System.out.println("  ║    근태 기록 개별 조회    ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.println("  💡 직원번호를 입력하세요 >>  ");
			id = sc.next(); 					// 개별 조회할 직원 번호 입력받고
			WorkVO wvo = wdao.WorkSelect(id); 	// 입력받은 번호의 데이터들을 select해서 wvo로 보내기
			System.out.println();
	
			if (wvo != null) {					// wvo에 데이터가 있으면 조회
				System.out.println("──────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" 직원번호 | 직원이름 | 근속년수 | 지각일수 | 조퇴일수 | 결근일수 | 사용가능연차일수 | 사용연차일수 | 잔여연차일수                ");
				System.out.println("──────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" " + wvo.getEmid() + " |  " + wvo.getName() + "  |   " + wvo.getCont() + "   |   " 
				+ wvo.getLate() + "   |   "
						+ wvo.getEarly() + "    |   " + wvo.getAbs() + "   |      " + wvo.getAvAnn() + "      |     "
						+ wvo.getUsedAnn() + "     |    " + wvo.getUnusedAnn());
				System.out.println("──────────────────────────────────────────────────────────────────────────────────────");
				System.out.println();
				System.out.println("  1. 근태 기록 수정  2. 근태 기록 삭제  3. 근태 조회 메뉴");
				System.out.println();
				System.out.print("  💡 선택(숫자 입력) >> ");
				int num = sc.nextInt(); // 세부 메뉴 번호 받기
				HrAdminMain hra = new HrAdminMain();	// 밑의 메서드를 호출하기 위한 객체
				switch (num) {
				case 1:				// 1 입력 시 HrAdminMain에 있는 workModify() 실행
					hra.workModify();
					break; 		
				case 2:				// 2 입력 시 HrAdminMain에 있는 workRemove() 실행
					hra.workRemove(id);
					break; 		
				case 3:				// 3 입력 시 근태 조회 메뉴로 돌아감
					workView();
					break; 		
				default: 			// 다른 값 입력 시 메시지 출력 후 다시 조회 메뉴 메서드 호출
					System.out.println();
					System.out.println("--------------------------------");
					System.out.println("     1번 ~ 3번을 선택해주세요.  ");
					System.out.println("--------------------------------");
					System.out.println();
					workDetail();
				} // switch end
			} else {				// wvo에 데이터가 없으면 메시지 출력 후 근태 기록 전체 목록으로 돌아감
				System.out.println("--------------------------------");
				System.out.println("  존재하지 않는 직원번호입니다. ");
		   		System.out.println("--------------------------------");
				System.out.println();
				workList();
			}	// if end
			System.out.println();
		}else if(hrsys.sysid.equals("admin2")) {   // 인사팀 열람자
			System.out.println("  ╔═══════════════════════════╗");  
			System.out.println("  ║    근태 기록 개별 조회    ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.println("  💡 직원번호를 입력하세요 >>  ");
			id = sc.next(); 					// 개별 조회할 직원 번호 입력받고
			WorkVO wvo = wdao.WorkSelect(id); 	// 입력받은 번호의 데이터들을 select해서 wvo로 보내기
			System.out.println();
	
			if (wvo != null) {					// wvo에 데이터가 있으면 조회
				System.out.println("──────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" 직원번호 | 직원이름 | 근속년수 | 지각일수 | 조퇴일수 | 결근일수 | 사용가능연차일수 | 사용연차일수 | 잔여연차일수                ");
				System.out.println("──────────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" " + wvo.getEmid() + " |  " + wvo.getName() + "  |   " + wvo.getCont() + "   |   " 
				+ wvo.getLate() + "   |   "
						+ wvo.getEarly() + "    |   " + wvo.getAbs() + "   |      " + wvo.getAvAnn() + "      |     "
						+ wvo.getUsedAnn() + "     |    " + wvo.getUnusedAnn());
				System.out.println("──────────────────────────────────────────────────────────────────────────────────────");
				System.out.println();
				System.out.println("--------------------------------");
		        System.out.println("     조회 메뉴로 이동합니다.    ");
				System.out.println("--------------------------------");
				System.out.println();
				workView();
			}
		}
	} // workDetail(String emid) end

	// 모든 직원의 연차 승인 내역 목록
	public void yearAllList() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║    연차 승인 내역 목록    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		List<YearVO> list = ydao.yearSelect();	// list에 YearDAO에서 select한 데이터를 불러옴

		if (list.size() > 0) { 			// list의 사이즈가 0보다 크면 즉, list에 값이 들어있으면 목록 조회
			System.out.println("────────────────────────────────────────────────");
			System.out.println("  NO | 직원번호 | 직원이름 | 연차승인자 | 연차승인일자");
			System.out.println("────────────────────────────────────────────────");
			for (YearVO yvo : list) { 	// list를 끝까지 반복
				System.out.println("  " + yvo.getYno() + "  | " + yvo.getEmid() + " |  " + yvo.getName() + "  |  "
						+ yvo.getApp() + "  | " + yvo.getAppDate());
				System.out.println("────────────────────────────────────────────────");
			}
		System.out.println();
		System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");
		System.out.println();
		System.out.println("  1. 개별 조회  2. 근태 조회 메뉴");
		System.out.println();
		System.out.print("  💡 선택(숫자 입력) >> ");
		System.out.println();

		int input = sc.nextInt(); 	// 번호 선택 입력값
		switch (input) {
		case 1: 					// 1 입력 시 연차 승인 내역 개별 조회
			yearList(id);
			break;
		case 2: 					// 2 입력 시 근태 조회 메뉴로 돌아감
			workView();
		} // switch end
		
		} else {					// list에 값이 없으면 메시지 출력 후 근태 조회 메뉴로 돌아감
			System.out.println("--------------------------------");
			System.out.println("        데이터가 없습니다.       ");
			System.out.println("--------------------------------");
			workView();
		}	// if end
		System.out.println();  
		
	} //  yearAllList() end
	
	// 한 명의 연차 승인 내역 조회
	public void yearList(String emid) {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║    연차 승인 내역 조회    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.print("  💡 직원번호를 입력하세요 >>  ");
		emid = sc.next(); 							// 개별 조회할 직원 번호 입력받고
		List<YearVO> list = ydao.yearSelect(emid); 	// select()메소드 호출해서 반환되는 값을 저장한 후 화면에 표시
		System.out.println();
		
		if (list.size() > 0) { 			// list의 사이즈가 0보다 크면 즉, list에 값이 들어있으면 목록 조회
			System.out.println("──────────────────────────────────────────────────────────────────────────────────");
			System.out.println(" NO  | 직원번호 | 직원이름 | 연차사용일자 | 연차사용일수 | 연차승인자 | 연차승인일자");
			System.out.println("──────────────────────────────────────────────────────────────────────────────────");
			for (YearVO yvo : list) { // list를 끝까지 반복
				System.out.println("  " + yvo.getYno() + "  | " + yvo.getEmid() + " |  " + yvo.getName() + "  | "
						+ yvo.getYearDate() + " |   " + yvo.getYearCnt() + "    |  " + yvo.getApp() + "   | "
						+ yvo.getAppDate());
				System.out.println("──────────────────────────────────────────────────────────────────────────────────");
			}
			yearDetail();				// 개별 조회 후 상세조회 메서드로 넘어감
		} else {						// list에 값이 없으면 메시지 출력 후 연차승인내역 전체 목록으로 돌아감
			System.out.println("--------------------------------");
			System.out.println("  존재하지 않는 직원번호입니다. ");
	   		System.out.println("--------------------------------");
			System.out.println();
			yearAllList();
		}

	} // yearList(String emid) end
	
	// 개인 연차 승인 내역 중 상세 조회
	public void yearDetail() {
		if (hrsys.sysid.equals("admin")) {   // 인사팀 관리자
			System.out.println();
			System.out.print("  💡 조회할 번호 선택(숫자 입력) >> ");
			no = sc.nextInt();			// 조회할 연차승인번호 입력
			YearVO yvo = ydao.yearSelect(no);	// 번호에 맞는 한 행을 select해서 yvo에 담기
			System.out.println();
			
			System.out.println("  ╔════════════════════════════╗");  // 2칸씩
			System.out.println("  ║  연차 승인 내역 상세 조회  ║");
			System.out.println("  ╚════════════════════════════╝");
			System.out.println();
			if (yvo != null) {			// yvo에 값이 있으면 데이터 조회
				System.out.println("──────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" NO | 직원번호 | 직원이름 | 연차사용일자 | 연차사용일수 | 연차승인자 | 연차승인일자");
				System.out.println("──────────────────────────────────────────────────────────────────────────────────");
				System.out.println("  " + yvo.getYno() + " | " + yvo.getEmid() + " |  " + yvo.getName() + "  | "
						+ yvo.getYearDate() + " |   " + yvo.getYearCnt() + "    |  " + yvo.getApp() + "   | "
						+ yvo.getAppDate());
				System.out.println("──────────────────────────────────────────────────────────────────────────────────");
				System.out.println();
				System.out.println("  1. 연차 승인 내역 수정  2. 연차 승인 내역 삭제  3. 근태 조회 메뉴");
				System.out.println();
				System.out.print("  💡 선택(숫자 입력) >> ");
	
				int num = sc.nextInt(); // 세부 메뉴 번호 받기
				HrAdminMain hra = new HrAdminMain();	// 밑의 메서드를 사용하기 위한 객체 생성
				switch (num) {
				case 1:					// 1 입력 시 HrAdminMain에 있는 yearModify() 실행
					hra.yearModify();
					break; 
				case 2:					// 2 입력 시 HrAdminMain에 있는 yearRemove() 실행
					hra.yearRemove(no);
					break; 
				case 3:					// 3 입력 시 근태 조회 메뉴로 돌아감
					workView();
					break; 
				default: 				// 다른 값 입력 시 메시지 출력 후 다시 조회 메뉴 메서드 호출
					System.out.println();
					System.out.println("--------------------------------");
					System.out.println("     1번 ~ 3번을 선택해주세요.  ");
					System.out.println("--------------------------------");
					System.out.println();
					workDetail();
				} // switch end
			}	// if end
		}else if(hrsys.sysid.equals("admin2")) {  // 인사팀 열람자
			System.out.println();
			System.out.print("  💡 조회할 번호 선택(숫자 입력) >> ");
			no = sc.nextInt();			// 조회할 연차승인번호 입력
			YearVO yvo = ydao.yearSelect(no);	// 번호에 맞는 한 행을 select해서 yvo에 담기
			System.out.println();
			
			System.out.println("  ╔════════════════════════════╗");  // 2칸씩
			System.out.println("  ║  연차 승인 내역 상세 조회  ║");
			System.out.println("  ╚════════════════════════════╝");
			System.out.println();
			if (yvo != null) {			// yvo에 값이 있으면 데이터 조회
				System.out.println("──────────────────────────────────────────────────────────────────────────────────");
				System.out.println(" NO | 직원번호 | 직원이름 | 연차사용일자 | 연차사용일수 | 연차승인자 | 연차승인일자");
				System.out.println("──────────────────────────────────────────────────────────────────────────────────");
				System.out.println("  " + yvo.getYno() + " | " + yvo.getEmid() + " |  " + yvo.getName() + "  | "
						+ yvo.getYearDate() + " |   " + yvo.getYearCnt() + "    |  " + yvo.getApp() + "   | "
						+ yvo.getAppDate());
				System.out.println("──────────────────────────────────────────────────────────────────────────────────");
				System.out.println();
				System.out.println("--------------------------------");
		        System.out.println("     조회 메뉴로 이동합니다.    ");
				System.out.println("--------------------------------");
				System.out.println();
				workView();
			}
		}

	} // yearDetail() end
	
	
	// 급여 조회 메뉴
	public void salMain() {
		if (hrsys.sysid.equals("admin") || hrsys.sysid.equals("admin2")) {   // 인사팀 관리자
			System.out.println("  ╔═══════════════════════════╗");  
			System.out.println("  ║       급여 조회 메뉴      ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.println("  1.급여 정보 전체 목록");
			System.out.println("  2.급여 지급 내역 전체 목록");
			System.out.println("  3.메인메뉴");                     
			System.out.println();
			System.out.print("  💡 선택(숫자 입력) >>  ");
			input = sc.nextInt();
			switch (input) {
			case 1:
				salInfoList(); //급여 정보 전체 목록 호츌
				break;
			case 2:
				salPayAllList(); //급여 지급 내역 전체 목록 호출
				break;
			case 3:
				pmReader(); //인사팀 조회 메뉴로 재귀
				break;
			default:
				System.out.println("--------------------------------");
				System.out.println("     1번 ~ 3번을 선택해주세요.  ");
				System.out.println("--------------------------------");
				System.out.println();
				salMain(); // 급여 조회 메뉴 재출력
				break;
			}// END switch()
		}else if(hrsys.sysid.equals("admin3")) {   // 경리팀 관리자
			System.out.println("  ╔═══════════════════════════╗");  
			System.out.println("  ║       급여 조회 메뉴      ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.println("  1.급여 정보 전체 목록");
			System.out.println("  2.급여 지급 내역 전체 목록");
			System.out.println("  3.급여 관리 메뉴");
			System.out.println("  4.메인메뉴");                     
			System.out.println();
			System.out.print("  💡 선택(숫자 입력) >>  ");
			input = sc.nextInt();
			System.out.println();
			
			switch (input) {
			case 1:
				salInfoList(); //급여 정보 전체 목록 호츌
				break;
			case 2:
				salPayAllList(); //급여 지급 내역 전체 목록 호출
				break;
			case 3: new AccountAdminMain().adManage();  // 급여 관리 메뉴로 이동
				break;
			case 4 :	pmReader();   //인사팀 조회 메뉴로 재귀
			default:
				System.out.println("--------------------------------");
				System.out.println("     1번 ~ 3번을 선택해주세요.  ");
				System.out.println("--------------------------------");
				System.out.println();
				salMain(); // 급여 조회 메뉴 재출력
				break;
			}// END switch()
		}
		
	}// salMain()end
	
	// 모든 직원의 급여 정보 전체 목록
	public void salInfoList() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║     급여정보 전체 목록    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		
		List<SalaryInfoVO> savoList = sdao.salSelect();
		if (savoList.size() > 0) {// list의 사이즈가 0보다 크면 즉, list에 값이 들어있으면 목록 조회
			System.out.println("───────────────────────────────────────────");
			System.out.println(" NO. | 직원번호 |  직원이름 | 기본급(호봉) ");
			System.out.println("───────────────────────────────────────────");
			for (SalaryInfoVO savo : savoList) { //list 끝까지 반복
				System.out.printf("  %d  |   %s  |   %s  |   %d%n" ,savo.getSno(), savo.getEmid(), savo.getName(), savo.getSal());
				System.out.println("───────────────────────────────────────────");		
			} // for end
			
			System.out.println();
			System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");
			System.out.println();
			System.out.println("   1. 개별 조회  2. 급여 조회 메뉴");
			System.out.println();
			System.out.print("  💡 선택(숫자 입력) >>  ");
			int input = sc.nextInt(); // 키보드에서 메뉴 선택 받기
			System.out.println();

			if (input == 1) {
				salInfoDetail(); // 급여정보 개별조회
			} else if (input == 2) {
				salMain(); // 급여 조회 메뉴로 회귀
			} else {
				System.out.println("---------------------------");
				System.out.println("      잘못된 입력입니다.   ");
				System.out.println("---------------------------");
				System.out.println();
			}
		} else {
			System.out.println("------------------------------------");
			System.out.println("    급여 정보가 존재하지 않습니다.  ");
			System.out.println("------------------------------------");
			System.out.println();
		}
		
	} // salInfoList() end
	
	
	// 한 명의 급여 정보 조회 
	public void salInfoDetail() { 
		if (hrsys.sysid.equals("admin3")) {   // 인사팀 관리자
			System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
			System.out.println("  ║     급여정보 개별 조회    ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.print("  💡 직원번호를 입력하세요 (D0000) >>  ");
			String emid = sc.next();
			SalaryInfoVO savo = sdao.salSelect(emid);
			System.out.println();
	
			if (savo != null) {
				System.out.println("───────────────────────────────────────────────────────────────────────────────");
				System.out.println(" NO. | 직원번호 |  직원이름 | 기본급(호봉) | 은행명 | 예금주 |    계좌번호 ");
				System.out.println("───────────────────────────────────────────────────────────────────────────────");
				System.out.printf("  %d  |   %s  |   %s  |    %d   |  %s  | %s |  %s%n"
						,savo.getSno(), savo.getEmid(), savo.getName(),savo.getSal(), savo.getBank(), savo.getDepositor(),  savo.getAccount());		
				System.out.println("───────────────────────────────────────────────────────────────────────────────");
			}
	
			System.out.println();
			System.out.println(" < 원하시는 메뉴를 선택해주세요. >");
			System.out.println();
			System.out.println("  1. 급여 정보 수정  2. 급여 정보 삭제  3. 급여 조회 메뉴");
			System.out.println();
			System.out.print("  💡 선택(숫자 입력) >>  ");
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
			
		}else if(hrsys.sysid.equals("admin") || hrsys.sysid.equals("admin2") ) {  // 인사팀 열람자
			System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
			System.out.println("  ║     급여정보 개별 조회    ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.print("  💡 직원번호를 입력하세요 (D0000) >>  ");
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
				System.out.println();
	            System.out.println("--------------------------------");
	            System.out.println("     조회 메뉴로 이동합니다.    ");
			    System.out.println("--------------------------------");
			    System.out.println();
				salMain();
				
			}
			
		}
	} // salInfoDetail()end
	
	
	// 모든 직원의 급여 지급 내역 목록
	public void salPayAllList() { 
			System.out.println("╔═════════════════════════════╗"); 
			System.out.println("║   급여 지급 내역 전체 목록  ║");
			System.out.println("╚═════════════════════════════╝");
			System.out.println();

			List<SalaryPaymentVO> spavoList = spdao.salPaySelect();
			if (spavoList.size() > 0) {
				System.out.println("─────────────────────────────────────────────────────────");
				System.out.println(" NO. |직원번호 |직원이름 | 지급일시 | 총급여 ");
				System.out.println("───-──────────────────────────────────────────────────────");
				for (SalaryPaymentVO spavo : spavoList) {
//				    spavo.calculateTotal(); // 총 급여 계산
				    System.out.printf(" %3d | %8s | %10s | %12s | %8d | \n",
				            spavo.getSpno(), spavo.getEmid(), spavo.getName(), spavo.getPayDate(), spavo.getTotal());
				}

				System.out.println("───────────────────────────────────────────");
				System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");
				System.out.println();
				System.out.println("  1. 개별 조회  2. 급여 조회 메뉴");
				System.out.println();
				System.out.print("  💡 선택(숫자 입력) >> ");
				int input = sc.nextInt(); //// 키보드에서 메뉴 선택 받기
				System.out.println();
//				sc.close(); // Scanner 닫기

				if (input == 1) { // 메인
					salPayList(); // 급여 지급 내역 개별조회
				} else if (input == 2) {
					salMain(); // 급여 조회 메뉴로 회귀
				} else {
					System.out.println("---------------------------");
					System.out.println("     잘못된 입력입니다.    ");
					System.out.println("---------------------------");
					System.out.println();
					salPayList();
				}
			} else {
				System.out.println("------------------------------------");
				System.out.println(" 급여 지급 내역이 존재하지 않습니다.");
				System.out.println("------------------------------------");
				System.out.println();
				salMain();
			}
		} // salPayAllList() end
	
	// 한 명의 급여 지급 내역 조회
	public void salPayList() {
	    System.out.println("  ╔═════════════════════════════╗");
	    System.out.println("  ║   급여 지급 내역 개별 조회	║");
	    System.out.println("  ╚═════════════════════════════╝");
	    System.out.println();
	    System.out.print("  💡 직원번호를 입력하세요 (D0000) >>  ");
	    String emid = sc.next();
	    System.out.println();
	    
	    List<SalaryPaymentVO> spvolist = spdao.salPaySelect(emid);

	    if (!spvolist.isEmpty()) {
	        System.out.println("─────────────────────────────────────────────────────────");
	        System.out.println(" NO. |직원번호 | 직원이름 | 지급일시 | 기본급(호봉) | 상여금 | 총급여 ");
	        System.out.println("─────────────────────────────────────────────────────────");
	        for (SalaryPaymentVO spavo : spvolist) {
	            System.out.printf(" %3d | %8s | %10s | %12s | %8d | %10d | %10d \n",
	                    spavo.getSpno(), spavo.getEmid(), spavo.getName(), spavo.getPayDate(), spavo.getBonus(), spavo.getSal(), spavo.getTotal());
	        }
	        System.out.println("───────────────────────────────────────────");
	        System.out.println();
	        System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");
	        System.out.println();
	        System.out.println("  1. 상세 조회  2. 급여 조회 메뉴");
	        System.out.println();
	        System.out.print("  💡 선택(숫자 입력) >>  ");
	        int input = sc.nextInt(); // 키보드에서 메뉴 선택 받기
	        System.out.println();
//		        sc.close();

	        if (input == 1) { // 메인으로 이동
	            salPayDetail(); // 급여 지급 내역 개별조회
	        } else if (input == 2) { // 급여 조회 메뉴
	            salMain();
	        } else {
	            System.out.println("--------------------------------");
	            System.out.println("     1번 ~ 2번을 선택해주세요.  ");
	            System.out.println("--------------------------------");
	            System.out.println();
	            salPayDetail();
	        }
	    } else {
	    	System.out.println("-------------------------------------------------");
	        System.out.println(" 해당 직원의 급여 지급 내역이 존재하지 않습니다. ");
	        System.out.println("-------------------------------------------------");
	        System.out.println();
	        salMain();
	    }
	} //salPayList();
	
	// 급여 지급 내역 상세 조회
		public void salPayDetail() {
			if (hrsys.sysid.equals("admin3")) {   // 인사팀 관리자
				System.out.print("  💡 조회할 상세 지급내역 번호를 입력하세요 >>  ");
			    int spno = sc.nextInt();
			    SalaryPaymentVO spavo = spdao.salPaySelect(spno);
			    System.out.println();
				System.out.println("  ╔═════════════════════════════╗"); 
				System.out.println("  ║  급여 지급 내역 상세 조회	║");
				System.out.println("  ╚═════════════════════════════╝");
				System.out.println();
			    
			    if (spavo != null) {
				System.out.println("──────────────────────────────────────────────────────");
				System.out.println(" NO. |직원번호 | 직원이름 | 지급일시 | 기본급(호봉) | 상여금 | 총급여 ");
				System.out.println("──────────────────────────────────────────────────────");
				 System.out.printf(" %3d | %8s | %10s | %12s | %8d | %10d | %10d \n",
			                spavo.getSpno(), spavo.getEmid(), spavo.getName(), spavo.getPayDate(), spavo.getBonus(), spavo.getSal(), spavo.getTotal());
				System.out.println("──────────────────────────────────────────────────────");
				System.out.println();
		        System.out.println("    < 메뉴를 선택해주세요. >");
		        System.out.println();
				System.out.println("   1. 급여 지급 내역 수정  2. 급여 지급 내역 삭제  3. 급여 조회 메뉴");
				System.out.println();
				System.out.print("  💡 선택(숫자 입력) >> ");
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
			    }
			}else if(hrsys.sysid.equals("admin") || hrsys.sysid.equals("admin2")) {  // 인사팀 열람자
				System.out.println("  ╔═════════════════════════════╗"); // 2칸씩
				System.out.println("  ║  급여 지급 내역 상세 조회	║");
				System.out.println("  ╚═════════════════════════════╝");
				System.out.println();
			    System.out.print("  💡 조회할 상세 지급내역 번호를 입력하세요 >>  ");
			    int spno = sc.nextInt();
			    SalaryPaymentVO spavo = spdao.salPaySelect(spno);
			    System.out.println();
			 

			    if (spavo != null) {
				System.out.println("──────────────────────────────────────────────────────");
				System.out.println(" NO. |직원번호 | 직원이름 | 지급일시 | 기본급(호봉) | 상여금 | 총급여 ");
				System.out.println("──────────────────────────────────────────────────────");
				 System.out.printf(" %3d | %8s | %10s | %12s | %8d | %10d | %10d \n",
			                spavo.getSpno(), spavo.getEmid(), spavo.getName(), spavo.getPayDate(), spavo.getBonus(), spavo.getSal(), spavo.getTotal());
				System.out.println("──────────────────────────────────────────────────────");
				System.out.println();
	            System.out.println("--------------------------------");
	            System.out.println("     조회 메뉴로 이동합니다.    ");
			    System.out.println("--------------------------------");
			    System.out.println();
				salMain();
			    }
				
			}
		}// salPayDetail()end	
	
		// 인사 조회메뉴
		public void revView() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║     인사 고과 조회 메뉴   ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  1. 평가기록 전체 목록");
		System.out.println("  2. 평가기록 개별 조회");
		System.out.println("  3. 메인메뉴");                     
		System.out.println();
		System.out.print(" 💡 선택(숫자 입력) >> ");
		int input = sc.nextInt();
		System.out.println();
		
	   	
	   	switch(input) {
	   	case 1 : revAllList();  break;  		//  1. 평가기록 전체 목록
	   	case 2 : revList();  break;				//  2. 평가기록 개별 조회
	   	case 3 : pmReader(); break;				//  3. 메인메뉴
	   	default: 
	   		System.out.println("--------------------------------");
	   		System.out.println("     1번 ~ 3번을 선택해주세요.  ");
	   		System.out.println("--------------------------------");
	   		System.out.println();	
	   		revView();
	   	} 

	} // revView end
	
		// 평가 전체 목록
		public void revAllList() {
		System.out.println("  ╔═══════════════════════════╗");  
		System.out.println("  ║     인사고과 전체 목록    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		
		List<ReviewVO> reviewList = rdao.revSelect();				// 전체 목록 출력
		
		if(reviewList.size() >0) {				
			System.out.println("──────────────────────────────────────────────────────────");
			System.out.println(" NO | 직원번호 | 부서번호 | 직급 | 평가등급 |  평가일시   ");
			System.out.println("──────────────────────────────────────────────────────────");
			
			for(ReviewVO list : reviewList) {
				System.out.printf("  %d |   %s  |    %s    | %s |     %s    | %s%n",list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getGrade(),list.getEvalDate());
				System.out.println("──────────────────────────────────────────────────────────");
			}
				System.out.println();			
				System.out.println(" < 상세조회가 필요하면 아래 메뉴를 선택해주세요. >");	
				System.out.println();
				System.out.println("    1. 부서별 조회  2. 직급별 조회");
				System.out.println("    3. 연도별 조회  4. 개별 조회");
				System.out.println("    5. 인사고과 조회 메뉴");
			    System.out.println();
			    System.out.print("    💡 선택(숫자 입력) >> ");
			    int input = sc.nextInt();
			    System.out.println();
			    
			    switch(input) {
			    case 1 : revAllList1(); break;						//  1. 부서별 조회
			    case 2 : revAllList2(); break;						//  2. 직급별 조회
			    case 3 : revAllList3(); break;						//  3. 연도별 조회
			    case 4 : revList(); break;							//  4. 개별 조회
			    case 5 : revView();  break;							//  5. 인사고과 조회 메뉴
			    default :
			    	System.out.println("--------------------------------");
			   		System.out.println("     1번 ~ 5번을 선택해주세요.  ");
			   		System.out.println("--------------------------------");
			   		System.out.println();	
			   		revAllList();
			    }
			
		}else {
			System.out.println("--------------------------------");
			System.out.println("     등록된 평가가 없습니다.    ");
			System.out.println("--------------------------------");
			System.out.println();
			revView();
				
			}
						
		} // revAllList end
		
	    // 부서
		public void revAllList1() {
			System.out.println("  ╔═══════════════════════════╗");   // switch 를 이용하여 입력값에 따라 리스트 다르게 출력
			System.out.println("  ║    부서별 인사고과 조회   ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.println("  1. 생산팀(D1)     2. 인사팀(D2)");
			System.out.println("  3. 품질관리팀(D3) 4. 경리팀(D4) ");
			System.out.println();
			System.out.print("  💡 선택(숫자 입력) >> ");
			int input = sc.nextInt();
			System.out.println();
			
			switch(input) {
			case 1 :
				List<ReviewVO> reviewList = rdao.revSelectD("D1");
				
				if(reviewList.size() >0) {
					System.out.println("──────────────────────────────────────────────────────────");
					System.out.println(" NO | 직원번호 | 부서번호 | 직급 | 평가등급 |  평가일시   ");
					System.out.println("──────────────────────────────────────────────────────────");
					
					for(ReviewVO list : reviewList) {
						System.out.printf("  %d |   %s  |    %s    | %s |     %s    | %s%n",list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getGrade(),list.getEvalDate());
						System.out.println("──────────────────────────────────────────────────────────");
					}
						System.out.println();
						System.out.println("  1. 개별 조회  2. 인사고과 조회 메뉴"); 						
				        System.out.println();  
				        System.out.print("  💡 선택(숫자 입력) >> ");
				        int num = sc.nextInt();
				        System.out.println();
				        
				        switch(num) {
				        	case 1 : revList(); break;
				        	case 2 : revView(); break;
				        	default : 
				        		System.out.println("--------------------------------");
						   		System.out.println("    1번이나 2번을 선택해주세요. ");
						   		System.out.println("--------------------------------");
						   		System.out.println();
						   		revAllList1();
				        }
					
				}
				break;
			case 2 :
				List<ReviewVO> reviewList1 = rdao.revSelectD("D2");
				
				if(reviewList1.size() >0) {
					System.out.println("──────────────────────────────────────────────────────────");
					System.out.println(" NO | 직원번호 | 부서번호 | 직급 | 평가등급 |  평가일시   ");
					System.out.println("──────────────────────────────────────────────────────────");
					
					for(ReviewVO list : reviewList1) {
						System.out.printf("  %d |   %s  |    %s    | %s |     %s    | %s%n",list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getGrade(),list.getEvalDate());
						System.out.println("──────────────────────────────────────────────────────────");
					}
					System.out.println();
					System.out.println("  1. 개별 조회  2. 인사고과 조회 메뉴"); 
			        System.out.println();
			        System.out.print("  💡 선택(숫자 입력) >> ");
			        int num = sc.nextInt();
			        System.out.println();
			        
			        switch(num) {
		        	case 1 : revList(); break;
		        	case 2 : revView(); break;
		        	default : 
		        		System.out.println("--------------------------------");
				   		System.out.println("    1번이나 2번을 선택해주세요. ");
				   		System.out.println("--------------------------------");
				   		System.out.println();
				   		revAllList1();
		        }
				}
				break;
			case 3 :
				List<ReviewVO> reviewList2 = rdao.revSelectD("D3");
				
				if(reviewList2.size() >0) {
					System.out.println("──────────────────────────────────────────────────────────");
					System.out.println(" NO | 직원번호 | 부서번호 | 직급 | 평가등급 |  평가일시   ");
					System.out.println("──────────────────────────────────────────────────────────");
					
					for(ReviewVO list : reviewList2) {
						System.out.printf("  %d |   %s  |    %s    | %s |     %s    | %s%n",list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getGrade(),list.getEvalDate());
						System.out.println("──────────────────────────────────────────────────────────");	
					}
						System.out.println();
						System.out.println("  1. 개별 조회  2. 인사고과 조회 메뉴"); 
				        System.out.println();
				        System.out.print("  💡 선택(숫자 입력) >> ");
				        int num = sc.nextInt();
				        System.out.println();
				        
				        switch(num) {
			        	case 1 : revList(); break;
			        	case 2 : revView(); break;
			        	default : 
			        		System.out.println("--------------------------------");
					   		System.out.println("    1번이나 2번을 선택해주세요. ");
					   		System.out.println("--------------------------------");
					   		System.out.println();
					   		revAllList1();
			        }
				}
				break;
			case 4 : 
				List<ReviewVO> reviewList3 = rdao.revSelectD("D3");
				
				if(reviewList3.size() >0) {
					System.out.println("──────────────────────────────────────────────────────────");
					System.out.println(" NO | 직원번호 | 부서번호 | 직급 | 평가등급 |  평가일시   ");
					System.out.println("──────────────────────────────────────────────────────────");
					
					for(ReviewVO list : reviewList3) {
						System.out.printf("  %d |   %s  |    %s    | %s |     %s    | %s%n",list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getGrade(),list.getEvalDate());
						System.out.println("──────────────────────────────────────────────────────────");
					}
						System.out.println();
						System.out.println("  1. 개별 조회  2. 인사고과 조회 메뉴"); 
				        System.out.println();
				        System.out.print("  💡 선택(숫자 입력) >> ");
				        int num = sc.nextInt();
				        System.out.println();
				        
				        switch(num) {
			        	case 1 : revList(); break;
			        	case 2 : revView(); break;
			        	default : 
			        		System.out.println("--------------------------------");
					   		System.out.println("    1번이나 2번을 선택해주세요. ");
					   		System.out.println("--------------------------------");
					   		System.out.println();
					   		revAllList1();
			        }
				}
				break;
			default : 
				System.out.println("--------------------------------");
		   		System.out.println("     1번 ~ 4번을 선택해주세요.  ");
		   		System.out.println("--------------------------------");
		   		System.out.println();	
		   		revAllList1();
			}
			
		}
			
		// 직급
		public void revAllList2() {
			System.out.println("  ╔═══════════════════════════╗");  // 부서별과 출력과 같이
			System.out.println("  ║    직급별 인사고과 조회   ║");
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
			System.out.println("  1. 사원   2. 대리");
			System.out.println("  3. 과장   4. 부장");
			System.out.println();
			System.out.print("  💡 선택(숫자 입력) >> ");
			int input = sc.nextInt();
			System.out.println();
			
			switch(input) {
				case 1 :
					List<ReviewVO> reviewList = rdao.revSelectP("사원");
					
					if(reviewList.size() >0) {
						System.out.println("──────────────────────────────────────────────────────────");
						System.out.println(" NO | 직원번호 | 부서번호 | 직급 | 평가등급 |  평가일시   ");
						System.out.println("──────────────────────────────────────────────────────────");
						
						for(ReviewVO list : reviewList) {
							System.out.printf("  %d |   %s  |    %s    | %s |     %s    | %s%n",list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getGrade(),list.getEvalDate());
							System.out.println("──────────────────────────────────────────────────────────");
						}
							System.out.println();
							System.out.println("  1. 개별 조회  2. 인사고과 조회 메뉴"); 
					        System.out.println();
					        System.out.print("  💡 선택(숫자 입력) >> ");
					        int num = sc.nextInt();
					        System.out.println();
					        
					        switch(num) {
				        	case 1 : revList(); break;
				        	case 2 : revView(); break;
				        	default : 
				        		System.out.println("--------------------------------");
						   		System.out.println("    1번이나 2번을 선택해주세요. ");
						   		System.out.println("--------------------------------");
						   		System.out.println();
						   		revAllList2();
				        }
					}
					break;
				case 2 :
					List<ReviewVO> reviewList1 = rdao.revSelectP("대리");
					
					if(reviewList1.size() >0) {
						System.out.println("──────────────────────────────────────────────────────────");
						System.out.println(" NO | 직원번호 | 부서번호 | 직급 | 평가등급 |  평가일시   ");
						System.out.println("──────────────────────────────────────────────────────────");
						
						for(ReviewVO list : reviewList1) {
							System.out.printf("  %d |   %s  |    %s    | %s |     %s    | %s%n",list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getGrade(),list.getEvalDate());
							System.out.println("──────────────────────────────────────────────────────────");
						}
							System.out.println();
							System.out.println("  1. 개별 조회  2. 인사고과 조회 메뉴"); 
					        System.out.println();
					        System.out.print("  💡 선택(숫자 입력) >> ");
					        int num = sc.nextInt();
					        System.out.println();
					        
					        switch(num) {
				        	case 1 : revList(); break;
				        	case 2 : revView(); break;
				        	default : 
				        		System.out.println("--------------------------------");
						   		System.out.println("    1번이나 2번을 선택해주세요. ");
						   		System.out.println("--------------------------------");
						   		System.out.println();
						   		revAllList2();
				        }
					}
					break;
				case 3 :
					List<ReviewVO> reviewList2 = rdao.revSelectP("과장");
					
					if(reviewList2.size() >0) {
						System.out.println("──────────────────────────────────────────────────────────");
						System.out.println(" NO | 직원번호 | 부서번호 | 직급 | 평가등급 |  평가일시   ");
						System.out.println("──────────────────────────────────────────────────────────");
						
						for(ReviewVO list : reviewList2) {
							System.out.printf("  %d |   %s  |    %s    | %s |     %s    | %s%n",list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getGrade(),list.getEvalDate());
							System.out.println("──────────────────────────────────────────────────────────");					
						}
							System.out.println();
							System.out.println("  1. 개별 조회  2. 인사고과 조회 메뉴"); 
					        System.out.println();
					        System.out.print("  💡 선택(숫자 입력) >> ");
					        int num = sc.nextInt();
					        System.out.println();
					        
					        switch(num) {
				        	case 1 : revList(); break;
				        	case 2 : revView(); break;
				        	default : 
				        		System.out.println("--------------------------------");
						   		System.out.println("    1번이나 2번을 선택해주세요. ");
						   		System.out.println("--------------------------------");
						   		System.out.println();
						   		revAllList2();
				        }
					}
					break;
				case 4 :
					List<ReviewVO> reviewList3 = rdao.revSelectP("부장");
					
					if(reviewList3.size() >0) {
						System.out.println("──────────────────────────────────────────────────────────");
						System.out.println(" NO | 직원번호 | 부서번호 | 직급 | 평가등급 |  평가일시   ");
						System.out.println("──────────────────────────────────────────────────────────");
						
						for(ReviewVO list : reviewList3) {
							System.out.printf("  %d |   %s  |    %s    | %s |     %s    | %s%n",list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getGrade(),list.getEvalDate());
							System.out.println("──────────────────────────────────────────────────────────");
						}
							System.out.println();
							System.out.println("  1. 개별 조회  2. 인사고과 조회 메뉴"); 
					        System.out.println();
					        System.out.print("  💡 선택(숫자 입력) >> ");
					        int num = sc.nextInt();
					        System.out.println();
					        
					        switch(num) {
				        	case 1 : revList(); break;
				        	case 2 : revView(); break;
				        	default : 
				        		System.out.println("--------------------------------");
						   		System.out.println("    1번이나 2번을 선택해주세요. ");
						   		System.out.println("--------------------------------");
						   		System.out.println();
						   		revAllList2();
				        }
					}
					break;
				default :
					System.out.println("--------------------------------");
			   		System.out.println("     1번 ~ 4번을 선택해주세요.  ");
			   		System.out.println("--------------------------------");
			   		System.out.println();	
			   		revAllList2();
			}		
		}
		
		// 연도
	    public void revAllList3() {
	    	System.out.println("  ╔══════════════════════════════╗"); 
			System.out.println("  ║      연도별 인사고과 조회    ║");
			System.out.println("  ╠══════════════════════════════║");
			System.out.println("  ║ 기록은 평가일로부터 3년 유효 ║");
			System.out.println("  ╚══════════════════════════════╝");
			System.out.println();
			System.out.print("  💡 연도를 입력하세요(숫자 입력) >>  ");
			String input = sc.next();
			System.out.println();
				
			
			List<ReviewVO> reviewList = rdao.revSelectY(input);
			
			if(reviewList.size() >0) {
				System.out.println("──────────────────────────────────────────────────────────");
				System.out.println(" NO | 직원번호 | 부서번호 | 직급 | 평가등급 |  평가일시   ");
				System.out.println("──────────────────────────────────────────────────────────");
				
				for(ReviewVO list : reviewList) {
					System.out.printf("  %d |   %s  |    %s    | %s |     %s    | %s%n",list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getGrade(),list.getEvalDate());
					System.out.println("──────────────────────────────────────────────────────────");
				}
					System.out.println();
					System.out.println("  1. 개별 조회  2. 데이터 삭제  2. 인사고과 조회 메뉴"); 
			        System.out.println();
			        System.out.print("  💡 선택(숫자 입력) >> ");
			        int num = sc.nextInt();
			        System.out.println();
			        
			        switch(num) {
		        	case 1 : revList(); break;
		        	case 2 : revAllList3renive(input); break;
		        	case 3 : revView(); break;
		        	default : 
		        		System.out.println("--------------------------------");
				   		System.out.println("    1번이나 2번을 선택해주세요. ");
				   		System.out.println("--------------------------------");
				   		System.out.println();
				   		revAllList3();
		        }
			}else {
				System.out.println("--------------------------------");
		   		System.out.println("   데이터가 존재하지 않습니다.  ");
		   		System.out.println("--------------------------------");
		   		System.out.println();
		   		revView();
				
			}

	    }
	    
	    // 조회 연도 데이터 전체 삭제
	    public void revAllList3renive(String year) {
	    	System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
			System.out.printf("  ║     %s년 데이터 삭제    ║%n",year);
			System.out.println("  ╚═══════════════════════════╝");
			System.out.println();
	    	System.out.println(" < 삭제하려면 Y를 그렇지 않으면 N을 입력해주세요 >");
	    	System.out.println();
			System.out.print("  💡 입력 >> ");
			String answer = sc.next();
			System.out.println();
			
		
			if(answer.equalsIgnoreCase("Y")) {  // equalsIgnoreCase는 대소문자 구분없이 사용할 수 있다.
				
				  if(rdao.revyearDelete(year)){	
					  	System.out.println("--------------------------------");
						System.out.println("         삭제되었습니다.        ");
						System.out.println("--------------------------------");
				        
				 	}else {
				 		System.out.println("--------------------------------");
						System.out.println("     삭제를 실패하였습니다.     ");
						System.out.println("--------------------------------");
				 	}
					}else if(answer.equalsIgnoreCase("N")) {
			  			System.out.println("--------------------------------");
			  		   	System.out.println("         취소되었습니다.        ");
			  		   	System.out.println("--------------------------------");
	  		}else {
	  			System.out.println("--------------------------------");
				System.out.println("  다시 선택해주세요. ( Y | N )  ");
				System.out.println("--------------------------------");
	     }
			System.out.println();
			revView();
	    }
		
	    // 평가 개별조회
	    public void revList() {

		System.out.println("  ╔═══════════════════════════╗");  
		System.out.println("  ║     인사고과 개별 조회    ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.print("  💡 직원번호를 입력하세요 >>  ");
		String input = sc.next();
		System.out.println();
		
		List<ReviewVO> reviewList = rdao.revSelect(input);
		
		if(reviewList.size() >0) {
				System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");
				System.out.println(" NO.| 직원번호 | 부서번호 | 직급 | 관리능력 | 유대관계 | 책임감 | 근면성 | 업무지식 | 총점수 | 평가등급 |      비고    |  평가일시");
				System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
			
			for(ReviewVO list : reviewList) {
				System.out.printf("  %d |   %s  |    %s    | %s |    %d    |    %d    |   %d   |   %d   |    %d    |   %d  |    %s     |     %s     | %s%n",
						list.getReno(),list.getEmid(),list.getDno(),list.getPosition(),list.getEval1(),list.getEval2(),
						list.getEval3(),list.getEval4(),list.getEval5(),list.getEvalTot(),list.getGrade(),list.getRemark(),list.getEvalDate());
				System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");
			}
				System.out.println("  1. 상세 조회   2. 인사고과 조회 메뉴"); 
		        System.out.println();
		        System.out.print("  💡 선택 >> ");
		        int num = sc.nextInt();
		        System.out.println();
		        
		        switch(num) {
		        	case 1 : revDetail(); break;
		        	case 2 : revView(); break;
		        	default : 
		        		System.out.println("--------------------------------");
				   		System.out.println("    1번이나 2번을 선택해주세요. ");
				   		System.out.println("--------------------------------");
				   		System.out.println();
				   		revAllList3();
		        }
					
		}else {
			System.out.println("--------------------------------");
	   		System.out.println("  존재하지 않는 직원번호입니다. ");
	   		System.out.println("--------------------------------");
	   		System.out.println();
	   		revView();
		}
	}		
	    
	
	    // 평가  상세 조회
	    public void revDetail() {
	    	if (hrsys.sysid.equals("admin")) {   // 인사팀 관리자
				System.out.print("  💡 조회할 번호 선택(숫자 입력) >> ");
				rno = sc.nextInt();
				System.out.println();	
				System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
				System.out.println("  ║     인사고과 상세 조회    ║");
				System.out.println("  ╚═══════════════════════════╝");
				System.out.println();
				
					
				ReviewVO revo = rdao.revSelect(rno);
				
				if(revo !=null) {
					System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");
					System.out.println(" NO.| 직원번호 | 부서번호 | 직급 | 관리능력 | 유대관계 | 책임감 | 근면성 | 업무지식 | 총점수 | 평가등급 |      비고    |  평가일시");
					System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.printf("  %d |   %s  |    %s    | %s |    %d    |    %d    |   %d   |   %d   |    %d    |   %d  |    %s     |     %s     | %s%n",
							revo.getReno(),revo.getEmid(),revo.getDno(),revo.getPosition(),revo.getEval1(),revo.getEval2(),
							revo.getEval3(),revo.getEval4(),revo.getEval5(),revo.getEvalTot(),revo.getGrade(),revo.getRemark(),revo.getEvalDate());
					System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");
					System.out.println();
					System.out.println("  1. 평가 수정  2. 평가 삭제  3. 인사고과 조회 메뉴");
					System.out.println();
					System.out.print("  💡 선택(숫자 입력) >> ");
					int num = sc.nextInt();
					System.out.println();
					
					HrAdminMain hram = new  HrAdminMain();
					switch(num) {
						case 1 : hram.revModify(revo);  break;
						case 2 : hram.revRemove(rno); break;
						case 3 : revView(); break;
						default :
						System.out.println("--------------------------------");
				   		System.out.println("     1번 ~ 3번을 선택해주세요.  ");
				   		System.out.println("--------------------------------");
				   		System.out.println();	
				   		revList();
					}
				
				} // else end
	    	}else if(hrsys.sysid.equals("admin2")) {   // 인사팀 열람자
	    		System.out.print("  💡 조회할 번호 선택(숫자 입력) >> ");
				rno = sc.nextInt();
				System.out.println();	
				System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
				System.out.println("  ║     인사고과 상세 조회    ║");
				System.out.println("  ╚═══════════════════════════╝");
				System.out.println();
				
					
				ReviewVO revo = rdao.revSelect(rno);
				
				if(revo !=null) {
					System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");
					System.out.println(" NO.| 직원번호 | 부서번호 | 직급 | 관리능력 | 유대관계 | 책임감 | 근면성 | 업무지식 | 총점수 | 평가등급 |      비고    |  평가일시");
					System.out.println("──────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
					System.out.printf("  %d |   %s  |    %s    | %s |    %d    |    %d    |   %d   |   %d   |    %d    |   %d  |    %s     |     %s     | %s%n  ",
							revo.getReno(),revo.getEmid(),revo.getDno(),revo.getPosition(),revo.getEval1(),revo.getEval2(),
							revo.getEval3(),revo.getEval4(),revo.getEval5(),revo.getEvalTot(),revo.getGrade(),revo.getRemark(),revo.getEvalDate());
					System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────── ");	
					System.out.println();
		            System.out.println("--------------------------------");
		            System.out.println("     조회 메뉴로 이동합니다.    ");
				    System.out.println("--------------------------------");
				    System.out.println();
					revView();
				}
	    	}
		
	} // detail end	    	    
	    
}// class end
