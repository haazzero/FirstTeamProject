package memo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import memo.util.DBConn;
import memo.vo.MemberVO;

public class MemberDAO {
	private String query; // 쿼리문 저장 필드
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs; // selcect쿼리 저장 필드

	// 회원목록 - 접근제한 X, 반환타입 : MemberVo를 저장한 List, 매개변수 : X ()
	// 기능 : t_member 테이블의 전체 데이터를 조회하여 List에 저장한 후 반환
	// overload select !!!
	public List<MemberVO> select() {
		List<MemberVO> mvoList = new ArrayList<>();
		MemberVO mvo = null;
		try {
			// 1. select 쿼리문(테이블 전체를 조회)
			// 주의
			// - 쿼리문 끝에 ; X
			// - 자바에서 실행한 DML은 내부적으로 AUTO COMMIT
			// - Oracle에서 commit이 안되면 데이터 액션처리가 이루어지지않음
			query = " SELECT * FROM t_member ORDER BY mid ASC"; // queary를 먼저 선언하는 preparedStatement
			// 2. 쿼리문을 실행할 prepared statement 객체
			pstmt = DBConn.getConnection().prepareStatement(query);
			// 3. ?가 없기 떄문에 바인딩 할 것이 없음
			// 4. 쿼리를 실행
			// excuteQuery() : DB에서 질의 결과를 '가져와야'하는 경우
			// excuteUpdate() : '특정' 내용을 DB에 '적용' 해야하는 경우
			rs = pstmt.executeQuery(); // rs.next 시 다음 값이 있다면 true, 없다면 null 반환
			// if는 1개 조회할 때, while은 전체조회 할 때,
			// next의 기능 : 1개의 다음 행으로 커서를 움직임
			while (rs.next() == true) { // 조회된 레코드들이 있다면
				mvo = new MemberVO(); // MemberVO객체를 생성, 아니면 생성X
				mvo.setMid(rs.getString("mid")); // 해당 레코드 값을 저장
				mvo.setMname(rs.getString("mname")); // 해당 레코드 값을 저장
				mvo.setAge(rs.getInt("age")); // 해당 레코드 값을 저장
				mvo.setPhone(rs.getString("phone")); // 해당 레코드 값을 저장
				mvo.setRegDate(rs.getDate("reg_date")); // 해당 레코드 값을 저장

				// List 객체에 추가=List에 저장
				mvoList.add(mvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt); // DBconn에 있는 close 메서드 호출
		}
		return mvoList;
	}

	// 로그인 - 매개변수로 아이디와 전화번호를 받아 해당 레코드를 객체에 저장하여 반환하는 login() 메서드
	// 반환타입: boolean, 매개변수: mid(String), phone(String)
	
	
	//1. 존재하지 않는지 판별하는 쿼리문
	//2. 그 쿼리를 실행
	//3. rs.next로 있냐 없냐 DB에서 확인
	
	//<<로그인 성공 여부 확인 과정>>	
	//	 매개변수로 넘겨받은 아이디와 전화번호가 회원테이블에 존재하면 1이 반환되어 로그인 성공,
	//			그렇지 않으면 0이 반환되어 로그인 실패
	// 로그인 창에서 입력받은 아이디, 전화번호가 DB에 존재하는지 체크하는 메서드
	   public boolean login(String mid, String phone) {
	      // select count 쿼리문
	      query = "SELECT COUNT (*) FROM t_member WHERE mid=? AND phone=?";   // 입력받은 id와 phone이 전부 일치하는 레코드가 존재하는가? count갯수로 체크

	      try {
	         pstmt = DBConn.getConnection().prepareStatement(query);
	         pstmt.setString(1, mid); 
	         pstmt.setString(2, phone); 

	         rs = pstmt.executeQuery(); // 쿼리실행 -> 받는 값이 있는 read이므로 executeQuery();
	         
	         if(rs.next()) {
	            if(rs.getString(1).equals("1"))
	            return true;
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      }finally {
	         DBConn.close(pstmt);
	      }
	      return false;
	   }

	// 회원조회 - 매개변수로 아이디를 넘겨받아 해당 레코드를
	// 객체에 저장하여 반환하는 select() 메서드 (접근제한X)
	// 회원 하나의 정보를 조회
	public MemberVO select(String mid) {
		MemberVO mvo = null; // 변수의 초기화
		try {
			// 1. select 쿼리문(id를 조회)
			query = " SELECT * FROM t_member WHERE mid=? "; // queary를 먼저 선언하는 preparedStatement
			// 2. 쿼리문을 실행할 prepared statement 객체
			pstmt = DBConn.getConnection().prepareStatement(query);
			// 3. id에 해당하는 값 하나를 바인딩
			pstmt.setString(1, mid);
			// 4. 쿼리를 실행
			// next의 기능 : 1개의 다음 행으로 커서를 움직임
			rs = pstmt.executeQuery(); // rs.next 시 다음 값이 있다면 true, 없다면 null 반환
			if (rs.next() == true) { // 조회된 레코드가 있다면
				mvo = new MemberVO(); // MemberVO객체를 생성, 아니면 생성X
				mvo.setMid(rs.getString("mid")); // 해당 레코드 값을 저장
				mvo.setMname(rs.getString("mname")); // 해당 레코드 값을 저장
				mvo.setAge(rs.getInt("age")); // 해당 레코드 값을 저장
				mvo.setPhone(rs.getString("phone")); // 해당 레코드 값을 저장
				mvo.setRegDate(rs.getDate("reg_date")); // 해당 레코드 값을 저장

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt); // DBconn에 있는 close 메서드 호출
		}
		return mvo;
	}

	// 회원가입
	public boolean insert(MemberVO mvo) {
		// 매개변수로 넘겨받은 데이터를 t_member 테이블에 저장
		// DBconn이 static으로 받으니까 바로 . . 으로 getConnection을 호출하고, String ~이런 내용을 안써도돰
		try {
			// insert 쿼리문, JDBCTest.java에서 데리고 온 코드들
			query = " INSERT INTO t_member VALUES(?,?,?,?,SYSDATE) "; // queary를 먼저 선언하는 preparedStatement
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, mvo.getMid());
			pstmt.setString(2, mvo.getMname());
			pstmt.setInt(3, mvo.getAge());
			pstmt.setString(4, mvo.getPhone());

			int result = pstmt.executeUpdate();
			if (result == 1) { // 정상 회원가입 성공 시 true 반환
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt);
		}
		return false;// 그렇지 않으면 false반환
	}// insert end

	// 회원수정
	public boolean update(MemberVO mvo) {
		// 매개변수로 넘겨받은 데이터를 t_member 테이블에 변경
		try {
			// update 쿼리문, JDBCTest.java에서 데리고 온 코드들
			query = " UPDATE t_member SET age = ?, phone = ? WHERE mid = ? ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, mvo.getAge()); // age = ? 를 mapping
			pstmt.setString(2, mvo.getPhone()); // phone = ? 를 mapping
			pstmt.setString(3, mvo.getMid()); // mid = ? 를 mapping
			int result = pstmt.executeUpdate();
			if (result == 1) { // 정상 회원 수정 시 true 반환
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt); // pstmt 닫음
		}
		return false;// 그렇지 않으면 false반환
	}// update end

	// 회원삭제
	public boolean delete(String mid) { // delete는 mid만 받아도 됨
		// 매개변수로 넘겨받은 데이터를 t_member 테이블에서 삭제
		try {
			// delete 쿼리문, JDBCTest.java에서 데리고 온 코드들
			query = " DELETE t_member WHERE mid = ? "; // queary를 먼저 선언하는 preparedStatement
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, mid); // mid = ? 를 mapping
			int result = pstmt.executeUpdate();
			if (result == 1) { // 정상 회원 삭제 시 true 반환
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt); // pstmt 닫음
		}
		return false;// 그렇지 않으면 false반환
	}// delete end

}// class end
