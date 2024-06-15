package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

import controller.MainController;
import model.terminate.TerminateInsurance;

public class TerminateTui {
	private MainController mainController;

	public void associate(MainController mainController) {
		this.mainController = mainController;
	}
	// -------------------------보험 해지/환급(직원전용메뉴)----------------------------------
	public void terminateInsurance(BufferedReader objReader) throws IOException {
        Vector<TerminateInsurance> terminateList = this.mainController.getTerminateInsuranceList()
                .getTerminateInsuranceList();

        if (terminateList.isEmpty()) {
            System.out.println("해지된 보험이 없습니다.");
        } else {
            System.out.println("해지된 보험 목록:");
            for (TerminateInsurance insurance : terminateList) {
                System.out.println("-------------------------------\n" +
                		"보험 ID: " + insurance.getInsuranceId() + 
                                   "\n고객 ID: " + insurance.getCustomerId() +
                                   "\n가입 날짜: " + insurance.getContractDate() +
                                   "\n해지 날짜: " + insurance.getTerminationDate() +
                                   "\n만기해지여부: " + insurance.isMature() +
                                   "\n환급금: " + insurance.getRefundAmount() + "원" + 
                                   "\n-------------------------------\n");
            }

            System.out.println("환급금을 지급할 보험 ID를 입력하세요:");
            String insuranceIdStr = objReader.readLine();
            int insuranceId = -1;
            try {
                insuranceId = Integer.parseInt(insuranceIdStr);
            } catch (NumberFormatException e) {
                System.out.println("보험이 선택되지 않았습니다. 1개 이상의 보험을 선택해주세요.");
                return;
            }

            TerminateInsurance selectedInsurance = null;
            for (TerminateInsurance insurance : terminateList) {
                if (insurance.getInsuranceId() == insuranceId) {
                    selectedInsurance = insurance;
                    break;
                }
            }

            if (selectedInsurance != null) {
                // 환급금은 이미 설정된 값을 사용
                System.out.println("해지환급금 " + selectedInsurance.getRefundAmount() + "원을 지급했습니다.");
                this.mainController.getTerminateInsuranceList().removeTerminateInsurance(selectedInsurance);
            } else {
                System.out.println("유효하지 않은 보험 ID입니다.");
            }
        }
    }
}
