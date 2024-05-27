package freshInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.rmi.RemoteException;

import controll.InsuranceProductList;

public interface FreshInsuranceVisitor {
	public void visitInsuranceApprovalProcess(InsuranceProductList insuranceList, BufferedReader objReader) throws IOException, RemoteException;
}