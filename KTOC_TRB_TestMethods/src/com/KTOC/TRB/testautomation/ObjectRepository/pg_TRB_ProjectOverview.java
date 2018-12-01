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
	public static By dd_seismicArea = By.xpath("//*[@data-ctcwgtname='ISeismic Area']/button");
	public static By txt_weeklyTeamCostforZone = By.xpath("//*[@data-ctcwgtname='ZoneExpense']");
	public static By txt_weeklyTeamCostforRoomandBoard = By.xpath("//*[@data-ctcwgtname='RoomandBoard']");

}
