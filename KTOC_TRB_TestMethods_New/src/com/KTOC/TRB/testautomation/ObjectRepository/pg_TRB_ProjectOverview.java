/**
 * 
 */
package com.KTOC.TRB.testautomation.ObjectRepository;

import org.openqa.selenium.By;

/**
 * @author CON_SVIJAY02
 *
 */
public class pg_TRB_ProjectOverview {

	//checkHandOverDateIsGreaterThanInstallationDate
	public static By date_DateHandoverMS5 = By.xpath("//*[@data-ctcwgtname='DateHandoverMS5_1']");
	public static By txt_DateHandoverMS5 = By.xpath("//*[@data-ctcname='MS5_HandOver_Complete_T']/input");
	//checkSalesOfficeisSelected
	public static By dd_salesOffice = By.xpath("//*[@data-ctcname='SalesOffice_T']/button");
	//additionalfieldsinProjectOverviewforCanada
	public static By dd_seismicArea = By.xpath("//*[@data-ctcname='ISeismicArea_C']/button"); //*[@data-ctcwgtname='ISeismic Area']/button
	public static By txt_weeklyTeamCostforZone = By.xpath("//*[@data-ctcname='ZoneExpense_I']"); //*[@data-ctcwgtname='ZoneExpense']
	public static By txt_weeklyTeamCostforRoomandBoard = By.xpath("//*[@data-ctcname='RoomandBoard_I']"); //*[@data-ctcwgtname='RoomandBoard']
	public static By txt_numberOfCrews = By.xpath("//*[@data-ctcwgtname='NumberOfCrews']"); 
	
	//checkHandOverDateIsGreaterThanInstallationDate (re-work)
	public static String date_DateHandoverMS5_New = "//*[@data-ctcwgtname='DateHandoverMS5_1']";
	public static String txt_DateHandoverMS5_New = "//*[@data-ctcname='MS5_HandOver_Complete_T']/input";
	
	//checkSalesOfficeisSelected (re-work)
	public static String dd_salesOffice_New = "//*[@data-ctcname='SalesOffice_T']/button";
	
	//additionalfieldsinProjectOverviewforCanada (re-work)
		public static String dd_seismicArea_New = "//*[@data-ctcname='ISeismicArea_C']/button";
		public static String txt_weeklyTeamCostforZone_New = "//*[@data-ctcname='ZoneExpense_I']";
		public static String txt_weeklyTeamCostforRoomandBoard_New = "//*[@data-ctcname='RoomandBoard_I']";
		public static String txt_numberOfCrews_New = "//*[@data-ctcwgtname='NumberOfCrews']"; 
}
