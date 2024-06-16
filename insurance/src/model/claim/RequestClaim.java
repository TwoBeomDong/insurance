package model.claim;

import java.time.LocalDate;

import model.claim.info.ClaimStatus;
import model.contract.ContractInsurance;

public class RequestClaim {
	//식별자
	private String ClaimId;
	//경위서 관련 field
	private LocalDate accidentDate;
	private String causer;
	private String place;
	private String detail;
	private String name; 
	private String phoneNumber;
	private String address;
	private String damageAmount;
	// 보상액 field
	private int compensationAmount;
	// 손해조사 field
	private String EmployOpinion;	// 검토소견
	// 상태 field
	private ClaimStatus claimStatus;
	//청구에 해당하는 보험
	private ContractInsurance contractInsurance;
	
	//사고일시, 사고원인, 사고장소, 사고내용, 피해자 성명, 피해자 연락처 ,피해자 주소, 피해내역/품목
	
	//constructor
	public RequestClaim(ContractInsurance contractInsurance) {
		this.contractInsurance = contractInsurance;
	}
	public void setClaimId(String id) {
		this.ClaimId = id;
	}
	public String getClaimId() {
		return this.ClaimId;
	}
	public boolean equals(String id) {
		return this.ClaimId.equals(id);
	}
	
	public ContractInsurance getContractInsurance() {
		return this.contractInsurance;
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
	public LocalDate getAccidentDate() {
		return accidentDate;
	}
	public void setAccidentDate(LocalDate accidentDate) {
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

	public String getEmployOpinion() {
		return EmployOpinion;
	}

	public void setEmployOpinion(String employOpinion) {
		EmployOpinion = employOpinion;
	}
}
