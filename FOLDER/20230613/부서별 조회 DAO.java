public List<ReviewVO> revSelectD(String dno) {
	   List<ReviewVO> reviewList = new ArrayList<ReviewVO>();
	    
	    ReviewVO revo = null;
	    
	    try {
			query =   " SELECT r.review_no, e.emid, d.dno, e.position, r.grade, r.eval_date"
					+ " FROM  review r, employees e, departments d"
					+ " WHERE r.emid = e.emid(+)"
					+ " AND e.dname = d.dname(+)"
					+ " AND d.dno = ?"
					+ " ORDER BY r.review_no ASC";
			
			pstmt = DBConn.getConnection().prepareStatement(query);  // 바인딩이 없으니 이것만 사용
			pstmt.setString(1, dno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {   //. 여러 줄이니까 while 
				revo = new ReviewVO();   //  MemberVO 객체를 생성하여 
				revo.setReno(rs.getInt("review_no"));
				revo.setEmid(rs.getString("emid"));
				revo.setDno(rs.getString("dno"));						//  해당 레코드 값을 저장
				revo.setPosition(rs.getString("position"));
				revo.setGrade(rs.getString("grade"));	
				revo.setEvalDate(rs.getString("eval_date"));
			
				reviewList.add(revo);	// list 객체에 추가
			}
		}catch (SQLException e) {
 			e.printStackTrace();
 		}finally { 
 		 DBConn.close(rs, pstmt); // 사용한 코드가 rs와 psmt다. 이 두개를 닫기
 		}	
		return reviewList;
      
   }
