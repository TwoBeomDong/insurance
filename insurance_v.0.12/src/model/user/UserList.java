package model.user;

public class UserList {

	private CustomerList customerList;
	private EmployeeList employeeList;

	public UserList() {
		this.customerList = new CustomerList();
		this.employeeList = new EmployeeList();
	}

	public CustomerList getCustomerList() {
		return customerList;
	}

	public void setCustomerList(CustomerList customerList) {
		this.customerList = customerList;
	}

	public EmployeeList getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(EmployeeList employeeList) {
		this.employeeList = employeeList;
	}

}
