package pack5db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest3 {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	public DbTest3() {
		dbloading();
		accessDb();
	}
	private void dbloading() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("로딩 실패 :" +e);
			System.exit(0);
		}
	}
	private void accessDb() {
		// 관리 고객이 있는 직원 출력
		// 사번 이름 직급 고객명 고객전화
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test","root","seoho123");
			stmt = conn.createStatement();
			
			String sql = "select jikwon_no, jikwon_name, jikwon_jik, gogek_name, gogek_tel "
					+ "from jikwon inner join gogek on jikwon_no = gogek_damsano order by jikwon_name";
			rs = stmt.executeQuery(sql);
			int su = 0; // 건수 출력용
			while(rs.next()) {
				System.out.println(rs.getString("jikwon_no") + " " +
						rs.getString("jikwon_name") + " " +
						rs.getString("jikwon_jik") + " " +
						rs.getString("gogek_name") + " " +
						rs.getString("gogek_tel"));	
				su = su+1;
			}
			System.out.println("건수 : "+su);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
	} catch (Exception e) {
	}
		}   
}

	public static void main(String[] args) {
		new DbTest3();

	}

}
