package controller;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Vector;

import model.insurance.BasicInsuranceInfo;
import model.insurance.InsuranceProduct;
import model.insurance.InsuranceProductList;
import model.insurance.InsuranceStatus;
import model.insurance.InsuranceType;
import model.insurance.MemberPaperForm;
import model.insurance.StatusChangeInfo;
import model.insurance.TermPeriod;

/**
 * @author dongyeonkim
 * @version 1.0
 * @created 14-5-2024 ���� 6:43:15
 */
public class InsuranceProductController {

	BasicInsuranceInfo basicInsuranceInfo;
	MemberPaperForm memberPaperForm;
	InsuranceProduct insuranceProduct;
	InsuranceProductList insuranceProductList;
	
	public InsuranceProductController(InsuranceProductList insuranceProductList) {
	}
	

	public boolean addFreshInsuranceProduct(BasicInsuranceInfo basicInsuranceInfo, MemberPaperForm memberPaperForm) {
		int id = insuranceProductList.getFreshInsuranceProductList().size() + insuranceProductList.getRegularInsuranceProductList().size();
		InsuranceProduct insuranceProduct = new InsuranceProduct(basicInsuranceInfo, memberPaperForm, id);
		
		// 상태 저장부 -> 나중에 빌더 패턴 쓰거나 다른 방식으로 수정하기.
		StatusChangeInfo statusChangeInfo = new StatusChangeInfo();
		statusChangeInfo.setChangeDate(LocalDate.now());
		statusChangeInfo.setPersonInCharge(null);
		statusChangeInfo.setInsuranceStatus(InsuranceStatus.adminApprovalWait);
		insuranceProduct.changeStatus(statusChangeInfo);
		
		this.insuranceProductList.getFreshInsuranceProductList().add(insuranceProduct);
		
		return true;
	}

	public boolean registerRegularInsutranceProduct(InsuranceProduct insuranceProduct) {
		for(InsuranceProduct fresh : insuranceProductList.getFreshInsuranceProductList()) {
			if(fresh.equals(insuranceProduct.getID())) {
				this.insuranceProductList.getFreshInsuranceProductList().remove(insuranceProduct);
				this.insuranceProductList.getRegularInsuranceProductList().add(insuranceProduct);
				return true;
			}
		}
		return false;
	}

	public String getFreshInsuranceString() {
		return this.getInsuranceString(this.insuranceProductList.getFreshInsuranceProductList());
	}
	public InsuranceProduct getFreshInsurance(int id) {
		return this.getInsurance(this.insuranceProductList.getFreshInsuranceProductList(), id);
	}
	
	public String getRegularInsuranceString() {
		return this.getInsuranceString(this.insuranceProductList.getRegularInsuranceProductList());
	}
	public InsuranceProduct getRegularInsurance(int id) {
		return this.getInsurance(this.insuranceProductList.getRegularInsuranceProductList(), id);
	}
	
	private String getInsuranceString(Vector<InsuranceProduct> list) {
		String retStr = "";
		for(InsuranceProduct i : list) {
			retStr += i.toString()+"\n";
		}
		return retStr;
	}
	private InsuranceProduct getInsurance(Vector<InsuranceProduct> list, int id) {
		for(InsuranceProduct i : list) {
			if(i.equals(id)) return i;
		}
		return null;
	}


	public boolean addNewInsurance(String insuranceName, InsuranceType selectedType, TermPeriod selectedTerm,
			LinkedHashMap<String, String> basicPaperList) {
		
		// 매개변수 값 다 받아서 저장하기
		// 현재 무조건 true 반환
//		basicInsuranceInfo = new BasicInsuranceInfo(insuranceName, selectedType, selectedTerm);
//		MemberPaperForm memberPaperForm = new MemberPaperForm(basicPaperList);
//		
		return true;
	}
}