package com.KTOC.TRB.testautomation.Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
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
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class KTOCTRBUtils {
	private static final int TIME_OUT_IN_SECONDS = 300;
	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

	public static WebElement element = null;
	// public static String ScreenshotPath="/Users/roja/git/nemo-testautomation/src/main/java/com/nemo/testautomation/Screenshots/Screenshots-Failure/";
	public static String CurrentDir = System.getProperty("user.dir");
	public static String ScreenshotPath = CurrentDir.concat("/Screenshots/Screenshots-Failure/\\");
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static DecimalFormat roundoff = new DecimalFormat("##.00");
	public static String operatingSystem;
	public static String browser;
	public static String username;
	public static String password;
	public static String opportunity;
	public static String opportunityCreateorSearch;
	public static String equipmentid; // France:10503512 / 10911391, Australia:30493722
	public static String equipmentid_2;
	public static String groupName;
	public static String salesoffice; // France: VB FRRW, VB FRPP, Australia: AU21 - Sydney Region;
	public static String changeSalesOffice; //France: VB FRPP, Australia: AU22 - Newcastle Region
	public static int MS5HODatetoChange=2;
	public static Boolean istenderPrice = false;
	public static Boolean isMultipleEquipment = false;
	public static int showInstallationCalculation=0;
	public static String isFirstMaintenancetoEdit;
	public static String isFirstMaintenancetoEdittoChange1;
	public static String isFirstMaintenancetoEdittoChange2;
	public static Float regionalDiscount;
/*	public static Float regionalDiscounttoChange;
	public static Float regionalDiscount_MultipleEqup;
	public static Float regionalDiscounttoChange_MultipleEqup;*/
	public static Float check_showtotal_ITEfactor;
/*	public static Float check_showtotal_ITEfactortoChange;
	public static Float check_showtotal_ITEfactor_MultipleEqup;
	public static Float check_showtotal_ITEfactortoChange_MultipleEqup;*/
	public static Float LabourRate;
/*	public static Float LabourRatetoChange;
	public static Float LabourRate_MultipleEqup;
	public static Float LabourRatetoChange_MultipleEqup;*/
	public static String FreezePrintedVersion="No";
	public static String NewVersionProduct="Yes"; //No
	public static String SaveandClose="Yes";
	public static String ProductRelease;
	public static String customerid;
	public static String equipment_ADDorChange;
	public static String equipmentinService;
	public static String equipmentinService_Escalator;
	public static String supervisor_ResponsiblePerson;
	public static String supervisor_ResponsiblePersontoChange;
	public static String template;
	public static String template_2;
	public static String StageProbability_Stage;
	public static String StageProbability_Description;
	public static String StageProbability_probability;
	public static String value_seismicArea;
	public static String weeklyTeamCostforZone;
	public static String weeklyTeamCostforRoomandBoard;
	public static String frontlineAssigned;
	
	public Float Final_SalesPrice;
	public static Float TenderPrice;
	public static Float allTenderPrice;
	//----------------------------
	public static String withoutFirstMaintenance;
	public static String withFirstMaintenance_1;
	public static String withFirstMaintenance_2;
	public static String discount;
	public static String read_tenderPrice;
	public static String read_allTenderPrice;
	
	
	/**
	 **Reuse method, it will launch the browser
	 * @param browser
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void LaunchBrowser(String frontline, String excelpath) throws Exception {
		readTestData(frontline, excelpath);
		try {
			if (operatingSystem.equalsIgnoreCase("ios")) {
				ChromeOptions chromeOptions = new ChromeOptions();
//			chromeOptions.setBinary(new File(System.getProperty("user.dir")));
				String headless = System.getProperty("headless");
				if (headless != null && headless.equalsIgnoreCase("true")) {
					// String PROXY = "23.23.23.23:3128"; //# IP:PORT or HOST:PORT
					chromeOptions.addArguments("--headless");
					chromeOptions.addArguments("--no-proxy-server");
					// chromeOptions.addArguments("--proxy-server=http://%s' % PROXY");
					chromeOptions.addArguments("--ignore-certificate-errors");
					chromeOptions.addArguments("--disable-gpu");
//				chromeOptions.addArguments("window-size=2000,2000");
				}
					driver = new ChromeDriver(chromeOptions);
					driver.manage().window().setSize(new Dimension(1400,1080));
				} else if (operatingSystem.equalsIgnoreCase("windows")) {
				if (browser.equalsIgnoreCase("ff")) {
					System.setProperty("webdriver.gecko.driver","C:\\Users\\con_svijay02\\Downloads\\geckodriver-v0.23.0-win64\\geckodriver.exe");
					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("ch")) {
					DesiredCapabilities CHDes = DesiredCapabilities.chrome();
					ChromeOptions CHOpt = new ChromeOptions();
//					CHOpt.setUseCleanSession(true);
//					CHOpt.setUseTechnologyPreview(true);
					CHDes.setCapability(ChromeOptions.CAPABILITY, CHOpt);
					File CHPath = new File("C:\\Users\\con_svijay02\\Downloads\\chromedriver_win32_2.46\\chromedriver.exe");
					System.setProperty("webdriver.chrome.driver", CHPath.getAbsolutePath());
					driver = new ChromeDriver(CHOpt);
				}
			}	
			wait = new WebDriverWait(driver, 2000000);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(420, TimeUnit.SECONDS);
			driver.get("https://test.salesforce.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param frontline: Frontline to getdata
	 * @param EXCELPATH: Path of the testdata file
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public HashMap<String, Float> hm_ITEfactorforSalesOfficeData = new HashMap<String, Float>();
	public HashMap<String, Float> hm_RegionalDiscountforSalesOfficeData  = new HashMap<String, Float>();
	public HashMap<String, Float> hm_LabourRateforSalesOfficeData  = new HashMap<String, Float>();
	public List<String> ls_allEquipmentIDs=new LinkedList<>();
	public void readTestData(String frontline, String EXCELPATH) throws Exception{
		try {
//			String torun="java1";
			frontline = frontline.toUpperCase();
			File path = new File(CurrentDir+EXCELPATH);
			String location = path.getAbsolutePath();
			ExcelReader excelReader=new ExcelReader(location);
			operatingSystem = excelReader.GetData("GeneralData").get("OperatingSystem");
			browser = excelReader.GetData("GeneralData").get("Browser");
			username = excelReader.GetData("GeneralData").get("UserName");
			password = excelReader.GetData("GeneralData").get("Password");

			switch(frontline) {
			case "FRANCE":
				opportunityCreateorSearch  = excelReader.GetData("France").get("Opportunity_CreateorSearch");
				opportunity  = excelReader.GetData("France").get("OpportunityName");
				ProductRelease  = excelReader.GetData("France").get("ProductRelease");
				equipmentid  = excelReader.GetData("France").get("EquipmentID");
				//-----for multiEquipment-----
				equipmentid_2 = excelReader.GetData("France").get("EquipmentID_2");
				groupName = excelReader.GetData("France").get("GroupName");
				template_2  = excelReader.GetData("France").get("TemplateName2");
				equipmentinService_Escalator  = excelReader.GetData("France").get("EquipmentinService_Escalator");
//------		regionalDiscount_MultipleEqup = Float.valueOf(excelReader.GetData("France").get("RegionalDiscount_MultipleEqup"));
//				regionalDiscounttoChange_MultipleEqup = Float.valueOf(excelReader.GetData("France").get("changeRegionalDiscount_MultipleEqup"));
				//-----for multiEquipment-----
				equipment_ADDorChange  = excelReader.GetData("France").get("Equipment_ADDorChange");
				customerid  = excelReader.GetData("France").get("CustomerID");	
				salesoffice  = excelReader.GetData("France").get("SalesOffice");
				changeSalesOffice  = excelReader.GetData("France").get("ChangeSalesOffice");
				equipmentinService  = excelReader.GetData("France").get("EquipmentinService");
				supervisor_ResponsiblePerson  = excelReader.GetData("France").get("Supervisor");
				supervisor_ResponsiblePersontoChange  = excelReader.GetData("France").get("changeSupervisor");
				template  = excelReader.GetData("France").get("TemplateName");
/*				if(torun.equalsIgnoreCase("java")) {
					withoutFirstMaintenance  = excelReader.GetData("France").get("withoutFirstMaintenance");
					withFirstMaintenance_1 = excelReader.GetData("France").get("withFirstMaintenance1");
					withFirstMaintenance_2  = excelReader.GetData("France").get("withFirstMaintenance2");
					discount  = excelReader.GetData("France").get("Discount");
					read_tenderPrice  = excelReader.GetData("France").get("TenderPrice");
				}*/
				isFirstMaintenancetoEdit = excelReader.GetData("France").get("isFirstMaintenancetoEdit");
				isFirstMaintenancetoEdittoChange1 = excelReader.GetData("France").get("isFirstMaintenancetoEdittoChange_1");
				isFirstMaintenancetoEdittoChange2 = excelReader.GetData("France").get("isFirstMaintenancetoEdittoChange_2");
/*-----------------------------------------Comment for getMaintenanceDatafromPage-----------------------------------------				
				regionalDiscount = Float.valueOf(excelReader.GetData("France").get("RegionalDiscount"));
				regionalDiscounttoChange = Float.valueOf(excelReader.GetData("France").get("changeRegionalDiscount"));
				check_showtotal_ITEfactor = Float.valueOf(excelReader.GetData("France").get("ITEfactor"));
				check_showtotal_ITEfactortoChange = Float.valueOf(excelReader.GetData("France").get("changeITEfactor"));
				check_showtotal_ITEfactor_MultipleEqup = Float.valueOf(excelReader.GetData("France").get("ITEfactor_MultipleEqup"));
				check_showtotal_ITEfactortoChange_MultipleEqup = Float.valueOf(excelReader.GetData("France").get("changeITEfactor_MultipleEqup"));
				LabourRate = Float.valueOf(excelReader.GetData("France").get("LabourRate"));
				LabourRatetoChange = Float.valueOf(excelReader.GetData("France").get("changeLabourRate"));
				LabourRate_MultipleEqup = Float.valueOf(excelReader.GetData("France").get("LabourRate_MultipleEqup"));
				LabourRatetoChange_MultipleEqup = Float.valueOf(excelReader.GetData("France").get("changeLabourRate__MultipleEqup"));
//----------------------------------------Comment for getMaintenanceDatafromPage-----------------------------------------*/				
				StageProbability_Stage = excelReader.GetData("France").get("StageProbabilityStage");
				StageProbability_Description = excelReader.GetData("France").get("StageProbabilityDescription");
				StageProbability_probability = excelReader.GetData("France").get("StageProbabilityProbability");
				frontlineAssigned=frontline;
//				System.out.println("frontlineAssigned"+frontlineAssigned);
				break;
				
			case "AUSTRALIA":	
				opportunityCreateorSearch  = excelReader.GetData("Australia").get("Opportunity_CreateorSearch");
				opportunity  = excelReader.GetData("Australia").get("OpportunityName");
				ProductRelease  = excelReader.GetData("Australia").get("ProductRelease");
				equipmentid  = excelReader.GetData("Australia").get("EquipmentID");
				equipment_ADDorChange  = excelReader.GetData("Australia").get("Equipment_ADDorChange");
				customerid  = excelReader.GetData("Australia").get("CustomerID");	
				salesoffice  = excelReader.GetData("Australia").get("SalesOffice");
				changeSalesOffice  = excelReader.GetData("Australia").get("ChangeSalesOffice");
				equipmentinService  = excelReader.GetData("Australia").get("EquipmentinService");
				supervisor_ResponsiblePerson  = excelReader.GetData("Australia").get("Supervisor");
				supervisor_ResponsiblePersontoChange  = excelReader.GetData("Australia").get("changeSupervisor");
				template  = excelReader.GetData("Australia").get("TemplateName");
/*				if(torun.equalsIgnoreCase("java")) {
					withoutFirstMaintenance  = excelReader.GetData("Australia").get("withoutFirstMaintenance");
					withFirstMaintenance_1 = excelReader.GetData("Australia").get("withFirstMaintenance1");
					withFirstMaintenance_2  = excelReader.GetData("Australia").get("withFirstMaintenance2");
					discount  = excelReader.GetData("Australia").get("Discount");
					read_tenderPrice  = excelReader.GetData("Australia").get("TenderPrice");
				}*/
				isFirstMaintenancetoEdit = excelReader.GetData("Australia").get("isFirstMaintenancetoEdit");
				isFirstMaintenancetoEdittoChange1 = excelReader.GetData("Australia").get("isFirstMaintenancetoEdittoChange_1");
				isFirstMaintenancetoEdittoChange2 = excelReader.GetData("Australia").get("isFirstMaintenancetoEdittoChange_2");
/*-----------------------------------------Comment for getMaintenanceDatafromPage-----------------------------------------
				regionalDiscount = Float.valueOf(excelReader.GetData("Australia").get("RegionalDiscount"));
				regionalDiscounttoChange = Float.valueOf(excelReader.GetData("Australia").get("changeRegionalDiscount"));
				check_showtotal_ITEfactor = Float.valueOf(excelReader.GetData("Australia").get("ITEfactor"));
				check_showtotal_ITEfactortoChange = Float.valueOf(excelReader.GetData("Australia").get("changeITEfactor"));
				LabourRate = Float.valueOf(excelReader.GetData("Australia").get("LabourRate"));
				LabourRatetoChange = Float.valueOf(excelReader.GetData("Australia").get("changeLabourRate"));
//-----------------------------------------Comment for getMaintenanceDatafromPage-----------------------------------------*/
				StageProbability_Stage = excelReader.GetData("Australia").get("StageProbabilityStage");
				StageProbability_Description = excelReader.GetData("Australia").get("StageProbabilityDescription");
				StageProbability_probability = excelReader.GetData("Australia").get("StageProbabilityProbability");
				frontlineAssigned=frontline;
				break;
				
			case "CANADA":	
				opportunityCreateorSearch  = excelReader.GetData("Canada").get("Opportunity_CreateorSearch");
				opportunity  = excelReader.GetData("Canada").get("OpportunityName");
				ProductRelease  = excelReader.GetData("Canada").get("ProductRelease");
				equipmentid  = excelReader.GetData("Canada").get("EquipmentID");
				equipment_ADDorChange  = excelReader.GetData("Canada").get("Equipment_ADDorChange");
				customerid  = excelReader.GetData("Canada").get("CustomerID");	
				salesoffice  = excelReader.GetData("Canada").get("SalesOffice");
				value_seismicArea  = excelReader.GetData("Canada").get("SeismicArea");
				weeklyTeamCostforZone  = excelReader.GetData("Canada").get("WeeklyTeamCostforZone");
				weeklyTeamCostforRoomandBoard  = excelReader.GetData("Canada").get("WeeklyTeamCostforRoomandBoard");
				changeSalesOffice  = excelReader.GetData("Canada").get("ChangeSalesOffice");
				equipmentinService  = excelReader.GetData("Canada").get("EquipmentinService");
				supervisor_ResponsiblePerson  = excelReader.GetData("Canada").get("Supervisor");
				supervisor_ResponsiblePersontoChange  = excelReader.GetData("Canada").get("changeSupervisor");
				template  = excelReader.GetData("Canada").get("TemplateName");
/*				if(torun.equalsIgnoreCase("java")) {
					withoutFirstMaintenance  = excelReader.GetData("Canada").get("withoutFirstMaintenance");
					withFirstMaintenance_1 = excelReader.GetData("Canada").get("withFirstMaintenance1");
					withFirstMaintenance_2  = excelReader.GetData("Canada").get("withFirstMaintenance2");
					discount  = excelReader.GetData("Canada").get("Discount");
					read_tenderPrice  = excelReader.GetData("Canada").get("TenderPrice");
				}*/
				isFirstMaintenancetoEdit = excelReader.GetData("Canada").get("isFirstMaintenancetoEdit");
				isFirstMaintenancetoEdittoChange1 = excelReader.GetData("Canada").get("isFirstMaintenancetoEdittoChange_1");
				isFirstMaintenancetoEdittoChange2 = excelReader.GetData("Canada").get("isFirstMaintenancetoEdittoChange_2");
/*-----------------------------------------Comment for getMaintenanceDatafromPage-----------------------------------------
				regionalDiscount = Float.valueOf(excelReader.GetData("Canada").get("RegionalDiscount"));
				regionalDiscounttoChange = Float.valueOf(excelReader.GetData("Canada").get("changeRegionalDiscount"));
				check_showtotal_ITEfactor = Float.valueOf(excelReader.GetData("Canada").get("ITEfactor"));
				check_showtotal_ITEfactortoChange = Float.valueOf(excelReader.GetData("Canada").get("changeITEfactor"));
				LabourRate = Float.valueOf(excelReader.GetData("Canada").get("LabourRate"));
				LabourRatetoChange = Float.valueOf(excelReader.GetData("Canada").get("changeLabourRate"));
//-----------------------------------------Comment for getMaintenanceDatafromPage-----------------------------------------*/				
				StageProbability_Stage = excelReader.GetData("Canada").get("StageProbabilityStage");
				StageProbability_Description = excelReader.GetData("Canada").get("StageProbabilityDescription");
				StageProbability_probability = excelReader.GetData("Canada").get("StageProbabilityProbability");
				frontlineAssigned=frontline;
				break;
			}
