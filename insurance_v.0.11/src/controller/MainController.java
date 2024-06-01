package controller;

import model.contract.ContractInsuranceList;
import model.insurance.InsuranceProductList;
import model.user.CustomerList;
import model.user.EmployeeList;
import view.MainTui;

public class MainController {

	// 1. controller 들만 new 해서 갖고있어.
	// 1-1. class를 쪼개!!! 쪼개서 지금 list들(각 부서별 컨트롤러 / 거기서 사용되는 Model들) 각각 쪼개.
	// 1-2 각 쪼갠 Model들이 있지. 그거 그냥 생성자에서 더미데이터 만들기 뭐든 아무거나 만들어.
	// 2. 각 컨트롤러에서도. 생성자에서 모델을 new 해야돼 그럼 그 순간에 데이터가 생겨.
	// 2-1. 하위 컨트롤러 내에서 각각 컨트롤러가 가져야 할(알고 있어야 할) 모델들을 가져. new 해서.

	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡmodelㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	private CustomerList customerList;
	private ContractInsuranceList contractInsuranceList;
	private EmployeeList employeeList;
	private InsuranceProductList insuranceProductList;

	// ----------------------view--------------------
	private MainTui mainTui;

	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡcontrollerㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	private C_Customer customerController;
	private C_ContractInsurance contractInsuranceController;
	private C_Employee employeeController;
	private C_InsuranceProduct insuranceProductController;

	public MainController() {
		this.customerList = new CustomerList();
		this.contractInsuranceList = new ContractInsuranceList();
		this.employeeList = new EmployeeList();
		this.insuranceProductList=new InsuranceProductList();
		
		this.customerController = new C_Customer(customerList);
		this.contractInsuranceController = new C_ContractInsurance(contractInsuranceList);
		this.employeeController = new C_Employee(employeeList);
		this.insuranceProductController = new C_InsuranceProduct(insuranceProductList);
	}

	public void associate(MainTui mainTui) {
		this.mainTui = mainTui;
	}

	public C_Customer getCustomerController() {
		return customerController;
	}

	public void setCustomerController(C_Customer customerController) {
		this.customerController = customerController;
	}

	public C_ContractInsurance getContractInsuranceController() {
		return contractInsuranceController;
	}

	public void setContractInsuranceController(C_ContractInsurance contractInsuranceController) {
		this.contractInsuranceController = contractInsuranceController;
	}

	public C_Employee getEmployeeController() {
		return employeeController;
	}

	public void setEmployeeController(C_Employee employeeController) {
		this.employeeController = employeeController;
	}

	public C_InsuranceProduct getInsuranceProductController() {
		return insuranceProductController;
	}

	public void setInsuranceProductController(C_InsuranceProduct insuranceProductController) {
		this.insuranceProductController = insuranceProductController;
	}
	
}
