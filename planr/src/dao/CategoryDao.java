package dao;

import java.util.Collection;

import model.Category;
import util.Dao;

public class CategoryDao extends Dao {
	
	public CategoryDao() {
		super("default");
	}

	public void save(Category category) throws Exception {
		try {
			if (category.getId() > 0)
				category = getEntityManager().merge(category);
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(category);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
			throw e;
		}
	}
	
	public Collection<Category> getAll() {
		return this.getAll(false);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Category> getAll(boolean activeOnly) {
		StringBuilder query = new StringBuilder("from Category c ");
		getEntityManager().getTransaction().begin();
		if (activeOnly) {
			query.append("where c.active = true ");
		}
		query.append("order by c.name asc");
		Collection<Category> categories =  getEntityManager().createQuery(query.toString()).getResultList();
		getEntityManager().getTransaction().commit();
		return categories;
	}
	
	public Category select(int id) {
		getEntityManager().getTransaction().begin();
		Category category =  getEntityManager().find(Category.class, id);
		getEntityManager().getTransaction().commit();
		return category;
	}
}
