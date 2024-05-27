package insurance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;

public class IMain {
	public static void main(String[] args) throws Exception {
		Insurance insurance = new Insurance();

		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));

		insurance.printMenu();
		System.out.print("menu를 선택하세요. (종료하려면 x를 입력합니다.): ");
		String sChoice = objReader.readLine().trim();

		int failNum = 0;
		while (!sChoice.equals("x")) {
			switch (sChoice) {
			case "1":
				insurance.addNewInsurance(objReader);
				break;
			case "2":
				insurance.approvalNewInsurance(objReader);
				break;
			case "3":
				System.out.println("준비중인 메뉴입니다.");
			case "menu":
				insurance.printMenu();
			default:
				failNum++;
				break;
			}
			if (failNum > insurance.RE_PRINT_MENU_COUNT) {
				System.out.println("메뉴를 다시 보고싶다면 menu를 입력하세요.");
				failNum = 0;
			}
			System.out.print("menu를 선택하세요. (종료하려면 x를 입력합니다.): ");
			sChoice = objReader.readLine().trim();
		}
	}
}
