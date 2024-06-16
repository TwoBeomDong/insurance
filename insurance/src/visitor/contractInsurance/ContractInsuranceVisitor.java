package visitor.contractInsurance;

import java.io.BufferedReader;
import java.io.IOException;

import model.contract.ContractInsurance;

public interface ContractInsuranceVisitor {
	public void visitContractInsurance(ContractInsurance contractInsurance, BufferedReader objReader)throws IOException ;
}
