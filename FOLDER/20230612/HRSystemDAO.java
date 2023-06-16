package hr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hr.util.DBConn;
import hr.vo.HRSystemVO;

public class HRSystemDAO {

   private String query;
   private PreparedStatement pstmt;
   private ResultSet rs;
   private HRSystemVO hrsysVO;
   
   
   public boolean loginCheck(String id, String pw) {
	   try {
			query = "SELECT COUNT (*) FROM HR_SYSTEM WHERE ID = ? AND PW = ?";// 로그인 하는 쿼리 작성
			pstmt = DBConn.getConnection().prepareStatement(query);

			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery(); // 매개변수로 넘겨받은 유효한 사용자인지 확인

			if (rs.next()) { // 조회된 레코드가 있다면
				if (rs.getInt(1) == 1) { // COUNT로 사용했으니 1이 넘겨오면 사용자가 있다. -> 로그인 가능
					return true; // COUNT 로 0 이 넘어오면 사용자 존재 안함 -> 로그인 불가 메시지
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, pstmt); // 사용한 코드가 rs와 psmt다. 이 두개를 닫기
		}
	    return false;
	   
   } // loginCheck end
   
   public boolean pwUpdate(HRSystemVO hrsysVO) {
	   try {  
		   query = " UPDATE HR_SYSTEM SET PW =? WHERE ID = ?";
		   
		   pstmt = DBConn.getConnection().prepareStatement(query);
		
		   pstmt.setString(1, hrsysVO.getPw());   // 쿼리 ? 개수만큼 값을 입력
		   pstmt.setString(2, hrsysVO.getId());
		   
		  
		   int result = pstmt.executeUpdate();
		 
		   if(result ==1) {   // 정상적으로 비밀번호 변경시 true 반환
			  return true; }
	  		} catch (SQLException e) {
	  			e.printStackTrace();
	  		}finally {   
	  		 DBConn.close(pstmt);
	  		}   
	   		return false;
      
   } // update end
   
   
}
