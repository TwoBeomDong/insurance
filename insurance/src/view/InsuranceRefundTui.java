package view;

import java.io.BufferedReader;
import java.util.Vector;

import controller.MainController;
import model.claim.RequestClaim;

public class InsuranceRefundTui {
	
	private MainController mainController;
	
	public void associate(MainController mainController) {
		this.mainController = mainController;
		
	}
	
	public InsuranceRefundTui() {
		
		
	}
	public void printClaimList(BufferedReader objReader) {
		System.out.println("청구를 승인할 보험금");
		Vector<RequestClaim> list = this.mainController.getRequestClaimController().getRequestClaim();
		int index =1;
		for(RequestClaim requestClaim : list) {
			System.out.println(index + ": "+requestClaim);
			index++;
		}
		
	
	}
	
	
}
