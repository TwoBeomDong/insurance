 package controller;

import java.util.Vector;

import model.user.Customer;
import model.user.CustomerList;

/**
 * @author dongyeonkim
 * @version 1.0
 * @created 14-5-2024 ���� 6:43:14
 */
public class C_Customer {
	private CustomerList customerList;

	public C_Customer(CustomerList customerList) {
		this.customerList = customerList;

	}

	public boolean addCustomer(String name, String age, String sex, String paymentBankAccount, String id, String pw) {

		// 정상적인 회원가입 정보인지 (아이디값을 중복되지 않게 넣었는지) 처리하는 로직

		return true;
	}

	public boolean deleteCustomer(String id) {
		return true;
	}

	public boolean checkPassword(String id, String password) {
		// is user exists
		for(Customer user : customerList.getCustomerList()) {
			if(user.getId().equals(id) && user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	public Customer getCustomer(String id) {
		Vector<Customer> list = customerList.getCustomerList();

		for (Customer c : list)
			if (c.equals(id)) return c;
		return null;
	}

}