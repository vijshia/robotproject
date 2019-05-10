/**
 * 
 */
package com.KTOC.TRB.testautomation.ObjectRepository;

import org.openqa.selenium.By;

/**
 * @author CON_SVIJAY02
 *
 */
public class pg_TBR_PricingPage {

	//clickonCurrenciesTab
	public static By tab_Currencies = By.xpath("//*[text()='Currencies']");
	//getConversionFactor
	public static By value_ConversionFactor = By.xpath("//*[text()='Conversion factor']/..//input");
	//clickonAdditionalDiscountIcon
	public static By icon_additionalDiscount = By.xpath("//*[@data-ctcwgtname='nPricingOverview']/div");
	public static By header_RegionalDiscount = By.xpath("//*[text()='Regional discount on component (%)']");
	//getRegionalDiscount
	public static By grid_RegionalDiscountValues = By.xpath("//*[text()='Regional discount on component (%)']/../../..//div[3]/div/div[1]/*");
	//validateDetailBreakdownTab
	public static By tab_detailBreakDown = By.xpath("//*[text()='Detail breakdown']");
	public static By dd_selectProject = By.xpath("//*[@data-ctcwgtname='nCustomComboboxProductSelection']");
	public static By value_ropes = By.xpath("//*[text()='Ropes' or text()='KCM831 (controller+electrification+trav. Cables+Wiring Duct) - A process']");
	public static By lnk_ShowTotalCostCalculationDetails = By.xpath("//*[@src='SMG?i=acdda3ea032315878f95d47164849ea79f364ad3&w=16&h=16']");
	public static By header_ITEfactor = By.xpath("//*[text()='ITE factor']");
	public static By gridvalues_SubTotal = By.xpath("//*[text()='Subtotal']/..//*");
	//gotoConfigurationPageandChangeTheSalesOffice
	public static By lnk_toConfiguration = By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[1]/img");
	//goToDocumentsTabandClickTheTender
	public static By lnk_toWord = By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[5]/img");
	//handShake
	public static By icon_pricing = By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[3]/img");
	public static By icon_HandShake = By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[9]/img");
	//selectingFirstMaintenance
	public static By dd_firstMaintenance = By.xpath("//*[@data-ctcname='FirstMaintenance_D']/button");
	public static By header_firstMaintenance = By.xpath("//*[text()='First Maintenance']");
	//selectingDiscount
	public static By btn_discountPencil = By.xpath("//*[@data-ctcname='Discount_Pencil_I']");
	public static By grid_discount = By.xpath("//*[text()='Project']/..//div[contains(text(),' %')]");
	public static By txt_discount = By.xpath("//*[@data-ctcname='New_Dicount_T']");
	public static By btn_discountOK = By.xpath("//*[@data-ctcname='Discount_Ok_B']");
	//selectingTenderPrice
	public static By tab_priceOverview = By.xpath("//*[text()='Price overview']"); 
	public static By grid_tenderPrice = By.xpath("//*[text()='Project']/..//div");
	public static By txt_tenderPrice = By.xpath("//*[@data-ctcname='TenderPrice_T']");
	public static By btn_tenderPriceOK = By.xpath("//*[@data-ctcname='TenderPrice_Ok_B']");
	//checkingTargetPrice
	public static By grid_allValues = By.xpath("//*[text()='Project']/..//*"); //*[text()='Project']/../..//div
	public static By grid_allRowallValues = By.xpath("//*[text()='Project']/../../..//div[@class='av']/div/*");
	//maximumDiscountLimitExceedPopup
	public static By header_maximumDiscountLimitExceedPopup = By.xpath("//*[contains(text(),'Maximum discount limit exceeded.')]");
	public static By btn_maximumDiscountLimitExceedPopup_YES = By.xpath("//*[text()='Yes']");
	public static By btn_maximumDiscountLimitExceedPopup_No = By.xpath("//*[text()='No']");
	public static By message_maximumDiscountLimitExceedPopup_Body = By.xpath("//textarea[2]");
	public static By btn_maximumDiscountLimitExceed_SEND = By.xpath("//*[text()='Send']");
	public static By header_maximumDiscountLimitExceedConfirmation = By.xpath("//*[text()='XML created and Sent']");
	public static By btn_maximumDiscountLimitExceedConfirmation_Ok = By.xpath("//*[text()='XML created and Sent']/../button/div");
	
	//selectingTenderPrice (re-work)
	public static String tab_priceOverview_New = "//*[text()='Price overview']";
	public static String btn_tenderPriceOK_New = "//*[@data-ctcname='TenderPrice_Ok_B']";
	
	//validateDetailBreakdownTab (re-work)
	public static String tab_detailBreakDown_New = "//*[text()='Detail breakdown']";
	public static String dd_selectProject_New = "//*[@data-ctcwgtname='nCustomComboboxProductSelection']";
	public static String value_ropes_New = "//*[text()='Ropes' or text()='KCM831 (controller+electrification+trav. Cables+Wiring Duct) - A process']";
	public static String header_ITEfactor_New = "//*[text()='ITE factor']";
	
	//clickonCurrenciesTab (re-work)
	public static String tab_Currencies_New = "//*[text()='Currencies']";
	
	//selectingFirstMaintenance (re-work)
	public static String header_firstMaintenance_New = "//*[text()='First Maintenance']";
	
	//selectingDiscount (re-work)
	public static String btn_discountOK_New = "//*[@data-ctcname='Discount_Ok_B']";
	public static String grid_discount_New = "//*[text()='Project']/..//div[contains(text(),' %')]";
	
	//maximumDiscountLimitExceedPopup (re-work)
	public static String btn_maximumDiscountLimitExceedPopup_YES_New = "//*[text()='Yes']";
	public static String btn_maximumDiscountLimitExceed_SEND_New = "//*[text()='Send']";
	public static String header_maximumDiscountLimitExceedConfirmation_New = "//*[text()='XML created and Sent']";
	public static String btn_maximumDiscountLimitExceedConfirmation_Ok_New = "//*[text()='XML created and Sent']/../button/div";
	public static String btn_maximumDiscountLimitExceedPopup_No_New = "//*[text()='No']";
	
	//getRegionalDiscount (re-work)
	public static String grid_RegionalDiscountValues_New = "//*[text()='Regional discount on component (%)']/../../..//div[3]/div/div[1]/*";
	
	//getConversionFactor (re-work)
	public static String value_ConversionFactor_New = "//*[text()='Conversion factor']/..//input";
	
	//handShake (re-work)
	public static String icon_pricing_New = "//*[@data-ctcwgtname='MainNavigationMenu']/div[3]/img";
}
