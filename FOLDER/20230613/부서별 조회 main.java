public void revAllList1() {
			System.out.println("  ╔═══════════════════════════╗");  // 2칸씩
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
