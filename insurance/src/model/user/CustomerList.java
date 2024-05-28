package model.user;

import java.util.Vector;

public class CustomerList {
	private Vector<Customer> customerList;

	public CustomerList() {
		this.customerList = new Vector<>();
		// test data
		// customer list 만들자마자 testUser를 넣은 것
		Customer testUser = new Customer();
		testUser.setId("test");
		testUser.setPassword("1234");
		
		this.customerList.add(testUser);

	}

	public Vector<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(Vector<Customer> customerList) {
		this.customerList = customerList;
	}
}
