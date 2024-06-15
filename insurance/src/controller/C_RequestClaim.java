package controller;

import java.util.Vector;

import model.claim.RequestClaim;
import model.claim.RequestClaimList;

public class C_RequestClaim {
	private RequestClaimList requestClaimList;

	public C_RequestClaim(RequestClaimList requestClaimList) {
		this.requestClaimList = requestClaimList;
	}
	
	public Vector<RequestClaim> getRequestClaim(){
		return this.requestClaimList.getRequestClaimList();
	};
}

