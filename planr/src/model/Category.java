package model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import util.base.Model;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQuery(name="Category.all", query="SELECT c FROM Category c order by c.active desc, c.name asc")
public class Category extends Model {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private boolean active = true;

	private Timestamp created;

	@NotNull(message="O nome da categoria é obrigatório")
	@Pattern(regexp="^[A-Za-z0-9].*$",message="O nome da categoria precisa começar com uma letra ou número")
	private String name;

	//TODO trocar o ponto pela vírgula
	@NotNull(message="Informe uma previsão de gastos. Deixe o valor zerado para ignorar a previsão")
	@Min(value=0,message="A previsão de gastos deve ser maior que zero")
	private float prevision;

	public Category() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrevision() {
		return this.prevision;
	}

	public void setPrevision(float prevision) {
		this.prevision = prevision;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}