/*-----------------------------------------Comment for getMaintenanceDatafromPage-----------------------------------------			
			hm_ITEfactorforSalesOfficeData.put(salesoffice, check_showtotal_ITEfactor);
			hm_ITEfactorforSalesOfficeData.put(changeSalesOffice, check_showtotal_ITEfactortoChange);
			hm_ITEfactorforSalesOfficeData.put("MultipleEqup_"+salesoffice, check_showtotal_ITEfactor_MultipleEqup);
			hm_ITEfactorforSalesOfficeData.put("MultipleEqup_"+changeSalesOffice, check_showtotal_ITEfactortoChange_MultipleEqup);
			hm_RegionalDiscountforSalesOfficeData.put(salesoffice, regionalDiscount);
			hm_RegionalDiscountforSalesOfficeData.put(changeSalesOffice, regionalDiscounttoChange);	
			hm_LabourRateforSalesOfficeData.put(salesoffice, LabourRate);
			hm_LabourRateforSalesOfficeData.put(changeSalesOffice, LabourRatetoChange);
			hm_LabourRateforSalesOfficeData.put("MultipleEqup_"+salesoffice, LabourRate_MultipleEqup);
			hm_LabourRateforSalesOfficeData.put("MultipleEqup_"+changeSalesOffice, LabourRatetoChange_MultipleEqup);
//-----------------------------------------Comment for getMaintenanceDatafromPage-----------------------------------------*/			
			ls_allEquipmentIDs.add(equipmentid);
			ls_allEquipmentIDs.add(equipmentid_2);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	public boolean isSiblingPresent(WebElement element, By by) {
	    try {
	    	element.findElements(by);
	        return true;
	    } catch (NoSuchElementException e) {
	        return false;
	    }
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
 **Reuse method, it will remove multiple dots from price field value. implemented in validateDetailBreakdownTab 
 * @param value: Value having multiple dots
 * @return: Value to be returned after removing dots
 * @throws Exception: For exception handling
 * @author CON_SVIJAY02
 */
	public String removingDot(String value) throws Exception{
		try {
			int count=0; 
			int location=0;
			StringBuilder stringbuilder=new StringBuilder(value);
			for(Character chr:value.toCharArray()){
				if(chr.equals('.')){
					count++;
				}
			}
			if(count>1){
				for(int i=0; i<=count; i++){
				location=value.indexOf(".");
					if(count>1){
						value=stringbuilder.deleteCharAt(location).toString();
						count--;
					}
				} return value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} return value;
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
	public WebElement scrollIntoView_Javascript(WebElement element) throws Exception{
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
	 **Reuse method, it will take screenshots
	 * @param screenshotName: screenshot name will be sent into the variable
	 * @return: for future purposes
	 * @throws IOException: For exception handling
	 * @author CON_SVIJAY02
	 */
	public String screenshotCapture(String screenshotName) throws IOException {
        String location = null;
        String dateformat = new SimpleDateFormat("ddMMMyyyy_hh_mm_ssaa").format(Calendar.getInstance().getTime());
		try {
			TakesScreenshot takescreenshot = (TakesScreenshot) driver;
			File source = takescreenshot.getScreenshotAs(OutputType.FILE);
			File creatFolder=new File(CurrentDir+"\\Screenshots\\"+dateformat);
			creatFolder.mkdir();
			String new_folderLocation=creatFolder.getAbsolutePath();
			File destination = new File(new_folderLocation+"\\SS_"+dateformat+"_"+screenshotName+".png");
			location=destination.getAbsolutePath();
//			System.out.println(location);
			FileUtils.copyFile(source, destination);	
			String imagetostring=destination.toString();
			embedImage(imagetostring);
		} catch (WebDriverException e) {
			e.printStackTrace();
		}
		return location;
    }
	/**
	 **Reuse method, appending failure SS to display in report
	 * @param Imagep: image to display in report
	 * @author CON_SVIJAY02/CON_KarthickSairam
	 */
	public void embedImage(String Imagep) {
		logHTML("<a href='" + Imagep + "' target='_blank'><img src='" + Imagep+ "' style='width:80%; height: auto;'/></a>");
	}
	/**
	 **Reuse method, failure SS to display in report
	 * @param log: 
	 * @author CON_SVIJAY02/CON_KarthickSairam
	 */
	public void logHTML(Object log) {
		System.out.println("*HTML* " + log);
	}
	
	
	
	public void SwitchToFramebyID(String FrameId) {
		driver.switchTo().frame(FrameId);
	}

//Find Elements
	public static WebElement FindTheElement(String locatorType, String locatorValue) {
		// String TextLink = null;
		if (locatorType.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		/*
		 * else if (locatorType.equalsIgnoreCase("linkText")){
		 * driver.findElement(By.linkText(TextLink)); }
		 */
		return element;
	}

	// Wait Till Element To Be Clickable
	protected static void WaitTillElementToBeClickable(String locatorType, String locatorValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);

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

	// Wait Till Element To Be Clickable with values
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

	// Wait And Click Element Directly
	protected void WaitAndClickElementDirectly(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			;
		} catch (Exception e) {
			Assert.fail("Unable to wait for the element clickable more than 100secs" + e);
		}
	}

	// Wait Till Element To Be Displayed
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

	protected void WaitTillElementDisplayed(String locatorType, String locatorVaue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 100);

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

	// Wait And Click On Element
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

	// Wait And Click On Element with values
	protected void WaitAndClick(String locatorType, String locatorValue) {
		WebDriverWait wait = new WebDriverWait(driver, 100);

		if (locatorType.equalsIgnoreCase("cssSelector")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue))).click();
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue))).click();
		} else if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue))).click();
		}
	}

	// Wait till the element is visible
	protected void WaitTillElementVisible(String locatorType, String locatorValue) {
		// int seconds = 100;
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
		if (locatorType.equalsIgnoreCase("cssSelector")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
		} else if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}
	}

	protected static void WaitTillVisible(String locatorType, String locatorValue) {
		// int seconds = 100;
		WebDriverWait wait = new WebDriverWait(driver, 10000);
		if (locatorType.equalsIgnoreCase("cssSelector")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
		} else if (locatorType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}
	}

	protected void WaitAndClear(String locatorValue) {
		WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue))).clear();

	}

	protected void FluentWaitClick(WebElement element) {
		new WebDriverWait(driver, TIME_OUT_IN_SECONDS).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		/*
		 * Wait<WebDriver> stubbornWait = new FluentWait<WebDriver>(driver)
		 * .withTimeout(30, TimeUnit.SECONDS) .pollingEvery(5, TimeUnit.SECONDS)
		 * .ignoring(NoSuchElementException.class)
		 * .ignoring(StaleElementReferenceException.class);
		 * 
		 * element = stubbornWait.until(new Function<WebDriver, WebElement>() { public
		 * WebElement apply(WebDriver driver) { return driver.findElement(By.id("foo"));
		 * } });
		 */
	}

	protected void FluentWait(WebElement element) {
		new WebDriverWait(driver, TIME_OUT_IN_SECONDS).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void RightClick(String locatorType, String locatorValue) {
		WebElement element = getWebElement(locatorType, locatorValue);
		Actions action = null;
		if (element != null) {
			action = new Actions(driver).contextClick(element);
		}
		if (action != null) {
			action.build().perform();
		}
	}

	public void checkPageIsReady() {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			// Initially bellow given if condition will check ready state of page.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				System.out.println("Page Is loaded.");
				return;
			}

			// This loop will rotate for 25 times to check If page Is ready after every 1
			// second.
			// You can replace your value with 25 If you wants to Increase or decrease wait
			// time.
			for (int i = 0; i < 25; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				// To check page ready state.
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					break;
				}
			}
		} catch (Exception e) {
			Assert.fail("The page is not ready");
		}
	}

	public void SelectOptionWithIndex(String AutoCompleteTeBoxlocator, String ListItemsLocator, int IndexToSelect) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
			WebElement autoOptions = driver.findElement(By.xpath(AutoCompleteTeBoxlocator));
			wait.until(ExpectedConditions.visibilityOf(autoOptions));
			List<WebElement> optionsToSelect = autoOptions.findElements(By.xpath(ListItemsLocator));
			if (IndexToSelect <= optionsToSelect.size()) {
				System.out.println("Trying to select based on index: " + IndexToSelect);
				optionsToSelect.get(IndexToSelect).click();
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void SelectOptionWithText(String AutoCompleteTeBoxlocator, String ListItemsLocator, String TextToSelect) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
			WebElement autoOptions = driver.findElement(By.xpath(AutoCompleteTeBoxlocator));
			wait.until(ExpectedConditions.visibilityOf(autoOptions));
			List<WebElement> optionsToSelect = autoOptions.findElements(By.xpath(ListItemsLocator));
			for (WebElement option : optionsToSelect) {
				if (option.getText().equals(TextToSelect)) {
					System.out.println("Trying to select: " + TextToSelect);
					option.click();
					break;
				}
			}

		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	protected static void EnterValues(String locator, String locatorValue, String text) {

		element = getWebElement(locator, locatorValue);

		if (element != null) {
			element.sendKeys(text);
		}
		/*
		 * if(element.isEnabled()) { element.sendKeys(text); logger.log(LogStatus.PASS,
		 * "The Text"+text+"is typed successfully"); }else { logger.log(LogStatus.FAIL,
		 * "Error typing the Text:"+ text); }
		 */
		/*
		 * if(element.getText()!=text) { throw new FailToEnterValues(); }
		 */
	}

	private static WebElement getWebElement(String locator, String locatorValue) {
		if (locator.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id"))
			element = driver.findElement(By.id(locatorValue));
		return element;
	}

	protected void EnterValuesByIndex(String locator, String locatorValue, String text, int indexvalue)
			throws InterruptedException {
		// WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
		element = getWebElement(locator, locatorValue, indexvalue);
		if (element != null) {
			element.sendKeys(text);
			element.sendKeys(Keys.UP);
		}

	}

	protected void EnterValuesNoIndex(String locator, String locatorValue, String text) throws InterruptedException {
		// WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
		element = getWebElement(locator, locatorValue);
		if (element != null) {
			element.sendKeys(text);
			// element.sendKeys(Keys.UP);
		}

	}

	public void EnterValuesUsingJavaScript(String idlocator, String text) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("document.getElementById('idlocator').value = text;");
	}

	// Enter values character by character
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

	private static WebElement getWebElement(String locator, String locatorValue, int indexvalue) {
		if (locator.equalsIgnoreCase("cssSelector")) {
			// wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
			element = driver.findElements(By.cssSelector(locatorValue)).get(indexvalue - 1);
		} else if (locator.equalsIgnoreCase("xpath")) {
			/*
			 * WebDriverWait wait = new WebDriverWait(driver, 500);
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
			 */
			element = driver.findElements(By.xpath(locatorValue)).get(indexvalue - 1);

		} else if (locator.equalsIgnoreCase("id")) {
			/*
			 * WebDriverWait wait = new WebDriverWait(driver, 500);
			 * wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
			 */
			element = driver.findElements(By.id(locatorValue)).get(indexvalue - 1);
			// element = driver.findElement(By.id(CustomerPageData.CustomerSearch));

		}
		return element;
	}

	// Enter Values using Robot Class
	public void EnterTextByKeyboard(String locator, String locatorValue, String text, int indexvalue) throws Exception {
		Robot robot = new Robot();
		element = getWebElement(locator, locatorValue, indexvalue);
		if (element != null && element.isEnabled()) {
			element.clear();
			for (char c : text.toCharArray()) {
				int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
				if (KeyEvent.CHAR_UNDEFINED == keyCode) {
					throw new RuntimeException("Key code not found for character '" + c + "'");
				}
				robot.keyPress(keyCode);
				robot.delay(200);
				robot.keyRelease(keyCode);
				robot.delay(200);
			}
			robot.keyPress(KeyEvent.VK_TAB);
		} else {
			System.out.println(text);
		}
	}

	public void PressEnter() throws AWTException {
		Robot robot = new Robot();
		// WebElement element = getWebElement(locator, locatorValue);
		robot.keyPress(KeyEvent.VK_ENTER);

	}

	// Scroll Up To An Element
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

	protected static void ScrollToElementDirectly(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	protected void ScrollToElementDirectlyAndWait(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element).wait();
	}

	public void Display(String str) {
		System.out.println(str);
	}

	public void ScrollPageDown() {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void ScrollPageUP() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("scroll(0, -10000);");
	}

	public void ScrollRight() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(10000,0)", "");
		// driver.executeScript("window.scrollByLines(2),"")
	}

	public void ScrollLeft() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(-3000,0)", "");
	}

	protected void ScrollAndClickOnElement(String locator, String LocatorValue) {
		element = getWebElement(locator, LocatorValue);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		if (element != null) {
			element.click();
		}
	}

	protected static void ClickOnElement(String locator, String locatorValue) {
		element = getWebElement(locator, locatorValue);
		if (element != null) {
			element.click();
		}

	}

	protected void ClickOnElementByIndex(String locator, String locatorValue, int indexvalue) {
		element = getWebElement(locator, locatorValue, indexvalue);
		if (element != null) {
			element.click();
		}

	}

	public void ClearTextField(String xpath) {
		driver.findElement(By.xpath(xpath)).clear();
	}

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

	protected void SelectBootStrap(String textval) {
		try {
			WebElement listElement = driver
					.findElement(By.xpath("//div[@class='Select-menu-outer']//div[text()='" + textval + "']"));
			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);", listElement);
			// listElement.click();
			if (listElement.isDisplayed()) {
				WaitAndClickElementDirectly(listElement);
				// listElement.click();
			} else {
				Assert.fail("ListElement could not be selected.");
			}
		} catch (Exception e) {
			Assert.fail(e);
		}
	}

	protected void SelectBootStrapCSS(String textval) {
		WebElement listElement = driver
				.findElement(By.cssSelector("div.Select-menu-outer and text()='" + textval + "'"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listElement);
		listElement.click();

	}

	// iterate text value from dropdown - KTOC specific
	protected void SelectTextValueFromDropdown(String locator, String locatorValue, String node, String text) {
		if (locator.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		element.click();
		List<WebElement> TextValues = driver.findElements(By.xpath("//" + node + "[text()='" + text + "']"));
		for (WebElement TextValue : TextValues) {
			TextValue.click();
			break;
		}
	}

	// Select the first text of the list from a dropdown - KTOC Specific
	protected String SelectFirstTextFromDropdown(String locatorType, String locatorValue, String item1LocatorType,
			String itemLocatorValue) {
		if (locatorType.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		element.click();
		WebElement Item = FindTheElement(item1LocatorType, itemLocatorValue);
		Item.click();
		return Item.getText();
	}

	// Get The Text Of An Element
	public String GetTextOf(String locator, String locatorValue) {
		String TextofTheElement = null;
		if (locator.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		TextofTheElement = element.getText();
		return TextofTheElement;
	}

	// Mouse Hover On An Element
	public void MouseHover(String locator, String locatorValue) {
		if (locator.equalsIgnoreCase("cssSelector")) {
			element = driver.findElement(By.cssSelector(locatorValue));
		} else if (locator.equalsIgnoreCase("xpath")) {
			element = driver.findElement(By.xpath(locatorValue));
		} else if (locator.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	// Click Calendar Date

	protected static void ClickCalendarValue(String selVal) {
		WebElement cal = driver.findElement(By.xpath("//div[@class='calendar-weeks']"));
		WebElement selecval = cal.findElement(By.xpath("//div[text()='" + selVal + "']"));
		selecval.click();

	}

	protected static void ClickDate(String CalenderField, String GivenValue) {
		driver.findElement(By.xpath(CalenderField)).click();
		driver.findElement(By.xpath("//td[text()='" + GivenValue + "']")).click();
	}

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
}
