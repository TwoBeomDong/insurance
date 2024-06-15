package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import contractInsuranceVisitor.ChangePaymentTypeVisitor;
import contractInsuranceVisitor.ContractInsuranceVisitor;
import contractInsuranceVisitor.InsuranceClaimVisitor;
import contractInsuranceVisitor.PayPremiumVisitor;
import controller.MainController;
import model.claim.RequestClaim;
import model.claim.info.ClaimStatus;
import model.contract.ContractInsurance;
import model.user.Customer;

public class ContractInsuranceTui {

	private MainController mainController;

	public void associate(MainController mainController) {
		this.mainController = mainController;
	}

	public void printContractInsurance(BufferedReader objReader, Customer customer) throws IOException {
		/*
		 * 기존 보험 확인부
		 * 
		 * 사용자가 선택한 메뉴에 대한 일을 처리할 비지터 호출
		 */
		Vector<ContractInsurance> list = this.mainController.getContractInsuranceController()
				.getSelectedCustomer(customer);

		// 없으면 가입보험 없음
		if (list == null || list.size() == 0) {
			System.out.println("현재 가입중이신 보험 상품이 없습니다.");
			return;
		} else {
			for (int i = 0; i < list.size(); i++) {
				System.out
						.println((i + 1) + ". " + list.get(i).getInsuranceProduct().getBasicInsuranceInfo().getName());
			}
		}
		System.out.println("처리할 신규 보험 번호를 입력하여 주십시오.");
		System.out.print("입력 : ");
		int index = MainTui.getInputInteger(objReader, list.size());
		ContractInsurance selectedInsurance = list.get(index - 1);

		System.out.println("********** 가입보험정보 **********");
		System.out.println("보험명 : " + selectedInsurance.getInsuranceProduct().getBasicInsuranceInfo().getName());
		System.out.println("보험 가입일 : " + selectedInsurance.getContractDate());
		System.out.println("만기 예정일 : " + selectedInsurance.getExpireDate());
		System.out.println("보험료 납입계좌 : " + selectedInsurance.getPaymentBankAccount());
		System.out.println("현재 납입방식 : " + selectedInsurance.getPaymentType().getTitle());
		System.out.println("현재 보험료 : " + selectedInsurance.getMoney());
		System.out.println("다음 납입일 : " + selectedInsurance.getPaymentDate());
		System.out.println("납입상태 : " + selectedInsurance.getPaymentStatus().getTitle());
		System.out.println("*******************************");

		Vector<String> processList = this.mainController.getContractInsuranceController()
				.getContractInsuranceProcessList(selectedInsurance);
		if (processList == null) {
			System.out.println("현재 해당 보험에 대한 처리 가능 목록이 없습니다. 초기 화면으로 돌아갑니다.");
			return;
		}
		for (int i = 0; i < processList.size(); i++) {
			System.out.println((i + 1) + ": " + processList.get(i));
		}
		System.out.println("처리할 업무 번호를 입력하세요 : ");
		int processIndex = MainTui.getInputInteger(objReader, processList.size());

		// 각 업무에 알맞은 visitor 할당
		ContractInsuranceVisitor visitor = null;
		switch (processIndex) {
		case 1: // 보험료 납부방식 변경 요청
			visitor = new ChangePaymentTypeVisitor();
			break;
		case 2: // 보험료 납부
			visitor = new PayPremiumVisitor();
			break;
		case 3: // 보험 중도해지
			break;
		case 4: // 보험금 청구
			visitor = new InsuranceClaimVisitor();
			break;
		}
		visitor.visitContractInsurance(selectedInsurance, objReader);
	}

	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ청구 리스트 확인 (손해조사) ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public void printClaimList(BufferedReader objReader) throws IOException {
		Vector<RequestClaim> requestClaimList = this.mainController.getContractInsuranceController()
				.getRequestClaimList();
		for (int i = 0; i < requestClaimList.size(); i++) {
			System.out.println((i + 1) + ": [accidentDate: " + requestClaimList.get(i).getAccidentDate() + "]");
		}

		System.out.println("어떤 청구에 관한 손해조사를 진행하시겠습니까? (ex 1, 2, 3):");
		int selectedIndex = MainTui.getInputInteger(objReader, requestClaimList.size());

		RequestClaim selectedClaim = requestClaimList.get(selectedIndex - 1);
		System.out.println("선택된 청구의 상세 정보:");
		System.out.println(selectedClaim);
		selectedClaim.setClaimStatus(ClaimStatus.standByClaim);
		System.out.println("손해조사가 완료처리 되었습니다.");
	}

	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ손해사정 대기목록 확인ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public void printStandByClaim(BufferedReader objReader) throws IOException {
		Vector<RequestClaim> requestClaimList = this.mainController.getContractInsuranceController()
				.getRequestClaimList();

		Vector<RequestClaim> standByClaims = new Vector<>();

		// ClaimStatus가 standByClaim인 객체들만 필터링
		for (RequestClaim claim : requestClaimList) {
			if (claim.getClaimStatus() == ClaimStatus.standByClaim) {
				standByClaims.add(claim);
			}
		}

		// standByClaim 상태인 청구 목록 출력
		if (standByClaims.isEmpty()) {
			System.out.println("대기 중인 손해조사 청구가 없습니다.");
		} else {
			for (int i = 0; i < standByClaims.size(); i++) {
				System.out.println((i + 1) + ": [accidentDate: " + standByClaims.get(i).getAccidentDate() + "]");
			}

			System.out.println("어떤 청구를 확인하시겠습니까? (ex 1, 2, 3):");
			int selectedIndex = MainTui.getInputInteger(objReader, standByClaims.size());

			RequestClaim selectedClaim = standByClaims.get(selectedIndex - 1);
			ContractInsurance associatedContractInsurance = this.mainController.getContractInsuranceController()
					.getContractInsuranceByRequestClaim(selectedClaim);
			System.out.println("선택된 청구의 상세 정보");
			System.out.println("---------------------------");
			System.out.println("accidentDate: " + selectedClaim.getAccidentDate());
			System.out.println("causer: " + selectedClaim.getCauser());
			System.out.println("place: " + selectedClaim.getPlace());
			System.out.println("detail: " + selectedClaim.getDetail());
			System.out.println("name: " + selectedClaim.getName());
			System.out.println("phoneNumber: " + selectedClaim.getPhoneNumber());
			System.out.println("address: " + selectedClaim.getAddress());
			System.out.println("damageAmount: " + selectedClaim.getDamageAmount());
			System.out.println("claimStatus: " + selectedClaim.getClaimStatus().getName());
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
			System.out.println("납입상태 : " + associatedContractInsurance.getPaymentStatus().getTitle());
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");

			System.out.println();
			System.out.println("산정 보상액을 입력하시겠습니까?  (yes/no):");
			if (!MainTui.getBoolean(objReader)) {
				System.out.println("업무 처리를 거부했습니다. 초기 화면으로 돌아갑니다.");
				return;
			}

			System.out.println("산정 보상액 입력: ");
			int compensationAmount = MainTui.getInputInteger(objReader, Integer.MAX_VALUE);
			selectedClaim.setCompensationAmount(compensationAmount);
			System.out.println("보상액 선정이 완료되었습니다.");
			selectedClaim.setClaimStatus(ClaimStatus.standByInsuranceProvide);
		}
	}

//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ보험금 지급 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public void printCompensationPayment(BufferedReader objReader) throws IOException {
		Vector<RequestClaim> requestClaimList = this.mainController.getContractInsuranceController()
				.getRequestClaimList();
		Vector<RequestClaim> compensationClaims = new Vector<>();

