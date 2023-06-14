package hr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.util.DBConn;
import hr.vo.ReviewVO;
import hr.vo.SalaryInfoVO;
import hr.vo.SalaryPaymentVO;

public class SalaryPaymentDAO {

	private String query;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// 급여 지급내역 관련 View 데이터 조회
	// SALARY_PAYMENT에 BONUS를 주기 위해선 REVIEW의 GRADE와 EMID만을 보게함
	public List<ReviewVO> getReviewVO() {
		List<ReviewVO> reviewList = new ArrayList<>();
		String query = "SELECT * FROM review_view"; // View의 이름
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewVO rvo = new ReviewVO();
				rvo.setGrade(rs.getString("grade").charAt(0)); // 인사평가등급 char 타입으로 가져옴
				rvo.setRemark(rs.getString("remark"));
				rvo.setEmid(rs.getString("emid"));
				reviewList.add(rvo);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviewList;
	}

	// 급여 지급 내역 등록
	public boolean salPayInsert(SalaryPaymentVO spvo) {
		// salary_payment에 db 삽입
		// (1열엔 salpay_no 시퀀스)
		query = " INSERT INTO salary_payment (salpay_no, emid, pay_date, bonus, total) VALUES (SEQ_SALARY_PAYMENT_SALPAY_NO.nextval, ?, ?, ?, ?) ";
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			// SALPAY_NO((sequence, no set)
			// ? 순서대로 mapping
			pstmt.setString(1, spvo.getEmid()); // 직원번호
			pstmt.setString(2, spvo.getPayDate()); // 지급날짜
			pstmt.setInt(3, spvo.getBonus()); // 보너스
			pstmt.setInt(4, spvo.getTotal()); // 총급여
			int result = pstmt.executeUpdate(); // 실행했을 때 1이 나와야 성공
			if (result == 1) {// 정상적으로 회원가입 성공 시 true 반환
				return true;
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

	// 급여 지급내역 전체 목록
	public List<SalaryPaymentVO> salPaySelect() {
		List<SalaryPaymentVO> spvolist = new ArrayList<>();
		SalaryPaymentVO spvo = null;
		try {
			// (join)employees, salary_step, salary_payment
			query = "SELECT salary_payment.salpay_no, salary_payment.emid, employees.name, salary_payment.pay_date, salary_payment.bonus, (salary_step.sal + salary_payment.bonus) AS total, salary_step.sal "
					+ "FROM salary_payment " + "JOIN employees ON employees.emid = salary_payment.emid "
					+ "JOIN salary_step ON employees.position = salary_step.position AND employees.hobong = salary_step.sal_grade "
					+ "ORDER BY salary_payment.emid ASC "; //// 목록은 emid 오름차순
			pstmt = DBConn.getConnection().prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				spvo = new SalaryPaymentVO();// 레코드를 저장할 객체
				spvo.setSpno(rs.getInt("salpay_no")); // 급여지급내역번호
				spvo.setEmid(rs.getString("emid")); // 직원번호
				spvo.setName(rs.getString("name")); // 직원이름
				spvo.setPayDate(rs.getString("pay_date")); // 급여지급내역날짜
				spvo.setSal(rs.getInt("sal")); // 기본급
				spvo.setBonus(rs.getInt("bonus")); // 상여금
				spvo.setTotal(rs.getInt("total")); // 총 급여(기본급+상여금)
				spvolist.add(spvo); // list 객체에 spvo 데이터 추가
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
		return spvolist;
	}// List<SalaryPaymentVO> salPaySelect() end

	// 급여 지급내역 개별 조회
	public SalaryPaymentVO salPaySelect(String emid) { // 급여 지급내역 '전체'조회에서 개별 조회 할 직원번호를 입력받아 select
		List<SalaryPaymentVO> spvolist = new ArrayList<>();
		SalaryPaymentVO spvo = null;
		try {
			// (join)employees, salary_step, salary_info

			query = "SELECT salary_payment.salpay_no, salary_payment.emid, employees.name, salary_payment.pay_date, salary_payment.bonus, (salary_step.sal + salary_payment.bonus) AS total, salary_step.sal "
					+ "FROM salary_payment " + "JOIN employees ON employees.emid = salary_payment.emid "
					+ "JOIN salary_step ON employees.position = salary_step.position AND employees.hobong = salary_step.sal_grade " // 기본급
					+ "WHERE salary_payment.emid=? " // where절로 입력받은 emid를 조회
					+ "ORDER BY salary_payment.emid ASC "; // 목록은 emid 오름차순

			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, emid);
			rs = pstmt.executeQuery();

			while (rs.next()) { // 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				spvo = new SalaryPaymentVO(); // 레코드를 저장할 객체
				spvo.setSpno(rs.getInt("salpay_no"));
				spvo.setEmid(rs.getString("emid"));
				spvo.setName(rs.getString("name"));
				spvo.setPayDate(rs.getString("pay_date"));
				spvo.setSal(rs.getInt("sal"));
				spvo.setBonus(rs.getInt("bonus"));
				spvo.setTotal(rs.getInt("total")); // 위와 동일
				spvolist.add(spvo);
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
		return spvo;
	}// 개별조회 end

	// 급여 지급내역 상세 목록 조회
	public SalaryPaymentVO salPaySelect(int spno) { // 급여 지급내역 개별 조회에서 지급내역번를 입력받아 그에 맞는 행만 출력
		SalaryPaymentVO spvo = null; // 변수 초기화
		try {
			query = "SELECT salary_payment.salpay_no, salary_payment.emid, employees.name, salary_payment.pay_date, salary_payment.bonus, (salary_step.sal + salary_payment.bonus) AS total, salary_step.sal "
					+ "FROM salary_payment " + "JOIN employees ON employees.emid = salary_payment.emid "
					+ "JOIN salary_step ON employees.position = salary_step.position AND employees.hobong = salary_step.sal_grade " // 기본급
					+ "WHERE salary_payment.salpay_no=? " // where절로 입력받은 salpay_no을 조회
					+ "ORDER BY salary_payment.emid ASC ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, spno);
			rs = pstmt.executeQuery();

			while (rs.next()) { // 조회되는 레코드가 있다면 VO객체를 생성하여 해당 레코드 값을 저장
				spvo = new SalaryPaymentVO();
				spvo.setSpno(rs.getInt("salpay_no"));
				spvo.setEmid(rs.getString("emid"));
				spvo.setName(rs.getString("name"));
				spvo.setPayDate(rs.getString("pay_date"));
				spvo.setSal(rs.getInt("sal"));
				spvo.setBonus(rs.getInt("bonus"));
				spvo.setTotal(rs.getInt("total")); // 총 급여 계산
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
		return spvo;
	}// 상세 조회 end

	// 급여 지급 내역 수정
	// 급여 지급 내역 상세 조회의 spno(1개의 행)의 지급일시, 상여금을 수정
	public boolean salPayUpdate(int spno, String payDate, int bonus) {
		try {
			query = " UPDATE salary_payment SET pay_date=?, bonus=? WHERE salpay_no = ? ";
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setString(1, payDate); // 지급일시
			pstmt.setInt(2, bonus); // 상여금
			pstmt.setInt(3, spno); // 급여지급내역번호

			int result = pstmt.executeUpdate(); // 실행했을 때 1이 나와야 성공
			if (result == 1) // 정상적으로 수정 시 true 반환
				return true;

		} catch (SQLException e) { // 예외 처리
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt); // pstmt만 닫음
		}
		return false; // 실패 시 false 반환
	}// update()end

	// 급여 지급 내역 삭제
	// 급여 지급 내역 상세 조회의 spno(1개의 행)의 정보 삭제
	public boolean salPayDelete(int spno) {
		query = "DELETE FROM salary_payment WHERE salpay_no=?";
		try {
			pstmt = DBConn.getConnection().prepareStatement(query);
			pstmt.setInt(1, spno);

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
}//delete end
