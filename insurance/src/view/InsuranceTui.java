package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;

import controller.MainController;
import freshInsuranceVisitor.ApprovalVisitor;
import model.insurance.InsuranceType;
import model.insurance.TermPeriod;

public class InsuranceTui {
	
	// attribute
	public static final int RE_PRINT_MENU_COUNT = 1;
	public static final int SCOPE_NONE = -2;

	// --------------------------addNewInsurance---------------------------------------

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
		LinkedHashMap<String, String> BasicPaperList = new LinkedHashMap<>();
		String input;
		System.out.println("필요 서류 이름과 입력 타입을 입력해 주십시오. (예: key value). 종료하려면 'exit'를 입력하십시오.");

		while (true) {
			input = objReader.readLine();
			if ("exit".equals(input)) {
				break;
			}

			String[] line = input.split(" ");
			if (line.length == 2) {
				BasicPaperList.put(line[0], line[1]);
			} else {
				// exception 처리 보강 예정
				System.out.println("잘못된 입력입니다. 'key value' 형식으로 입력해 주세요.");
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
		BasicPaperList.forEach((key, value) -> System.out.println(key + ": " + value));
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
	public void printApprovalNewInsurance(BufferedReader objReader) {
		/*
		 * 신규 보험 승인부
		 * 
		 * visitor pattern 사용으로 해당 메소드에서는 visitor를 호출만 한다. vistitor 는 View의 일종으로 사용.
		 */
		ApprovalVisitor visitor = new ApprovalVisitor();
//		visitor.visitInsuranceApprovalProcess(objReader, this.mainController);

	}
	
}
