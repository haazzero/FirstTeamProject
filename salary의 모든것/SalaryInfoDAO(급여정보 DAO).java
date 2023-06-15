package hr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.util.DBConn;
import hr.vo.SalaryInfoVO;

public class SalaryInfoDAO {

	private String query;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 급여 정보 내역 등록
	public boolean salInsert(SalaryInfoVO svo) {
		// salary_info에 db 삽입
		// (1열엔 salinfo_no 시퀀스)
		query = " INSERT INTO salary_info (salinfo_no, emid, bank, depositor, account) VALUES (SEQ_SALARY_INFO_SALINFO_NO.NEXTVAL, ?, ?, ?, ?) ";
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			// SALINFO_NO(sequence, no set)
			// ? 순서대로 mapping
			pstmt.setString(1, svo.getEmid()); // 직원번호
			pstmt.setString(2, svo.getBank()); // 은행명
			pstmt.setString(3, svo.getDepositor());// 예금주
			pstmt.setString(4, svo.getAccount());// 계좌
			int result = pstmt.executeUpdate(); // 실행했을 때 1이 나와야 성공
			if (result == 1) {
				return true; // 정상적으로 정보 등록 성공 시 true 반환
			}
		} catch (SQLException e) { // 외래키 제약조건을 위반하면 메시지 출력 후 다시 등록으로 돌아감
			if (e.getMessage().contains("ORA-02291: integrity constraint")) {
				System.out.println("---------------------------------------------");
				System.out.println("		해당하는 정보가 없습니다.");
				System.out.println("---------------------------------------------");
			} else { // 그 외의 오류는 자세히 출력되게 함
				e.printStackTrace();
			}
		} finally {
			DBConn.close(pstmt);
		}
		return false;// 그렇지 않으면 false반환
	}// insert()end

	// 일반 catch ver
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBConn.close(pstmt); // pstmt만 닫음
//		}
//		return false; // 그렇지 않으면 false 반환

	// 급여 정보 전체 목록
	public List<SalaryInfoVO> salSelect() {
		List<SalaryInfoVO> svolist = new ArrayList<>();
		SalaryInfoVO svo = null;
		try {
			// (join)employees, salary_step, salary_info
			// name(이름), sal(기본급) 출력
			query = "SELECT salary_info.salinfo_no, salary_info.emid, employees.name, salary_step.sal "
					+ " FROM salary_info " + " JOIN employees ON employees.emid = salary_info.emid "
					+ " JOIN salary_step ON employees.position = salary_step.position AND employees.hobong = salary_step.sal_grade "
					+ " ORDER BY salary_info.emid ASC "; // 목록은 emid 오름차순
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				svo = new SalaryInfoVO(); // 레코드를 저장할 객체
				svo.setSno(rs.getInt("salinfo_no")); // 급여정보번호
				svo.setEmid(rs.getString("emid")); // 직원번호
				svo.setName(rs.getString("name")); // 직원이름
				svo.setSal(rs.getInt("sal")); // 기본급
				svolist.add(svo); // list 객체에 svo 데이터 추가 (급여정보번호, 직원번호, 직원이름, 기본급)
			}
		} catch (SQLException e) { // 외래키 제약조건을 위반하면 메시지 출력 후 다시 등록으로 돌아감
			if (e.getMessage().contains("ORA-02291: integrity constraint")) {
				System.out.println("---------------------------------------------");
				System.out.println("		해당하는 정보가 없습니다.");
				System.out.println("---------------------------------------------");
			} else { // 그 외의 오류는 자세히 출력되게 함
				e.printStackTrace();
			}
		} finally {
			DBConn.close(pstmt);
		}
		return svolist;

	}// List<SalaryInfoVO> salSelect()end

	// 급여 정보 개별 조회
	public SalaryInfoVO salSelect(String emid) { // 급여 정보 '전체'조회에서 개별 조회 할 직원번호를 입력받아 select
		List<SalaryInfoVO> svolist = new ArrayList<>();
		SalaryInfoVO svo = null;
		try {
			// (join)employees, salary_step, salary_info
			// name(이름), sal(기본급), 은행명(bank), 예금주(depositor) 출력
			query = "SELECT salary_info.salinfo_no, salary_info.emid, employees.name, salary_step.sal, salary_info.bank, salary_info.depositor, salary_info.account "
					+ " FROM salary_info " + " JOIN employees ON employees.emid = salary_info.emid "
					+ " JOIN salary_step ON employees.position = salary_step.position AND employees.hobong = salary_step.sal_grade "
					+ " WHERE salary_info.emid = ? " // where절로 입력받은 emid를 조회
					+ " ORDER BY salary_info.emid ASC "; // 목록은 emid 오름차순

			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, emid);
			rs = pstmt.executeQuery();

			while (rs.next()) { // 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				svo = new SalaryInfoVO(); // 레코드를 저장할 객체
				svo.setSno(rs.getInt("salinfo_no")); // 급여정보번호
				svo.setEmid(rs.getString("emid")); // 직원번호
				svo.setName(rs.getString("name")); // 직원이름
				svo.setSal(rs.getInt("sal")); // 기본급
				svo.setBank(rs.getString("bank")); // 은행명
				svo.setDepositor(rs.getString("depositor")); // 예금주
				svo.setAccount(rs.getString("account")); // 계좌
				svolist.add(svo); // list 객체에 svo 데이터 추가 (직원번호, 직원이름, 기본급, 은행명, 예금주, 계좌번호)
			}
		} catch (SQLException e) { // 외래키 제약조건을 위반하면 메시지 출력 후 다시 등록으로 돌아감
			if (e.getMessage().contains("ORA-02291: integrity constraint")) {
				System.out.println("---------------------------------------------");
				System.out.println("		해당하는 정보가 없습니다.");
				System.out.println("---------------------------------------------");
			} else { // 그 외의 오류는 자세히 출력되게 함
				e.printStackTrace();
			}
		} finally {
			DBConn.close(pstmt);
		}
		return svo;
	}// SalaryInfoVO salSelect(String emid)end

	// 급여 정보 수정
	// 급여 정보 개별조회의 emid 1명(1개의 행)의 은행이름, 예금주, 계좌번호를 수정
	public boolean salUpdate(String emid, String bank, String depositor, String account) {
		try {
			query = "UPDATE salary_info SET bank=?, depositor=?, account=? WHERE emid=?";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, bank);
			pstmt.setString(2, depositor);
			pstmt.setString(3, account);
			pstmt.setString(4, emid);

			int result = pstmt.executeUpdate();
			if (result == 1) { // 실행했을 때 1이 나와야 성공
				return true; // 정상적으로 수정 시 true 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt); // pstmt 닫음
		}
		return false;
	}// update()end

	// 급여 정보 삭제
	// 급여 정보 개별조회의 emid 1명(1개의 행)의 정보 삭제
	public boolean salDelete(String emid) {
		query = "DELETE FROM salary_info WHERE emid=?";
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, emid);

			int result = pstmt.executeUpdate(); // 실행했을 때 1이 나와야 성공
			if (result == 1) // 정상적으로 삭제 성공 시 true 반환
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt); // pstmt 닫음
		}
		return false;
	}
}// delete()end
