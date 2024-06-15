package model.terminate;

import java.time.LocalDate;

public class TerminateInsurance {
    private int insuranceId;
    private String customerId;
    private LocalDate terminateDate;
    private boolean isMature;
    private LocalDate contractDate;
    private int refundAmount;

    public TerminateInsurance(int insuranceId, String customerId, LocalDate terminationDate, boolean isMature, LocalDate contractDate, int refundAmount) {
        this.insuranceId = insuranceId;
        this.customerId = customerId;
        this.terminateDate = terminationDate;
        this.isMature = isMature;
        this.contractDate = contractDate;
        this.refundAmount = refundAmount;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getTerminationDate() {
        return terminateDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminateDate = terminationDate;
    }
    
    public boolean isMature() {
        return isMature;
    }

    public void setMature(boolean isMature) {
        this.isMature = isMature;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }
}
