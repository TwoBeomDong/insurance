package model.insurancePremium;

public enum PaymentType{
	eBasicPayment("일반납부"),
	eAutomaticTransfer("자동이체납부"),
	;
	private String title;
	private PaymentType(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
}