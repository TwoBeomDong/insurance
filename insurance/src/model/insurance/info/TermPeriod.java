package model.insurance.info;

import java.io.Serializable;

public enum TermPeriod implements Serializable {
	month_6("6개월",6),
	month_1("1개월",1),
	;
	private final String name;
	private final int term;
	private TermPeriod (String name, int term){
		this.name = name;
		this.term = term;
	}
	public String getName() {
		return this.name;
	}
	public int getTerm() {
		return this.term;
	}
}