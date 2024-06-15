package model.contract;

import java.util.Vector;

import model.insurance.InsuranceProduct;

public class ContractInsuranceList {
	private Vector<ContractInsurance> contractInsuranceList;
	
	public ContractInsuranceList(){
		this.contractInsuranceList = new Vector<>();
	}

	public Vector<ContractInsurance> getContractInsuranceList() {
		return contractInsuranceList;
	}

	public void setContractInsuranceList(Vector<ContractInsurance> contractInsuranceList) {
		this.contractInsuranceList = contractInsuranceList;
	}
	
	public void AddContractInsurance(ContractInsurance contractInsurance) {
		this.contractInsuranceList.add(contractInsurance);
	}
	
	public Vector<InsuranceProduct> getRegisteredInsuranceList(String id){
		Vector<InsuranceProduct> retList = new Vector<>();
		for(ContractInsurance i : this.contractInsuranceList)
			if(i.getCustomer().equals(id)) retList.add(i.getInsuranceProduct());
		
		return retList;
	}
	public ContractInsurance getContractInsurance(ContractInsurance contractInsurance) {
		for(ContractInsurance c : this.contractInsuranceList) {
			if(contractInsurance.equals(c)) {
				return c;
			}
		}
		return null;
	}
}
