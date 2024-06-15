package model.claim;

import model.claim.info.ClaimStatus;
import model.contract.ContractInsurance;

public class RequestClaim {
	
	private String accidentDate;
	private String causer;
	private String place;
	private String detail;
	private String name; 
	private String phoneNumber;
	private String address;
	private String damageAmount;
	private int compensationAmount;
	
	private ClaimStatus claimStatus;
	
	
	//사고일시, 사고원인, 사고장소, 사고내용, 피해자 성명, 피해자 연락처 ,피해자 주소, 피해내역/품목
	
	//constructor
	public RequestClaim() {
	}
	
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
	public String getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(String accidentDate) {
		this.accidentDate = accidentDate;
	}
	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
	}
	public int getCompensationAmount() {
		return compensationAmount;
	}

	public void setCompensationAmount(int compensationAmount) {
		this.compensationAmount = compensationAmount;
	}


	@Override
	public String toString() {
		return "---------------------------\n" +
				"accidentDate: " + accidentDate + "\n" +
				"causer: " + causer + "\n" +
				"place: " + place + "\n" +
				"detail: " + detail + "\n" +
				"name: " + name + "\n" +
				"phoneNumber: " + phoneNumber + "\n" +
				"address: " + address + "\n" +
				"damageAmount: " + damageAmount + "\n" +
				"claimStatus: " + claimStatus.getName() + "\n" +
				"------------------------------------";
	}
}
