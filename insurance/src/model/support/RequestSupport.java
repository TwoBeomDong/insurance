package model.support;

import model.contract.ContractInsurance;
import model.insurance.InsuranceProduct;
import model.user.Customer;

public class RequestSupport {
	
	private String accidentDate;
	private String causer;
	private String place;
	private String detail;
	private String name; 
	private String phoneNumber;
	private String address;
	private String damageAmount;
	
	//사고일시, 사고원인, 사고장소, 사고내용, 피해자 성명, 피해자 연락처 ,피해자 주소, 피해내역/품목
	// cutomer
	private Customer customer;
	// insurance
	private ContractInsurance contractInsurance;
	

	public String getCauser() {
		return causer;
	}
	public void setCauser(String causer) {
		this.causer = causer;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDamageAmount() {
		return damageAmount;
	}
	public void setDamageAmount(String accidentObject) {
		this.damageAmount = accidentObject;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}
	public ContractInsurance getContractInsurance() {
		return contractInsurance;
	}
	public void setContractInsurance(ContractInsurance contractInsurance) {
		this.contractInsurance = contractInsurance;
	}
	
	
	

}
