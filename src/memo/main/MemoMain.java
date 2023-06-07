package memo.main;

import java.util.List;
import java.util.Scanner;

import memo.dao.MemberDAO;
import memo.dao.MemoDAO;
import memo.util.DBConn;
import memo.vo.MemberVO;
import memo.vo.MemoVO;

public class MemoMain {
	private Scanner sc;
	private int input;
	private MemoDAO medao;
	public String ID;

	public MemoMain(String mid) {
		sc = new Scanner(System.in); // 멤버 필드 초기화
		medao = new MemoDAO();
		ID = mid;

	}// public end

	// 메인 메뉴
	public void menu() {
		System.out.println("\n------------------------------------------");
		System.out.println("                MEMO SYSTEM    ");
		System.out.println("------------------------------------------");
		System.out.println("   1. 한 줄 메모 작성");
		System.out.println("   2. 전체 메모 목록 보기"); // 모든 메모 보기
		System.out.println("   3. 내 메모 보기");// String id가 쓴 메모
		System.out.println("   4. 종료 ");
		System.out.print("   >> 선택 : ");

		input = sc.nextInt(); // 키보드에서 메뉴 선택 받기

		switch (input) {
		case 1:
			write(); // 1. 한 줄 메모 작성
			break; // 1을 선택하면 write() 호출
		case 2:
			list(); // 2. 메모 목록 보기
			break; // 2를 선택하면 list() 호출
		case 3:
			view(); // 3. 내 메모 보기
			break; // 3을 선택하면 view() 호출
		case 4:
			System.out.println("   >> 시스템을 종료합니다."); // 4를 선택하면 "시스템을 종료합니다."를 출력하고
			sc.close(); // sc 객체를 닫은 후
			DBConn.close(); //
			System.exit(0); // 시스템 종료 처리
		default:
			System.out.println("   >> 1 ~ 4를 선택해주세요."); // 그 외의 경우에는 "1 ~ 4를 선택해주세요"를 출력하고
			System.out.println();
			menu(); // 메인 메뉴 표시
			break;
		}// END switch()
	}// END menu()

	// 메모 작성
	public void write() {
		// 메모 작성에 필요한 정보를 입력받아서
		// 객체에 저장한 후 DAO 클래스의 메서드로 전달
		System.out.println("\n------------------------------------------");
		System.out.println("                  MEMO WRITE   ");
		System.out.println("------------------------------------------");

		MemoVO mevo = new MemoVO();
		System.out.print(" 메모 내용 : ");
		mevo.setMemo(sc.next());
		mevo.setMid(ID);

		// 메모 작성에 성공하면 "작성이 완료되었습니다."를 출력하고
		// 그렇지 않으면 "작성에 실패했습니다."를 출력한 후
		// 메인 메뉴 표시
		boolean result = medao.insert(mevo);
		if (result == true) {
			System.out.println(" 작성이 완료되었습니다.");
		} else {
			System.out.println(" 작성에 실패했습니다.");
		}
		menu();
	}

