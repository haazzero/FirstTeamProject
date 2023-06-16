package hr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.main.HrAdminMain;
import hr.util.DBConn;
import hr.vo.HistoryVO;

public class HistoryDAO {

	private String query;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//히스토리 등록
	public boolean hisInsert(HistoryVO hivo) {
		try {		// 히스토리 테이블에 데이터 삽입 (단, 첫번째 열엔 시퀀스 적용)
			query = " INSERT INTO JOB_HISTORY VALUES(SEQ_JOB_HISTORY_HIS_NO.NEXTVAL,?,?,?,?,?,?) ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, hivo.getDname());		// hno는 시퀀스로 자동으로 올라가서 setter 필요없음
			pstmt.setString(2, hivo.getPosition());		// 각 물음표에 번호대로 데이터 바인딩 
			pstmt.setString(3, hivo.getEmid());
			pstmt.setString(4, hivo.getLeaveStart());
			pstmt.setString(5, hivo.getLeaveFin());
			pstmt.setString(6, hivo.getRemark());
			
			int result = pstmt.executeUpdate();
			if (result == 1) { 							// 실행했을 때 1이 나와야 성공
				return true;							// 정상적으로 정보 등록 성공 시 true 반환
			}
		} catch (SQLException e) {						// 외래키 제약조건을 위반하면 메시지 출력 후 다시 등록으로 돌아감
			if (e.getMessage().contains("ORA-02291: integrity constraint")) {
				System.out.println("---------------------------------------------");
				System.out.println("		해당하는 직원정보가 없습니다.");
				System.out.println("---------------------------------------------");
		    } else {									// 그 외의 오류는 자세히 출력되게 함
			e.printStackTrace();
		    }
		} finally {
			DBConn.close(pstmt);
		}
		return false;// 그렇지 않으면 false반환
		
	}// insert end
	
	//히스토리 전체 목록
	public List<HistoryVO> hisSelect() {
		List<HistoryVO> hivoList = new ArrayList<>();
		HistoryVO hivo = null;
		try {		// 직원번호로 조인하여 직원번호, 부서이름, 직급이름, 비고를 출력
			query = " SELECT emid, dname, position, remark FROM job_history ORDER BY emid ASC "; 
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery(); 
			while (rs.next() == true) { 	// 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				hivo = new HistoryVO(); 	// 레코드를 저장할 객체
				hivo.setEmid(rs.getString("emid"));
				hivo.setDname(rs.getString("dname"));
				hivo.setPosition(rs.getString("position"));
				hivo.setRemark(rs.getString("remark"));

				hivoList.add(hivo);			// List 객체에 hivo 데이터 추가 (직원번호, 부서이름, 직급이름, 비고)
			}
		} catch (SQLException e) {
			if (e.getMessage().contains("ORA-02291: integrity constraint")) {
				System.out.println("---------------------------------------------");
				System.out.println("		해당하는 직원정보가 없습니다.");
				System.out.println("---------------------------------------------");
		    } else {
			e.printStackTrace();
		    }
		} finally {
			DBConn.close(rs, pstmt); // DBconn에 있는 close 메서드 호출
		}
		return hivoList;
	}
	
