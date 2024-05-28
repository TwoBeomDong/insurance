package model.user;

/**
 * @author dongyeonkim
 * @version 1.0
 * @created 14-5-2024 ���� 6:43:14
 */
public class Customer extends User {

	private String paymentBankAccount;

	public Customer(){

	}

	
	public String getPaymentBankAccount() {
		return paymentBankAccount;
	}


	public void setPaymentBankAccount(String paymentBankAccount) {
		this.paymentBankAccount = paymentBankAccount;
	}


	public void finalize() throws Throwable {
		super.finalize();
	}

}