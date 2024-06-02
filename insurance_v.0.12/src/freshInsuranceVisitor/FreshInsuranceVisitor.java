package freshInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;

import controller.C_InsuranceProduct;

public interface FreshInsuranceVisitor {
	public void visitInsuranceApprovalProcess(C_InsuranceProduct insuranceList, BufferedReader objReader) throws IOException, RemoteException;
}