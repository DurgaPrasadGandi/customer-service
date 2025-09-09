package com.example.customer.controller;

public class CheckoutRequest {
	  private String customerEmail;
	  private String sku;
	  private Integer qty;
	public CheckoutRequest() {
		super();
	}
	public CheckoutRequest(String customerEmail, String sku, Integer qty) {
		super();
		this.customerEmail = customerEmail;
		this.sku = sku;
		this.qty = qty;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "CheckoutRequest [customerEmail=" + customerEmail + ", sku=" + sku + ", qty=" + qty + "]";
	}
	  


}
