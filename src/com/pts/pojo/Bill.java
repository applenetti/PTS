package com.pts.pojo;

import java.sql.Date;

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
@Table(name = "bill")
@Access(value = AccessType.FIELD)
public class Bill {

	@Id
	@SequenceGenerator(name = "sq_id", sequenceName = "bill_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id")
	@Column(name = "id", nullable = false)
	private int id;

	@ManyToOne
	@JoinColumn(name = "biller_id")
	private Biller biller;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	@Column(name = "billnumber", nullable = false, unique = true)
	private String billNumber;

	@Column(name = "billdate", nullable = false, unique = false)
	private Date billDate;

	@Column(name = "duedate", nullable = false, unique = false)
	private Date billDueDate;

	@Column(name = "amount", nullable = false, unique = false)
	private double billAmount;

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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getBillDueDate() {
		return billDueDate;
	}

	public void setBillDueDate(Date billDueDate) {
		this.billDueDate = billDueDate;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", biller=" + biller + ", account=" + account
				+ ", status=" + status + ", billNumber=" + billNumber
				+ ", billDate=" + billDate + ", billDueDate=" + billDueDate
				+ ", billAmount=" + billAmount + "]";
	}

}
