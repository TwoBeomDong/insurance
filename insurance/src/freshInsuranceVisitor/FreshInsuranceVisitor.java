package freshInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;

import controller.InsuranceProductController;

public interface FreshInsuranceVisitor {
	public void visitInsuranceApprovalProcess(InsuranceProductController insuranceList, BufferedReader objReader) throws IOException, RemoteException;
}