package model.user;

import java.util.Vector;

public class CustomerList {
	private Vector<Customer> customerList;

	public CustomerList() {
		this.customerList = new Vector<>();
	}

	public Vector<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(Vector<Customer> customerList) {
		this.customerList = customerList;
	}
}
