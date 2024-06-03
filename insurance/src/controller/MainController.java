package controller;

import model.contract.ContractInsurance.PaymentType;
import model.contract.ContractInsuranceList;
import model.insurance.InsuranceProduct;
import model.insurance.InsuranceProductList;
import model.insurance.info.BasicInsuranceInfo;
import model.insurance.info.InsuranceType;
import model.insurance.info.TermPeriod;
import model.support.RequestSupportList;
import model.terminate.TerminateInsuranceList;
import model.user.Customer;
import model.user.CustomerList;
import model.user.EmployeeList;
import model.user.User.eSex;
import terminator.Terminator;
import view.MainTui;

public class MainController {

	// model
	private CustomerList customerList;
	private ContractInsuranceList contractInsuranceList;
	private EmployeeList employeeList;
	private InsuranceProductList insuranceProductList;
	private RequestSupportList requestSupportList;
	private TerminateInsuranceList terminateInsuranceList; // 추가
	

	// ----------------------view--------------------
	private MainTui mainTui;

	// controller
	private C_Customer customerController;
	private C_ContractInsurance contractInsuranceController;
	private C_Employee employeeController;
	private C_InsuranceProduct insuranceProductController;
	private C_Support requestSupportController;
	private Terminator terminator; // 추가

	public MainController() {

		this.customerList = new CustomerList();
		this.contractInsuranceList = new ContractInsuranceList();
		this.employeeList = new EmployeeList();
		this.insuranceProductList = new InsuranceProductList();
		
		this.terminateInsuranceList = new TerminateInsuranceList(); // 추가
		this.terminator = new Terminator(contractInsuranceList, terminateInsuranceList); // 추가
		
		this.requestSupportList = new RequestSupportList();

		this.customerController = new C_Customer(customerList);
		this.contractInsuranceController = new C_ContractInsurance(contractInsuranceList);
		this.employeeController = new C_Employee(employeeList);
		this.insuranceProductController = new C_InsuranceProduct(insuranceProductList);
		this.requestSupportController = new C_Support(requestSupportList);
		
		
		
		// test data
		// customer list 만들자마자 testUser를 넣은 것
		Customer testUser = new Customer();
		testUser.setId("test");
		testUser.setPassword("1234");
		testUser.addPaymentBankAccount("12345678");

		BasicInsuranceInfo testBasicInsuranceInfo = new BasicInsuranceInfo("supportInsurance", InsuranceType.life,
				TermPeriod.month_1);
		InsuranceProduct testInsurance = new InsuranceProduct(testBasicInsuranceInfo, null, 123);
		
		// add customer test data
		this.customerController.addCustomer("데모유저", 25, eSex.eMale, "123456789", "test", "1234");
		
		// add Contract Insurance test data
		this.contractInsuranceController.AddContractInsurance(testInsurance, testUser, 12,
				testUser.getPaymentBankAccount().get(0), PaymentType.eBasicPayment);

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

	public TerminateInsuranceList getTerminateInsuranceList() {
		return this.terminateInsuranceList;
	}
}
