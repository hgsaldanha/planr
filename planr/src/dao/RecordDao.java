package dao;

import java.util.Collection;

import model.Record;
import util.Dao;

public class RecordDao extends Dao {
	
	public RecordDao() {
		super("default");
	}

	public void save(Record record) throws Exception {
		try {
			if (record.getId() > 0)
				record = getEntityManager().merge(record);
			getEntityManager().getTransaction().begin();
			getEntityManager().persist(record);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			getEntityManager().getTransaction().rollback();
			throw e;
		}
	}
	
	public void saveAll(Collection<Record> records) throws Exception {
		for (Record record : records) {
			record.setId(0);
			this.save(record);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Record> getAll() {
		getEntityManager().getTransaction().begin();
		Collection<Record> records =  getEntityManager().createQuery("from Record r where r.invoice is null and r.card is null order by r.dateTime asc").getResultList();
		getEntityManager().getTransaction().commit();
		return records;
	}
	
	public Record select(int id) {
		getEntityManager().getTransaction().begin();
		Record record =  getEntityManager().find(Record.class, id);
		getEntityManager().getTransaction().commit();
		record.setValue(record.getValue() * record.getParcelTotal());
		return record;
	}
	
	public void delete(Record record) throws Exception {
		getEntityManager().getTransaction().begin();
		//Collection<Record> records = record.getRecords();
		//for (Record rec : records) {
			//getEntityManager().remove(rec);
		//}
		record = getEntityManager().merge(record);
		getEntityManager().remove(record);
		getEntityManager().getTransaction().commit();
	}
	
	public void deleteChildren(Record record) throws Exception {
		getEntityManager().getTransaction().begin();
		record = getEntityManager().merge(record);
		//Collection<Record> records = record.getRecords();
		//for (Record rec : records) {
			//getEntityManager().remove(rec);
		//}
		getEntityManager().getTransaction().commit();
	}
}
