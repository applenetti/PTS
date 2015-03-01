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
	
	@OneToOne
	@JoinColumn(name="biller_id")
	private Biller biller;
	
	@OneToOne
	@JoinColumn(name="billtype_id")
	private BillType billType;
	
	@OneToOne
	@JoinColumn(name="billsubtype_id")
	private BillSubType billSubType;
	
	@Column
	private String accountId;
	
	@Column
	private String mobileNumber;
	
	@Column
	private String username;
	
	@Column
	private String email;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Biller getBiller() {
		return biller;
	}

	public void setBiller(Biller biller) {
		this.biller = biller;
	}

	public BillType getBillType() {
		return billType;
	}

	public void setBillType(BillType billType) {
		this.billType = billType;
	}

	public BillSubType getBillSubType() {
		return billSubType;
	}

	public void setBillSubType(BillSubType billSubType) {
		this.billSubType = billSubType;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "id: " + id + ", accountId: " + accountId;
	}

}
