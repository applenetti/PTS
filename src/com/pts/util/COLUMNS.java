package com.pts.util;

public enum COLUMNS {
	ID("id"), TYPE("type"), SUBTYPE("subType"), CARDTYPE("cardType"), NAME("name"), CARDNUMBER(
			"cardNumber"), ACCOUNTID("accountid"), ACCOUNTNUMBER(
			"accountnumber"), USERNAME("username"), PASSWORD("password"), BILLNUMBER(
			"billnumber"), BILLDATE("billdate"), DUEDATE("duedate"), AMOUNT(
			"amount"), MODE("mode"), PAYMENTDATE("paymentdate"), PAIDAMOUNT(
			"paidamount"), STATUS("status");
	COLUMNS(String column) {
		this.column = column;
	}

	private String column;

	public String getColumn() {
		return column;
	}
}
