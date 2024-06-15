package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import contractInsuranceVisitor.ChangePaymentTypeVisitor;
import contractInsuranceVisitor.ContractInsuranceVisitor;
import contractInsuranceVisitor.EarlyTerminateVisitor;
import contractInsuranceVisitor.InsuranceClaimVisitor;
import contractInsuranceVisitor.PayPremiumVisitor;
import controller.MainController;
import model.contract.ContractInsurance;
import terminator.Terminator;
import model.user.Customer;

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
		System.out.println("보험명 : "+selectedInsurance.getInsuranceProduct().getBasicInsuranceInfo().getName());
		System.out.println("보험 가입일 : "+selectedInsurance.getContractDate());
		System.out.println("만기 예정일 : "+selectedInsurance.getExpireDate());
		System.out.println("보험료 납입계좌 : "+selectedInsurance.getPaymentBankAccount());
		System.out.println("현재 납입방식 : "+selectedInsurance.getPaymentType().getTitle());
		System.out.println("현재 보험료 : "+selectedInsurance.getMoney());
		System.out.println("다음 납입일 : "+selectedInsurance.getPaymentDate());
		System.out.println("납입상태 : "+selectedInsurance.getPaymentStatus().getTitle());
		System.out.println("*******************************");
		
		Vector<String> processList = this.mainController.getContractInsuranceController()
				.getContractInsuranceProcessList(selectedInsurance);
		if (processList == null) {
			System.out.println("현재 해당 보험에 대한 처리 가능 목록이 없습니다. 초기 화면으로 돌아갑니다.");
			return;
		}
		for(int i=0; i<processList.size(); i++) {
			System.out.println((i+1)+": "+processList.get(i));
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
		case 2:	// 보험료 납부
			visitor = new PayPremiumVisitor();
			break;
		case 3:	// 보험 중도해지
			visitor = new EarlyTerminateVisitor(terminator);
			break;
		case 4:	// 보험금 청구
			visitor = new InsuranceClaimVisitor();
			break;
		}
		visitor.visitContractInsurance(selectedInsurance, objReader);
	}
}
