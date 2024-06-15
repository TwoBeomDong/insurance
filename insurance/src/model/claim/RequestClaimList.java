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

	public void setRequestClaimList(Vector<RequestClaim> requestSupportsList) {
		this.requestClaimList = requestSupportsList;
	}

}
