package model.insurancePremium;

public enum PaymentStatus {
	completePayment("납부완료"),
	defaultPayment("미납"),
	overduePayment("연체"),
	;
	private String title;
	private PaymentStatus(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
}
