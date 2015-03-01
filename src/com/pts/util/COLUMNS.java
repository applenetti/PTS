package com.pts.util;

public enum COLUMNS {
	ID("id"), BILLTYPE("billType"), BILLSUBTYPE("billSubType"), CARDTYPE("cardType"), NAME("name"), CARDNUMBER(
			"cardNumber"), ACCOUNTID("accountId"), MOBILENUMBER(
			"mobileNumber"), USERNAME("username"), EMAIL("email"), BILLNUMBER(
			"billNumber"), BILLID("billId"), BILLDATE("billDate"), BILLDUEDATE("billDuedate"), BILLAMOUNT(
			"billAmount"), PAIDAMOUNT("paidAmount"), MODE("mode"), PAYMENTDATE("paymentdate"), STATUS("status");
	COLUMNS(String column) {
		this.column = column;
	}

	private String column;

	public String getColumn() {
		return column;
	}
}
