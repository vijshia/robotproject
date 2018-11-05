package com.KTOC.TRB.testautomation.test_Run;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class testRun_KTOCTRB {

	public static void main(String[] args) {
		WebDriver driver = null;
		String browser = "ff";
		String opportunity = "KOFCOL TRB SFA"; // 1723- Automation_Template_001
		String ProductRelease="1723"; //1813 1723 1713
		String equipmentid = "10503512"; // 10894788
		String equipment_ADDorChange = "change";
		String salesoffice="VB FRPP";// VB FRPC, VB FRPF, VB FRPH, VB FRPP, VB FRRA, VB FRRE, VB FRRM, VB FRRN, VB FRRR, VB FRRS, VB FRRW
		int MS5HODatetoChange=1;
		String supervisor_ResponsiblePreson="06200428"; //06114080
		String equipmentinService="DOC Door"; //LIS Elevator, EIS Escalator, Non-Lis Elevator, Non-Lis Escalator, DIS Door, DOC Door, Non DIS/DOC Door
		String template = "Automation_Template_002";
		String discount = "10";
		String FirstMaintenance = "10";
		Float check_showtotal_ITEfactor=2f;
		String FreezePrintedVersion="No"; // Yes No
		String SaveandClose="Yes"; //No Yes
		String StageProbability_Description="Automation Test Description";
		String StageProbability_Stage="Budget Price"; //RFQ, Prospecting/Design Assist, Budget Price, Tender/Proposal, Negotiation/Review, Order Agreed
		String StageProbability_probability="22";

		if (browser.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\con_svijay02\\Downloads\\geckodriver-v0.23.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("ch")) {
			DesiredCapabilities CHDes = DesiredCapabilities.chrome();
			ChromeOptions CHOpt = new ChromeOptions();
			CHDes.setCapability(ChromeOptions.CAPABILITY, CHOpt);
			File CHPath = new File(
					"C:\\Backups\\Vijay S\\Download Folder\\chromedriver_win32 (2.42)\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", CHPath.getAbsolutePath());
			driver = new ChromeDriver();
		}
		WebDriverWait wait = new WebDriverWait(driver, 50000);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

		driver.get("https://test.salesforce.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		driver.findElement(By.id("username")).sendKeys("Prakash.mercina@kone.com.qa");
		driver.findElement(By.id("password")).sendKeys("Welcome3");
		driver.findElement(By.id("Login")).click();
		// executes in salesforce LIGHTINING
		// waiting for searchbox appears in homepage
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='Search Salesforce']")));
		// entering Opportunity in homepage searchbox
		driver.findElement(By.xpath("//*[@title='Search Salesforce']")).sendKeys(opportunity);
		// waiting for searchbox values to appears in homepage
		driver.findElement(By.xpath("//*[@title='Search Salesforce']")).sendKeys(Keys.RETURN);
		// waiting for Opportunity to appear in grid
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@title='"+opportunity+"'])[last()]")));
		// clicking on Opportunity
		driver.findElement(By.xpath("(//*[@title='"+opportunity+"'])[last()]")).click();
		// waiting for FL Tenders header in Opportunity
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='FL Tenders' and starts-with(text(),'FL Tenders')]")));
		// clicking on FL Tenders header in Opportunity
		driver.findElement(By.xpath("//*[@title='FL Tenders' and starts-with(text(),'FL Tenders')]")).click();
		// waiting for New FLTenderbutton in FL Tender page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='New FL Tender' and text()='New FL Tender']")));
		// clicking on New FLTenderbutton in FL Tender page
		driver.findElement(By.xpath("//*[@title='New FL Tender' and text()='New FL Tender']")).click();
		// waiting for iframe to appear
		WebElement switchframe1 = driver.findElement(By.xpath("//iframe[starts-with(@scrolling,'yes') and starts-with(@id,'vfFrameId_')]"));
		driver.switchTo().frame(switchframe1);
		WebElement switchframe2 = driver.findElement(By.id("clientTarget"));
		driver.switchTo().frame(switchframe2);
			System.out.println("is "+ProductRelease+" displaying:"+ driver.findElement(By.xpath("//*[text()='Product release for "+ProductRelease+"']/..//img[2]")).isDisplayed()+ "/ is OK displaying:"
						+ driver.findElement(By.xpath("//*[@class='pbn3' and text()='OK']")).isDisplayed());
		driver.findElement(By.xpath("//*[text()='Product release for "+ProductRelease+"']/..//img[2]")).click();
			System.out.println("Release "+ProductRelease+" is Selected");
		WebElement findElement = driver.findElement(By.xpath("//*[@class='pbn3' and text()='OK']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findElement);
			System.out.println("scrolled to OK button");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='pbOK']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='pbOK']")).click();
			System.out.println("Clicked on OK button");
		driver.findElement(By.xpath("//*[@data-ctcwgtname='CustomerID']")).clear();
			System.out.println("CustomerID existing value cleared");
		driver.findElement(By.xpath("//*[@data-ctcwgtname='EquipmentID']")).sendKeys(equipmentid);
			System.out.println("EquipmentID entered");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='CustomerID']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
