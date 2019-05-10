package com.KTOC.TRB.testautomation.ObjectRepository;

public class TenderPage {

	public static String SaveIcon = "//div[@id='b49_12']";
	public static String SaveOKButton = "//button[@id='e294']";
	public static String SaveCloseIcon =  "//div[@data-ctcname='Save_And_Close_Select']"; //"//div[@data-ctcname='TCSaveClose']";
	public static String SaveCloseYesButton = "//button[@data-ctcname='Save_Changes_Ok']";//"//button[@data-ctcname='AT9a1']"; //"//button[@data-ctcname='TCCloseFormSaveNo']/following::button";
	//While clicking Save and Close KTOC icon, Ok button appears after clicking Yes from the confirmation window.
	public static String CloseOKButton = "//button[@data-ctcname='SFDC_Stage_Ok']";//"//button[@data-ctcname='AT10a1']"; // "//button[@id='e300']"; 
	public static String TotalSalesPrice= "//span[contains(@id,'_id94')]";
	public static String TenderPageOpportunity ="//td[text()='Opportunity']/following::td/a";
	
}
