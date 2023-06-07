package memo.main;

import java.util.List;
import java.util.Scanner;

import memo.dao.MemberDAO;
import memo.dao.MemoDAO;
import memo.util.DBConn;
import memo.vo.MemberVO;

public class MemberMain { 
	private Scanner sc; // 멤버 필드로 선언
	private int input;
	private MemberDAO mdao;	
	private MemoDAO medao;
//	= public static String id; //입력받은 아이디는 필드로 선언된 공유 변수 id에 저장
	static String id; //입력받은 아이디는 필드로 선언된 공유 변수 id에 저장

	// source로 기본생성자 생성
	public MemberMain() {
		sc = new Scanner(System.in); // 멤버 필드 초기화 //keyboard로 받으니까 System.in
		mdao = new MemberDAO();
	}

	public static void main(String[] args) {
		// menu(); X //menu()는 static이 없고, main 메서드엔 static이 있어서 참조변수를 만들어 줘야한다.
		// Cannot make a static reference to the non-static method menu() from the type
		// MemberMain
		MemberMain membermain = new MemberMain();
		membermain.menu();
		
		// = new MemberMain().menu();
		
	}//main end

	public void menu() { // 메인 메뉴
		System.out.println("\n------------------------------------------");
		System.out.println("   A LINE MEMO BOARD SYSTEM    ");
		System.out.println("------------------------------------------");
		System.out.println("   1.로그인");
		System.out.println("   2.회원 가입");
		System.out.println("   3.종료 ");
		System.out.print("   >> 선택 : ");

		input = sc.nextInt(); // 키보드에서 메뉴 선택 받기

		switch (input) {
		case 1:
			login();
			break; // 1을 선택하면 login() 호출
		case 2:
			join();
			break; // 2를 선택하면 join() 호출
		case 3:
			System.out.println("   >> 시스템을 종료합니다."); // 3을 선택하면 "시스템을 종료합니다."를 출력하고
			sc.close(); // sc 객체를 닫은 후
			DBConn.close(); // Connection 닫고 나가기
			System.exit(0); // 시스템 종료 처리
		default:
			System.out.println("   >> 1 ~ 3를 선택해주세요."); // 그 외의 경우에는 "1 ~ 4를 선택해주세요"를 출력하고
			System.out.println();
			menu(); // 메인 메뉴 표시
			break;
		}// END switch()
	}// END menu()

	//로그인
	public void login() {
		System.out.println("\n------------------------------------------");
		System.out.println("              SYSTEM LOGIN   ");
		System.out.println("------------------------------------------");
		System.out.print(" 아이디 : ");
		String mid = sc.next();

		System.out.print(" 비밀번호(폰번호) : ");
		String phone = sc.next();

		// <<로그인 확인>>
		// 로그인에 필요한 아이디와 비밀번호를 DAO 클래스의 메서드로 전달하여 로그인 시도
		boolean result = mdao.login(mid, phone);
		// 사용자에게서 입력받은 아이디와 전화번호가 데이터베이스와 일치하면
			if (result == true) {
				System.out.println(" 로그인 성공"); // 로그인 성공 메시지를 출력하고
				id = mid;
				// 관리자와 회원에 맞는 메뉴 표시한다.
				// equlas를 사용하여 대소문자 구분
				if (id.equals("admin")){
					AdminMain adminmain = new AdminMain();
					adminmain.logInMenuAdmin();
				} else {
					logInMenu(mid);
				}
				// 단, 입력받은 아이디는 필드로 선언된 공유 변수 id에 저장
			} else { 		// 그렇지 않으면 
				System.out.println(" 로그인 실패 "); // 로그인 실패 메시지를 출력하고
			}
			login(); // 로그인 실패한 경우 다시 메인 메뉴로 돌아감
			// 로그인 화면을 다시 표시
		}//login end

	// 일반 회원 메뉴
	private void logInMenu(String mid) {
		System.out.println("\n------------------------------------------");
		System.out.println("    " + mid + " 님, 환영합니다!    ");
		System.out.println("------------------------------------------");
		System.out.println("   1.회원 정보 보기");
		System.out.println("   2.회원 정보 수정");
		System.out.println("   3.회원 탈퇴");
		System.out.println("   4.한 줄 메모 게시판 가기 ");		
		System.out.print("   >> 선택 : ");

		input = sc.nextInt();

		switch (input) {
		case 1:
			// 회원 정보 보기 메서드 호출
			info(mdao.select(mid));
			break;
		case 2:
			// 회원 정보 수정 메서드 호출
			modify(mdao.select(mid));
			break;
		case 3:
			// 회원 정보 삭제 메서드 호출
			remove(mid);
			break;
		case 4:
			// 한줄 메모 게시판 가기
			MemoMain memomain = new MemoMain(mid);
			memomain.menu();
		default:
			System.out.println("   >> 1 ~ 4를 선택해주세요.");
			System.out.println();
			logInMenu(mid);
			break;
		}
	}// loggedInMenu

