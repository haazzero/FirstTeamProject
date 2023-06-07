package memo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import memo.main.MemberMain;
import memo.util.DBConn;
import memo.vo.MemoVO;

public class MemoDAO {
	private String query; // 쿼리문 저장 필드
	private PreparedStatement pstmt;
	private ResultSet rs; // selcect쿼리 저장 필드
	
	
	//1. 메모 입력
	public boolean insert(MemoVO mevo) {
		try {
			query = " INSERT INTO t_memo VALUES(memo_seq.NEXTVAL,?,?,SYSDATE) "; // queary를 먼저 선언하는 preparedStatement
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, mevo.getMemo());
			pstmt.setString(2, mevo.getMid());
			
			System.out.print(mevo.getMid());
			int result = pstmt.executeUpdate();
			if (result == 1) { // 정상 메모 작성 성공 시
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt);
		}
		return false;// 그렇지 않으면 false반환
	}// insert end

	// 전체 메모 목록조회
	//- 접근제한 X, 반환타입 : MemberVo를 저장한 List, 매개변수 : X ()
	// 기능 : t_memo 테이블의 전체 데이터를 조회하여 List에 저장한 후 반환
	// overload select !!!
	public List<MemoVO> select() {
		List<MemoVO> mevoList = new ArrayList<>();
		MemoVO mevo = null;
		try {
			// 1. select 쿼리문(테이블 전체를 조회)
			query = " SELECT ROWNUM, mno, memo, mid, reg_date FROM t_memo ORDER BY mno ASC "; // query를 먼저 선언하는 preparedStatement
			// 2. 쿼리문을 실행할 prepared statement 객체
			pstmt = DBConn.getConnection().prepareStatement(query);
			// 3. ?가 없기 떄문에 바인딩 할 것이 없음
			// 4. 쿼리를 실행
			// next의 기능 : 1개의 다음 행으로 커서를 움직임
			rs = pstmt.executeQuery(); // rs.next 시 다음 값이 있다면 true, 없다면 null 반환
			// if는 1개 조회할 때, while은 전체조회 할 때,
			while (rs.next()==true) { // 조회된 레코드들이 있다면
				mevo = new MemoVO(); // MemoVO객체를 생성, 아니면 생성X
				mevo.setRownum(rs.getInt("ROWNUM"));
				mevo.setMno(rs.getInt("mno")); // 해당 레코드 값을 저장
				mevo.setMemo(rs.getString("memo")); // 해당 레코드 값을 저장
				mevo.setMid(rs.getString("mid"));
				mevo.setRegDate(rs.getDate("reg_date")); // 해당 레코드 값을 저장

				// List 객체에 추가=List에 저장
				mevoList.add(mevo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt); // DBconn에 있는 close 메서드 호출
		}
		return mevoList;
	}
	
	// mid가 작성한 memolist 조회
	public List<MemoVO> select(String mid) {
	    List<MemoVO> mevoList = new ArrayList<>();
	    try {
	        query = "SELECT ROWNUM, mno, memo, mid, reg_date FROM t_memo WHERE mid=? ORDER BY mno ASC";
	        pstmt = DBConn.getConnection().prepareStatement(query);
	        pstmt.setString(1, mid);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            MemoVO mevo = new MemoVO();
	            mevo.setRownum(rs.getInt("ROWNUM"));
	            mevo.setMno(rs.getInt("mno"));
	            mevo.setMemo(rs.getString("memo"));
	            mevo.setMid(rs.getString("mid"));
	            mevo.setRegDate(rs.getDate("reg_date"));

	            mevoList.add(mevo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(rs, pstmt);
	    }
	    return mevoList;
	}

	// 상세 메모 조회(mno) - 매개변수로 게시물 번호를 넘겨받아 해당 레코드를
	// 객체에 저장하여 반환하는
	public MemoVO select(int mno) {
		MemoVO mevo = null; // 변수의 초기화
		try {
			// 1. select 쿼리문(mno를 조회)
			query = " SELECT ROWNUM, mno, memo, mid, reg_date FROM t_memo WHERE mno=? ORDER BY mno ASC "; // queary를 먼저 선언하는 preparedStatement
			// 2. 쿼리문을 실행할 prepared statement 객체
			pstmt = DBConn.getConnection().prepareStatement(query);
			// 3. id에 해당하는 값 하나를 바인딩
			pstmt.setInt(1, mno);
			// 4. 쿼리를 실행
			// next의 기능 : 1개의 다음 행으로 커서를 움직임
			rs = pstmt.executeQuery(); // rs.next 시 다음 값이 있다면 true, 없다면 null 반환
			if (rs.next()==true) { // 조회된 레코드가 있다면
				mevo = new MemoVO(); // MemberVO객체를 생성, 아니면 생성X
	            mevo.setRownum(rs.getInt("ROWNUM"));
				mevo.setMno(rs.getInt("mno")); // 해당 레코드 값을 저장
				mevo.setMemo(rs.getString("memo")); // 해당 레코드 값을 저장
				mevo.setMid(rs.getString("mid"));
				mevo.setRegDate(rs.getDate("reg_date")); // 해당 레코드 값을 저장
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt); // DBconn에 있는 close 메서드 호출
		}
		return mevo;
	}

	// 메모 수정
	public boolean update(MemoVO mevo) {
		try {
			query = " UPDATE t_memo SET memo = ? WHERE mno = ? ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, mevo.getMemo()); // memo = ? 를 mapping
			pstmt.setInt(2, mevo.getMno()); // mno = ? 를 mapping
			int result = pstmt.executeUpdate();
			if (result == 1) { // 정상 메모 수정 시 true 반환
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt); // pstmt 닫음
		}
		return false;// 그렇지 않으면 false반환
	}// update end

	// 메모 삭제
	public boolean delete(int mno) { // delete는 mno만 받아도 됨
		try {
			query = " DELETE t_memo WHERE mno = ? ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, mno); // mno = ? 를 mapping
			int result = pstmt.executeUpdate();
			if (result == 1) { 
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
