package model.support;

import java.util.Vector;

public class RequestSupportList {

	private Vector<RequestSupport> requestSupportsList;

	public RequestSupportList() {
		requestSupportsList = new Vector<RequestSupport>();
	}

	public Vector<RequestSupport> getRequestSupportsList() {
		return requestSupportsList;
	}

	public void setRequestSupportsList(Vector<RequestSupport> requestSupportsList) {
		this.requestSupportsList = requestSupportsList;
	}

}
