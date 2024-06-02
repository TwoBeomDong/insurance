package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Vector;

import controller.MainController;
import freshInsuranceVisitor.ApprovalVisitor;
import model.contract.ContractInsurance.PaymentType;
import model.insurance.InsuranceProduct;
import model.insurance.info.InsuranceType;
import model.insurance.info.TermPeriod;
import model.user.Customer;

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
	
	// --------------------------사용자 입력 정형화---------------------------------------
	public static int getInputInteger(BufferedReader objReader, int scope) throws IOException {
		int retVal;
		while (true) {
			try {
				retVal = Integer.parseInt(objReader.readLine().trim());
				if (scope != SCOPE_NONE) {
					if (0 >= retVal || scope < retVal) {
						System.out.print("올바르지 않은 입력입니다. 다시 입력하세요: ");
						continue;
					}
				}
				return retVal;
			} catch (NumberFormatException e) {
				System.out.print("올바르지 않은 입력입니다. 다시 입력하세요: ");
			}
		}
	}
	public static boolean getBoolean(BufferedReader objReader) throws IOException {
        String approvalInput;
        while(true) {
        	approvalInput = objReader.readLine();
            if ("yes".equalsIgnoreCase(approvalInput)) {
                return true;
            }else if("no".equalsIgnoreCase(approvalInput)) {

                return false;
            }else {
            	System.out.print("올바르지 않은 입력입니다. 다시 입력하세요 : ");
            }
        }
	}

	// --------------------------addNewInsurance---------------------------------------

	public void printAddNewInsurance(BufferedReader objReader, MainController mainController) throws IOException {

		System.out.println("-----신규 보험 등록-----");
		System.out.print("보험 이름: ");
		String insuranceName = objReader.readLine().trim();
		// ----- 보험 타입 -----
		System.out.println("보험 타입을 선택해 주십시오");
		for (InsuranceType type : InsuranceType.values()) {
			System.out.println((type.ordinal() + 1) + ". " + type.name());
		}
		System.out.print("입력 (예: 1, 2, 3): ");
		int typeInput = getInputInteger(objReader, InsuranceType.values().length) - 1;

		// 입력된 값으로 InsuranceType 객체 생성
		// exception 처리 보강 예정
		InsuranceType selectedType = InsuranceType.values()[typeInput];
		System.out.println("선택된 보험 타입: " + selectedType.getName());
		// ---------------

		// ----- 보험 기간 -----
		System.out.println("보험 기간을 선택해 주십시오");
		for (TermPeriod termPeriod : TermPeriod.values()) {
			System.out.println((termPeriod.ordinal() + 1) + ". " + termPeriod.name());
		}
		System.out.print("입력 (예: 1, 2, 3): ");
		int termInput = getInputInteger(objReader, TermPeriod.values().length) - 1;

		// exception 처리 보강 예정
		TermPeriod selectedTerm = TermPeriod.values()[termInput];
		System.out.println("선택된 보험 기간: " + selectedTerm.getName());
		// ---------------

		// ----- 기초서류양식 -----
		LinkedHashMap<String, Object> BasicPaperList = new LinkedHashMap<>();
		String input;

		while (true) {
			System.out.println("필요 서류 이름을 입력해 주십시오. 종료하려면 'exit'를 입력하십시오.");
			input = objReader.readLine();
			if ("exit".equals(input)) {
				break;
			}

			System.out.println("서류 타입을 선택해 주십시오.\n1: 문자열 타입\n2: 논리 타입");
			int type = getInputInteger(objReader, 2);
			if(type == 1) {
				BasicPaperList.put(input, String.class);
			}else {
				BasicPaperList.put(input, Boolean.class);
			}
		}

		if (BasicPaperList.isEmpty()) {
			System.out.println("맵이 비어 있습니다.");
		} else {
			System.out.println("입력된 서류이름-타입 쌍:");
			BasicPaperList.forEach((key, value) -> System.out.println(key + ": " + value));
		}
		// ---------------
		System.out.println();
		System.out.println("-----확인-----");
		System.out.println("보험 이름: " + insuranceName);
		System.out.println("선택된 보험 타입: " + selectedType);
		System.out.println("선택된 보험 기간: " + selectedTerm);
		BasicPaperList.forEach((key, value) -> System.out.println(key + ": " + (value == String.class ? "문자열 타입" : "논리 타입")));
		System.out.println();

		if (mainController.getInsuranceProductController().addNewInsurance(insuranceName, selectedType,
				selectedTerm, BasicPaperList)) {
			System.out.println("새 보험 등록 완료");
		} else {
			System.out.println("새 보험 등록 실패");
		}

	}

	// -------------------------END-----------------------------------
	
	
	// -------------------------approvalNewInsurance----------------------------------
	public void printApprovalNewInsurance(BufferedReader objReader) throws IOException {
		/*
		 * 신규 보험 승인부
		 * 
		 * visitor pattern 사용으로 해당 메소드에서는 visitor를 호출만 한다. vistitor 는 View의 일종으로 사용.
		 */
		ApprovalVisitor visitor = new ApprovalVisitor();
		visitor.visitInsuranceApprovalProcess(this.mainController.getInsuranceProductController(), objReader);

	}
	
	// -------------------------보험 가입부----------------------------------
	public void registerInsurance(BufferedReader objReader) throws IOException {
		Vector<InsuranceProduct> registerInsuranceList = this.mainController.getContractInsuranceController().getRegisteredInsuranceList(currentCustomer);		
		
		Vector<InsuranceProduct> newInsurance = new Vector<>();
		while(true) {
			System.out.println("가입할 보험 종류를 선택하십시오:");
			for(int i=0; i<InsuranceType.values().length; i++) {
				System.out.println(i+1+" : "+InsuranceType.values()[i].getName());
			}
			InsuranceType type = InsuranceType.values()[getInputInteger(objReader, InsuranceType.values().length)-1];
	        
			// 여기서 보험사에 있는 보험에서 현재 가입한 보험을 뺀 리스트를 만든다.
			for(InsuranceProduct regular : this.mainController.getInsuranceProductController().getTypeRegularInsuranceList(type)) {
				boolean isInclude = false;
				for(InsuranceProduct registered : registerInsuranceList)
					if(registered.equals(regular.getID())) isInclude = true;
				if(!isInclude) newInsurance.add(regular);
			}
			if(newInsurance.size() == 0) {
				System.out.println("현재 가입할 수 있는 해당 종류의 보험이 없습니다.");
			}else {
				break;
			}
		}
		
		System.out.println("가입할 보험을 선택하십시오:");
		System.out.println(this.mainController.getInsuranceProductController().getInsuranceString(newInsurance));
		InsuranceProduct selectInsurance;
		do {
			selectInsurance  = this.mainController.getInsuranceProductController().getRegularInsurance
					(getInputInteger(objReader, SCOPE_NONE));
			if(selectInsurance == null) System.out.println("올바르지 않은 입력입니다. 다시 입력하세요: ");
		}while(selectInsurance == null);
		System.out.println(selectInsurance.getBasicInsuranceInfo().toString());
		
		System.out.println("보험 가입을 진행하시겠습니까? (yes/no):");
		boolean continueRegister = getBoolean(objReader);
		
		if(!continueRegister) {
			System.out.println("보험 가입을 거부하여 초기 화면으로 돌아갑니다.");
			return;
		}
		
		System.out.println("가입자 정보를 입력해주세요");
		LinkedHashMap<String,Object> map = selectInsurance.getMemberPaperForm().getBasicPaperList();
		for (Entry<String, Object> entry : map.entrySet()) {
            String paper = entry.getKey();
            Object type = entry.getValue();
            if(type == Boolean.class) {
            	System.out.print(paper+": ");
            	objReader.readLine();			//저장은 나중에 고려
            }else if(type == String.class) {
            	System.out.println(paper+" (yes/no): ");
            	getBoolean(objReader);
            }
		}
		System.out.println("예상 보험료\t: "+10000+"원");		//임시로 예상 보험료 지정해둠
		System.out.println("해당 보험에 가입하시겠습니까?  (yes/no):");
		boolean isRegister = getBoolean(objReader);
		if(!isRegister) {
			System.out.println("보험 가입을 거부하여 초기 화면으로 돌아갑니다.");
			return;
		}
		
		boolean isPeriod = false;
		int quarter = 0;
		while(!isPeriod) {
			System.out.println("보험 가입 기간 분기를 입력해주세요. (단위 x 분기만큼 기간이 설정됩니다..)");
			quarter = getInputInteger(objReader, SCOPE_NONE) * selectInsurance.getBasicInsuranceInfo().getTermPeriod().getTerm();
			System.out.println("가입하시려는 기간이 "+quarter+"개월이 맞으십니까? (yes / no)");
			isPeriod = getBoolean(objReader);
		}
		
		System.out.println("보험금 납부 및 지급받을 계좌를 입력해주세요.");
		Vector<String> accountList = currentCustomer.getPaymentBankAccount();
		for(int i=0; i<accountList.size(); i++) {
			System.out.println(i+1+": "+accountList.get(i));
		}
		String selectedAccount = accountList.get(getInputInteger(objReader, accountList.size())-1);
		
		System.out.println("보험금 납부 방식을 선택하세요.");
		for(int i=0; i<PaymentType.values().length; i++) {
			System.out.println(i+1+" :"+PaymentType.values()[i].getTitle());	
		}
		PaymentType paymentType = PaymentType.values()[getInputInteger(objReader, PaymentType.values().length)];
		
		this.mainController.getContractInsuranceController().AddContractInsurance(selectInsurance, currentCustomer, quarter, selectedAccount, paymentType);
		
		System.out.println("보험 가입이 정상적으로 완료되었습니다.");
		System.out.println("납부계좌는 "+selectedAccount + ", 납부방식은 " +
		paymentType.getTitle()+" 방식을 선택하셨습니다.");
		System.out.println("[보험비 납부방식 변경] 메뉴에서 납부방식을 변경할 수 있습니다.");
		System.out.println("[사용자 정보 변경]메뉴에서 납부계좌를 변경하실 수 있습니다.");

	}
}
