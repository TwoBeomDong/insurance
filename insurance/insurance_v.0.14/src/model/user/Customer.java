package model.user;

import java.util.Vector;

public class Customer extends User {

	private Vector<String> paymentBankAccount;

	public Customer(){
		this.paymentBankAccount = new Vector<>();
	}
	
	public Vector<String> getPaymentBankAccount() {
		return paymentBankAccount;
	}


	public void addPaymentBankAccount(String paymentBankAccount) {
		this.paymentBankAccount.add(paymentBankAccount);
	}


	public void finalize() throws Throwable {
		super.finalize();
	}

}