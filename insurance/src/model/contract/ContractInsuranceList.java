package model.contract;

import java.util.Vector;

public class ContractInsuranceList {

	public Vector<ContractInsurance> getContractInsuranceList() {
		return contractInsuranceList;
	}

	public void setContractInsuranceList(Vector<ContractInsurance> contractInsuranceList) {
		this.contractInsuranceList = contractInsuranceList;
	}

	private Vector<ContractInsurance> contractInsuranceList;

	public ContractInsuranceList(){
		this.contractInsuranceList = new Vector<>();
	}
}
