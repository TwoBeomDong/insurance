package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.MainController;
import model.user.Customer;
import model.user.User.eSex;

public class MainTui {
	public static final int SCOPE_NONE = -2;
	// login
	Customer customer;

	// component
	private MainController mainController;

	// views
		private InsuranceTui insuranceTui = new InsuranceTui();
		private ContractInsuranceTui contractInsuranceTui = new ContractInsuranceTui();
		private TerminateTui terminateTui = new TerminateTui();

		public void associate(MainController mainController) {
			this.mainController = mainController;
			this.insuranceTui.associate(this.mainController);
			this.contractInsuranceTui.associate(this.mainController);
			this.terminateTui.associate(this.mainController);
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

	
	// ---------------------Login Logic-----------------------------------

	public void displayLogin() throws IOException {
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		// login & register
		System.out.println("환영합니다.");
		System.out.println("저희 보험사 회원이신가요? (yes / no)");
		if(MainTui.getBoolean(objReader)) {
			this.customer = null;
			do {
				this.customer = printLogin(objReader);
				if(this.customer == null) System.out.println("입력하신 아이디/비밀번호가 잘못되었습니다.");
			} while (customer == null);
			this.insuranceTui.login(customer);
			System.out.println("로그인 성공");
			this.displayMain(objReader);
		}else {
			printRegisterMenu(objReader);
		}
	}

	private Customer printLogin(BufferedReader objReader) throws IOException {
		System.out.print("아이디를 입력하세요: ");
		String id = objReader.readLine().trim();
		System.out.print("비밀번호를 입력하세요: ");
		String password = objReader.readLine().trim();
		if (this.mainController.getCustomerController().checkPassword(id, password)) {
			System.out.println("enter");
			return this.mainController.getCustomerController().getCustomer(id);
		}
		return null;
	}

	private void printRegisterMenu(BufferedReader objReader) throws IOException {
		System.out.println("회원가입을 진행합니다.");
		System.out.println("이름을 입력하세요: ");
		String name = objReader.readLine().trim();

		System.out.println("나이를 입력하세요. 예: (25)");
		int age = MainTui.getInputInteger(objReader, 150);

		System.out.println("성별을 선택하세요.");
		for(int i = 0; i< eSex.values().length; i++) {
			System.out.println(i+1+" : "+eSex.values()[i].getTitle());
		}
		eSex sex = eSex.values()[MainTui.getInputInteger(objReader, eSex.values().length)-1];

		System.out.println("계좌 정보를 입력하세요. 예: 123456");
		String paymentBankAccount = objReader.readLine().trim();

		System.out.println("아이디를 입력하세요: ");
		String id = objReader.readLine().trim();
		while(mainController.getCustomerController().getCustomer(id)!=null) {
			System.out.println("이미 사용중인 아이디입니다. 다른 아이디를 입력하세요.");
			id = objReader.readLine().trim();
		}
			
		System.out.println("비밀번호를 입력하세요: ");
		String pw = objReader.readLine().trim();

		System.out.println();
		System.out.println("-----확인-----");
		System.out.println("가입자 이름: " + name);
		System.out.println("가입자 나이: " + age);
		System.out.println("가입자 성별: " + sex);
		System.out.println("가입자 계좌정보: " + paymentBankAccount);
		System.out.println("가입자 id: " + id);
		System.out.println("가입자 pw: " + pw);
		System.out.println();
		System.out.println("사용 가능한 ID와 비밀번호 입니다. 해당 정보로 회원가입 하시겠습니까?");
		System.out.println("1. 예");
		String signUP = objReader.readLine().trim();

		if (mainController.getCustomerController().addCustomer(name, age, sex, paymentBankAccount, id, pw)) {
			System.out.println("회원가입이 완료되었습니다.");
			displayLogin();
		} else {
			System.out.println("회원가입 실패");
		}
	}

	// ---------------------LoginLogicEND----------------------------------------------

	// ----------------------------StartInsuranceSystem------------------------------------

	public void displayMain(BufferedReader objReader) throws IOException {
		// print menu
		printMenu();
		System.out.print("menu를 선택하세요. (종료하려면 x를 입력합니다.): ");
		String sChoice = objReader.readLine().trim();

		int failNum = 0;
		while (!sChoice.equals("x")) {
			switch (sChoice) {
			case "1":
				insuranceTui.printAddNewInsurance(objReader, this.mainController);
				break;
			case "2":
				insuranceTui.printApprovalNewInsurance(objReader);
				break;
			case "3":
				insuranceTui.registerInsurance(objReader);
				break;
			case "4":
				contractInsuranceTui.printContractInsurance(objReader, customer);
				break;
			case "5":
				terminateTui.terminateInsurance(objReader);
				break;
			case "6":
				contractInsuranceTui.printClaimInsurance(objReader);
				break;
			case "menu":
				this.printMenu();
			default:
				failNum++;
				break;
			}
			if (failNum > 3) {
				System.out.println("메뉴를 다시 보고싶다면 menu를 입력하세요.");
				failNum = 0;
			}
			System.out.print("menu를 선택하세요. (종료하려면 x를, 메뉴를 다시 보고싶으면 menu를 입력합니다.): ");
			sChoice = objReader.readLine().trim();
		}
		System.out.println("보험사 시스템을 종료합니다.");
	}

	private void printMenu() {
		System.out.println("***********************MENU***********************");
		System.out.println("-----상품 개발 부서-----");
		System.out.println("1. 신규 보험 등록");
		System.out.println("-----상품 개발 부서 관리자-----");
		System.out.println("2. 신규 보험 목록");
		System.out.println("-----고객-----");
		System.out.println("3. 신규 보험 가입");
		System.out.println("4. 가입중인 보험 확인");
		System.out.println("-----계약 관리 부서 관리자-----");
		System.out.println("5. 해지 보험 목록");
		System.out.println("ㅡㅡㅡㅡㅡ보상운용부서ㅡㅡㅡㅡㅡ");
		System.out.println("6. 보험금 청구목록 확인");
	}

}
