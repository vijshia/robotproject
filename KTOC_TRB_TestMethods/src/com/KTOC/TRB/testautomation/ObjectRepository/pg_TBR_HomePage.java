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
	public static By firstFrame = By.xpath("//iframe[starts-with(@scrolling,'yes') and starts-with(@id,'vfFrameId_')]");
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

}
