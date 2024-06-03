package model.terminate;

import java.time.LocalDate;

public class TerminateInsurance {
    private int insuranceId;
    private String customerId;
    private LocalDate terminationDate;

    public TerminateInsurance(int id, String customerId, LocalDate terminationDate, boolean ismature, LocalDate contractDate) {
        this.insuranceId = id;
        this.customerId = customerId;
        this.terminationDate = terminationDate;
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
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }
}
