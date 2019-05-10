/**
 * 
 */
package com.KTOC.TRB.testautomation.ObjectRepository;

import org.openqa.selenium.By;
import static com.KTOC.TRB.testautomation.Utilities.KTOCTRBUtils.NewVersionProduct;
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
	public static By header_flTender = By.xpath("//*[@class='title' and text()='Tenders & Orders']"); //*[@title='FL Tenders' and starts-with(text(),'FL Tenders')]
	public static By btn_flTender = By.xpath("//*[@title='New FL Tender' and text()='New FL Tender']");
	//getSalesPriceFromSalesForce
	public static By frameforwait = By.tagName("iframe");
	public static By btn_configurator = By.xpath("//*[@title='Configurator']");
	public static By btn_ButtoninNewVersionProduct = By.xpath("//button[text()='"+NewVersionProduct+"']");
	
	//Salesforce HomePage(re-work)
		public static String txt_searchBox_New = "//*[@title='Search Salesforce' or @id='phSearchInput']";
		public static String lnk_toLigntning_New="//*[@class='switch-to-lightning']";
		public static String txt_ligtningHomeSearchbox_New = "//*[@title='Search Salesforce']";
		public static String img_toClassic_New="(//*[@class='tooltipTrigger tooltip-trigger uiTooltip' and @data-aura-class='uiTooltip'])[last()]";
		public static String lnk_toClassic_New="//*[text()='Switch to Salesforce Classic']";
		public static String txt_classicHomeSearchbox_New="phSearchInput";
		//Salesforce TenderPage
		public static String header_flTender_New = "//*[@class='title' and text()='Tenders & Orders']"; //*[@title='FL Tenders' and starts-with(text(),'FL Tenders')]
		public static String btn_flTender_New = "//*[@title='New FL Tender' and text()='New FL Tender']";
		//getSalesPriceFromSalesForce
		public static String frameforwait_New = "iframe";
		public static String btn_configurator_New = "//*[@title='Configurator']";
		public static String btn_ButtoninNewVersionProduct_New = "//button[text()='"+NewVersionProduct+"']";

}
