package model.insurance;

import java.util.Vector;

public class InsuranceProductList {
	private Vector<InsuranceProduct> freshInsuranceProductList;
	private Vector<InsuranceProduct> RegularInsuranceProductList;

	public InsuranceProductList() {
		this.freshInsuranceProductList = new Vector<>();
		this.RegularInsuranceProductList = new Vector<>();
	}

	public Vector<InsuranceProduct> getFreshInsuranceProductList() {
		return freshInsuranceProductList;
	}

	public void setFreshInsuranceProductList(Vector<InsuranceProduct> freshInsuranceProductList) {
		this.freshInsuranceProductList = freshInsuranceProductList;
	}

	public Vector<InsuranceProduct> getRegularInsuranceProductList() {
		return RegularInsuranceProductList;
	}

	public void setRegularInsuranceProductList(Vector<InsuranceProduct> regularInsuranceProductList) {
		this.RegularInsuranceProductList = regularInsuranceProductList;
	}

	public void finalize() throws Throwable {

	}
}
