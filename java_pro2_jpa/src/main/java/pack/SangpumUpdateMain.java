package pack;

import domain.SangpumTable;
import jakarta.persistence.*;

public class SangpumUpdateMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gojpa");
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			SangpumTable sangtab = entityManager.find(SangpumTable.class, "9");
			if (sangtab == null) {
				System.out.println("해당 자료 없음");
			} else {
				String newName = "마스크";
				// sangtab.changeName(newName);
				sangtab.setSang(newName); // 그냥 setter로 해도 됨
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
