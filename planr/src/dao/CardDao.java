package dao;

import java.util.Collection;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import model.Card;
import util.Dao;

public class CardDao extends Dao {
	
	public CardDao() {
		super("default");
	}

	public void save(Card card) throws Exception {
		try {
			if (card.getId() > 0)
				card = getEntityManager().merge(card);
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(card);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Collection<Card> all() {
		getEntityManager().getTransaction().begin();
		Collection<Card> cards =  getEntityManager().createNamedQuery("Card.all").getResultList();
		getEntityManager().getTransaction().commit();
		return cards;
	}
	
	public Card select(int id) {
		getEntityManager().getTransaction().begin();
		Card card =  getEntityManager().find(Card.class, id);
		getEntityManager().getTransaction().commit();
		return card;
	}
}
