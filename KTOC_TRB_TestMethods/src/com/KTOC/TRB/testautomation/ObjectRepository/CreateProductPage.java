package com.KTOC.TRB.testautomation.ObjectRepository;

public class CreateProductPage 
{
	
public static String KTOCFrameId = "clientTarget";
public static String ProductPlatformField =  "//div[@data-ctcname='Select_Platform_Selection']"; //"//div[@data-ctcname='TC12a']";
//public static String ProductPlatformValue= "//div[text() = 'KONE Ecospace 15.2']";//"//div[@id='s71']";    //"//div[@data-ctcname='TC12a']/input[1]";  // "//input[@id='i71']";    //  
public static String SalesOfficeField=  "//div[@data-ctcname='Sales_Office_Selection']";//"//div[@data-ctcname='TC12c']";
//public static String SalesOfficeField= "//div[@id='e93']";  //"//div[@data-ctcname='TC12c']";
//public static String SalesOfficeValue = "//div[text()='AT11 - Wien 1']";  //"//div[@id='s93']/div[1]";
public static String CreateProductButton =  "//button[@data-ctcname='Create_Product_Ok']"; //"//button[@data-ctcname='TC12a']";
public static String TenderNo = "//input[@data-ctcname='FL_Tender_Number_Select']";  //"//input[@data-ctcname='AT7a1']";
public static String WrongCreateProductButton = "//button[@data-ctcname='TC1a']";


//Template Locators
public static String TemplateExpand= "//div[@data-ctcname='XML_Template_Open']";  //"//div[@data-ctcname='AT8b1']";
public static String TemplateSection = "//div[@data-ctcname='AT8b3']";
public static String AutomationTestingTemplate="//div[text()='Automation Testing Template']/parent::div";

//Dropdown Values
public static String ProductPlatformValue= "//body[@class='notranslate']/child::div[9]/div/child::div[1]"; //"KONE MonoSpace Home 18.1 - EXP"; //"KONE Ecospace 15.2";
public static String SalesOfficeValue = "AT11 - Wien 1";  //"//div[@id='s93']/div[1]";

//Node Values
public static String DivNode="div";

}
