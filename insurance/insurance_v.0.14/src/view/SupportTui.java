package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import controller.MainController;
import model.contract.ContractInsurance;
import model.user.Customer;

public class SupportTui {

	private MainController mainController;

	public void associate(MainController mainController) {
		this.mainController = mainController;
	}
	public void printSupprot(BufferedReader objReader, Customer customer) throws IOException {
		// 이 고객이 가입한 contractinsurance "list" 를 요청 --> List 형태 
		Vector<ContractInsurance> list = this.mainController.getContractInsuranceController().getSelectedCustomer(customer);
		
		// 없으면 가입보험 없음
		if(list == null) {
			System.out.println("현재 가입중이신 보험 상품이 없습니다.");
		}
		else {
			for(int i=0; i<list.size();i++ ) {
				System.out.println((i+1)+". 1"+list.get(i).getInsuranceProduct().getBasicInsuranceInfo().getName());
			}
		}
		// 있으면 가입 보험 리스트 출력
		System.out.println("청구하실 보험을 선택해주세요.");
		int select = Integer.parseInt(objReader.readLine().trim());
		
		ContractInsurance selectedInsurance = list.get(select-1);
		
		System.out.println("청구를 위한 사고 경위서를 양식에 맞게 작성해주세요.");
		System.out.println("1. 사고 일시를 입력하세요.  예: 240602");
		String accidentDate = objReader.readLine().trim();
		
		System.out.println("2. 사고 원인을 입력하세요.  예: 전방 부주의");
		String causer = objReader.readLine().trim();
		
		System.out.println("3. 사고 장소를 입력하세요.  예: 명지대학교 정문");
		String place = objReader.readLine().trim();
		
		System.out.println("4. 사고 내용을 입력해주세요  예: 호흡 곤란으로 인한 기절");
		String detail = objReader.readLine().trim();
		
		System.out.println("5. 피해자 성명을 입력해주세요.  예: 홍길동");
		String name = objReader.readLine().trim();
		
		System.out.println("피해자 연락처를 입력해주세요.  예: 010-6662-3518");
		String phoneNumber = objReader.readLine().trim();
		
		System.out.println("피해자의 거주지를 입력해 주세요  예: 남가좌동 95-54");
		String address = objReader.readLine().trim();
		
		System.out.println("피해 내역을 입력해주세요.  예: 35만원");
		String damageAmount = objReader.readLine().trim();
		System.out.println();
		
		if(mainController.getRequestSupportController().addRequestSupport(accidentDate,causer,place,detail,name,phoneNumber,
				address, damageAmount, customer, selectedInsurance)) {
			System.out.println("보험 청구가 완료되었습니다.");
			
		}
		
		
		// 번호 입력해서 번호에 맞는 보험 가져옴 "보험 열" 하나. 그냥 갖고 있어봐
		// 경위서 : 사고일시, 사고원인, 사고장소, 사고내용, 피해자 성명, 피해자 연락처 ,피해자 주소, 피해내역/품목을 입력한다.
		// 다 입력한 값을 
		
		
	}

}
