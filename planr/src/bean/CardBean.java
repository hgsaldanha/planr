package bean;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import model.Card;
import util.Bean;
import control.CardControl;

@ManagedBean
@RequestScoped
public class CardBean extends Bean {
	private Card card;
	private Collection<Card> list = null;
	
	@ManagedProperty(value="#{param.id}")
	private Integer id;

	public String save() {
		try {
			new CardControl().save(getCard());
			addMessage(FacesMessage.SEVERITY_INFO,"Cartão salvo com sucesso.");
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR,"Não foi possível salvar o novo cartão.");
			e.printStackTrace();
			return "/card/insert";
		}
		return "/card/index?faces-redirect=true";
	}

	public Collection<Card> getAll() {
		if (getList() == null) {
			try {
				setList(new CardControl().all());
			} catch (Exception e) {
				addMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível carregar a lista de cartões.");
				e.printStackTrace();
			}
		}
		return getList();
	}

	public void select() {
		try {
			setCard(new CardControl().select(getId()));
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR,	"Não foi possível carregar as informações do cartão selecionado.");
			e.printStackTrace();
			//return "index";
		}
		//return "select";
	}

	public Card getCard() {
		if (card == null)
			card = new Card();
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void setList(Collection<Card> list) {
		this.list = list;
	}

	public Collection<Card> getList() {
		return list;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
}
