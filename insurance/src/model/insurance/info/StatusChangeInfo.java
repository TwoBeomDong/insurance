package model.insurance.info;

import java.time.LocalDate;

import model.user.Employee;
import model.user.User;


public class StatusChangeInfo {
	private InsuranceStatus insuranceStatus;
	private User personInCharge;
	private final LocalDate changeDate;
	private String changeReason;

	public StatusChangeInfo(){
		this.changeDate = LocalDate.now();
	}

	public InsuranceStatus getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(InsuranceStatus insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}

	public User getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(User personInCharge) {
		this.personInCharge = personInCharge;
	}

	public LocalDate getChangeDate() {
		return changeDate;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}


}