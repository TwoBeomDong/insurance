package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import controller.MainController;
import model.claim.RequestClaim;
import model.claim.info.ClaimStatus;
import model.contract.ContractInsurance;
import model.user.Customer;
import terminator.Terminator;
import visitor.claimInsurance.AssessDamageVisitor;
import visitor.claimInsurance.ClaimApprovalVisitor;
import visitor.claimInsurance.ClaimInsuranceVisitor;
import visitor.claimInsurance.InsuranceMoneyPaymentVisitor;
import visitor.contractInsurance.ChangePaymentTypeVisitor;
import visitor.contractInsurance.ContractInsuranceVisitor;
import visitor.contractInsurance.EarlyTerminateVisitor;
import visitor.contractInsurance.InsuranceClaimVisitor;
import visitor.contractInsurance.PayPremiumVisitor;

public class ContractInsuranceTui {

    private MainController mainController;

    public void associate(MainController mainController) {
        this.mainController = mainController;
    }

    public void printContractInsurance(BufferedReader objReader, Customer customer) throws IOException {
        Vector<ContractInsurance> list = this.mainController.getContractInsuranceController()
                .getSelectedCustomer(customer);

        if (list == null || list.size() == 0) {
            System.out.println("현재 가입중이신 보험 상품이 없습니다.");
            return;
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i).getInsuranceProduct().getBasicInsuranceInfo().getName());
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

		Terminator terminator = this.mainController.getTerminator();
		switch(processIndex) {
		case 1:	// 보험료 납부방식 변경 요청
			visitor = new ChangePaymentTypeVisitor();
			break;
		case 2: // 보험료 납부
			visitor = new PayPremiumVisitor();
			break;
		case 3:	// 보험 중도해지
			visitor = new EarlyTerminateVisitor(terminator);
			break;
		case 4: // 보험금 청구
			visitor = new InsuranceClaimVisitor();
			break;
		}
		visitor.visitContractInsurance(selectedInsurance, objReader);
	}


	public void printClaimInsurance(BufferedReader objReader) throws IOException {
		Vector<RequestClaim> requestClaimList = this.mainController.getContractInsuranceController()
				.getRequestClaimList();
		for (int i = 0; i < requestClaimList.size(); i++) {
			System.out.println("********** "+(i + 1)+" **********");
			System.out.println("청구인 : " + requestClaimList.get(i).getName());
			System.out.println("사고일 : " + requestClaimList.get(i).getAccidentDate());
			System.out.println("처리상태 : " + requestClaimList.get(i).getClaimStatus().getName());
		}
		System.out.println("***********************");

		System.out.println("처리할 청구 번호를 입력하세요. (ex 1, 2, 3):");
		int selectedIndex = MainTui.getInputInteger(objReader, requestClaimList.size());
		
		RequestClaim selectedClaim = requestClaimList.get(selectedIndex-1);
		
		String processStr = ClaimStatus.getClaimProcessList(selectedClaim.getClaimStatus());
		if (processStr == null) {
			System.out.println("현재 해당 보험에 대한 처리 가능 목록이 없습니다. 초기 화면으로 돌아갑니다.");
			return;
		}
		System.out.println("처리 가능 업무: " + processStr);
		System.out.print("해당 업무를 처리하시겠습니까? (yes / no) : ");
		if (!MainTui.getBoolean(objReader)) {
			System.out.println("업무 처리를 거부했습니다. 초기 화면으로 돌아갑니다.");
			return;
		}
		
		ClaimInsuranceVisitor claimInsuranceVisitor = null;
		switch(selectedClaim.getClaimStatus()){
		case requestClaim:	// 손해조사 
			claimInsuranceVisitor = new ClaimApprovalVisitor();
			break;
		case standByClaim: // 손해사정 
			claimInsuranceVisitor = new AssessDamageVisitor();
			break;
		case standByInsuranceProvide: // 보험금 지급
			claimInsuranceVisitor = new InsuranceMoneyPaymentVisitor();
			break;
		default:
			break;
		}
		claimInsuranceVisitor.visitContractInsurance(selectedClaim, objReader);
	}
}
