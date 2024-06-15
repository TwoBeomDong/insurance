package freshInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;

import controller.C_InsuranceProduct;
import model.insurance.InsuranceProduct;
import model.user.User;

public interface FreshInsuranceVisitor {
	public void visitFreshInsurance(User user, InsuranceProduct insurance, C_InsuranceProduct insuranceList, BufferedReader objReader) throws IOException;
}