//		driver.findElement(By.xpath("//*[@data-ctcwgtname='CustomerID']")).click();
		WebElement element_customerID = driver.findElement(By.xpath("//*[@data-ctcwgtname='CustomerID']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element_customerID);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='EquipmentID']")));
		WebElement element_EquipmentID = driver.findElement(By.xpath("//*[@data-ctcwgtname='EquipmentID']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element_EquipmentID);
			System.out.println("EquipmentID clicked");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='ic:EquipmentID']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
//		driver.findElement(By.xpath("//*[@data-ctcwgtname='ic:EquipmentID']")).click();
		WebElement element_equipmentID = driver.findElement(By.xpath("//*[@data-ctcwgtname='ic:EquipmentID']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element_equipmentID);
			System.out.println("Equipment loader clicked");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Add new equipment' or text()='Found no dataset']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='"+equipmentid+"']/..//img")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[text()='" + equipmentid + "']/..//img")).click();
		System.out.println("checkbox clicked");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
			if(equipment_ADDorChange.equalsIgnoreCase("add")) {
			// click on ADD
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='ic:Constant.!IcNew']")));
				driver.findElement(By.xpath("//*[@data-ctcwgtname='ic:Constant.!IcNew']")).click();
				System.out.println("ADD button clicked");
			} else if(equipment_ADDorChange.equalsIgnoreCase("change")) {
			// click on Change
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='ic:Constant.!IcDynamicForm']")));
				driver.findElement(By.xpath("//*[@data-ctcwgtname='ic:Constant.!IcDynamicForm']")).click();
				System.out.println("Change button clicked");
			}
		WebElement Elementtoscroll = driver.findElement(By.xpath("//*[text()='" + equipmentid + "']/../..//div/div[text()='Project']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", Elementtoscroll);
		System.out.println("scroll UP");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[text()='" + equipmentid + "']/../..//div/div[text()='Project']")).click();
			System.out.println("Project clicked");
		SimpleDateFormat input_dateformat = new SimpleDateFormat("dd/mm/yyyy");
		SimpleDateFormat output_dateformat = new SimpleDateFormat("dd/mm/yyyy");
		String MS5HODate_toChange = null;
		if(driver.findElement(By.xpath("//*[@data-ctcwgtname='DateHandoverMS5_1']")).getCssValue("background-image").contains("0191f5aaded042c33180d357113667fbefc81b95")) {
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
				System.out.println("MS5HODatetoChange:"+MS5HODate_toChange);
		}
			System.out.println("MS5HODate_Current: "+driver.findElement(By.xpath("//*[@data-ctcwgtname='DateHandoverMS5']/input")).getAttribute("value"));
		WebElement element_MS5HODate=driver.findElement(By.xpath("//*[@data-ctcwgtname='DateHandoverMS5']/input"));
		element_MS5HODate.clear();
			System.out.println("MS5HODate_Current Cleared");
		wait.until(ExpectedConditions.elementToBeClickable(element_MS5HODate));
		element_MS5HODate.sendKeys(MS5HODate_toChange);
			if(element_MS5HODate.getAttribute("value").isEmpty()) {
				element_MS5HODate.sendKeys(MS5HODate_toChange);
			}
			System.out.println("*****MS5HODate Changed to ="+MS5HODate_toChange);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='SalesOffice']/button")).click();
			System.out.println("SalesOffice drop down clicked");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//div[text()='"+salesoffice+"']")).click();
		System.out.println("SalesOffice value "+salesoffice+" Selected");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		WebElement element_equipment = driver.findElement(By.xpath("(//*[text()='" + equipmentid + "'])[last()-1]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element_equipment);
		System.out.println("Equipment clicked back");
//	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='Supervisor_Select']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='Supervisor_Select']")).click();
		System.out.println("Supervisor drop down clicked");
		String a[] = salesoffice.split(" ");
		String dd_SalesOffice=a[1].trim();
//		System.out.println("dd_SalesOffice: "+dd_SalesOffice);
//	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		WebElement element_supervisor = driver.findElement(By.xpath("//div[text()='"+dd_SalesOffice+"']/..//div[text()='"+supervisor_ResponsiblePreson+"']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element_supervisor);
		System.out.println(supervisor_ResponsiblePreson+" is clicked");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='EquipmentInService']/button")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='EquipmentInService']/button")).click();
		System.out.println("EquipmentInService drop down clicked");
