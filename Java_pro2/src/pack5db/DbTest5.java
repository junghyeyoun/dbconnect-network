package pack5db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Properties; // DB 연결정보를 별도의 파일로 작성 후 읽기

public class DbTest5 {
	public DbTest5() {
		try {
			properties.load(new FileInputStream("C:\\work\\jsou\\Java_pro2\\src\\pack5db\\Dbconn.properties"));
		} catch (Exception e) {
			
		}
		dbLoading();
		insertDb();
		selectDb();
	}
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties properties = new Properties();
	
	private void dbLoading() {
		try {
			Class.forName(properties.getProperty("driver"));
		} catch (Exception e) {
			System.out.println("로딩 실패 :" +e);
			System.exit(0);
		}
	}
	
	private void insertDb() {
		// 키보드로 DB에 자료 입력
		try {
			Scanner scanner = new Scanner(System.in);
			// conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test","root","seoho123");
			conn = DriverManager.getConnection(properties.getProperty("url"),
					properties.getProperty("user"),
					properties.getProperty("password"));
			
			while(true) {
				System.out.print("code : ");
				String code = scanner.next();
				System.out.print("sang : ");
				String sang = scanner.next();
				System.out.print("su : ");
				String su = scanner.next();
				System.out.print("dan : ");
				String dan = scanner.next();
				// 입력 자료 오류 검사는 생략
				
				pstmt = conn.prepareStatement("insert into sangdata values(?,?,?,?)");
				pstmt.setString(1, code);
				pstmt.setString(2, sang);
				pstmt.setString(3, su);
				pstmt.setString(4, dan);
				pstmt.executeUpdate(); // 추가 작업 진행. 반환값은 생략
				System.out.print("계속할까요 (y/n) :");
				String yn = scanner.next();
				// if(yn.equals("n")) break; // while 탈출
				if(yn.equalsIgnoreCase("n")) break; // 대소문자 구분 안함		
			}
			scanner.close();
		} catch (Exception e) {
			System.out.println("insertDb err : " +e );
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
	
	private void selectDb() {
		// DB에 입력된 자료 읽기
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/test","root","seoho123");
			
			String sql = "select * from sangdata";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			int count = 0;
			System.out.println("코드\t상품명\t수량\t단가");
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t" +
						rs.getString(2) + "\t" +
						rs.getString(3) + "\t" +
						rs.getString(4));
				count += 1;
			}
			System.out.println("건 수 :" +count + "개");
		} catch (Exception e) {
			System.out.println("selectDb err : " +e );
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
		
	
	
	
	public static void main(String[] args) {
		new DbTest5();

	}

}
