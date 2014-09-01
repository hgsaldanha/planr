package control;

import java.util.Collection;

import model.Card;
import util.Control;
import dao.CardDao;

public class CardControl extends Control {
	CardDao dao;

	public void save(Card card) throws Exception {
		try {
			dao = new CardDao();
			dao.save(card);
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}
	}

	public Collection<Card> all() throws Exception {
		Collection<Card> all = null;
		try {
			dao = new CardDao();
			all = dao.all();
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}
		return all;
	}
	
	public Card select(int id) throws Exception {
		Card card = null;
		try {
			dao = new CardDao();
			card = dao.select(id);
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}
		return card;
	}
}
