package model.insurance;

import java.util.Vector;

import model.insurance.info.BasicInsuranceInfo;
import model.insurance.info.MemberPaperForm;

public class InsuranceProductList {
	private Vector<InsuranceProduct> freshInsuranceProductList;
	private Vector<InsuranceProduct> RegularInsuranceProductList;

	public InsuranceProductList() {
		this.freshInsuranceProductList = new Vector<>();
		this.RegularInsuranceProductList = new Vector<>();
	}

	// ------------------------------- 신규 보험 처리부 -------------------------------------
	public Vector<InsuranceProduct> getFreshInsuranceProductList() {
		return freshInsuranceProductList;
	}

	public void setFreshInsuranceProductList(Vector<InsuranceProduct> freshInsuranceProductList) {
		this.freshInsuranceProductList = freshInsuranceProductList;
	}
	
	public InsuranceProduct getFreshInsurance(int insurnaceId) {
		for(InsuranceProduct i : this.freshInsuranceProductList) {
			if(i.equals(insurnaceId)) return i;
		}
		return null;
	}
	public boolean addFreshInsuranceProduct(BasicInsuranceInfo basicInsuranceInfo, MemberPaperForm memberPaperForm) {
		InsuranceProduct freshInsurance = new InsuranceProduct(basicInsuranceInfo, memberPaperForm, this.freshInsuranceProductList.size());
		return this.freshInsuranceProductList.add(freshInsurance);
	}
	
	// ------------------------------- 기존 보험 처리부 -------------------------------------
	public Vector<InsuranceProduct> getRegularInsuranceProductList() {
		return RegularInsuranceProductList;
	}

	public void setRegularInsuranceProductList(Vector<InsuranceProduct> regularInsuranceProductList) {
		this.RegularInsuranceProductList = regularInsuranceProductList;
	}

	public InsuranceProduct getRegularInsurance(int insurnaceId) {
		for(InsuranceProduct i : this.RegularInsuranceProductList) {
			if(i.equals(insurnaceId)) return i;
		}
		return null;
	}
}
