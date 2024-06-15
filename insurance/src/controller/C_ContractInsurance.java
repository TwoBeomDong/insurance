package controller;

import java.util.Vector;

import model.claim.RequestClaim;
import model.claim.info.ClaimStatus;
import model.contract.ContractInsurance;
import model.contract.ContractInsuranceList;
import model.insurance.InsuranceProduct;
import model.insurancePremium.PaymentType;
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
	
	public Vector<String> getContractInsuranceProcessList(ContractInsurance contractInsurance){
		// output	: 특정 보험에서 처리할 수 있는 일 리스트	
		//ex_보험 관리자 승인 / 보험 교육 승인
		ContractInsurance insurance = this.contractInsuranceList.getContractInsurance(contractInsurance);
		if(insurance == null) return null;
		RequestClaim claim = insurance.getCurrentClaim();
		Vector<String> retVector = new Vector<>();
		retVector.add("보험금 납부방식 변경 요청");
		retVector.add("보험료 납부");
		retVector.add("보험 중도해지");
		if(claim == null || claim.getClaimStatus() == ClaimStatus.insuranceProvideComplete) {
			// 이전 청구가 종결됐거나 청구한 내역이 없는 경우
			retVector.add("보험금 청구");
		}
		return retVector;
	}
}