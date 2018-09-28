package com.KTOC.TRB.testautomation.test_Run;

import com.KTOC.TRB.testautomation.Keywords.Keywords;
import com.KTOC.TRB.testautomation.Utilities.KTOCTRBUtils;

public class testRun {

	public static void main(String[] args) {
		KTOCTRBUtils obj=new KTOCTRBUtils();
		Keywords obj1=new Keywords();
		try {
			obj.LaunchBrowser("windows");
			obj1.LogonToSalesforce();	
			obj1.CreateOpportunity();
			obj1.MapContactWithOpportunity();
			obj1.MapOpportunityWithFLTenders();
			obj1.SwitchToKTOCApp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
