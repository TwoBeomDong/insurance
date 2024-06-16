package claimInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;

import model.claim.RequestClaim;
import model.claim.info.ClaimStatus;
import view.MainTui;

public class ClaimApprovalVisitor implements ClaimInsuranceVisitor{

	@Override
	public void visitContractInsurance(RequestClaim requestClaim, BufferedReader objReader) throws IOException {
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
		System.out.println("---------------------------");
		System.out.println("해당 청구를 승인하시겠습니까? (yes / no)");
		String employOpinion;
		if(MainTui.getBoolean(objReader)) {
			System.out.println("검토 소견을 입력하세요 : ");
			employOpinion = objReader.readLine().trim();
			requestClaim.setClaimStatus(ClaimStatus.standByClaim);
			System.out.println("손해조사가 완료처리 되었습니다.");
		}else {
			System.out.print("부적합 사유를 입력하세요: ");
			employOpinion = objReader.readLine().trim();
			requestClaim.setClaimStatus(ClaimStatus.investigationDeny);
			System.out.println("청구 거부가 정상적으로 완료되었습니다.");
		}
		requestClaim.setEmployOpinion(employOpinion);
	}

}
