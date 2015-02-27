package com.pts.pojo;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "billtype")
@Access(value = AccessType.FIELD)
public class BillType {
	
	@Id
	@SequenceGenerator(name = "sq_id", sequenceName = "billtype_id", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id")
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "type", nullable = false, unique = true)
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "id: " + id + ", type: " + type;
	}
}