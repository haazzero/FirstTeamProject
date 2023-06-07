package memo.main;

import java.util.List;
import java.util.Scanner;

import memo.dao.MemberDAO;
import memo.vo.MemberVO;


public class AdminMain {
	private Scanner sc; // 멤버 필드로 선언
	private int input;
	private MemberDAO mdao;
	
	public AdminMain() {
		sc = new Scanner(System.in); // 멤버 필드 초기화
		mdao = new MemberDAO();
	}

	public static void main(String[] args) {
		AdminMain adminmain = new AdminMain();
		adminmain.logInMenuAdmin();
	}
	
	// 관리자 회원 메뉴
		public void logInMenuAdmin() {
			System.out.println("\n------------------------------------------");
			System.out.println("  관리자  admin 님, 환영합니다!    ");
			System.out.println("------------------------------------------");
			System.out.println("   1.전체 회원 목록을 보시겠습니까?");
			System.out.print("   >> 원하시면 1을 입력해주세요 : ");

			input = sc.nextInt();

			if (input == 1) {
				list();
			}
		}// loggedInMenu
		
		// 전체 회원 목록
		public void list() { 
			System.out.println("\n------------------------------------------");
			System.out.println("              ALL  MEMBER LIST   ");
			System.out.println("------------------------------------------");

			List<MemberVO> mvoList = mdao.select();

			if (mvoList.size() > 0) { // 등록된 게시물이 있으면 화면에 표시

				// foreach를 이용-------------------------------------------------
				System.out.println("아이디 | 이름 | 나이 | 전화번호 | 가입일자");
				for (MemberVO mvo : mvoList) {
					System.out.println(mvo.getMid() + " | " + mvo.getMname() + " | " + mvo.getAge() + " | " + mvo.getPhone()
							+ " | " + mvo.getRegDate());
				}
			} else {
				System.out.println("   등록된 회원이 없습니다.");
			}

			System.out.println("\n------------------------------------------");
			System.out.println("  상세회원 정보 보기 및 수정 삭제를 하시겠습니까?    ");
			System.out.println("  >> 원하시면 1을 입력해주세요 :   ");
			System.out.println("------------------------------------------");
			input = sc.nextInt();

			if (input == 1) {
				info(); //상세 회원 정보 보기
			}
		}//list end

		 // 상세 회원 조회
		public void info() {
			System.out.println("\n------------------------------------------");
			System.out.println("                  MEMBER INFO   ");
			System.out.println("------------------------------------------");
			System.out.print("   조회 아이디 : ");

			String mid = sc.next();
			MemberVO mvo = mdao.select(mid);
			
			if (mvo != null) { // 4.
				System.out.println("   이름 : " + mvo.getMname());
				System.out.println("   나이 : " + mvo.getAge());
				System.out.println("   전화번호 : " + mvo.getPhone());
				System.out.println("   가입일자 : " + mvo.getRegDate());
				System.out.println("   --------------------------------------");
				System.out.println("   1.회원 수정     2.회원 삭제     3.메인 ");
				System.out.println("   >> 선택 : ");

				input = sc.nextInt(); // 키보드에서 메뉴 선택 받기

				switch (input) { // menu의 switch문을 copy
				case 1:
					modify(mvo);
					break; // 1을 선택하면 modify() 호출
				case 2:
					remove(mid);
					break; // 2를 선택하면 remove() 호출
				case 3:
					logInMenuAdmin();
					break; // 3을 선택하면 menu() 호출
				default:
					System.out.println("   >> 1 ~ 3를 선택해주세요.");
					System.out.println();
					info(); // 재귀적 회원정보 메뉴 표시
					break;
				}// END switch()

			} else {
				System.out.println("   해당 아이디가 존재하지 않습니다.\n");
				logInMenuAdmin(); // 메인메뉴 표시
			}
		}

		// modify
		// ()안에 매개변수를 받아야함.
		// 방법 1 : mid로 아이디 하나를 받아옴
		// 방법 2 : select에서 가지고있는 회원 정보를 조회한 memberVO객체를 매개변수로 받아옴 --택2
		public void modify(MemberVO mvo) {
			System.out.println("\n------------------------------------------");
			System.out.println("                  MEMBER MODIFY   ");
			System.out.println("------------------------------------------");
//			1. 수정할 나이를 입력받는다
//			2. 수정할 전화번호를 입력받는다.
//			3. 입력받은 값을 VO에 저장한다.
//			4. 입력받은 정보를 DAO에 update한다.
//			5. 호출한 결과를 받아서 정상적으로 수정됐는지 안됐는지 확인하는 문구를 출력한다.
//			+6. 정상적인 값을 입력받은 경우에만 저장한다. (나이를 숫자를 입력해주세요. 4자리를 입력해주세요 등)
//				즉, 먼저 정상적인 값을 입력하여 테스트를 하고, 잘 작동하면 그 외에 예외 상황들을 커버하는 코드를 작성

			System.out.print(" 나이 : ");
			mvo.setAge(sc.nextInt());

			System.out.print(" 전화번호 : ");
			mvo.setPhone(sc.next()); // 3번까지 완료

			boolean result = mdao.update(mvo);
			if (result == true) {
				System.out.println(" 회원수정을 완료했습니다.");
			} else {
				System.out.println(" 회원수정을 실패했습니다.");
			} // 4번 완

			info();
		} // 회원 수정

		// remove
		// () 안에 회원 삭제할 ID를 매개변수로 넘겨받는다.
		// Select String mid로 불러옴
		public void remove(String mid) {
			System.out.println("\n------------------------------------------");
			System.out.println("                  MEMBER REMOVE   ");
			System.out.println("------------------------------------------");
//			1. 정말 삭제할지 의사확인 (Y/N) + Y와 N의 대소문자 구분여부
//			1-1 Y를 누르면 삭제하며, N을 누르면 menu로 돌아간다.
//			2. (Y)입력받은 정보를 DAO에 delete한다.
//			3. 호출한 결과를 받아서 정상적으로 삭제됐는지 안됐는지 확인하는 문구를 출력한다.
//			4. 정상값 이외 체크
			System.out.print("정말 삭제하시겠습니까? (Y/N): ");
			String input = sc.next(); // 매개변수가 String이기 때문에 String으로 받음

			// input.equals()는 대소문자 구분 O
			// input.equalsIgnoreCase 대소문자 구분 X
			if (input.equalsIgnoreCase("Y")) {
				boolean result = mdao.delete(mid); // dao에 작성된 것과 같이 mid 매개변수
				if (result == true) {
					System.out.println(" 회원삭제 완료 ");
				} else {
					System.out.println(" 회원삭제 실패 ");
				}
			} else if (input.equalsIgnoreCase("N")) {
				System.out.println(" 회원삭제 취소");
			} else {
				System.out.println(" Y 또는 N을 입력해야지! (대소문자 구분 안하고 써도 돼^^)");
			}
			logInMenuAdmin();
		}// remove end

}//class end
