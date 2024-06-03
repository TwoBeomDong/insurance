package model.user;

import java.util.Vector;

public class EmployeeList {
	private Vector<Employee> employeeList;

	public EmployeeList(){
		this.employeeList = new Vector<>();
}

	public Vector<Employee> getEmployeeList() {
		return employeeList;
	}
	
}
