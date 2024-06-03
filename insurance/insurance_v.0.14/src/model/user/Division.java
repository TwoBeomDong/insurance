package model.user;

public enum Division {
	ProductDevelopment("상품개발부"),
	
	;
	private String name;
	private Division(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}
