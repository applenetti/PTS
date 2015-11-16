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
@Table(name = "payment")
@Access(value = AccessType.FIELD)
public class Payment {

	@Id
	@SequenceGenerator(name = "sq_id", sequenceName = "payment_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id")
	@Column(name = "id", nullable = false)
	private int id;

	@ManyToOne
	@JoinColumn(name = "bill_id")
	private Bill bill;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "mode_id")
	private PaymentMode mode;

	private Date paymentDate;

	private double paidAmount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public PaymentMode getMode() {
		return mode;
	}

	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", bill=" + bill + ", status=" + status
				+ ", mode=" + mode + ", paymentDate=" + paymentDate
				+ ", paidAmount=" + paidAmount + "]";
	}

}
