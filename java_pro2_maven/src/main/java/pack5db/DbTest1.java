package pack5db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest1 {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	public DbTest1() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("로딩 실패 :" +e);
			System.exit(0);
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test","root","seoho123");
		} catch (Exception e) {
			System.out.println("연결 실패 : "+e);
			System.exit(0);
		}
		
		try {
			stmt = conn.createStatement();
			
			// rs = stmt.executeQuery("select * from sangdata");
			// rs = stmt.executeQuery("select code,sang,su,dan from sangdata"); // 순서 바꿔도 상관 x 
			rs = stmt.executeQuery("select code,sang,su,dan from sangdata"); // 칼럼명 바꿔도 됨
			//                              1번째  2번째 3   4
			
			/* 이렇게 하면 행마다 저 문장들을 계속 써야함
			rs.next(); // 레코드 포인터 이동 / 자료 있으면 true, 없으면 false 
			String code = rs.getString("code"); // 칼럼의 이름
			String sang = rs.getString("sang");
			String su = rs.getString("su");
			String dan = rs.getString("dan");
			System.out.println(code + " " +sang + " " + su+ " "+dan);
			*/
			
			while(rs.next()) {
				String code = rs.getString("code"); // 칼럼의 이름
				String sang = rs.getString(2); // 이름대신 순서도 가능
				// String su = rs.getString("su");
				// String dan = rs.getString("dan");
				int su = rs.getInt("su");
				int dan = rs.getInt("dan");
				int keum = su * dan;
				System.out.println(code + " " +sang + " " + su+ " "+dan+" "+keum);

			}
			String sql = "select count(*) from sangdata";
			rs = stmt.executeQuery(sql);
			rs.next(); // pointer 이동
			System.out.println("건수 : " +rs.getInt(1));
			
			
		} catch (Exception e) {
			System.out.println("SQL 처리 실패 : " +e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) rs.close();
				if(conn != null) rs.close(); // 다 사용한 자원 반납하기
			} catch (Exception e2) {
			
			}
		}
	}
	public static void main(String[] args) {
		System.out.println("자바 기본 빌드 도구 사용");
		new DbTest1();

	}

}
