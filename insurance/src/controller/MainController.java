package controller;

import java.time.LocalDate;

import model.contract.ContractInsurance;
import model.contract.ContractInsurance.PaymentType;
import model.contract.ContractInsuranceList;
import model.insurance.InsuranceProduct;
import model.insurance.InsuranceProductList;
import model.insurance.info.BasicInsuranceInfo;
import model.insurance.info.InsuranceType;
import model.insurance.info.TermPeriod;
import model.support.RequestSupportList;
import model.user.Customer;
import model.user.CustomerList;
import model.user.EmployeeList;
import view.MainTui;

public class MainController {

	// model
	private CustomerList customerList;
	private ContractInsuranceList contractInsuranceList;
	private EmployeeList employeeList;
	private InsuranceProductList insuranceProductList;
	private RequestSupportList requestSupportList;

	// ----------------------view--------------------
	private MainTui mainTui;

	// controller
	private C_Customer customerController;
	private C_ContractInsurance contractInsuranceController;
	private C_Employee employeeController;
	private C_InsuranceProduct insuranceProductController;
	private C_Support requestSupportController;

	public MainController() {

		// test data
		// customer list 만들자마자 testUser를 넣은 것
		Customer testUser = new Customer();
		testUser.setId("test");
		testUser.setPassword("1234");
		testUser.addPaymentBankAccount("12345678");

		BasicInsuranceInfo testBasicInsuranceInfo = new BasicInsuranceInfo("supportInsurance", InsuranceType.life,
				TermPeriod.month_1);
		InsuranceProduct testInsurance = new InsuranceProduct(testBasicInsuranceInfo, null, 123);

		this.customerList = new CustomerList();
		// add customer test data
		this.customerList.getCustomerList().add(testUser);

		this.contractInsuranceList = new ContractInsuranceList();
		// add Contract Insurance test data
		this.contractInsuranceList.getContractInsuranceList().add(new ContractInsurance(LocalDate.now(), testUser,
				LocalDate.now(), testInsurance, "123456", PaymentType.eBasicPayment));
		this.employeeList = new EmployeeList();
		this.insuranceProductList = new InsuranceProductList();
		
		
		this.requestSupportList = new RequestSupportList();

		this.customerController = new C_Customer(customerList);
		this.contractInsuranceController = new C_ContractInsurance(contractInsuranceList);
		this.employeeController = new C_Employee(employeeList);
		this.insuranceProductController = new C_InsuranceProduct(insuranceProductList);
		this.requestSupportController = new C_Support(requestSupportList);
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

	public C_Support getRequestSupportController() {
		return requestSupportController;
	}

	public void setRequestSupportController(C_Support requestSupportController) {
		this.requestSupportController = requestSupportController;
	}

}
