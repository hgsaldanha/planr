package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JPAUtil {
	
	
	private static EntityManager em = null;
	private static EntityManagerFactory emf = null;

	public static EntityManager getEntityManager(String pu) {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(pu);
		}
		if (em == null || !em.isOpen()) {
			em = emf.createEntityManager();
		}
		return em;
	}
}
