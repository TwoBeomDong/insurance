package model.user;

/**
 * @author dongyeonkim
 * @version 1.0
 * @created 14-5-2024 ���� 6:43:14
 */
public class Employee extends User {
	private Division division;
	public Employee(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

}