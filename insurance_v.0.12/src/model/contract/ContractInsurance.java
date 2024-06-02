package model.contract;


import java.time.LocalDate;

import model.insurance.InsuranceProduct;
import model.user.Customer;


public class ContractInsurance {
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
	private LocalDate contractDate;
	private Customer customer;
	private LocalDate expireDate;
	private InsuranceProduct insuranceProduct;
	private String paymentBankAccount;
	private PaymentType paymentType;
	// 보험비 추가해야됨.

	public ContractInsurance(){

	}

	public LocalDate getContractDate() {
		return contractDate;
	}

	public void contract() {
		this.contractDate = LocalDate.now();
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(int month) {
		this.expireDate = this.contractDate.plusMonths(month);
	}

	public InsuranceProduct getInsuranceProduct() {
		return insuranceProduct;
	}

	public void setInsuranceProduct(InsuranceProduct insuranceProduct) {
		this.insuranceProduct = insuranceProduct;
	}

	public String getPaymentBankAccount() {
		return paymentBankAccount;
	}

	public void setPaymentBankAccount(String paymentBankAccount) {
		this.paymentBankAccount = paymentBankAccount;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	

}