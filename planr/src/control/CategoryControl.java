package control;

import java.util.Collection;

import model.Category;
import util.Control;
import dao.CategoryDao;

public class CategoryControl extends Control {
	CategoryDao dao;

	public void save(Category category) throws Exception {
		try {
			dao = new CategoryDao();
			dao.save(category);
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}
	}

	public Collection<Category> getAll(boolean activeOnly) throws Exception {
		Collection<Category> all = null;
		try {
			dao = new CategoryDao();
			all = dao.getAll(activeOnly);
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}
		return all;
	}
	
	public Category select(int id) throws Exception {
		Category category = null;
		try {
			dao = new CategoryDao();
			category = dao.select(id);
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}
		return category;
	}
}
