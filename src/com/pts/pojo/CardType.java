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
@Table(name = "cardtype")
@Access(value = AccessType.FIELD)
public class CardType {
	
	@Id
	@SequenceGenerator(name = "sq_id", sequenceName = "cardtype_id", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id")
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "cardtype", nullable = false, unique = true)
	private String cardType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return "id: " + id + ", cardType: " + cardType;
	}

}
