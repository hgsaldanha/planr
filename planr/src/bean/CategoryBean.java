package bean;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import model.Category;
import util.Bean;
import control.CategoryControl;

@ManagedBean
@RequestScoped
public class CategoryBean extends Bean {
	private Category category;
	private Collection<Category> list = null;
	
	@ManagedProperty(value="#{param.id}")
	private Integer id;

	public String save() {
		try {
			new CategoryControl().save(getCategory());
			addMessage(FacesMessage.SEVERITY_INFO,"Categoria salva com sucesso.");
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR,"Não foi possível salvar a nova categoria.");
			e.printStackTrace();
			return "/category/insert";
		}
		return "/category/index?faces-redirect=true";
	}
	
	public Collection<Category> getAll() {
		return this.getAll(false);
	}

	public Collection<Category> getAll(boolean activeOnly) {
		if (getList() == null) {
			try {
				setList(new CategoryControl().getAll(activeOnly));
			} catch (Exception e) {
				addMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível carregar a lista de categorias.");
				e.printStackTrace();
			}
		}
		return getList();
	}

	public void select() {
		try {
			setCategory(new CategoryControl().select(getId()));
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR,	"Não foi possível carregar as informações da categoria selecionada.");
			e.printStackTrace();
		}
	}

	public Category getCategory() {
		if (category == null)
			category = new Category();
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setList(Collection<Category> list) {
		this.list = list;
	}

	public Collection<Category> getList() {
		return list;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
}
