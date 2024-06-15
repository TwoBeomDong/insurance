package model.insurancePremium;

import java.time.LocalDate;
import java.time.Period;

public class InsurancePremium {
	private PaymentType paymentType;
	private int money;	// 금액
	private LocalDate paymentDate;
	private PaymentStatus paymentStatus;
	
	public InsurancePremium() {
		this.paymentDate = LocalDate.now().plusMonths(1);	// 다음달부터 납부 시작
	}
	public InsurancePremium(PaymentType paymentType) {
		this.setPaymentType(paymentType);
		this.paymentDate = LocalDate.now().plusMonths(1);
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
	public boolean doPayment() {
		if(this.paymentStatus == PaymentStatus.completePayment) {
			return false;	// 이미 납부한 경우
		}else {
			this.paymentStatus = PaymentStatus.completePayment;
			this.paymentDate = this.paymentDate.plusMonths(1);	// 다음달로 납부일 변경
			return true;
		}
	}
	public PaymentStatus getPaymentStatus() {
		this.setPaymentStatus();
		return paymentStatus;
	}
	public LocalDate getPaymentDate() {
		return this.paymentDate;
	}
	private void setPaymentStatus() {
		LocalDate today = LocalDate.now();
		if(this.paymentDate.isAfter(today)) {	// 납부일이 오늘보다 이후
			Period period = Period.between(today.plusDays(1), this.paymentDate);
			if(period.getMonths() < 1) { // 납부일과 오늘 차이가 1달 이내
				this.paymentStatus = PaymentStatus.defaultPayment;
			}else { // 납부일과 오늘 차이가 1달 이상
				this.paymentStatus = PaymentStatus.completePayment;
			}
		}else {	// 납부일이 오늘보다 이후  
			this.paymentStatus = PaymentStatus.overduePayment;
		}
	}
	
}
