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
@Table(name = "paymentmode")
@Access(value = AccessType.FIELD)
public class PaymentMode {
	
	@Id
	@SequenceGenerator(name = "sq_id", sequenceName = "paymentmode_id", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id")
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(length = 20, nullable = false, unique = true)
	private String mode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	@Override
	public String toString() {
		return "id: " + id + ", mode: " + mode;
	}

}
