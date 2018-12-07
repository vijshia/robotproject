/**
 * 
 */
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
	Keywords.CheckTenderPriceAfterDiscountUpdate("10", "0");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("2001", "0");
	Keywords.GetTargetPrice();
	//5 ValidateTenderPriceandDiscountWithFirstMaintenance(Australia1st,France2nd,Canada1st)
	Keywords.CheckTenderPriceAfterDiscountUpdate("11", "3");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("2002", "3");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("2003", "6");
	Keywords.GetTargetPrice();
	//6.CheckRegionalFactorAtSalesOfficeLevel
	Keywords.verifyRegionalDiscountDisplayedCorrectly();
	Keywords.verifyTargetPriceDisplayedCorrectly("0");
	//7.CheckRegionalFactorWhenSalesOfficeIsChanged(additionallyforCanada:ITEandlabourrate)
	Keywords.gotoConfigurationPageandChangeTheSalesOffice();
	Keywords.verifyRegionalDiscountDisplayedCorrectly();
	Keywords.verifyTargetPriceDisplayedCorrectly("0");
	//8 & 9 Verify ITEFactorValue and LaborRateValue IsTakenFromSalesOffice
	Keywords.gotoConfigurationPageandChangeThePrimarySalesOffice("france", EXCEL_PATH);
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
	Keywords.CheckTenderPriceAfterDiscountUpdate("15", "0");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("2501", "0");
	Keywords.GetTargetPrice();
	//5 ValidateTenderPriceandDiscountWithFirstMaintenance(Australia1st,France2nd,Canada1st)
	Keywords.CheckTenderPriceAfterDiscountUpdate("16", "3");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("2502", "4");
	Keywords.GetTargetPrice();
	Keywords.VerifyDiscountByChangingTheTenderPrice("2503", "7");
	Keywords.GetTargetPrice();
	//6.CheckRegionalFactorAtSalesOfficeLevel
	Keywords.verifyRegionalDiscountDisplayedCorrectly();
	Keywords.verifyTargetPriceDisplayedCorrectly("0");
	//7.CheckRegionalFactorWhenSalesOfficeIsChanged(additionallyforCanada:ITEandlabourrate)
	Keywords.gotoConfigurationPageandChangeTheSalesOffice();
	Keywords.verifyRegionalDiscountDisplayedCorrectly();
	Keywords.verifyTargetPriceDisplayedCorrectly("0");
	//8 & 9 Verify ITEFactorValue and LaborRateValue IsTakenFromSalesOffice
	Keywords.gotoConfigurationPageandChangeThePrimarySalesOffice("france", EXCEL_PATH);
	Keywords.validateDetailBreakdownTab();
	Keywords.gotoConfigurationPageandChangeTheSalesOffice();
	Keywords.validateDetailBreakdownTab();
	//10.CheckCostAndPriceCalculatedCorrectlyWhenTheTenderCurrencyIsDifferentFromSLCurrency (Australia)
	Keywords.VerifyCostCalculatedSuccessfully();
	Keywords.VerifyPriceCalculatedSuccessfully_TobecheckedinFrance();

//*****selectMultipleEquipment*************************
	//11.CheckTenderLetterIsgeneratedCorrectlyWithAllThecomponents
	Keywords.goToDocumentsTabandClickTheTender();
	Keywords.verifySuccessfulMessageDisplayed();
	//12.CloseKTOC
	Keywords.clickSaveandCloseButton();
	//13.VerifyTotalSalesPriceWithSFProductInformation
	Keywords.compareSalesPricebetweenTenderPageandSalesforce();
	Keywords.handShake();
	}
}
