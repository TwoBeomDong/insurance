
package model.insurance.info;

public enum InsuranceType {
	life("생명보험"),
	thirdParty("제3보험"),
	indemnity("손해보험")
	;
	private final String name;
	private InsuranceType (String name){
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}