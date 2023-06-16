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
