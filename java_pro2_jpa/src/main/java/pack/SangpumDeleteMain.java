package pack;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import domain.SangpumTable;

public class SangpumDeleteMain {
	private static Logger logger = LoggerFactory.getLogger(SangpumDeleteMain.class); // log 출력용

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gojpa");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			int findId = 9;
			SangpumTable santab = entityManager.find(SangpumTable.class, findId);
			if (santab == null) {
				System.out.println("자료 없음");
			} else {
				entityManager.remove(santab); // 삭제!
				logger.info("사용자 삭제함: {}", findId); // log 출력
			}
			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			transaction.rollback();
		} finally {
			entityManager.close();
		}
		emf.close();
		
		SangpumSelectMain.main(args);
	}
}
