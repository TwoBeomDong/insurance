package visitor.contractInsurance;

import java.io.BufferedReader;
import java.io.IOException;

import model.contract.ContractInsurance;
import model.insurancePremium.PaymentType;
import view.MainTui;

public class ChangePaymentTypeVisitor implements ContractInsuranceVisitor{

	@Override
	public void visitContractInsurance(ContractInsurance contractInsurance, BufferedReader objReader)
			throws IOException {
		PaymentType paymentType = contractInsurance.getPaymentType();
		PaymentType newPaymentType = (paymentType == PaymentType.eBasicPayment ? PaymentType.eAutomaticTransfer : PaymentType.eBasicPayment);
		System.out.println("현재 해당 보험의 납부방식은 ["+paymentType.getTitle()+"] 입니다.");
		System.out.println("납부방식을 ["+newPaymentType.getTitle()+"] 로 변경하시겠습니까? (yes / no)");
		if(paymentType == PaymentType.eBasicPayment) {
			System.out.println("현재 등록된 계좌로 자동이체가 설정됩니다.");
		}
		if(MainTui.getBoolean(objReader)) {
			contractInsurance.setPaymentType(newPaymentType);
			System.out.println("보험료 납부방식이 정상적으로 변경되었습니다.");
		}else {
			System.out.println("납부방식 변경을 거부하였습니다. 초기화면으로 돌아갑니다.");
		}
	}

}
