/**
 * 
 */
package com.KTOC.TRB.testautomation.ObjectRepository;

import org.openqa.selenium.By;

/**
 * @author CON_SVIJAY02
 *
 */
public class pg_TRB_DocumentsPage {
	
	//goToDocumentsTabandClickTheTender
	public static By label_KONELogo = By.xpath("//*[text()='Print with KONE Logo']");
	public static By lnk_templateDOC = By.xpath("//*[contains(text(),'_Modular_Tender_Template.doc') or text()='Tender Letter MOD TRB' or contains(text(),'CKQ TRB Tender Letter')]");
	//verifySuccessfulMessageDisplayed
	public static By icon_printOut = By.xpath("//*[@data-ctcname='PrintOut_I']/div"); //*[@data-ctcname='PrintOut_I']/div
	public static By text_freezePrint = By.xpath("//*[contains(text(),'Do you want to freeze printed version?')]");
	public static By text_infotoDocServer = By.xpath("//*[contains(text(),'Information have been sent to the document server')]");
	public static By btn_infotoDocServer = By.xpath("//button[text()='OK']");
	//clickSaveandCloseButton
	public static By icon_saveandClose = By.xpath("//*[@data-ctcwgtname='ToolBar' and @data-ctctype='Toolbar']/div[2]");
	public static By txt_stageProbabilityDescription = By.xpath("//*[text()='Description:']/..//input");
	public static By dd_stage = By.xpath("//*[@data-ctcwgtname='_TenderVersion.Stage__c']/button");
	public static By txt_probability = By.xpath("//input[@data-ctcwgtname='_TenderVersion.Probability__c']");
	public static By btn_stageProbability = By.xpath("//*[text()='OK']");

}