		for (RequestClaim claim : requestClaimList) {
			if (claim.getClaimStatus() == ClaimStatus.standByInsuranceProvide) {
				compensationClaims.add(claim);
			}
		}

		if (compensationClaims.isEmpty()) {
			System.out.println("지급 대기 중인 보험금 청구가 없습니다.");
		} else {
			for (int i = 0; i < compensationClaims.size(); i++) {
				System.out.println((i + 1) + ": [accidentDate: " + compensationClaims.get(i).getAccidentDate() + "]");
			}

			System.out.println("보험금 지급을 실행하실 항목을 선택해주세요. (ex 1, 2, 3):");
			int selectedIndex = MainTui.getInputInteger(objReader, compensationClaims.size());

			RequestClaim selectedClaim = compensationClaims.get(selectedIndex - 1);
			ContractInsurance associatedContractInsurance = this.mainController.getContractInsuranceController()
					.getContractInsuranceByRequestClaim(selectedClaim);
			System.out.println("선택된 청구의 상세 정보");
			System.out.println("---------------------------");
			System.out.println("보험 가입일 : " + associatedContractInsurance.getContractDate());
			System.out.println("보험료 납입 계좌 : " + associatedContractInsurance.getPaymentBankAccount());
			System.out.println("보상액 : " + selectedClaim.getCompensationAmount());
			System.out.println("---------------------------");
			System.out.println("산정 보상액을 입력하시겠습니까?  (yes/no):");
			if (!MainTui.getBoolean(objReader)) {
				System.out.println("업무 처리를 거부했습니다. 초기 화면으로 돌아갑니다.");
				return;
			} else {
				System.out.println("최종 보험금이 지급되었습니다.");
				selectedClaim.setClaimStatus(ClaimStatus.insuranceProvideComplete);
			}

		}

	}
}
