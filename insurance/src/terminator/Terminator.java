package terminator;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import model.contract.ContractInsurance;
import model.contract.ContractInsuranceList;
import model.terminate.TerminateInsurance;
import model.terminate.TerminateInsuranceList;

public class Terminator {

    private ContractInsuranceList contractList;
    private TerminateInsuranceList terminateList;

    public Terminator(ContractInsuranceList contractList, TerminateInsuranceList terminateList) {
        this.contractList = contractList;
        this.terminateList = terminateList;

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TerminateTask(), 300, 6000); // 데모용 타이머 설정
    }
    //타이머
    private class TerminateTask extends TimerTask {
        public void run() {
            checkTerminateInsurance();
            System.out.println("check timer"); // debugging
        }
    }
    //해지 메소드
    private void terminate(ContractInsurance contract, Iterator<ContractInsurance> iterator, LocalDate terminateDate, boolean isExpired) {
        iterator.remove();
        int refundAmount = calculateRefundAmount(isExpired, contract.getContractDate(), terminateDate);
        TerminateInsurance terminated = new TerminateInsurance(
            contract.getInsuranceProduct().getID(), // insuranceID
            contract.getCustomer().getId(), // customerID
            terminateDate, // terminateDate
            isExpired, // 만기 해지 여부
            contract.getContractDate(), // contractDate
            refundAmount // 환급금
        );
        terminateList.addTerminateInsurance(terminated);
        System.out.println("\nTEXT ONLY IN DEMO:: 보험 ID: " + contract.getInsuranceProduct().getID() + (isExpired ? "가 계약기간이 만료되어 만기해지되었습니다." : "가 중도 해지되었습니다."));
    }
    //해지환급금 계산
    //해지환급금이 최초로 발생하는 기간:6개월 으로 설정. 보험료는 임시로 1달에 10000원으로 설정.
    //최초 해지환급금은 6개월간 납입한 총 보험료의 10%.(6000원)
    //6개월 이 후부터, 6개월 단위로 비율 증가.(증가할때마다 10% 증가. 그리고 최대 50%까지 증가할 수 있게 설정)
    //만기해지시에는 최대(50%)로 설정
    //실제 보험에서는 증가하는 비율이나 최초발생 기간이 보험별로 다르다.
    private int calculateRefundAmount(boolean isExpired, LocalDate contractDate, LocalDate terminateDate) {
        final int monthlyPremium = 10000; //임시로 1달 보험료 10000원으로 설정
        long totalMonths = java.time.temporal.ChronoUnit.MONTHS.between(contractDate, terminateDate); // 보험 계약 기간
        int refundPercentage = 0; // 해지환급기본값 0%
        
        //환급 비율 계산
        if (totalMonths >= 6) {
            refundPercentage = (int) ((totalMonths / 6) * 10); //6개월횟수*10% 증가
            if (refundPercentage > 50) {
                refundPercentage = 50; //최대 50%
            }
        }
        int totalPremiumsPaid = (int) totalMonths * monthlyPremium; //납입한 총 보험료
        int refundAmount = (totalPremiumsPaid * refundPercentage) / 100; //보험료 최종계산

        return isExpired ? 30000 : refundAmount; //임시로 현재 만기해지시 최대(30000원)적용
        //return isExpired ? (int) (totalPremiumsPaid * 50 / 100) : refundAmount; //만기해지시 50%적용
    }

    // 만기날짜가 지났는지 확인
    private void checkTerminateInsurance() {
        LocalDate now = LocalDate.now();
        Vector<ContractInsurance> contracts = contractList.getContractInsuranceList();
        Iterator<ContractInsurance> iterator = contracts.iterator();
        
        while (iterator.hasNext()) {
            ContractInsurance contract = iterator.next();
           // if (contract.getExpireDate().isBefore(now)) {
                terminate(contract, iterator, now, true);
           // }
        }
    }
    //중도해지 메소드
    public void earlyTerminate(ContractInsurance contract, LocalDate terminateDate) {
        Vector<ContractInsurance> contracts = contractList.getContractInsuranceList();
        Iterator<ContractInsurance> iterator = contracts.iterator();
        
        while (iterator.hasNext()) {
            ContractInsurance currentContract = iterator.next();
            if (currentContract.equals(contract)) {
            if (currentContract.getExpireDate().isBefore(terminateDate)) {
                System.out.println("해당 보험은 만기일자가 초과되었습니다. 만기해지 절차가 진행중입니다.");
                  terminate(currentContract, iterator, terminateDate, true);} // 예외처리:만기해지로 처리
            else {terminate(currentContract, iterator, terminateDate, false);}// 중도해지로 처리
            return;
            }
        }
    }
}
