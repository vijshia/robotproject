package com.KTOC.TRB.testautomation.test_Run;

import com.KTOC.TRB.testautomation.Keywords.Keywords;
import com.KTOC.TRB.testautomation.Utilities.KTOCTRBUtils;

/**
 * @author CON_SVIJAY02
 *
 */
public class testRun_Sample {

	public static void main(String[] args) throws Exception {	

	Keywords Keywords=new Keywords();
	KTOCTRBUtils KTOCTRBUtils=new KTOCTRBUtils();
	String EXCEL_PATH = "\\src\\com\\KTOC\\TRB\\testautomation\\TestData\\KTOCTRB_AutomationTestData.xlsx";
//	String EXCEL_PATH = "C:\\Users\\con_svijay02\\KTOC-TRB-Automation\\KTOC_TRB_TestMethods\\src\\com\\KTOC\\TRB\\testautomation\\TestData\\KTOCTRB_AutomationTestData.xlsx";
	Keywords.LaunchBrowser("france", EXCEL_PATH);
	//1.LogonToSalesforce
	Keywords.logintoSalesforce();
	//2.VerifyCreatingOpportunityandMappingItWithFLTender
	Keywords.createOpportunityORSearchOpportunity();
	//3.VerifyTenderCreatedSuccessfully
	Keywords.navigatetoKTOCTRB();
	Keywords.addEquipmentIDElevator();
	Keywords.selectTemplateToBeUploaded();
	Keywords.checkHandOverDateIsGreaterThanInstallationDate();
	Keywords.checkSalesOfficeisSelected();
//	Keywords.additionalfieldsinProjectOverviewforCanada();
	Keywords.selectSupervisor();
	Keywords.selectEquipmentInService();
//	Keywords.selectTemplateToBeUploaded();
	Keywords.verifyTenderConsistency();
	Keywords.getTenderNumber();

	//4 ValidateTenderPriceandDiscountWithoutFirstMaintenance(Australia1st,France2nd,Canada1st)
	Keywords.pricingIconClick();
	Keywords.CheckTenderPriceAfterDiscountUpdate("6", "0");	
//	Keywords.checkingTargetPriceFullGrid(); //fullGrid
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("65000", "0");
	Keywords.GetTargetPrice();
	//5 ValidateTenderPriceandDiscountWithFirstMaintenance(Australia1st,France2nd,Canada1st)
	Keywords.CheckTenderPriceAfterDiscountUpdate("7", "3");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("64750", "2");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("64500", "3");
	Keywords.GetTargetPrice();
	//6.CheckRegionalFactorAtSalesOfficeLevel
	Keywords.verifyRegionalDiscountDisplayedCorrectly();
//	Keywords.getRegionalDiscountFullGrid();//fullGrid
	Keywords.verifyTargetPriceDisplayedCorrectly();
	//7.CheckRegionalFactorWhenSalesOfficeIsChanged(additionallyforCanada:ITEandlabourrate)
	Keywords.gotoConfigurationPageandChangeTheSalesOffice();
	Keywords.verifyRegionalDiscountDisplayedCorrectly();
	Keywords.verifyTargetPriceDisplayedCorrectly(); //"0"
	//8 & 9 Verify ITEFactorValue and LaborRateValue IsTakenFromSalesOffice
	Keywords.gotoConfigurationPageandChangeThePrimarySalesOffice("canada", EXCEL_PATH);
//	Keywords.validateDetailBreakdownTabFullGrid();//fullGrid
	Keywords.validateDetailBreakdownTab();
	Keywords.gotoConfigurationPageandChangeTheSalesOffice();
	Keywords.validateDetailBreakdownTab();
	//10.CheckCostAndPriceCalculatedCorrectlyWhenTheTenderCurrencyIsDifferentFromSLCurrency (Australia)
	Keywords.VerifyCostCalculatedSuccessfully();
	Keywords.VerifyPriceCalculatedSuccessfully_TobecheckedinFrance();
	
//*****selectMultipleEquipment*************************
	KTOCTRBUtils.readTestData("france", EXCEL_PATH);
	Keywords.addMultipleEquipments();
	Keywords.addEquipmentIDElevator();
	Keywords.checkHandOverDateIsGreaterThanInstallationDate();
	Keywords.checkSalesOfficeisSelected();
	Keywords.additionalfieldsinProjectOverviewforCanada();
	Keywords.selectSupervisor();
	Keywords.selectEquipmentInService();
	Keywords.selectTemplateToBeUploaded();
	Keywords.verifyTenderConsistency();
	Keywords.getTenderNumber();
	//4 ValidateTenderPriceandDiscountWithoutFirstMaintenance(Australia1st,France2nd,Canada1st)
	Keywords.pricingIconClick();
//	Keywords.getMaintenanceDatafromPage();
	Keywords.CheckTenderPriceAfterDiscountUpdate("6", "0");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("23000", "0");
	Keywords.GetTargetPrice();
	//5 ValidateTenderPriceandDiscountWithFirstMaintenance(Australia1st,France2nd,Canada1st)
	Keywords.CheckTenderPriceAfterDiscountUpdate("7", "3");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("22500", "4");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("22250", "3");
	Keywords.GetTargetPrice();
	//6.CheckRegionalFactorAtSalesOfficeLevel
	Keywords.verifyRegionalDiscountDisplayedCorrectly();
	Keywords.verifyTargetPriceDisplayedCorrectly();
	//7.CheckRegionalFactorWhenSalesOfficeIsChanged(additionallyforCanada:ITEandlabourrate)
	Keywords.gotoConfigurationPageandChangeTheSalesOffice();
	Keywords.verifyRegionalDiscountDisplayedCorrectly();
	Keywords.verifyTargetPriceDisplayedCorrectly();
	//8 & 9 Verify ITEFactorValue and LaborRateValue IsTakenFromSalesOffice
	Keywords.gotoConfigurationPageandChangeThePrimarySalesOffice("france", EXCEL_PATH);
	Keywords.validateDetailBreakdownTab();
	Keywords.gotoConfigurationPageandChangeTheSalesOffice();
	Keywords.validateDetailBreakdownTab();
	//10.CheckCostAndPriceCalculatedCorrectlyWhenTheTenderCurrencyIsDifferentFromSLCurrency (Australia)
	Keywords.VerifyCostCalculatedSuccessfully();
	Keywords.VerifyPriceCalculatedSuccessfully_TobecheckedinFrance();

//*****selectMultipleEquipment*************************
	//11.CheckingMaximumDiscountLimitExceeded
	Keywords.CheckingMaximumDiscountLimitExceeded("101");
	//12.CheckTenderLetterIsgeneratedCorrectlyWithAllThecomponents
	Keywords.goToDocumentsTabandClickTheTender();
	Keywords.verifySuccessfulMessageDisplayed();
	//13.CloseKTOC
	Keywords.clickSaveandCloseButton();
	//14.VerifyTotalSalesPriceWithSFProductInformation
	Keywords.compareSalesPricebetweenTenderPageandSalesforce();
	Keywords.handShake();
	Keywords.CloseBrowser();
	}
}
