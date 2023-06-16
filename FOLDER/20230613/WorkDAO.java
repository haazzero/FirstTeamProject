package hr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.main.HrAdminMain;
import hr.util.DBConn;
import hr.vo.WorkVO;

public class WorkDAO {

	private String query;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 근태 기록 등록
	public boolean workInsert(WorkVO wvo) {
				// 근태 테이블에 데이터 삽입
		query = "INSERT INTO work VALUES (?,?,?,?,?,?,?,?)";
		try {	
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setString(1, wvo.getEmid());	// 각 물음표에 번호대로 데이터 바인딩
			pstmt.setInt(2, wvo.getCont());
			pstmt.setInt(3, wvo.getLate());
			pstmt.setInt(4, wvo.getEarly());
			pstmt.setInt(5, wvo.getAbs());
			pstmt.setInt(6, wvo.getAvAnn());
			pstmt.setInt(7, wvo.getUsedAnn());
			pstmt.setInt(8, wvo.getUnusedAnn());
			
			int result = pstmt.executeUpdate();	// 실행했을 때 1이 나와야 성공
			if (result == 1) 					// 정상적으로 정보 등록 성공 시 true 반환
				return true; 
			
		} catch (SQLException e) {				// 고유 제약조건을 위반하면 메시지 출력 후 다시 등록으로 돌아감
		    if (e.getMessage().contains("ORA-00001: unique constraint")) {
				System.out.println("---------------------------------------------");
				System.out.println("  이미 존재하는 직원번호 입니다. 다른 값을 입력해주세요.");
				System.out.println("---------------------------------------------");
		    } 									// 외래키 제약조건을 위반하면 메시지 출력 후 다시 등록으로 돌아감
		    else if (e.getMessage().contains("ORA-02291: integrity constraint")) {
				System.out.println("---------------------------------------------");
				System.out.println("		해당하는 직원정보가 없습니다.");
				System.out.println("---------------------------------------------");
		    } else {							// 그 외의 오류는 자세히 출력되게 함
		    	e.printStackTrace();
		    }
		} finally {
			DBConn.close(pstmt);	// con을 생성하지 않았기 때문에 pstmt만 닫음
		}
		return false;				// 그렇지 않으면 false 반환
		
	}
	
