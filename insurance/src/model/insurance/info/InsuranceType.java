
package model.insurance.info;

public enum InsuranceType {
	life("생명보험", 10000),
	thirdParty("제3보험", 20000),
	indemnity("손해보험", 30000)
	;
	private final String name;
	private final int money; // 보험료
	private InsuranceType (String name, int money){
		this.name = name;
		this.money = money;
	}
	public String getName() {
		return this.name;
	}
	public int getMoney() {
		return this.money;
	}
}