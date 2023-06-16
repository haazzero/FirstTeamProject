package hr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.main.HrAdminMain;
import hr.util.DBConn;
import hr.vo.YearVO;

public class YearDAO {

   private String query;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   // 연차 승인 내역 등록
   public boolean yearInsert(YearVO yvo) {		
	   			// 연차승인 테이블에 데이터 삽입 (단, 첫번째 열엔 시퀀스 적용)
		query = "INSERT INTO years VALUES (SEQ_YEARS_YEARS_NO.NEXTVAL,?,?,?,?,?)";
		try {	
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setString(1, yvo.getEmid());		// yno는 시퀀스로 자동으로 올라가서 setter 필요없음
			pstmt.setString(2, yvo.getYearDate());	// 각 물음표에 번호대로 데이터 바인딩 
			pstmt.setString(3, yvo.getYearCnt());
			pstmt.setString(4, yvo.getApp());
			pstmt.setString(5, yvo.getAppDate());
			
			int result = pstmt.executeUpdate();	// 실행했을 때 1이 나와야 성공
			if (result == 1) 					// 정상적으로 정보 등록 성공 시 true 반환
				return true; 

		} catch (SQLException e) {				// 외래키 제약조건을 위반하면 메시지 출력 후 다시 등록으로 돌아감
			if (e.getMessage().contains("ORA-02291: integrity constraint")) {
				System.out.println("---------------------------------------------");
				System.out.println("		해당하는 직원정보가 없습니다.");
				System.out.println("---------------------------------------------");
		    } else {							// 그 외의 오류는 자세히 출력되게 함
			e.printStackTrace();
		    }
		}	finally {
			DBConn.close(pstmt);	// con을 생성하지 않았기 때문에 pstmt만 닫음
		}
		return false;			// 그렇지 않으면 false 반환
      
   }
   
