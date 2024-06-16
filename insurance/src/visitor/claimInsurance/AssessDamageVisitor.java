package visitor.claimInsurance;

import java.io.BufferedReader;
import java.io.IOException;

import model.claim.RequestClaim;
import model.claim.info.ClaimStatus;
import model.contract.ContractInsurance;
import view.MainTui;

public class AssessDamageVisitor implements ClaimInsuranceVisitor{

	@Override
	public void visitContractInsurance(RequestClaim requestClaim, BufferedReader objReader) throws IOException {

		ContractInsurance associatedContractInsurance = requestClaim.getContractInsurance();
		System.out.println("선택된 청구의 상세 정보");
		System.out.println("---------------------------");
		System.out.println("사고일 : " + requestClaim.getAccidentDate());
		System.out.println("사고원인 : " + requestClaim.getCauser());
		System.out.println("사고장소 : " + requestClaim.getPlace());
		System.out.println("사고원인 : " + requestClaim.getDetail());
		System.out.println("피해자 성명: " + requestClaim.getName());
		System.out.println("피해자 연락처: " + requestClaim.getPhoneNumber());
		System.out.println("피해자 주소: " + requestClaim.getAddress());
		System.out.println("피해내역: " + requestClaim.getDamageAmount());
		System.out.println("현재 진행상황: " + requestClaim.getClaimStatus().getName());
		System.out.println("관리자 소견: " + requestClaim.getEmployOpinion());
		System.out.println("------------------------------------");
		System.out.println("연결된 계약 보험 정보");
		System.out.println(
				"보험명 : " + associatedContractInsurance.getInsuranceProduct().getBasicInsuranceInfo().getName());
		System.out.println("보험 가입일 : " + associatedContractInsurance.getContractDate());
		System.out.println("만기 예정일 : " + associatedContractInsurance.getExpireDate());
		System.out.println("보험료 납입계좌 : " + associatedContractInsurance.getPaymentBankAccount());
		System.out.println("현재 납입방식 : " + associatedContractInsurance.getPaymentType().getTitle());
		System.out.println("현재 보험료 : " + associatedContractInsurance.getMoney());
		System.out.println("다음 납입일 : " + associatedContractInsurance.getPaymentDate());
		System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		

		System.out.println();
		System.out.println("산정 보상액을 입력하시겠습니까?  (yes/no):");
		if (!MainTui.getBoolean(objReader)) {
			System.out.println("업무 처리를 거부했습니다. 초기 화면으로 돌아갑니다.");
			return;
		}

		System.out.println("산정 보상액 입력: ");
		int compensationAmount = MainTui.getInputInteger(objReader, Integer.MAX_VALUE);
		requestClaim.setCompensationAmount(compensationAmount);
		System.out.println("보상액 선정이 완료되었습니다.");
		requestClaim.setClaimStatus(ClaimStatus.standByInsuranceProvide);
	}

}
