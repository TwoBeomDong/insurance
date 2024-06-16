package visitor.claimInsurance;

import java.io.BufferedReader;
import java.io.IOException;

import model.claim.RequestClaim;
import model.claim.info.ClaimStatus;
import model.contract.ContractInsurance;
import view.MainTui;

public class InsuranceMoneyPaymentVisitor implements ClaimInsuranceVisitor{

	@Override
	public void visitContractInsurance(RequestClaim requestClaim, BufferedReader objReader) throws IOException {
		ContractInsurance associatedContractInsurance = requestClaim.getContractInsurance();
		System.out.println("청구 상세 정보");
		System.out.println("---------------------------");
		System.out.println("보험 가입일 : " + associatedContractInsurance.getContractDate());
		System.out.println("보험금 지급 계좌 : " + associatedContractInsurance.getPaymentBankAccount());
		System.out.println("보상액 : " + requestClaim.getCompensationAmount());
		System.out.println("---------------------------");
		System.out.println("산정 보상액을 지급하시겠습니까?  (yes/no):");
		if (!MainTui.getBoolean(objReader)) {
			System.out.println("업무 처리를 거부했습니다. 초기 화면으로 돌아갑니다.");
			return;
		} else {
			System.out.println("최종 보험금이 지급되었습니다.");
			requestClaim.setClaimStatus(ClaimStatus.insuranceProvideComplete);
		}
	}

}
