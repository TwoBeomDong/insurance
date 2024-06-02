package model.insurance.info;

import java.time.LocalDate;

import model.user.Employee;


public class StatusChangeInfo {
	private InsuranceStatus insuranceStatus;
	private Employee personInCharge;
	private LocalDate changeDate;

	public StatusChangeInfo(){

	}

	public InsuranceStatus getInsuranceStatus() {
		return insuranceStatus;
	}

	public void setInsuranceStatus(InsuranceStatus insuranceStatus) {
		this.insuranceStatus = insuranceStatus;
	}

	public Employee getPersonInCharge() {
		return personInCharge;
	}

	public void setPersonInCharge(Employee personInCharge) {
		this.personInCharge = personInCharge;
	}

	public LocalDate getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(LocalDate changeDate) {
		this.changeDate = changeDate;
	}
	
	public void finalize() throws Throwable {
		
	}

}