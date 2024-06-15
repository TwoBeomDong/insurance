package contractInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

import model.contract.ContractInsurance;
import terminator.Terminator;
import view.MainTui;

public class EarlyTerminateVisitor implements ContractInsuranceVisitor {

    private Terminator terminator;
    private String caution = "가입된 보험에 대한 중도해지시 위약금이 발생할 수 있으며, 일부 보험상품의 경우 해지환급금이 지급되지 않을 수 있습니다.";

    public EarlyTerminateVisitor(Terminator terminator) {
        this.terminator = terminator;
    }

    @Override
    public void visitContractInsurance(ContractInsurance contractInsurance, BufferedReader objReader) throws IOException {
        System.out.println("#주의사항#: " + caution + "\n선택한 보험을 중도해지하시겠습니까?. (yes / no)");
        if (MainTui.getBoolean(objReader)) {
            LocalDate now = LocalDate.now();
            terminator.earlyTerminate(contractInsurance, now);
            System.out.println("중도 해지가 완료되었습니다.");
        } else {
            System.out.println("중도 해지가 취소되었습니다.");
        }
    }
}
