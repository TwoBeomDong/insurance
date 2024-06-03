package controller;

import model.contract.ContractInsurance;
import model.support.RequestSupport;
import model.support.RequestSupportList;
import model.user.Customer;

public class C_Support {
	private RequestSupportList requestSupportList;
	
	public C_Support(RequestSupportList requestSupportList) {
		this.requestSupportList = requestSupportList;
	}

	public boolean addRequestSupport(String accidentDate, String causer, String place, String detail, String name, String phoneNumber, String address, String damageAmount, Customer customer, ContractInsurance selectedInsurance) {
		
		RequestSupport requestSupport = new RequestSupport();
		requestSupport.setAccidentDate(accidentDate);
		requestSupport.setCauser(causer);
		requestSupport.setPlace(place);
		requestSupport.setDetail(detail);
		requestSupport.setName(name);
		requestSupport.setPhoneNumber(phoneNumber);
		requestSupport.setAddress(address);
		requestSupport.setDamageAmount(accidentDate);
		requestSupport.setCustomer(customer);
		requestSupport.setContractInsurance(selectedInsurance);
		
		this.requestSupportList.getRequestSupportsList().add(requestSupport);
		
		System.out.println(requestSupportList.getRequestSupportsList().get(0).toString());
		return true;
	}
	
}
