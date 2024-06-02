package controller;

import java.util.Vector;

import model.contract.ContractInsurance;
import model.contract.ContractInsurance.PaymentType;
import model.contract.ContractInsuranceList;
import model.insurance.InsuranceProduct;
import model.user.Customer;

public class C_ContractInsurance {

	private ContractInsuranceList contractInsuranceList;

	public C_ContractInsurance(ContractInsuranceList contractInsuranceList) {
		this.contractInsuranceList = contractInsuranceList;

	}

	public void AddContractInsurance(InsuranceProduct insurance, Customer customer, int quarter, String account,
			PaymentType paymentType) {
		ContractInsurance contractInsurance = new ContractInsurance();
		contractInsurance.setCustomer(customer);
		contractInsurance.setInsuranceProduct(insurance);
		contractInsurance.contract();
		contractInsurance.setExpireDate(quarter);
		contractInsurance.setPaymentBankAccount(account);
		contractInsurance.setPaymentType(paymentType);

		this.contractInsuranceList.AddContractInsurance(contractInsurance);
	}

	public Vector<InsuranceProduct> getRegisteredInsuranceList(Customer customer) {
		// 고객이 가입한 보험 리스트 반환
		return this.contractInsuranceList.getRegisteredInsuranceList(customer.getId());
	}

	public Vector<ContractInsurance> getSelectedCustomer(Customer customer) {
		// TODO Auto-generated method stub// 얘한테 파라미터로 Customer 를 받아. 함수가 !
		// 메소드 안에다가도 Vector<InusranceProducst> list = new ---

		Vector<ContractInsurance> customerInsuranceList = new Vector<>();
		
		// for문 돌려서 판별을 해. 
		for(ContractInsurance selectedInsurance : this.contractInsuranceList.getContractInsuranceList()) {
			if(selectedInsurance.getCustomer().equals(customer)) {
				customerInsuranceList.add(selectedInsurance);
			}
		}
		return customerInsuranceList;
	}
}