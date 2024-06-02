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
        timer.scheduleAtFixedRate(new TerminateTask(), 60000, 60000); // 데모하기 위해 프로그램 시작 60초후 timer실행, 60초마다 timer가 만기보험 체크
    }

    class TerminateTask extends TimerTask {
        public void run() {
            checkTerminateInsurance();
        }
    }

    private void checkTerminateInsurance() {
        LocalDate now = LocalDate.now();
        Vector<ContractInsurance> contracts = contractList.getContractInsuranceList();
        Iterator<ContractInsurance> iterator = contracts.iterator();
        
        while (iterator.hasNext()) {
            ContractInsurance contract = iterator.next();   	
//          if (contract.getExpireDate().isBefore(now)) { 현재 만기 날짜를 확인하지 않고 무조건 계약 보험 삭제, 해지보험에 저장
                iterator.remove();
                TerminateInsurance terminated = new TerminateInsurance(
                    contract.getInsuranceProduct().getID(), // insuranceID
                    contract.getCustomer().getId(), // customerID
                    now, // expireDate
                    true, // isTerminated
                    contract.getContractDate() // contractDate
                );
                terminateList.addTerminateInsurance(terminated);
                System.out.println("TEXT ONLY IN DEMO:: 보험 ID: " + contract.getInsuranceProduct().getID()+"가 계약기간이 만료되어 만기해지되었습니다.");
        //}
        }
    }
}
