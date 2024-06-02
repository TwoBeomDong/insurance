package model.insurance;

import java.util.LinkedHashMap;
import java.util.Vector;

import model.insurance.info.BasicInsuranceInfo;
import model.insurance.info.InsuranceType;
import model.insurance.info.MemberPaperForm;
import model.insurance.info.TermPeriod;

public class InsuranceProductList {
	private Vector<InsuranceProduct> freshInsuranceProductList;
	private Vector<InsuranceProduct> RegularInsuranceProductList;

	public InsuranceProductList() {
		this.freshInsuranceProductList = new Vector<>();
		this.RegularInsuranceProductList = new Vector<>();
		
		BasicInsuranceInfo demoInfo = new BasicInsuranceInfo("테스트 보험", InsuranceType.life, TermPeriod.month_6);
		demoInfo.approval();
		LinkedHashMap<String,Object> demoMap = new LinkedHashMap<>();
		demoMap.put("기저질환 여부", Boolean.class);
		MemberPaperForm demoMemberForm = new MemberPaperForm(demoMap);
		demoMemberForm.approval();
		InsuranceProduct testRegularInsurance = new InsuranceProduct(demoInfo,demoMemberForm, 1);
		this.RegularInsuranceProductList.add(testRegularInsurance);
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
		InsuranceProduct freshInsurance = new InsuranceProduct(basicInsuranceInfo, memberPaperForm, this.freshInsuranceProductList.size()+1);
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
