package model.insurancePremium;

import java.time.LocalDate;

public class InsurancePremium {
	private PaymentType paymentType;
	private int money;	// 금액
	private LocalDate paymentDate;
	private PaymentStatus paymentStatus;
	
	public InsurancePremium() {
	}
	public InsurancePremium(PaymentType paymentType) {
		this.setPaymentType(paymentType);
	}

	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	public void doPayment() {
		this.paymentStatus = PaymentStatus.completePayment;
	}
	
}
