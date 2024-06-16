package claimInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;

import model.claim.RequestClaim;

public interface ClaimInsuranceVisitor {
	public void visitContractInsurance(RequestClaim requestClaim, BufferedReader objReader)throws IOException ;
}
