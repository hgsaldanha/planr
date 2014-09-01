package control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import model.Record;
import util.Control;
import dao.RecordDao;

public class RecordControl extends Control {
	RecordDao dao;

	public void save(Record record) throws Exception {
		try {
			dao = new RecordDao();
			if (record.getId() > 0) {
				dao.deleteChildren(record);
			}
			if (record.getParcelTotal() > 1) {
				Collection<Record> records = new ArrayList<Record>();
				float parcelValue = record.getValue() / record.getParcelTotal();
				int parcelTotal = record.getParcelTotal();
				record.setValue(parcelValue);
				dao.save(record);
				for(int i = 2; i <= parcelTotal; i++) {
					Record rec = (Record)record.clone();
					rec.setValue(parcelValue);
					rec.setDateTime(new Date(record.getDateTime().getTime()));
					rec.setParcelIndex(i);
					//rec.setRecord(record);
					//rec.getRecords().add(record);
					records.add(rec);
				}
				dao.saveAll(records);
			} else {
				record.setParcelIndex(1);
				dao.save(record);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}
	}

	public Collection<Record> getAll() throws Exception {
		Collection<Record> all = null;
		try {
			dao = new RecordDao();
			all = dao.getAll();
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}
		return all;
	}
	
	public Record select(int id) throws Exception {
		Record record = null;
		try {
			dao = new RecordDao();
			record = dao.select(id);
		} catch (Exception e) {
			throw e;
		} finally {
			dao.close();
		}
		return record;
	}
}
