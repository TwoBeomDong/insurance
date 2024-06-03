package model.terminate;

import java.time.LocalDate;

public class TerminateInsurance {
    private int insuranceId;
    private String customerId;
    private LocalDate contractDate;
    private LocalDate terminationDate;
    private boolean ismature;
    
    public TerminateInsurance(int id, String customerId, LocalDate terminationDate, boolean ismature, LocalDate contractDate) {
        this.insuranceId = id;
        this.customerId = customerId;
        this.terminationDate = terminationDate;
        this.contractDate = contractDate;
        this.ismature = ismature;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public String getCustomerId() {
        return customerId;
    }
    
	public LocalDate getContractDate() {
		return contractDate;
	}
	
	public LocalDate getTerminationDate() {
        return terminationDate;
    }

	public boolean getIsmature() {
		return ismature;
	}
}
