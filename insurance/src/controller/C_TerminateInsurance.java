package controller;

import model.terminate.TerminateInsurance;
import model.terminate.TerminateInsuranceList;

public class C_TerminateInsurance {
//해지보험리스트 controller
//해지 자체는 terminator가 함.
	private TerminateInsuranceList terminateInsuranceList;

    public C_TerminateInsurance() {
        this.terminateInsuranceList = new TerminateInsuranceList();
    }

    public TerminateInsuranceList getTerminateInsuranceList() {
        return terminateInsuranceList;
    }

    public void processRefund(int insuranceId) {
        for (TerminateInsurance insurance : terminateInsuranceList.getTerminateInsuranceList()) {
            if (insurance.getInsuranceId() == insuranceId) {

                System.out.println("환급금 " + insurance.getRefundAmount() + "을 지급했습니다.");
                terminateInsuranceList.removeTerminateInsurance(insurance);
                break;
            }
        }
    }
}
