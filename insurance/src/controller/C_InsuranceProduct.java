package controller;

import java.util.LinkedHashMap;
import java.util.Vector;

import message.Message;
import model.insurance.InsuranceProduct;
import model.insurance.InsuranceProductList;
import model.insurance.info.BasicInsuranceInfo;
import model.insurance.info.InsuranceStatus;
import model.insurance.info.InsuranceType;
import model.insurance.info.MemberPaperForm;
import model.insurance.info.ProductApprovalPaper;
import model.insurance.info.StatusChangeInfo;
import model.insurance.info.TermPeriod;
import model.user.Employee;
import model.user.User;

/**
 * @author dongyeonkim
 * @version 1.0
 * @created 14-5-2024 ���� 6:43:15
 */
public class C_InsuranceProduct {

	InsuranceProductList insuranceProductList;
	
	public C_InsuranceProduct(InsuranceProductList insuranceProductList) {
		this.insuranceProductList = insuranceProductList;
	}

	// ------------------------------신규 보험 처리부-------------------------------
	public boolean addFreshInsuranceProduct(String insuranceName, InsuranceType selectedType, TermPeriod selectedTerm,
			LinkedHashMap<String, Object> basicPaperList, Employee employee) {
		
		BasicInsuranceInfo basicInsuranceInfo = new BasicInsuranceInfo(insuranceName, selectedType, selectedTerm);
		MemberPaperForm memberPaperForm = new MemberPaperForm(basicPaperList);
		return this.insuranceProductList.addFreshInsuranceProduct(basicInsuranceInfo, memberPaperForm, employee);
	}
	
	public String approvalBasicInsuranceInfo(User user, int insuranceId, boolean approvalStatus){
		// 보험 기본정보 승인
		InsuranceProduct insurance = this.insuranceProductList.getFreshInsurance(insuranceId);
		if(insurance == null) return Message.Irregular_request.getMessage();
		if(approvalStatus) {
			insurance.getBasicInsuranceInfo().approval();
			return Message.Insurance_approve_admin_complete.getMessage();
		}else {
			StatusChangeInfo statusChangeInfo = new StatusChangeInfo();
			statusChangeInfo.setPersonInCharge(user);
			statusChangeInfo.setInsuranceStatus(InsuranceStatus.adminDeny);
			insurance.changeStatus(statusChangeInfo);
			return Message.Insurance_deny_complete.getMessage();
		}
	}
	
	public String approvalMemberPaperForm(User user, int insuranceId, boolean approvalStatus) {
		InsuranceProduct insurance = this.insuranceProductList.getFreshInsurance(insuranceId); 
		if(insurance == null) return Message.Irregular_request.getMessage();
		if(approvalStatus) {
			insurance.getMemberPaperForm().approval();
			StatusChangeInfo statusChangeInfo = new StatusChangeInfo();
			statusChangeInfo.setPersonInCharge(user);
			statusChangeInfo.setInsuranceStatus(InsuranceStatus.adminReview);
			insurance.changeStatus(statusChangeInfo);
			
			return Message.Insurance_approve_admin_complete.getMessage();
		}else {
			StatusChangeInfo statusChangeInfo = new StatusChangeInfo();
			statusChangeInfo.setPersonInCharge(user);
			statusChangeInfo.setInsuranceStatus(InsuranceStatus.adminDeny);
			insurance.changeStatus(statusChangeInfo);
			
			return Message.Insurance_deny_complete.getMessage();
		}
		
	}
	
	public String decideStandardRate(int insuranceId, float standardRate) {
		InsuranceProduct insurance = this.insuranceProductList.getFreshInsurance(insuranceId);
		if(insurance != null) {
			if(insurance.setStandardRate(standardRate)) {
				return Message.Standard_rate_decide_complete.getMessage(); 
			}
			else {
				return Message.Standard_rate_decide_failed.getMessage(); 
			}
		}
		return Message.Irregular_request.getMessage(); 
	}
	
	public String setProductApprovalPaper(User user, int insuranceId, String info) {
		InsuranceProduct insurance = this.insuranceProductList.getFreshInsurance(insuranceId); 
		if(insurance != null) {
			ProductApprovalPaper pap = new ProductApprovalPaper(info);
			insurance.setProductApprovalPaper(pap);
			
			StatusChangeInfo statusChangeInfo = new StatusChangeInfo();
			statusChangeInfo.setPersonInCharge(user);
			statusChangeInfo.setInsuranceStatus(InsuranceStatus.FSSConsent);
			insurance.changeStatus(statusChangeInfo);
			
			return Message.Product_approval_paper_set_complete.getMessage();
		}
		return Message.Irregular_request.getMessage();
	}
	
	public boolean transferToRegularInsurance(int insuranceId) {
		return this.insuranceProductList.transferToRegularInsurance(insuranceId);
	}

	
	// ------------------------------보험 가입부-------------------------------
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

	// ------------------------------정보 처리부-------------------------------
	
	// ------------------------------신규 보험-------------------------------
	public String getFreshInsuranceString() {
		return this.getInsuranceString(this.insuranceProductList.getFreshInsuranceProductList());
	}
	public InsuranceProduct getFreshInsurance(int id) {
		return this.getInsurance(this.insuranceProductList.getFreshInsuranceProductList(), id);
	}
	public Vector<InsuranceProduct> getFreshInsuranceList(){
		return this.insuranceProductList.getFreshInsuranceProductList();
	}
	
	// ------------------------------정규 보험-------------------------------
	public String getRegularInsuranceString() {
		return this.getInsuranceString(this.insuranceProductList.getRegularInsuranceProductList());
	}
	public InsuranceProduct getRegularInsurance(int id) {
		return this.getInsurance(this.insuranceProductList.getRegularInsuranceProductList(), id);
	}
	public Vector<InsuranceProduct> getRegularInsuranceList(){
		return this.insuranceProductList.getRegularInsuranceProductList();
	}
	public Vector<InsuranceProduct> getTypeRegularInsuranceList(InsuranceType type){
		Vector<InsuranceProduct> retList = new Vector<>();
		for(InsuranceProduct i : this.insuranceProductList.getRegularInsuranceProductList())
			if(i.getBasicInsuranceInfo().getType() == type) retList.add(i);
		return retList;
	}
	
	public String getInsuranceString(Vector<InsuranceProduct> list) {
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
	
	public String getMemberPaperForm(int insuranceId) {
		InsuranceProduct insurance = this.insuranceProductList.getFreshInsurance(insuranceId); 
		if(insurance == null) { // 검토
			return Message.Irregular_request.getMessage();
		}
		return insurance.getMemberPaperForm().toString();
	}

}