package model.insurance.info;

/**
 * @author dongyeonkim
 * @version 1.0
 * @created 14-5-2024 ���� 6:43:15
 */
public enum InsuranceStatus {
	adminApprovalWait("관리자 승인 대기"),
	adminDeny("관리자 거부"),
	adminReview("관리자 검토"),
	FSSConsent("금융감독원 인가"),
	FSSDeny("금융감독원 거부"),
	FSSApproval("금융감독원 승인"),
	trainRequest("교육 의뢰"),
	trainProgress("교육 진행"),
	trainWait("교육 대기"),
	;
	private String name;
	private InsuranceStatus(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public static String getFreshInsuranceProcessList(InsuranceStatus status){
		// output	: 특정 보험에서 처리할 수 있는 일 리스트	
		//ex_보험 관리자 승인 / 보험 교육 승인
		String retString = "";
		switch(status){
		case FSSApproval:	// 금융감독원 승인
			retString += "교육 의뢰 요청";
			break;
		case FSSConsent:	// 금융감독원 인가
			retString = null;
			break;
		case FSSDeny:		// 금융감독원 거부
			retString += "보험 승인 재검토";
			break;
		case adminApprovalWait:	// 관리자 승인 대기
			retString += "보험 관리자 승인";
			break;
		case adminDeny:		// 관리자 거부
			retString += "거부사유 확인";
			break;
		case adminReview:	// 관리자 검토
			retString = null;
			break;
		case trainProgress:	// 교육 진행
			retString += "보험 교육 완료";
			break;
		case trainRequest:	// 교육 의뢰
			retString += "보험 교육 승인";
			break;
		case trainWait:		// 교육 대기
			retString += "보험 교육 승인";
			break;
		}
		return retString;
	}
}