package com.pts.pojo;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@Access(value = AccessType.FIELD)
public class Account {
	
	@Id
	@SequenceGenerator(name = "sq_id", sequenceName = "account_id", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id")
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(length = 20, nullable = false, unique = true)
	private String accountId;
	
	@Column(length = 20, nullable = false, unique = true)
	private String accountNumber;
	
	@Column(length = 20, nullable = false)
	private String username;
	
	@Column(length = 20, nullable = false)
	private String password;
	
	@OneToOne
	@JoinColumn(name="biller_id")
	private Biller biller;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Biller getBiller() {
		return biller;
	}

	public void setBiller(Biller biller) {
		this.biller = biller;
	}

	@Override
	public String toString() {
		return "id: " + id + ", accountId: " + accountId;
	}

}