//	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/div[text()='"+equipmentinService+"']")));
		driver.findElement(By.xpath("//div/div[text()='"+equipmentinService+"']")).click();
		System.out.println(equipmentinService+" is clicked in EquipmentInService");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='HydraulicElevCheck' and @data-ctctype='Radiobutton']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='HydraulicElevCheck' and @data-ctctype='Radiobutton']")).click();
		System.out.println("HydraulicElevCheck clicked (**need to work**)");
		Actions rightclick_equipment = new Actions(driver).contextClick(element_equipment);
		rightclick_equipment.build().perform();
		System.out.println("Rightclick performed in solution");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Open Templates']")));
		driver.findElement(By.xpath("//div[text()='Open Templates']")).click();
		System.out.println("open templates clicked in solution");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@data-ctcwgtname='ic_Constant__Closed'])[last()-1]")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("(//*[@data-ctcwgtname='ic_Constant__Closed'])[last()-1]")).click();
		System.out.println("Binary Templates: clicked");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-ctcwgtname='SearchString']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='SearchString']")).clear();
		System.out.println("clear search field value");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='SearchString']")).sendKeys(template);
		System.out.println(template + " search field value");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-ctcwgtname='SearchShared_2']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='SearchShared_2']/div[1]")).click();
		System.out.println("Shared templates (for all sales orgs.) Clicked");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[text()='" + template + "'])[last()]")));
		WebElement doubleclick_elementTemplate = driver.findElement(By.xpath("(//*[text()='" + template + "'])[last()]"));
		Actions doubleclick_template = new Actions(driver).doubleClick(doubleclick_elementTemplate);
		doubleclick_template.build().perform();
		System.out.println(template+" Template Clicked");
		List<WebElement> Elements_ConsistencyCheck_1 = driver.findElements(By.xpath("//div/div[text()='Project']/../..//div/div"));
		for (WebElement Element : Elements_ConsistencyCheck_1) {
			if (Element.getCssValue("background-image").contains("0191f5aaded042c33180d357113667fbefc81b95")) {
				System.out.println(Element.getCssValue("background-image"));
			} else if (Element.getCssValue("background-image").contains("cad9a93f6c879df1ed1373d2853634026ff3224f")) {
				System.out.println(Element.getCssValue("background-image"));
			}
		}
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='EquipmentID']")));
		WebElement element_pricingicon=driver.findElement(By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[3]/img"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element_pricingicon);
		System.out.println("scrolled to pricingicon");
		element_pricingicon.click();
		System.out.println("pricing icon Clicked");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		WebElement scrollto_Firstmaintenance = driver.findElement(By.xpath("//*[@data-ctcwgtname='FreeMaintenance']/button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scrollto_Firstmaintenance);
		System.out.println("scrolled to Firstmaintenance");
//	    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mf' and @class='ca05gp cz009']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='FreeMaintenance']/button")).click();
		System.out.println("First maintenance: Clicked");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//div[starts-with(text(),'" + FirstMaintenance + " Mois de gratuité ')]")).click();
		System.out.println("First maintenance: " + FirstMaintenance + " Clicked");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-ctcwgtname='icGraphic']")));
		WebElement scrollto_TMP_Discount = driver.findElement(By.xpath("//*[@data-ctcwgtname='icGraphic']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scrollto_TMP_Discount);
		System.out.println("scrolled UP to Discount");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-ctcwgtname='icGraphic']")));
		WebElement doubleclick_elementDiscount = driver.findElement(By.xpath("//*[text()='Project']/..//div[contains(text(),' %')]"));
		Actions doubleclick_discount = new Actions(driver).doubleClick(doubleclick_elementDiscount);
		doubleclick_discount.build().perform();
		System.out.println("double Clicked on discount");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-ctcwgtname='TMP_Discount']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='TMP_Discount']")).clear();
		System.out.println("TMP_Discount cleared");
		driver.findElement(By.xpath("//*[@data-ctcwgtname='TMP_Discount']")).sendKeys(discount);
		System.out.println("TMP_Discount entered as: " + discount);
		driver.findElement(By.xpath("//*[@data-ctcwgtname='pbOK']")).click();
		System.out.println("Discount pbOK clicked");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Project']/..//div[text()='" + discount + ".00 %']")));
		List<WebElement> Elements_PriceOverview = driver.findElements(By.xpath("//*[text()='Project']/..//*"));
		Float arrary[] = new Float[2];
		Float TargetPrice, Firstmaintenance, Discount = null, TenderPrice = null;
		for (WebElement Element : Elements_PriceOverview) {
			if (!Element.getText().isEmpty() || Element.getAttribute("value") != null) {
				if (Element.getAttribute("value") == null) {
					if (!Element.getText().contains("Project")) {
						if (!Element.getText().contains("%")) {
							String element_readTenderPrice = Element.getText().replaceAll("[€ .]", "");
							element_readTenderPrice = element_readTenderPrice.replace(",", ".");
							TenderPrice = Float.valueOf(element_readTenderPrice);
//							System.out.println("****TenderPrice:" + TenderPrice);
						} else {
							String element_readDiscount = Element.getText().replaceAll("[% ]", "");
							Discount = Float.valueOf(element_readDiscount);
//							System.out.println("****Discount:" + Discount);
						}
					}
				} else {
//	        		System.out.println(count1+". Attribute==>"+Element.getAttribute("value"));
					String element_read = Element.getAttribute("value").replaceAll("[€ .]", "");
					element_read = element_read.replace(",", ".");
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
		if(FirstMaintenance.equals("0")) {
			arrary[1] = 0f;
		}
		if (arrary[0] > arrary[1]) {
			TargetPrice = arrary[0];
			Firstmaintenance = arrary[1];
			System.out.println("**CONDTION_1** TargetPrice: "+TargetPrice);
//			System.out.println("**CONDTION_1** Firstmaintenance:"+Firstmaintenance);
		} else {
			TargetPrice = arrary[1];
			Firstmaintenance = arrary[0];
//			System.out.println("**CONDTION_2** TargetPrice: "+TargetPrice);
//			System.out.println("**CONDTION_2** Firstmaintenance:"+Firstmaintenance);
		}
		Float TargetMinusMaintenance = TargetPrice - Firstmaintenance;
		Float TargetplusDiscount = TargetMinusMaintenance - (TargetMinusMaintenance * (Discount / 100));
		Float TargetPriceFinal = TargetplusDiscount + Firstmaintenance;
		DecimalFormat roundoff = new DecimalFormat("##.00");
		System.out.println("TargetPriceFinal:" + roundoff.format(TargetPriceFinal) + " /TargetMinusMaintenance:"
				+ roundoff.format(TargetMinusMaintenance) + " /TargetplusDiscount:"
				+ roundoff.format(TargetplusDiscount));
		System.out.println(
				"COmpare TargetFinal: " + roundoff.format(TargetPriceFinal).equals(roundoff.format(TenderPrice)));
		WebElement scrollto_DetailbreakdownTab = driver.findElement(By.xpath("//*[text()='Detail breakdown']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scrollto_DetailbreakdownTab);
		System.out.println("scrolled up to DetailbreakdownTab");
		//PricingOverview, additional discount
		/*wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-ctcwgtname='nPricingOverview']/div[4]")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='nPricingOverview']/div[4]")).click();
		System.out.println("additional discount CLICKED");*/
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Detail breakdown']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[text()='Detail breakdown']")).click();
		System.out.println("Detailed breakdown Clicked");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-ctcwgtname='nCustomComboboxProductSelection']")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='nCustomComboboxProductSelection']")).click();
		System.out.println("Dropdown in DetailedbreakdownTAB Clicked");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Ropes']")));
		WebElement element_Ropes = driver.findElement(By.xpath("//*[text()='Ropes']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element_Ropes);
		System.out.println("ROPES CLICKED (**need to work**)");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='Ropes']")));
		driver.findElement(By.xpath("//*[@src='SMG?i=acdda3ea032315878f95d47164849ea79f364ad3&w=16&h=16']")).click();
		System.out.println("ShowTotalCostCalculationDetails CLICKED (**need to work**)");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		List<WebElement> Elements_showtotalcostFirstRow = driver.findElements(By.xpath("//*[text()='Subtotal']/..//*"));
		List<String> ls_TotalCostHeader=new LinkedList<>();
		List<Float> ls_TotalCostValue=new LinkedList<>();
		HashMap<String, Float> hm_data = new HashMap<String, Float>();
		/*for(WebElement Element_showtotalcostHeader:Elements_showtotalcostHeader) {
			if(!Element_showtotalcostHeader.getText().isEmpty())	{
				System.out.println("HEADER_Element_showtotalcostHeader:"+Element_showtotalcostHeader.getText());*/
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
/*			}
		}*/
	Float convertedvalue=null;
		for(WebElement Element_showtotalcostFirstRow:Elements_showtotalcostFirstRow) {
			if (Element_showtotalcostFirstRow.getAttribute("value") == null ) {
				if(!Element_showtotalcostFirstRow.getText().isEmpty())	{
//					System.out.println("getText:"+Element_showtotalcostFirstRow.getText());
						if(!Element_showtotalcostFirstRow.getText().contains("Subtotal")) {
							String getvalue=Element_showtotalcostFirstRow.getText().replaceAll("[€ % h]", "");
							getvalue = getvalue.replace(",", ".");
							convertedvalue = Float.valueOf(getvalue);
							ls_TotalCostValue.add(convertedvalue);
						}
				}
			} else if(!Element_showtotalcostFirstRow.getAttribute("value").isEmpty() && Element_showtotalcostFirstRow.getAttribute("value")!=null)	{
//					System.out.println("1stROW_getAttribute:"+Element_showtotalcostFirstRow.getAttribute("value"));
					String getvalue=Element_showtotalcostFirstRow.getAttribute("value").replaceAll("[€ % h .]", "");
					getvalue = getvalue.replace(",", ".");
					convertedvalue = Float.valueOf(getvalue);
					ls_TotalCostValue.add(convertedvalue);
			}
			/*if (!Element_showtotalcostFirstRow.getText().isEmpty() || Element_showtotalcostFirstRow.getAttribute("value") != null) {
				if (Element_showtotalcostFirstRow.getAttribute("value") == null) {
					if (!Element_showtotalcostFirstRow.getText().contains("Subtotal")) {
						System.out.println("getText:"+Element_showtotalcostFirstRow.getText());
					}
				} else if (!Element_showtotalcostFirstRow.getAttribute("value").isEmpty()) {
					System.out.println("getAttribute:"+Element_showtotalcostFirstRow.getAttribute("value"));
				}
			}*/
		}
		for(int i=0; i<14; i++) {
			hm_data.put(ls_TotalCostHeader.get(i), ls_TotalCostValue.get(i));
		}
		/*Float showtotal_TargetPrice= hm_data.get("Target Price");
		Float showtotal_Materialcosts= hm_data.get("Material costs");
		Float showtotal_MaterialcostSLCurrency= hm_data.get("Material cost (SL Currency)");*/
		Float showtotal_Referencehours= hm_data.get("Reference hours");
		Float showtotal_ITEfactor= hm_data.get("ITE factor");
		Float showtotal_InstallationHours= hm_data.get("Installation Hours");
		Float showtotal_Labourrate= hm_data.get("Labour rate");
		Float showtotal_LaborCosts= hm_data.get("Labor Costs");
		/*Float showtotal_Contigency= hm_data.get("Contigency");
		Float showtotal_OverheadRecovery= hm_data.get("Overhead Recovery");
		Float showtotal_FullCosts= hm_data.get("Full Costs");
		Float showtotal_TotalCost= hm_data.get("Total Cost");
		Float showtotal_RatioofLabor= hm_data.get("Ratio of Labor");
		Float showtotal_TenderPrice= hm_data.get("Tender Price");*/
		Float check_showtotal_Referencehours= showtotal_InstallationHours/showtotal_ITEfactor;
		Float check_showtotal_Labourrate= showtotal_LaborCosts/showtotal_InstallationHours;
		/*System.out.println("is_showtotal_ITEfactor: "+check_showtotal_ITEfactor+"/"+showtotal_ITEfactor);
		System.out.println("is_showtotal_LaborCosts "+check_showtotal_Labourrate+"/"+showtotal_Labourrate);
		System.out.println("is_showtotal_Referencehours "+check_showtotal_Referencehours+"/"+showtotal_Referencehours);*/
		Boolean is_showtotal_ITEfactor = roundoff.format(check_showtotal_ITEfactor).equals(roundoff.format(showtotal_ITEfactor));
		Boolean is_showtotal_Referencehours= roundoff.format(check_showtotal_Referencehours).equals(roundoff.format(showtotal_Referencehours));
		Boolean is_showtotal_Labourrate= roundoff.format(check_showtotal_Labourrate).equals(roundoff.format(showtotal_Labourrate));
		System.out.println("is_showtotal_ITEfactor: "+is_showtotal_ITEfactor+" /is_showtotal_Labourrate "+is_showtotal_Labourrate+" /is_showtotal_Referencehours "+is_showtotal_Referencehours);
		
		/*for (Entry<String, Float> entry : hm_data.entrySet()) {
			String key = entry.getKey();
			Float ValueStored = entry.getValue();
			System.out.println("key:"+key+" /ValuetoStore:"+ValueStored);
		}*/
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[5]/img")));
		WebElement element_ToWord = driver.findElement(By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[5]/img"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element_ToWord);
		System.out.println("ToWord icon CLICKED");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Print with KONE Logo']")));
		System.out.println("Wating till -Print with KONE Logo- displays");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'_Modular_Tender_Template.doc')]")));
		WebElement element_ModularTenderDOC = driver.findElement(By.xpath("//*[contains(text(),'_Modular_Tender_Template.doc')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element_ModularTenderDOC);
		wait.until(ExpectedConditions.visibilityOf(element_ModularTenderDOC));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[contains(text(),'_Modular_Tender_Template.doc')]")).click();
		System.out.println("_Modular_Tender_Template.doc CLICKED");
		WebElement element_PrintwithKONELogo = driver.findElement(By.xpath("//*[text()='Print with KONE Logo']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element_PrintwithKONELogo);
		wait.until(ExpectedConditions.visibilityOf(element_PrintwithKONELogo));
		driver.findElement(By.id("b3520_1")).click();
		/*WebElement element_CreatePrintOuticon = driver.findElement(By.xpath("//*[@data-ctcwgtname='PrintOut_c' and @data-ctctype='Toolbar']/div/img"));
		Actions builder = new Actions(driver);
	     builder.moveToElement( element_CreatePrintOuticon ).click( element_CreatePrintOuticon );
	     builder.perform();*/
		System.out.println("Create PrintOut icon CLICKED (**need to work**)");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Do you want to freeze printed version?')]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='"+FreezePrintedVersion+"']")));
		driver.findElement(By.xpath("//button[text()='"+FreezePrintedVersion+"']")).click();
		System.out.println(FreezePrintedVersion+" CLICKED in Do you want to freeze printed version?");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Information have been sent to the document server')]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		System.out.println("OK CLICKED");
		WebElement element_SaveandClose = driver.findElement(By.xpath("//*[@data-ctcwgtname='ToolBar' and @data-ctctype='Toolbar']/div[2]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element_SaveandClose);
		wait.until(ExpectedConditions.visibilityOf(element_SaveandClose));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='ToolBar' and @data-ctctype='Toolbar']/div[2]")).click();
		System.out.println("SaveandClose icon CLICKED");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-ctcwgtname='pbYes']")));
		driver.findElement(By.xpath("//*[@data-ctcwgtname='pb"+SaveandClose+"']")).click();
		System.out.println(SaveandClose+" CLICKED in SaveandClose");
		driver.findElement(By.xpath("//*[text()='Description:']/..//input")).sendKeys(StageProbability_Description);
		System.out.println(StageProbability_Description+" entered in Description field in stage / Probability window");
		driver.findElement(By.xpath("//*[@data-ctcwgtname='_TenderVersion.Stage__c']/button")).click();
		System.out.println("Clicked on Stage dropdown");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//div[text()='"+StageProbability_Stage+"']")).click();
		System.out.println(StageProbability_Stage+" Clicked in Stage dropdown");
		driver.findElement(By.xpath("//input[@data-ctcwgtname='_TenderVersion.Probability__c']")).clear();
		System.out.println("Probability: value Cleared");
		driver.findElement(By.xpath("//input[@data-ctcwgtname='_TenderVersion.Probability__c']")).sendKeys(StageProbability_probability);
		System.out.println("value entered in Probability field");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='gp' and @class='gp']")));
		driver.findElement(By.xpath("//*[text()='OK']")).click();
		System.out.println("OK Button Clicked");
		wait.until(elementToBeClickableInFrame(By.tagName("iframe"), By.xpath("//*[@title='Configurator']")));
		System.out.println("NewWait's Executed");
		driver.switchTo().defaultContent();
		System.out.println("switchedTodefaultContent");
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		System.out.println("switched to frame");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Configurator']")));
		System.out.println("Configurator button is visible");
		WebElement element_Configurator = driver.findElement(By.xpath("//*[@title='Configurator']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element_Configurator);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element_Configurator);
		System.out.println("Configurator Clicked");
		List<WebElement> frames=driver.findElements(By.tagName("iframe"));
		WebElement secondFrame=null;
			for(WebElement frame:frames) {
				if(frame.getAttribute("id").equals("clientTarget"));{
					secondFrame=frame;
					System.out.println("secondFrame:"+secondFrame.getAttribute("id"));
					break;
				}
			}
		driver.switchTo().frame(secondFrame);
		System.out.println("Switched to secondFrame");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='No']")));
		driver.findElement(By.xpath("//button[text()='No']")).click();
		System.out.println("NO clicked in New Version is available for this product");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[3]/img"))));
		System.out.println("pricingicon's visible");
		List<WebElement> Elements_ConsistencyCheck_2 = driver.findElements(By.xpath("//div/div[text()='Project']/../..//div/div"));
		for (WebElement Element : Elements_ConsistencyCheck_2) {
			if (Element.getCssValue("background-image").contains("0191f5aaded042c33180d357113667fbefc81b95")) {
				System.out.println(Element.getCssValue("background-image"));
			} else if (Element.getCssValue("background-image").contains("cad9a93f6c879df1ed1373d2853634026ff3224f")) {
				System.out.println(Element.getCssValue("background-image"));
			}
		}
		WebElement element_HandShakeicon=driver.findElement(By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div[9]/img"));
		element_HandShakeicon.click();
		System.out.println("HandShakeicon CLICKED");
	}
}