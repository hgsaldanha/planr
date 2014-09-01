package model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.sql.Timestamp;


/**
 * The persistent class for the card database table.
 * 
 */
@Entity
@NamedQuery(name="Card.all", query="SELECT c FROM Card c order by c.active desc, c.name asc")
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private boolean active = true;

	private Timestamp created;

	private int expiration = 1;

	@NotNull(message="O nome do cartão é obrigatório")
	@Pattern(regexp="^[A-Za-z0-9].*$",message="O nome do cartão precisa começar com uma letra ou número")
	private String name;

	public Card() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public int getExpiration() {
		return this.expiration;
	}

	public void setExpiration(int expiration) {
		this.expiration = expiration;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}