package com.KTOC.TRB.testautomation.test_Run;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.icu.impl.Assert;

import com.KTOC.TRB.testautomation.ObjectRepository.SalesForceData;


public class testRun_KTOCTRB {

	static WebDriver driver;
	static WebDriverWait wait;
	static DecimalFormat roundoff = new DecimalFormat("##.00");
	static String browser;
	public static String opportunity;
	static String equipmentid = "10503512"; // France:10503512 / 10911391, Australia:30493722
	static String customerid = "A10137873";
//	static String ProductRelease;
	static String salesoffice="VB FRRW"; // France: VB FRRW, VB FRPP, Australia: AU21 - Sydney Region;
	static String changeSalesOffice="VB FRPP"; //France: VB FRPP, Australia: AU22 - Newcastle Region
	static int MS5HODatetoChange=2;
	static String equipmentinService="DOC Door"; //LIS Elevator, EIS Escalator, Non-Lis Elevator, Non-Lis Escalator, DIS Door, DOC Door, Non DIS/DOC Door;
	static String template = "Automation_Template_002"; //France: "Automation_Template_002", Australia: AustraliaRopes_For Automation
	static String withoutFirstMaintenance = "0";
	static String withFirstMaintenance_1 = "3";
	static String withFirstMaintenance_2 = "8";
	static String discount= "10";
	static String tenderPrice = "2001";
	static Boolean istenderPrice = false;
	static Float regionalDiscount = 5f;
	static int changeregionalDiscount = 4;
	static Float check_showtotal_ITEfactor=2f;
	static String FreezePrintedVersion="No";
	static String SaveandClose="Yes";
	static String StageProbability_Stage="Budget Price"; //RFQ, Prospecting/Design Assist, Budget Price, Tender/Proposal, Negotiation/Review, Order Agreed;
	
	public static void main(String[] args) throws Exception {		
		
		testRun_KTOCTRB testClass=new testRun_KTOCTRB();
		browser = "ff";
		opportunity = "KOFCOL TRB SFA"; // france: "KOFCOL TRB SFA", Australia: "KOFCOL TRB 201"
		String ProductRelease ="1723"; //1813 1723 1713
//		equipmentid = "10503512"; // 10894788
		String equipment_ADDorChange = "change";
//		salesoffice="VB FRPP";// VB FRPC, VB FRPF, VB FRPH, VB FRPP, VB FRRA, VB FRRE, VB FRRM, VB FRRN, VB FRRR, VB FRRS, VB FRRW
//		MS5HODatetoChange=2;
//		equipmentinService="DOC Door"; //LIS Elevator, EIS Escalator, Non-Lis Elevator, Non-Lis Escalator, DIS Door, DOC Door, Non DIS/DOC Door
		String supervisor_ResponsiblePerson="06114080"; //France: 06114080(VB FRRW), Australia: 13012004
//		template = "Automation_Template_002";
//		FirstMaintenance = "0";
//		discount = "10";
//		tenderPrice = "2001";
//		testRun_KTOCTRB.istenderPrice = false;
//		check_showtotal_ITEfactor=2f;
//		FreezePrintedVersion="No"; // Yes No
//		SaveandClose="Yes"; //No Yes
//		StageProbability_Stage="Budget Price"; //RFQ, Prospecting/Design Assist, Budget Price, Tender/Proposal, Negotiation/Review, Order Agreed
		String StageProbability_Description="Automation Test Description";
		String StageProbability_probability="22";

		testClass.launchBrowser("windows", browser);
		//1.LogonToSalesforce
		testClass.logonToSalesforce("s.vijay@kone.com.qa", "Vijay1234");
		//2.VerifyCreatingOpportunityandMappingItWithFLTender
		testClass.createOpportunityORSearchOpportunity("search",opportunity);
		//3.VerifyTenderCreatedSuccessfully
		testClass.addEquipmentIDElevator(ProductRelease, equipment_ADDorChange);
		testClass.checkHandOverDateIsGreaterThanInstallationDate();
		testClass.checkSalesOfficeisSelected(salesoffice);
		testClass.selectSupervisor(supervisor_ResponsiblePerson);
		testClass.selectEquipmentInService();
		testClass.selectTemplateToBeUploaded(template);
		testClass.verifyTenderConsistency();
		testClass.getTenderNo();
		//4 ValidateTenderPriceandDiscountWithoutFirstMaintenance(Australia1st,France2nd,Canada1st)
		testClass.pricingIconClick();
		testClass.CheckTenderPriceAfterDiscountUpdate(discount, withoutFirstMaintenance);
		testClass.GetTargetPrice();
		testClass.VerifyDiscountByChangingTheTenderPrice(tenderPrice, withoutFirstMaintenance);
		testClass.GetTargetPrice();
		//5 ValidateTenderPriceandDiscountWithFirstMaintenance(Australia1st,France2nd,Canada1st)
		testClass.CheckTenderPriceAfterDiscountUpdate(discount, withFirstMaintenance_1);
		testClass.GetTargetPrice();
		testClass.VerifyDiscountByChangingTheTenderPrice(tenderPrice, withFirstMaintenance_1);
		testClass.GetTargetPrice();
		testClass.VerifyDiscountByChangingTheTenderPrice(tenderPrice, withFirstMaintenance_2);
		testClass.GetTargetPrice();
		//6.CheckRegionalFactorAtSalesOfficeLevel
		testClass.verifyRegionalDiscountDisplayedCorrectly();
		testClass.verifyTargetPriceDisplayedCorrectly(withoutFirstMaintenance);
		//7.CheckRegionalFactorWhenSalesOfficeIsChanged(additionallyforCanada:ITEandlabourrate)
		testClass.gotoConfigurationPageandChangeTheSalesOffice(changeSalesOffice);
		testClass.verifyRegionalDiscountDisplayedCorrectly();
		testClass.verifyTargetPriceDisplayedCorrectly(withoutFirstMaintenance);
		//8 & 9 Verify ITEFactorValue and LaborRateValue IsTakenFromSalesOffice
		testClass.validateDetailBreakdownTab();
		testClass.gotoConfigurationPageandChangeTheSalesOffice(changeSalesOffice);
		testClass.validateDetailBreakdownTab();
		//10.CheckCostAndPriceCalculatedCorrectlyWhenTheTenderCurrencyIsDifferentFromSLCurrency (Australia)
		testClass.VerifyCostCalculatedSuccessfully();
		testClass.VerifyPriceCalculatedSuccessfully_TobecheckedinFrance();
		//11.CheckTenderLetterIsgeneratedCorrectlyWithAllThecomponents
		testClass.goToDocumentsTabandClickTheTender();
		testClass.verifySuccessfulMessageDisplayed();
		//12.CloseKTOC
		testClass.clickSaveandCloseButton(StageProbability_Description, StageProbability_probability);
		//13.VerifyTotalSalesPriceWithSFProductInformation
		testClass.getSalesPriceFromSalesForce();
		testClass.handShake();
	}

