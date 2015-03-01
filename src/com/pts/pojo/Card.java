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
@Table(name = "card")
@Access(value = AccessType.FIELD)
public class Card {
	
	@Id
	@SequenceGenerator(name = "sq_id", sequenceName = "card_id", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id")
	@Column(name = "id", nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="bank_id")
	private Bank bank;
	
	@ManyToOne
	@JoinColumn(name="cardtype_id")
	private CardType cardType;
	
	@Column(name="cardnumber", nullable = false, unique = true)
	private String cardNumber;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}	

	@Override
	public String toString() {
		return "id: " + id + ", cardnumber: " + cardNumber;
	}

}