	//히스토리 개별 조회     
	public List<HistoryVO> hisSelect(String emid) {		// 메인에서 직원번호를 입력받아서 그에 맞는 리스트를 select
		HistoryVO hivo = null;		
		List<HistoryVO> hivoList = new ArrayList<>(); 
		try {		// 직원번호로 조인하여 히스토리번호, 직원이름, 직원번호, 부서코드, 시작일자, 종료일자, 구분을 출력
			query = " SELECT his_no, employees.emid, name, employees.dname, job_history.position, leavestart, leavefin, remark "
					+ " FROM job_history, employees "
					+ " WHERE job_history.emid = employees.emid AND employees.emid = ? ";			
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setString(1, emid);								
			rs = pstmt.executeQuery();								
			
	        while (rs.next()) {						// 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
	            hivo = new HistoryVO();				// 레코드를 저장할 객체
	            hivo.setHno(rs.getInt("his_no"));
	            hivo.setEmid(rs.getString("emid"));
	            hivo.setName(rs.getString("name"));
	            hivo.setDname(rs.getString("dname"));
	            hivo.setPosition(rs.getString("position"));
	            hivo.setLeaveStart(rs.getString("leavestart"));
	            hivo.setLeaveFin(rs.getString("leavefin"));
	            hivo.setRemark(rs.getString("remark"));

	            hivoList.add(hivo);					// List 객체에 hivo 데이터 추가 (히스토리번호, 직원번호, 직원이름, 부서, 직급, 시작일자, 종료일자, 구분)
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(rs, pstmt);
   }
	    return hivoList;

	}
	
	//히스토리 상세 조회		   
	public HistoryVO hisSelect(int hno) {	// 메인에서 승인번호를 입력받아 개별 조회한 목록에서 그에 맞는 행만 출력
		HistoryVO hivo = null; // 변수의 초기화
		try {		// 직원번호로 조인하여 히스토리번호, 직원이름, 직원번호, 부서이름, 시작일자, 종료일자, 구분을 출력
			query = " SELECT his_no, employees.emid, name, employees.dname, job_history.position, leavestart, leavefin, remark "
					+ " FROM job_history, employees "
					+ " WHERE job_history.emid = employees.emid AND his_no = ? ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, hno);
			
			rs = pstmt.executeQuery(); 
			if (rs.next()==true) { 			// 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				hivo = new HistoryVO();		// 레코드를 저장할 객체
				hivo.setHno(rs.getInt("his_no"));
				hivo.setEmid(rs.getString("emid"));
	            hivo.setName(rs.getString("name"));
				hivo.setDname(rs.getString("dname")); 
				hivo.setPosition(rs.getString("position")); 
				hivo.setLeaveStart(rs.getString("leavestart"));
				hivo.setLeaveFin(rs.getString("leavefin")); 
				hivo.setRemark(rs.getString("remark")); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt); // DBconn에 있는 close 메서드 호출
		}
		return hivo;
	}	
	
	//히스토리 수정
	public boolean hisUpdate(HistoryVO hivo) {		// 상세 조회에서 사용된 히스토리번호의 행을 수정
		String query = " UPDATE Job_History SET ";
		String queryw = " WHERE his_no = ? ";
		String v = "";
		
		if(HrAdminMain.input == 1) {				// 같은 쿼리문이 계속 반복되어 하나의 메서드로 묶기 위해 if문 사용
			query += " dname = ? " + queryw;		// 수정할 내용의 번호에 맞게 쿼리문과 바인딩할 변수를 변경
			v = hivo.getDname();					// 1 입력 시 부서이름 수정하는 쿼리문
		} else if(HrAdminMain.input == 2) {			// 2 입력 시 직급이름 수정하는 쿼리문
			query += " position = ? " + queryw;
			v = hivo.getPosition();
		} else if(HrAdminMain.input == 3) {			// 3 입력 시 시작일자 수정하는 쿼리문
			query += " leavestart = ? " + queryw;
			v = hivo.getLeaveStart();
		} else if(HrAdminMain.input == 4) {			// 4 입력 시 종료일자 수정하는 쿼리문
			query += " leavefin = ? " + queryw;
			v = hivo.getLeaveFin();
		} else if(HrAdminMain.input == 5) {			// 5 입력 시 구분 수정하는 쿼리문
			query += " remark = ? " + queryw;
			v = hivo.getRemark();
		}	// if end
		
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, v); 
			pstmt.setInt(2, hivo.getHno()); 
			
			int result = pstmt.executeUpdate();		// 실행했을 때 1이 나와야 성공
			if (result == 1) { 						// 정상적으로 업데이트 시 true 반환
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt); // pstmt 닫음
		}
		return false;					// 그렇지 않으면 false반환
		
	}// update end
	
	//히스토리 삭제
	public boolean hisDelete(int hno) {		// 상세 조회에서 사용된 히스토리번호의 행을 삭제
		try {
			query = " DELETE job_history WHERE his_no = ? ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, hno); 
			
			int result = pstmt.executeUpdate();		// 실행했을 때 1이 나와야 성공
			if (result == 1) { 						// 정상적으로 삭제 성공 시 true 반환
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt); // pstmt 닫음
		}
		return false;// 그렇지 않으면 false반환
	}// delete end

		
}