   // 연차 승인 내역 전체 목록 
   public List<YearVO> yearSelect() {		
		List<YearVO> yvoList = new ArrayList<>();
		YearVO yvo = null;
		try {			// 직원번호로 조인하여 직원번호, 직원이름, 연차승인자, 연차승인일자를 출력
			query = " SELECT years_no, years.emid, name, app, app_date FROM years, employees "
					+ "	WHERE employees.emid = years.emid ORDER BY years.emid ASC ";
			pstmt = DBConn.getConnection().prepareStatement(query);	
			rs = pstmt.executeQuery();
			while(rs.next()) {			// 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				yvo = new YearVO();		// 레코드를 저장할 객체
				yvo.setYno(rs.getInt("years_no"));
				yvo.setEmid(rs.getString("emid"));
				yvo.setName(rs.getString("name"));
				yvo.setApp(rs.getString("app"));
				yvo.setAppDate(rs.getString("app_date"));
				
				yvoList.add(yvo);		// List 객체에 yvo 데이터 추가 (연차승인번호, 직원번호, 이름, 승인자, 승인일자)
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			DBConn.close(rs, pstmt);	// 사용했던 rs, pstmt 순으로 닫음
		}
		return yvoList;
      
   }
   
   // 연차 승인 내역 개별 조회
   public List<YearVO> yearSelect(String emid) {	// 메인에서 직원번호를 입력받아서 그에 맞는 리스트를 select
		YearVO yvo = null;		
		List<YearVO> yvoList = new ArrayList<>(); 
		try {		// 직원번호로 조인하여 승인번호, 직원번호, 직원이름, 연차사용일자, 연차사용일수, 연차승인자, 연차승인일자를 출력
			query = " SELECT years_no, years.emid, name, years_date, years_cnt, app, app_date "
					+ "	FROM years, employees WHERE employees.emid = years.emid AND years.emid = ? ";			
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setString(1, emid);								
			rs = pstmt.executeQuery();								
			
			while(rs.next()) {		// 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				yvo = new YearVO();	// 레코드를 저장할 객체
				yvo.setYno(rs.getInt("years_no"));
				yvo.setEmid(rs.getString("emid"));
				yvo.setName(rs.getString("name"));
				yvo.setYearDate(rs.getString("years_date"));
				yvo.setYearCnt(rs.getString("years_cnt"));
				yvo.setApp(rs.getString("app"));
				yvo.setAppDate(rs.getString("app_date"));
				
				yvoList.add(yvo);	// List 객체에 yvo 데이터 추가 (연차승인번호, 직원번호, 이름, 연차일자, 일수, 승인자, 승인일자)
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			DBConn.close(rs, pstmt); // 사용했던 rs, pstmt 순으로 닫음
		}
		return yvoList;	
      
   }
   
   // 연차 승인 내역 상세 조회
   public YearVO yearSelect(int yno) {		// 메인에서 승인번호를 입력받아 개별 조회한 목록에서 그에 맞는 행만 출력
		YearVO yvo = null;		
		try {		// 직원번호로 조인하여 승인번호, 직원번호, 직원이름, 연차사용일자, 연차사용일수, 연차승인자, 연차승인일자를 출력
			query = " SELECT years_no, years.emid, name, years_date, years_cnt, app, app_date"
					+ "	FROM years, employees WHERE employees.emid = years.emid AND years_no = ? ";			
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setInt(1, yno);									
			rs = pstmt.executeQuery();								
			
			while(rs.next()) {		// 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				yvo = new YearVO();	// 레코드를 저장할 객체
				yvo.setYno(rs.getInt("years_no"));
				yvo.setEmid(rs.getString("emid"));
				yvo.setName(rs.getString("name"));
				yvo.setYearDate(rs.getString("years_date"));
				yvo.setYearCnt(rs.getString("years_cnt"));
				yvo.setApp(rs.getString("app"));
				yvo.setAppDate(rs.getString("app_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
			DBConn.close(rs, pstmt); // 사용했던 rs, pstmt 순으로 닫음
		}
		return yvo;	
	   
   }
   
   // 연차 승인 내역 수정
   public boolean yearUpdate(YearVO yvo) {		// 상세 조회에서 사용된 연차승인번호의 행을 수정
	   String query = " UPDATE years SET ";		
	   String queryw = " WHERE years_no = ? ";
	   String v = "";
	   
	   if(HrAdminMain.input == 1) {				 // 같은 쿼리문이 계속 반복되어 하나의 메서드로 묶기 위해 if문 사용
		   query += " years_date = ? " + queryw; // 수정할 내용의 번호에 맞게 쿼리문과 바인딩할 변수를 변경 
		   v = yvo.getYearDate();				 // 1 입력 시 연차사용일자 수정하는 쿼리문
	   } else if (HrAdminMain.input == 2) {		 // 2 입력 시 연차사용일수 수정하는 쿼리문
		   query += " years_cnt = ? " + queryw;
		   v = yvo.getYearCnt();
	   } else if(HrAdminMain.input == 3) {		 // 3 입력 시 연차승인자 수정하는 쿼리문
		   query += " app = ? " + queryw;
		   v = yvo.getApp();
	   } else if(HrAdminMain.input == 4) {		 // 4 입력 시 연차승인일자 수정하는 쿼리문
		   query += " app_date = ? " + queryw;
		   v = yvo.getAppDate();
	   }	// if end  
			try {
				pstmt = DBConn.getConnection().prepareStatement(query);	
				pstmt.setString(1, v);		
				pstmt.setInt(2, yvo.getYno());	 
				
				int result = pstmt.executeUpdate();	// 실행했을 때 1이 나와야 성공
				if (result == 1) 					// 정상적으로 업데이트 시 true 반환
					return true; 

			} catch (SQLException e) {	// 예외 처리
				e.printStackTrace();
			} finally {
				DBConn.close(pstmt);	// 사용한 pstmt 닫음
			} return false;				// 실패 시 false 반환
	  
   } // yearUpdate end
   
   // 연차 승인 내역 삭제
   public boolean yeardelete(int yno) {		// 상세 조회에서 사용된 연차승인번호의 행을 삭제
		query = "DELETE FROM years WHERE years_no = ?";
		try {	
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setInt(1, yno);	
			
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
