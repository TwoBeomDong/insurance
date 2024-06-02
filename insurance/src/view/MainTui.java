package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.MainController;
import model.user.Customer;

public class MainTui {
	public static final int SCOPE_NONE = -2;
	// login
	Customer customer;

	// component
	private MainController mainController;

	// views
	private InsuranceTui insuranceTui = new InsuranceTui();
	private ContractTui contractTui = new ContractTui();
	private SupportTui supportTui = new SupportTui();

	public void associate(MainController mainController) {
		this.mainController = mainController;
		this.insuranceTui.associate(this.mainController);
		this.supportTui.associate(this.mainController);
	}

	
	// ---------------------Login Logic-----------------------------------

	public void displayLogin() throws IOException {
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		// login & register
		printFirstloginMenu();
		String sLoginChoice = objReader.readLine().trim();
		if (sLoginChoice.equals("1")) {
			// login
			this.customer = null;
			do {
				this.customer = printLogin(objReader);
			} while (customer == null);
			System.out.println("로그인 성공");
			this.insuranceTui.login(customer);
			displayMain(objReader);
		} else if (sLoginChoice.equals("2")) {
			// register
			printRegisterMenu(objReader);
		}
	}

	private Customer printLogin(BufferedReader objReader) throws IOException {
		System.out.print("아이디를 입력하세요: ");
		String id = objReader.readLine().trim();
		System.out.print("비밀번호를 입력하세요: ");
		String password = objReader.readLine().trim();
		if (this.mainController.getCustomerController().checkPassword(id, password)) {
			return this.mainController.getCustomerController().getCustomer(id);
		}
		return null;
	}

	private void printFirstloginMenu() {
		System.out.println("환영합니다.");
		System.out.println("저희 보험사 회원이신가요?");
		System.out.println("1. 예 2. 아니오");
	}

	private void printRegisterMenu(BufferedReader objReader) throws IOException {
		System.out.println("이름을 입력하세요: ");
		String name = objReader.readLine().trim();

		System.out.println("나이를 입력하세요. 예: (25살)");
		String age = objReader.readLine().trim();

		System.out.println("성별을 입력하세요. 예: (남자)");
		String sex = objReader.readLine().trim();

		System.out.println("계좌 정보를 입력하세요. 예: 123456");
		String paymentBankAccount = objReader.readLine().trim();

		System.out.println("아이디를 입력하세요: ");
		String id = objReader.readLine().trim();
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
				supportTui.printSupprot(objReader, this.customer);
				break;
			case "5":
				insuranceTui.terminateInsurance(objReader);
			case "menu":
				this.printMenu();
			default:
				failNum++;
				break;
			}
			if (failNum > 5) {
				System.out.println("메뉴를 다시 보고싶다면 menu를 입력하세요.");
				failNum = 0;
			}
			System.out.print("menu를 선택하세요. (종료하려면 x를 입력합니다.): ");
			sChoice = objReader.readLine().trim();
		}
	}

	private void printMenu() {
		System.out.println("***********************MENU***********************");
		System.out.println("-----상품 개발 부서-----");
		System.out.println("1. 신규 보험 등록");
		System.out.println("-----상품 개발 부서 관리자-----");
		System.out.println("2. 신규 보험 목록");
		System.out.println("-----고객-----");
		System.out.println("3. 신규 보험 가입");
		System.out.println("4. 보험금 청구");
		System.out.println("-----계약 관리 부서 관리자-----");
		System.out.println("5. 해지 보험 목록");
	}

}
