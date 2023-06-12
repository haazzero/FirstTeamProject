package hr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.util.DBConn;
import hr.vo.HistoryVO;

public class HistoryDAO {

	private String query;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//히스토리 등록
	public boolean hisInsert(HistoryVO hivo) {
		
		try {
			query = " INSERT INTO JOB_HISTORY VALUES(SEQ_JOB_HISTORY_HIS_NO.NEXTVAL,?,?,?,?,?,?) ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, hivo.getDname());
			pstmt.setString(2, hivo.getPosition());
			pstmt.setString(3, hivo.getEmid());
			pstmt.setString(4, hivo.getLeaveStart());
			pstmt.setString(5, hivo.getLeaveFin());
			pstmt.setString(6, hivo.getRemark());
			
			int result = pstmt.executeUpdate();
			if (result == 1) { // 히스토리 등록 성공 시
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt);
		}
		return false;// 그렇지 않으면 false반환
		
	}// insert end
	
	//히스토리 전체목록
	public List<HistoryVO> hisSelect() {
		
		List<HistoryVO> hivoList = new ArrayList<>();
		HistoryVO hivo = null;
		try {
			query = " SELECT emid, dname, remark FROM job_history ORDER BY emid ASC "; 
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery(); 
			while (rs.next() == true) { 
				hivo = new HistoryVO(); 
				hivo.setEmid(rs.getString("emid"));
				hivo.setDname(rs.getString("dname"));
				hivo.setRemark(rs.getString("remark"));

				hivoList.add(hivo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt); // DBconn에 있는 close 메서드 호출
		}
		return hivoList;
	}
	
	//히스토리 개별 조회     직원이름, 직원번호, 부서코드, 시작 날짜, 끝 날짜,구분
	public List<HistoryVO> hisSelect(String emid) {
		HistoryVO hivo = null;		
		List<HistoryVO> hivoList = new ArrayList<>(); 
		try {	
			query = " SELECT his_no, employees.emid, name, employees.dname, leavestart, leavefin, remark "
					+ " FROM job_history, employees "
					+ " WHERE job_history.emid = employees.emid AND employees.emid = ? ";			
			pstmt = DBConn.getConnection().prepareStatement(query);	
			pstmt.setString(1, emid);								
			rs = pstmt.executeQuery();								
			
	        while (rs.next()) {
	            hivo = new HistoryVO();
	            hivo.setEmid(rs.getString("emid"));
	            hivo.setName(rs.getString("name"));
	            hivo.setDname(rs.getString("dname"));
	            hivo.setLeaveStart(rs.getString("leavestart"));
	            hivo.setLeaveFin(rs.getString("leavefin"));
	            hivo.setRemark(rs.getString("remark"));

	            hivoList.add(hivo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(rs, pstmt);
   }
	    return hivoList;

	}
	
	//히스토리 상세 조회		   직원이름, 직원번호, 부서코드, 시작 날짜, 끝 날짜, 구분
	public HistoryVO hisSelect(int hno) {
		HistoryVO hivo = null; // 변수의 초기화
		try {
			query = " SELECT his_no, emid, name, dname, leavestart, leavefin, remark "
					+ " FROM job_history, employees "
					+ " WHERE job_history.emid = employees.emid AND his_no = ? ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, hno);
			
			rs = pstmt.executeQuery(); 
			if (rs.next()==true) { 
				hivo = new HistoryVO();
				hivo.setHno(rs.getInt("his_no"));
				hivo.setEmid(rs.getString("emid"));
	            hivo.setName(rs.getString("name"));
				hivo.setDname(rs.getString("dname")); 
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
	public boolean hisUpdate(HistoryVO hivo) {
		
		try {
			query = " UPDATE Job_History SET leavestart = ?, leavefin = ?, remark = ? WHERE his_no = ? ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, hivo.getLeaveStart()); 
			pstmt.setString(2, hivo.getLeaveFin());  
			pstmt.setString(3, hivo.getRemark());
			pstmt.setInt(4, hivo.getHno()); 
			
			int result = pstmt.executeUpdate();
			if (result == 1) { // 정상 수정 시 true 반환
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt); // pstmt 닫음
		}
		return false;// 그렇지 않으면 false반환
	}// update end
	
	//히스토리 삭제
	public boolean hisDelete(int hno) {
		
		try {
			query = " DELETE job_history WHERE his_no = ? ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, hno); 
			
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

		
}