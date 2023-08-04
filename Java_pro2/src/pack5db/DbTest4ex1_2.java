package pack5db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DbTest4ex1_2 {
	public static void main(String[] args) {
		new DbTest4ex1_2();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("로딩 실패 :" +e);
			System.exit(0);
		}
	
	
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test","root","seoho123");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("부서명 : ");
			String bname = sc.next();
			String sql = "select jikwon_no, jikwon_name, jikwon_jik, buser_name, jikwon_gen "
					+ "from jikwon left outer join buser on buser_num = buser_no where buser_name = ?";
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, bname);
			rs = pstmt.executeQuery();
			int man = 0;
			int woman = 0;
			while(rs.next()) {
				System.out.println(rs.getString("jikwon_no") + " " +
						rs.getString("jikwon_name") + " " +
						rs.getString("jikwon_jik") + " " +
						rs.getString("buser_name") + " " +
						rs.getString("jikwon_gen") + " " );
				if ((rs.getString("jikwon_gen").equals("남")) == true) man++;
				else woman++;
			}
			System.out.println("남직원 : "+man+","+"여직원 : "+woman);
			sc.close();
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	


	
	
		
	
	}


