package bean;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import model.Category;
import model.Record;
import util.Bean;
import control.RecordControl;

@ManagedBean
@RequestScoped
public class RecordBean extends Bean {
	private Record record = null;
	private Collection<Record> list = null;
	private Integer[] maxParcel = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
	
	@ManagedProperty(value="#{param.id}")
	private Integer id;
	
	@ManagedProperty(value="#{categoryBean}")
	private CategoryBean categoryBean;

	public String save() {
		try {
			new RecordControl().save(getRecord());
			addMessage(FacesMessage.SEVERITY_INFO, "Compra registrada.");
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível registrar a compra.");
			e.printStackTrace();
			return "/record/insert?faces-redirect=true";
		}
		return "/record/index?faces-redirect=true";
	}

	public Collection<Record> getAll() {
		if (getList() == null) {
			try {
				setList(new RecordControl().getAll());
			} catch (Exception e) {
				addMessage(FacesMessage.SEVERITY_ERROR, "Não foi possível carregar a lista de compras.");
				e.printStackTrace();
			}
		}
		return getList();
	}

	public void select() {
		try {
			setRecord(new RecordControl().select(getId()));
		} catch (Exception e) {
			addMessage(FacesMessage.SEVERITY_ERROR,	"Não foi possível carregar as informações da compra selecionada.");
			e.printStackTrace();
		}
	}
	
	public Collection<Category> getCategories() {
		return this.categoryBean.getAll(true);
	}
	
	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
	}

	public Record getRecord() {
		if (record == null)
			record = new Record();
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public void setList(Collection<Record> list) {
		this.list = list;
	}

	public Collection<Record> getList() {
		return list;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public Integer[] getMaxParcel() {
		return maxParcel;
	}

	public void setMaxParcel(Integer[] maxParcel) {
		this.maxParcel = maxParcel;
	}
}
