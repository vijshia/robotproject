/**
 * 
 */
package com.KTOC.TRB.testautomation.ObjectRepository;

import org.openqa.selenium.By;

/**
 * @author CON_SVIJAY02
 *
 */
public class pg_SF_HomePage {
	
	//Salesforce HomePage
	public static By txt_searchBox = By.xpath("//*[@title='Search Salesforce' or @id='phSearchInput']"); //*[@title='Search Salesforce' or @id='phSearchInput']
	public static By lnk_toLigntning=By.xpath("//*[@class='switch-to-lightning']");
	public static By txt_ligtningHomeSearchbox = By.xpath("//*[@title='Search Salesforce']");
	public static By img_toClassic=By.xpath("(//*[@class='tooltipTrigger tooltip-trigger uiTooltip' and @data-aura-class='uiTooltip'])[last()]");
	public static By lnk_toClassic=By.xpath("//*[text()='Switch to Salesforce Classic']");
	public static By txt_classicHomeSearchbox=By.id("phSearchInput");
	//Salesforce TenderPage
	public static By header_flTender = By.xpath("//*[@title='FL Tenders' and starts-with(text(),'FL Tenders')]");
	public static By btn_flTender = By.xpath("//*[@title='New FL Tender' and text()='New FL Tender']");
	//getSalesPriceFromSalesForce
	public static By frameforwait = By.tagName("iframe");
	public static By btn_configurator = By.xpath("//*[@title='Configurator']");
	public static By btn_NoButtoninNewVersionProduct = By.xpath("//button[text()='No']");

}
