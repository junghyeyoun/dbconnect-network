package pack5db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbTest6 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public DbTest6() {
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
		
		conn.setAutoCommit(false); // transaction 처리가 수동으로 전환
		// 자료 추가 - Transaction 시작 --------------------------------------------------------------
		String insql = "insert into sangdata values(?,?,?,?)";
		pstmt = conn.prepareStatement(insql);
		pstmt.setString(1, "5");
		pstmt.setString(2, "새상품");
		pstmt.setString(3, "3");
		pstmt.setString(4, "3000");
		pstmt.executeUpdate(); // insert 완료
		// 추가를 해줘도 autocommit 상태가 아니기 때문에 commit을 해줘야 함. (DB(원본)에서는 insert 되지 않았기 때문에)
		
		// 추가 후 뭔가를 로컬에서 하다가...
		// 자료 수정
		String upsql = "update sangdata set sang=?, su=? where code = ?";
		pstmt = conn.prepareStatement(upsql);
		pstmt.setString(1, "신상");
		pstmt.setString(2, "50");
		pstmt.setString(3, "5");
		pstmt.executeUpdate(); // update 완료
		
		conn.commit(); 
		// trnasaction 끝남 -------------------------------------------------------------------------
	
		
		// 모든 자료 읽기
		String sql = "select * from sangdata";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
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
		try {
			conn.rollback(); // trnasaction 끝남  // 오류 나면 rollback (취소)
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
	}finally {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			conn.setAutoCommit(true); // 다시 자동으로 설정
		} catch (Exception e) {
		
		}
	}
	
}
	
	public static void main(String[] args) {
		new DbTest6();

	}

}
