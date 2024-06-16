package contractInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import model.contract.ContractInsurance;
import view.MainTui;

public class InsuranceClaimVisitor implements ContractInsuranceVisitor{

	@Override
	public void visitContractInsurance(ContractInsurance contractInsurance, BufferedReader objReader) throws IOException {

		System.out.println("청구를 위한 사고 경위서를 양식에 맞게 작성해주세요.");
		System.out.println("1. 사고 일시를 입력하세요.  예: 2024-06-17");
//		String accidentDate = objReader.readLine().trim();
		LocalDate accidentDate = null;
		while(true) {
	        try {
	            String input = objReader.readLine().trim();
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // 날짜 형식 지정
	            accidentDate = LocalDate.parse(input, formatter);
	            break;
	        } catch (DateTimeParseException e) {
	            System.out.println("잘못된 형식입니다. 다시 입력하세요.");
	        }	
		}

		System.out.println("2. 사고 원인을 입력하세요.  예: 전방 부주의");
		String causer = objReader.readLine().trim();

		System.out.println("3. 사고 장소를 입력하세요.  예: 명지대학교 정문");
		String place = objReader.readLine().trim();

		System.out.println("4. 사고 내용을 입력하세요.  예: 호흡 곤란으로 인한 기절");
		String detail = objReader.readLine().trim();

		System.out.println("5. 피해자 성명을 입력하세요.  예: 홍길동");
		String name = objReader.readLine().trim();

		System.out.println("6. 피해자 연락처를 입력하세요.  예: 010-6662-3518");
		String phoneNumber = objReader.readLine().trim();

		System.out.println("7. 피해자의 거주지를 입력하세요.  예: 남가좌동 95-54");
		String address = objReader.readLine().trim();

		System.out.println("8. 피해 내역을 입력하세요.  예: 발목 염좌 치료로 인한 병원비 35만원");
		String damageAmount = objReader.readLine().trim();
		System.out.println();
		
		System.out.println("기재사항에 대해 거짓이 없음에 동의하십니까? (yes/no): ");

		if (MainTui.getBoolean(objReader)) {
			if (contractInsurance.addRequestClaim(accidentDate, causer, place,
					detail, name, phoneNumber, address, damageAmount)) {
				System.out
						.println("입력하신 건에 대해 보험금 청구가 완료되었습니다. 청구 요청이 승인되고 영업일 기준 2-3일 이내에 등록된 계좌로 보험금이 지급됩니다.");
				System.out.println("또한 담당 직원이 배정된 후 수속이 진행됩니다.");
			}
		} else {
			System.out.println("기재사항에 대해 거짓이 없음에 동의하시지 않을 경우 보험금 지급이 불가합니다.\n");
			return;
		}
		
	}

}
