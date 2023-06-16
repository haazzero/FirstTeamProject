package hr.main;

import java.util.Scanner;

import hr.dao.HRSystemDAO;
import hr.util.DBConn;
import hr.vo.HRSystemVO;

public class HRSystemMain {

	private Scanner sc;
	private HRSystemDAO hrsdao;
	private HRSystemVO hrsysVO;
	public static String sysid;
	
	public HRSystemMain() {
		sc = new Scanner(System.in);
		hrsdao = new HRSystemDAO();
		
	}
	
	public static void main(String [] argus) {
		HRSystemMain hrsys = new HRSystemMain();
		hrsys.dragonInfo();
		hrsys.dragonMain();
	}
	
	public void dragonInfo() {
		System.out.println("______  _____  _   _ ______  _      _____  ______ ______   ___   _____  _____  _   _ ");
		System.out.println("|  _  \\|  _  || | | || ___ \\| |    |  ___| |  _  \\| ___ \\ / _ \\ |  __ \\|  _  || \\ | |");
		System.out.println("| | | || | | || | | || |_/ /| |    | |__   | | | || |_/ // /_\\ \\| |  \\/| | | ||  \\| |");
		System.out.println("| | | || | | || | | || ___ \\| |    |  __|  | | | ||    / |  _  || | __ | | | || . ` |");
		System.out.println("| |/ / \\ \\_/ /| |_| || |_/ /| |____| |___  | |/ / | |\\ \\ | | | || |_\\ \\\\ \\_/ /| |\\  |");
		System.out.println("|___/   \\___/  \\___/ \\____/ \\_____/\\____/  |___/  \\_| \\_|\\_| |_/ \\____/ \\___/ \\_| \\_/");
		System.out.println();
		System.out.println("                    안녕하세요. 더블 드래곤 인사관리시스템입니다.");
		System.out.println("                     이곳에서는 어쩌구 저쩌구 관리가 가능합니다.  ");
		System.out.println();
		
	}
	
	public void dragonMain() {
		
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║            Main           ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("    1. 로그인     2. 시스템 종료   ");
		System.out.println();
		System.out.print("    💡 선택(숫자 입력) >>    ");
		int input = sc.nextInt();
	   	System.out.println();
	   	
	   	switch(input) {
	   		case 1 : login();
	   			break;
	   		case 2 : sysEnd();
	   			break;
	   		default : 	System.out.println("--------------------------------");
		   				System.out.println("    1번이나 2번을 선택하세요.   ");
		   				System.out.println("--------------------------------");
		   				dragonMain();
		   				System.out.println();
	   	}
	}
	
	public void login() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║           LOGIN           ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		
		System.out.print("    💡 아이디 >>  ");
		String id = sc.next();
		System.out.println();
		System.out.print("    💡 비밀번호 >>  ");
		String pw = sc.next();
		System.out.println();
		
		HRSystemVO hrsysVO = new HRSystemVO();
		hrsysVO.setId(id);
		hrsysVO.setPw(pw);
		
		if(hrsdao.loginCheck(id, pw)) {
			   System.out.println("-----------------------------");
	    	   System.out.println("     로그인에 성공했습니다   "); // 로그인 성공 메시지를 출력한다.
	    	   System.out.println("-----------------------------");
	    	   System.out.println();
	    	   
	    	   if(id.equals("admin")) {
	    		   System.out.println("-----------------------------------");
	    		   System.out.println(" 인사팀 관리자 계정으로 접속합니다.");
	    		   System.out.println("-----------------------------------");
	    		   System.out.println();
	    		   sysid = id;
	    		  new HrAdminMain().pmMenu();
	    		  
	    	   }
	    	   else if(id.equals("admin2")) {
	    		   System.out.println("-----------------------------------");
	    		   System.out.println(" 인사팀 열람자 계정으로 접속합니다.");
	    		   System.out.println("-----------------------------------");
	    		   System.out.println();
	    		   sysid = id;
	    		   new HrReadMain().pmReader();
	    	   }
	    	   else if(id.equals("admin3")) {
	    		   System.out.println("-----------------------------------");
	    		   System.out.println(" 경리팀 열람자 계정으로 접속합니다.");
	    		   System.out.println("-----------------------------------");
	    		   System.out.println();
	    		   sysid = id;
	    		   new AccountAdminMain().adManage();
	    	   }
		} else {
			System.out.println("------------------------------------------------");
			System.out.println("   로그인에 실패했습니다. 메인으로 돌아갑니다.  ");	   
			System.out.println("------------------------------------------------");   
			System.out.println();
			dragonMain();
		}
		
		System.out.println("-----------------------------------------");
	   	System.out.println("   잘못된 입력입니다. 다시 입력해주세요  ");
	   	System.out.println("-----------------------------------------");
	   	System.out.println();
		
	   // 인사팀 관리자 HrAdminMain  pmMenu()
	   // 인사팀 열람자 HrReadMain   pmReader()
	   // 경리팀 관리자 AccountAdminMain adManage()
	   //로그인하는 계정이 모두 다르고, 로그인 하는 것에 따라 다른 메뉴
	   	
	   // 로그인 성공 - > 아이디는 못바꾸니까 아이디에 일치하는 권한 메뉴로 / 각자 메뉴에 맞는 메인으로 이동
	   // 존재하지 않는 아이디입니다.
	   // 비밀번호가 틀립니다.
	}
	
	public void pwChange(HRSystemVO hrsysVO) {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║      Change Password      ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  < 변경할 비밀번호 입력 (숫자 4자리) > ");    // 비밀번호 찾기 기능 넣어서 제한 없애기
		System.out.println();
	    System.out.print("  💡>> ");
	    hrsysVO.setPw(sc.next());
	    System.out.println();
	    hrsysVO.setId(sysid);
	    
	    if(hrsdao.pwUpdate(hrsysVO)) {
	    	System.out.println("---------------------------------------");
		    System.out.println("     비밀번호 변경이 완료되었습니다.   ");
		    System.out.println("---------------------------------------");
	    } else {
	    	System.out.println("-------------------------------------------------");
	        System.out.println(" 비밀번호 변경에 실패했습니다. 다시 입력해주세요.");
	    	System.out.println("-------------------------------------------------");
	    }
	    login();
	    System.out.println();
	} //pwChange end
	
	public void sysEnd() {
		System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
		System.out.println("  ║        SYSTEM EXIT        ║");
		System.out.println("  ╚═══════════════════════════╝");
		System.out.println();
		System.out.println("  💡 시스템을 종료하시겠습니까? ( Y | N )");
		System.out.println();
		System.out.print("  💡 >> ");
		String input = sc.next();
		System.out.println();
			
		if(input.equalsIgnoreCase("y")) {
				System.out.println("--------------------------------");
				System.out.println("     시스템이 종료되었습니다.   ");
				System.out.println("--------------------------------");
				sc.close();
				DBConn.close();		// connection close. 시스템이 끝나면 끝나지만 명시적으로 하기 위해
				System.exit(0);  // 정상종료
			
		}
		else if (input.equalsIgnoreCase("n")) {
				System.out.println("----------------------------");
				System.out.println("     메인으로 돌아갑니다.   ");
				System.out.println("----------------------------");
				System.out.println();
				dragonMain();
		}
		else {
			System.out.println("--------------------------------");
			System.out.println("  다시 선택해주세요. ( Y | N )  ");
			System.out.println("--------------------------------");
			System.out.println();
			sysEnd();
		}
	}
	
		
}
