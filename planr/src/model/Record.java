package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import util.base.Model;


/**
 * The persistent class for the record database table.
 * 
 */
@Entity
@NamedQuery(name="Record.findAll", query="SELECT r FROM Record r")
public class Record extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="card_id")
	private int cardId;

	@ManyToOne
	private Category category;

	@Temporal(TemporalType.DATE)
	@Column(name="date_time")
	private Date dateTime;

	private String description;

	@Column(name="invoice_id")
	private int invoiceId;

	private int parcelIndex;

	private int parcelTotal;

	private float value;
/*
	//bi-directional many-to-one association to Record
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id", referencedColumnName="record_id")
	private Record record;

	//bi-directional many-to-one association to Record
	@OneToMany(mappedBy="record")
	private List<Record> records;
*/
	public Record() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCardId() {
		return this.cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInvoiceId() {
		return this.invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getParcelIndex() {
		return this.parcelIndex;
	}

	public void setParcelIndex(int parcelIndex) {
		this.parcelIndex = parcelIndex;
	}

	public int getParcelTotal() {
		return this.parcelTotal;
	}

	public void setParcelTotal(int parcelTotal) {
		this.parcelTotal = parcelTotal;
	}

	public float getValue() {
		return this.value;
	}

	public void setValue(float value) {
		this.value = value;
	}
/*
	public Record getRecord() {
		return this.record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public List<Record> getRecords() {
		return this.records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public Record addRecord(Record record) {
		getRecords().add(record);
		record.setRecord(this);

		return record;
	}

	public Record removeRecord(Record record) {
		getRecords().remove(record);
		record.setRecord(null);

		return record;
	}
	*/
	public String getFormattedDate() {
		return getDateTime().toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Record))
			return false;
		Record other = (Record) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}