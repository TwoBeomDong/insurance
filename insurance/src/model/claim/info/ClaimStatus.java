package model.claim.info;

public enum ClaimStatus {
	requestClaim("보험금 청구"),
	standByClaim("손해사정 대기"),
	/*
	 * 관공서 내역요청 진행
	 * 손해조사 부적합
	 * 손해사정 대기
	 * 보험금 지급대기
	 * 손해사정 부적합
	 * 사고접수 거부
	 */
	standByInsuranceProvide("보험금 지급 대기"),
	investigationDeny("손해조사 부적합"),
	insuranceProvideComplete("보험금 지급완료")
	;
	private String name;
	private ClaimStatus(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public static String getClaimProcessList(ClaimStatus status){
		// @output	: 현재 청구 상태에서 할 수 있는 일
		String retString = "";
		switch(status){
		case requestClaim:
			retString += "손해조사 진행";
			break;
		case standByClaim:
			retString += "손해사정 진행";
			break;
		case standByInsuranceProvide:
			retString += "보험금 지급";
			break;
		default:
			retString = null;
			break;
		}
		return retString;
	}

}
