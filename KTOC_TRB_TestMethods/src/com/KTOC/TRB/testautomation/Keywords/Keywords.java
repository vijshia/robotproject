package com.KTOC.TRB.testautomation.Keywords;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.python.icu.impl.Assert;
import com.KTOC.TRB.testautomation.Utilities.KTOCTRBUtils;
import com.KTOC.TRB.testautomation.ObjectRepository.*;
import static com.KTOC.TRB.testautomation.ObjectRepository.pg_SF_Login.*;
import static com.KTOC.TRB.testautomation.ObjectRepository.pg_SF_HomePage.*;
import static com.KTOC.TRB.testautomation.ObjectRepository.pg_TBR_HomePage.*;
import static com.KTOC.TRB.testautomation.ObjectRepository.pg_TRB_ProjectOverview.*;
import static com.KTOC.TRB.testautomation.ObjectRepository.pg_TBR_PricingPage.*;
import static com.KTOC.TRB.testautomation.ObjectRepository.pg_TRB_DocumentsPage.*;

public class Keywords extends KTOCTRBUtils{
	
	/**
	 **Reuse method, it will enter username & password
	 * @param username: username to be entered
	 * @param password: password to be entered
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void logintoSalesforce() throws Exception{
		try {
			waitForVisibilityOfElementLocated(txt_userName);
			enteringValues(txt_userName, username);
			enteringValues(txt_password, password);
			clickonButton(btn_login);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Logon To Salesforce Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will CreateOpportunity, MapContactWithOpportunity, MapOpportunityWithFLTenders or Search Opportunity and click on New FLTender button
	 *@param opportunity: opportunity to be searched
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void createOpportunityORSearchOpportunity() throws Exception{
		try {
			By lnk_selectOpportunity = By.xpath("(//*[@title='"+opportunity+"'])[last()]");
			WebElement searchBox = gettingWebElement(txt_searchBox);
			wait.until(ExpectedConditions.visibilityOf(searchBox));
			if(opportunityCreateorSearch.equalsIgnoreCase("search")) {
				if(!searchBox.getAttribute("title").equals("Search Salesforce")) {
					waitForVisibilityOfElementLocated(lnk_toLigntning);
					clickonButton(lnk_toLigntning);
					waitForVisibilityOfElementLocated(txt_ligtningHomeSearchbox);
					System.out.println("ligtning HomePage displayed");
				} 
				enteringValues(txt_searchBox, opportunity);
				System.out.println("Opportunity Name:"+opportunity+ " entered in Search box");
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
			e.printStackTrace();
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
	public void addEquipmentIDElevator() throws Exception{
		try {
			By checkbox_Equipment = By.xpath("//*[text()='"+equipmentid+"']/..//img");
			By radio_ProductRelease = By.xpath("//*[text()='Product release for "+ProductRelease+"']/..//img[2]");
			WebElement switchframe1 = gettingWebElement(firstFrame);
			switchtoFrame(switchframe1);
			WebElement switchframe2 = gettingWebElement(secondFrame);
			switchtoFrame(switchframe2);
			if(!frontlineAssigned.equals("CANADA")) {
				clickonButton(radio_ProductRelease);
				System.out.println("Release "+ProductRelease+" is Selected");
				WebElement findElement_Ok = gettingWebElement(btn_productrelease_OK); //*[@data-ctcwgtname='pbOK']
				scrollIntoView_Javascript(findElement_Ok);
	//			System.out.println("scrolled to OK button");
				wait.until(ExpectedConditions.elementToBeClickable(findElement_Ok));
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				click_Javascript(findElement_Ok); 
				System.out.println("Clicked on OK button");
			}
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
			e.printStackTrace();
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
			e.printStackTrace();
			Assert.fail("Select ProjectTree Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will check HandoverMS5date and update handoverdate to greater date 
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
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
			e.printStackTrace();
			Assert.fail("Check HandOverDate Is Greater than InstallationDate Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will select SalesOffice
	 *@param salesoffice: salesoffice to be selected
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void checkSalesOfficeisSelected() throws Exception{
		try {
			By value_salesOffice = By.xpath("//div[text()='"+salesoffice+"']");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(dd_salesOffice); //*[@data-ctcwgtname='SalesOffice']/button
//			System.out.println("SalesOffice drop down clicked");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(value_salesOffice);
			System.out.println("SalesOffice value "+salesoffice+" Selected");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Check SalesOffice is Selected Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will enter additional fields in ProjectOverview for Canada
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void additionalfieldsinProjectOverviewforCanada() throws Exception{
			try {
				if(frontlineAssigned.equals("CANADA")) {
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					clickonButton(dd_seismicArea);
					waitForinvisibilityOfElementLocated(elementtoInvisible);
//					System.out.println(gettingWebElement(By.xpath("//div[text()='"+value_seismicArea+"']")).getAttribute("id"));
					WebElement element_seismicArea = gettingWebElement(By.xpath("//div[text()='"+value_seismicArea+"']"));
					click_Javascript(element_seismicArea);
					System.out.println(value_seismicArea+" value selected in SeismicArea");
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					enteringValues(txt_weeklyTeamCostforZone, weeklyTeamCostforZone);
					System.out.println(weeklyTeamCostforZone+" value entered in weeklyTeamCostforZone");
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					enteringValues(txt_weeklyTeamCostforRoomandBoard, weeklyTeamCostforRoomandBoard);
					System.out.println(weeklyTeamCostforRoomandBoard+" value entered in weeklyTeamCostforRoomandBoard");
				}
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Additional fields in ProjectOverview for Canada Failed due to: "+e);
			}
			}
	
	/**
	 **Reuse method, it will select Supervisor
	 *@param supervisor_ResponsiblePreson: supervisor to be selected
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void selectSupervisor() throws Exception{
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
			String dd_SalesOffice = null;
			if(frontlineAssigned.equals("FRANCE")) {
				dd_SalesOffice=a[1].trim();
			} else if(frontlineAssigned.equals("AUSTRALIA")) {
				dd_SalesOffice=a[0].trim();
			} else if(frontlineAssigned.equals("CANADA")) {
				if(salesoffice.equals("Montréal")) {
					dd_SalesOffice="CA25";
				} else if(salesoffice.equals("Québec City")) {
					dd_SalesOffice="CA21";
				} else if(salesoffice.equals("Sherbrooke")) {
					dd_SalesOffice="CA22";
				}
			}
//	        waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement element_ResponsiblePerson = driver.findElement(By.xpath("//div[text()='"+dd_SalesOffice+"']/..//div[text()='"+supervisor_ResponsiblePerson+"']"));
			click_Javascript(element_ResponsiblePerson);
			System.out.println(supervisor_ResponsiblePerson+" selected in Supervisor");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Select Supervisor Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will select equipmentinService
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void selectEquipmentInService() throws Exception{
		try {
			By value_equipmentinService = By.xpath("//div/div[text()='"+equipmentinService+"']");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement element_EquipmentInService = gettingWebElement(dd_equipmentinService);
			wait.until(ExpectedConditions.elementToBeClickable(element_EquipmentInService)); //*[@data-ctcwgtname='EquipmentInService']/button
			scrollIntoView_Javascript(element_EquipmentInService);
			element_EquipmentInService.click();
			waitForElementToBeClickable(value_equipmentinService);
			clickonButton(value_equipmentinService);
			System.out.println(equipmentinService+" is clicked in Equipment InService");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			if(frontlineAssigned.equals("FRANCE")) {
				waitForElementToBeClickable(radio_hydeaulicElevator);
				clickonButton(radio_hydeaulicElevator);
				System.out.println("HydraulicElevCheck clicked");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Select Equipment InService Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will rightclick equipment and click on template and clear existing template name and enter new template name and click on shared template radoi button
	 *@param template: template to be selected
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void selectTemplateToBeUploaded() throws Exception{
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
			WebElement element_TemplateSearch = gettingWebElement(txt_searchTemplate);
			wait.until(ExpectedConditions.visibilityOf(element_TemplateSearch)); //*[@data-ctcwgtname='SearchString']
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_TemplateSearch.clear();
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
			e.printStackTrace();
			Assert.fail("Select Template to be Uploaded Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will check Tender consistency
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void verifyTenderConsistency() throws Exception{
		try {
			List<WebElement> Elements_ConsistencyCheck_1 = gettingWebElementsfromList(consistencyCheckElement);
			for (WebElement Element : Elements_ConsistencyCheck_1) {
				if (Element.getCssValue("background-image").contains("0191f5aaded042c33180d357113667fbefc81b95")) {
					System.out.println("FAIL: Tender is NOT Consistent, value:"+Element.getCssValue("background-image"));
				} else if (Element.getCssValue("background-image").contains("cad9a93f6c879df1ed1373d2853634026ff3224f")) {
					System.out.println("PASS: Tender is Consistent, value:"+Element.getCssValue("background-image"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Verify Tender Consistency Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will get Tender number
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void getTenderNumber() throws Exception{
		try {
			String getTenderNo=gettingWebElement(text_tenderNumber).getAttribute("value");
			System.out.println("Tender # is :"+getTenderNo.trim());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Get TenderNo Failed due to:"+e);
		}
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
		if(FirstMaintenance.equals("0")) {
			withoutFirstMaintenance = FirstMaintenance;
		} 
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
		if(FirstMaintenance.equals("0")) {
			withoutFirstMaintenance = FirstMaintenance;
		} 
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
		try {
			if(frontlineAssigned.equals("AUSTRALIA")) {
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
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Verify Cost Calculated Successfully Falied due to:"+e);
		}
	}
	
	/**
	 **Reuse method, it will 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void VerifyPriceCalculatedSuccessfully_TobecheckedinFrance() throws Exception{
		if(frontlineAssigned.equals("AUSTRALIA")) {
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
	public void clickonCurrenciesTab() throws Exception{
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement scrollto_currenciesTab = gettingWebElement(tab_Currencies);
			scrollIntoView_Javascript(scrollto_currenciesTab);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(tab_Currencies);
			System.out.println("CurrenciesTab Clicked");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Click on Currencies Tab Failed due to:"+e);
		}
	}
	
	/**
	 **Reuse method, it will get ConversionFactor value
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public String conversionFactor;
	public void getConversionFactor() throws Exception{
		try {
			waitForVisibilityOfElementLocated(value_ConversionFactor);
			WebElement element_ConversionFactor=gettingWebElement(value_ConversionFactor);
				conversionFactor=element_ConversionFactor.getAttribute("value");
				System.out.println("Conversion Factor:"+conversionFactor);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Get ConversionFactor Failed due to:"+e);
		}
	}
	
	/**
	 **Reuse method, it will click on AdditionalDiscountIcon
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void clickonAdditionalDiscountIcon() throws Exception{
		try {
			wait.until(ExpectedConditions.visibilityOf(gettingWebElementsfromList(icon_additionalDiscount).get(3)));
			scrollIntoView_Javascript(gettingWebElementsfromList(icon_additionalDiscount).get(3));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(icon_additionalDiscount).get(3).click();
//		System.out.println(gettingWebElementsfromList(icon_additionalDiscount).get(3).getAttribute("id"));
			System.out.println("Additional Discount Icon CLICKED");
			waitForVisibilityOfElementLocated(header_RegionalDiscount);
//		System.out.println("header RegionalDiscount is visible");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Click on Additional Discount Icon Failed due to"+e);
		}
	}
	
	/**
	 **Reuse method, it will get RegionalDiscount from table
	 *@param firstMaintenance: FirstMaintenance to be entered
	 *@throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public HashMap<String, Float> hm_RegionalDiscountData;
	public void getRegionalDiscount(String firstMaintenance) throws Exception{
		try {
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
			Float final_Regionaldiscountoncomponent=read_TargetPrice_Base*(regionalDiscount/100);
			final_Regionaldiscountoncomponent=Float.valueOf(roundoff.format(final_Regionaldiscountoncomponent));
			Float final_TenderPrice=read_TargetPrice_Base-final_Regionaldiscountoncomponent;
			final_TenderPrice=Float.valueOf(roundoff.format(final_TenderPrice));
			System.out.println("is read_Regionaldiscountoncomponent_Percent VS regionalDiscount equal:"+read_Regionaldiscountoncomponent_Percent.equals(regionalDiscount)+" *** / read_Regionaldiscountoncomponent_Percent:"+read_Regionaldiscountoncomponent_Percent+" / regionalDiscount:"+regionalDiscount);
			System.out.println("is final_Regionaldiscountoncomponent VS read_Regionaldiscountoncomponent equal:"+final_Regionaldiscountoncomponent.equals(read_Regionaldiscountoncomponent)+" *** / final_Regionaldiscountoncomponent:"+final_Regionaldiscountoncomponent+" / read_Regionaldiscountoncomponent:"+read_Regionaldiscountoncomponent);
			System.out.println("is final_TenderPrice VS read_TargetPrice equal:"+final_TenderPrice.equals(read_TargetPrice)+" *** / final_TenderPrice:"+final_TenderPrice+" / read_TargetPrice:"+read_TargetPrice);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Get Regional Discount Failed due to:"+e);
		}
	}
	
	/**
	 **Reuse method, it will click Detail Breakdown Tab and checks ITE factor, Labour rate, Reference hours
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public HashMap<String, Float> hm_DetailBreakdownData;
	public void validateDetailBreakdownTab() throws Exception{
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement scrollto_DetailbreakdownTab = gettingWebElement(tab_detailBreakDown);
			scrollIntoView_Javascript(scrollto_DetailbreakdownTab);
			waitForVisibilityOfElementLocated(tab_detailBreakDown);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(tab_detailBreakDown);
			System.out.println("Detailed breakdown Clicked");
			waitForVisibilityOfElementLocated(dd_selectProject);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(dd_selectProject);
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
					if(frontlineAssigned.equals("FRANCE") || frontlineAssigned.equals("CANADA")) {
						getvalue=Element_showtotalcostFirstRow.getAttribute("value").replaceAll("[€ % h .]", "");
						getvalue = getvalue.replace(",", ".");
					} else if(frontlineAssigned.equals("AUSTRALIA")) {
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
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div")).get(4).click();
//			System.out.println("elementShowTotalCostCalculationDetail_CLICKED*****BACK="+driver.findElements(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div")).get(4).getAttribute("id"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
//			waitForinvisibilityOfElementLocated(header_ITEfactor);
//			System.out.println("header_ITEfactor disabled");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Validate Detail Breakdown Tab Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click ToConfiguration icon and select Project from projecttree and select SalesOffice and click on Pricing Icon
 	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void gotoConfigurationPageandChangeTheSalesOffice() throws Exception{
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
			checkSalesOfficeisSelected();
			pricingIconClick();
		} catch (Exception e) {;
			e.printStackTrace();
			Assert.fail("Goto Configuration Page and Change SalesOffice Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click ToWord icon and click on ModularTenderDOC.doc
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
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
			WebElement element_ModularTenderDOC = gettingWebElement(lnk_templateDOC);
			wait.until(ExpectedConditions.elementToBeClickable(element_ModularTenderDOC));
			scrollIntoView_Javascript(element_ModularTenderDOC);
			wait.until(ExpectedConditions.visibilityOf(element_ModularTenderDOC));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_ModularTenderDOC.click();
			if(frontlineAssigned.equals("FRANCE")) {
				By lnk_templateDOC_France = By.xpath("//*[contains(text(),'_Modular_Tender_Template.doc')]");
				gettingWebElement(lnk_templateDOC_France).click();
			} else if(frontlineAssigned.equals("CANADA")) {
				By lnk_templateDOC_Canada = By.xpath("//*[contains(text(),'CKQ TRB Tender Letter')]");
				gettingWebElement(lnk_templateDOC_Canada).click();
			} if(frontlineAssigned.equals("AUSTRALIA")) {
				By lnk_templateDOC_Australia = By.xpath("//*[text()='Tender Letter MOD TRB']");
				gettingWebElement(lnk_templateDOC_Australia).click();
			}
//			System.out.println("Modular_Tender_Template.doc clicked");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Goto Documents tab and Click Tender Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click printOut icon in toword screen
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
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
			if(frontlineAssigned.equals("FRANCE")) {
				waitForVisibilityOfElementLocated(text_freezePrint);
				waitForElementToBeClickable(btn_freezePrint);
				clickonButton(btn_freezePrint);
				System.out.println(FreezePrintedVersion+" clicked in Do you want to freeze printed version?");
			}
			waitForVisibilityOfElementLocated(text_infotoDocServer);
			WebElement element_OkbuttoninInformationtodocumentserver = gettingWebElement(btn_infotoDocServer);
			wait.until(ExpectedConditions.elementToBeClickable(element_OkbuttoninInformationtodocumentserver));
			element_OkbuttoninInformationtodocumentserver.click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Verify Successful Message Displayed Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click ClickSave and CloseButton in KTOC-TRB
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void clickSaveandCloseButton() throws Exception{
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
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(value_stage);
			WebElement element_enderVersionProbability = gettingWebElement(txt_probability);
			element_enderVersionProbability.clear();
			element_enderVersionProbability.sendKeys(StageProbability_probability);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(btn_stageProbability);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Click Save&Close button Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click Configurator button and navigate back to frames
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public Float Final_SalesPrice;
	public void compareSalesPricebetweenTenderPageandSalesforce() throws Exception{
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
		System.out.println("secondFrame:"+secondFrame.getAttribute("id")+"/framesize:"+frames.size());
						break;
					}
				}
			switchtoFrame(secondFrame);
//			System.out.println("Switched to secondFrame");
			if(!frontlineAssigned.equals("CANADA")) {
				WebElement element_NoButtoninNewVersionProduct = gettingWebElement(btn_NoButtoninNewVersionProduct);
				wait.until(ExpectedConditions.elementToBeClickable(element_NoButtoninNewVersionProduct));
				element_NoButtoninNewVersionProduct.click();
				System.out.println("NO clicked in New Version is available for this product");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Get SalesPrice from SalesForce Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will click HandShakeIcon
	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void handShake() throws InterruptedException{
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
			e.printStackTrace();
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
			System.out.println("pricing icon Clicked");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("PricingIcon Click Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will select FirstMaintenance dropdown value in PriceOverview tab
 	 *@throws Exception: For exception handling
	 *@author CON_SVIJAY02
	 */
	public void selectingFirstMaintenance(String FirstMaintenance) throws Exception{
		try {
			By value_firstMaintenance = null;
			if(frontlineAssigned.equals("FRANCE") || frontlineAssigned.equals("CANADA")) {
				value_firstMaintenance = By.xpath("//div[starts-with(text(),'" + FirstMaintenance + " Mois de gratuité ')]");
			} else if(frontlineAssigned.equals("AUSTRALIA")) {
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
			if(!FirstMaintenance.equals("0") && !frontlineAssigned.equals("CANADA")) {
				waitForVisibilityOfElementLocated(header_firstMaintenance);
			} else if(frontlineAssigned.equals("AUSTRALIA") && FirstMaintenance.equals("0")) {
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
			e.printStackTrace();
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
	public void selectingDiscount(String discount) throws Exception{
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement scrollto_TMP_Discount = gettingWebElement(btn_discountPencil);
			wait.until(ExpectedConditions.visibilityOf(scrollto_TMP_Discount)); //*[@data-ctcwgtname='icGraphic']
			scrollIntoView_Javascript(scrollto_TMP_Discount);
			wait.until(ExpectedConditions.visibilityOf(scrollto_TMP_Discount));
			WebElement doubleclick_elementDiscount = gettingWebElement(grid_discount);
			Actions doubleclick_discount = new Actions(driver).doubleClick(doubleclick_elementDiscount);
			doubleclick_discount.build().perform();
			WebElement scrollto_NewDicount = gettingWebElement(txt_discount); //*[@data-ctcwgtname='TMP_Discount']
			wait.until(ExpectedConditions.visibilityOf(scrollto_NewDicount)); 
			scrollto_NewDicount.clear();
			scrollto_NewDicount.sendKeys(discount);
			System.out.println("Discount entered as: " + discount);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(btn_discountOK); //*[@data-ctcwgtname='pbOK']
			By grid_discountisApplied = By.xpath("//*[text()='Project']/..//div[text()='"+discount+".00 %']");
//			System.out.println("grid_discountisApplied:"+gettingWebElement(grid_discountisApplied).getAttribute("id"));
			waitForpresenceOfElementLocated(grid_discountisApplied);
//			System.out.println("Discount applied to GRID");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Selecting Discount Failed due to: "+e);
		}
	}
	
	/**
	 **Reuse method, it will change TenderPrice in PriceOverview tab
	 *@author CON_SVIJAY02
	 */
	public void selectingTenderPrice(String tenderPrice) throws Exception{
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement scrollto_priceOverview = gettingWebElement(tab_priceOverview);
			wait.until(ExpectedConditions.visibilityOf(scrollto_priceOverview)); 
			scrollIntoView_Javascript(scrollto_priceOverview);
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
			scrollto_TenderPrice_T.sendKeys(tenderPrice);
		System.out.println("TenderPrice entered as: " + tenderPrice);
			clickonButton(btn_tenderPriceOK); 
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
	public static Float TenderPrice;
	public void checkingTargetPrice() throws Exception{
		try {
			waitForVisibilityOfElementLocated(grid_discount);
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
								if(frontlineAssigned.equals("FRANCE") || frontlineAssigned.equals("CANADA")) {
									element_readTenderPrice = element_readTenderPrice.replace(".", "");
									element_readTenderPrice = element_readTenderPrice.replace(",", ".");
								} else if(frontlineAssigned.equals("AUSTRALIA")) {
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
						if(frontlineAssigned.equals("FRANCE") || frontlineAssigned.equals("CANADA")) {
							element_read = element_read.replace(".", "");
							element_read = element_read.replace(",", ".");
						} else if(frontlineAssigned.equals("AUSTRALIA")) {
							element_read = element_read.replace(",", "");
						}
						Float converted = Float.valueOf(element_read);
//	        	System.out.println("converted: "+converted);
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
				if(arrary[1] == null) {
					arrary[1] = 0f;
				}
			}
			if (arrary[0] > arrary[1]) {
				TargetPrice = arrary[0];
				Firstmaintenance = arrary[1];
//System.out.println("CONDTION_1 TargetPrice: "+TargetPrice+", Firstmaintenance:"+Firstmaintenance);
			} else {
				TargetPrice = arrary[1];
				Firstmaintenance = arrary[0];
			System.out.println("CONDTION_2 TargetPrice: "+TargetPrice+", Firstmaintenance:"+Firstmaintenance);
			}
//System.out.println("istenderPrice=>"+istenderPrice);			
			if(istenderPrice) {
				Float DiscountFinal = ((((TargetPrice-Firstmaintenance)-(TenderPrice-Firstmaintenance))/(TargetPrice-Firstmaintenance))*100);
				System.out.println("TargetPrice:"+TargetPrice);
				System.out.println("Firstmaintenance:"+Firstmaintenance);
				System.out.println("TenderPrice:"+TenderPrice);
				System.out.println("DiscountFinal:"+ roundoff.format(DiscountFinal)+" / Current Discount:"+Discount);
				System.out.println("*** is FinalDiscount VS ApplicationDiscount Equal:"+roundoff.format(DiscountFinal).equals(roundoff.format(Discount))+" ***");
				istenderPrice = false;
			} else {
				/*Float TargetMinusMaintenance = TargetPrice - Firstmaintenance;
				Float TargetplusDiscount = TargetMinusMaintenance - (TargetMinusMaintenance * (Discount / 100));
				Float TargetPriceFinal = TargetplusDiscount + Firstmaintenance;*/
				Float TenderPriceFinal = ((TargetPrice-Firstmaintenance)-((TargetPrice-Firstmaintenance)*(Discount/100))+Firstmaintenance);
				System.out.println("TargetPrice:"+TargetPrice);
				System.out.println("Firstmaintenance:"+Firstmaintenance);
				System.out.println("Discount:"+Discount);
				System.out.println("*** TenderPriceFinal:" + roundoff.format(TenderPriceFinal)+" / CurrentTenderPrice:"+TenderPrice+" ***");
				System.out.println("is TenderPriceFinal VS ApplicationTenderPrice Equal: " + roundoff.format(TenderPriceFinal).equals(roundoff.format(TenderPrice))+" ***");	
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Checking TargetPrice Failed due to: "+e);
		}
	}
	
	
	
	public static final int TIME = 200;
	public static String Error = null;
	// public static WebDriver driver;
	public static Properties pro;
	public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";
	public static String environment;
	public static String AccountName = null;
	public static String OpportunityName = null;
	public static String Frontline = null;
	public static int TestStatus=1;
	public static String MandatoryField=null;
	public static String KTOCSalesPrice;
	public static String SalesForceSalesPrice;
	public static WebElement element;
	

	//Logon to Salesforce
	public static void LogonToSalesforce() throws Exception {
		TestStatus=1;
		if(TestStatus==1)
		{
		try {
			//call driver
			driver.manage().window().maximize();
			driver.get(LogOnPage.SalesForceURL);
			driver.manage().timeouts().implicitlyWait(TIME, TimeUnit.SECONDS);
			
			//Assign username and Password

/*			String UserName = Xls_Reader.getData("LoginData", "LogOnToKTOC", "UserName");
			String Password = Xls_Reader.getData("LoginData", "LogOnToKTOC", "Password");*/
			String UserName = "Prakash.mercina@kone.com.qa";
			String Password = "Welcome3";
			WaitTillElementToBeClickable("cssSelector", LogOnPage.UserNameField);
			EnterValues("cssSelector", LogOnPage.UserNameField, UserName);
			EnterValues("cssSelector", LogOnPage.PasswordField, Password);
			ClickOnElement("cssSelector", LogOnPage.LoginButton);
			WebElement PageTitle = driver.findElement(By.id(SalesForceData.PageTitle));
			if (PageTitle.isDisplayed()) {
			} else {
				driver.navigate().refresh();
			}
		} catch (Exception e) {
			TestStatus=0;
			TakeSnapShot("SalesForceLoginPage");
			Assert.fail("Login Interrupted " + e);
		}
		}
		else
		{
			Assert.fail("Test Skipped: Browser Launch Failed");
		}
	}

	//Change User Settings with SalesOrg
	public void ChangeSalesOrg()
	{
		TestStatus=1;
		if(TestStatus==1)
		{
		try 
		{
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Assert.fail("Login Interrupted" + e);
		}
		}
		else
		{
			Assert.fail("Test Skipped: Browser Launch Failed");
		}
	}

	//Logon to KTOC NEB
	public void LogonToKTOCNEB() throws Exception {
		TestStatus=1;
		if(TestStatus==1)
		{
		try {
			driver.manage().window().maximize();
			driver.get(LogOnPage.SalesForceURL);
			driver.manage().timeouts().implicitlyWait(TIME, TimeUnit.SECONDS);
			WaitTillElementToBeClickable("xpath", CreateProductPage.ProductPlatformField);
			//Thread.sleep(50000);
		}
		catch(Exception e)
		{
			TestStatus=0;
			TakeSnapShot("KTOCNEBLoginPage");
			Assert.fail("Login interrupted");
		}
		}
		else
		{
			Assert.fail("Test Skipped: Browser Launch Failed");
		}
	}
	
	//Generate Random Opportunity Number
	public static int RandomNumber() 
	{
		Random rand = new Random();
		return rand.nextInt(10000) + 1;
	}

		// Create Opportunity
	public static String CreateOpportunity() throws Exception 
	{
			if(TestStatus==1)
			{
			// Frontline = LogonToSalesforce().;
			String AccountName = "AutomationKTOC";
			// String OpportunityName=null;
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
			//Thread.sleep(10000);
			WaitTillClickable("xpath", SalesForceData.BusinessType);
			SelectDropDownValues("xpath", SalesForceData.BusinessType, "Modernization (TRB)");
			WaitTillClickable("xpath", SalesForceData.OpportunityName);
			Thread.sleep(5000);
			EnterTextbyChar("xpath", SalesForceData.OpportunityName, OpportunityName, 1);
			// WaitTillElementToBeClickable("xpath", SalesForceData.AccountNameField);
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
			
	public void MapContactWithOpportunity() throws Exception
	{
			if(TestStatus==1)
			{
			try
			{
			Thread.sleep(4000);
			WaitAndClickOnElement("xpath", SalesForceData.ContactsLink);
			WaitAndClickOnElement("xpath", SalesForceData.ContactSectionNewButton);
			WaitAndClickOnElement("xpath", SalesForceData.ContactOption);
			SelectDropDownText("xpath", SalesForceData.ContactRole,"Decision Maker");
			WaitAndClickOnElement("xpath", SalesForceData.SaveButton);
			}
			catch(Exception e)
			{
				TestStatus=0;
				Assert.fail("Mapping contact with opportunity fails");
			}
			}
			else
			{
				Assert.fail("Test Skipped: Opportunity Creation Failed");
			}
		}
	
		//Map Opportunity to FLTenders - FLTenders Tab
		public void MapOpportunityWithFLTenders() throws Exception
		{
			if(TestStatus==1)
			{
			try
			{
			WaitAndClickOnElement("xpath", SalesForceData.FLTendersLink);
			WaitAndClickOnElement("xpath", SalesForceData.FLTendersNewButton);
/*			Thread.sleep(10000);
			//MouseHover("xpath", SalesForceData.ReleaseWindow);
			MouseHover("xpath", SalesForceData.FocusRelease);
			ScrollUptoElement("xpath", SalesForceData.ScrollDownRelease);
			ScrollAndClickOnElement("xpath", SalesForceData.Release);
			WaitAndClickOnElement("xpath", SalesForceData.ReleaseOkButton);*/
			}
			catch(Exception e)
			{
				TestStatus=0;
				Assert.fail("Failed mapping opportunity with tender.");
			}
			}
			else
			{
				Assert.fail("Test Skipped: Opportunity Creation Failed");
			}
		}
/*		//Map Opportunity to FLTenders - FLTenders Tab
		public void MapOpportunityWithFLTenders()
		{
			if(TestStatus==1)
			{
			try
			{
				Thread.sleep(4000);
				MouseHover("xpath", SalesForceData.FLTendersTab);
				WaitAndClickOnElement("xpath", SalesForceData.FLTendersTab);
				WaitAndClickOnElement("xpath", SalesForceData.NewButton);
				WaitAndClickOnElement("xpath", SalesForceData.ContinueButton);
				Thread.sleep(5000);
				MouseHover("xpath", SalesForceData.RequiredInput);
				EnterValues("xpath", SalesForceData.RequiredInput, OpportunityName);
				WaitAndClickOnElement("xpath", SalesForceData.SaveButton);
				WaitAndClickOnElement("xpath", SalesForceData.VersionCheckBox);
				List<WebElement> elements= driver.findElements(By.css("css"));
				element = elements.get(list.size() - 1);
				//"div#s11:last-child"
				ScrollAndClickOnElement("xpath", SalesForceData.Release);
				WaitAndClickOnElement("xpath", SalesForceData.ReleaseOkButton);
			}
			catch(Exception e)
			{
				TestStatus=0;
				Assert.fail("Failed mapping Opportunity with FL Tender " + e);
			}
			}
			else
			{
				Assert.fail("Test Skipped: Failed Mapping The Opportunity");
			}
		}*/
		
	//To move to KTOC iFrame window
	public void SwitchToKTOCApp()
	{
		if(TestStatus==1)
		{
		try
		{
		
			SwitchToFramebyID(CreateProductPage.KTOCFrameId);
		//Thread.sleep(5000);
		}
		catch(Exception e)
		{
			TestStatus=0;
			Assert.fail("Failed switching to KTOC" + e);
		}
		}
		else
		{
			Assert.fail("Test Skipped: ");
		}
	}
	
	//Select Release Version
	public void ChooseReleaseVersionAndClickOk(String ReleaseNo)  //String ReleaseNo
	{
		if(TestStatus==1)
		{
		try
		{
		
		SwitchToFramebyID(CreateProductPage.KTOCFrameId);
		ScrollUptoElement("xpath", VersionPage.ReleaseOkButton);
		MouseHover("xpath", VersionPage.ReleaseVersionWindow);
		element = driver.findElement(By.xpath("//div[@data-ctcname='KNB_Major_Version_Selector']//div[@data-ctcname='KNB_Major_Version_Selector']/child::div[3]//div[text()='"+ReleaseNo+"']/preceding-sibling::img"));
		//element = driver.findElement(By.xpath("//div[@data-ctcname='KNB_Major_Version_Selector']//div[@data-ctcname='KNB_Major_Version_Selector']/child::div[3]//div[text()='Release 18.1']/preceding-sibling::img"));
		WaitAndClickElementDirectly(element);
		//element.click();
		WaitTillElementToBeDisplayed("xpath", VersionPage.ReleaseVersionWindow);
		ClickOkButton();
		//$x("//div[@data-ctcname='KNB_Major_Version_Selector']//div[@data-ctcname='KNB_Major_Version_Selector']/child::div[3]/div/div/../text()='Release 12.1'")
		}
		catch(Exception e)
		{
			TestStatus=0;
			Assert.fail("Failed switching to KTOC" + e);
		}
		}
		else
		{
			Assert.fail("Test Skipped: ");
		}
	}


	public void ClickOkButton()
	{
		try
		{
		Thread.sleep(4000);
		WaitAndClick("xpath", VersionPage.ReleaseOkButton);
		}
		catch(Exception e)
		{
			TestStatus=0;
			Assert.fail("Failed Clicking Ok Button" + e);
		}
	}
	
	//Select First Product From The Product Dropdown
	public String SelectProductPlatform() throws Exception
	{
		String ProductName = null;
		//TestStatus=1;
			if(TestStatus==1)
			{
			try
			{
			//SwitchToFrame(CreateProductPage.KTOCFrameId);
			//Thread.sleep(5000);
			WaitTillClickable("xpath",CreateProductPage.ProductPlatformField);
			ProductName=SelectFirstTextFromDropdown("xpath",CreateProductPage.ProductPlatformField,"xpath",CreateProductPage.ProductPlatformValue);
			//SelectTextValueFromDropdown("xpath",CreateProductPage.ProductPlatformField,"div",ProductName);
			WaitTillClickable("xpath",CreateProductPage.SalesOfficeField);
			Thread.sleep(7000);
/*			WaitTillClickable("xpath",CreateProductPage.ProductPlatformField);
			WaitAndClickOnElement("xpath",CreateProductPage.ProductPlatformField);
			Thread.sleep(5000);
			MouseHover("xpath",CreateProductPage.ProductPlatformValue);
			WaitAndClickOnElement("xpath",CreateProductPage.ProductPlatformValue);*/
			}
			catch(Exception e)
			{
				TestStatus=0;
				Assert.fail("Failed selecting the product platform." + e);
			}
			}
			else
			{
				Assert.fail("Test Skipped: ");
			}

			return ProductName;
	}
	
	//Select Product Platform With The Given Product Name
	public void SelectProductPlatformWithTheGivenProduct(String ProductName) throws Exception
	{
			if(TestStatus==1)
			{
			try
			{
			//SwitchToFrame(CreateProductPage.KTOCFrameId);
			//Thread.sleep(5000);
			WaitTillClickable("xpath",CreateProductPage.ProductPlatformField);
			SelectTextValueFromDropdown("xpath",CreateProductPage.ProductPlatformField,"div",ProductName);
			WaitTillClickable("xpath",CreateProductPage.SalesOfficeField);
			Thread.sleep(7000);
/*			WaitTillClickable("xpath",CreateProductPage.ProductPlatformField);
			WaitAndClickOnElement("xpath",CreateProductPage.ProductPlatformField);
			Thread.sleep(5000);
			MouseHover("xpath",CreateProductPage.ProductPlatformValue);
			WaitAndClickOnElement("xpath",CreateProductPage.ProductPlatformValue);*/
			}
			catch(Exception e)
			{
				TestStatus=0;
				Assert.fail("Failed selecting the product platform." + e);
			}
			}
			else
			{
				Assert.fail("Test Skipped: ");
			}
	}
	
	//Select Template
	public void SelectTemplate()
	{
		if(TestStatus==1)
		{
		try
		{
			Thread.sleep(3000);
			//WaitTillElementDisplayed("xpath",CreateProductPage.CreateProductButton);
			//ScrollUptoElement("xpath",CreateProductPage.TemplateExpand);
			MouseHover("xpath",CreateProductPage.TemplateExpand);
			WaitAndClickOnElement("xpath",CreateProductPage.TemplateExpand);
			WaitAndClickOnElement("xpath",CreateProductPage.AutomationTestingTemplate);
		}
		catch(Exception e)
		{
			TestStatus=0;
			Assert.fail("Failed selecting the template." + e);
		}
		}
		else
		{
			Assert.fail("Test Skipped: Selecting Product Platform Failed.");
		}
	}
	
	//Select Sales Office
	public void SelectSalesOffice() throws Exception
	{
		if(TestStatus==1)
		{
		try
		{
		//Thread.sleep(10000);
		//WaitTillClickable("xpath",CreateProductPage.SalesOfficeField);
		SelectTextValueFromDropdown("xpath",CreateProductPage.SalesOfficeField,CreateProductPage.DivNode,CreateProductPage.SalesOfficeValue);
		}
		catch(Exception e)
		{
			TestStatus=0;
			Assert.fail("Failed selecting the sales office." + e);
		}
		}
		else
		{
			Assert.fail("Test Skipped: Selecting Product Platform Failed.");
		}
	}
	
	public void ClickCreateProductButton() throws Exception
	{
		if(TestStatus==1)
		{
		try
		{
		ScrollUptoElement("xpath",CreateProductPage.SalesOfficeField);
		WaitAndClickOnElement("xpath",CreateProductPage.CreateProductButton);
		WaitTillVisible("xpath",TenderPage.SaveCloseIcon);
		}
		catch(Exception e)
		{
			TestStatus=0;
			TakeSnapShot("CreateProductPage");
			Assert.fail("Failed clicking Create Product button.");
		}
		}
		else
		{
			Assert.fail("Test Skipped: Selecting Product Platform Failed.");
		}
	}
	
	//Click Create Product Button Having Wrong Locator
	public void ClickWrongButton() throws Exception
	{
		if(TestStatus==1)
		{
		try
		{
		ScrollUptoElement("xpath",CreateProductPage.SalesOfficeField);
		WaitAndClickOnElement("xpath",CreateProductPage.WrongCreateProductButton);
		}
		catch(Exception e)
		{
			TestStatus=0;
			TakeSnapShot("CreateProductPage");
			Assert.fail("Failed clicking Create Product button.");
		}
		}
		else
		{
			Assert.fail("Test Skipped: Selecting Product Platform Failed.");
		}
	}
	//Get Tender No.
	public String GetTenderNo()
	{
		String Tender=null;
		if(TestStatus==1)
		{
		try
		{
			element=FindTheElement("xpath",CreateProductPage.TenderNo);
			Tender=element.getAttribute("value");
			Thread.sleep(20000);
			WaitTillClickable("xpath",TenderPage.SaveCloseIcon);
		}
		catch(Exception e)
		{
			TestStatus=0;
			//TakeSnapShot(Frontline,"SectionC");
			Assert.fail("Failed: ");
		}
		}
		else
		{
			Assert.fail("Test Skipped: ");
		}
		return Tender;
	}
	
	//Get Total Sales Price
	public String GetSalesPriceFromTenderPage()
	{
		if(TestStatus==1)
		{
		try
		{
			String SalesPrice=GetTextOf("xpath",TenderPage.TotalSalesPrice);
			String[] SplitPrice=SalesPrice.split("INR ", 2);
			KTOCSalesPrice=SplitPrice[1];
		}
		catch(Exception e)
		{
			TestStatus=0;
			//TakeSnapShot(Frontline,"SectionC");
			Assert.fail("Failed Getting KTOC Sales Price "+e);
		}
		}
		else
		{
			Assert.fail("Test Skipped: ");
		}
		return KTOCSalesPrice;
	}
	
	//Get Sales Price From Opportunity Page
	public String GetSalesPriceFromSalesForce()
	{
		if(TestStatus==1)
		{
		try
		{
			WaitAndClickOnElement("xpath",TenderPage.TenderPageOpportunity);
			ScrollUptoElement("xpath",SalesForceData.ProductList);
			String SalesPrice=GetTextOf("xpath",SalesForceData.TotalPrice);
			String[] SplitPrice= SalesPrice.split("EUR ", 2);
			SalesForceSalesPrice= SplitPrice[1];
		}
		catch(Exception e)
		{
			TestStatus=0;
			//TakeSnapShot(Frontline,"SectionC");
			Assert.fail("Failed Getting SalesForce Sales Price"+e);
		}
		}
		else
		{
			Assert.fail("Test Skipped: ");
		}
		return SalesForceSalesPrice;
	}
	
	//Verify And Compare Total Prices
	public String CompareTotalSalesPrice()
	{
		if(TestStatus==1)
		{
		try
		{
			if(KTOCSalesPrice.equalsIgnoreCase(SalesForceSalesPrice))
			{
				System.out.println("The Sales Price Matching");
			}
			else
			{
				Assert.fail("\n" +"  KTOC Sales Price: " + KTOCSalesPrice +  "\n"  +"SalesForce Sales Price: " +SalesForceSalesPrice);
			}
		}
		catch(Exception e)
		{
			TestStatus=0;
			//TakeSnapShot(Frontline,"SectionC");
			Assert.fail("The Total Price from KTOC is not matching with salesforce "+ e);
		}
		}
		else
		{
			Assert.fail("Test Skipped: ");
		}
		return "EUR "+ KTOCSalesPrice;
	}
	
	
	//Save Product
	public void SaveProduct() throws Exception
	{
		if(TestStatus==1)
		{
		try
		{
		Thread.sleep(5000);
		WaitTillVisible("xpath",TenderPage.SaveIcon);
		WaitAndClickOnElement("xpath",TenderPage.SaveIcon);
		WaitAndClickOnElement("xpath",TenderPage.SaveOKButton);
		}
		catch(Exception e)
		{
			TestStatus=0;
			Assert.fail("Failed saving the product.");
		}
		}
		else
		{
			Assert.fail("Test Skipped: Product Creation Failed.");
		}
	}

	//Save And Exit The Page In One Click
	public void SaveAndExitKTOC()
	{
		if(TestStatus==1)
		{
		try
		{
		WaitTillClickable("xpath",TenderPage.SaveCloseIcon);
		WaitAndClick("xpath",TenderPage.SaveCloseIcon);
		MouseHover("xpath",TenderPage.SaveCloseYesButton);
		WaitAndClickOnElement("xpath",TenderPage.SaveCloseYesButton);
		WaitTillVisible("xpath",TenderPage.CloseOKButton);
		WaitAndClick("xpath",TenderPage.CloseOKButton);
		}
		catch(Exception e)
		{
			TestStatus=0;
			Assert.fail("Failed closing KTOC."+e);
		}
		}
		else
		{
			Assert.fail("Test Skipped: Product Creation Failed.");
		}
	}
	
	public void CloseBrowser() 
	{
		try
		{
			driver.quit();
		} 
		catch(Exception e)
		{
			Assert.fail("Failed closing KTOC.");
		}
	}
	
	//Method Template
	public void Template()
	{
		if(TestStatus==1)
		{
		try
		{
			
		}
		catch(Exception e)
		{
			TestStatus=0;
			//TakeSnapShot(Frontline,"SectionC");
			Assert.fail("Failed: ");
		}
		}
		else
		{
			Assert.fail("Test Skipped: ");
		}
	}
	
}
