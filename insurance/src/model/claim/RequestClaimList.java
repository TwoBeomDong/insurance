package model.claim;

import java.util.Vector;

public class RequestClaimList {

	private Vector<RequestClaim> requestClaimList;

	public RequestClaimList() {
		requestClaimList = new Vector<RequestClaim>();
	}

	public Vector<RequestClaim> getRequestClaimList() {
		return requestClaimList;
	}
	
	public boolean addRequestClaim(RequestClaim requestClaim, String claimId) {
		requestClaim.setClaimId(claimId+this.requestClaimList.size());
		return this.requestClaimList.add(requestClaim);
	}
}
