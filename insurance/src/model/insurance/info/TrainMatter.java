package model.insurance.info;

import java.time.LocalDate;
import java.util.Vector;

import model.user.Division;

public class TrainMatter {
	private LocalDate closingDate_Estimated;
	private String place;
	private Vector<Division> targetDivision;
	private String instructorName;
	
	public TrainMatter() {
		
	}

	public LocalDate getEstmatedClosingDate() {
		return closingDate_Estimated;
	}

	public void setEstimatedClosingDate(LocalDate schedule) {
		this.closingDate_Estimated = schedule;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Vector<Division> getTargetDivision() {
		return targetDivision;
	}

	public void setTargetDivision(Vector<Division> targetDivision) {
		this.targetDivision = targetDivision;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	
}