	// 근태 기록 전체 목록
	public List<WorkVO> WorkSelect() {
		List<WorkVO> wvoList = new ArrayList<>();
		WorkVO wvo = null;
		try {			// 직원번호로 조인하여 직원번호, 직원이름, 지각일수, 조퇴일수, 결근일수, 잔여연차일수를 출력
			query = " SELECT employees.emid, name, late, early, abs, unused_ann FROM work, employees "
					+ " WHERE work.emid = employees.emid ORDER BY work.emid ASC ";
			pstmt = DBConn.getConnection().prepareStatement(query);	
			rs = pstmt.executeQuery();
			while(rs.next()) {			// 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				wvo = new WorkVO();		// 레코드를 저장할 객체
				wvo.setEmid(rs.getString("emid"));
				wvo.setName(rs.getString("name"));
				wvo.setLate(rs.getInt("late"));
				wvo.setEarly(rs.getInt("early"));
				wvo.setAbs(rs.getInt("abs"));
				wvo.setUnusedAnn(rs.getInt("unused_ann"));
				
				wvoList.add(wvo);		// List 객체에 wvo 데이터 추가
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			DBConn.close(rs, pstmt);	// 사용했던 rs, pstmt 순으로 닫음
		}
		return wvoList;
		
	}
	
	// 근태 기록 개별 조회
	public WorkVO WorkSelect(String emid) {
		WorkVO wvo = null;				
		try {		// 직원번호로 조인하여 직원번호, 직원이름, 지각일수, 조퇴일수, 결근일수, 사용가능연차일수, 사용연차일수, 잔여연차일수를 출력
			query = " SELECT work.emid, name, cont, late, early, abs, av_ann, used_ann, unused_ann "
					+ "	FROM WORK, employees "
					+ "	WHERE work.emid = employees.emid "
					+ "	AND work.emid = ? ";			
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setString(1, emid);									
			rs = pstmt.executeQuery();								
			
			while(rs.next()) {			// 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				wvo = new WorkVO();		// 레코드를 저장할 객체
				wvo.setEmid(rs.getString("emid"));
				wvo.setName(rs.getString("name"));
				wvo.setCont(rs.getInt("cont"));
				wvo.setLate(rs.getInt("late"));
				wvo.setEarly(rs.getInt("early"));
				wvo.setAbs(rs.getInt("abs"));
				wvo.setAvAnn(rs.getInt("av_ann"));
				wvo.setUsedAnn(rs.getInt("used_ann"));
				wvo.setUnusedAnn(rs.getInt("unused_ann"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			DBConn.close(rs, pstmt);	// 사용했던 rs, pstmt 순으로 닫음
		}
		return wvo;	
		
	}
	
	// 근태 기록 수정
	public boolean WorkUpdate(WorkVO wvo) {		// 개별 조회에서 사용된 직원번호의 행을 수정
		   String query = " UPDATE work SET ";	// 근속년수, 지각, 조퇴, 결근일수 수정 (1개 값만 수정)
		   String queryw = " WHERE emid = ? ";
		   int v = 0;
		   
		   if(HrAdminMain.input == 1) {			// 같은 쿼리문이 계속 반복되어 하나의 메서드로 묶기 위해 if문 사용
			   query += " cont = ? " + queryw;	// 수정할 내용의 번호에 맞게 쿼리문과 바인딩할 변수를 변경
			   v = wvo.getCont();				// 1 입력 시 근속년수 수정
		   } else if (HrAdminMain.input == 2) {	// 2 입력 시 지각일수 수정
			   query += " late = ? " + queryw;
			   v = wvo.getLate();
		   } else if(HrAdminMain.input == 3) {	// 3 입력 시 조퇴일수 수정
			   query += " early = ? " + queryw;
			   v = wvo.getEarly();
		   } else if(HrAdminMain.input == 4) {	// 4 입력 시 결근일수 수정
			   query += " abs = ? " + queryw;
			   v = wvo.getAbs();
		   } // if end

		try {
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setInt(1, v);
			pstmt.setString(2, wvo.getEmid());
			
			int result = pstmt.executeUpdate();	// 실행했을 때 1이 나와야 성공
			if (result == 1) 					// 정상적으로 업데이트 시 true 반환
				return true; 

		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt);	// pstmt만 닫음
		}
		
		return false;				// 실패 시 false 반환
		
	}
	
	// 근태 기록 수정
	public boolean WorkUpdate1(WorkVO wvo) {	// 개별 조회에서 사용된 직원번호의 행을 수정
		   String query = " UPDATE work SET ";	// 사용가능연차일수, 사용한연차일수 수정 (2개 값 수정)
		   String queryw = " , unused_ann = ? WHERE emid = ? ";	
		   int v = 0;
		if(HrAdminMain.input == 5) {			// 5 입력 시 사용가능연차일수, 잔여연차일수 수정
			   query += " av_ann = ? " + queryw;
			   v = wvo.getAvAnn();
		   } else if(HrAdminMain.input == 6) {	// 6 입력 시 사용한연차일수, 잔여연차일수 수정
			   query += " used_ann = ? " + queryw;
			   v = wvo.getUsedAnn();
		   }	// if end
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setInt(1, v);
			pstmt.setInt(2, wvo.getUnusedAnn());
			pstmt.setString(3, wvo.getEmid());
			
			int result = pstmt.executeUpdate();	// 실행했을 때 1이 나와야 성공
			if (result == 1) 					// 정상적으로 업데이트 시 true 반환
				return true; 

		} catch (SQLException e) {	// 예외 처리
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt);	// pstmt만 닫음
		} return false;				// 실패 시 false 반환
		
	}
	
	// 근태 기록 삭제
	public boolean WorkDelete(String emid) {	// 개별조회에서 사용된 직원번호에 맞는 행을 삭제
		query = "DELETE FROM work WHERE emid = ?";
		try {	
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setString(1, emid);	
			
			int result = pstmt.executeUpdate();	// 실행했을 때 1이 나와야 성공
			if (result == 1) 					// 정상적으로 삭제 성공 시 true 반환
				return true; 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt);	// pstmt 닫음
		}
		return false;			// 실패 시 false 반환
		
	}
	
}
