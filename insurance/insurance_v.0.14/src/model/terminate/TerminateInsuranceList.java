package model.terminate;

import java.util.Vector;

public class TerminateInsuranceList {
    private Vector<TerminateInsurance> terminateInsuranceList;

    public TerminateInsuranceList() {
        this.terminateInsuranceList = new Vector<>();
    }

    public Vector<TerminateInsurance> getTerminateInsuranceList() {
        return terminateInsuranceList;
    }

    public void setTerminateInsuranceList(Vector<TerminateInsurance> terminateInsuranceList) {
        this.terminateInsuranceList = terminateInsuranceList;
    }

    public void addTerminateInsurance(TerminateInsurance terminateInsurance) {
        this.terminateInsuranceList.add(terminateInsurance);
    }

    public void removeTerminateInsurance(TerminateInsurance terminateInsurance) {
        this.terminateInsuranceList.remove(terminateInsurance);
    }
}
