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
	insuranceProvideComplete("보험금 지급완료")
	;
	private String name;
	private ClaimStatus(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}
