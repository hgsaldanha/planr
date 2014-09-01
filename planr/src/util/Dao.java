package util;

import javax.persistence.EntityManager;



public class Dao {
	EntityManager em = null;
	
	public Dao(String pu) {
		em = JPAUtil.getEntityManager(pu);
	}
	
	public EntityManager getEntityManager() {
		return em;
	}
	
//	public void save(Object obj) {
//		//TODO ADICIONAR O PARÂMETRO DA CLASSE DO OBJETO
//		getEntityManager().getTransaction().begin();
//		getEntityManager().persist(obj);
//		getEntityManager().getTransaction().commit();
//	}
	
	public void close() {
		if (getEntityManager().isOpen()) {
			if (getEntityManager().getTransaction().isActive())
				getEntityManager().getTransaction().rollback();
			getEntityManager().close();
		}
	}
}
