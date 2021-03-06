package com.pts.pojo;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "billsubtype")
@Access(value = AccessType.FIELD)
public class BillSubType {
	
	@Id
	@SequenceGenerator(name = "sq_id", sequenceName = "billsubtype_id", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id")
	@Column(name = "id", nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="billtype_id")
	private BillType billType;
	
	@Column(name = "billsubtype", nullable = false)
	private String billSubType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BillType getBillType() {
		return billType;
	}

	public void setBillType(BillType billType) {
		this.billType = billType;
	}

	public String getBillSubType() {
		return billSubType;
	}

	public void setBillSubType(String billSubType) {
		this.billSubType = billSubType;
	}

	@Override
	public String toString() {
		return "id: " + id + ", billSubType: " + billSubType;
	}
}