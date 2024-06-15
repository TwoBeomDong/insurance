package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import contractInsuranceVisitor.ChangePaymentTypeVisitor;
import contractInsuranceVisitor.ContractInsuranceVisitor;
import contractInsuranceVisitor.InsuranceClaimVisitor;
import controller.MainController;
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
		switch(processIndex) {
		case 1:	// 보험료 납부방식 변경 요청
			visitor = new ChangePaymentTypeVisitor();
		case 2:	// 보험료 납부
			
		case 3:	// 보험 중도해지
			
		case 4:	// 보험금 청구
			visitor = new InsuranceClaimVisitor();
		}
		visitor.visitContractInsurance(selectedInsurance, objReader);
	}

}