	// 메모 목록 보기
	public void list() {
		System.out.println("\n------------------------------------------");
		System.out.println("               ALL  MEMO LIST   ");
		System.out.println("------------------------------------------");

		List<MemoVO> mevoList = medao.select();
		System.out.println("번호\t| 내용\t\t| 작성자\t| 작성일자\t| 고유번호");
		for (MemoVO mevo : mevoList) {
			System.out.println(mevo.getRownum() + "\t| " + mevo.getMemo() + "\t| " + mevo.getMid() + "\t| "
					+ mevo.getRegDate() + "\t| " + mevo.getMno());
		}
		while (true) {
			System.out.println("");
			System.out.println(" >> 메뉴를 선택하세요.");
			System.out.println("   1. 메인으로 돌아가기");
			System.out.println("   2. 내가 작성한 메모 보기");
			System.out.print("   >> 선택 : ");

			input = sc.nextInt(); // 키보드에서 메뉴 선택 받기

			if (input == 1) { // 메인
				menu();
				break;
			} else if (input == 2) {
				// 내가 작성한 메모 보기
				view();
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}// list end

	// 내 메모 목록 보기
	public void view() {
		System.out.println("            MY  MEMO LIST   ");
		System.out.println("------------------------------------------");

		List<MemoVO> mevoList = medao.select(ID);
		if (mevoList.isEmpty()) {
			System.out.println("작성한 메모가 없습니다.");
		} else {
//			System.out.println("향상for");
//			System.out.println("번호 | 내용 | 작성자 | 작성일자");
//			for (MemoVO mevo : mevoList) {
//				System.out.println(
//						mevo.getMno() + " | " + mevo.getMemo() + " | " + mevo.getMid() + " | " + mevo.getRegDate());
//			}
			System.out.println();
			System.out.println("foreach");
			System.out.println("번호 | 내용 | 작성자 | 작성일자 | 고유번호");
			mevoList.forEach(mevo -> System.out.println(mevo.getRownum() + " | " + mevo.getMemo() + " | "
					+ mevo.getMid() + " | " + mevo.getRegDate() + " | " + mevo.getMno()));
		}

		while (true) {
			System.out.println("");
			System.out.println(" >> 메뉴를 선택하세요.");
			System.out.println("   1. 메인으로 돌아가기     2.게시물 선택 ");
			System.out.print("   >> 선택 : ");
			input = sc.nextInt(); // 키보드에서 메뉴 선택 받기
			if (input == 1) {
				menu();
				break;
			} else if (input == 2) {
				System.out.println("조회 게시물 번호(고유번호 입력) : ");
				int mno = sc.nextInt();
				ViewView(mno);
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}// View end

	// 게시물 번호 1개 조회
	public void ViewView(int mno) {
		System.out.println("\n------------------------------------------");
		System.out.println("             MEMO   ");
		System.out.println("------------------------------------------");

		MemoVO mevo = medao.select(mno);

		if (mevo != null) {
			System.out.println("   번호 : " + mevo.getRownum());
			System.out.println("   내용 : " + mevo.getMemo());
			System.out.println("   작성자 : " + mevo.getMid());
			System.out.println("   작성일자 : " + mevo.getRegDate());
			System.out.println("   고유번호 : " + mevo.getMno());
			System.out.println("   --------------------------------------");
			System.out.println("   1.게시물 수정     2.게시물 삭제 ");
			System.out.println("   >> 선택 : ");

			while (true) {
				input = sc.nextInt(); // 키보드에서 메뉴 선택 받기

				if (input == 1) {
					modify(mevo);
					break; // 1을 선택하면 modify() 호출
				} else if (input == 2) {
					remove(mno);
					break; // 2를 선택하면 remove() 호출
				} // END switch()
			}
		}

	}// ViewView end

	// 메모 수정
	// 방법 2 : select에서 가지고있는 회원 정보를 조회한 memberVO객체를 매개변수로 받아옴
	public void modify(MemoVO mevo) {
		System.out.println("\n------------------------------------------");
		System.out.println("                  MEMO MODIFY   ");
		System.out.println("------------------------------------------");
//			1. 수정할 메모 내용을 입력
//			3. 입력받은 값을 VO에 저장한다.
//			4. 입력받은 정보를 DAO에 update한다.
//			5. 호출한 결과를 받아서 정상적으로 수정됐는지 안됐는지 확인하는 문구를 출력한다.

		System.out.print(" 메모 내용 : ");
		mevo.setMemo(sc.next());

		boolean result = medao.update(mevo);
		if (result == true) {
			System.out.println(" 메모수정을 완료했습니다.");
		} else {
			System.out.println(" 메모수정을 실패했습니다.");
		} // 4번 완

		// view();
	}// modify end

	// 메모 삭제
	// Select int mno으로 불러옴
	public void remove(int mno) {
		System.out.println("\n------------------------------------------");
		System.out.println("                  MEMO REMOVE   ");
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
			boolean result = medao.delete(mno); // dao에 작성된 것과 같이 mid 매개변수
			if (result == true) {
				System.out.println(" 메모삭제 완료 ");
			} else {
				System.out.println(" 메모삭제 실패 ");
			}
		} else if (input.equalsIgnoreCase("N")) {
			System.out.println(" 메모삭제 취소");
		} else {
			System.out.println(" Y 또는 N을 입력해야지! (대소문자 구분 안하고 써도 돼^^)");
		}
		menu();
	}// remove end

}// class end
