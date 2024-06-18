package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Vector;

import controller.MainController;
import model.insurance.InsuranceProduct;
import model.insurance.info.InsuranceStatus;
import model.insurance.info.InsuranceType;
import model.insurance.info.TermPeriod;
import model.insurancePremium.PaymentType;
import model.terminate.TerminateInsurance;
import model.user.Customer;
import visitor.freshInsurance.AdminApprovalVisitor;
import visitor.freshInsurance.FreshInsuranceVisitor;
import visitor.freshInsurance.TrainApprovalVisitor;
import visitor.freshInsurance.TrainFinishVisitor;
import visitor.freshInsurance.TrainRequestVisitor;

public class InsuranceTui {
	// attribute
	public static final int RE_PRINT_MENU_COUNT = 1;
	public static final int SCOPE_NONE = -2;
	private Customer currentCustomer;

	// component
	private MainController mainController;

	public void associate(MainController mainController) {
		this.mainController = mainController;
	}

	public void login(Customer user) {
		this.currentCustomer = user;
	}

	// --------------------------addNewInsurance---------------------------------------

	public void printAddNewInsurance(BufferedReader objReader, MainController mainController) throws IOException {

		System.out.println("-----기본 보험정보 입력-----");
		System.out.print("보험 이름: ");
		String insuranceName = objReader.readLine().trim();
		// ----- 보험 타입 -----
		System.out.println("보험 타입을 선택해 주십시오");
		for (InsuranceType type : InsuranceType.values()) {
			System.out.println((type.ordinal() + 1) + ". " + type.getName());
		}
		System.out.print("입력 (예: 1, 2, 3): ");
		int typeInput = MainTui.getInputInteger(objReader, InsuranceType.values().length) - 1;

		// 입력된 값으로 InsuranceType 객체 생성
		// exception 처리 보강 예정
		InsuranceType selectedType = InsuranceType.values()[typeInput];
		System.out.println("선택된 보험 타입: " + selectedType.getName());
		// ---------------

		// ----- 보험 기간 -----
		System.out.println("보험 기간 단위를 선택해 주십시오");
		for (TermPeriod termPeriod : TermPeriod.values()) {
			System.out.println((termPeriod.ordinal() + 1) + ". " + termPeriod.getName());
		}
		System.out.print("입력 (예: 1, 2, 3): ");
		int termInput = MainTui.getInputInteger(objReader, TermPeriod.values().length) - 1;

		// exception 처리 보강 예정
		TermPeriod selectedTerm = TermPeriod.values()[termInput];
		System.out.println("선택된 보험 기간: " + selectedTerm.getName());
		// ---------------

		// ----- 기초서류양식 -----
		System.out.println("-----가입자 기초서류양식 입력-----");
		LinkedHashMap<String, Object> BasicPaperList = new LinkedHashMap<>();
		String input;

		while (true) {
			System.out.println("필요 서류 이름을 입력해 주십시오. 종료하려면 'exit'를 입력하십시오.");
			input = objReader.readLine();
			if ("exit".equals(input)) {
				break;
			}

			System.out.println("서류 타입을 선택해 주십시오.\n1: 문자열 타입\n2: 논리 타입");
			int type = MainTui.getInputInteger(objReader, 2);
			if (type == 1) {
				BasicPaperList.put(input, String.class);
			} else {
				BasicPaperList.put(input, Boolean.class);
			}
		}

		if (BasicPaperList.isEmpty()) {
			System.out.println("기초서류를 입력하지 않았습니다.");
		} else {
			System.out.println("입력된 서류이름-타입 쌍:");
			BasicPaperList.forEach(
					(key, value) -> System.out.println(key + ": " + (value == String.class ? "문자열 타입" : "논리 타입")));
		}
		// ---------------
		System.out.println();
		System.out.println("-----확인-----");
		System.out.println("보험 이름: " + insuranceName);
		System.out.println("선택된 보험 타입: " + selectedType);
		System.out.println("선택된 보험 기간: " + selectedTerm);
		BasicPaperList
				.forEach((key, value) -> System.out.println(key + ": " + (value == String.class ? "문자열 타입" : "논리 타입")));
		System.out.println();

		if (mainController.getInsuranceProductController().addFreshInsuranceProduct(insuranceName, selectedType,
				selectedTerm, BasicPaperList, null)) {
			System.out.println("보험 등록이 정상적으로 요청되었습니다. 관리자 승인 이후 금융감독원으로 인계됩니다.");
		} else {
			System.out.println("보험 등록 요청이 거부되었습니다. 입력 서류를 재검토하거나 잠시 후 시도해주세요.");
		}

	}

