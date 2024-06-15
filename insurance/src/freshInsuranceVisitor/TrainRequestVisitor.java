package freshInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;

import controller.C_InsuranceProduct;
import model.insurance.InsuranceProduct;
import model.insurance.info.InsuranceStatus;
import model.insurance.info.StatusChangeInfo;
import model.user.User;
import view.MainTui;

public class TrainRequestVisitor implements FreshInsuranceVisitor{

	@Override
	public void visitFreshInsurance(User user, InsuranceProduct insurance, C_InsuranceProduct insuranceList,
			BufferedReader objReader) throws IOException {
		System.out.println("해당 보험을 교육 의뢰 하시겠습니까? (yes / no)");
		boolean isRequest = MainTui.getBoolean(objReader);
		if(isRequest) {
			StatusChangeInfo info = new StatusChangeInfo();
			info.setPersonInCharge(user);
			info.setInsuranceStatus(InsuranceStatus.trainRequest);
			insurance.changeStatus(info);
			System.out.println("해당 보험에 대한 교육 의뢰 요청이 정상적으로 처리되었습니다.");
		}else {
			System.out.println("교육 의뢰를 거부했습니다. 초기 화면으로 돌아갑니다.");
		}
	}

}
