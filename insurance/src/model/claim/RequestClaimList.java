package model.claim;

import java.util.Vector;

public class RequestClaimList {

	private Vector<RequestClaim> requestSupportsList;

	public RequestClaimList() {
		requestSupportsList = new Vector<RequestClaim>();
	}

	public Vector<RequestClaim> getRequestSupportsList() {
		return requestSupportsList;
	}

	public void setRequestSupportsList(Vector<RequestClaim> requestSupportsList) {
		this.requestSupportsList = requestSupportsList;
	}

}
