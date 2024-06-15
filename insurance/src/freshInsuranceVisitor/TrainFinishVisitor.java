package freshInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;

import controller.C_InsuranceProduct;
import model.insurance.InsuranceProduct;
import model.user.User;
import view.MainTui;

public class TrainFinishVisitor implements FreshInsuranceVisitor{

	@Override
	public void visitFreshInsurance(User user, InsuranceProduct insurance, C_InsuranceProduct insuranceList,
			BufferedReader objReader) throws IOException {
		System.out.println("보험 교육을 완료하시겠습니까? 완료한다면 해당 보험은 정규 보험으로 전환됩니다. (yes / no)");
		if(!MainTui.getBoolean(objReader)) {
			System.out.println("보험 교육 완료 거부 요청이 정상적으로 처리되었습니다.");
			return;
		}
		if(insuranceList.transferToRegularInsurance(insurance.getID())) {
			System.out.println("보험 교육 완료 요청이 정상적으로 처리되었습니다.");
		}else {
			System.out.println("요청이 처리되지 못했습니다. 계속 해당 문제가 발생한다면 기술팀에 문의하세요.");
		}
	}

}