	/**
	 **Reuse method, it will launch the browser
	 * @param browser
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void launchBrowser(String operatingsystem, String browser) throws Exception{
		if (operatingsystem.equalsIgnoreCase("ios")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setBinary(new File(System.getProperty("user.dir")));
			String headless = System.getProperty("headless");
			if (headless != null && headless.equalsIgnoreCase("true")) {
				// String PROXY = "23.23.23.23:3128"; //# IP:PORT or HOST:PORT
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--no-proxy-server");
				// chromeOptions.addArguments("--proxy-server=http://%s' % PROXY");
				chromeOptions.addArguments("--ignore-certificate-errors");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("window-size=2000,2000");
			}
				driver = new ChromeDriver(chromeOptions);
			} else if (operatingsystem.equalsIgnoreCase("windows")) {
			if (browser.equalsIgnoreCase("ff")) {
				System.setProperty("webdriver.gecko.driver","C:\\Users\\con_svijay02\\Downloads\\geckodriver-v0.23.0-win64\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("ch")) {
				DesiredCapabilities CHDes = DesiredCapabilities.chrome();
				ChromeOptions CHOpt = new ChromeOptions();
				CHDes.setCapability(ChromeOptions.CAPABILITY, CHOpt);
				File CHPath = new File("C:\\Backups\\Vijay S\\Download Folder\\chromedriver_win32 (2.42)\\chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", CHPath.getAbsolutePath());
				driver = new ChromeDriver();
			}
		}	
		wait = new WebDriverWait(driver, 50000);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("https://test.salesforce.com");
	}
	
	/**
	 **Reuse method, it will enter username & password
	 * @param username: username to be entered
	 * @param password: password to be entered
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public static By txt_userName = By.id("username");
	public static By txt_password = By.id("password");
	public static By btn_login = By.id("Login");
	public void logonToSalesforce(String username, String password) throws Exception{
		try {
			waitForVisibilityOfElementLocated(txt_userName);
			enteringValues(txt_userName, username);
			enteringValues(txt_password, password);
			clickonButton(btn_login);
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Logon To Salesforce Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will CreateOpportunity, MapContactWithOpportunity, MapOpportunityWithFLTenders or Search Opportunity and click on New FLTender button
	 *@param opportunity: opportunity to be searched
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By txt_searchBox = By.xpath("//*[@title='Search Salesforce' or @id='phSearchInput']");
	public static By lnk_toLigntning=By.xpath("//*[@class='switch-to-lightning']");
	public static By txt_ligtningHomeSearchbox = By.xpath("//*[@title='Search Salesforce']");
	public static By img_toClassic=By.xpath("(//*[@class='tooltipTrigger tooltip-trigger uiTooltip' and @data-aura-class='uiTooltip'])[last()]");
	public static By lnk_toClassic=By.xpath("//*[text()='Switch to Salesforce Classic']");
	public static By txt_classicHomeSearchbox=By.id("phSearchInput");
	public static By header_flTender = By.xpath("//*[@title='FL Tenders' and starts-with(text(),'FL Tenders')]");
	public static By btn_flTender = By.xpath("//*[@title='New FL Tender' and text()='New FL Tender']");
	public void createOpportunityORSearchOpportunity(String createorsearch, String opportunity) throws Exception{
		try {
			By lnk_selectOpportunity = By.xpath("(//*[@title='"+opportunity+"'])[last()]");
			WebElement searchBox = gettingWebElement(txt_searchBox);
			wait.until(ExpectedConditions.visibilityOf(searchBox));
			if(createorsearch.equalsIgnoreCase("search")) {
				if(!searchBox.getAttribute("title").equals("Search Salesforce")) {
					waitForVisibilityOfElementLocated(lnk_toLigntning);
					clickonButton(lnk_toLigntning);
					waitForVisibilityOfElementLocated(txt_ligtningHomeSearchbox);
					System.out.println("ligtning HomePage displayed");
				} 
				enteringValues(txt_searchBox, opportunity);
				System.out.println("Entered opportunity name:"+opportunity+ " in Search box");
				enteringValues(txt_searchBox, Keys.RETURN);
				waitForVisibilityOfElementLocated(lnk_selectOpportunity);
				clickonButton(lnk_selectOpportunity);
				System.out.println(opportunity+" has been clicked");
				waitForElementToBeClickable(header_flTender);
				clickonButton(header_flTender);
//				System.out.println("clicked on FL Tenders header in Opportunity");
				waitForVisibilityOfElementLocated(btn_flTender);
				clickonButton(btn_flTender);
//				System.out.println("clicked on New FLTender button in FL Tender page");
			} else {
				if(!searchBox.getAttribute("title").equals("Search...")) {
					waitForVisibilityOfElementLocated(img_toClassic);
					clickonButton(img_toClassic);
					waitForVisibilityOfElementLocated(lnk_toClassic);
					clickonButton(lnk_toClassic);
					waitForVisibilityOfElementLocated(txt_classicHomeSearchbox);
					System.out.println("Classic HomePage Displayed");
				}
				CreateOpportunity();
				MapContactWithOpportunity();
				MapOpportunityWithFLTenders();
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("CreateOpportunity OR SearchOpportunity Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will add/change equipment
	 *@param ProductRelease: Release number of the application
	 *@param equipment_ADDorChange: equipment to be added or changed
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By firstFrame = By.xpath("//iframe[starts-with(@scrolling,'yes') and starts-with(@id,'vfFrameId_')]");
	public static By secondFrame = By.id("clientTarget");
	public static By btn_productrelease_OK = By.xpath("//*[@data-ctcname='Product_Release_Version_Ok_B']");
	public static By elementtoInvisible = By.xpath("//*[@id='gp' and @class='gp']");
	public static By txt_CustomerID = By.xpath("//*[@data-ctcname='Customer_ID_T']");
	public static By txt_EquipmentID = By.xpath("//*[@data-ctcname='Equipment_ID_T']");
	public static By lookup_EquipmentID = By.xpath("//*[@data-ctcname='Equipment_ID_I']");
	public static By header_AddNewEquipment = By.xpath("//*[text()='Add new equipment' or text()='Found no dataset']");
	public static By btn_addEquipment = By.xpath("//*[@data-ctcname='Equipment_List_AddEquipmentNo_PopUp_I']");
	public static By btn_changeEquipment = By.xpath("//*[@data-ctcname='Equipment_List_ChangeEquipmentData_PopUp_I']");
	public void addEquipmentIDElevator(String ProductRelease, String equipment_ADDorChange) throws Exception{
		try {
			By checkbox_Equipment = By.xpath("//*[text()='"+equipmentid+"']/..//img");
			By radio_ProductRelease = By.xpath("//*[text()='Product release for "+ProductRelease+"']/..//img[2]");
			WebElement switchframe1 = gettingWebElement(firstFrame);
			switchtoFrame(switchframe1);
			WebElement switchframe2 = gettingWebElement(secondFrame);
			switchtoFrame(switchframe2);
			clickonButton(radio_ProductRelease);
			System.out.println("Release "+ProductRelease+" is Selected");
			WebElement findElement_Ok = gettingWebElement(btn_productrelease_OK); //*[@data-ctcwgtname='pbOK']
			scrollIntoView_Javascript(findElement_Ok);
//			System.out.println("scrolled to OK button");
			wait.until(ExpectedConditions.elementToBeClickable(findElement_Ok));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			click_Javascript(findElement_Ok); 
			System.out.println("Clicked on OK button");
			WebElement element_customerID = gettingWebElement(txt_CustomerID); //*[@data-ctcwgtname='CustomerID']
			element_customerID.clear(); 
//			System.out.println("CustomerID existing value cleared");
			WebElement element_EquipmentID = gettingWebElement(txt_EquipmentID); //*[@data-ctcwgtname='EquipmentID']
			element_EquipmentID.clear();
			element_EquipmentID.sendKeys(equipmentid); 
			System.out.println(equipmentid+" EquipmentID entered");
			wait.until(ExpectedConditions.elementToBeClickable(element_customerID));
			waitForElementToBeClickable(txt_CustomerID);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(txt_CustomerID);
			wait.until(ExpectedConditions.elementToBeClickable(element_EquipmentID));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_EquipmentID.click();
//			System.out.println("EquipmentID clicked");
			wait.until(ExpectedConditions.elementToBeClickable(lookup_EquipmentID)); 
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(lookup_EquipmentID);
			System.out.println("Equipment loader clicked");
			waitForVisibilityOfElementLocated(header_AddNewEquipment);
			waitForVisibilityOfElementLocated(checkbox_Equipment);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(checkbox_Equipment);
			System.out.println("checkbox clicked");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
				if(equipment_ADDorChange.equalsIgnoreCase("add")) {
					WebElement element_EquipmentIAddButton = gettingWebElement(btn_addEquipment); //*[@data-ctcwgtname='ic:Constant.!IcNew']
					wait.until(ExpectedConditions.elementToBeClickable(element_EquipmentIAddButton)); 
					element_EquipmentIAddButton.click();
					System.out.println("ADD button clicked");
					wait.until(ExpectedConditions.invisibilityOf(element_EquipmentIAddButton));
				} else if(equipment_ADDorChange.equalsIgnoreCase("change")) {
					WebElement element_EquipmentIChangeButton = gettingWebElement(btn_changeEquipment); //*[@data-ctcwgtname='ic:Constant.!IcDynamicForm']
					wait.until(ExpectedConditions.elementToBeClickable(element_EquipmentIChangeButton)); 
					element_EquipmentIChangeButton.click();
					System.out.println("Change button clicked");
					wait.until(ExpectedConditions.invisibilityOf(element_EquipmentIChangeButton));
				}
				wait.until(ExpectedConditions.elementToBeClickable(element_customerID));
				if(!element_customerID.getAttribute("value").isEmpty()) {
					element_customerID.clear();
					wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(element_customerID, "value")));
				}
				wait.until(ExpectedConditions.elementToBeClickable(element_customerID));
				element_customerID.sendKeys(customerid);
				element_EquipmentID.click();
				System.out.println("CustomerID: "+customerid+" entered");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Add EquipmentID in Elevator Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will select Project in projecttree 
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void selectProjectTree() throws Exception{
		try {
			By tree_project = By.xpath("//*[text()='" + equipmentid + "']/../..//div/div[text()='Project']");
			waitForVisibilityOfElementLocated(tree_project);
			WebElement Elementtoscroll = gettingWebElement(tree_project);
			scrollIntoView_Javascript(Elementtoscroll);
//			System.out.println("scroll UP to Project tree");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(tree_project);
			System.out.println("Project clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Select ProjectTree Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will check HandoverMS5date and update handoverdate to greater date 
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By date_DateHandoverMS5 = By.xpath("//*[@data-ctcwgtname='DateHandoverMS5_1']");
	public static By txt_DateHandoverMS5 = By.xpath("//*[@data-ctcname='MS5_HandOver_Complete_T']/input");
	public void checkHandOverDateIsGreaterThanInstallationDate() throws Exception{
		try {
			selectProjectTree();
			SimpleDateFormat input_dateformat = new SimpleDateFormat("dd/mm/yyyy");
			SimpleDateFormat output_dateformat = new SimpleDateFormat("dd/mm/yyyy");
			String MS5HODate_toChange = null;
			if(gettingWebElement(date_DateHandoverMS5).getCssValue("background-image").contains("0191f5aaded042c33180d357113667fbefc81b95")) {
				List<WebElement> elements_InstallationDate=driver.findElements(By.xpath("//div[text()='Project']/..//input"));
				String getDate = null;
				Date formatedDate = null;
					for(WebElement element:elements_InstallationDate) {
						if(!element.getAttribute("value").isEmpty()) {
							getDate=element.getAttribute("value");
							break;
						}
					}
					try {
						formatedDate=input_dateformat.parse(getDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					formatedDate=DateUtils.addDays(formatedDate, MS5HODatetoChange);
					MS5HODate_toChange=output_dateformat.format(formatedDate);
//					System.out.println("MS5HODatetoChange:"+MS5HODate_toChange);
			}
			System.out.println("Current MS5HO Date: "+gettingWebElement(txt_DateHandoverMS5).getAttribute("value"));
			WebElement element_MS5HODate=gettingWebElement(txt_DateHandoverMS5); //*[@data-ctcwgtname='DateHandoverMS5']/input
			element_MS5HODate.clear();
//			System.out.println("MS5HODate_Current Cleared");
			wait.until(ExpectedConditions.elementToBeClickable(element_MS5HODate));
			element_MS5HODate.sendKeys(MS5HODate_toChange);
				if(element_MS5HODate.getAttribute("value").isEmpty()) {
					element_MS5HODate.sendKeys(MS5HODate_toChange);
				}
			System.out.println("MS5HODate Changed to: "+MS5HODate_toChange);
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Check HandOverDate Is Greater than InstallationDate Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will select SalesOffice
	 *@param salesoffice: salesoffice to be selected
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By dd_salesOffice = By.xpath("//*[@data-ctcname='SalesOffice_T']/button");
	public void checkSalesOfficeisSelected(String salesoffice) throws Exception{
		try {
			By value_salesOffice = By.xpath("//div[text()='"+salesoffice+"']");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(dd_salesOffice); //*[@data-ctcwgtname='SalesOffice']/button
//			System.out.println("SalesOffice drop down clicked");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(value_salesOffice);
			System.out.println("SalesOffice value "+salesoffice+" Selected");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Check SalesOffice is Selected Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will select Supervisor
	 *@param supervisor_ResponsiblePreson: supervisor to be selected
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By dd_supervisor = By.xpath("//*[@data-ctcname='Supervisor_C']");
	public void selectSupervisor(String supervisor_ResponsiblePerson) throws Exception{
		try {
			By tree_equipmentID = By.xpath("(//*[text()='" + equipmentid + "'])[last()-1]");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement element_equipment = gettingWebElement(tree_equipmentID);
			click_Javascript(element_equipment);
//			System.out.println("Equipment clicked back");
			WebElement element_Supervisor = gettingWebElement(dd_supervisor); //*[@data-ctcwgtname='Supervisor_Select']
			wait.until(ExpectedConditions.elementToBeClickable(element_Supervisor)); 
			element_Supervisor.click();
//			System.out.println("Supervisor drop down clicked");
			String a[] = salesoffice.split(" ");
			String dd_SalesOffice=a[1].trim();
			if(salesoffice.contains("AU")) {
				dd_SalesOffice=a[0].trim();
			}
//		System.out.println("dd_SalesOffice: "+dd_SalesOffice);
//	        waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement element_ResponsiblePerson = driver.findElement(By.xpath("//div[text()='"+dd_SalesOffice+"']/..//div[text()='"+supervisor_ResponsiblePerson+"']"));
			click_Javascript(element_ResponsiblePerson);
			System.out.println(supervisor_ResponsiblePerson+" selected in Supervisor");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Select Supervisor Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will select equipmentinService
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By dd_equipmentinService = By.xpath("//*[@data-ctcname='Equipment_In_Service_C']/button");
	public static By radio_hydeaulicElevator = By.xpath("//*[@data-ctcname='Hydraulic_Elevator_Check_CB']");
	public void selectEquipmentInService() throws Exception{
		try {
			By value_equipmentinService = By.xpath("//div/div[text()='"+equipmentinService+"']");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement element_EquipmentInService = gettingWebElement(dd_equipmentinService);
			wait.until(ExpectedConditions.elementToBeClickable(element_EquipmentInService)); //*[@data-ctcwgtname='EquipmentInService']/button
			element_EquipmentInService.click();
//			System.out.println("EquipmentInService drop down clicked");
			waitForElementToBeClickable(value_equipmentinService);
			clickonButton(value_equipmentinService);
			System.out.println(equipmentinService+" is clicked in Equipment InService");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			if(!salesoffice.contains("AU")) {
				waitForElementToBeClickable(radio_hydeaulicElevator);
				clickonButton(radio_hydeaulicElevator);
				System.out.println("HydraulicElevCheck clicked");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Select Equipment InService Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will rightclick equipment and click on template and clear existing template name and enter new template name and click on shared template radoi button
	 *@param template: template to be selected
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By lnk_openTemplates = By.xpath("//div[text()='Open Templates']");
	public static By lnk_binaryTemplates = By.xpath("(//*[@data-ctcname='Template_Open_I'])[last()-1]");
	public static By txt_searchTemplate = By.xpath("//*[@data-ctcname='Template_Search_T']");
	public static By radio_sharedTemplate = By.xpath("//*[@data-ctcname='Shared_Template_AllOrg_R']");
	public void selectTemplateToBeUploaded(String template) throws Exception{
		try {
			By tree_equipmentID = By.xpath("//*[text()='" + equipmentid + "']");
			By lnk_template = By.xpath("(//*[text()='" + template + "'])[last()]");
			WebElement element_equipment = gettingWebElement(tree_equipmentID); //driver.findElement(By.xpath("//*[text()='" + equipmentid + "']"));
//			System.out.println("Equipment identified for RightClick");
			Actions rightclick_equipment = new Actions(driver).contextClick(element_equipment);
			rightclick_equipment.build().perform();
			System.out.println("Rightclick performed on Equipment");
			waitForElementToBeClickable(lnk_openTemplates);
			clickonButton(lnk_openTemplates);
			System.out.println("Open Templates clicked");
			waitForElementToBeClickable(lnk_binaryTemplates); //(//*[@data-ctcwgtname='ic_Constant__Closed'])[last()-1]
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(lnk_binaryTemplates);
//			System.out.println("Binary Templates: clicked");
			WebElement element_TemplateSearch = gettingWebElement(txt_searchTemplate);
			wait.until(ExpectedConditions.visibilityOf(element_TemplateSearch)); //*[@data-ctcwgtname='SearchString']
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_TemplateSearch.clear();
//			System.out.println("cleared search field value");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_TemplateSearch.sendKeys(template);
			System.out.println(template + " Entered in search");
			WebElement element_SharedTemplateAllOrg = gettingWebElement(radio_sharedTemplate); //*[@data-ctcwgtname='SearchShared_2']
			wait.until(ExpectedConditions.visibilityOf(element_SharedTemplateAllOrg)); 
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			click_Javascript(element_SharedTemplateAllOrg); //*[@data-ctcwgtname='SearchShared_2']/div[1]
			System.out.println("Shared templates radio button Clicked");
			waitForVisibilityOfElementLocated(lnk_template);
			WebElement doubleclick_elementTemplate = gettingWebElement(lnk_template);
			Actions doubleclick_template = new Actions(driver).doubleClick(doubleclick_elementTemplate);
			doubleclick_template.build().perform();
			System.out.println(template+" Template Clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Select Template to be Uploaded Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will check Tender consistency
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By consistencyCheckElement = By.xpath("//div/div[text()='Project']/../..//div/div");
	public void verifyTenderConsistency() throws Exception{
		try {
			List<WebElement> Elements_ConsistencyCheck_1 = gettingWebElementsfromList(consistencyCheckElement);
			for (WebElement Element : Elements_ConsistencyCheck_1) {
				if (Element.getCssValue("background-image").contains("0191f5aaded042c33180d357113667fbefc81b95")) {
					System.out.println("FAIL: Tender is NOT Consistency, value:"+Element.getCssValue("background-image"));
				} else if (Element.getCssValue("background-image").contains("cad9a93f6c879df1ed1373d2853634026ff3224f")) {
					System.out.println("PASS: Tender is Consistency, value:"+Element.getCssValue("background-image"));
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Verify Tender Consistency Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will get Tender number
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By text_tenderNumber = By.xpath("//*[@data-ctcname='Document_Number_T']");
	public void getTenderNo() throws Exception{
		String getTenderNo=gettingWebElement(text_tenderNumber).getAttribute("value");
		System.out.println("Tender # is :"+getTenderNo.trim());
	}
	
	/**
	 ***pricingIconClick method is the prerequisite for Pricing screen***
	 **Reuse method, it will select Discount & FirstMaintenance  
	 * @param discount: discount to be entered
	 * @param FirstMaintenance: FirstMaintenance to be entered
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void CheckTenderPriceAfterDiscountUpdate(String discount, String FirstMaintenance) throws Exception{
		selectingDiscount(discount);
		selectingFirstMaintenance(FirstMaintenance);
	}
	
	/**
	 ***pricingIconClick method is the prerequisite for Pricing screen***
	 **Reuse method, it will select TenderPrice & FirstMaintenance
	 * @param tenderPrice: tenderPrice to be entered
	 * @param FirstMaintenance: FirstMaintenance to be entered
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void VerifyDiscountByChangingTheTenderPrice(String tenderPrice, String FirstMaintenance) throws Exception{
		selectingTenderPrice(tenderPrice);
		selectingFirstMaintenance(FirstMaintenance);
	}
	/**
	 **Reuse method, it will Get TargetPrice from table
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void GetTargetPrice() throws Exception{
		checkingTargetPrice();
	}
	
	/**
	 **Reuse method, it will click on AdditionalDiscountIcon
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void verifyRegionalDiscountDisplayedCorrectly() throws Exception{
		clickonAdditionalDiscountIcon();
	}
	
	/**
	 **Reuse method, it will get regionaldiscount
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void verifyTargetPriceDisplayedCorrectly(String firstMaintenance) throws Exception{
		getRegionalDiscount(firstMaintenance);
		gettingWebElementsfromList(icon_additionalDiscount).get(3).click();
	}
	
	/**
	 **Reuse method, it will click on CurrenciesTab and get ConversionFactor value
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void VerifyCostCalculatedSuccessfully() throws Exception{
		if(salesoffice.contains("AU")) {
			clickonCurrenciesTab();
			getConversionFactor();
			Float convertedvalue_conversionFactor=Float.valueOf(conversionFactor);
			Float showtotal_Materialcosts= hm_DetailBreakdownData.get("Material costs");
			showtotal_Materialcosts=Float.valueOf(roundoff.format(showtotal_Materialcosts));
			Float showtotal_MaterialcostSLCurrency= hm_DetailBreakdownData.get("Material cost (SL Currency)");
			Float check_Materialcosts=showtotal_MaterialcostSLCurrency*convertedvalue_conversionFactor;
			check_Materialcosts=Float.valueOf(roundoff.format(check_Materialcosts));
			System.out.println("Calculate Materialcost:"+check_Materialcosts+" / "+"Actual Materialcost:"+showtotal_Materialcosts);
			String condition=null;
			if(!check_Materialcosts.equals(showtotal_Materialcosts)) {
				check_Materialcosts=check_Materialcosts-0.01f;
				if(check_Materialcosts.equals(showtotal_Materialcosts)) {
					condition="-0.01";
				} else {
				 check_Materialcosts=check_Materialcosts-0.02f;
				 condition="-0.02";
				}
			}
			System.out.println(condition+" Added in CalculatedMaterialCost hence CalculatedMaterialcost VS ActualMaterialcost is: "+roundoff.format(check_Materialcosts).equals(roundoff.format(showtotal_Materialcosts))+" ***");
		} else {
			System.out.println("*** Currencies Tab is applicable for Australia alone hence skipping TC#10 ***");
		}
	}
	
	/**
	 **Reuse method, it will 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void VerifyPriceCalculatedSuccessfully_TobecheckedinFrance() throws Exception{
		if(salesoffice.contains("AU")) {
		// not fesible at present
		} else {
			System.out.println("*** Currencies Tab is applicable for Australia alone hence skipping TC#10 ***");
		}
	}
	
	/**
	 **Reuse method, it will click on CurrenciesTab
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public static By tab_Currencies = By.xpath("//*[text()='Currencies']");
	public void clickonCurrenciesTab() throws Exception{
		waitForinvisibilityOfElementLocated(elementtoInvisible);
		WebElement scrollto_currenciesTab = gettingWebElement(tab_Currencies);
		scrollIntoView_Javascript(scrollto_currenciesTab);
//		System.out.println("scrolled up to CurrenciesTab");
		waitForinvisibilityOfElementLocated(elementtoInvisible);
		clickonButton(tab_Currencies);
		System.out.println("CurrenciesTab Clicked");
	}
	
	/**
	 **Reuse method, it will get ConversionFactor value
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public static By value_ConversionFactor = By.xpath("//*[text()='Conversion factor']/..//input");
	public String conversionFactor;
	public void getConversionFactor() throws Exception{
		waitForVisibilityOfElementLocated(value_ConversionFactor);
		WebElement element_ConversionFactor=gettingWebElement(value_ConversionFactor);
			conversionFactor=element_ConversionFactor.getAttribute("value");
			System.out.println("Conversion Factor:"+conversionFactor);
	}
	
	/**
	 **Reuse method, it will click on AdditionalDiscountIcon
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public static By icon_additionalDiscount = By.xpath("//*[@data-ctcwgtname='nPricingOverview']/div");
	public static By header_RegionalDiscount = By.xpath("//*[text()='Regional discount on component (%)']");
	public void clickonAdditionalDiscountIcon() throws Exception{
		wait.until(ExpectedConditions.visibilityOf(gettingWebElementsfromList(icon_additionalDiscount).get(3)));
		scrollIntoView_Javascript(gettingWebElementsfromList(icon_additionalDiscount).get(3));
		waitForinvisibilityOfElementLocated(elementtoInvisible);
		gettingWebElementsfromList(icon_additionalDiscount).get(3).click();
//		System.out.println(gettingWebElementsfromList(icon_additionalDiscount).get(3).getAttribute("id"));
		System.out.println("Additional Discount Icon CLICKED");
		waitForVisibilityOfElementLocated(header_RegionalDiscount);
//		System.out.println("header RegionalDiscount is visible");
	}
	
	/**
	 **Reuse method, it will get RegionalDiscount from table
	 *@param firstMaintenance: FirstMaintenance to be entered
	 *@throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public static By grid_RegionalDiscountValues = By.xpath("//*[text()='Regional discount on component (%)']/../../..//div[3]/div/div[1]/*");
	public HashMap<String, Float> hm_RegionalDiscountData;
	public void getRegionalDiscount(String firstMaintenance) throws Exception{
		waitForVisibilityOfElementLocated(grid_RegionalDiscountValues);
		List<WebElement> element_RegionalDiscountValues=gettingWebElementsfromList(grid_RegionalDiscountValues);
		List<String> ls_RegionalDiscountHeader=new LinkedList<>();
		List<Float> ls_RegionalDiscountValue=new LinkedList<>();
		hm_RegionalDiscountData = new HashMap<String, Float>();
			ls_RegionalDiscountHeader.add("TargetPrice_Base");
			ls_RegionalDiscountHeader.add("FundingSectorDiscount_Percent");
			ls_RegionalDiscountHeader.add("FundingSectorDiscount");
			ls_RegionalDiscountHeader.add("MarketSegmentDiscount_Percent");
			ls_RegionalDiscountHeader.add("MarketSegmentDiscount");
			ls_RegionalDiscountHeader.add("BillingPlanDiscount_Percent");
			ls_RegionalDiscountHeader.add("BillingPlanDiscount");
			ls_RegionalDiscountHeader.add("SalesOfficeMultiplier_Percent");
			ls_RegionalDiscountHeader.add("SalesOfficeMultiplier");
			ls_RegionalDiscountHeader.add("SellingValuePackageDiscount_Percent");
			ls_RegionalDiscountHeader.add("SellingValuePackageDiscount");
			ls_RegionalDiscountHeader.add("Regionaldiscountoncomponent_Percent");
			ls_RegionalDiscountHeader.add("Regionaldiscountoncomponent");
			ls_RegionalDiscountHeader.add("TargetPrice");
			ls_RegionalDiscountHeader.add("Discount");
			ls_RegionalDiscountHeader.add("TenderPrice");
			ls_RegionalDiscountHeader.add("FirstMaintenance");
		Float convertedvalue=null;
		for(WebElement element_RegionalDiscountValue:element_RegionalDiscountValues) {
			if (element_RegionalDiscountValue.getAttribute("value") == null ) {
				if(!element_RegionalDiscountValue.getText().isEmpty())	{
						if(!element_RegionalDiscountValue.getText().contains("Subtotal") && !element_RegionalDiscountValue.getText().contains("Project")) {
							String getvalue=element_RegionalDiscountValue.getText();
//							System.out.println("2*"+element_RegionalDiscountValue.getAttribute("id")+"="+getvalue);
							if(!getvalue.equals("0.00") && !getvalue.contains("%")) {
								getvalue = getvalue.replace(".", "");
							}
							getvalue=getvalue.replaceAll("[€ % h $]", "");
							getvalue = getvalue.replace(",", ".");
//							System.out.println("2**"+element_RegionalDiscountValue.getAttribute("id")+"="+getvalue);
							convertedvalue = Float.valueOf(getvalue);
							ls_RegionalDiscountValue.add(convertedvalue);
//							System.out.println("2***"+element_RegionalDiscountValue.getAttribute("id")+"="+convertedvalue);
						}
				}
			} else if(!element_RegionalDiscountValue.getAttribute("value").isEmpty() && element_RegionalDiscountValue.getAttribute("value")!=null)	{
					String getvalue=element_RegionalDiscountValue.getAttribute("value").replaceAll("[€ % h . $]", "");
					getvalue = getvalue.replace(",", ".");
//					System.out.println("3**"+element_RegionalDiscountValue.getAttribute("id")+"="+getvalue);
					convertedvalue = Float.valueOf(getvalue);
					ls_RegionalDiscountValue.add(convertedvalue);
//					System.out.println("3***"+element_RegionalDiscountValue.getAttribute("id")+"="+convertedvalue);
			}
		}
		int toIterate=16;
			if(!firstMaintenance.equals("0")){
				toIterate=toIterate+1;
			}
			for(int i=0; i<toIterate; i++) {
				hm_RegionalDiscountData.put(ls_RegionalDiscountHeader.get(i), ls_RegionalDiscountValue.get(i));
			}
		Float read_TargetPrice_Base=hm_RegionalDiscountData.get("TargetPrice_Base");
		/*Float read_FundingSectorDiscount_Percent=hm_data.get("FundingSectorDiscount_Percent");
		Float read_FundingSectorDiscount=hm_data.get("FundingSectorDiscount");
		Float read_MarketSegmentDiscount_Percent=hm_data.get("MarketSegmentDiscount_Percent");
		Float read_MarketSegmentDiscount=hm_data.get("MarketSegmentDiscount");
		Float read_BillingPlanDiscount_Percent=hm_data.get("BillingPlanDiscount_Percent");
		Float read_BillingPlanDiscount=hm_data.get("BillingPlanDiscount");
		Float read_SalesOfficeMultiplier_Percent=hm_data.get("SalesOfficeMultiplier_Percent");
		Float read_SalesOfficeMultiplier=hm_data.get("SalesOfficeMultiplier");
		Float read_SellingValuePackageDiscount_Percent=hm_data.get("SellingValuePackageDiscount_Percent");
		Float read_SellingValuePackageDiscount=hm_data.get("SellingValuePackageDiscount");*/
		Float read_Regionaldiscountoncomponent_Percent=hm_RegionalDiscountData.get("Regionaldiscountoncomponent_Percent");
		Float read_Regionaldiscountoncomponent=hm_RegionalDiscountData.get("Regionaldiscountoncomponent");
		Float read_TargetPrice=hm_RegionalDiscountData.get("TargetPrice");
		/*Float read_Discount=hm_data.get("Discount");
		Float read_TenderPrice=hm_data.get("TenderPrice");
		Float read_FirstMaintenance=hm_data.get("FirstMaintenance");*/	
		Float final_Regionaldiscountoncomponent=read_TargetPrice_Base-(read_TargetPrice_Base*(regionalDiscount/100));
		Float final_TenderPrice=read_TargetPrice_Base-final_Regionaldiscountoncomponent;
		System.out.println("is read_Regionaldiscountoncomponent_Percent VS regionalDiscount equal:"+read_Regionaldiscountoncomponent_Percent.equals(regionalDiscount)+" *** / read_Regionaldiscountoncomponent_Percent:"+read_Regionaldiscountoncomponent_Percent+" / regionalDiscount:"+regionalDiscount);
		System.out.println("is final_Regionaldiscountoncomponent VS read_Regionaldiscountoncomponent equal:"+final_Regionaldiscountoncomponent.equals(read_Regionaldiscountoncomponent)+" *** / final_Regionaldiscountoncomponent:"+final_Regionaldiscountoncomponent+" / read_Regionaldiscountoncomponent:"+read_Regionaldiscountoncomponent);
		System.out.println("is final_TenderPrice VS read_TargetPrice equal:"+final_TenderPrice.equals(read_TargetPrice)+" *** / final_TenderPrice:"+final_TenderPrice+" / read_TargetPrice:"+read_TargetPrice);
	}
	
	/**
	 **Reuse method, it will click Detail Breakdown Tab and checks ITE factor, Labour rate, Reference hours
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By tab_detailBreakDown = By.xpath("//*[text()='Detail breakdown']");
	public static By dd_selectProject = By.xpath("//*[@data-ctcwgtname='nCustomComboboxProductSelection']");
	public static By value_ropes = By.xpath("//*[text()='Ropes']");
	public static By lnk_ShowTotalCostCalculationDetails = By.xpath("//*[@src='SMG?i=acdda3ea032315878f95d47164849ea79f364ad3&w=16&h=16']");
	public static By header_ITEfactor = By.xpath("//*[text()='ITE factor']");
	public static By gridvalues_SubTotal = By.xpath("//*[text()='Subtotal']/..//*");
	public HashMap<String, Float> hm_DetailBreakdownData;
	public void validateDetailBreakdownTab() throws Exception{
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement scrollto_DetailbreakdownTab = gettingWebElement(tab_detailBreakDown);
			scrollIntoView_Javascript(scrollto_DetailbreakdownTab);
//			System.out.println("scrolled up to DetailbreakdownTab");
			waitForVisibilityOfElementLocated(tab_detailBreakDown);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(tab_detailBreakDown);
			System.out.println("Detailed breakdown Clicked");
			waitForVisibilityOfElementLocated(dd_selectProject);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(dd_selectProject);
//			System.out.println("Dropdown in DetailedbreakdownTAB Clicked");
			WebElement element_Ropes = gettingWebElement(value_ropes);
			waitForVisibilityOfElementLocated(value_ropes);
			waitForElementToBeClickable(value_ropes);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			wait.until(ExpectedConditions.visibilityOf(element_Ropes));
			click_Javascript(element_Ropes);
			System.out.println("ROPES CLICKED");
			waitForinvisibilityOfElementLocated(value_ropes);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div")).get(4).click();
			System.out.println("Show TotalCost Calculation Details CLICKED");
			waitForVisibilityOfElementLocated(header_ITEfactor);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			List<WebElement> Elements_showtotalcostFirstRow = gettingWebElementsfromList(gridvalues_SubTotal);
			List<String> ls_TotalCostHeader=new LinkedList<>();
			List<Float> ls_TotalCostValue=new LinkedList<>();
			hm_DetailBreakdownData = new HashMap<String, Float>();
					ls_TotalCostHeader.add("Target Price");
					ls_TotalCostHeader.add("Material costs");
					ls_TotalCostHeader.add("Material cost (SL Currency)");
					ls_TotalCostHeader.add("Reference hours");
					ls_TotalCostHeader.add("ITE factor");
					ls_TotalCostHeader.add("Installation Hours");
					ls_TotalCostHeader.add("Labour rate");
					ls_TotalCostHeader.add("Labor Costs");
					ls_TotalCostHeader.add("Contigency");
					ls_TotalCostHeader.add("Overhead Recovery");
					ls_TotalCostHeader.add("Full Costs");
					ls_TotalCostHeader.add("Total Cost");
					ls_TotalCostHeader.add("Ratio of Labor");
					ls_TotalCostHeader.add("Tender Price");
			Float convertedvalue=null;
			for(WebElement Element_showtotalcostFirstRow:Elements_showtotalcostFirstRow) {
				if (Element_showtotalcostFirstRow.getAttribute("value") == null ) {
					if(!Element_showtotalcostFirstRow.getText().isEmpty())	{
//			System.out.println("getText:"+Element_showtotalcostFirstRow.getText());
							if(!Element_showtotalcostFirstRow.getText().contains("Subtotal")) {
								String getvalue=Element_showtotalcostFirstRow.getText().replaceAll("[€ % h $]", "");
								getvalue = getvalue.replace(",", ".");
								convertedvalue = Float.valueOf(getvalue);
								ls_TotalCostValue.add(convertedvalue);
							}
					}
				} else if(!Element_showtotalcostFirstRow.getAttribute("value").isEmpty() && Element_showtotalcostFirstRow.getAttribute("value")!=null)	{
//			System.out.println("getAttribute:"+Element_showtotalcostFirstRow.getAttribute("value"));
					String getvalue = null;
					if(!salesoffice.contains("AU")) {
						getvalue=Element_showtotalcostFirstRow.getAttribute("value").replaceAll("[€ % h .]", "");
						getvalue = getvalue.replace(",", ".");
					} else {
						getvalue=Element_showtotalcostFirstRow.getAttribute("value").replaceAll("[€ % h $]", "");
						getvalue = getvalue.replace(",", "");
					}
						convertedvalue = Float.valueOf(getvalue);
						ls_TotalCostValue.add(convertedvalue);
				}
			}
			for(int i=0; i<14; i++) {
				hm_DetailBreakdownData.put(ls_TotalCostHeader.get(i), ls_TotalCostValue.get(i));
			}
			/*Float showtotal_TargetPrice= hm_DetailBreakdownData.get("Target Price");
			Float showtotal_Materialcosts= hm_DetailBreakdownData.get("Material costs");
			Float showtotal_MaterialcostSLCurrency= hm_DetailBreakdownData.get("Material cost (SL Currency)");
		System.out.println("====="+showtotal_TargetPrice+"/"+showtotal_Materialcosts+"/"+showtotal_MaterialcostSLCurrency);*/
			Float showtotal_Referencehours= hm_DetailBreakdownData.get("Reference hours");
			Float showtotal_ITEfactor= hm_DetailBreakdownData.get("ITE factor");
			Float showtotal_InstallationHours= hm_DetailBreakdownData.get("Installation Hours");
			Float showtotal_Labourrate= hm_DetailBreakdownData.get("Labour rate");
			Float showtotal_LaborCosts= hm_DetailBreakdownData.get("Labor Costs");
			/*Float showtotal_Contigency= hm_DetailBreakdownData.get("Contigency");
			Float showtotal_OverheadRecovery= hm_DetailBreakdownData.get("Overhead Recovery");
			Float showtotal_FullCosts= hm_DetailBreakdownData.get("Full Costs");
			Float showtotal_TotalCost= hm_DetailBreakdownData.get("Total Cost");
			Float showtotal_RatioofLabor= hm_DetailBreakdownData.get("Ratio of Labor");
			Float showtotal_TenderPrice= hm_DetailBreakdownData.get("Tender Price");*/
			Float check_showtotal_Referencehours= showtotal_InstallationHours/showtotal_ITEfactor;
			Float check_showtotal_Labourrate= showtotal_LaborCosts/showtotal_InstallationHours;
			/*System.out.println("is_showtotal_ITEfactor: "+check_showtotal_ITEfactor+"/"+showtotal_ITEfactor);
			System.out.println("is_showtotal_LaborCosts "+check_showtotal_Labourrate+"/"+showtotal_Labourrate);
			System.out.println("is_showtotal_Referencehours "+check_showtotal_Referencehours+"/"+showtotal_Referencehours);*/
			Boolean is_showtotal_ITEfactor = roundoff.format(check_showtotal_ITEfactor).equals(roundoff.format(showtotal_ITEfactor));
			Boolean is_showtotal_Referencehours= roundoff.format(check_showtotal_Referencehours).equals(roundoff.format(showtotal_Referencehours));
			Boolean is_showtotal_Labourrate= roundoff.format(check_showtotal_Labourrate).equals(roundoff.format(showtotal_Labourrate));
			System.out.println("check_showtotal_ITEfactor:"+roundoff.format(check_showtotal_ITEfactor)+" / showtotal_ITEfactor:"+roundoff.format(showtotal_ITEfactor));
			System.out.println("check_showtotal_Referencehours:"+roundoff.format(check_showtotal_Referencehours)+" / showtotal_Referencehours:"+roundoff.format(showtotal_Referencehours));
			System.out.println("check_showtotal_Labourrate:"+roundoff.format(check_showtotal_Labourrate)+" / showtotal_Labourrate:"+roundoff.format(showtotal_Labourrate));
			System.out.println("*** is_showtotal_ITEfactor: "+is_showtotal_ITEfactor+" *** / is_showtotal_Labourrate: "+is_showtotal_Labourrate+" *** / is_showtotal_Referencehours: "+is_showtotal_Referencehours+" ***");
			/*for (Entry<String, Float> entry : hm_DetailBreakdownData.entrySet()) {
				String key = entry.getKey();
				Float ValueStored = entry.getValue();
				System.out.println("key:"+key+" /ValuetoStore:"+ValueStored);
			}*/
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div")).get(4).click();
//			System.out.println("elementShowTotalCostCalculationDetail_CLICKED*****BACK="+driver.findElements(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div")).get(4).getAttribute("id"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
//			waitForinvisibilityOfElementLocated(header_ITEfactor);
//			System.out.println("header_ITEfactor disabled");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Validate Detail Breakdown Tab Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click ToConfiguration icon and select Project from projecttree and select SalesOffice and click on Pricing Icon
 	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By lnk_toConfiguration = By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[1]/img");
	public void gotoConfigurationPageandChangeTheSalesOffice(String changeSalesOffice) throws Exception{
		try {
			WebElement element_toConfiguration = gettingWebElement(lnk_toConfiguration);
			scrollIntoView_Javascript(element_toConfiguration);
			wait.until(ExpectedConditions.elementToBeClickable(element_toConfiguration));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			List<WebElement> element_toConfigurations = gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div/img"));
			int count=0;
			for(WebElement elementtoConfiguration:element_toConfigurations) {
//			System.out.println(elementToWord.getAttribute("id"));
				count++;
				if(count==1) {
					scrollIntoView_Javascript(elementtoConfiguration);
					wait.until(ExpectedConditions.visibilityOf(elementtoConfiguration));
					elementtoConfiguration.click();
					break;
				}
			}
			System.out.println("ToConfiguration icon clicked");
			selectProjectTree();
			checkSalesOfficeisSelected(changeSalesOffice);
			pricingIconClick();
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Goto Configuration Page and Change SalesOffice Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click ToWord icon and click on ModularTenderDOC.doc
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By lnk_toWord = By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[5]/img");
	public static By label_KONELogo = By.xpath("//*[text()='Print with KONE Logo']");
	public static By lnk_templateDOC = By.xpath("//*[contains(text(),'_Modular_Tender_Template.doc') or text()='Tender Letter MOD TRB']");
	public void goToDocumentsTabandClickTheTender() throws Exception{
		try {
			WebElement element_ToWord = gettingWebElement(lnk_toWord);
			scrollIntoView_Javascript(element_ToWord);
			wait.until(ExpectedConditions.elementToBeClickable(element_ToWord));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			List<WebElement> element_ToWords = gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div/img"));
			int count=0;
			for(WebElement elementToWord:element_ToWords) {
//				System.out.println(elementToWord.getAttribute("id"));
				count++;
				if(count==4) {
					elementToWord.click();
					break;
				}
			}
			System.out.println("ToWord icon clicked");
			waitForVisibilityOfElementLocated(label_KONELogo);
//			System.out.println("Waiting till -Print with KONE Logo- displays");
			WebElement element_ModularTenderDOC = gettingWebElement(lnk_templateDOC);
			wait.until(ExpectedConditions.elementToBeClickable(element_ModularTenderDOC));
			scrollIntoView_Javascript(element_ModularTenderDOC);
			wait.until(ExpectedConditions.visibilityOf(element_ModularTenderDOC));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_ModularTenderDOC.click();
//			System.out.println("Modular_Tender_Template.doc clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Goto Documents tab and Click Tender Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click printOut icon in toword screen
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By icon_printOut = By.xpath("//*[@data-ctcname='PrintOut_I']/div"); //*[@data-ctcname='PrintOut_I']/div
	public static By text_freezePrint = By.xpath("//*[contains(text(),'Do you want to freeze printed version?')]");
	public static By text_infotoDocServer = By.xpath("//*[contains(text(),'Information have been sent to the document server')]");
	public static By btn_infotoDocServer = By.xpath("//button[text()='OK']");
	public void verifySuccessfulMessageDisplayed() throws Exception{
		try {
			By btn_freezePrint = By.xpath("//button[text()='"+FreezePrintedVersion+"']");
			WebElement element_PrintwithKONELogo = gettingWebElement(label_KONELogo);
			scrollIntoView_Javascript(element_PrintwithKONELogo);
			wait.until(ExpectedConditions.visibilityOf(element_PrintwithKONELogo));
			WebElement element_PrintOut = gettingWebElement(icon_printOut); //*[@data-ctcwgtname='PrintOut_c']/div  //*[@data-ctcname='PrintOut_I']/div
			wait.until(ExpectedConditions.visibilityOf(element_PrintOut));
			wait.until(ExpectedConditions.stalenessOf(element_PrintOut));
			retryingClick(icon_printOut);
			System.out.println("Create PrintOut icon clicked");
			if(!salesoffice.contains("AU")) {
				waitForVisibilityOfElementLocated(text_freezePrint);
				waitForElementToBeClickable(btn_freezePrint);
				clickonButton(btn_freezePrint);
				System.out.println(FreezePrintedVersion+" clicked in Do you want to freeze printed version?");
			}
			waitForVisibilityOfElementLocated(text_infotoDocServer);
			WebElement element_OkbuttoninInformationtodocumentserver = gettingWebElement(btn_infotoDocServer);
			wait.until(ExpectedConditions.elementToBeClickable(element_OkbuttoninInformationtodocumentserver));
			element_OkbuttoninInformationtodocumentserver.click();
//			System.out.println("OK clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Verify Successful Message Displayed Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click ClickSave and CloseButton in KTOC-TRB
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By icon_saveandClose = By.xpath("//*[@data-ctcwgtname='ToolBar' and @data-ctctype='Toolbar']/div[2]");
	public static By txt_stageProbabilityDescription = By.xpath("//*[text()='Description:']/..//input");
	public static By dd_stage = By.xpath("//*[@data-ctcwgtname='_TenderVersion.Stage__c']/button");
	public static By txt_probability = By.xpath("//input[@data-ctcwgtname='_TenderVersion.Probability__c']");
	public static By btn_stageProbability = By.xpath("//*[text()='OK']");
	public void clickSaveandCloseButton(String StageProbability_Description, String StageProbability_probability) throws Exception{
		try {
			By btn_saveandClose = By.xpath("//*[@data-ctcwgtname='pb"+SaveandClose+"']");
			By value_stage = By.xpath("//div[text()='"+StageProbability_Stage+"']");
			WebElement element_SaveandClose = gettingWebElement(icon_saveandClose);
			scrollIntoView_Javascript(element_SaveandClose);
			wait.until(ExpectedConditions.visibilityOf(element_SaveandClose));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_SaveandClose.click();
			System.out.println("SaveandClose icon CLICKED");
			waitForVisibilityOfElementLocated(btn_saveandClose);
			click_Javascript(gettingWebElement(btn_saveandClose));
			System.out.println(SaveandClose+" CLICKED in SaveandClose");
			enteringValues(txt_stageProbabilityDescription, StageProbability_Description);
			System.out.println(StageProbability_Description+" entered in Description field in stage / Probability window");
			clickonButton(dd_stage);
//			System.out.println("Clicked on Stage dropdown");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(value_stage);
//			System.out.println(StageProbability_Stage+" Clicked in Stage dropdown");
			WebElement element_enderVersionProbability = gettingWebElement(txt_probability);
			element_enderVersionProbability.clear();
//			System.out.println("Probability: value Cleared");
			element_enderVersionProbability.sendKeys(StageProbability_probability);
//			System.out.println("Value entered in Probability field");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(btn_stageProbability);
//			System.out.println("OK Button Clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Click Save&Close button Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click Configurator button and navigate back to frames
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By frameforwait = By.tagName("iframe");
	public static By btn_configurator = By.xpath("//*[@title='Configurator']");
	public static By btn_NoButtoninNewVersionProduct = By.xpath("//button[text()='No']");
	public Float Final_SalesPrice;
	public void getSalesPriceFromSalesForce() throws Exception{
		try {
			wait.until(elementToBeClickableInFrame(frameforwait, btn_configurator));
			switchtoDefaultContentFrame();
			switchtoFrame(gettingWebElement(frameforwait));
			WebElement element_Configurator = gettingWebElement(btn_configurator);
			wait.until(ExpectedConditions.visibilityOf(element_Configurator));
//			System.out.println("Configurator button is visible");
			scrollIntoView_Javascript(element_Configurator);
			By header_totalSalesPrice=By.xpath("//*[text()='Total Sales Price']/../../../..//tr/td/*"); 
			List<WebElement>element_totalSalesPrices=gettingWebElementsfromList(header_totalSalesPrice);
			for(WebElement element_totalSalesPrice:element_totalSalesPrices) {
				if(!element_totalSalesPrice.getText().isEmpty()) {
					if(element_totalSalesPrice.getText().contains("(")){
						String totalsalesprice=element_totalSalesPrice.getText();
						System.out.println("Salesforce TenderPrice:"+totalsalesprice);
						String a[] = totalsalesprice.split("\\(");
						totalsalesprice=a[0].trim();
						String b[] = totalsalesprice.split(" ");
						totalsalesprice=b[1].trim();
						totalsalesprice=totalsalesprice.replaceAll(",", "");
						Final_SalesPrice=Float.valueOf(totalsalesprice);
						System.out.println("Tender Price:"+TenderPrice+" / Final_SalesPrice:"+Final_SalesPrice);
						System.out.println("is TRB TenderPrice VS Salesforce TenderPrice equal:"+TenderPrice.equals(Final_SalesPrice)+" ***");
						break;
					}
				}
			}
			click_Javascript(element_Configurator);
			System.out.println("Configurator Clicked");
			List<WebElement> frames=gettingWebElementsfromList(frameforwait);
			WebElement secondFrame=null;
				for(WebElement frame:frames) {
					if(frame.getAttribute("id").equals("clientTarget"));{
						secondFrame=frame;
//						System.out.println("secondFrame:"+secondFrame.getAttribute("id"));
						break;
					}
				}
			switchtoFrame(secondFrame);
//			System.out.println("Switched to secondFrame");
			WebElement element_NoButtoninNewVersionProduct = gettingWebElement(btn_NoButtoninNewVersionProduct);
			wait.until(ExpectedConditions.elementToBeClickable(element_NoButtoninNewVersionProduct));
			element_NoButtoninNewVersionProduct.click();
			System.out.println("NO clicked in New Version is available for this product");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Get SalesPrice from SalesForce Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click HandShakeIcon
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By icon_pricing = By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[3]/img");
	public static By icon_HandShake = By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[9]/img");
	public void handShake() throws Exception{
		try {
			waitForVisibilityOfElementLocated(icon_pricing);
//			System.out.println("pricingicon's visible");
			verifyTenderConsistency();
			WebElement element_HandShakeicon=gettingWebElement(icon_HandShake);
			wait.until(ExpectedConditions.visibilityOf(element_HandShakeicon));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_HandShakeicon.click();
			/*List<WebElement> element_HandShakeicons = gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div/img"));
			int count=0;
			for(WebElement elementHandShakeicon:element_HandShakeicons) {
//				System.out.println(elementHandShakeicon.getAttribute("id"));
				count++;
				if(count==5) {
					elementHandShakeicon.click();
					break;
				}
			}*/
			System.out.println("isHandShakeiconisEnabled:"+element_HandShakeicon.isEnabled()+" HandShakeicon CLICKED");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("HandShake Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click PriceIcon
 	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	//***reuse pricingIconClick method is the prerequisite for Pricing screen***
	public void pricingIconClick() throws Exception{
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			waitForElementToBeClickable(icon_pricing);
			WebElement element_pricingicon=gettingWebElement(icon_pricing);
			scrollIntoView_Javascript(element_pricingicon);
//			System.out.println("scrolled to pricingicon");
			element_pricingicon.click();
			/*List<WebElement> element_pricingicons = gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div/img"));
			int count=0;
			for(WebElement elementpricingicon:element_pricingicons) {
//				System.out.println(elementpricingicon.getAttribute("id"));
				count++;
				if(count==2) {
					elementpricingicon.click();
					break;
				}
			}*/
//			System.out.println("pricing icon Clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("PricingIcon Click Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will select FirstMaintenance dropdown value in PriceOverview tab
 	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public static By dd_firstMaintenance = By.xpath("//*[@data-ctcname='FirstMaintenance_D']/button");
	public static By header_firstMaintenance = By.xpath("//*[text()='First Maintenance']");
	public void selectingFirstMaintenance(String FirstMaintenance) throws Exception{
		try {
			By value_firstMaintenance = null;
			if(!salesoffice.contains("AU")) {
				value_firstMaintenance = By.xpath("//div[starts-with(text(),'" + FirstMaintenance + " Mois de gratuité ')]");
			} else {
				if(!FirstMaintenance.equals("0")) {
					value_firstMaintenance = By.xpath("//div[text()='12 Months Free Maintenance']");
				} else {
					value_firstMaintenance = By.xpath("//div[text()='No Free Maintenance']");
				}
			}
			waitForinvisibilityOfElementLocated(elementtoInvisible);
//			String tenderPriceBefore =isTargetupdatedafterFirstMaintenance();
			WebElement scrollto_Firstmaintenance = gettingWebElement(dd_firstMaintenance); //*[@data-ctcwgtname='FreeMaintenance']/buttonS
			scrollIntoView_Javascript(scrollto_Firstmaintenance);
//			System.out.println("scrolled to Firstmaintenance");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			click_Javascript(gettingWebElement(dd_firstMaintenance));
//			System.out.println("First maintenance: Clicked");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(value_firstMaintenance);
			System.out.println("First maintenance: " + FirstMaintenance + " Clicked");
			if(!FirstMaintenance.equals("0")) {
				waitForVisibilityOfElementLocated(header_firstMaintenance);
			} else if(salesoffice.contains("AU") && FirstMaintenance.equals("0")) {
				List<WebElement>headers_priceOverview=gettingWebElementsfromList(By.xpath("//*[text()='Discount']/../..//div/div"));
				for(WebElement header_priceOverview:headers_priceOverview) {
					if(!header_priceOverview.getText().isEmpty()) {
//						System.out.println("header_priceOverview="+header_priceOverview.getText());
						if(header_priceOverview.getText().equals("First Maintenance")) {
							wait.until(ExpectedConditions.invisibilityOf(header_priceOverview));
							break;
						}
					}
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Selecting FirstMaintenance Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will get Discount value from the table in PriceOverview tab
	 * @return: Discount value will be returned
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public String isTargetupdatedafterFirstMaintenance() throws Exception{
		List<WebElement> Elements_PriceOverview = gettingWebElementsfromList(grid_allValues);
		String element_readTenderPrice = null;
			for (WebElement Element : Elements_PriceOverview) {
//				System.out.println(Element.getAttribute("id"));
				if (!Element.getText().isEmpty() || Element.getAttribute("value") != null) {
					if (Element.getAttribute("value") == null) {
						if (!Element.getText().contains("Project")) {
							if (!Element.getText().contains("%")) {
								element_readTenderPrice = Element.getText();
//								System.out.println("****TenderPrice:" + element_readTenderPrice);
							} 
						}
					}
				}
			}
			return element_readTenderPrice;
	}
	
	/**
	 **Reuse method, it will change Discount in PriceOverview tab
	 *@author CON_SVIJAY02
	 */
	public static By btn_discountPencil = By.xpath("//*[@data-ctcname='Discount_Pencil_I']");
	public static By grid_discount = By.xpath("//*[text()='Project']/..//div[contains(text(),' %')]");
	public static By txt_discount = By.xpath("//*[@data-ctcname='New_Dicount_T']");
	public static By btn_discountOK = By.xpath("//*[@data-ctcname='Discount_Ok_B']");
	public void selectingDiscount(String discount) throws Exception{
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement scrollto_TMP_Discount = gettingWebElement(btn_discountPencil);
			wait.until(ExpectedConditions.visibilityOf(scrollto_TMP_Discount)); //*[@data-ctcwgtname='icGraphic']
			scrollIntoView_Javascript(scrollto_TMP_Discount);
//			System.out.println("Scrolled UP to Discount");
			wait.until(ExpectedConditions.visibilityOf(scrollto_TMP_Discount));
			WebElement doubleclick_elementDiscount = gettingWebElement(grid_discount);
			Actions doubleclick_discount = new Actions(driver).doubleClick(doubleclick_elementDiscount);
			doubleclick_discount.build().perform();
//			System.out.println("Double Clicked on discount");
			WebElement scrollto_NewDicount = gettingWebElement(txt_discount); //*[@data-ctcwgtname='TMP_Discount']
			wait.until(ExpectedConditions.visibilityOf(scrollto_NewDicount)); 
			scrollto_NewDicount.clear();
//			System.out.println("TMP_Discount cleared");
			scrollto_NewDicount.sendKeys(discount);
			System.out.println("Discount entered as: " + discount);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(btn_discountOK); //*[@data-ctcwgtname='pbOK']
//			System.out.println("Discount OK clicked");	
			By grid_discountisApplied = By.xpath("//*[text()='Project']/..//div[text()='"+discount+".00 %']");
//			System.out.println("grid_discountisApplied:"+gettingWebElement(grid_discountisApplied).getAttribute("id"));
			waitForpresenceOfElementLocated(grid_discountisApplied);
//			System.out.println("Discount applied to GRID");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Selecting Discount Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will change TenderPrice in PriceOverview tab
	 *@author CON_SVIJAY02
	 */
	public static By tab_priceOverview = By.xpath("//*[text()='Price overview']");
	public static By grid_tenderPrice = By.xpath("//*[text()='Project']/..//div");
	public static By txt_tenderPrice = By.xpath("//*[@data-ctcname='TenderPrice_T']");
	public static By btn_tenderPriceOK = By.xpath("//*[@data-ctcname='TenderPrice_Ok_B']");
	public void selectingTenderPrice(String tenderPrice) throws Exception{
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement scrollto_priceOverview = gettingWebElement(tab_priceOverview);
			wait.until(ExpectedConditions.visibilityOf(scrollto_priceOverview)); 
			scrollIntoView_Javascript(scrollto_priceOverview);
//		System.out.println("Scrolled UP to TenderPrice_T");
			wait.until(ExpectedConditions.visibilityOf(scrollto_priceOverview));
			List<WebElement> elements=driver.findElements(grid_tenderPrice);
			WebElement doubleclick_elementselectingTenderPrice = null;
			for(WebElement element:elements) {
//		System.out.println(element.getText());
				if(element.getText().contains("€ ") || element.getText().contains("$")) {
					doubleclick_elementselectingTenderPrice=element;
//		System.out.println("if:"+doubleclick_elementselectingTenderPrice.getAttribute("id"));
					break;
				}
			}
			wait.until(ExpectedConditions.visibilityOf(doubleclick_elementselectingTenderPrice));
			wait.until(ExpectedConditions.elementToBeClickable(doubleclick_elementselectingTenderPrice));
			scrollIntoView_Javascript(doubleclick_elementselectingTenderPrice);
			wait.until(ExpectedConditions.visibilityOf(doubleclick_elementselectingTenderPrice));
			Actions doubleclick_targetPrice = new Actions(driver).doubleClick(doubleclick_elementselectingTenderPrice);
			doubleclick_targetPrice.build().perform();
//		System.out.println("Double Clicked on selectingTargetPrice");
			WebElement scrollto_TenderPrice_T = gettingWebElement(txt_tenderPrice); //*[@data-ctcwgtname='TMP_Discount']
			wait.until(ExpectedConditions.visibilityOf(scrollto_TenderPrice_T)); 
			scrollto_TenderPrice_T.sendKeys(Keys.chord((Keys.CONTROL+"a")));
			scrollto_TenderPrice_T.sendKeys((Keys.DELETE));
//		System.out.println("TenderPrice_T cleared");
			scrollto_TenderPrice_T.sendKeys(tenderPrice);
		System.out.println("TenderPrice entered as: " + tenderPrice);
			clickonButton(btn_tenderPriceOK); 
//		System.out.println("TenderPrice_T OK clicked");	
			istenderPrice=true;
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Selecting TenderPrice Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will check TenderPrice & Discount in PriceOverview tab
	 *@author CON_SVIJAY02
	 */
	public static By grid_allValues = By.xpath("//*[text()='Project']/..//*");
	public static Float TenderPrice;
	public void checkingTargetPrice() throws Exception{
		try {
			waitForVisibilityOfElementLocated(grid_discount);
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Project']/..//div[text()='" + discount + ".00 %']")));
			List<WebElement> Elements_PriceOverview = gettingWebElementsfromList(grid_allValues);
			Float arrary[] = new Float[2];
			Float TargetPrice, Firstmaintenance, Discount = null;
			for (WebElement Element : Elements_PriceOverview) {
//		System.out.println(Element.getAttribute("id"));
				if (!Element.getText().isEmpty() || Element.getAttribute("value") != null) {
					if (Element.getAttribute("value") == null) {
						if (!Element.getText().contains("Project")) {
							if (!Element.getText().contains("%")) {
								String element_readTenderPrice = Element.getText().replaceAll("[€  $]", "");
								if(!salesoffice.contains("AU")) {
									element_readTenderPrice = element_readTenderPrice.replace(".", "");
									element_readTenderPrice = element_readTenderPrice.replace(",", ".");
								}  else {
									element_readTenderPrice = element_readTenderPrice.replace(",", "");
								}
								TenderPrice = Float.valueOf(element_readTenderPrice);
//				System.out.println("****TenderPrice==>" + TenderPrice);
							} else {
								String element_readDiscount = Element.getText().replaceAll("[% ]", "");
								Discount = Float.valueOf(element_readDiscount);
//				System.out.println("****Discount:" + Discount);
							}
						}
					} else {
//	        	System.out.println("****Attribute==>"+Element.getAttribute("value")+"/id==>"+Element.getAttribute("id"));
						String element_read = Element.getAttribute("value").replaceAll("[€  $]", "");
						if(!salesoffice.contains("AU")) {
							element_read = element_read.replace(".", "");
							element_read = element_read.replace(",", ".");
						} else {
							element_read = element_read.replace(",", "");
						}
						Float converted = Float.valueOf(element_read);
//	        		System.out.println("converted: "+converted);
						if (arrary[0] == null) {
							arrary[0] = converted;
							/*System.out.println("ARRAY 1");
							System.out.println(arrary[0] = converted);*/
						} else {
							arrary[1] = converted;
							/*System.out.println("ARRAY 2");
							System.out.println(arrary[1] = converted);*/
						}
					}
				}
			}
			if(withoutFirstMaintenance.equals("0")) {
				arrary[1] = 0f;
			}
			if (arrary[0] > arrary[1]) {
				TargetPrice = arrary[0];
				Firstmaintenance = arrary[1];
//				System.out.println("CONDTION_1 TargetPrice: "+TargetPrice+", Firstmaintenance:"+Firstmaintenance);
			} else {
				TargetPrice = arrary[1];
				Firstmaintenance = arrary[0];
			System.out.println("CONDTION_2 TargetPrice: "+TargetPrice+", Firstmaintenance:"+Firstmaintenance);
			}
			if(istenderPrice) {
				Float DiscountFinal = ((((TargetPrice-Firstmaintenance)-(TenderPrice-Firstmaintenance))/(TargetPrice-Firstmaintenance))*100);
				System.out.println("DiscountFinal:"+ roundoff.format(DiscountFinal)+" / Current Discount:"+Discount);
				System.out.println("*** is FinalDiscount VS ApplicationDiscount Equal:"+roundoff.format(DiscountFinal).equals(roundoff.format(Discount))+" ***");
			} else {
				/*Float TargetMinusMaintenance = TargetPrice - Firstmaintenance;
				Float TargetplusDiscount = TargetMinusMaintenance - (TargetMinusMaintenance * (Discount / 100));
				Float TargetPriceFinal = TargetplusDiscount + Firstmaintenance;*/
				Float TenderPriceFinal = ((TargetPrice-Firstmaintenance)-((TargetPrice-Firstmaintenance)*(Discount/100))+Firstmaintenance);
				System.out.println("*** TenderPriceFinal:" + roundoff.format(TenderPriceFinal)+" / CurrentTenderPrice:"+TenderPrice+" ***");
				System.out.println("is TenderPriceFinal VS ApplicationTenderPrice Equal: " + roundoff.format(TenderPriceFinal).equals(roundoff.format(TenderPrice))+" ***");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Checking TargetPrice Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will wait till the element identified by switching in to frame 
	 * @param locatorFrame: Locator of the frame to be identified
	 * @param locator: Locator of the element to be identified
	 * @return: null
	 * @author CON_SVIJAY02
	 */
	public static ExpectedCondition<WebElement> elementToBeClickableInFrame(final By locatorFrame, final By locator) {
			  return new ExpectedCondition<WebElement>() {
			    @Override
			    public WebElement apply(WebDriver driver) {
			      try {
			        driver.switchTo().defaultContent();
			        driver.switchTo().frame(driver.findElement(locatorFrame));
			        WebElement elem = driver.findElement(locator);
			        return elem.isDisplayed() && elem.isEnabled() ? elem : null;
			      } catch (Exception e) {
			        return null;
			      }
			    }
			    @Override
			    public String toString() {
			      return "element located by: " + locator + " in " + locatorFrame;
			    }
			  };
			}
	/**
	 **Reuse method, it will try continuously till element is clicked
	 * @param locator: Locator of the element to be identified
	 * @return: returns true if element is clicked
	 * @author CON_SVIJAY02
	 */
	public boolean retryingClick(By locator){
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	        	wait.until(ExpectedConditions.elementToBeClickable(locator));
	            driver.findElement(locator).click();
//	            System.out.println("attempts="+attempts);
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        	e.printStackTrace();
	        }
	        attempts++;
	    }
	    return result;
	}
	/**
	 **Reuse method, it will enter value in a text box
	 * @param locator: Locator of the element to be identified
	 * @param value: Value to be entered in the field
	 * @return: Returning the locator for future purposes
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public WebElement enteringValues(By locator, String value) throws Exception{
		try {
			driver.findElement(locator).sendKeys(value);
			return driver.findElement(locator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it's for Key actions
	 * @param locator: Locator of the element to be identified 
	 * @param key: Key action to be performed. For Example, Keys.RETURN will perform Keyboard "Enter" action
	 * @return: Returning the locator for future purposes
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public WebElement enteringValues(By locator, Keys key) throws Exception{
		try {
			driver.findElement(locator).sendKeys(key);
			return driver.findElement(locator);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will perform click action over the element
	 * @param locator: Locator of the element to be identified 
	 * @return: null
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public WebElement clickonButton(By locator) throws Exception{
		try {
			driver.findElement(locator).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will wait till the element is visible in DOM
	 * @param locator: Locator of the element to be identified 
	 * @return: null
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public WebElement waitForVisibilityOfElementLocated(By locator) throws Exception{
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will wait till presence of the element in DOM
	 * @param locator: Locator of the element to be identified 
	 * @return: null
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public WebElement waitForpresenceOfElementLocated(By locator) throws Exception{
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will wait till the element is clickable in DOM
	 * @param locator: Locator of the element to be identified 
	 * @return: null
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public WebElement waitForElementToBeClickable(By locator) throws Exception{
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will wait till the element is invisible in DOM
	 * @param locator: Locator of the element to be identified 
	 * @return: null
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public WebElement waitForinvisibilityOfElementLocated(By locator) throws Exception{
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will retrive webelement
	 * @param locator: Locator of the element to be identified 
	 * @return: webelement
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public WebElement gettingWebElement(By locator) throws Exception{
		try {
			WebElement localvariable = driver.findElement(locator);
			return localvariable;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will retrive list of webelements
	 * @param locator: Locator of the element to be identified 
	 * @return: list of webelements
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public List<WebElement> gettingWebElementsfromList(By locator) throws Exception{
		List<WebElement> localvariable = null;
		try {
			localvariable = driver.findElements(locator);
			return localvariable;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will switch to default frame 
	 * @return: null
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public WebElement switchtoDefaultContentFrame() throws Exception{
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will switch to frame 
	 * @param frame: Frame to switch
	 * @return: null
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public WebElement switchtoFrame(WebElement frame) throws Exception{
		try {
			driver.switchTo().frame(frame);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will scroll to the element by JavascriptExecutor
	 * @param element: Scrolled to the webelement 
	 * @return: null
	 * @author CON_SVIJAY02
	 */
	public WebElement scrollIntoView_Javascript(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 **Reuse method, it will click on the element by JavascriptExecutor
	 * @param element: Webelement to perform click 
	 * @return: null
	 * @author CON_SVIJAY02
	 */
	public WebElement click_Javascript(WebElement element) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	/**
	 * 
	 * @author Roja
	 */
	public static String CurrentDir = System.getProperty("user.dir");
	public static String ScreenshotPath = CurrentDir.concat("/Screenshots/Screenshots-Failure/\\");
	public static void TakeSnapShot(String PageName) throws Exception {
		// Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		String TimeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		// Call getScreenshotAs method to create image file
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		// Move image file to new destination
		// File DestFile=new File(ScreenshotPath);
		// Copy file at destination
		// FileUtils.copyFile(SrcFile, DestFile);
		FileUtils.copyFile(SrcFile, new File(ScreenshotPath + '-' + PageName + TimeStamp + ".png"));
	}
	
	/**
	 * @author Roja
	 */
	public static WebElement element = null;
	private static WebElement getWebElement(String locator, String locatorValue) {
		if (locator.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id"))
			element = driver.findElement(By.id(locatorValue));
		return element;
	}
	
	/**
	 * @author Roja
	 */
	protected static void ClickOnElement(String locator, String locatorValue) {
		element = getWebElement(locator, locatorValue);
		if (element != null) {
			element.click();
		}
	}
	
	/**
	 * @author Roja
	 */
	protected static void ScrollUptoElement(String locator, String LocatorValue) {
		try {
			element = getWebElement(locator, LocatorValue);
			if (element.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			}
		} catch (Exception e) {
			Assert.fail("Scrolling is not successful");
		}
	}
	
	/**
	 * @author Roja
	 */
	protected static void SelectDropDownValues(String locator, String locatorValue, String value) {
		Select select = null;
		if (locator.equalsIgnoreCase("cssSelector")) {
			select = new Select(driver.findElement(By.cssSelector(locatorValue)));
		} else if (locator.equalsIgnoreCase("xpath")) {
			select = new Select(driver.findElement(By.xpath(locatorValue)));
		} else if (locator.equalsIgnoreCase("id")) {
			select = new Select(driver.findElement(By.id(locatorValue)));
		}
		if (select != null) {
			select.selectByValue(value);
			WaitTillElementToBeDisplayed(locator, locatorValue);
		}
	}
	
	/**
	 * @author Roja
	 */
	private static final int TIME_OUT_IN_SECONDS = 300;
	protected static void WaitTillElementToBeDisplayed(String locatorType, String locatorVaue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);

			if (locatorType.equalsIgnoreCase("cssSelector")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorVaue)));
			} else if (locatorType.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorVaue)));
			} else if (locatorType.equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorVaue)));
			}
		} catch (Exception e) {
			System.out.println("Webdriver Locator Error" + e);
		}
	}
	
	/**
	 * @author Roja
	 */
	public static int RandomNumber() {
		Random rand = new Random();
		return rand.nextInt(10000) + 1;
	}
	
	/**
	 * @author Roja
	 */
	public static WebElement FindTheElement(String locatorType, String locatorValue) {
		// String TextLink = null;
		if (locatorType.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		return element;
	}
	
	/**
	 * @author Roja
	 */
	protected static void WaitAndClickOnElement(String locatorType, String locatorVaue) {
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
		if (locatorType.equalsIgnoreCase("cssSelector")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorVaue))).click();
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorVaue))).click();
		} else if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorVaue))).click();
		}
	}
	
	/**
	 * @author Roja
	 */
	protected static void WaitTillClickable(String locatorType, String locatorValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10000);
			if (locatorType.equalsIgnoreCase("cssSelector")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
			} else if (locatorType.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			} else if (locatorType.equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
			}
		} catch (Exception e) {
			System.out.println("Webdriver Locator Error" + e);
		}
	}
	
	/**
	 * @author Roja
	 */
	public static void EnterTextbyChar(String locator, String locatorValue, String text, int indexvalue)
			throws Exception {
		try {
			element = getWebElement(locator, locatorValue, indexvalue);
			if (element != null && element.isEnabled()) {
				element.clear();
				for (int i = 0; i < text.length(); i++) {
					char c = text.charAt(i);
					String s = String.valueOf(c);
					element.sendKeys(s);
				}
				element.sendKeys(Keys.DOWN);
			} else {
			}
		} catch (Exception e) {
			Assert.fail("Entering text failed");
		}
	}
	
	/**
	 * @author Roja
	 */
	private static WebElement getWebElement(String locator, String locatorValue, int indexvalue) {
		if (locator.equalsIgnoreCase("cssSelector")) {
			element = driver.findElements(By.cssSelector(locatorValue)).get(indexvalue - 1);
		} else if (locator.equalsIgnoreCase("xpath")) {
			element = driver.findElements(By.xpath(locatorValue)).get(indexvalue - 1);
		} else if (locator.equalsIgnoreCase("id")) {
			element = driver.findElements(By.id(locatorValue)).get(indexvalue - 1);
		}
		return element;
	}
	
	/**
	 * @author Roja
	 */
	protected static void EnterValues(String locator, String locatorValue, String text) {
		element = getWebElement(locator, locatorValue);
		if (element != null) {
			element.sendKeys(text);
		}
	}
	
	/**
	 * @author Roja
	 */
	protected static void ClickDate(String CalenderField, String GivenValue) {
		driver.findElement(By.xpath(CalenderField)).click();
		driver.findElement(By.xpath("//td[text()='" + GivenValue + "']")).click();
	}
	
	/**
	 * @author Roja
	 */
	protected static void SelectDropDownText(String locator, String locatorValue, String value) {
		WebElement mySelectElement = null;
		if (locator.equalsIgnoreCase("cssSelector")) {
			mySelectElement = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			mySelectElement = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id")) {
			mySelectElement = driver.findElement(By.id(locatorValue));
		}
		Select dropdown = new Select(mySelectElement);
		dropdown.selectByVisibleText(value);
	}
	
	/**
	 * @author Roja
	 */
	public static int TestStatus=1;
	public void ChangeSalesOrg() {
		TestStatus=1;
		if(TestStatus==1){
		try {
			//Change Sales Organization From SF User Settings Page
			ClickOnElement("xpath",SalesForceData.LoggedInUserName);
			ClickOnElement("xpath",SalesForceData.MySettings);
			ClickOnElement("xpath",SalesForceData.PersonalInfo);
			ClickOnElement("xpath",SalesForceData.AdvancedUserDetails);
			ClickOnElement("xpath",SalesForceData.UserEditButton);
			ClickOnElement("xpath",SalesForceData.UserEditButton);
			ScrollUptoElement("xpath",SalesForceData.AdditionalInformationSection);
			SelectDropDownValues("xpath",SalesForceData.SalesOrgField,"220");
		} catch (Exception e) {
			TestStatus=0;
			try {
				TakeSnapShot("SalesForceLoginPage");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Assert.fail("Login Interrupted" + e);
		}
		}
		else {
			Assert.fail("Test Skipped: Browser Launch Failed");
		}
	}
	
	/**
	 * @author Roja
	 */
	public static String OpportunityName = null;
	public static String CreateOpportunity() throws Exception {
			if(TestStatus==1) {
			String AccountName = "AutomationKTOC";
			OpportunityName = "Wartung" + RandomNumber();
			WebElement OppoTab = FindTheElement("xpath", SalesForceData.OpportunityTab);
			if (OppoTab.isDisplayed()) {
				WaitAndClickOnElement("xpath", SalesForceData.OpportunityTab);
			} else {
				driver.navigate().refresh();
				WaitAndClickOnElement("xpath", SalesForceData.OpportunityTab);
			}
			WaitAndClickOnElement("xpath", SalesForceData.NewButton);
			WaitAndClickOnElement("xpath", SalesForceData.ContinueButton);
			WaitTillClickable("xpath", SalesForceData.BusinessType);
			SelectDropDownValues("xpath", SalesForceData.BusinessType, "New Equipment (NEB)");
			WaitTillClickable("xpath", SalesForceData.OpportunityName);
			Thread.sleep(5000);
			EnterTextbyChar("xpath", SalesForceData.OpportunityName, OpportunityName, 1);
			EnterTextbyChar("xpath", SalesForceData.AccountNameField, AccountName, 1);
			SelectDropDownValues("xpath", SalesForceData.MarketSegment, "Leisure and Education");
			SelectDropDownValues("xpath", SalesForceData.LeadSource, "Invitation to Tender");
			EnterValues("xpath", SalesForceData.QuantityField, SalesForceData.QuantityValue);
			EnterValues("xpath", SalesForceData.AmountField, SalesForceData.Amountvalue);
			ClickDate(SalesForceData.PriceDueDateField, SalesForceData.PriceDueDateValue);
			ClickDate(SalesForceData.CloseDateField, SalesForceData.CloseDateValue);
			SelectDropDownValues("xpath", SalesForceData.Stage, "Tender/Proposal");
			ScrollUptoElement("xpath", SalesForceData.SiteCountryField);
			SelectDropDownValues("xpath", SalesForceData.SiteCountryField, SalesForceData.SiteCountryValue);
			ScrollUptoElement("xpath", SalesForceData.SiteCountyField);
			SelectDropDownValues("xpath", SalesForceData.SiteCountyField, SalesForceData.SiteCountyValue);
			ScrollUptoElement("xpath", SalesForceData.StartOnSiteDateField);
			ClickDate(SalesForceData.StartOnSiteDateField, SalesForceData.StartOnSiteDateValue);
			ClickDate(SalesForceData.ProjectEndDateField, SalesForceData.ProjectEndDateValue);
			SelectDropDownText("xpath", SalesForceData.ProjectComplexity,"Small");
			ScrollUptoElement("xpath", SalesForceData.PageDescription);
			WaitAndClickOnElement("xpath", SalesForceData.SaveButton);
			WaitTillElementToBeDisplayed("xpath", SalesForceData.OpportunityDesc);
			WebElement OpportunityDescription = driver.findElement(By.xpath(SalesForceData.OpportunityDesc));
			String OppoName = OpportunityDescription.getText();
			if (OppoName.equalsIgnoreCase(OpportunityName)) {
			} else {
				TestStatus=0;
				Assert.fail("Opportunity creation failed");
			}
			}
			else
			{
				Assert.fail("Test Skipped: Failed Logon To Salesforce");
			}
			return OpportunityName;
		}	

	/**
	 * @author Roja
	 */
	public void MapContactWithOpportunity() throws Exception {
			if(TestStatus==1) {
			try {
			Thread.sleep(4000);
			WaitAndClickOnElement("xpath", SalesForceData.ContactsLink);
			WaitAndClickOnElement("xpath", SalesForceData.ContactSectionNewButton);
			WaitAndClickOnElement("xpath", SalesForceData.ContactOption);
			SelectDropDownText("xpath", SalesForceData.ContactRole,"Decision Maker");
			WaitAndClickOnElement("xpath", SalesForceData.SaveButton);
			}
			catch(Exception e) {
				TestStatus=0;
				Assert.fail("Mapping contact with opportunity fails");
			}
			}
			else {
				Assert.fail("Test Skipped: Opportunity Creation Failed");
			}
		}
	
		/**
		 * @author Roja
		 */
		public void MapOpportunityWithFLTenders() throws Exception {
			if(TestStatus==1) {
			try {
			WaitAndClickOnElement("xpath", SalesForceData.FLTendersLink);
			WaitAndClickOnElement("xpath", SalesForceData.FLTendersNewButton);
			}
			catch(Exception e) {
				TestStatus=0;
				Assert.fail("Failed mapping opportunity with tender.");
			}
			}
			else {
				Assert.fail("Test Skipped: Opportunity Creation Failed");
			}
		}
}