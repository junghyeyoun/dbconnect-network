package pack5db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest2 {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public DbTest2() {
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
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test","root","seoho123");
			stmt = conn.createStatement();
			
			// 자료 추가 -- autocommit이 기본
			// String isql = "insert into sangdata values (5, '새우깡' ,5 ,1500)";
			// stmt.executeUpdate(isql); // select를 제외한 sql문을 적을 수 있다.
			
			// String isql = "insert into sangdata values (6, '콘칩' ,5 ,2500)";
			// int result = stmt.executeUpdate(isql); // insert 경과 반환 /반환은 0(실패) or 1(성공)
			// System.out.println("result : "+result);

			// 자료 수정
			// String usql = "update sangdata set sang = '감자깡', su = 33 where code = 5";
			// stmt.executeUpdate(usql);
			/*
		    String usql = "update sangdata set sang = '감자깡', su = 33 where code = 5";
		    int upResult = stmt.executeUpdate(usql); // 수정 행 수 결과 반환
		    if(upResult>0) {
		    	System.out.println("수정 성공");
		    }else {
		    	System.out.println("수정 실패");
		    }
		    */
			
			// 자료삭제
			String dsql = "delete from sangdata where code >= 5";
		    int delResult = stmt.executeUpdate(dsql); 
		    System.out.println("delResult : "+delResult);
		    if(delResult>0) {
		    	System.out.println("삭제 성공");
		    }else {
		    	System.out.println("삭제 실패");
		    }
			
			
			
			
			// 모든 자료 읽기
			rs = stmt.executeQuery("select * from sangdata");
			int cou = 0;
			while(rs.next()) {
				System.out.println(rs.getString("code") + " " +
									rs.getString("sang") + " " +
									rs.getString("su") + " " +
									rs.getString("dan") + " ");
				cou ++;
			}
			System.out.println("전체 건수 : "+cou);
			
		} catch (Exception e) {
			
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
			
			}
		}
		
	}
	public static void main(String[] args) {
		new DbTest2();

	}

}
