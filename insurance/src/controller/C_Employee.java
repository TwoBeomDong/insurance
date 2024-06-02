package controller;

import java.util.Vector;

import model.user.Employee;
import model.user.EmployeeList;

/**
 * @author dongyeonkim
 * @version 1.0
 * @created 14-5-2024 ���� 6:43:14
 */
public class C_Employee {

	private EmployeeList employeeList;

	public C_Employee(EmployeeList employeeList){
		this.employeeList = employeeList;
	}

	public EmployeeList getEmployeeList(){
		return this.employeeList;
	}
	public boolean addEmployee(Employee employee) {
		return true;
	}
	public boolean deleteEmployee(String id) {
		return true;
	}
	public boolean checkPassword(String id, String password) {
		Employee e = this.getEmployee(id);
		return e.getPassword().equals(password);
	}
	public Employee getEmployee(String id) {
		Vector<Employee> list = this.employeeList.getEmployeeList();
		
		for(Employee e : list)
			if(e.equals(id)) return e;
		return null;
	}
}