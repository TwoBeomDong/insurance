package visitor.contractInsurance;

import java.io.BufferedReader;
import java.io.IOException;

import model.contract.ContractInsurance;
import model.insurancePremium.PaymentType;
import view.MainTui;

public class PayPremiumVisitor implements ContractInsuranceVisitor{

	@Override
	public void visitContractInsurance(ContractInsurance contractInsurance, BufferedReader objReader)
			throws IOException {
		if(contractInsurance.getPaymentType() == PaymentType.eAutomaticTransfer) {
			System.out.println("해당 보험은 자동이체 납부 방식으로 등록된 보험입니다.");
			System.out.println("납부 방식을 변경하시려면 납부 방식 변경 메뉴 이용 바랍니다.");
			return;
		}
		System.out.println("납부 보험료 : "+contractInsurance.getMoney());
		System.out.println("보험료를 납부하시겠습니까? (yes / no)");
		if(MainTui.getBoolean(objReader)) {
			if(contractInsurance.doPayment()) {
				System.out.println("보험료가 정상적으로 납부됐습니다. 초기화면으로 돌아갑니다.");
			}else {
				System.out.println("이미 보험료를 납부했습니다.");
			}
		}else {
			System.out.println("보험료 납부를 거부했습니다. 초기화면으로 돌아갑니다.");
		}
	}

}
