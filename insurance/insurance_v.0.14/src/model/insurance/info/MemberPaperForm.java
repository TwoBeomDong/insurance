package model.insurance.info;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class MemberPaperForm {

	private LinkedHashMap<String,Object> BasicPaperList;
	private boolean approvalStatus;

	public MemberPaperForm(LinkedHashMap<String,Object> BasicPaperList){
		this.BasicPaperList = BasicPaperList;
		this.approvalStatus = false;
	}
	public void approval() {
		this.approvalStatus = true;
	}
	public boolean isApproval() {
		return this.approvalStatus;
	}

	public String toString() {
		String retStr = "";
        for (Entry<String, Object> entry : BasicPaperList.entrySet()) {
        	retStr += entry.getKey() + "\t: " + (entry.getValue() == String.class ? "문자열 타입" : "논리 타입") + "\n";
        }
        return retStr;
	}
	public LinkedHashMap<String,Object> getBasicPaperList(){
		return this.BasicPaperList;
	}

}