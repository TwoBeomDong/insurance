package freshInsuranceVisitor;

import java.io.BufferedReader;
import java.io.IOException;

import controller.C_InsuranceProduct;
import model.insurance.InsuranceProduct;
import model.insurance.info.InsuranceStatus;
import model.insurance.info.StatusChangeInfo;
import model.user.User;
import view.MainTui;

public class AdminApprovalVisitor implements FreshInsuranceVisitor {

    private boolean approveBasicInsuranceInfo(User user, int insuranceId, C_InsuranceProduct insuranceProductController, BufferedReader objReader) throws IOException {
        
    	String basicInfo = insuranceProductController.getFreshInsurance(insuranceId).getBasicInsuranceInfo().toString();
        System.out.println(basicInfo);
        System.out.println("기본보험 정보를 승인하시겠습니까? (yes/no)");
        boolean approvalInput = MainTui.getBoolean(objReader);
        if(approvalInput) {
        	insuranceProductController.getFreshInsurance(insuranceId).getBasicInsuranceInfo().approval();
            System.out.println(insuranceProductController.approvalBasicInsuranceInfo(user, insuranceId, true));
        }else { System.out.println("보험 승인이 정상적으로 거부되었습니다.");}
        return approvalInput;
    }

    private boolean approveMemberPaperForm(User user, int insuranceId, C_InsuranceProduct insuranceProductController, BufferedReader objReader) throws IOException {
        String paperFormInfo = insuranceProductController.getMemberPaperForm(insuranceId);
        System.out.println(paperFormInfo);
        System.out.println("기초서류양식을 승인하시겠습니까? (yes/no)");
        boolean approvalInput = MainTui.getBoolean(objReader);
        if(approvalInput) { System.out.println(insuranceProductController.approvalMemberPaperForm(user, insuranceId, true));
        }else {System.out.println(insuranceProductController.approvalMemberPaperForm(user, insuranceId, false));}
        return approvalInput;
    }

    private void decideStandardRate(int insuranceId, C_InsuranceProduct insuranceList, BufferedReader objReader) throws IOException {
        System.out.print("확정할 보험 기준 요율을 입력해 주십시오:");
        float rate;
		while(true) {
			try {
				rate = Float.parseFloat(objReader.readLine()); 		
				break;
			}catch(NumberFormatException e){
				System.out.print("올바르지 않은 입력입니다. 다시 입력하세요: ");
			}			
		}
        String response = insuranceList.decideStandardRate(insuranceId, rate);
        System.out.println(response);
    }

    private void setProductApprovalPaper(User user, int insuranceId, C_InsuranceProduct insuranceList, BufferedReader objReader) throws IOException {
        System.out.println("상품 인가 품의서 정보를 입력해 주십시오:");
        String info = objReader.readLine();
        String response = insuranceList.setProductApprovalPaper(user, insuranceId, info);
        System.out.println(response);
    }

	@Override
	public void visitFreshInsurance(User user,InsuranceProduct insurance, C_InsuranceProduct insuranceList, BufferedReader objReader) throws IOException {

        try {
        	int id = insurance.getID();
            // 기본보험 정보 승인
            if(!this.approveBasicInsuranceInfo(user, id, insuranceList, objReader)) return;

            // 기초서류양식 승인
            if(!this.approveMemberPaperForm(user, id, insuranceList, objReader)) return;

            // 요율 결정
            this.decideStandardRate(id, insuranceList, objReader);

            // 상품 인가 품의서 입력 및 저장
            this.setProductApprovalPaper(user, id, insuranceList, objReader);

//            System.out.println("승인이 완료되었습니다.");
            System.out.println("\n****************** Demo Message ******************");
            System.out.println(" 금융감독원 승인은 외부 요인이므로 임의 승인을 가정하고 진행합니다.");
            System.out.println("**************************************************\n");
			StatusChangeInfo statusChangeInfo = new StatusChangeInfo();
			statusChangeInfo.setPersonInCharge(user);
			statusChangeInfo.setInsuranceStatus(InsuranceStatus.FSSApproval);
			insurance.changeStatus(statusChangeInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		
	}
}
