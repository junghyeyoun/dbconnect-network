package pack;

import java.util.List;

import domain.SangpumTable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class SangpumSelectMain {
	public static void main(String[] args) {
		// EntityManagerFactory는 애플리케이션 전체에서 하나만 생성해서 사용한다.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gojpa");

		// EntityManager는 각 스레드별 하나씩 받아서 사용함(즉, 필요할 때 팩토리로부터 받아서 쓰고 난 후 반드시 닫아준다.)
		EntityManager entityManager = emf.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			int findId = 1; // String findId = "1"; 도 가능
			SangpumTable santab = entityManager.find(SangpumTable.class, findId); // find 쓰면 내부적으로 select (ORM)
			if (santab == null) {
				System.out.println("자료 없음");
			} else {
				System.out.printf("code=%s, sang=%s, su=%s, dan=%s\n", santab.getCode(), santab.getSang(),
						santab.getSu(), santab.getDan());
			}

			System.out.println("전체 보기"); // JPQL 사용 : https://jang8584.tistory.com/282
			List<SangpumTable> list = entityManager.createQuery("select m from SangpumTable m", SangpumTable.class)
					.getResultList();
			// "select m from SangpumTable m"은 테이블이 아니라 엔티티 객체를 의미한다.
			for (SangpumTable st : list) {
				System.out.println(st.getCode() + " " + st.getSang() + " " + st.getSu() + " " + st.getDan());
			}

			transaction.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			transaction.rollback();
		} finally {
			entityManager.close(); // 더 이상 필요 없다고 판단될 때 닫기 보통 안 닫음!
		}
		emf.close(); // 엔티티매니저 닫기 (반드시 해야 함)
	}
}
