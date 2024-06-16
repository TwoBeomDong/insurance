package visitor.freshInsurance;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

import controller.C_InsuranceProduct;
import model.insurance.InsuranceProduct;
import model.insurance.info.InsuranceStatus;
import model.insurance.info.StatusChangeInfo;
import model.insurance.info.TrainMatter;
import model.insurance.info.TrainPlan;
import model.user.User;
import view.InsuranceTui;
import view.MainTui;

public class TrainApprovalVisitor implements FreshInsuranceVisitor {

	@Override
	public void visitFreshInsurance(User user, InsuranceProduct insurance, C_InsuranceProduct insuranceList,
			BufferedReader objReader) throws IOException {
        StatusChangeInfo statusChangeInfo = new StatusChangeInfo();
        statusChangeInfo.setPersonInCharge(user);
		
		String basicInfo = insuranceList.getFreshInsurance(insurance.getID()).getBasicInsuranceInfo().toString();
        System.out.println(basicInfo);
        System.out.print("해당 보험에 대한 교육을 승인하시겠습니까? (yes / no): ");
        
        if(!MainTui.getBoolean(objReader)) {
        	System.out.print("승인 보류 사유를 입력하세요: ");
        	String reason = objReader.readLine();
        	statusChangeInfo.setChangeReason(reason);
        	statusChangeInfo.setInsuranceStatus(InsuranceStatus.trainWait);
        	insurance.changeStatus(statusChangeInfo);
        	return;
        }
        System.out.print("교재 링크를 입력하세요: ");
        String courseBookLink = objReader.readLine();
        insurance.setCourseBookLink(courseBookLink);
        System.out.println("교재 링크가 정상적으로 등록되었습니다.");
        System.out.println("----- 교육 재반 입력 ------");
        TrainMatter trainMatter = new TrainMatter();
        System.out.print("예상 마감 연도: ");
        int year = MainTui.getInputInteger(objReader, InsuranceTui.SCOPE_NONE);
        System.out.print("예상 마감월 : ");
        int month = MainTui.getInputInteger(objReader, 12);
        System.out.print("예상 마감일 : ");
        int day = MainTui.getInputInteger(objReader, 31);
        trainMatter.setEstimatedClosingDate(LocalDate.of(year, month, day));
//        insurance.set
        System.out.print("교육 장소: ");
        trainMatter.setPlace(objReader.readLine());
        System.out.print("교육 대상 부서: ");
        System.out.print("교육자 이름: ");
        trainMatter.setInstructorName(objReader.readLine());
        insurance.setTrainMatter(trainMatter);
        
        System.out.println("----- 교육 계획 입력 ------");
        TrainPlan trainPlan = new TrainPlan();
        System.out.print("교육 계획: ");
        trainPlan.setPlan(objReader.readLine());
        System.out.print("교육 예산: ");
        trainPlan.setBudget(MainTui.getInputInteger(objReader, InsuranceTui.SCOPE_NONE));
        insurance.setTrainPlan(trainPlan);
        
        System.out.println("교육 요청 승인이 완료되었습니다.");
    	statusChangeInfo.setInsuranceStatus(InsuranceStatus.trainProgress);
    	insurance.changeStatus(statusChangeInfo);
	}

}
