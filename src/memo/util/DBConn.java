package memo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	private static Connection con; // static(공유할게)
	// private 생성자이므로 다른 곳에서 new DBConn 불가능
	// 커넥션을 하나만 만들어서 여러 애가 쓸 건데, public 생성자면 생성할 때마다 커넥션이 생겨버림
	// 그래서 생성자를 잠궈놓고, getConnection만 public static로 열어둬서 이 메서드만 공유해서 사용하도록
	// 1. 외부 접근 불가한 기본생성자 작성

	private DBConn() {
	}

	// 2. Connection 객체가 null인 경우에만
	// 객체를 생성하고 반환하는 공유메서드
	// getConnection()작성 (접근 제한 X)
	// *객체지향 : 유지보수 용이, 코드를 부품화
	public static Connection getConnection() {
		String driver = "oracle.jdbc.OracleDriver";
		String uri = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "dev";
		String password = "1111";

		if (con == null) {
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(uri, username, password);
			} catch (ClassNotFoundException e) { // catch만 추가
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}// getConnection end

	// 4. PreparedStatement를매개변수로 받아서
	public static void close(PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// close end

	// 4. PreparedStatement와
	// 5. Select에서 사용하는 Result set 객체를 매개변수로 받아서 닫는 공유 메서드 close() 작성
	public static void close(ResultSet rs, PreparedStatement pstmt) {
		try {
			if (rs != null) // 주의 ! 역순으로 닫아야함
				rs.close();
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// close end

	// 6. 매개변수 없이 connection 클래스의 객체 con을 종료하는 공유 메서드 close() 작성
	public static void close() {
		try {
			if (con != null) // null을 사용하는 이유?
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}// class end