	// -------------------------END-----------------------------------

	// -------------------------approvalNewInsurance----------------------------------
	public void printApprovalNewInsurance(BufferedReader objReader) throws IOException {
		/*
		 * 신규 보험 승인부
		 * 
		 * 사용자가 선택한 메뉴에 대한 일을 처리할 비지터 호출
		 */
		// 신규보험 목록 출력
		String insuranceString = this.mainController.getInsuranceProductController().getFreshInsuranceString();
		if (insuranceString.equals("")) {
			System.out.println("신규 보험 목록이 비어있습니다.");
			return;
		}
		System.out.println(insuranceString);
		System.out.println("처리할 신규 보험 번호를 입력하여 주십시오.");
		System.out.print("입력 : ");
		// 신규 보험 개수도 알아야 한다.
		InsuranceProduct selectedInsurance = null;
		while (selectedInsurance == null) {
			int selectedInsuranceID = MainTui.getInputInteger(objReader, InsuranceTui.SCOPE_NONE);
			selectedInsurance = this.mainController.getInsuranceProductController()
					.getFreshInsurance(selectedInsuranceID);
			if (selectedInsurance == null)
				System.out.print("올바르지 않은 입력입니다. 다시 입력하세요: ");
		}

		// 특정 보험에서 처리할 수 있는 일 리스트 출력
		// ex_보험 관리자 승인 / 보험 교육 승인

		String processStr = InsuranceStatus.getFreshInsuranceProcessList(selectedInsurance.getCurrentStatus());
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

		// 각 업무에 알맞은 visitor 할당
		FreshInsuranceVisitor visitor = null;
		switch (selectedInsurance.getCurrentStatus()) {
		case FSSApproval: // 금융감독원 승인
			visitor = new TrainRequestVisitor();
			break;
		case FSSDeny: // 금융감독원 거부
//			retString += "보험 승인 재검토";
			break;
		case adminApprovalWait: // 관리자 승인 대기
			visitor = new AdminApprovalVisitor();
			break;
		case adminDeny: // 관리자 거부
//			retString += "거부사유 확인";
			break;
		case trainRequest: // 교육 의뢰
			visitor = new TrainApprovalVisitor();
			break;
		case trainWait: // 교육 대기
			visitor = new TrainApprovalVisitor();
			break;
		case trainProgress: // 교육 진행
			visitor = new TrainFinishVisitor();
			break;
		default:
			System.out.println("해당 보험에 대해 처리할 수 있는 일이 없습니다. 초기 화면으로 돌아갑니다.");
			return;
		}
		visitor.visitFreshInsurance(this.currentCustomer, selectedInsurance,
				this.mainController.getInsuranceProductController(), objReader);
	}