	// 회원 가입
	public void join() { 
		// 회원 가입에 필요한 정보를 입력받아서
		// 객체에 저장한 후 DAO 클래스의 메서드로 전달
		System.out.println("\n------------------------------------------");
		System.out.println("                  MEMBER JOIN   ");
		System.out.println("------------------------------------------");
		System.out.print(" 아이디 : ");
		String mid = sc.next();

		System.out.print(" 이름 : ");
		String mname = sc.next();

		// MemberVO 객체 생성 및 정보 설정
		MemberVO mvo = new MemberVO();
		mvo.setMid(mid);
		mvo.setMname(mname);

		System.out.print(" 나이 : ");
		mvo.setAge(sc.nextInt());

		System.out.print(" 전화번호 : ");
		mvo.setPhone(sc.next());

		// 회원가입에 성공하면 "회원가입이 완료되었습니다."를 출력하고
		// 그렇지 않으면 "회원가입에 실패했습니다."를 출력한 후
		// 메인 메뉴 표시
		boolean result = mdao.insert(mvo);
		if (result == true) {
			System.out.println(" 회원가입이 완료되었습니다.");
		} else {
			System.out.println(" 회원가입에 실패했습니다.");
		}
		menu();
	}

	// 회원 목록
	public void list() { 
		System.out.println("\n------------------------------------------");
		System.out.println("                  MEMBER LIST   ");
		System.out.println("------------------------------------------");

		// 1. dao에 매개변수가 없는 select를 호출하여
		// 2. 반환되는 값을 저장한 후
		List<MemberVO> mvoList = mdao.select();

		// 3. 받은 값을 화면에 표시한다.
		if (mvoList.size() > 0) { // 등록된 게시물이 있으면 화면에 표시
			// for문을 이용-------------------------------------------------
			System.out.println("아이디 | 이름 | 나이 | 전화번호 | 가입일자");
			for (int i = 0; i < mvoList.size(); i++) {
				MemberVO mvo = mvoList.get(i);
				System.out.println(mvo.getMid() + " | " + mvo.getMname() + " | " + mvo.getAge() + " | " + mvo.getPhone()
						+ " | " + mvo.getRegDate());
			}

			System.out.println();

			// foreach문
			System.out.println("아이디 | 이름 | 나이 | 전화번호 | 가입일자");
			for (MemberVO mvo : mvoList) {
				System.out.println(mvo.getMid() + " | " + mvo.getMname() + " | " + mvo.getAge() + " | " + mvo.getPhone()
						+ " | " + mvo.getRegDate());
			}
		} else {
			System.out.println("   등록된 회원이 없습니다.");
		}
		menu(); // 메인메뉴 표시
	}

	//회원 조회
	public void info(MemberVO mvo) { 
		// 1. 조회할 ID를 입력받는다.
		// 2. 입력받은 ID를 DAO의 select메서드에 매개변수로 전달한다.
		// 3. 반환되는 membervo를 받는다.
		// 4. 받은 데이터를 화면에 표시한다.
		System.out.println("\n------------------------------------------");
		System.out.println("                  MEMBER INFO   ");
		System.out.println("------------------------------------------");
		System.out.print("   조회 아이디 : ");
		String mid = sc.next();

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
				menu();
				break; // 3을 선택하면 menu() 호출
			default:
				System.out.println("   >> 1 ~ 3를 선택해주세요.");
				System.out.println();
				info(mvo); // 재귀적 회원정보 메뉴 표시
				break;
			}// END switch()

		} else {
			System.out.println("   해당 아이디가 존재하지 않습니다.\n");
			menu(); // 메인메뉴 표시
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
//		1. 수정할 나이를 입력받는다
//		2. 수정할 전화번호를 입력받는다.
//		3. 입력받은 값을 VO에 저장한다.
//		4. 입력받은 정보를 DAO에 update한다.
//		5. 호출한 결과를 받아서 정상적으로 수정됐는지 안됐는지 확인하는 문구를 출력한다.
//		+6. 정상적인 값을 입력받은 경우에만 저장한다. (나이를 숫자를 입력해주세요. 4자리를 입력해주세요 등)
//			즉, 먼저 정상적인 값을 입력하여 테스트를 하고, 잘 작동하면 그 외에 예외 상황들을 커버하는 코드를 작성

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
		info(mvo);
	} //modify end

	// remove
	// () 안에 회원 삭제할 ID를 매개변수로 넘겨받는다.
	// Select String mid로 불러옴
	public void remove(String mid) {
		System.out.println("\n------------------------------------------");
		System.out.println("                  MEMBER REMOVE   ");
		System.out.println("------------------------------------------");
//		1. 정말 삭제할지 의사확인 (Y/N) + Y와 N의 대소문자 구분여부
//		1-1 Y를 누르면 삭제하며, N을 누르면 menu로 돌아간다.
//		2. (Y)입력받은 정보를 DAO에 delete한다.
//		3. 호출한 결과를 받아서 정상적으로 삭제됐는지 안됐는지 확인하는 문구를 출력한다.
//		4. 정상값 이외 체크
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
		menu();
	}// remove end
	public static String getID() {
        return id;
    }
	}// class end
