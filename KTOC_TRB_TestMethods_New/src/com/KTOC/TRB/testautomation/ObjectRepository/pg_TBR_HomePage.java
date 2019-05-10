/**
 * 
 */
package com.KTOC.TRB.testautomation.ObjectRepository;

import org.openqa.selenium.By;

/**
 * @author CON_SVIJAY02
 *
 */
public class pg_TBR_HomePage {
	
	//KTOC-TRB HomePage, addEquipmentIDElevator
	public static By firstFrame = By.xpath("//iframe[starts-with(@scrolling,'yes')]"); // and starts-with(@id,'vfFrameId_')
	public static By secondFrame = By.id("clientTarget");
	public static By btn_productrelease_OK = By.xpath("//*[@data-ctcname='Product_Release_Version_Ok_B']");
	public static By elementtoInvisible = By.xpath("//*[@id='gp' and @class='gp']");
	public static By txt_CustomerID = By.xpath("//*[@data-ctcname='Customer_ID_T']");
	public static By txt_EquipmentID = By.xpath("//*[@data-ctcname='Equipment_ID_T']");
	public static By lookup_EquipmentID = By.xpath("//*[@data-ctcname='Equipment_ID_I']");
	public static By header_AddNewEquipment = By.xpath("//*[text()='Add new equipment' or text()='Found no dataset']");
	public static By header_AddNewGroup = By.xpath("//*[text()='Add new group:']");
	public static By txt_EquipmentIDPopUpT = By.xpath("//*[@data-ctcname='Equipment_ID_PopUp_T']");
	public static By lnk_SearchEquipment = By.xpath("//*[text()='Search Equipment']");
	
	
	public static By btn_addEquipment = By.xpath("//*[@data-ctcname='Equipment_List_AddEquipmentNo_PopUp_I']");
	public static By btn_changeEquipment = By.xpath("//*[@data-ctcname='Equipment_List_ChangeEquipmentData_PopUp_I']");
	//selectSupervisor
	public static By dd_supervisor = By.xpath("//*[@data-ctcname='Supervisor_C']");
	//selectEquipmentInService
	public static By dd_equipmentinService = By.xpath("//*[@data-ctcname='Equipment_In_Service_C']/button");
	public static By radio_hydeaulicElevator = By.xpath("//*[@data-ctcname='Hydraulic_Elevator_Check_CB']");
	//selectTemplateToBeUploaded
	public static By lnk_openTemplates = By.xpath("//div[text()='Open Templates']");
	public static By lnk_binaryTemplates = By.xpath("(//*[@data-ctcname='Template_Open_I'])[last()-1]");
	public static By txt_searchTemplate = By.xpath("//*[@data-ctcname='Template_Search_T']");
	public static By radio_sharedTemplate = By.xpath("//*[@data-ctcname='Shared_Template_AllOrg_R']");
	//verifyTenderConsistency
	public static By consistencyCheckElement = By.xpath("//div/div[text()='Project']/../..//div/div");
	//getTenderNo
	public static By text_tenderNumber = By.xpath("//*[@data-ctcname='Document_Number_T']");
	
	//KTOC-TRB HomePage, addEquipmentIDElevator (re-work)
		public static String firstFrame_New = "//iframe[starts-with(@scrolling,'yes')]"; // and starts-with(@id,'vfFrameId_')
		public static String secondFrame_New = "clientTarget";
		public static String btn_productrelease_OK_New = "//*[@data-ctcname='Product_Release_Version_Ok_B']";
		public static String elementtoInvisible_New = "//*[@id='gp' and @class='gp']";
		public static String txt_CustomerID_New = "//*[@data-ctcname='Customer_ID_T']";
		public static String txt_EquipmentID_New = "//*[@data-ctcname='Equipment_ID_T']";
		public static String lookup_EquipmentID_New = "//*[@data-ctcname='Equipment_ID_I']";
		public static String header_AddNewEquipment_New = "//*[text()='Add new equipment' or text()='Found no dataset']";
		public static String header_AddNewGroup_New = "//*[text()='Add new group:']";
		public static String txt_EquipmentIDPopUpT_New = "//*[@data-ctcname='Equipment_ID_PopUp_T']";
		public static String lnk_SearchEquipment_New = "//*[text()='Search Equipment']";
		
		
		public static String btn_addEquipment_New = "//*[@data-ctcname='Equipment_List_AddEquipmentNo_PopUp_I']";
		public static String btn_changeEquipment_New = "//*[@data-ctcname='Equipment_List_ChangeEquipmentData_PopUp_I']";
		//selectSupervisor (re-work)
		public static String dd_supervisor_New = "//*[@data-ctcname='Supervisor_C']";
		//selectEquipmentInService (re-work)
		public static String dd_equipmentinService_New = "//*[@data-ctcname='Equipment_In_Service_C']/button";
		public static String radio_hydeaulicElevator_New = "//*[@data-ctcname='Hydraulic_Elevator_Check_CB']";
		//selectTemplateToBeUploaded (re-work)
		public static String lnk_openTemplates_New = "//div[text()='Open Templates']";
		public static String lnk_binaryTemplates_New = "(//*[@data-ctcname='Template_Open_I'])[last()-1]";
		public static String txt_searchTemplate_New = "//*[@data-ctcname='Template_Search_T']";
		public static String radio_sharedTemplate_New = "//*[@data-ctcname='Shared_Template_AllOrg_R']";
		//verifyTenderConsistency (re-work)
		public static String consistencyCheckElement_New = "//div/div[text()='Project']/../..//div/div";
		//getTenderNo (re-work)
		public static String text_tenderNumber_New = "//*[@data-ctcname='Document_Number_T']";
		
		//KTOC-TRB HomePage, addEquipmentIDElevator  (re-work)
		public static String header_AddNewGroup_New_rework = "//*[text()='Add new group:']";
}