	// -------------------------보험 가입부----------------------------------
	public void registerInsurance(BufferedReader objReader) throws IOException {
		Vector<InsuranceProduct> registerInsuranceList = this.mainController.getContractInsuranceController()
				.getRegisteredInsuranceList(currentCustomer);

		Vector<InsuranceProduct> newInsurance = new Vector<>();
		while (true) {
			System.out.println("가입할 보험 종류를 선택하십시오:");
			for (int i = 0; i < InsuranceType.values().length; i++) {
				System.out.println(i + 1 + " : " + InsuranceType.values()[i].getName());
			}
			InsuranceType type = InsuranceType
					.values()[MainTui.getInputInteger(objReader, InsuranceType.values().length) - 1];

			// 여기서 보험사에 있는 보험에서 현재 가입한 보험을 뺀 리스트를 만든다.
			for (InsuranceProduct regular : this.mainController.getInsuranceProductController()
					.getTypeRegularInsuranceList(type)) {
				boolean isInclude = false;
				for (InsuranceProduct registered : registerInsuranceList)
					if (registered.equals(regular.getID()))
						isInclude = true;
				if (!isInclude)
					newInsurance.add(regular);
			}
			if (newInsurance.size() == 0) {
				System.out.println("현재 가입할 수 있는 해당 종류의 보험이 없습니다.");
			} else {
				break;
			}
		}

		System.out.println("가입할 보험을 선택하십시오:");
		System.out.println(this.mainController.getInsuranceProductController().getInsuranceString(newInsurance));
		InsuranceProduct selectInsurance;
		do {
			selectInsurance = this.mainController.getInsuranceProductController()
					.getRegularInsurance(MainTui.getInputInteger(objReader, SCOPE_NONE));
			if (selectInsurance == null)
				System.out.println("올바르지 않은 입력입니다. 다시 입력하세요: ");
		} while (selectInsurance == null);
		System.out.println(selectInsurance.getBasicInsuranceInfo().toString());

		System.out.println("보험 가입을 진행하시겠습니까? (yes/no):");
		boolean continueRegister = MainTui.getBoolean(objReader);

		if (!continueRegister) {
			System.out.println("보험 가입을 거부하여 초기 화면으로 돌아갑니다.");
			return;
		}

		System.out.println("가입자 기초 서류 정보를 입력해주세요");
		LinkedHashMap<String, Object> map = selectInsurance.getMemberPaperForm().getBasicPaperList();
		for (Entry<String, Object> entry : map.entrySet()) {
			String paper = entry.getKey();
			Object type = entry.getValue();
			if (type == Boolean.class) {
				System.out.println(paper + " (yes/no): ");
				MainTui.getBoolean(objReader); // 저장은 나중에 고려
			} else if (type == String.class) {
				System.out.print(paper + ": ");
				objReader.readLine();
			}
		}
		System.out.println("예상 보험료\t: " + selectInsurance.getBasicInsuranceInfo().getType().getMoney() + "원");
		System.out.println("해당 보험에 가입하시겠습니까?  (yes/no):");
		boolean isRegister = MainTui.getBoolean(objReader);
		if (!isRegister) {
			System.out.println("보험 가입을 거부하여 초기 화면으로 돌아갑니다.");
			return;
		}

		boolean isPeriod = false;
		int quarter = 0;
		while (!isPeriod) {
			System.out.println("보험 가입 기간 분기를 입력해주세요. (단위 x 분기만큼 가입 기간이 설정됩니다.)");
			quarter = MainTui.getInputInteger(objReader, SCOPE_NONE)
					* selectInsurance.getBasicInsuranceInfo().getTermPeriod().getTerm();
			System.out.println("가입하시려는 기간이 " + quarter + "개월이 맞으십니까? (yes / no)");
			isPeriod = MainTui.getBoolean(objReader);
		}

		System.out.println("보험금 납부 및 지급받을 계좌를 선택해주세요.");
		Vector<String> accountList = currentCustomer.getPaymentBankAccount();
		for (int i = 0; i < accountList.size(); i++) {
			System.out.println(i + 1 + ": " + accountList.get(i));
		}
		String selectedAccount = accountList.get(MainTui.getInputInteger(objReader, accountList.size()) - 1);

		System.out.println("보험금 납부 방식을 선택하세요.");
		for (int i = 0; i < PaymentType.values().length; i++) {
			System.out.println(i + 1 + " :" + PaymentType.values()[i].getTitle());
		}
		PaymentType paymentType = PaymentType.values()[MainTui.getInputInteger(objReader, PaymentType.values().length)
				- 1];

		this.mainController.getContractInsuranceController().AddContractInsurance(selectInsurance, currentCustomer,
				quarter, selectedAccount, paymentType);

		System.out.println("보험 가입이 정상적으로 완료되었습니다.");
		System.out.println("납부계좌는 " + selectedAccount + ", 납부방식은 " + paymentType.getTitle() + " 방식을 선택하셨습니다.");
		System.out.println("[보험비 납부방식 변경] 메뉴에서 납부방식을 변경할 수 있습니다.");
		System.out.println("[사용자 정보 변경]메뉴에서 납부계좌를 변경하실 수 있습니다.");

	}


	// -------------------------보험 해지/환급(직원전용메뉴)----------------------------------
	public void terminateInsurance(BufferedReader objReader) {
		Vector<TerminateInsurance> terminateList = this.mainController.getMainControllerTerminateInsuranceList()
				.getTerminateInsuranceList();

		if (terminateList.isEmpty()) {
			System.out.println("해지된 보험이 없습니다.");
		} else {
			System.out.println("해지된 보험 목록:");
			for (TerminateInsurance insurance : terminateList) {
				System.out.println("보험 ID: " + insurance.getInsuranceId() + "\n고객 ID: " + insurance.getCustomerId()
						+ ", 해지 날짜: " + insurance.getTerminationDate());
			}
		}
	}


}
