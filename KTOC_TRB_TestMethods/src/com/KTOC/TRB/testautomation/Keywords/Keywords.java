package com.KTOC.TRB.testautomation.Keywords;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
//import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

public class Keywords extends KTOCTRBUtils {

	/**
	 ** Reuse method, it will enter username & password
	 * @param username: username to be entered
	 * @param password: password to be entered
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void logintoSalesforce() throws Exception {
		try {
			waitForVisibilityOfElementLocated(txt_userName);
			enteringValues(txt_userName, username);
			enteringValues(txt_password, password);
			clickonButton(btn_login);
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Logon To Salesforce Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will CreateOpportunity, MapContactWithOpportunity,
	 * MapOpportunityWithFLTenders or Search Opportunity and click on New FLTender
	 * button
	 * @param opportunity: opportunity to be searched
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void createOpportunityORSearchOpportunity() throws Exception {
		try {
			By lnk_selectOpportunity = By.xpath("(//*[@title='" + opportunity + "'])[last()]");
			WebElement searchBox = gettingWebElement(txt_searchBox);
			wait.until(ExpectedConditions.visibilityOf(searchBox));
			if (opportunityCreateorSearch.equalsIgnoreCase("search")) {
				if (!searchBox.getAttribute("title").equals("Search Salesforce")) {
					waitForVisibilityOfElementLocated(lnk_toLigntning);
					clickonButton(lnk_toLigntning);
					waitForVisibilityOfElementLocated(txt_ligtningHomeSearchbox);
					System.out.println("ligtning HomePage displayed");
				}
				enteringValues(txt_searchBox, opportunity);
				System.out.println("Opportunity Name:" + opportunity + " entered in Search box");
				enteringValues(txt_searchBox, Keys.RETURN);
				waitForVisibilityOfElementLocated(lnk_selectOpportunity);
				clickonButton(lnk_selectOpportunity);
				System.out.println(opportunity + " has been clicked");
				waitForVisibilityOfElementLocated(header_flTender);
				/*
				 * waitForElementToBeClickable(header_flTender);
				 * scrollIntoView_Javascript(gettingWebElement(header_flTender));
				 * waitForVisibilityOfElementLocated(header_flTender);
				 */
				waitForElementToBeClickable(header_flTender);
				clickonButton(header_flTender);
//				System.out.println("clicked on FL Tenders header in Opportunity");
				waitForVisibilityOfElementLocated(btn_flTender);
				clickonButton(btn_flTender);
//				System.out.println("clicked on New FLTender button in FL Tender page");
			} else {
				if (!searchBox.getAttribute("title").equals("Search...")) {
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
			Assert.fail("CreateOpportunity OR SearchOpportunity Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will navigate to KTOCTRB page
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void navigatetoKTOCTRB() throws Exception {
		try {
			By radio_ProductRelease = By.xpath("//*[text()='Product release for " + ProductRelease + "']/..//img[2]");
			WebElement switchframe1 = gettingWebElement(firstFrame);
			switchtoFrame(switchframe1);
			WebElement switchframe2 = gettingWebElement(secondFrame);
			switchtoFrame(switchframe2);
			if (!frontlineAssigned.equals("CANADA")) {
				clickonButton(radio_ProductRelease);
				System.out.println("Release " + ProductRelease + " is Selected");
				WebElement findElement_Ok = gettingWebElement(btn_productrelease_OK); // *[@data-ctcwgtname='pbOK']
				scrollIntoView_Javascript(findElement_Ok);
				// System.out.println("scrolled to OK button");
				wait.until(ExpectedConditions.elementToBeClickable(findElement_Ok));
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				click_Javascript(findElement_Ok);
				System.out.println("Clicked on OK button");
			}
		} catch (Exception e) {
//		e.printStackTrace();
		Assert.fail("selectMultipleEquipment Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will add/change equipment
	 * @param ProductRelease: Release number of the application
	 * @param equipment_ADDorChange: equipment to be added or changed
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void addEquipmentIDElevator() throws Exception {
		try {
			By checkbox_Equipment = By.xpath("//*[text()='" + equipmentid + "']/..//img");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			click_Javascript(gettingWebElement(txt_CustomerID));
//			clickonButton(txt_CustomerID);
			if (!isMultipleEquipment) {
				WebElement element_customerID = gettingWebElement(txt_CustomerID); // *[@data-ctcwgtname='CustomerID']
				wait.until(ExpectedConditions.visibilityOf(element_customerID));
				element_customerID.clear();
				// System.out.println("CustomerID existing value cleared");
				WebElement element_EquipmentID = gettingWebElement(txt_EquipmentID); // *[@data-ctcwgtname='EquipmentID']
				element_EquipmentID.clear();
				element_EquipmentID.sendKeys(equipmentid);
				System.out.println(equipmentid + " EquipmentID entered");
				wait.until(ExpectedConditions.elementToBeClickable(element_customerID));
				waitForElementToBeClickable(txt_CustomerID);
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				click_Javascript(gettingWebElement(txt_CustomerID));
				// clickonButton(txt_CustomerID);
				wait.until(ExpectedConditions.elementToBeClickable(element_EquipmentID));
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				element_EquipmentID.click();
				// System.out.println("EquipmentID clicked");
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
				if (equipment_ADDorChange.equalsIgnoreCase("add")) {
					addEquipment();
				} else if (equipment_ADDorChange.equalsIgnoreCase("change")) {
					changeEquipment();
				}
			}
			/*
			 * WebElement element_customerID1 = gettingWebElement(txt_CustomerID);
			 * wait.until(ExpectedConditions.elementToBeClickable(element_customerID1));
			 * if(!element_customerID1.getAttribute("value").isEmpty()) {
			 * element_customerID1.clear();
			 * wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(
			 * element_customerID1, "value"))); }
			 * wait.until(ExpectedConditions.elementToBeClickable(txt_CustomerID));
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * click_Javascript(gettingWebElement(txt_EquipmentID));
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * enteringValues(txt_CustomerID, customerid);
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * click_Javascript(gettingWebElement(txt_CustomerID));
			 * System.out.println("CustomerID: "+customerid+" entered");
			 */
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Add EquipmentID in Elevator Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will enter equipmentID in Add New Equipment lookup and click search Equipment
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void searchEquipmentinAddNewEquipmentLookup() throws Exception {
		try {
			waitForVisibilityOfElementLocated(txt_EquipmentIDPopUpT);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElement(txt_EquipmentIDPopUpT).clear();
			enteringValues(txt_EquipmentIDPopUpT, equipmentid);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(lnk_SearchEquipment);
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("search Equipment in Add New Equipment LookupFailed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click on add button in Add New equipment lookup
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void addEquipment() throws Exception {
		try {
			WebElement element_EquipmentIAddButton = gettingWebElement(btn_addEquipment); // *[@data-ctcwgtname='ic:Constant.!IcNew']
			wait.until(ExpectedConditions.elementToBeClickable(element_EquipmentIAddButton));
			element_EquipmentIAddButton.click();
			System.out.println("ADD button clicked");
			wait.until(ExpectedConditions.invisibilityOf(element_EquipmentIAddButton));
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Add Equipment Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click on change button in Add New equipment lookup
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void changeEquipment() throws Exception {
		try {
			WebElement element_EquipmentIChangeButton = gettingWebElement(btn_changeEquipment); // *[@data-ctcwgtname='ic:Constant.!IcDynamicForm']
			wait.until(ExpectedConditions.elementToBeClickable(element_EquipmentIChangeButton));
			element_EquipmentIChangeButton.click();
			System.out.println("Change button clicked");
			wait.until(ExpectedConditions.invisibilityOf(element_EquipmentIChangeButton));
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Change Equipment Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will select Project in projecttree
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void selectProjectTree() throws Exception {
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
			Assert.fail("Select ProjectTree Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will check HandoverMS5date and update handoverdate to greater date
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void checkHandOverDateIsGreaterThanInstallationDate() throws Exception {
		try {
			selectProjectTree();
			SimpleDateFormat input_dateformat = new SimpleDateFormat("dd/mm/yyyy");
			SimpleDateFormat output_dateformat = new SimpleDateFormat("dd/mm/yyyy");
			String MS5HODate_toChange = null;
			if (gettingWebElement(date_DateHandoverMS5).getCssValue("background-image")
					.contains("0191f5aaded042c33180d357113667fbefc81b95")) {
				String getDate = null;
				Date formatedDate = null;
				List<WebElement> elements_InstallationDate = driver
						.findElements(By.xpath("//div[text()='Project']/..//input"));
				wait.until(ExpectedConditions.visibilityOfAllElements(elements_InstallationDate));
				for (WebElement element : elements_InstallationDate) {
					if (!element.getAttribute("value").isEmpty()) {
						getDate = element.getAttribute("value");
						break;
					}
				}
				try {
					formatedDate = input_dateformat.parse(getDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				formatedDate = DateUtils.addDays(formatedDate, MS5HODatetoChange);
				MS5HODate_toChange = output_dateformat.format(formatedDate);
					System.out.println("MS5HODatetoChange:"+MS5HODate_toChange);
//			}
			System.out.println("Current MS5HO Date: " + gettingWebElement(txt_DateHandoverMS5).getAttribute("value"));
			WebElement element_MS5HODate = gettingWebElement(txt_DateHandoverMS5); // *[@data-ctcwgtname='DateHandoverMS5']/input
			element_MS5HODate.clear();
//			System.out.println("MS5HODate_Current Cleared");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(By.xpath("//*[@data-ctcwgtname='DateTenderPriceReference']"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_MS5HODate.click();
			if(browser.equalsIgnoreCase("ch")) {
				wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(gettingWebElement(txt_DateHandoverMS5), "value")));
				if(gettingWebElement(txt_DateHandoverMS5).getAttribute("value").isEmpty()) {
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					enteringValues(txt_DateHandoverMS5, MS5HODate_toChange);
				}
			} else {
				wait.until(ExpectedConditions.elementToBeClickable(element_MS5HODate));
				element_MS5HODate.sendKeys(MS5HODate_toChange);
			}
			if (element_MS5HODate.getAttribute("value").isEmpty()) {
				element_MS5HODate.sendKeys(MS5HODate_toChange);
			}
			System.out.println("MS5HODate Changed to: " + MS5HODate_toChange);
		}	
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Check HandOverDate Is Greater than InstallationDate Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will select SalesOffice
	 * @param salesoffice: salesoffice to be selected
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public String selectedSalesOffice;

	public void checkSalesOfficeisSelected() throws Exception {
		try {
			By value_salesOffice = By.xpath("//div[text()='" + salesoffice + "']");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(dd_salesOffice); // *[@data-ctcwgtname='SalesOffice']/button
//			System.out.println("SalesOffice drop down clicked");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(value_salesOffice);
			selectedSalesOffice = salesoffice;
			System.out.println("SalesOffice value " + salesoffice + " Selected");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Check SalesOffice is Selected Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will enter additional fields in ProjectOverview for Canada
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void additionalfieldsinProjectOverviewforCanada() throws Exception {
		try {
			if (frontlineAssigned.equals("CANADA")) {
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				clickonButton(dd_seismicArea);
				waitForinvisibilityOfElementLocated(elementtoInvisible);
//					System.out.println(gettingWebElement(By.xpath("//div[text()='"+value_seismicArea+"']")).getAttribute("id"));
				WebElement element_seismicArea = gettingWebElement(
						By.xpath("//div[text()='" + value_seismicArea + "']"));
				click_Javascript(element_seismicArea);
			System.out.println(value_seismicArea + " selected in SeismicArea");
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				enteringValues(txt_weeklyTeamCostforZone, weeklyTeamCostforZone);
//			System.out.println(weeklyTeamCostforZone+" value entered in weeklyTeamCostforZone");
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				enteringValues(txt_weeklyTeamCostforRoomandBoard, weeklyTeamCostforRoomandBoard);
//			System.out.println(weeklyTeamCostforRoomandBoard+" value entered in weeklyTeamCostforRoomandBoard");
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				clickonButton(txt_numberOfCrews);
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				clickonButton(txt_numberOfCrews);
				if(!(Float.valueOf(gettingWebElement(txt_weeklyTeamCostforRoomandBoard).getAttribute("value"))).equals(Float.valueOf(weeklyTeamCostforRoomandBoard))) {
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					enteringValues(txt_weeklyTeamCostforRoomandBoard, weeklyTeamCostforRoomandBoard);
				}
				if(!(Float.valueOf(gettingWebElement(txt_weeklyTeamCostforZone).getAttribute("value"))).equals(Float.valueOf(weeklyTeamCostforZone))) {
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					enteringValues(txt_weeklyTeamCostforZone, weeklyTeamCostforZone);
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Additional fields in ProjectOverview for Canada Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will select Supervisor
	 * @param supervisor_ResponsiblePreson: supervisor to be selected
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void selectSupervisor() throws Exception {
		try {
			By tree_equipmentID = By.xpath("(//*[text()='" + equipmentid + "'])[last()-1]");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement element_equipment = gettingWebElement(tree_equipmentID);
			click_Javascript(element_equipment);
//			System.out.println("Equipment clicked back");
			if (frontlineAssigned.equals("AUSTRALIA")) {
				WebElement element_customerID1 = gettingWebElement(txt_CustomerID);
				wait.until(ExpectedConditions.elementToBeClickable(element_customerID1));
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				element_customerID1.clear();
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				enteringValues(txt_CustomerID, customerid);
				System.out.println("CustomerID: " + customerid + " entered");
			}
			WebElement element_Supervisor = gettingWebElement(dd_supervisor); // *[@data-ctcwgtname='Supervisor_Select']
			wait.until(ExpectedConditions.elementToBeClickable(element_Supervisor));
			element_Supervisor.click();
//			System.out.println("Supervisor drop down clicked");
			String a[] = salesoffice.split(" ");
			String dd_SalesOffice = null;
			if (frontlineAssigned.equals("FRANCE")) {
				dd_SalesOffice = a[1].trim();
			} else if (frontlineAssigned.equals("AUSTRALIA")) {
				dd_SalesOffice = a[0].trim();
			} else if (frontlineAssigned.equals("CANADA")) {
				if (salesoffice.equals("Montréal")) {
					dd_SalesOffice = "CA25";
				} else if (salesoffice.equals("Québec City")) {
					dd_SalesOffice = "CA21";
				} else if (salesoffice.equals("Sherbrooke")) {
					dd_SalesOffice = "CA22";
				}
			}
//	        waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement element_ResponsiblePerson = driver.findElement(By.xpath(
					"//div[text()='" + dd_SalesOffice + "']/..//div[text()='" + supervisor_ResponsiblePerson + "']"));
			click_Javascript(element_ResponsiblePerson);
			System.out.println(supervisor_ResponsiblePerson + " selected in Supervisor");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Select Supervisor Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will select equipmentinService
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void selectEquipmentInService() throws Exception {
		try {
			By value_equipmentinService = By.xpath("//div/div[text()='" + equipmentinService + "']");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement element_EquipmentInService = gettingWebElement(dd_equipmentinService);
			wait.until(ExpectedConditions.elementToBeClickable(element_EquipmentInService)); // *[@data-ctcwgtname='EquipmentInService']/button
			scrollIntoView_Javascript(element_EquipmentInService);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_EquipmentInService.click();
			waitForElementToBeClickable(value_equipmentinService);
			clickonButton(value_equipmentinService);
			System.out.println(equipmentinService + " is clicked in Equipment InService");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			if (frontlineAssigned.equals("FRANCE") && !equipmentinService.contains("Escalator")) {
				waitForElementToBeClickable(radio_hydeaulicElevator);
				clickonButton(radio_hydeaulicElevator);
				System.out.println("HydraulicElevCheck clicked");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Select Equipment InService Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will rightclick equipment and click on template and clear
	 * existing template name and enter new template name and click on shared
	 * template radoi button
	 * 
	 * @param template: template to be selected
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void selectTemplateToBeUploaded() throws Exception {
		try {
			By tree_equipmentID = By.xpath("//*[text()='" + equipmentid + "']");
			By lnk_template = By.xpath("(//*[text()='" + template + "'])[last()]");
			WebElement element_equipment = gettingWebElement(tree_equipmentID); // driver.findElement(By.xpath("//*[text()='"
																				// + equipmentid + "']"));
//			System.out.println("Equipment identified for RightClick");
			Actions rightclick_equipment = new Actions(driver).contextClick(element_equipment);
			rightclick_equipment.build().perform();
			System.out.println("Rightclick performed on Equipment");
			waitForElementToBeClickable(lnk_openTemplates);
			clickonButton(lnk_openTemplates);
			System.out.println("Open Templates clicked");
			if (!isMultipleEquipment) {
				waitForElementToBeClickable(lnk_binaryTemplates); // (//*[@data-ctcwgtname='ic_Constant__Closed'])[last()-1]
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				clickonButton(lnk_binaryTemplates);
			}
			WebElement element_TemplateSearch = gettingWebElement(txt_searchTemplate);
			wait.until(ExpectedConditions.visibilityOf(element_TemplateSearch)); // *[@data-ctcwgtname='SearchString']
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_TemplateSearch.clear();
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_TemplateSearch.sendKeys(template);
			System.out.println(template + " Entered in search");
			WebElement element_SharedTemplateAllOrg = gettingWebElement(radio_sharedTemplate); // *[@data-ctcwgtname='SearchShared_2']
			wait.until(ExpectedConditions.visibilityOf(element_SharedTemplateAllOrg));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			click_Javascript(element_SharedTemplateAllOrg); // *[@data-ctcwgtname='SearchShared_2']/div[1]
			System.out.println("Shared templates radio button Clicked");
			if (isMultipleEquipment) {
				waitForElementToBeClickable(txt_searchTemplate);
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				clickonButton(txt_searchTemplate);
			}
			waitForVisibilityOfElementLocated(lnk_template);
			WebElement doubleclick_elementTemplate = gettingWebElement(lnk_template);
			Actions doubleclick_template = new Actions(driver).doubleClick(doubleclick_elementTemplate);
			doubleclick_template.build().perform();
			System.out.println(template + " Template Clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Select Template to be Uploaded Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will check Tender consistency
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void verifyTenderConsistency() throws Exception {
		try {
			List<WebElement> Elements_ConsistencyCheck_1 = gettingWebElementsfromList(consistencyCheckElement);
			wait.until(ExpectedConditions.visibilityOfAllElements(Elements_ConsistencyCheck_1));
			for (WebElement Element : Elements_ConsistencyCheck_1) {
				if (Element.getCssValue("background-image").contains("0191f5aaded042c33180d357113667fbefc81b95")) {
					System.out.println(
							"FAIL: Tender is NOT Consistent, value:" + Element.getCssValue("background-image"));
				} else if (Element.getCssValue("background-image")
						.contains("cad9a93f6c879df1ed1373d2853634026ff3224f")) {
					System.out.println("PASS: Tender is Consistent, value:" + Element.getCssValue("background-image"));
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Verify Tender Consistency Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will get Tender number
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void getTenderNumber() throws Exception {
		try {
			String getTenderNo = gettingWebElement(text_tenderNumber).getAttribute("value");
			System.out.println("Tender # is :" + getTenderNo.trim());
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Get TenderNo Failed due to:" + e);
		}
	}

	/**
	 *** pricingIconClick method is the prerequisite for Pricing screen*** Reuse
	 * method, it will select Discount & FirstMaintenance
	 * @param discount: discount to be entered
	 * @param FirstMaintenance: FirstMaintenance to be entered
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void CheckTenderPriceAfterDiscountUpdate(String discount, String FirstMaintenance) throws Exception {
		selectingDiscount(discount);
		selectingFirstMaintenance(FirstMaintenance);
		/*
		 * if(FirstMaintenance.equals("0")) { withoutFirstMaintenance =
		 * FirstMaintenance; }
		 */
	}

	/**
	 *** pricingIconClick method is the prerequisite for Pricing screen*** Reuse
	 * method, it will select TenderPrice & FirstMaintenance
	 * @param tenderPrice: tenderPrice to be entered
	 * @param FirstMaintenance: FirstMaintenance to be entered
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void VerifyDiscountByChangingTheTenderPrice(String tenderPrice, String FirstMaintenance) throws Exception {
		selectingTenderPrice(tenderPrice);
		selectingFirstMaintenance(FirstMaintenance);
		/*
		 * if(FirstMaintenance.equals("0")) { withoutFirstMaintenance =
		 * FirstMaintenance; }
		 */
	}

	/**
	 ** Reuse method, it will Get TargetPrice from table
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void GetTargetPrice() throws Exception {
		checkingTargetPrice();
	}
	
	/**
	 ** Reuse method, it will enter discount and check MaximumDiscountLimitExceeded pop-up appears
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public Boolean maximumDiscountLimitExceeded = true;
	public void CheckingMaximumDiscountLimitExceeded(String discount) throws Exception {
		maximumDiscountLimitExceeded = false;
		pricingIconClick();
		waitForVisibilityOfElementLocated(tab_priceOverview);
		waitForinvisibilityOfElementLocated(elementtoInvisible);
		clickonButton(tab_priceOverview);
		selectingDiscount(discount);
		maximumDiscountLimitExceeded = true;
		maximumDiscountLimitExceedPopup();
	}

	/**
	 ** Reuse method, it will click on AdditionalDiscountIcon
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void verifyRegionalDiscountDisplayedCorrectly() throws Exception {
		clickonAdditionalDiscountIcon();
	}

	/**
	 ** Reuse method, it will get regionaldiscount
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void verifyTargetPriceDisplayedCorrectly() throws Exception { // String firstMaintenance
		getRegionalDiscountFullGrid();
//		getRegionalDiscount(firstMaintenance);
		waitForinvisibilityOfElementLocated(elementtoInvisible);
		gettingWebElementsfromList(icon_additionalDiscount).get(3).click();
		try {
			if (!isgetRegionalDiscount) {
				isgetRegionalDiscount = true;
				Assert.fail(
						"Failed due to (Regionaldiscountoncomponent% or Regionaldiscountoncomponent or TargetPrice)");
			}
		} catch (Exception e) {
			Assert.fail("Get Regional Discount Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click on CurrenciesTab and get ConversionFactor value
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void VerifyCostCalculatedSuccessfully() throws Exception {
		try {
			if (frontlineAssigned.equals("AUSTRALIA")) {
				clickonCurrenciesTab();
				getConversionFactor();
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				WebElement scrollto_DetailbreakdownTab = gettingWebElement(tab_detailBreakDown);
				scrollIntoView_Javascript(scrollto_DetailbreakdownTab);
				waitForVisibilityOfElementLocated(tab_detailBreakDown);
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				clickonButton(tab_detailBreakDown);
				Float convertedvalue_conversionFactor = Float.valueOf(conversionFactor);
				Float showtotal_Materialcosts = hm_DetailBreakdownData.get("Material costs");
				showtotal_Materialcosts = Float.valueOf(roundoff.format(showtotal_Materialcosts));
				Float showtotal_MaterialcostSLCurrency = hm_DetailBreakdownData.get("Material cost (SL Currency)");
				Float check_Materialcosts = showtotal_MaterialcostSLCurrency * convertedvalue_conversionFactor;
				check_Materialcosts = Float.valueOf(roundoff.format(check_Materialcosts));
				System.out.println("Calculate Materialcost:" + check_Materialcosts + " / " + "Actual Materialcost:"
						+ showtotal_Materialcosts);
				String condition = null;
				if (!check_Materialcosts.equals(showtotal_Materialcosts)) {
					Float difference = check_Materialcosts - showtotal_Materialcosts;
//					System.out.println(roundoff.format(difference));
					if (roundoff.format(difference).equals(roundoff.format(-.00f))
							|| roundoff.format(difference).equals(roundoff.format(-.01f))) {
						check_Materialcosts = check_Materialcosts + 0.01f;
						condition = "+0.01";
//					System.out.println(roundoff.format(check_Materialcosts));
					} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
						check_Materialcosts = check_Materialcosts + 0.02f;
						condition = "+0.02";
//					System.out.println(roundoff.format(check_Materialcosts));
					} else {
						check_Materialcosts = check_Materialcosts - 0.01f;
						if (check_Materialcosts.equals(showtotal_Materialcosts)) {
							condition = "-0.01";
						} else {
							check_Materialcosts = check_Materialcosts - 0.01f;
							condition = "-0.02";
						}
					}
				}
				System.out.println(condition
						+ " Added in CalculatedMaterialCost hence CalculatedMaterialcost VS ActualMaterialcost is: "
						+ roundoff.format(check_Materialcosts).equals(roundoff.format(showtotal_Materialcosts))
						+ " ***");
				if (!roundoff.format(check_Materialcosts).equals(roundoff.format(showtotal_Materialcosts))) {
					screenshotCapture("MaterialCost not equal in VerifyCostCalculatedSuccessfully");
					Assert.fail("MaterialCost not equal in VerifyCostCalculatedSuccessfully");
				}
			} else {
				System.out.println("*** Currencies Tab is applicable for Australia alone hence skipping TC#10 ***");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Verify Cost Calculated Successfully Falied due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void VerifyPriceCalculatedSuccessfully_TobecheckedinFrance() throws Exception {
		if (frontlineAssigned.equals("AUSTRALIA")) {
//			checkingTargetPriceFullGrid();
//			validateDetailBreakdownTabFullGrid();
			validateDetailBreakdownTabFullGrid1();
		} else {
//			System.out.println("*** Currencies Tab is applicable for Australia alone hence skipping TC#10 ***");
//			validateDetailBreakdownTabFullGrid();
//			System.out.println("======"+new SimpleDateFormat("ddMMMyyyy_hh_mm_ssaa").format(Calendar.getInstance().getTime()));
			validateDetailBreakdownTabFullGrid1();
//			System.out.println("========"+new SimpleDateFormat("ddMMMyyyy_hh_mm_ssaa").format(Calendar.getInstance().getTime()));
//			System.out.println("*** Currencies Tab is applicable for Australia alone hence skipping TC#10 ***");
		}
	}

	/**
	 ** Reuse method, it will click on CurrenciesTab
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void clickonCurrenciesTab() throws Exception {
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement scrollto_currenciesTab = gettingWebElement(tab_Currencies);
			scrollIntoView_Javascript(scrollto_currenciesTab);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(tab_Currencies);
			System.out.println("CurrenciesTab Clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Click on Currencies Tab Failed due to:" + e);
		}
	}

	/**
	 ** Reuse method, it will get ConversionFactor value
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public String conversionFactor;

	public void getConversionFactor() throws Exception {
		try {
			waitForVisibilityOfElementLocated(value_ConversionFactor);
			WebElement element_ConversionFactor = gettingWebElement(value_ConversionFactor);
			conversionFactor = element_ConversionFactor.getAttribute("value");
			System.out.println("Conversion Factor:" + conversionFactor);
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Get ConversionFactor Failed due to:" + e);
		}
	}

	/**
	 ** Reuse method, it will click on AdditionalDiscountIcon
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void clickonAdditionalDiscountIcon() throws Exception {
		try {
			waitForVisibilityOfElementLocated(tab_priceOverview); //By.xpath("//*[text()='Price overview']")
			waitForElementToBeClickable(By.xpath("//*[@data-ctcwgtname='nPricingOverview']/div"));
			wait.until(ExpectedConditions.visibilityOf(gettingWebElementsfromList(icon_additionalDiscount).get(3)));
			scrollIntoView_Javascript(gettingWebElementsfromList(icon_additionalDiscount).get(3));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(icon_additionalDiscount).get(3).click();
			/*
			 * System.out.println(gettingWebElementsfromList(icon_additionalDiscount).get(3)
			 * .getAttribute("id")); for(WebElement
			 * additionaldiscount:gettingWebElementsfromList(icon_additionalDiscount)) {
			 * System.out.println(additionaldiscount.getAttribute("id")); }
			 */
			System.out.println("Additional Discount Icon CLICKED");
			waitForVisibilityOfElementLocated(grid_RegionalDiscountValues); // header_RegionalDiscount
//		System.out.println("header RegionalDiscount is visible");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Click on Additional Discount Icon Failed due to:" + e);
		}
	}

	/**
	 ** Reuse method, it will get RegionalDiscount from table (1st rowdata)
	 * 
	 * @param firstMaintenance: FirstMaintenance to be entered
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public HashMap<String, Float> hm_RegionalDiscountData;
	public boolean isgetRegionalDiscount = true;

	public void getRegionalDiscount(String firstMaintenance) throws Exception {
		try {
			waitForVisibilityOfElementLocated(grid_RegionalDiscountValues);
			// --------------------------------------------------------------
			/*
			 * String Project=GetAdditionalDiscountGridTargetPriceBaseValues("Project");
			 * Project=Project.replaceAll("[€ % h . $]", ""); Project=Project.replace(",",
			 * "."); Float TargetPriceBaseValues_Project=Float.valueOf(Project);
			 * System.out.println(TargetPriceBaseValues_Project);
			 * 
			 * String Solution=GetAdditionalDiscountGridTargetPriceBaseValues("Solution 2");
			 * Solution=Solution.replaceAll("[€ % h . $]", "");
			 * Solution=Solution.replace(",", "."); Float
			 * TargetPriceBaseValues_Solution=Float.valueOf(Solution);
			 * System.out.println(TargetPriceBaseValues_Solution);
			 * 
			 * String Project_TargetPriceBase=gettingWebElement(By.xpath(
			 * "//div[text()='Project']/parent::div/parent::div/div[1]/input[1]")).
			 * getAttribute("value");
			 * Project_TargetPriceBase=Project_TargetPriceBase.replaceAll("[€ % h . $]",
			 * ""); Project_TargetPriceBase = Project_TargetPriceBase.replace(",", ".");
			 * Float TargetPriceBaseValues_Project1 =
			 * Float.valueOf(Project_TargetPriceBase);
			 * System.out.println(TargetPriceBaseValues_Project1);
			 * 
			 * String project_Regionaldiscountoncomponent=gettingWebElement(By.xpath(
			 * "//div[text()='Project']/parent::div/parent::div/div[1]/input[7]")).
			 * getAttribute("value");
			 * project_Regionaldiscountoncomponent=project_Regionaldiscountoncomponent.
			 * replaceAll("[€ % h . $]", ""); project_Regionaldiscountoncomponent =
			 * project_Regionaldiscountoncomponent.replace(",", "."); Float
			 * Regionaldiscountoncomponent_Project1=Float.valueOf(
			 * project_Regionaldiscountoncomponent);
			 * System.out.println(Regionaldiscountoncomponent_Project1);
			 * 
			 * String
			 * project_Regionaldiscountoncomponent_Percent=gettingWebElement(By.xpath(
			 * "//div[text()='Project']/parent::div/parent::div/div[1]/input[7]")).
			 * getAttribute("value"); project_Regionaldiscountoncomponent_Percent=
			 * project_Regionaldiscountoncomponent_Percent.replaceAll("[€ % h . $]", "");
			 * project_Regionaldiscountoncomponent_Percent =
			 * project_Regionaldiscountoncomponent_Percent.replace(",", "."); Float
			 * Regionaldiscountoncomponent_Percent_Project=Float.valueOf(
			 * project_Regionaldiscountoncomponent_Percent);
			 * System.out.println(Regionaldiscountoncomponent_Percent_Project);
			 * 
			 * HashMap<String, Float> hm_GetAdditionalDiscountData = new HashMap<String,
			 * Float>();
			 */
			// --------------------------------------------------------------
			List<String> ls_RegionalDiscountHeader = new LinkedList<>();
			List<Float> ls_RegionalDiscountValue = new LinkedList<>();
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
			Float convertedvalue = null;
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(grid_RegionalDiscountValues));
			List<WebElement> element_RegionalDiscountValues = gettingWebElementsfromList(grid_RegionalDiscountValues);
			wait.until(ExpectedConditions.visibilityOfAllElements(element_RegionalDiscountValues));
			for (WebElement element_RegionalDiscountValue : element_RegionalDiscountValues) {
				String getAttribute_element_RegionalDiscountValue = element_RegionalDiscountValue.getAttribute("value");
				String getText_element_RegionalDiscountValue = element_RegionalDiscountValue.getText();
				if (getAttribute_element_RegionalDiscountValue == null) {
					if (!getText_element_RegionalDiscountValue.isEmpty()) {
						if (!getText_element_RegionalDiscountValue.contains("Subtotal")
								&& !getText_element_RegionalDiscountValue.contains("Project")) {
							String getvalue = getText_element_RegionalDiscountValue;
//							System.out.println("2*"+element_RegionalDiscountValue.getAttribute("id")+"="+getvalue);
							if (!getvalue.equals("0.00") && !getvalue.contains("%")) {
								getvalue = getvalue.replace(".", "");
							}
							getvalue = getvalue.replaceAll("[€ % h $]", "");
							getvalue = getvalue.replace(",", ".");
//							System.out.println("2**"+element_RegionalDiscountValue.getAttribute("id")+"="+getvalue);
							convertedvalue = Float.valueOf(getvalue);
							ls_RegionalDiscountValue.add(convertedvalue);
//							System.out.println("2***"+element_RegionalDiscountValue.getAttribute("id")+"="+convertedvalue);
						}
					}
				} else if (!getAttribute_element_RegionalDiscountValue.isEmpty()
						&& getAttribute_element_RegionalDiscountValue != null) {
					String getvalue = getAttribute_element_RegionalDiscountValue.replaceAll("[€ % h . $]", "");
					getvalue = getvalue.replace(",", ".");
//					System.out.println("3**"+element_RegionalDiscountValue.getAttribute("id")+"="+getvalue);
					convertedvalue = Float.valueOf(getvalue);
					ls_RegionalDiscountValue.add(convertedvalue);
//					System.out.println("3***"+element_RegionalDiscountValue.getAttribute("id")+"="+convertedvalue);
				}
			}
			int toIterate = 16;
			if (!firstMaintenance.equals("0")) {
				toIterate = toIterate + 1;
			}
			for (int i = 0; i < toIterate; i++) {
				hm_RegionalDiscountData.put(ls_RegionalDiscountHeader.get(i), ls_RegionalDiscountValue.get(i));
			}
			Float read_TargetPrice_Base = hm_RegionalDiscountData.get("TargetPrice_Base");
			/*
			 * Float read_FundingSectorDiscount_Percent=hm_data.get(
			 * "FundingSectorDiscount_Percent"); Float
			 * read_FundingSectorDiscount=hm_data.get("FundingSectorDiscount"); Float
			 * read_MarketSegmentDiscount_Percent=hm_data.get(
			 * "MarketSegmentDiscount_Percent"); Float
			 * read_MarketSegmentDiscount=hm_data.get("MarketSegmentDiscount"); Float
			 * read_BillingPlanDiscount_Percent=hm_data.get("BillingPlanDiscount_Percent");
			 * Float read_BillingPlanDiscount=hm_data.get("BillingPlanDiscount"); Float
			 * read_SalesOfficeMultiplier_Percent=hm_data.get(
			 * "SalesOfficeMultiplier_Percent"); Float
			 * read_SalesOfficeMultiplier=hm_data.get("SalesOfficeMultiplier"); Float
			 * read_SellingValuePackageDiscount_Percent=hm_data.get(
			 * "SellingValuePackageDiscount_Percent"); Float
			 * read_SellingValuePackageDiscount=hm_data.get("SellingValuePackageDiscount");
			 */
			Float read_Regionaldiscountoncomponent_Percent = hm_RegionalDiscountData
					.get("Regionaldiscountoncomponent_Percent");
			Float read_Regionaldiscountoncomponent = hm_RegionalDiscountData.get("Regionaldiscountoncomponent");
			Float read_TargetPrice = hm_RegionalDiscountData.get("TargetPrice");
			/*
			 * Float read_Discount=hm_data.get("Discount"); Float
			 * read_TenderPrice=hm_data.get("TenderPrice"); Float
			 * read_FirstMaintenance=hm_data.get("FirstMaintenance");
			 */
			regionalDiscount = hm_RegionalDiscountforSalesOfficeData.get(selectedSalesOffice);
			Float final_Regionaldiscountoncomponent = read_TargetPrice_Base * (regionalDiscount / 100);
			final_Regionaldiscountoncomponent = Float.valueOf(roundoff.format(final_Regionaldiscountoncomponent));
			Float final_TargetPrice = read_TargetPrice_Base - final_Regionaldiscountoncomponent;
			final_TargetPrice = Float.valueOf(roundoff.format(final_TargetPrice));
			System.out.println("read_Regionaldiscountoncomponent_Percent:" + read_Regionaldiscountoncomponent_Percent
					+ " / regionalDiscount:" + regionalDiscount);
			System.out.println("*** is read_Regionaldiscountoncomponent_Percent VS regionalDiscount equal:"
					+ read_Regionaldiscountoncomponent_Percent.equals(regionalDiscount) + " ***");
			System.out.println("final_Regionaldiscountoncomponent:" + final_Regionaldiscountoncomponent
					+ " / read_Regionaldiscountoncomponent:" + read_Regionaldiscountoncomponent);
			System.out.println("*** is final_Regionaldiscountoncomponent VS read_Regionaldiscountoncomponent equal:"
					+ final_Regionaldiscountoncomponent.equals(read_Regionaldiscountoncomponent) + " ***");
			System.out.println("final_TargetPrice:" + final_TargetPrice + " / read_TargetPrice:"
					+ roundoff.format(read_TargetPrice));
			System.out.println("*** is final_TargetPrice VS read_TargetPrice equal:"
					+ roundoff.format(final_TargetPrice).equals(roundoff.format(read_TargetPrice)) + " ***");
			String condition = null;
			if (!read_Regionaldiscountoncomponent_Percent.equals(regionalDiscount)) {
				Float difference = read_Regionaldiscountoncomponent_Percent - regionalDiscount;
//				System.out.println(roundoff.format(difference));
				if (roundoff.format(difference).equals(roundoff.format(-.00f))
						|| roundoff.format(difference).equals(roundoff.format(-.01f))) {
					read_Regionaldiscountoncomponent_Percent = read_Regionaldiscountoncomponent_Percent + 0.01f;
					condition = "+0.01";
//				System.out.println(roundoff.format(read_Regionaldiscountoncomponent_Percent));
				} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
					read_Regionaldiscountoncomponent_Percent = read_Regionaldiscountoncomponent_Percent + 0.02f;
					condition = "+0.02";
//				System.out.println(roundoff.format(read_Regionaldiscountoncomponent_Percent));
				} else {
					read_Regionaldiscountoncomponent_Percent = read_Regionaldiscountoncomponent_Percent - 0.01f;
					if (read_Regionaldiscountoncomponent_Percent.equals(regionalDiscount)) {
						condition = "-0.01";
					} else {
						read_Regionaldiscountoncomponent_Percent = read_Regionaldiscountoncomponent_Percent - 0.01f;
						condition = "-0.02";
					}
				}
			}
			System.out.println(condition
					+ " Added in Calculated RegionaldiscountoncomponentPercent hence Calculated RegionaldiscountoncomponentPercent VS Actual regionalDiscount shown in Application is: "
					+ read_Regionaldiscountoncomponent_Percent.equals(regionalDiscount) + " ***");
			String condition1 = null;
			if (!roundoff.format(final_TargetPrice).equals(roundoff.format(read_TargetPrice))) {
				Float difference = final_TargetPrice - read_TargetPrice;
//				System.out.println(roundoff.format(difference));
				if (roundoff.format(difference).equals(roundoff.format(-.00f))
						|| roundoff.format(difference).equals(roundoff.format(-.01f))) {
					final_TargetPrice = final_TargetPrice + 0.01f;
					condition = "+0.01";
//				System.out.println(roundoff.format(final_TargetPrice));
				} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
					final_TargetPrice = final_TargetPrice + 0.02f;
					condition = "+0.02";
//				System.out.println(roundoff.format(final_TargetPrice));
				} else {
					final_TargetPrice = final_TargetPrice - 0.01f;
					if (roundoff.format(final_TargetPrice).equals(roundoff.format(read_TargetPrice))) {
						condition1 = "-0.01";
					} else {
						final_TargetPrice = final_TargetPrice - 0.01f;
						condition1 = "-0.02";
					}
				}
			}
			System.out.println(condition1
					+ " Added in final_TargetPrice hence CalculatedTargetPrice VS ActualTargetPrice shown in Application is: "
					+ roundoff.format(final_TargetPrice).equals(roundoff.format(read_TargetPrice)) + " ***");
			if (!roundoff.format(read_Regionaldiscountoncomponent_Percent).equals(roundoff.format(regionalDiscount))
					|| !roundoff.format(final_Regionaldiscountoncomponent)
							.equals(roundoff.format(read_Regionaldiscountoncomponent))
					|| !roundoff.format(final_TargetPrice).equals(roundoff.format(read_TargetPrice))) {
				screenshotCapture("VerifyTargetPriceDisplayedCorrectly");
				isgetRegionalDiscount = false;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Get Regional Discount Failed:" + e);
		}
	}

	/**
	 ** Reuse method, it will get RegionalDiscount from table (FullGrid)
	 * 
	 * @param firstMaintenance: FirstMaintenance to be entered
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public HashMap<String, Float> hm_RegionalDiscountDataFullGrid;

	public void getRegionalDiscountFullGrid() throws Exception {
		try {
			waitForVisibilityOfElementLocated(grid_RegionalDiscountValues);
			List<String> ls_RegionalDiscountColumnHeader = new LinkedList<>();
			List<String> ls_RegionalDiscountRowHeader = new LinkedList<>();
			List<Float> ls_RegionalDiscountValue = null;
			hm_RegionalDiscountDataFullGrid = null;
			hm_RegionalDiscountDataFullGrid = new HashMap<String, Float>();
			Float convertedvalue = null;
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(grid_RegionalDiscountValues));
			List<WebElement> element_RegionalDiscountValues = gettingWebElementsfromList(grid_RegionalDiscountValues);
			wait.until(ExpectedConditions.visibilityOfAllElements(element_RegionalDiscountValues));
			/*
			 * List<WebElement>
			 * elements_RegionalDiscountHeader=gettingWebElementsfromList(By.
			 * xpath("//*[text()='Regional discount on component (%)']/../../div"));
			 * for(WebElement
			 * element_RegionalDiscountHeader:elements_RegionalDiscountHeader) {
			 * List<WebElement> Element1 =
			 * element_RegionalDiscountHeader.findElements(By.xpath("./*")); //
			 * System.out.println(elements_RegionalDiscountHeader.size()+"/"+Element1.size()
			 * ); for(WebElement Element:Element1) {
			 * System.out.println(Element.getAttribute("id")+"/"+Element.getText());
			 * if(!Element.getText().contains("Description") &&
			 * !Element.getText().contains("LO") && !Element.getText().isEmpty()) { //
			 * System.out.println(Element.getText());
			 * ls_RegionalDiscountColumnHeader.add(Element.getText()); } } }
			 */
			ls_RegionalDiscountColumnHeader.add("Target Price (Base)");
			ls_RegionalDiscountColumnHeader.add("Funding Sector Discount (%)");
			ls_RegionalDiscountColumnHeader.add("Funding Sector Discount");
			ls_RegionalDiscountColumnHeader.add("Market Segment Discount (%)");
			ls_RegionalDiscountColumnHeader.add("Market Segment Discount");
			ls_RegionalDiscountColumnHeader.add("Billing Plan Discount (%)");
			ls_RegionalDiscountColumnHeader.add("Billing Plan Discount");
			ls_RegionalDiscountColumnHeader.add("Sales Office Multiplier (%)");
			ls_RegionalDiscountColumnHeader.add("Sales Office Multiplier");
			ls_RegionalDiscountColumnHeader.add("Selling Value Package Discount (%)");
			ls_RegionalDiscountColumnHeader.add("Selling Value Package Discount");
			ls_RegionalDiscountColumnHeader.add("Regional discount on component (%)");
			ls_RegionalDiscountColumnHeader.add("Regional discount on component");
			ls_RegionalDiscountColumnHeader.add("Target Price");
			ls_RegionalDiscountColumnHeader.add("Discount");
			ls_RegionalDiscountColumnHeader.add("Tender Price");
			if (!firstMaintenance.equals("0")) {
				ls_RegionalDiscountColumnHeader.add("First Maintenance");
			}
			List<String> numerals = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "€", "$", ".", " ",
					"-");
			List<WebElement> elements_RegionalDiscountValues = gettingWebElementsfromList(
					By.xpath("//*[text()='Regional discount on component (%)']/../../../div[3]"));
//			int indexvalue=1;
			String element_RegionalDiscountRowHeader = null;
			for (WebElement elements_RegionalDiscountValue : elements_RegionalDiscountValues) {
//				System.out.println(elements_RegionalDiscountValues.size());
//				System.out.println("elements_RegionalDiscountValueID:"+elements_RegionalDiscountValue.getAttribute("id"));
				List<WebElement> Element1 = elements_RegionalDiscountValue.findElements(By.xpath("./div/*"));
//				System.out.println("size:"+Element1.size());

				for (WebElement Element : Element1) {
					if (!Element.getAttribute("id").contains("ccf") && !Element.getAttribute("id").contains("srp")) {
//					System.out.println("ElementID:"+Element.getAttribute("id"));
						List<WebElement> Element2 = Element.findElements(By.xpath("./*"));
//					System.out.println("size&*(:"+Element2.size());
						ls_RegionalDiscountValue = new LinkedList<>();
						for (WebElement Element3 : Element2) {
//						System.out.println("ElementID:"+Element3.getAttribute("id"));
							if (Element3.getAttribute("value") == null) {
								if (!Element3.getText().isEmpty()) {
//								System.out.println(Element3.getText());
									String[] array = Element3.getText().split("");
									if (!numerals.contains(array[0])
											|| ls_allEquipmentIDs.contains(Element3.getText())) {
										element_RegionalDiscountRowHeader = Element3.getText();
//							System.out.println("element_RegionalDiscountRowHeader="+element_RegionalDiscountRowHeader);
										ls_RegionalDiscountRowHeader.add(Element3.getText());
									}
									if (!ls_RegionalDiscountRowHeader.contains(Element3.getText())) { // !Element3.getText().contains("Subtotal")
																										// &&
										String getvalue = Element3.getText();
//							System.out.println("2*"+Element3.getAttribute("id")+"="+getvalue);
										/*
										 * if(!getvalue.equals("0.00") && !getvalue.contains("%")) { getvalue =
										 * getvalue.replace(".", ""); }
										 */
										getvalue = getvalue.replaceAll("[€ % h $]", "");
										if (frontlineAssigned.equals("FRANCE")) { // || frontlineAssigned.equals("CANADA")
											getvalue = getvalue.replace(",", ".");
										} else if (frontlineAssigned.equals("AUSTRALIA") || frontlineAssigned.equals("CANADA")) {
											getvalue = getvalue.replace(",", "");
										}
										getvalue = removingDot(getvalue);
//									System.out.println("2**"+Element3.getAttribute("id")+"="+getvalue);
										convertedvalue = Float.valueOf(getvalue);
										ls_RegionalDiscountValue.add(convertedvalue);
//							System.out.println("2***"+Element3.getAttribute("id")+"="+convertedvalue);
									}
								}
							} else if (!Element3.getAttribute("value").isEmpty()
									&& Element3.getAttribute("value") != null) {
								String getvalue = Element3.getAttribute("value").replaceAll("[€ % h $]", "");
								if (frontlineAssigned.equals("FRANCE")) { // || frontlineAssigned.equals("CANADA")
									getvalue = getvalue.replace(".", "");
									getvalue = getvalue.replace(",", ".");
								} else if (frontlineAssigned.equals("AUSTRALIA") || frontlineAssigned.equals("CANADA")) {
									getvalue = getvalue.replace(",", "");
								}
//							System.out.println("3**"+Element3.getAttribute("id")+"="+getvalue);
								convertedvalue = Float.valueOf(getvalue);
								ls_RegionalDiscountValue.add(convertedvalue);
//							System.out.println("3***"+Element3.getAttribute("id")+"="+convertedvalue);
							}
						}
//					System.out.println(ls_RegionalDiscountValue.size());
						for (int i = 0; i < ls_RegionalDiscountValue.size(); i++) {
//						System.out.println(element_RegionalDiscountRowHeader+"_"+ls_RegionalDiscountColumnHeader.get(i)+"="+ ls_RegionalDiscountValue.get(i));
							hm_RegionalDiscountDataFullGrid.put(
									element_RegionalDiscountRowHeader + "_" + ls_RegionalDiscountColumnHeader.get(i),
									ls_RegionalDiscountValue.get(i));
						}
//					indexvalue++;
					}
				}
//				System.out.println("indexvalue:"+indexvalue);
			}
			/* hm_RegionalDiscountDataFullGrid.values().forEach(System.out::println); */
//			hm_RegionalDiscountDataFullGrid.forEach((key, value) -> {System.out.println("Key : " + key + " Value : " + value);});
			Float read_TargetPriceBase1 = hm_RegionalDiscountDataFullGrid.get(
					ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 1) + "_Target Price (Base)");
			Float read_Regionaldiscountoncomponent_percentage1 = hm_RegionalDiscountDataFullGrid
					.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 1)
							+ "_Regional discount on component (%)");
			Float read_Regionaldiscountoncomponent1 = hm_RegionalDiscountDataFullGrid
					.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 1)
							+ "_Regional discount on component");
//			Float read_TargetPrice1=hm_RegionalDiscountDataFullGrid.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size()-1)+"_Target Price");
//			System.out.println("read_"+ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size()-1)+"_Target Price (Base)="+read_TargetPriceBase1+" / read_"+ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size()-1)+"_Regional discount on component (%)="+read_Regionaldiscountoncomponent_percentage1+" / read_"+ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size()-1)+"_Regional discount on component="+read_Regionaldiscountoncomponent1+" / read_"+ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size()-1)+"_Target Price="+read_TargetPrice1);
			Float calculated_Regionaldiscountoncomponent1 = read_TargetPriceBase1
					* (read_Regionaldiscountoncomponent_percentage1 / 100);
			Boolean isRegionaldiscountoncomponent_calculated = roundoff.format(calculated_Regionaldiscountoncomponent1)
					.equals(roundoff.format(read_Regionaldiscountoncomponent1));
			System.out.println(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 1)
					+ "_calculatedRegionaldiscountoncomponent ="
					+ roundoff.format(calculated_Regionaldiscountoncomponent1) + " / read_Regionaldiscountoncomponent ="
					+ roundoff.format(read_Regionaldiscountoncomponent1));
			System.out
					.println("*** is calculated_Regionaldiscountoncomponent vs read_Regionaldiscountoncomponent equal: "
							+ isRegionaldiscountoncomponent_calculated);
			Boolean isRegionaldiscountoncomponent_testdata = null;
			if (!isMultipleEquipment) {
				isRegionaldiscountoncomponent_testdata = read_Regionaldiscountoncomponent_percentage1
						.equals(regionalDiscount);
				System.out.println("read_" + ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 1)
						+ "_Regional discount on component (%)=" + read_Regionaldiscountoncomponent_percentage1
						+ " / testdata_regionalDiscount:" + regionalDiscount);
			} else {
				isRegionaldiscountoncomponent_testdata = read_Regionaldiscountoncomponent_percentage1
						.equals(regionalDiscount_MultipleEqup);
				System.out.println("read_" + ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 1)
						+ "_Regional discount on component (%)=" + read_Regionaldiscountoncomponent_percentage1
						+ " / testdata_regionalDiscount:" + regionalDiscount_MultipleEqup);
			}
			System.out.println("***is read_" + ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 1)
					+ "_Regional discount on component (%) VS testdata_regionalDiscount equal:"
					+ isRegionaldiscountoncomponent_testdata);
			Float read_TargetPriceBase2 = hm_RegionalDiscountDataFullGrid.get(
					ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2) + "_Target Price (Base)");
			Float read_Regionaldiscountoncomponent_percentage2 = hm_RegionalDiscountDataFullGrid
					.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2)
							+ "_Regional discount on component (%)");
			Float read_Regionaldiscountoncomponent2 = hm_RegionalDiscountDataFullGrid
					.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2)
							+ "_Regional discount on component");
			Float read_TargetPrice2 = hm_RegionalDiscountDataFullGrid
					.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2) + "_Target Price");
			Float read_SalesOfficeMultiplier2 = hm_RegionalDiscountDataFullGrid.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2) + "_Sales Office Multiplier");
			/*Float read_FundingSectorDiscount2 = hm_RegionalDiscountDataFullGrid.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2) + "_Funding Sector Discount");
			Float read_MarketSegmentDiscount2 = hm_RegionalDiscountDataFullGrid.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2) + "_Market Segment Discount");
			Float read_BillingPlanDiscount2 = hm_RegionalDiscountDataFullGrid.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2) + "_Billing Plan Discount");
			Float read_SellingValuePackageDiscount2 = hm_RegionalDiscountDataFullGrid.get(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2) + "_Selling Value Package Discount");*/
//			System.out.println("read_"+ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size()-2)+"_Target Price (Base)="+read_TargetPriceBase2+" / read_"+ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size()-2)+"_Regional discount on component (%)="+read_Regionaldiscountoncomponent_percentage2+" / read_"+ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size()-2)+"_Regional discount on component="+read_Regionaldiscountoncomponent2+" / read_"+ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size()-2)+"_Target Price="+read_TargetPrice2);
			Float calculated_Regionaldiscountoncomponent_percentage2 = (read_Regionaldiscountoncomponent2
					/ read_TargetPriceBase2) * 100;
			Float calculated_TargetPrice2 = read_TargetPriceBase2 - read_Regionaldiscountoncomponent2;
			calculated_TargetPrice2 = calculated_TargetPrice2 + (-read_SalesOfficeMultiplier2); //+ (-read_FundingSectorDiscount2) + (-read_MarketSegmentDiscount2) + (-read_BillingPlanDiscount2) + (-read_SellingValuePackageDiscount2)
			Float NaN = 0f/0f; //NotaNumber
			if (calculated_Regionaldiscountoncomponent_percentage2.equals(NaN)) {
				calculated_Regionaldiscountoncomponent_percentage2 = 0f;
			}
			Boolean isRegionaldiscountoncomponentpercentage_calculated = roundoff
					.format(calculated_Regionaldiscountoncomponent_percentage2)
					.equals(roundoff.format(read_Regionaldiscountoncomponent_percentage2));
			System.out.println(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2)
					+ "_calculated_Regionaldiscountoncomponent % ="
					+ roundoff.format(calculated_Regionaldiscountoncomponent_percentage2) + " / read_"
					+ ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2)
					+ "_Regional discount on component (%)=" + read_Regionaldiscountoncomponent_percentage2);
			System.out.println(
					"*** is calculated_Regionaldiscountoncomponent % vs read_Regionaldiscountoncomponent % equal = "
							+ isRegionaldiscountoncomponentpercentage_calculated);
			System.out.println(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2)+ "_Sales Office Multiplier =" + roundoff.format(read_SalesOfficeMultiplier2));
			/*System.out.println(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2)+ "_Funding Sector Discount =" + roundoff.format(read_FundingSectorDiscount2));
			System.out.println(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2)+ "_Market Segment Discount =" + roundoff.format(read_MarketSegmentDiscount2));
			System.out.println(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2)+ "_Billing Plan Discount =" + roundoff.format(read_BillingPlanDiscount2));
			System.out.println(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2)+ "_Selling Value Package Discount =" + roundoff.format(read_SellingValuePackageDiscount2));*/
			System.out.println(ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2)
					+ "_calculated_TargetPrice =" + roundoff.format(calculated_TargetPrice2) + " / read_"
					+ ls_RegionalDiscountRowHeader.get(ls_RegionalDiscountRowHeader.size() - 2) + "_Target Price="
					+ roundoff.format(read_TargetPrice2));
			String condition1 = null;
			if (!roundoff.format(calculated_TargetPrice2).equals(roundoff.format(read_TargetPrice2))) {
				Float difference = calculated_TargetPrice2 - read_TargetPrice2;
//				System.out.println(roundoff.format(difference));
				if (roundoff.format(difference).equals(roundoff.format(-.00f))
						|| roundoff.format(difference).equals(roundoff.format(-.01f))) {
					calculated_TargetPrice2 = calculated_TargetPrice2 + 0.01f;
					condition1 = "+0.01";
//				System.out.println(roundoff.format(calculated_TargetPrice2));
				} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
					calculated_TargetPrice2 = calculated_TargetPrice2 + 0.02f;
					condition1 = "+0.02";
//				System.out.println(roundoff.format(calculated_TargetPrice2));
				} else {
					calculated_TargetPrice2 = calculated_TargetPrice2 + 0.01f;
					if (roundoff.format(calculated_TargetPrice2).equals(roundoff.format(read_TargetPrice2))) {
						condition1 = "0.01";
					} else {
						calculated_TargetPrice2 = calculated_TargetPrice2 + 0.01f;
//						System.out.println("calculated_TargetPrice2="+calculated_TargetPrice2);
						condition1 = "0.02";
					}
				}
			}
			Boolean isTargetPrice_calculated = roundoff.format(calculated_TargetPrice2)
					.equals(roundoff.format(read_TargetPrice2));
			System.out.println("*** " + condition1
					+ " Added in calculated_TargetPrice hence calculated_TargetPrice VS read_TargetPrice shown in Application is: "
					+ isTargetPrice_calculated);

			/*
			 * int iterate=1; for(int a=1; a<indexvalue; a++) { String
			 * TargetPrice_Base=iterate+"_Target Price (Base)";
			 * TargetPrice_Base=TargetPrice_Base.toString(); String
			 * Regionaldiscountoncomponent_Percent=
			 * iterate+"_Regional discount on component (%)";
			 * Regionaldiscountoncomponent_Percent=Regionaldiscountoncomponent_Percent.
			 * toString(); String
			 * Regionaldiscountoncomponent=iterate+"_Regional discount on component";
			 * Regionaldiscountoncomponent=Regionaldiscountoncomponent.toString(); String
			 * TargetPrice=iterate+"_Target Price"; TargetPrice=TargetPrice.toString();
			 * Float
			 * read_TargetPrice_Base=hm_RegionalDiscountDataFullGrid.get(TargetPrice_Base);
			 * Float
			 * read_Regionaldiscountoncomponent_Percent=hm_RegionalDiscountDataFullGrid.get(
			 * Regionaldiscountoncomponent_Percent); Float
			 * read_Regionaldiscountoncomponent=hm_RegionalDiscountDataFullGrid.get(
			 * Regionaldiscountoncomponent); Float
			 * read_TargetPrice=hm_RegionalDiscountDataFullGrid.get(TargetPrice);
			 * System.out.println("read_TargetPrice_Base:"+read_TargetPrice_Base);
			 * System.out.println("read_Regionaldiscountoncomponent_Percent:"+
			 * read_Regionaldiscountoncomponent_Percent);
			 * System.out.println("read_Regionaldiscountoncomponent:"+
			 * read_Regionaldiscountoncomponent);
			 * System.out.println("read_TargetPrice:"+read_TargetPrice+" /TargetPrice=="
			 * +TargetPrice); iterate++; // } Object[] keys =
			 * hm_RegionalDiscountDataFullGrid.keySet().toArray(); Arrays.sort(keys);
			 * for(Object key : keys) { System.out.print(key);
			 * System.out.println("="+hm_RegionalDiscountDataFullGrid.get(key)); String
			 * y=key.toString(); String TargetPrice_Base=iterate+"_Target Price (Base)";
			 * TargetPrice_Base=TargetPrice_Base.toString();
			 * System.out.println(TargetPrice_Base+"/"+y+"="+TargetPrice_Base.equals(y));
			 * if(y.equalsIgnoreCase(TargetPrice_Base)) { System.out.println("L"); } }
			 * regionalDiscount =
			 * hm_RegionalDiscountforSalesOfficeData.get(selectedSalesOffice); Float
			 * final_Regionaldiscountoncomponent=read_TargetPrice_Base*(regionalDiscount/100
			 * ); final_Regionaldiscountoncomponent=Float.valueOf(roundoff.format(
			 * final_Regionaldiscountoncomponent)); Float
			 * final_TargetPrice=read_TargetPrice_Base-final_Regionaldiscountoncomponent;
			 * final_TargetPrice=Float.valueOf(roundoff.format(final_TargetPrice));
			 * System.out.
			 * println("***is read_Regionaldiscountoncomponent_Percent VS regionalDiscount equal:"
			 * +read_Regionaldiscountoncomponent_Percent.equals(regionalDiscount)
			 * +" *** / read_Regionaldiscountoncomponent_Percent:"
			 * +read_Regionaldiscountoncomponent_Percent+" / regionalDiscount:"
			 * +regionalDiscount); System.out.
			 * println("***is final_Regionaldiscountoncomponent VS read_Regionaldiscountoncomponent equal:"
			 * +final_Regionaldiscountoncomponent.equals(read_Regionaldiscountoncomponent)
			 * +" *** / final_Regionaldiscountoncomponent:"
			 * +final_Regionaldiscountoncomponent+" / read_Regionaldiscountoncomponent:"
			 * +read_Regionaldiscountoncomponent);
			 * System.out.println("***is final_TargetPrice VS read_TargetPrice equal:"
			 * +final_TargetPrice.equals(read_TargetPrice)+" *** / final_TargetPrice:"
			 * +final_TargetPrice+" / read_TargetPrice:"+read_TargetPrice); String
			 * condition=null;
			 * if(!read_Regionaldiscountoncomponent_Percent.equals(regionalDiscount)) {
			 * read_Regionaldiscountoncomponent_Percent=
			 * read_Regionaldiscountoncomponent_Percent-0.01f;
			 * if(read_Regionaldiscountoncomponent_Percent.equals(regionalDiscount)) {
			 * condition="-0.01"; } else { read_Regionaldiscountoncomponent_Percent=
			 * read_Regionaldiscountoncomponent_Percent-0.02f; condition="-0.02"; } }
			 * System.out.println(
			 * condition+" Added in CalculatedMaterialCost hence CalculatedMaterialcost VS ActualMaterialcost shown in Application is: "
			 * +read_Regionaldiscountoncomponent_Percent.equals(regionalDiscount)+" ***");
			 */
			if (!isRegionaldiscountoncomponent_calculated || !isRegionaldiscountoncomponent_testdata
					|| !isRegionaldiscountoncomponentpercentage_calculated || !isTargetPrice_calculated) {
				screenshotCapture("VerifyTargetPriceDisplayedCorrectly");
				isgetRegionalDiscount = false;
			}

		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Get Regional Discount (FullGrid) Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click Detail Breakdown Tab and checks ITE factor,
	 * Labour rate, Reference hours (1st rowdata)
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public HashMap<String, Float> hm_DetailBreakdownData;

	public void validateDetailBreakdownTab() throws Exception {
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
			if (isMultipleEquipment) {
				By checkbox_forMultipleEquipment = By.xpath("//*[text()='MMS Electrification Module']");
				WebElement element_forMultipleEquipment = gettingWebElement(checkbox_forMultipleEquipment);
				wait.until(ExpectedConditions.visibilityOf(element_forMultipleEquipment));
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				click_Javascript(element_forMultipleEquipment);
				System.out.println("MMS Electrification Module CLICKED");
			} else {
				click_Javascript(element_Ropes);
				System.out.println("ROPES CLICKED");
			}
			waitForinvisibilityOfElementLocated(value_ropes);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			/*
			 * List<WebElement> buttons=gettingWebElementsfromList(By.
			 * xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			 * int count=0; for(WebElement button:buttons) { //
			 * System.out.println(button.getAttribute("id")); count++; if(count==5) {
			 * scrollIntoView_Javascript(button);
			 * wait.until(ExpectedConditions.visibilityOf(button));
			 * waitForinvisibilityOfElementLocated(elementtoInvisible); button.click();
			 * waitForVisibilityOfElementLocated(By.
			 * xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * gettingWebElementsfromList(By.
			 * xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div")).get
			 * (3).click(); break; } }
			 */
			waitForVisibilityOfElementLocated(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"))
					.get(4).click();
			waitForVisibilityOfElementLocated(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			waitForVisibilityOfElementLocated(header_ITEfactor);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			System.out.println("Show TotalCost Calculation Details CLICKED");
			if (showInstallationCalculation != 0) {
				int count = 0;
				List<WebElement> buttons = gettingWebElementsfromList(
						By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
				wait.until(ExpectedConditions.visibilityOfAllElements(buttons));
				for (WebElement button : buttons) {
//			System.out.println(button.getAttribute("id"));
					count++;
					if (count == 4) {
						scrollIntoView_Javascript(button);
						wait.until(ExpectedConditions.visibilityOf(button));
						waitForinvisibilityOfElementLocated(elementtoInvisible);
						button.click();
						break;
					}
				}
			}
			showInstallationCalculation = 1;
			waitForVisibilityOfElementLocated(By.xpath("//*[text()='Reference hours']"));
			validateDetailBreakdownTabGetDatafromGrid();

			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"))
					.get(4).click();
			waitForVisibilityOfElementLocated(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			int count1 = 0;
			List<WebElement> buttons1 = gettingWebElementsfromList(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			wait.until(ExpectedConditions.visibilityOfAllElements(buttons1));
			for (WebElement button : buttons1) {
//			System.out.println(button.getAttribute("id"));
				count1++;
				if (count1 == 4) {
					scrollIntoView_Javascript(button);
					wait.until(ExpectedConditions.visibilityOf(button));
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					button.click();
					break;
				}
			}
//			System.out.println("elementShowTotalCostCalculationDetail_CLICKED*****BACK="+driver.findElements(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div")).get(4).getAttribute("id"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
//			waitForinvisibilityOfElementLocated(header_ITEfactor);
//			System.out.println("header_ITEfactor disabled");
			if (!isDetailBreakdown) {
				isDetailBreakdown = true;
				Assert.fail(
						"Failed due to (ITEfactor or Referencehours or Labourrate) not euqal in Detail Breakdown Tab");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Validate Detail Breakdown Tab Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will get data from Detail Breakdown (FullGrid)
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public boolean isDetailBreakdown = true;

	public void validateDetailBreakdownTabGetDatafromGrid() throws Exception {
		try {
			waitForVisibilityOfElementLocated(By.xpath("//*[text()='Reference hours']"));
			List<String> ls_TotalCostHeader = new LinkedList<>();
			List<Float> ls_TotalCostValue = new LinkedList<>();
			List<String> TotalCostHeader = Arrays.asList("Reference hours", "ITE factor", "Installation Hours",
					"Labour rate", "Labor Costs", "Contigency", "Overhead Recovery", "Full Costs", "Total Cost",
					"Ratio of Labor", "Tender Price", "Target Price", "Material costs", "Material cost (SL Currency)");
			/*
			 * System.out.println("Before rotate:\n " + TotalCostHeader);
			 * Collections.rotate(TotalCostHeader, 4); System.out.println("After rotate:\n "
			 * + TotalCostHeader);
			 */
			hm_DetailBreakdownData = new HashMap<String, Float>();
			/*
			 * ls_TotalCostHeader.add("Reference hours");
			 * ls_TotalCostHeader.add("ITE factor");
			 * ls_TotalCostHeader.add("Installation Hours");
			 * ls_TotalCostHeader.add("Labour rate"); ls_TotalCostHeader.add("Labor Costs");
			 * ls_TotalCostHeader.add("Contigency");
			 * ls_TotalCostHeader.add("Overhead Recovery");
			 * ls_TotalCostHeader.add("Full Costs"); ls_TotalCostHeader.add("Total Cost");
			 * ls_TotalCostHeader.add("Ratio of Labor");
			 * ls_TotalCostHeader.add("Tender Price");
			 * ls_TotalCostHeader.add("Target Price");
			 * ls_TotalCostHeader.add("Material costs");
			 * ls_TotalCostHeader.add("Material cost (SL Currency)");
			 */
			Float convertedvalue = null;
			int count = 0;
			int count1 = 1;
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			List<WebElement> Elements_showtotalcostFirstRow = gettingWebElementsfromList(gridvalues_SubTotal);
			wait.until(ExpectedConditions.visibilityOfAllElements(Elements_showtotalcostFirstRow));
			for (WebElement Element_showtotalcostFirstRow : Elements_showtotalcostFirstRow) {
				String getAttribute_showtotalcostFirstRow = Element_showtotalcostFirstRow.getAttribute("value");
				String getText_showtotalcostFirstRow = Element_showtotalcostFirstRow.getText();
				if (getAttribute_showtotalcostFirstRow == null) {
					if (!getText_showtotalcostFirstRow.isEmpty()) {
//			System.out.println("getText:"+Element_showtotalcostFirstRow.getText());
						if (!getText_showtotalcostFirstRow.contains("Subtotal")) {
							count++;
							if (Element_showtotalcostFirstRow.getText().contains("h")) {
								count1 = count;
//									System.out.println(count1);
							}
							String getvalue = Element_showtotalcostFirstRow.getText().replaceAll("[€ % h $]", "");
							getvalue = getvalue.replace(",", ".");
							getvalue = removingDot(getvalue);
							convertedvalue = Float.valueOf(getvalue);
							ls_TotalCostValue.add(convertedvalue);
						}
					}
				} else if (!getAttribute_showtotalcostFirstRow.isEmpty()
						&& getAttribute_showtotalcostFirstRow != null) {
//			System.out.println("getAttribute:"+Element_showtotalcostFirstRow.getAttribute("value"));
					String getvalue = null;
					if (frontlineAssigned.equals("FRANCE")) { // || frontlineAssigned.equals("CANADA")
						getvalue = getAttribute_showtotalcostFirstRow.replaceAll("[€ % h .]", "");
						getvalue = getvalue.replace(",", ".");
					} else if (frontlineAssigned.equals("AUSTRALIA") || frontlineAssigned.equals("CANADA")) {
						getvalue = getAttribute_showtotalcostFirstRow.replaceAll("[€ % h $]", "");
						getvalue = getvalue.replace(",", "");
					}
					convertedvalue = Float.valueOf(getvalue);
					ls_TotalCostValue.add(convertedvalue);
					count++;
				}
			}
			if (count1 == 6) {
				Collections.rotate(TotalCostHeader, 3);
//				System.out.println("After rotate:\n " + TotalCostHeader);
			}
			for (int j = 0; j < ls_TotalCostValue.size(); j++) {
//				System.out.println(TotalCostHeader.get(j));
				ls_TotalCostHeader.add(TotalCostHeader.get(j));
			}
			for (int i = 0; i < ls_TotalCostValue.size(); i++) {
//				System.out.println(i+". "+ls_TotalCostHeader.get(i).toString()+" / "+ ls_TotalCostValue.get(i).toString());
				hm_DetailBreakdownData.put(ls_TotalCostHeader.get(i), ls_TotalCostValue.get(i));
			}
			/*
			 * Float showtotal_TargetPrice= hm_DetailBreakdownData.get("Target Price");
			 * Float showtotal_Materialcosts= hm_DetailBreakdownData.get("Material costs");
			 * Float showtotal_MaterialcostSLCurrency=
			 * hm_DetailBreakdownData.get("Material cost (SL Currency)");
			 * System.out.println("====="+showtotal_TargetPrice+"/"+showtotal_Materialcosts+
			 * "/"+showtotal_MaterialcostSLCurrency);
			 */
			Float showtotal_Referencehours = hm_DetailBreakdownData.get("Reference hours");
			Float showtotal_ITEfactor = hm_DetailBreakdownData.get("ITE factor");
			Float showtotal_InstallationHours = hm_DetailBreakdownData.get("Installation Hours");
			Float showtotal_Labourrate = hm_DetailBreakdownData.get("Labour rate");
			Float showtotal_LaborCosts = hm_DetailBreakdownData.get("Labor Costs");
			/*
			 * Float showtotal_Contigency= hm_DetailBreakdownData.get("Contigency"); Float
			 * showtotal_OverheadRecovery= hm_DetailBreakdownData.get("Overhead Recovery");
			 * Float showtotal_FullCosts= hm_DetailBreakdownData.get("Full Costs"); Float
			 * showtotal_TotalCost= hm_DetailBreakdownData.get("Total Cost"); Float
			 * showtotal_RatioofLabor= hm_DetailBreakdownData.get("Ratio of Labor"); Float
			 * showtotal_TenderPrice= hm_DetailBreakdownData.get("Tender Price");
			 */
			Float check_showtotal_Referencehours = showtotal_InstallationHours / showtotal_ITEfactor;
			Float check_showtotal_Labourrate = showtotal_LaborCosts / showtotal_InstallationHours;
			Float NaN = 0f/0f; //NotaNumber
			if(check_showtotal_Referencehours.equals(NaN)) {
				check_showtotal_Referencehours = 0f;
			}
			if(check_showtotal_Labourrate.equals(NaN)) {
				check_showtotal_Labourrate = 0f;
			}
			/*
			 * System.out.println("is_showtotal_ITEfactor: "+check_showtotal_ITEfactor+"/"+showtotal_ITEfactor);
			 * System.out.println("is_showtotal_LaborCosts "+check_showtotal_Labourrate+"/"+showtotal_Labourrate); 
			 * System.out.println("is_showtotal_Referencehours "+check_showtotal_Referencehours+"/"+showtotal_Referencehours);
			 */
			if (!isMultipleEquipment) {
				check_showtotal_ITEfactor = hm_ITEfactorforSalesOfficeData.get(selectedSalesOffice);
				LabourRate = hm_LabourRateforSalesOfficeData.get(selectedSalesOffice);
			} else {
				check_showtotal_ITEfactor = hm_ITEfactorforSalesOfficeData.get("MultipleEqup_" + selectedSalesOffice);
				LabourRate = hm_LabourRateforSalesOfficeData.get("MultipleEqup_" + selectedSalesOffice);
			}
			Boolean is_showtotal_ITEfactor = roundoff.format(check_showtotal_ITEfactor)
					.equals(roundoff.format(showtotal_ITEfactor));
			Boolean is_showtotal_Referencehours = roundoff.format(check_showtotal_Referencehours)
					.equals(roundoff.format(showtotal_Referencehours));
			Boolean is_showtotal_Labourrate = roundoff.format(check_showtotal_Labourrate)
					.equals(roundoff.format(showtotal_Labourrate));
			Boolean is_Labourrate = roundoff.format(LabourRate).equals(roundoff.format(showtotal_Labourrate));
			System.out.println("check_showtotal_ITEfactor:" + roundoff.format(check_showtotal_ITEfactor)
					+ " / showtotal_ITEfactor:" + roundoff.format(showtotal_ITEfactor));
			System.out.println("check_showtotal_Referencehours:" + roundoff.format(check_showtotal_Referencehours)
					+ " / showtotal_Referencehours:" + roundoff.format(showtotal_Referencehours));
			System.out.println("check_showtotal_Labourrate:" + roundoff.format(check_showtotal_Labourrate)
					+ " / showtotal_Labourrate:" + roundoff.format(showtotal_Labourrate));
			System.out.println("LabourRate:" + roundoff.format(LabourRate) + " / showtotal_Labourrate:"
					+ roundoff.format(showtotal_Labourrate));
			System.out.println("*** is_showtotal_ITEfactor: " + is_showtotal_ITEfactor + " *** / is_Testdata_Labourrate: "
					+ is_Labourrate + " *** / is_showtotal_Labourrate: " + is_showtotal_Labourrate
					+ " *** / is_showtotal_Referencehours: " + is_showtotal_Referencehours + " ***");
			String condition = null;
			if (!roundoff.format(check_showtotal_ITEfactor).equals(roundoff.format(showtotal_ITEfactor))) {
				Float difference = check_showtotal_ITEfactor - showtotal_ITEfactor;
//				System.out.println(roundoff.format(difference));
				if (roundoff.format(difference).equals(roundoff.format(-.00f))
						|| roundoff.format(difference).equals(roundoff.format(-.01f))) {
					check_showtotal_ITEfactor = check_showtotal_ITEfactor + 0.01f;
					condition = "+0.01";
//				System.out.println(roundoff.format(check_showtotal_ITEfactor));
				} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
					check_showtotal_ITEfactor = check_showtotal_ITEfactor + 0.02f;
					condition = "+0.02";
//				System.out.println(roundoff.format(check_showtotal_ITEfactor));
				} else {
					check_showtotal_ITEfactor = check_showtotal_ITEfactor - 0.01f;
					if (roundoff.format(check_showtotal_ITEfactor).equals(roundoff.format(showtotal_ITEfactor))) {
						condition = "-0.01";
					} else {
						check_showtotal_ITEfactor = check_showtotal_ITEfactor - 0.01f;
						condition = "-0.02";
					}
				}
				System.out.println(condition
						+ " Added in ITEfactor hence CalculatedITEfactor VS ActualITEfactor shown in Application is: "
						+ roundoff.format(check_showtotal_ITEfactor).equals(roundoff.format(showtotal_ITEfactor))
						+ " ***");
			}
			String condition1 = null;
			if (!roundoff.format(check_showtotal_Referencehours).equals(roundoff.format(showtotal_Referencehours))) {
				Float difference = check_showtotal_Referencehours - showtotal_Referencehours;
//				System.out.println(roundoff.format(difference));
				if (roundoff.format(difference).equals(roundoff.format(-.00f))
						|| roundoff.format(difference).equals(roundoff.format(-.01f))) {
					check_showtotal_Referencehours = check_showtotal_Referencehours + 0.01f;
					condition = "+0.01";
//				System.out.println(roundoff.format(check_showtotal_Referencehours));
				} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
					check_showtotal_Referencehours = check_showtotal_Referencehours + 0.02f;
					condition = "+0.02";
//				System.out.println(roundoff.format(check_showtotal_Referencehours));
				} else {
					check_showtotal_Referencehours = check_showtotal_Referencehours - 0.01f;
					if (roundoff.format(check_showtotal_Referencehours)
							.equals(roundoff.format(showtotal_Referencehours))) {
						condition1 = "-0.01";
					} else {
						check_showtotal_Referencehours = check_showtotal_Referencehours - 0.01f;
						condition1 = "-0.02";
					}
				}
				System.out.println(condition1
						+ " Added in ITEfactor hence CalculatedReferencehours VS ActualReferencehours shown in Application is: "
						+ roundoff.format(check_showtotal_Referencehours)
								.equals(roundoff.format(showtotal_Referencehours))
						+ " ***");
			}
			String condition2 = null;
			if (!roundoff.format(LabourRate).equals(roundoff.format(showtotal_Labourrate))) {
				Float difference = LabourRate - showtotal_Labourrate;
//				System.out.println(roundoff.format(difference));
				if (roundoff.format(difference).equals(roundoff.format(-.00f))
						|| roundoff.format(difference).equals(roundoff.format(-.01f))) {
					showtotal_Labourrate = showtotal_Labourrate + 0.01f;
					condition = "+0.01";
//				System.out.println(roundoff.format(showtotal_Labourrate));
				} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
					showtotal_Labourrate = showtotal_Labourrate + 0.02f;
					condition = "+0.02";
//				System.out.println(roundoff.format(showtotal_Labourrate));
				} else {
					showtotal_Labourrate = showtotal_Labourrate - 0.01f;
					if (roundoff.format(LabourRate).equals(roundoff.format(showtotal_Labourrate))) {
						condition2 = "-0.01";
					} else {
						showtotal_Labourrate = showtotal_Labourrate - 0.01f;
						condition2 = "-0.02";
					}
				}
				System.out.println(condition2
						+ " Added in ITEfactor hence CalculatedLabourrate VS ActualLabourRate shown in Application is: "
						+ roundoff.format(LabourRate).equals(roundoff.format(showtotal_Labourrate)) + " ***");
			}
			if (!roundoff.format(check_showtotal_ITEfactor).equals(roundoff.format(showtotal_ITEfactor))
					|| !roundoff.format(check_showtotal_Referencehours)
							.equals(roundoff.format(showtotal_Referencehours))
					|| !roundoff.format(check_showtotal_Labourrate).equals(roundoff.format(showtotal_Labourrate))) {
				screenshotCapture("DetailBreakdownTab");
				isDetailBreakdown = false;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Validate Detail Breakdown Tab Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click Detail Breakdown Tab and checks ITE factor,
	 * Labour rate, Reference hours (FullGrid)
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public HashMap<String, Float> hm_DetailBreakdownDataFullGrid;

	public void validateDetailBreakdownTabFullGrid() throws Exception {
		try {
			/*
			 * waitForinvisibilityOfElementLocated(elementtoInvisible); WebElement
			 * scrollto_DetailbreakdownTab = gettingWebElement(tab_detailBreakDown);
			 * scrollIntoView_Javascript(scrollto_DetailbreakdownTab);
			 * waitForVisibilityOfElementLocated(tab_detailBreakDown);
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * clickonButton(tab_detailBreakDown);
			 * System.out.println("Detailed breakdown Clicked");
			 * waitForVisibilityOfElementLocated(dd_selectProject);
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * clickonButton(dd_selectProject); WebElement element_Ropes =
			 * gettingWebElement(value_ropes);
			 * waitForVisibilityOfElementLocated(value_ropes);
			 * waitForElementToBeClickable(value_ropes);
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * wait.until(ExpectedConditions.visibilityOf(element_Ropes));
			 * if(isMultipleEquipment) { By checkbox_forMultipleEquipment =
			 * By.xpath("//*[text()='MMS Electrification Module']"); WebElement
			 * element_forMultipleEquipment =
			 * gettingWebElement(checkbox_forMultipleEquipment);
			 * wait.until(ExpectedConditions.visibilityOf(element_forMultipleEquipment));
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * click_Javascript(element_forMultipleEquipment);
			 * System.out.println(element_forMultipleEquipment+" CLICKED"); } else {
			 * click_Javascript(element_Ropes); System.out.println("ROPES CLICKED"); }
			 * waitForinvisibilityOfElementLocated(value_ropes);
			 */
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			waitForVisibilityOfElementLocated(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"))
					.get(4).click();
			waitForVisibilityOfElementLocated(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			waitForVisibilityOfElementLocated(header_ITEfactor);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			System.out.println("Show TotalCost Calculation Details CLICKED");
			int count = 0;
			List<WebElement> buttons = gettingWebElementsfromList(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			wait.until(ExpectedConditions.visibilityOfAllElements(buttons));
			for (WebElement button : buttons) {
//			System.out.println(button.getAttribute("id"));
				count++;
				if (count == 4) {
					scrollIntoView_Javascript(button);
					wait.until(ExpectedConditions.visibilityOf(button));
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					button.click();
					break;
				}
			}
			waitForVisibilityOfElementLocated(By.xpath("//*[text()='Reference hours']"));

			validateDetailBreakdownTabGetDatafromFullGrid();

			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"))
					.get(4).click();
			waitForVisibilityOfElementLocated(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			int count1 = 0;
			List<WebElement> buttons1 = gettingWebElementsfromList(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			wait.until(ExpectedConditions.visibilityOfAllElements(buttons1));
			for (WebElement button : buttons1) {
//			System.out.println(button.getAttribute("id"));
				count1++;
				if (count1 == 4) {
					scrollIntoView_Javascript(button);
					wait.until(ExpectedConditions.visibilityOf(button));
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					button.click();
					break;
				}
			}
//			System.out.println("elementShowTotalCostCalculationDetail_CLICKED*****BACK="+driver.findElements(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div")).get(4).getAttribute("id"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
//			waitForinvisibilityOfElementLocated(header_ITEfactor);
//			System.out.println("header_ITEfactor disabled");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Validate Detail Breakdown Tab (FullGrid) Failed due to: " + e);
		}
	}

	public HashMap<String, Float> hm_DetailBreakdownDataFullGrid1;

	public void validateDetailBreakdownTabFullGrid1() throws Exception {
		try {
			/*
			 * waitForinvisibilityOfElementLocated(elementtoInvisible); WebElement
			 * scrollto_DetailbreakdownTab = gettingWebElement(tab_detailBreakDown);
			 * scrollIntoView_Javascript(scrollto_DetailbreakdownTab);
			 * waitForVisibilityOfElementLocated(tab_detailBreakDown);
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * clickonButton(tab_detailBreakDown);
			 * System.out.println("Detailed breakdown Clicked");
			 * waitForVisibilityOfElementLocated(dd_selectProject);
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * clickonButton(dd_selectProject); WebElement element_Ropes =
			 * gettingWebElement(value_ropes);
			 * waitForVisibilityOfElementLocated(value_ropes);
			 * waitForElementToBeClickable(value_ropes);
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * wait.until(ExpectedConditions.visibilityOf(element_Ropes));
			 * if(isMultipleEquipment) { By checkbox_forMultipleEquipment =
			 * By.xpath("//*[text()='MMS Electrification Module']"); WebElement
			 * element_forMultipleEquipment =
			 * gettingWebElement(checkbox_forMultipleEquipment);
			 * wait.until(ExpectedConditions.visibilityOf(element_forMultipleEquipment));
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * click_Javascript(element_forMultipleEquipment);
			 * System.out.println(element_forMultipleEquipment+" CLICKED"); } else {
			 * click_Javascript(element_Ropes); System.out.println("ROPES CLICKED"); }
			 * waitForinvisibilityOfElementLocated(value_ropes);
			 */
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			waitForVisibilityOfElementLocated(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"))
					.get(4).click();
			waitForVisibilityOfElementLocated(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			waitForVisibilityOfElementLocated(header_ITEfactor);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			System.out.println("Show TotalCost Calculation Details CLICKED");
			int count = 0;
			List<WebElement> buttons = gettingWebElementsfromList(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			wait.until(ExpectedConditions.visibilityOfAllElements(buttons));
			for (WebElement button : buttons) {
//			System.out.println(button.getAttribute("id"));
				count++;
				if (count == 4) {
					scrollIntoView_Javascript(button);
					wait.until(ExpectedConditions.visibilityOf(button));
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					button.click();
					break;
				}
			}
			waitForVisibilityOfElementLocated(By.xpath("//*[text()='Reference hours']"));

			validateDetailBreakdownTabGetDatafromFullGrid1();

			waitForinvisibilityOfElementLocated(elementtoInvisible);
			gettingWebElementsfromList(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"))
					.get(4).click();
			waitForVisibilityOfElementLocated(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			int count1 = 0;
			List<WebElement> buttons1 = gettingWebElementsfromList(
					By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div"));
			wait.until(ExpectedConditions.visibilityOfAllElements(buttons1));
			for (WebElement button : buttons1) {
//			System.out.println(button.getAttribute("id"));
				count1++;
				if (count1 == 4) {
					scrollIntoView_Javascript(button);
					wait.until(ExpectedConditions.visibilityOf(button));
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					button.click();
					break;
				}
			}
//			System.out.println("elementShowTotalCostCalculationDetail_CLICKED*****BACK="+driver.findElements(By.xpath("//*[@data-ctcwgtname='Toolbar' and @data-ctctype='Toolbar']/div")).get(4).getAttribute("id"));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
//			waitForinvisibilityOfElementLocated(header_ITEfactor);
//			System.out.println("header_ITEfactor disabled");
			if (!isDetailBreakdowndata) {
				isDetailBreakdowndata = true;
				Assert.fail(
						"Failed due to (TargetPrice or Materialcosts or MaterialcostSLCurrency or Referencehours or InstallationHours or LaborCosts or FullCosts or TotalCost or TenderPrice) not euqal in Detail Breakdown Tab");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Validate Detail Breakdown Tab (FullGrid) Failed due to: " + e);
		}
	}

	public Float totalTargetPrice;
	public Float totalMaterialcosts;
	public Float totalMaterialcostSLCurrency;
	public Float totalReferencehours;
	public Float totalInstallationHours;
	public Float totalLaborCosts;
	public Float totalFullCosts;
	public Float totalTotalCost;
	public Float totalTenderPrice;

	public void validateDetailBreakdownTabGetDatafromFullGrid1() throws Exception {
		try {
			List<WebElement> Elements_showtotalcostHeader = gettingWebElementsfromList(
					By.xpath("//*[text()='Subtotal']/../../../../div[2]/div/div[text()='Description']")); // *[text()='Subtotal']/..//*
																											// //*[text()='Subtotal']/../../../../div[2]
			wait.until(ExpectedConditions.visibilityOfAllElements(Elements_showtotalcostHeader));
			List<String> ls_TotalCostHeader = new LinkedList<>();
			hm_DetailBreakdownDataFullGrid = new HashMap<String, Float>();
			HashMap<String, Integer> hm_DetailBreakdownDataSizeFullGrid = new HashMap<String, Integer>();
			List<String> TotalCostHeaderFullGrid = Arrays.asList("Base hours", "Per Service3", "per travel [m]3",
					"Each3", "Reference hours", "ITE factor", "Installation Hours", "Labour rate", "Labor Costs",
					"Contingency", "Overhead Recovery", "Full Costs", "Total Cost", "Tender Price", "Target Price",
					"Base price", "Per Service1", "per travel [m]1", "Each1", "Base cost", "Per Service2",
					"per travel [m]2", "Each2", "Material costs", "Material cost (SL Currency)");
			List<String> numerals = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "€", "$", ".", " ");
			int rotate = 0;
//			System.out.println("Before rotate:\n " + TotalCostHeaderFullGrid);
			List<WebElement> Elements_showtotalcostRowHeader = gettingWebElementsfromList(
					By.xpath("//*[text()='Subtotal']/../../div"));
//			System.out.println(Elements_showtotalcostRowHeader.size());
			wait.until(ExpectedConditions.visibilityOfAllElements(Elements_showtotalcostRowHeader));
			List<String> ls_TotalCostRowHeader = new LinkedList<>();
			int countRowHeader = 0;
			int count = 0;
			int count1 = 1;
			for (WebElement Element_showtotalcostRowHeader : Elements_showtotalcostRowHeader) {
//			System.out.println(Element_showtotalcostRowHeader.getAttribute("id"));
				List<WebElement> Element_showtotalcostRowHeader1 = Element_showtotalcostRowHeader
						.findElements(By.xpath("./*"));
//		int count2=0;
				for (WebElement Element_showtotalcostRowHeader2 : Element_showtotalcostRowHeader1) {
//				System.out.println(Element_showtotalcostRowHeader2.getAttribute("id")+"<==>"+Element_showtotalcostRowHeader2.getText());
					String[] array = Element_showtotalcostRowHeader2.getText().split("");
					if (!Element_showtotalcostRowHeader2.getText().isEmpty()
							&& !Element_showtotalcostRowHeader2.getText().contains("Subtotal") && !array[0].isEmpty()
							&& !numerals.contains(array[0])) {
//					if(!Element_showtotalcostRowHeader2.getText().isEmpty() && !Element_showtotalcostRowHeader2.getText().contains("Subtotal") &&!Element_showtotalcostRowHeader2.getText().contains("€") && !Element_showtotalcostRowHeader2.getText().contains("$") && !Element_showtotalcostRowHeader2.getText().contains(".") && !Element_showtotalcostRowHeader2.getText().equals("0") && !Element_showtotalcostRowHeader2.getText().equals("1") && !Element_showtotalcostRowHeader2.getText().equals("2") && !Element_showtotalcostRowHeader2.getText().equals("3") && !Element_showtotalcostRowHeader2.getText().equals("4") && !Element_showtotalcostRowHeader2.getText().equals("5") && !Element_showtotalcostRowHeader2.getText().equals("6") && !Element_showtotalcostRowHeader2.getText().equals("7") && !Element_showtotalcostRowHeader2.getText().equals("8") && !Element_showtotalcostRowHeader2.getText().equals("9") && !Element_showtotalcostRowHeader2.getText().equals("0")) {						
//		System.out.println("Element_showtotalcostRowHeader2="+Element_showtotalcostRowHeader2.getText());
						/*
						 * if(Element_showtotalcostRowHeader2.getText().equals("SBU_ROPES_BASE_NEW")) {
						 */
//		System.out.println(count2+" showtotalcostRowHeader="+Element_showtotalcostRowHeader2.getText());
						/* } */
						ls_TotalCostRowHeader.add(Element_showtotalcostRowHeader2.getText());
						List<WebElement> Element_showtotalcostRowHeader3 = Element_showtotalcostRowHeader2
								.findElements(By.xpath("./../*"));
						Float convertedvalue = null;
						List<Float> ls_TotalCostRowRowValues = new LinkedList<>();
						for (WebElement Element_showtotalcostRowHeader4 : Element_showtotalcostRowHeader3) {
//					System.out.println(Element_showtotalcostRowHeader4.getAttribute("id")+"//"+Element_showtotalcostRowHeader4.getText()+"//"+Element_showtotalcostRowHeader4.getAttribute("value"));
							if (Element_showtotalcostRowHeader4.getAttribute("value") == null) {
								if (!Element_showtotalcostRowHeader4.getText().isEmpty()) {
//					System.out.println("getText:"+Element_showtotalcostRowHeader4.getText());
									if (!ls_TotalCostRowHeader.contains(Element_showtotalcostRowHeader4.getText())) {
//					System.out.println(Element_showtotalcostRowHeader4.getAttribute("id")+"_//getText:"+Element_showtotalcostRowHeader4.getText());
										count++;
										if (Element_showtotalcostRowHeader4.getText().contains("h")) {
											count1 = count;
//										System.out.println("index of h= "+count1);
										}
										String getvalue = Element_showtotalcostRowHeader4.getText()
												.replaceAll("[€ % h $]", "");
										getvalue = getvalue.replace(",", ".");
										getvalue = removingDot(getvalue);
										convertedvalue = Float.valueOf(getvalue);
										ls_TotalCostRowRowValues.add(convertedvalue);
									}
								}
							} else if (!Element_showtotalcostRowHeader4.getAttribute("value").isEmpty()
									&& Element_showtotalcostRowHeader4.getAttribute("value") != null) {
//				System.out.println(Element_showtotalcostRowHeader4.getAttribute("id")+"_//getAttribute:"+Element_showtotalcostRowHeader4.getAttribute("value"));
								String getvalue = null;
								if (frontlineAssigned.equals("FRANCE")) { // || frontlineAssigned.equals("CANADA")
									getvalue = Element_showtotalcostRowHeader4.getAttribute("value")
											.replaceAll("[€ % h .]", "");
									getvalue = getvalue.replace(",", ".");
								} else if (frontlineAssigned.equals("AUSTRALIA") || frontlineAssigned.equals("CANADA")) {
									getvalue = Element_showtotalcostRowHeader4.getAttribute("value")
											.replaceAll("[€ % h $]", "");
									getvalue = getvalue.replace(",", "");
								}
								convertedvalue = Float.valueOf(getvalue);
								ls_TotalCostRowRowValues.add(convertedvalue);
								count++;
								if ((Element_showtotalcostRowHeader2.getText().equals("SBU_ROPES_BASE_NEW")
										|| Element_showtotalcostRowHeader2.getText().equals("FreightCost_SBU_Ropes_new")
										|| Element_showtotalcostRowHeader2.getText().equals("Process Cost"))
										&& count == 15) {
									ls_TotalCostRowRowValues.add(count, 0f);
									ls_TotalCostRowRowValues.add(count + 1, 0f);
									ls_TotalCostRowRowValues.add(count + 2, 0f);
									ls_TotalCostRowRowValues.add(count + 3, 0f);
								}
							}
						}
						count = 0;
						if (count1 != 7) {
							if (rotate == 0) {
								Collections.rotate(TotalCostHeaderFullGrid, 20);
								rotate++;
//								System.out.println("After rotate:\n " + TotalCostHeaderFullGrid);
							}
							if (Element_showtotalcostRowHeader2.getText().equals("SBU_ROPES_BASE_NEW")
									|| Element_showtotalcostRowHeader2.getText().equals("FreightCost_SBU_Ropes_new")
									|| Element_showtotalcostRowHeader2.getText().equals("Process Cost")) {
								ls_TotalCostRowRowValues.add(9, 0f);
								ls_TotalCostRowRowValues.add(10, 0f);
								ls_TotalCostRowRowValues.add(11, 0f);
								ls_TotalCostRowRowValues.add(12, 0f);
							}
						}
						for (int j = 0; j < ls_TotalCostRowRowValues.size(); j++) {
							/*
							 * if(count1 != 7 && (rotate ==0 || rotate ==1) && (j==1 || j==20 || j==21 ||
							 * j==22 || j==23 || j==24)) {
							 * System.out.println("HI:"+TotalCostHeaderFullGrid.get(j)); } else {
							 */
							ls_TotalCostHeader.add(TotalCostHeaderFullGrid.get(j));
//								System.out.println(TotalCostHeaderFullGrid.get(j));
//							}
						}
						for (int i = 0; i < ls_TotalCostRowRowValues.size(); i++) {
//							System.out.println(i+". "+ls_TotalCostRowHeader.get(countRowHeader)+"_"+ls_TotalCostHeader.get(i)+"="+ls_TotalCostRowRowValues.get(i));
							hm_DetailBreakdownDataFullGrid.put(
									ls_TotalCostRowHeader.get(countRowHeader) + "_" + ls_TotalCostHeader.get(i),
									ls_TotalCostRowRowValues.get(i));
						}
						hm_DetailBreakdownDataSizeFullGrid.put(ls_TotalCostRowHeader.get(countRowHeader),
								ls_TotalCostRowRowValues.size());
//						System.out.println(ls_TotalCostRowHeader.get(countRowHeader)+"="+ls_TotalCostRowRowValues.size());
						countRowHeader++;
					}
					// below implemention done due to Australia - Detailed Breakdown tab, a row
					// header is starting with Numeral as “3. OSG rope reel option”
					else if (!Element_showtotalcostRowHeader2.getText().isEmpty()
							&& !Element_showtotalcostRowHeader2.getText().contains("Subtotal") && !array[0].isEmpty()) { // numerals.contains(array[0])
																															// &&
																															// array.length
																															// >5
						if (numerals.contains(array[0]) && array.length > 10 && !array[5].isEmpty() && array[5] != null
								&& !numerals.contains(array[5])) { // array[5]!=null && !numerals.contains(array[5])
//						System.out.println(" showtotalcostRowHeader="+Element_showtotalcostRowHeader2.getText());
							ls_TotalCostRowHeader.add(Element_showtotalcostRowHeader2.getText());
							List<WebElement> Element_showtotalcostRowHeader3 = Element_showtotalcostRowHeader2
									.findElements(By.xpath("./../*"));
							Float convertedvalue = null;
							List<Float> ls_TotalCostRowRowValues = new LinkedList<>();
							for (WebElement Element_showtotalcostRowHeader4 : Element_showtotalcostRowHeader3) {
//					System.out.println(Element_showtotalcostRowHeader4.getAttribute("id")+"//"+Element_showtotalcostRowHeader4.getText()+"//"+Element_showtotalcostRowHeader4.getAttribute("value"));
								if (Element_showtotalcostRowHeader4.getAttribute("value") == null) {
									if (!Element_showtotalcostRowHeader4.getText().isEmpty()) {
//					System.out.println("getText:"+Element_showtotalcostRowHeader4.getText());
										if (!ls_TotalCostRowHeader
												.contains(Element_showtotalcostRowHeader4.getText())) {
//					System.out.println(Element_showtotalcostRowHeader4.getAttribute("id")+"_//getText:"+Element_showtotalcostRowHeader4.getText());
											count++;
											if (Element_showtotalcostRowHeader4.getText().contains("h")) {
												count1 = count;
//										System.out.println("index of h= "+count1);
											}
											String getvalue = Element_showtotalcostRowHeader4.getText()
													.replaceAll("[€ % h $]", "");
											getvalue = getvalue.replace(",", ".");
											getvalue = removingDot(getvalue);
											convertedvalue = Float.valueOf(getvalue);
											ls_TotalCostRowRowValues.add(convertedvalue);
										}
									}
								} else if (!Element_showtotalcostRowHeader4.getAttribute("value").isEmpty()
										&& Element_showtotalcostRowHeader4.getAttribute("value") != null) {
//				System.out.println(Element_showtotalcostRowHeader4.getAttribute("id")+"_//getAttribute:"+Element_showtotalcostRowHeader4.getAttribute("value"));
									String getvalue = null;
									if (frontlineAssigned.equals("FRANCE") || frontlineAssigned.equals("CANADA")) {
										getvalue = Element_showtotalcostRowHeader4.getAttribute("value")
												.replaceAll("[€ % h .]", "");
										getvalue = getvalue.replace(",", ".");
									} else if (frontlineAssigned.equals("AUSTRALIA")) {
										getvalue = Element_showtotalcostRowHeader4.getAttribute("value")
												.replaceAll("[€ % h $]", "");
										getvalue = getvalue.replace(",", "");
									}
									convertedvalue = Float.valueOf(getvalue);
									ls_TotalCostRowRowValues.add(convertedvalue);
									count++;
									if ((Element_showtotalcostRowHeader2.getText().equals("SBU_ROPES_BASE_NEW")
											|| Element_showtotalcostRowHeader2.getText()
													.equals("FreightCost_SBU_Ropes_new")
											|| Element_showtotalcostRowHeader2.getText().equals("Process Cost"))
											&& count == 15) {
										ls_TotalCostRowRowValues.add(count, 0f);
										ls_TotalCostRowRowValues.add(count + 1, 0f);
										ls_TotalCostRowRowValues.add(count + 2, 0f);
										ls_TotalCostRowRowValues.add(count + 3, 0f);
									}
								}
							}
							count = 0;
							if (count1 != 7) {
								if (rotate == 0) {
									Collections.rotate(TotalCostHeaderFullGrid, 20);
									rotate++;
//								System.out.println("After rotate:\n " + TotalCostHeaderFullGrid);
								}
								if (Element_showtotalcostRowHeader2.getText().equals("SBU_ROPES_BASE_NEW")
										|| Element_showtotalcostRowHeader2.getText().equals("FreightCost_SBU_Ropes_new")
										|| Element_showtotalcostRowHeader2.getText().equals("Process Cost")) {
									ls_TotalCostRowRowValues.add(9, 0f);
									ls_TotalCostRowRowValues.add(10, 0f);
									ls_TotalCostRowRowValues.add(11, 0f);
									ls_TotalCostRowRowValues.add(12, 0f);
								}
							}
							for (int j = 0; j < ls_TotalCostRowRowValues.size(); j++) {
								/*
								 * if(count1 != 7 && (rotate ==0 || rotate ==1) && (j==1 || j==20 || j==21 ||
								 * j==22 || j==23 || j==24)) {
								 * System.out.println("HI:"+TotalCostHeaderFullGrid.get(j)); } else {
								 */
								ls_TotalCostHeader.add(TotalCostHeaderFullGrid.get(j));
//								System.out.println(TotalCostHeaderFullGrid.get(j));
//							}
							}
							for (int i = 0; i < ls_TotalCostRowRowValues.size(); i++) {
//							System.out.println(i+". "+ls_TotalCostRowHeader.get(countRowHeader)+"_"+ls_TotalCostHeader.get(i)+"="+ls_TotalCostRowRowValues.get(i));
								hm_DetailBreakdownDataFullGrid.put(
										ls_TotalCostRowHeader.get(countRowHeader) + "_" + ls_TotalCostHeader.get(i),
										ls_TotalCostRowRowValues.get(i));
							}
							hm_DetailBreakdownDataSizeFullGrid.put(ls_TotalCostRowHeader.get(countRowHeader),
									ls_TotalCostRowRowValues.size());
//						System.out.println(ls_TotalCostRowHeader.get(countRowHeader)+"="+ls_TotalCostRowRowValues.size());
							countRowHeader++;
						}
					}
//					count2++;
				}
			}
//			hm_DetailBreakdownDataFullGrid.forEach((key, value) -> {System.out.println("Key : " + key + " Value : " + value);});
//			System.out.println("countRowHeader:"+countRowHeader);
			/*
			 * for(int j=0; j<ls_TotalCostRowHeader.size(); j++) { int b=j+1;
			 * System.out.println(b+". "+ls_TotalCostRowHeader.get(j)+" /size:"
			 * +hm_DetailBreakdownDataSizeFullGrid.get(ls_TotalCostRowHeader.get(j)));
			 * System.out.println("-----------------------------------------------");
			 * for(int i=0;
			 * i<hm_DetailBreakdownDataSizeFullGrid.get(ls_TotalCostRowHeader.get(j)); i++)
			 * { int a=i+1; System.out.println(" "+a+"."+ls_TotalCostRowHeader.get(j)+"_"+
			 * ls_TotalCostHeader.get(i)+"="+hm_DetailBreakdownDataFullGrid.get(
			 * ls_TotalCostRowHeader.get(j)+"_"+ls_TotalCostHeader.get(i))); } }
			 */
			totalTargetPrice = 0f;
			totalMaterialcosts = 0f;
			totalMaterialcostSLCurrency = 0f;
			totalReferencehours = 0f;
			totalInstallationHours = 0f;
			totalLaborCosts = 0f;
			totalFullCosts = 0f;
			totalTotalCost = 0f;
			totalTenderPrice = 0f;
			for (Map.Entry<String, Float> entry : hm_DetailBreakdownDataFullGrid.entrySet()) {
				String key = entry.getKey();
				Float value = entry.getValue();
//			    System.out.println(key+"=="+value);
				if (key.contains("_Target Price")) {
					Float totalTargetPrice12 = value;
					totalTargetPrice = totalTargetPrice + totalTargetPrice12;
				} else if (key.contains("_Material costs")) {
					Float totalMaterialcosts12 = value;
					totalMaterialcosts = totalMaterialcosts + totalMaterialcosts12;
				} else if (key.contains("_Material cost (SL Currency)")) {
//			    	System.out.println(key+"=="+value);
					Float totalaterialcostSLCurrency12 = value;
					totalMaterialcostSLCurrency = totalMaterialcostSLCurrency + totalaterialcostSLCurrency12;
				} else if (key.contains("_Reference hours")) {
					Float totalReferencehours12 = value;
					totalReferencehours = totalReferencehours + totalReferencehours12;
				} else if (key.contains("_Installation Hours")) {
					Float totalInstallationHours12 = value;
					totalInstallationHours = totalInstallationHours + totalInstallationHours12;
				} else if (key.contains("_Labor Costs")) {
					Float totalLaborCosts12 = value;
					totalLaborCosts = totalLaborCosts + totalLaborCosts12;
				} else if (key.contains("_Full Costs")) {
					Float totalFullCosts12 = value;
					totalFullCosts = totalFullCosts + totalFullCosts12;
				} else if (key.contains("_Total Cost")) {
					Float totalTotalCost12 = value;
					totalTotalCost = totalTotalCost + totalTotalCost12;
				} else if (key.contains("_Tender Price")) {
					Float totalTenderPrice12 = value;
					totalTenderPrice = totalTenderPrice + totalTenderPrice12;
				}
			}
			/*
			 * System.out.println("totalTargetPrice="+totalTargetPrice);
			 * System.out.println("totalMaterialcosts="+totalMaterialcosts);
			 * System.out.println("totalaterialcostSLCurrency="+totalMaterialcostSLCurrency)
			 * ; System.out.println("totalReferencehours="+totalReferencehours);
			 * System.out.println("totalInstallationHours="+totalInstallationHours);
			 * System.out.println("totalLaborCosts="+totalLaborCosts);
			 * System.out.println("totalFullCosts="+totalFullCosts);
			 * System.out.println("totalTotalCost="+totalTotalCost);
			 * System.out.println("totalTenderPrice="+totalTenderPrice);
			 */
			validateDetailBreakdownTabCheckTotalValuesinFullGrid();
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Validate Detail Breakdown Tab (FullGrid) GetData from FullGrid Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will get data from Detail Breakdown (FullGrid)
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void validateDetailBreakdownTabGetDatafromFullGrid() throws Exception {
		try {
			List<WebElement> Elements_showtotalcostHeader = gettingWebElementsfromList(
					By.xpath("//*[text()='Subtotal']/../../../../div[2]/div/div[text()='Description']")); // *[text()='Subtotal']/..//*
																											// //*[text()='Subtotal']/../../../../div[2]
			wait.until(ExpectedConditions.visibilityOfAllElements(Elements_showtotalcostHeader));
			List<String> ls_TotalCostHeader = new LinkedList<>();
			hm_DetailBreakdownDataFullGrid = new HashMap<String, Float>();
			HashMap<String, Integer> hm_DetailBreakdownDataSizeFullGrid = new HashMap<String, Integer>();
			List<String> TotalCostHeaderFullGrid = Arrays.asList("Base hours", "Per Service3", "per travel [m]3",
					"Each3", "Reference hours", "ITE factor", "Installation Hours", "Labour rate", "Labor Costs",
					"Contingency", "Overhead Recovery", "Full Costs", "Total Cost", "Tender Price", "Target Price",
					"Base price", "Per Service1", "per travel [m]1", "Each1", "Base cost", "Per Service2",
					"per travel [m]2", "Each2", "Material costs", "Material cost (SL Currency)");
			List<String> numerals = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "€", "$", ".");
			int rotate = 0;
//			System.out.println("Before rotate:\n " + TotalCostHeaderFullGrid);
			List<WebElement> Elements_showtotalcostRowHeader = gettingWebElementsfromList(
					By.xpath("//*[text()='Subtotal']/../../div"));
//			System.out.println(Elements_showtotalcostRowHeader.size());
			wait.until(ExpectedConditions.visibilityOfAllElements(Elements_showtotalcostRowHeader));
			List<String> ls_TotalCostRowHeader = new LinkedList<>();
			int countRowHeader = 0;
			int count = 0;
			int count1 = 1;
			for (WebElement Element_showtotalcostRowHeader : Elements_showtotalcostRowHeader) {
//			System.out.println(Element_showtotalcostRowHeader.getAttribute("id"));
				List<WebElement> Element_showtotalcostRowHeader1 = Element_showtotalcostRowHeader
						.findElements(By.xpath("./*"));
//		int count2=0;
				for (WebElement Element_showtotalcostRowHeader2 : Element_showtotalcostRowHeader1) {
//				System.out.println(Element_showtotalcostRowHeader2.getAttribute("id")+"<==>"+Element_showtotalcostRowHeader2.getText());
					String[] array = Element_showtotalcostRowHeader2.getText().split("");
					if (!Element_showtotalcostRowHeader2.getText().isEmpty()
							&& !Element_showtotalcostRowHeader2.getText().contains("Subtotal") && !array[0].isEmpty()
							&& !numerals.contains(array[0])) {
//					if(!Element_showtotalcostRowHeader2.getText().isEmpty() && !Element_showtotalcostRowHeader2.getText().contains("Subtotal") &&!Element_showtotalcostRowHeader2.getText().contains("€") && !Element_showtotalcostRowHeader2.getText().contains("$") && !Element_showtotalcostRowHeader2.getText().contains(".") && !Element_showtotalcostRowHeader2.getText().equals("0") && !Element_showtotalcostRowHeader2.getText().equals("1") && !Element_showtotalcostRowHeader2.getText().equals("2") && !Element_showtotalcostRowHeader2.getText().equals("3") && !Element_showtotalcostRowHeader2.getText().equals("4") && !Element_showtotalcostRowHeader2.getText().equals("5") && !Element_showtotalcostRowHeader2.getText().equals("6") && !Element_showtotalcostRowHeader2.getText().equals("7") && !Element_showtotalcostRowHeader2.getText().equals("8") && !Element_showtotalcostRowHeader2.getText().equals("9") && !Element_showtotalcostRowHeader2.getText().equals("0")) {						
//		System.out.println("Element_showtotalcostRowHeader2="+Element_showtotalcostRowHeader2.getText());
						/*
						 * if(Element_showtotalcostRowHeader2.getText().equals("SBU_ROPES_BASE_NEW")) {
						 */
//		System.out.println(count2+" showtotalcostRowHeader="+Element_showtotalcostRowHeader2.getText());
						/* } */
						ls_TotalCostRowHeader.add(Element_showtotalcostRowHeader2.getText());
						List<WebElement> Element_showtotalcostRowHeader3 = Element_showtotalcostRowHeader2
								.findElements(By.xpath("./../*"));
						Float convertedvalue = null;
						List<Float> ls_TotalCostRowRowValues = new LinkedList<>();
						for (WebElement Element_showtotalcostRowHeader4 : Element_showtotalcostRowHeader3) {
//					System.out.println(Element_showtotalcostRowHeader4.getAttribute("id")+"//"+Element_showtotalcostRowHeader4.getText()+"//"+Element_showtotalcostRowHeader4.getAttribute("value"));
							if (Element_showtotalcostRowHeader4.getAttribute("value") == null) {
								if (!Element_showtotalcostRowHeader4.getText().isEmpty()) {
//					System.out.println("getText:"+Element_showtotalcostRowHeader4.getText());
									if (!ls_TotalCostRowHeader.contains(Element_showtotalcostRowHeader4.getText())) {
//					System.out.println(Element_showtotalcostRowHeader4.getAttribute("id")+"_//getText:"+Element_showtotalcostRowHeader4.getText());
										count++;
										if (Element_showtotalcostRowHeader4.getText().contains("h")) {
											count1 = count;
//										System.out.println("index of h= "+count1);
										}
										String getvalue = Element_showtotalcostRowHeader4.getText()
												.replaceAll("[€ % h $]", "");
										getvalue = getvalue.replace(",", ".");
										getvalue = removingDot(getvalue);
										convertedvalue = Float.valueOf(getvalue);
										ls_TotalCostRowRowValues.add(convertedvalue);
									}
								}
							} else if (!Element_showtotalcostRowHeader4.getAttribute("value").isEmpty()
									&& Element_showtotalcostRowHeader4.getAttribute("value") != null) {
//				System.out.println(Element_showtotalcostRowHeader4.getAttribute("id")+"_//getAttribute:"+Element_showtotalcostRowHeader4.getAttribute("value"));
								String getvalue = null;
								if (frontlineAssigned.equals("FRANCE") || frontlineAssigned.equals("CANADA")) {
									getvalue = Element_showtotalcostRowHeader4.getAttribute("value")
											.replaceAll("[€ % h .]", "");
									getvalue = getvalue.replace(",", ".");
								} else if (frontlineAssigned.equals("AUSTRALIA")) {
									getvalue = Element_showtotalcostRowHeader4.getAttribute("value")
											.replaceAll("[€ % h $]", "");
									getvalue = getvalue.replace(",", "");
								}
								convertedvalue = Float.valueOf(getvalue);
								ls_TotalCostRowRowValues.add(convertedvalue);
								count++;
								if ((Element_showtotalcostRowHeader2.getText().equals("SBU_ROPES_BASE_NEW")
										|| Element_showtotalcostRowHeader2.getText().equals("FreightCost_SBU_Ropes_new")
										|| Element_showtotalcostRowHeader2.getText().equals("Process Cost"))
										&& count == 15) {
									ls_TotalCostRowRowValues.add(count, 0f);
									ls_TotalCostRowRowValues.add(count + 1, 0f);
									ls_TotalCostRowRowValues.add(count + 2, 0f);
									ls_TotalCostRowRowValues.add(count + 3, 0f);
								}
							}
						}
						count = 0;
						if (count1 != 7) {
							if (rotate == 0) {
								Collections.rotate(TotalCostHeaderFullGrid, 20);
								rotate++;
//								System.out.println("After rotate:\n " + TotalCostHeaderFullGrid);
							}
							if (Element_showtotalcostRowHeader2.getText().equals("SBU_ROPES_BASE_NEW")
									|| Element_showtotalcostRowHeader2.getText().equals("FreightCost_SBU_Ropes_new")
									|| Element_showtotalcostRowHeader2.getText().equals("Process Cost")) {
								ls_TotalCostRowRowValues.add(9, 0f);
								ls_TotalCostRowRowValues.add(10, 0f);
								ls_TotalCostRowRowValues.add(11, 0f);
								ls_TotalCostRowRowValues.add(12, 0f);
							}
						}
						for (int j = 0; j < ls_TotalCostRowRowValues.size(); j++) {
							/*
							 * if(count1 != 7 && (rotate ==0 || rotate ==1) && (j==1 || j==20 || j==21 ||
							 * j==22 || j==23 || j==24)) {
							 * System.out.println("HI:"+TotalCostHeaderFullGrid.get(j)); } else {
							 */
							ls_TotalCostHeader.add(TotalCostHeaderFullGrid.get(j));
//								System.out.println(TotalCostHeaderFullGrid.get(j));
//							}
						}
						for (int i = 0; i < ls_TotalCostRowRowValues.size(); i++) {
//							System.out.println(i+". "+ls_TotalCostRowHeader.get(countRowHeader)+"_"+ls_TotalCostHeader.get(i)+"="+ls_TotalCostRowRowValues.get(i));
							hm_DetailBreakdownDataFullGrid.put(
									ls_TotalCostRowHeader.get(countRowHeader) + "_" + ls_TotalCostHeader.get(i),
									ls_TotalCostRowRowValues.get(i));
						}
						hm_DetailBreakdownDataSizeFullGrid.put(ls_TotalCostRowHeader.get(countRowHeader),
								ls_TotalCostRowRowValues.size());
//						System.out.println(ls_TotalCostRowHeader.get(countRowHeader)+"="+ls_TotalCostRowRowValues.size());
						countRowHeader++;
					}
//					count2++;
				}
			}
//			hm_DetailBreakdownDataFullGrid.forEach((key, value) -> {System.out.println("Key : " + key + " Value : " + value);});
//			System.out.println("countRowHeader:"+countRowHeader);
			/*
			 * for(int j=0; j<ls_TotalCostRowHeader.size(); j++) { int b=j+1;
			 * System.out.println(b+". "+ls_TotalCostRowHeader.get(j)+" /size:"
			 * +hm_DetailBreakdownDataSizeFullGrid.get(ls_TotalCostRowHeader.get(j)));
			 * System.out.println("-----------------------------------------------");
			 * for(int i=0;
			 * i<hm_DetailBreakdownDataSizeFullGrid.get(ls_TotalCostRowHeader.get(j)); i++)
			 * { int a=i+1; System.out.println(" "+a+"."+ls_TotalCostRowHeader.get(j)+"_"+
			 * ls_TotalCostHeader.get(i)+"="+hm_DetailBreakdownDataFullGrid.get(
			 * ls_TotalCostRowHeader.get(j)+"_"+ls_TotalCostHeader.get(i))); } }
			 */
			totalTargetPrice = 0f;
			totalMaterialcosts = 0f;
			totalMaterialcostSLCurrency = 0f;
			totalReferencehours = 0f;
			totalInstallationHours = 0f;
			totalLaborCosts = 0f;
			totalFullCosts = 0f;
			totalTotalCost = 0f;
			totalTenderPrice = 0f;
			for (Map.Entry<String, Float> entry : hm_DetailBreakdownDataFullGrid.entrySet()) {
				String key = entry.getKey();
				Float value = entry.getValue();
//			    System.out.println(key+"=="+value);
				if (key.contains("_Target Price")) {
					Float totalTargetPrice1 = value;
					totalTargetPrice = totalTargetPrice + totalTargetPrice1;
				} else if (key.contains("_Material costs")) {
					Float totalMaterialcosts1 = value;
					totalMaterialcosts = totalMaterialcosts + totalMaterialcosts1;
				} else if (key.contains("_Material cost (SL Currency)")) {
//			    	System.out.println(key+"=="+value);
					Float totalaterialcostSLCurrency1 = value;
					totalMaterialcostSLCurrency = totalMaterialcostSLCurrency + totalaterialcostSLCurrency1;
				} else if (key.contains("_Reference hours")) {
					Float totalReferencehours1 = value;
					totalReferencehours = totalReferencehours + totalReferencehours1;
				} else if (key.contains("_Installation Hours")) {
					Float totalInstallationHours1 = value;
					totalInstallationHours = totalInstallationHours + totalInstallationHours1;
				} else if (key.contains("_Labor Costs")) {
					Float totalLaborCosts1 = value;
					totalLaborCosts = totalLaborCosts + totalLaborCosts1;
				} else if (key.contains("_Full Costs")) {
					Float totalFullCosts1 = value;
					totalFullCosts = totalFullCosts + totalFullCosts1;
				} else if (key.contains("_Total Cost")) {
					Float totalTotalCost1 = value;
					totalTotalCost = totalTotalCost + totalTotalCost1;
				} else if (key.contains("_Tender Price")) {
					Float totalTenderPrice1 = value;
					totalTenderPrice = totalTenderPrice + totalTenderPrice1;
				}
			}
			/*
			 * System.out.println("totalTargetPrice="+totalTargetPrice);
			 * System.out.println("totalMaterialcosts="+totalMaterialcosts);
			 * System.out.println("totalaterialcostSLCurrency="+totalMaterialcostSLCurrency)
			 * ; System.out.println("totalReferencehours="+totalReferencehours);
			 * System.out.println("totalInstallationHours="+totalInstallationHours);
			 * System.out.println("totalLaborCosts="+totalLaborCosts);
			 * System.out.println("totalFullCosts="+totalFullCosts);
			 * System.out.println("totalTotalCost="+totalTotalCost);
			 * System.out.println("totalTenderPrice="+totalTenderPrice);
			 */
			validateDetailBreakdownTabCheckTotalValuesinFullGrid();
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Validate Detail Breakdown Tab (FullGrid) GetData from FullGrid Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will compare data from Detail Breakdown (FullGrid)
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public boolean isDetailBreakdowndata = true;

	public void validateDetailBreakdownTabCheckTotalValuesinFullGrid() throws Exception {
		try {
			Float subtotal_TargetPrice = hm_DetailBreakdownData.get("Target Price");
			Float subtotal_Materialcosts = hm_DetailBreakdownData.get("Material costs");
			Float subtotal_MaterialcostSLCurrency = hm_DetailBreakdownData.get("Material cost (SL Currency)");
			Float subtotal_Referencehours = hm_DetailBreakdownData.get("Reference hours");
			Float subtotal_InstallationHours = hm_DetailBreakdownData.get("Installation Hours");
			Float subtotal_LaborCosts = hm_DetailBreakdownData.get("Labor Costs");
			Float subtotal_FullCosts = hm_DetailBreakdownData.get("Full Costs");
			Float subtotal_TotalCost = hm_DetailBreakdownData.get("Total Cost");
			Float subtotal_TenderPrice = hm_DetailBreakdownData.get("Tender Price");
//			System.out.println("====="+subtotal_TargetPrice+"/"+subtotal_Materialcosts+"/"+subtotal_MaterialcostSLCurrency+"/"+subtotal_Referencehours+"/"+subtotal_InstallationHours+"/"+subtotal_LaborCosts+"/"+subtotal_FullCosts+"/"+subtotal_TotalCost+"/"+subtotal_TenderPrice);
			System.out.println("totalTargetPrice:" + roundoff.format(totalTargetPrice) + " / subtotal_TargetPrice:"
					+ roundoff.format(subtotal_TargetPrice));
			Boolean TargetPrice = roundoff.format(totalTargetPrice).equals(roundoff.format(subtotal_TargetPrice));
			System.out.println("*** is totalTargetPrice vs subtotal_TargetPrice equal: " + TargetPrice);
			System.out.println("totalMaterialcosts=" + roundoff.format(totalMaterialcosts)
					+ " / subtotal_Materialcosts:" + subtotal_Materialcosts);
			String condition = null;
			if (!roundoff.format(totalMaterialcosts).equals(roundoff.format(subtotal_Materialcosts))) {
				Float difference = totalMaterialcosts - subtotal_Materialcosts;
//			System.out.println(roundoff.format(difference));
				if (roundoff.format(difference).equals(roundoff.format(-.00f))
						|| roundoff.format(difference).equals(roundoff.format(-.01f))) {
					totalMaterialcosts = totalMaterialcosts + 0.01f;
					condition = "+0.01";
//			System.out.println(roundoff.format(totalMaterialcosts));
				} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
					totalMaterialcosts = totalMaterialcosts + 0.02f;
					condition = "+0.02";
//			System.out.println(roundoff.format(totalMaterialcosts));
				} else {
					totalMaterialcosts = totalMaterialcosts + 0.01f;
					if (roundoff.format(totalMaterialcosts).equals(roundoff.format(subtotal_Materialcosts))) {
						condition = "0.01";
					} else {
						totalMaterialcosts = totalMaterialcosts - 0.02f;
						condition = "0.02";
					}
				}
			}
			Boolean Materialcosts = roundoff.format(totalMaterialcosts).equals(roundoff.format(subtotal_Materialcosts));
			System.out.println("*** " + condition
					+ " Added in totalMaterialcosts hence totalMaterialcosts vs subtotal_Materialcosts equal: "
					+ Materialcosts);
			System.out.println("totalMaterialcostSLCurrency=" + roundoff.format(totalMaterialcostSLCurrency)
					+ " / subtotal_MaterialcostSLCurrency:" + roundoff.format(subtotal_MaterialcostSLCurrency));
			String condition1 = null;
			if (!roundoff.format(totalMaterialcostSLCurrency)
					.equals(roundoff.format(subtotal_MaterialcostSLCurrency))) {
				Float difference = totalMaterialcostSLCurrency - subtotal_MaterialcostSLCurrency;
//			System.out.println(roundoff.format(difference));
				if (roundoff.format(difference).equals(roundoff.format(-.00f))
						|| roundoff.format(difference).equals(roundoff.format(-.01f))) {
					totalMaterialcostSLCurrency = totalMaterialcostSLCurrency + 0.01f;
					condition1 = "+0.01";
//			System.out.println(roundoff.format(totalMaterialcostSLCurrency));
				} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
					totalMaterialcostSLCurrency = totalMaterialcostSLCurrency + 0.02f;
					condition1 = "+0.02";
//			System.out.println(roundoff.format(totalMaterialcostSLCurrency));
				} else {
					totalMaterialcostSLCurrency = totalMaterialcostSLCurrency - 0.01f;
					if (roundoff.format(totalMaterialcostSLCurrency)
							.equals(roundoff.format(subtotal_MaterialcostSLCurrency))) {
						condition1 = "-0.01";
					} else {
						totalMaterialcostSLCurrency = totalMaterialcostSLCurrency - 0.01f;
						condition1 = "-0.02";
					}
				}
			}
			Boolean MaterialcostSLCurrency = roundoff.format(totalMaterialcostSLCurrency)
					.equals(roundoff.format(subtotal_MaterialcostSLCurrency));
			System.out.println("*** " + condition1
					+ " Added in totalMaterialcostSLCurrency hence totalMaterialcostSLCurrency vs subtotal_MaterialcostSLCurrency equal: "
					+ MaterialcostSLCurrency);
			System.out.println("totalReferencehours=" + roundoff.format(totalReferencehours)
					+ " / subtotal_Referencehours:" + roundoff.format(subtotal_Referencehours));
			Boolean Referencehours = roundoff.format(totalReferencehours)
					.equals(roundoff.format(subtotal_Referencehours));
			System.out.println("*** is totalReferencehours vs subtotal_Referencehours equal: " + Referencehours);
			System.out.println("totalInstallationHours=" + roundoff.format(totalInstallationHours)
					+ " / subtotal_InstallationHours:" + roundoff.format(subtotal_InstallationHours));
			Boolean InstallationHours = roundoff.format(totalInstallationHours)
					.equals(roundoff.format(subtotal_InstallationHours));
			System.out.println("*** is totalInstallationHours vs subtotal_Materialcosts equal: " + InstallationHours);
			System.out.println("totalLaborCosts=" + roundoff.format(totalLaborCosts) + " / subtotal_LaborCosts:"
					+ roundoff.format(subtotal_LaborCosts));
			Boolean LaborCosts = roundoff.format(totalLaborCosts).equals(roundoff.format(subtotal_LaborCosts));
			System.out.println("*** is totalLaborCosts vs subtotal_LaborCosts equal: " + LaborCosts);
			System.out.println("totalFullCosts=" + roundoff.format(totalFullCosts) + " / subtotal_FullCosts:"
					+ roundoff.format(subtotal_FullCosts));
			Boolean FullCosts = roundoff.format(totalFullCosts).equals(roundoff.format(subtotal_FullCosts));
			System.out.println("*** is totalFullCosts vs subtotal_FullCosts equal: " + FullCosts);
			System.out.println("totalTotalCost=" + roundoff.format(totalTotalCost) + " / subtotal_TotalCost:"
					+ subtotal_TotalCost);
			Boolean TotalCost = roundoff.format(totalTotalCost).equals(roundoff.format(subtotal_TotalCost));
			System.out.println("*** is totalTotalCost vs subtotal_TotalCost equal: " + TotalCost);
			System.out.println("totalTenderPrice=" + roundoff.format(totalTenderPrice) + " / subtotal_TenderPrice:"
					+ roundoff.format(subtotal_TenderPrice));
			Boolean TenderPrice = roundoff.format(totalTenderPrice).equals(roundoff.format(subtotal_TenderPrice));
			System.out.println("*** is totalTenderPrice vs subtotal_TenderPrice equal: " + TenderPrice);
			if (!TargetPrice || !Materialcosts || !MaterialcostSLCurrency || !Referencehours || !InstallationHours
					|| !LaborCosts || !FullCosts || !TotalCost || !TenderPrice) {
				screenshotCapture("DetailBreakdownTab");
				isDetailBreakdowndata = false;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Check TotalValues in Detail Breakdown Tab (FullGrid) Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click ToConfiguration icon and select Project from
	 * projecttree and select SalesOffice and click on Pricing Icon
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void gotoConfigurationPageandChangeTheSalesOffice() throws Exception {
		try {
			salesoffice = changeSalesOffice;
			supervisor_ResponsiblePerson = supervisor_ResponsiblePersontoChange;
			regionalDiscount = regionalDiscounttoChange;
			if (isMultipleEquipment) {
				regionalDiscount_MultipleEqup = regionalDiscounttoChange_MultipleEqup;
			}
			gotoConfigurationPage();
			selectProjectTree();
			checkSalesOfficeisSelected();
			pricingIconClick();
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Goto Configuration Page and Change SalesOffice Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click ToConfiguration icon and select Project from
	 * projecttree and select Primary SalesOffice and click on Pricing Icon
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void gotoConfigurationPageandChangeThePrimarySalesOffice(String frontline, String EXCELPATH)
			throws Exception {
		try {
			readTestData(frontline, EXCELPATH);
			gotoConfigurationPage();
			selectProjectTree();
			checkSalesOfficeisSelected();
			pricingIconClick();
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Goto Configuration Page and Change SalesOffice Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click ToConfiguration icon
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void gotoConfigurationPage() throws Exception {
		try {
			WebElement element_toConfiguration = gettingWebElement(lnk_toConfiguration);
			scrollIntoView_Javascript(element_toConfiguration);
			wait.until(ExpectedConditions.elementToBeClickable(element_toConfiguration));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			int count = 0;
			List<WebElement> element_toConfigurations = gettingWebElementsfromList(
					By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div/img"));
			wait.until(ExpectedConditions.visibilityOfAllElements(element_toConfigurations));
			for (WebElement elementtoConfiguration : element_toConfigurations) {
//			System.out.println(elementToWord.getAttribute("id"));
				count++;
				if (count == 1) {
					scrollIntoView_Javascript(elementtoConfiguration);
					wait.until(ExpectedConditions.visibilityOf(elementtoConfiguration));
					elementtoConfiguration.click();
					break;
				}
			}
			System.out.println("ToConfiguration icon clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Goto Configuration Page and Change SalesOffice Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click ToWord icon and click on ModularTenderDOC.doc
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void goToDocumentsTabandClickTheTender() throws Exception {
		try {
			scrollIntoView_Javascript(gettingWebElement(lnk_toWord));
			waitForElementToBeClickable(lnk_toWord);
			WebElement element_ToWord = gettingWebElement(lnk_toWord);
			scrollIntoView_Javascript(element_ToWord);
			wait.until(ExpectedConditions.elementToBeClickable(element_ToWord));
			int count = 0;
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			List<WebElement> element_ToWords = gettingWebElementsfromList(
					By.xpath("//*[@data-ctcwgtname='MainNavigationMenu']/div/img"));
			wait.until(ExpectedConditions.visibilityOfAllElements(element_ToWords));
			for (WebElement elementToWord : element_ToWords) {
//				System.out.println(elementToWord.getAttribute("id"));
				count++;
				if (count == 4) {
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
			if (frontlineAssigned.equals("FRANCE")) {
				By lnk_templateDOC_France = By.xpath("//*[contains(text(),'_Modular_Tender_Template.doc')]");
				gettingWebElement(lnk_templateDOC_France).click();
			} else if (frontlineAssigned.equals("CANADA")) {
				By lnk_templateDOC_Canada = By.xpath("//*[contains(text(),'CKQ TRB Tender Letter')]");
				gettingWebElement(lnk_templateDOC_Canada).click();
			}
			if (frontlineAssigned.equals("AUSTRALIA")) {
				By lnk_templateDOC_Australia = By.xpath("//*[text()='Tender Letter MOD TRB']");
				gettingWebElement(lnk_templateDOC_Australia).click();
			}
//			System.out.println("Modular_Tender_Template.doc clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Goto Documents tab and Click Tender Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click printOut icon in toword screen
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void verifySuccessfulMessageDisplayed() throws Exception {
		try {
			By btn_freezePrint = By.xpath("//button[text()='" + FreezePrintedVersion + "']");
			WebElement element_PrintwithKONELogo = gettingWebElement(label_KONELogo);
			scrollIntoView_Javascript(element_PrintwithKONELogo);
			wait.until(ExpectedConditions.visibilityOf(element_PrintwithKONELogo));
			WebElement element_PrintOut = gettingWebElement(icon_printOut); // *[@data-ctcwgtname='PrintOut_c']/div
																			// //*[@data-ctcname='PrintOut_I']/div
			wait.until(ExpectedConditions.visibilityOf(element_PrintOut));
			wait.until(ExpectedConditions.stalenessOf(element_PrintOut));
			retryingClick(icon_printOut);
			System.out.println("Create PrintOut icon clicked");
			if (frontlineAssigned.equals("FRANCE")) {
				waitForVisibilityOfElementLocated(text_freezePrint);
				waitForElementToBeClickable(btn_freezePrint);
				click_Javascript(gettingWebElement(btn_freezePrint));
				System.out.println(FreezePrintedVersion + " clicked in Do you want to freeze printed version?");
			}
			waitForVisibilityOfElementLocated(text_infotoDocServer);
			WebElement element_OkbuttoninInformationtodocumentserver = gettingWebElement(btn_infotoDocServer);
			wait.until(ExpectedConditions.elementToBeClickable(element_OkbuttoninInformationtodocumentserver));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_OkbuttoninInformationtodocumentserver.click();
			wait.until(ExpectedConditions.invisibilityOf(element_OkbuttoninInformationtodocumentserver));
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Verify Successful Message Displayed Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click ClickSave and CloseButton in KTOC-TRB
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void clickSaveandCloseButton() throws Exception {
		try {
			By btn_saveandClose = By.xpath("//*[@data-ctcwgtname='pb" + SaveandClose + "']");
			By value_stage = By.xpath("//div[text()='" + StageProbability_Stage + "']");
			WebElement element_SaveandClose = gettingWebElement(icon_saveandClose);
			scrollIntoView_Javascript(element_SaveandClose);
			waitForVisibilityOfElementLocated(icon_saveandClose);
			waitForElementToBeClickable(icon_saveandClose);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			/*
			 * WebElement activeElement_StageProbability=driver.switchTo().activeElement();
			 * if(activeElement_StageProbability.getAttribute("data-ctcwgtname")==null) {
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 */
			clickonButton(icon_saveandClose);
			System.out.println("SaveandClose icon CLICKED");
			waitForVisibilityOfElementLocated(btn_saveandClose);
			click_Javascript(gettingWebElement(btn_saveandClose));
			System.out.println(SaveandClose + " CLICKED in SaveandClose");
			waitForVisibilityOfElementLocated(txt_stageProbabilityDescription);
			enteringValues(txt_stageProbabilityDescription, StageProbability_Description);
			System.out.println(
					StageProbability_Description + " entered in Description field in stage / Probability window");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(dd_stage);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(value_stage);
			WebElement element_enderVersionProbability = gettingWebElement(txt_probability);
			element_enderVersionProbability.clear();
			element_enderVersionProbability.sendKeys(StageProbability_probability);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(btn_stageProbability);

			/*
			 * waitForinvisibilityOfElementLocated(btn_stageProbability); } else
			 * if(activeElement_StageProbability.getAttribute("data-ctcwgtname").equals(
			 * "close")) { //for SaveandClose="No";
			 * enteringValues(txt_stageProbabilityDescription,
			 * StageProbability_Description); System.out.println(
			 * StageProbability_Description+" entered in Description field in stage / Probability window"
			 * ); waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * clickonButton(dd_stage);
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * clickonButton(value_stage); WebElement element_enderVersionProbability =
			 * gettingWebElement(txt_probability); element_enderVersionProbability.clear();
			 * element_enderVersionProbability.sendKeys(StageProbability_probability);
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * clickonButton(btn_stageProbability);
			 */
			if (isMultipleEquipment || frontlineAssigned.equals("CANADA")) { // || frontlineAssigned.equals("FRANCE")
				WebElement activeElement_SFupdateFailed = driver.switchTo().activeElement();
				if (activeElement_SFupdateFailed.getText().contains("OK")) {
					By btn_updateFailedOK = By.xpath("//*[text()='OK']");
					waitForinvisibilityOfElementLocated(elementtoInvisible);
					clickonButton(btn_updateFailedOK);
					System.out.println("OK clicked in SalesForce.com update failed! Pop-up");
				}
				By icon_Close = By.xpath("//*[@data-ctcwgtname='ToolBar' and @data-ctctype='Toolbar']/div[1]");
				waitForVisibilityOfElementLocated(icon_Close);
				waitForinvisibilityOfElementLocated(elementtoInvisible);
				clickonButton(icon_Close);
				System.out.println("Close icon CLICKED");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Click Save&Close button Failed");
		}
	}

	/**
	 ** Reuse method, it will click Configurator button and navigate back to frames
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void compareSalesPricebetweenTenderPageandSalesforce() throws Exception {
		try {
			wait.until(elementToBeClickableInFrame(frameforwait, btn_configurator));
			switchtoDefaultContentFrame();
			switchtoFrame(gettingWebElement(frameforwait));
			WebElement element_Configurator = gettingWebElement(btn_configurator);
			wait.until(ExpectedConditions.visibilityOf(element_Configurator));
//			System.out.println("Configurator button is visible");
			scrollIntoView_Javascript(element_Configurator);
			By header_totalSalesPrice = By.xpath("//*[text()='Total Sales Price']/../../../../tbody/tr/td"); // *[text()='Total
																												// Sales
																												// Price']/../../../..//tr/td/*
			List<WebElement> element_totalSalesPrices = gettingWebElementsfromList(header_totalSalesPrice);

			String totalsalesprice = element_totalSalesPrices.get(12).getText();
//			System.out.println("Final_SalesPrice="+totalsalesprice);
			String b[] = totalsalesprice.split(" ");
			totalsalesprice = b[1].trim();
			totalsalesprice = totalsalesprice.replaceAll(",", "");
			Final_SalesPrice = Float.valueOf(totalsalesprice);
//			wait.until(ExpectedConditions.visibilityOfAllElements(element_totalSalesPrices));
			/*
			 * for(WebElement element_totalSalesPrice:element_totalSalesPrices) {
			 * System.out.println(element_totalSalesPrice.getAttribute("id"));
			 * if(!element_totalSalesPrice.getText().isEmpty()) {
			 * System.out.println(element_totalSalesPrice.getText()+"/"+
			 * element_totalSalesPrice.getAttribute("id"));
			 * if(element_totalSalesPrice.getText().contains("(")){ String
			 * totalsalesprice=element_totalSalesPrice.getText();
			 * System.out.println("Salesforce TenderPrice:"+totalsalesprice); String a[] =
			 * totalsalesprice.split("\\("); totalsalesprice=a[0].trim(); String b[] =
			 * totalsalesprice.split(" "); totalsalesprice=b[1].trim();
			 * totalsalesprice=totalsalesprice.replaceAll(",", "");
			 * Final_SalesPrice=Float.valueOf(totalsalesprice); break; } } }
			 */
			if (isMultipleEquipment) {
				addAllTenderPrice();
				System.out.println("allTender Price:" + allTenderPrice + " / Final_SalesPrice:" + Final_SalesPrice);
				System.out.println("is TRB allTenderPrice VS Salesforce TenderPrice equal:"
						+ allTenderPrice.equals(Final_SalesPrice) + " ***");
				if (!allTenderPrice.equals(Final_SalesPrice)) {
					screenshotCapture(
							"TenderPrice not equal in compareSalesPricebetweenTenderPageandSalesforce for MultipleEquipment");
					Assert.fail("Failed due to TenderPrice not equal in Get SalesPrice from SalesForce");
				}
			} else {
				System.out.println("Tender Price:" + TenderPrice + " / Final_SalesPrice:" + Final_SalesPrice);
				System.out.println("is TRB TenderPrice VS Salesforce TenderPrice equal:"
						+ TenderPrice.equals(Final_SalesPrice) + " ***");
				if (!TenderPrice.equals(Final_SalesPrice)) {
					screenshotCapture(
							"TenderPrice not equal in compareSalesPricebetweenTenderPageandSalesforce for SingleEquipment");
					Assert.fail("Failed due to TenderPrice not equal in Get SalesPrice from SalesForce");
				}
			}
			click_Javascript(element_Configurator);
			System.out.println("Configurator Clicked");
			WebElement secondFrame = null;
			List<WebElement> frames = gettingWebElementsfromList(frameforwait);
			wait.until(ExpectedConditions.visibilityOfAllElements(frames));
			for (WebElement frame : frames) {
				if (frame.getAttribute("id").equals("clientTarget"))
					;
				{
					secondFrame = frame;
//		System.out.println("secondFrame:"+secondFrame.getAttribute("id")+"/framesize:"+frames.size());
					break;
				}
			}
			switchtoFrame(secondFrame);
//			System.out.println("Switched to secondFrame");
			if (!frontlineAssigned.equals("CANADA")) {
				WebElement element_NoButtoninNewVersionProduct = gettingWebElement(btn_ButtoninNewVersionProduct);
				wait.until(ExpectedConditions.elementToBeClickable(element_NoButtoninNewVersionProduct));
				element_NoButtoninNewVersionProduct.click();
				System.out.println(NewVersionProduct + " clicked in New Version is available for this product");
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Get SalesPrice from SalesForce Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click HandShakeIcon
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void handShake() throws InterruptedException {
		try {
			waitForVisibilityOfElementLocated(icon_pricing);
//			System.out.println("pricingicon's visible");
			verifyTenderConsistency();
			WebElement element_HandShakeicon = gettingWebElement(icon_HandShake);
			wait.until(ExpectedConditions.visibilityOf(element_HandShakeicon));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			element_HandShakeicon.click();
			/*
			 * List<WebElement> element_HandShakeicons =
			 * gettingWebElementsfromList(By.xpath(
			 * "//*[@data-ctcwgtname='MainNavigationMenu']/div/img")); int count=0;
			 * for(WebElement elementHandShakeicon:element_HandShakeicons) { //
			 * System.out.println(elementHandShakeicon.getAttribute("id")); count++;
			 * if(count==5) { elementHandShakeicon.click(); break; } }
			 */
			System.out.println(
					"isHandShakeiconisEnabled:" + element_HandShakeicon.isEnabled() + " HandShakeicon CLICKED");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("HandShake Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will change data for selecting multiple equipment and
	 * navigate to Configuration Page
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void addMultipleEquipments() throws Exception {
		try {
			equipmentid = equipmentid_2;
			allTenderPrice = TenderPrice;
			template = template_2;
			TenderPrice = null;
			isMultipleEquipment = true;
			equipmentinService = equipmentinService_Escalator;
			gotoConfigurationPage();
			addGroups();
			addEquipmentinGroup();
			/*
			 * equipment_ADDorChange = "Add"; By tree_equipment = By.xpath("//*[text()='" +
			 * equipmentid + "']"); waitForVisibilityOfElementLocated(tree_equipment);
			 * WebElement Elementtoscroll = gettingWebElement(tree_equipment);
			 * scrollIntoView_Javascript(Elementtoscroll);
			 * System.out.println("scroll UP to Equipment in Project tree");
			 * waitForinvisibilityOfElementLocated(elementtoInvisible);
			 * click_Javascript(gettingWebElement(tree_equipment));
			 * System.out.println("Equipment clicked in Project tree"); equipmentid =
			 * equipmentid_2; equipment_ADDorChange = "Add"; allTenderPrice = TenderPrice;
			 * template = template_2; TenderPrice = null; isMultipleEquipment = true;
			 */
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Add Multiple Equipments Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will add group
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void addGroups() throws Exception {
		try {
			By tree_Building1 = By.xpath("//div/div[text()='Building 1']");
			By radio_EscalatorGroup = By.xpath("//*[@data-ctcname='EscalatorGroup_R']/div[1]");
			By txt_GroupName = By.xpath("//*[@data-ctcname='GroupName_T']");
			By lnk_Add = By.xpath("//*[text()='Add:']");
			By value_GroupName = By.xpath("//*[text()='" + groupName + "']");
			waitForVisibilityOfElementLocated(tree_Building1);
			WebElement scrollto_Building1 = gettingWebElement(tree_Building1);
			scrollIntoView_Javascript(scrollto_Building1);
			System.out.println("scroll UP to Building1 tree");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			Actions rightclick_Building1 = new Actions(driver).contextClick(gettingWebElement(tree_Building1));
			rightclick_Building1.build().perform();
			System.out.println("Rightclick performed on Building1");
			By lnk_New = By.xpath("//*[text()='New']");
			waitForVisibilityOfElementLocated(lnk_New);
			List<WebElement> elements_New = gettingWebElementsfromList(By.xpath("//*[text()='New']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(elements_New));
			for (WebElement element_New : elements_New) {
				if (element_New.getText().equals("New")) {
					element_New.click();
				}
			}
			waitForVisibilityOfElementLocated(header_AddNewGroup);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(radio_EscalatorGroup);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			enteringValues(txt_GroupName, groupName);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(lnk_Add);
			waitForVisibilityOfElementLocated(value_GroupName);
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Add Groups Failed due to: " + e);
		}
	}

	public void addEquipmentinGroup() throws Exception {
		try {
			By tree_GroupName = By.xpath("//*[text()='" + groupName + "']");
			By checkbox_Equipment = By.xpath("//*[text()='" + equipmentid + "']/..//img");
			waitForVisibilityOfElementLocated(tree_GroupName);
			WebElement scrollto_GroupName = gettingWebElement(tree_GroupName);
			scrollIntoView_Javascript(scrollto_GroupName);
			System.out.println("scroll UP to Group " + groupName + " tree");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			Actions rightclick_GroupName = new Actions(driver).contextClick(gettingWebElement(tree_GroupName));
			rightclick_GroupName.build().perform();
			System.out.println("Rightclick performed on " + groupName + " GroupName");
			By lnk_releaseAll = By.xpath("//*[text()='Release all']");
			waitForVisibilityOfElementLocated(lnk_releaseAll);
			List<WebElement> elements_New = gettingWebElementsfromList(By.xpath("//*[text()='New']"));
			wait.until(ExpectedConditions.visibilityOfAllElements(elements_New));
			for (WebElement element_New : elements_New) {
				if (element_New.getText().equals("New")) {
					element_New.click();
				}
			}
			searchEquipmentinAddNewEquipmentLookup();
			waitForVisibilityOfElementLocated(checkbox_Equipment);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(checkbox_Equipment);
			System.out.println("checkbox clicked");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			addEquipment();
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Add Equipment in Group Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will add multiple equipment's tenderPrice
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public void addAllTenderPrice() throws Exception {
		try {
			allTenderPrice = allTenderPrice + TenderPrice;
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Add All TenderPrice Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will click PriceIcon
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	// ***reuse pricingIconClick method is the prerequisite for Pricing screen***
	public void pricingIconClick() throws Exception {
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			waitForElementToBeClickable(icon_pricing);
			WebElement element_pricingicon = gettingWebElement(icon_pricing);
			scrollIntoView_Javascript(element_pricingicon);
//			System.out.println("scrolled to pricingicon");
			element_pricingicon.click();
			/*
			 * List<WebElement> element_pricingicons = gettingWebElementsfromList(By.xpath(
			 * "//*[@data-ctcwgtname='MainNavigationMenu']/div/img")); int count=0;
			 * for(WebElement elementpricingicon:element_pricingicons) { //
			 * System.out.println(elementpricingicon.getAttribute("id")); count++;
			 * if(count==2) { elementpricingicon.click(); break; } }
			 */
			System.out.println("pricing icon Clicked");
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("PricingIcon Click Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will select FirstMaintenance dropdown value in PriceOverview
	 * tab
	 * 
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public String firstMaintenance;

	public void selectingFirstMaintenance(String FirstMaintenance) throws Exception {
		try {
			firstMaintenance = FirstMaintenance;
			By value_firstMaintenance = null;
			if (frontlineAssigned.equals("FRANCE") || frontlineAssigned.equals("CANADA")) {
				value_firstMaintenance = By
						.xpath("//div[starts-with(text(),'" + FirstMaintenance + " Mois de gratuité ')]");
			} else if (frontlineAssigned.equals("AUSTRALIA")) {
				if (!FirstMaintenance.equals("0")) {
					value_firstMaintenance = By.xpath("//div[text()='12 Months Free Maintenance']");
					firstMaintenance = "12";
				} else {
					value_firstMaintenance = By.xpath("//div[text()='No Free Maintenance']");
					firstMaintenance = "0";
				}
			}
			waitForinvisibilityOfElementLocated(elementtoInvisible);
//			String tenderPriceBefore =isTargetupdatedafterFirstMaintenance();
			WebElement scrollto_Firstmaintenance = gettingWebElement(dd_firstMaintenance); // *[@data-ctcwgtname='FreeMaintenance']/buttonS
			scrollIntoView_Javascript(scrollto_Firstmaintenance);
//			System.out.println("scrolled to Firstmaintenance");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			click_Javascript(gettingWebElement(dd_firstMaintenance));
//			System.out.println("First maintenance: Clicked");
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(value_firstMaintenance);
			System.out.println("First maintenance: " + FirstMaintenance + " Clicked");
			if (!FirstMaintenance.equals("0") && !frontlineAssigned.equals("CANADA")) {
				waitForVisibilityOfElementLocated(header_firstMaintenance);
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(grid_allValues));
			} else if (FirstMaintenance.equals("0")) { // frontlineAssigned.equals("AUSTRALIA") &&
				List<WebElement> headers_priceOverview = gettingWebElementsfromList(
						By.xpath("//*[text()='Discount']/../..//div/div"));
				wait.until(ExpectedConditions.visibilityOfAllElements(headers_priceOverview));
				for (WebElement header_priceOverview : headers_priceOverview) {
					if (!header_priceOverview.getText().isEmpty()) {
//						System.out.println("header_priceOverview="+header_priceOverview.getText());
						if (header_priceOverview.getText().equals("First Maintenance")) {
							wait.until(ExpectedConditions.invisibilityOf(header_priceOverview));
							break;
						}
					}
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Selecting FirstMaintenance Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will get Discount value from the table in PriceOverview tab
	 * 
	 * @return: Discount value will be returned
	 * @throws Exception: For exception handling
	 * @author CON_SVIJAY02
	 */
	public String isTargetupdatedafterFirstMaintenance() throws Exception {
		String element_readTenderPrice = null;
		List<WebElement> Elements_PriceOverview = gettingWebElementsfromList(grid_allValues);
		wait.until(ExpectedConditions.visibilityOfAllElements(Elements_PriceOverview));
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
	 ** Reuse method, it will change Discount in PriceOverview tab
	 * 
	 * @author CON_SVIJAY02
	 */
	public String discountformaximumDiscountLimitExceeded;
	public void selectingDiscount(String discount) throws Exception {
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement scrollto_TMP_Discount = gettingWebElement(btn_discountPencil);
			wait.until(ExpectedConditions.visibilityOf(scrollto_TMP_Discount)); // *[@data-ctcwgtname='icGraphic']
			scrollIntoView_Javascript(scrollto_TMP_Discount);
			wait.until(ExpectedConditions.visibilityOf(scrollto_TMP_Discount));
			WebElement doubleclick_elementDiscount = gettingWebElement(grid_discount);
			Actions doubleclick_discount = new Actions(driver).doubleClick(doubleclick_elementDiscount);
			doubleclick_discount.build().perform();
			WebElement scrollto_NewDicount = gettingWebElement(txt_discount); // *[@data-ctcwgtname='TMP_Discount']
			wait.until(ExpectedConditions.visibilityOf(scrollto_NewDicount));
			scrollto_NewDicount.clear();
			scrollto_NewDicount.sendKeys(discount);
			System.out.println("Discount entered as: " + discount);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(btn_discountOK); // *[@data-ctcwgtname='pbOK']
			discountformaximumDiscountLimitExceeded = discount;
			if(maximumDiscountLimitExceeded) {
				By grid_discountisApplied = By.xpath("//*[text()='Project']/..//div[text()='" + discount + ".00 %']");
	//			System.out.println("grid_discountisApplied:"+gettingWebElement(grid_discountisApplied).getAttribute("id"));
				waitForpresenceOfElementLocated(grid_discountisApplied);
	//			System.out.println("Discount applied to GRID");	
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Selecting Discount Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will change TenderPrice in PriceOverview tab
	 * 
	 * @author CON_SVIJAY02
	 */
	public void selectingTenderPrice(String tenderPrice) throws Exception {
		try {
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			WebElement scrollto_priceOverview = gettingWebElement(tab_priceOverview);
			wait.until(ExpectedConditions.visibilityOf(scrollto_priceOverview));
			scrollIntoView_Javascript(scrollto_priceOverview);
			wait.until(ExpectedConditions.visibilityOf(scrollto_priceOverview));
			WebElement doubleclick_elementselectingTenderPrice = null;
			List<WebElement> elements = driver.findElements(grid_tenderPrice);
			wait.until(ExpectedConditions.visibilityOfAllElements(elements));
			for (WebElement element : elements) {
//		System.out.println(element.getText());
				if (element.getText().contains("€ ") || element.getText().contains("$")) {
					doubleclick_elementselectingTenderPrice = element;
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
			WebElement scrollto_TenderPrice_T = gettingWebElement(txt_tenderPrice); // *[@data-ctcwgtname='TMP_Discount']
			wait.until(ExpectedConditions.visibilityOf(scrollto_TenderPrice_T));
			scrollto_TenderPrice_T.sendKeys(Keys.chord((Keys.CONTROL + "a")));
			scrollto_TenderPrice_T.sendKeys((Keys.DELETE));
			scrollto_TenderPrice_T.sendKeys(tenderPrice);
			System.out.println("TenderPrice entered as: " + tenderPrice);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(btn_tenderPriceOK);
			istenderPrice = true;
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Selecting TenderPrice Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will get first row value and check TenderPrice & Discount in
	 * PriceOverview tab
	 * @author CON_SVIJAY02
	 */
	public void checkingTargetPrice() throws Exception {
		try {
			Float arrary[] = new Float[2];
			Float TargetPrice, Firstmaintenance = null, Discount = null;
			waitForVisibilityOfElementLocated(grid_discount);
			waitForVisibilityOfElementLocated(By.xpath("//*[@data-ctcwgtname='FreeMaintenancePeriod' and @value='" + firstMaintenance + "']"));
			if (!firstMaintenance.equals("0") && gettingWebElement(By.xpath("//*[@data-ctcwgtname='FreeMaintenanceBase']")).getAttribute("value").equals("0")) {
				enteringFirstMaintenanceValue();
			}
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(grid_allValues));
			List<WebElement> Elements_PriceOverview = gettingWebElementsfromList(grid_allValues);
			for (WebElement Element : Elements_PriceOverview) {
				wait.until(ExpectedConditions.visibilityOf(Element));
//		System.out.println(Element.getAttribute("id")+"/"+Element.getText());
				String getAttribute_Element = Element.getAttribute("value");
				String getText_Element = Element.getText();
				if (!getText_Element.isEmpty() || getAttribute_Element != null) {
					if (getAttribute_Element == null) {
						if (!getText_Element.contains("Project")) {
							if (!getText_Element.contains("%")) {
								String element_readTenderPrice = getText_Element.replaceAll("[€  $]", "");
								if (frontlineAssigned.equals("FRANCE")) { // || frontlineAssigned.equals("CANADA")
									element_readTenderPrice = element_readTenderPrice.replace(".", "");
									element_readTenderPrice = element_readTenderPrice.replace(",", ".");
								} else if (frontlineAssigned.equals("AUSTRALIA") || frontlineAssigned.equals("CANADA")) {
									element_readTenderPrice = element_readTenderPrice.replace(",", "");
								}
								TenderPrice = Float.valueOf(element_readTenderPrice);
//				System.out.println("****TenderPrice==>" + TenderPrice);
							} else {
								String element_readDiscount = getText_Element.replaceAll("[% ]", "");
								Discount = Float.valueOf(element_readDiscount);
//				System.out.println("****Discount:" + Discount);
							}
						}
					} else {
//	        	System.out.println("****Attribute==>"+Element.getAttribute("value")+"/id==>"+Element.getAttribute("id"));
						String element_read = getAttribute_Element.replaceAll("[€  $]", "");
						if (frontlineAssigned.equals("FRANCE")) { // || frontlineAssigned.equals("CANADA")
							element_read = element_read.replace(".", "");
							element_read = element_read.replace(",", ".");
						} else if (frontlineAssigned.equals("AUSTRALIA") || frontlineAssigned.equals("CANADA")) {
							element_read = element_read.replace(",", "");
						}
						Float converted = Float.valueOf(element_read);
//	        	System.out.println("converted: "+converted);
						if (arrary[0] == null) {
							arrary[0] = converted;
							/*
							 * System.out.println("ARRAY 1"); System.out.println(arrary[0] = converted);
							 */
						} else {
							arrary[1] = converted;
							/*
							 * System.out.println("ARRAY 2"); System.out.println(arrary[1] = converted);
							 */
						}
					}
				}
			}
			if (firstMaintenance.equals("0")) { // if(withoutFirstMaintenance.equals("0")) {
				if (arrary[1] == null) {
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
//System.out.println("CONDTION_2 TargetPrice: "+TargetPrice+", Firstmaintenance:"+Firstmaintenance);
			}
//System.out.println("istenderPrice=>"+istenderPrice);			
			if (istenderPrice) {
				Float DiscountFinal = ((((TargetPrice - Firstmaintenance) - (TenderPrice - Firstmaintenance))
						/ (TargetPrice - Firstmaintenance)) * 100);
				System.out.println("TargetPrice:" + TargetPrice);
				System.out.println("Firstmaintenance:" + Firstmaintenance);
				System.out.println("TenderPrice:" + TenderPrice);
				System.out.println("DiscountFinal:" + roundoff.format(DiscountFinal)
						+ " / Discount shown in Application:" + Discount);
//				System.out.println("*** is FinalDiscount VS ApplicationDiscount Equal:"+roundoff.format(DiscountFinal).equals(roundoff.format(Discount))+" ***");
				String condition = null;
				if (!roundoff.format(DiscountFinal).equals(roundoff.format(Discount))) {
					Float difference = DiscountFinal - Discount;
					/*
					 * System.out.println(roundoff.format(difference));
					 * 
					 * String[] diff = String.valueOf(difference).split("\\.", 2);//.split("\\.");
					 * System.out.println(roundoff.format(difference %
					 * 1)+"====="+diff[0]+"/"+diff[1]); if(diff[0].contains("-")) {
					 * if(difference<=0.02f) {
					 * 
					 * }
					 * 
					 * } else {
					 * 
					 * }
					 */

					if (roundoff.format(difference).equals(roundoff.format(-.00f))
							|| roundoff.format(difference).equals(roundoff.format(-.01f))) {
						DiscountFinal = DiscountFinal + 0.01f;
						condition = "+0.01";
//					System.out.println(roundoff.format(DiscountFinal));
					} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
						DiscountFinal = DiscountFinal + 0.02f;
						condition = "+0.02";
//					System.out.println(roundoff.format(DiscountFinal));
					} else {
						DiscountFinal = DiscountFinal - 0.01f;
						if (roundoff.format(DiscountFinal).equals(roundoff.format(Discount))) {
							condition = "-0.01";
						} else {
							DiscountFinal = DiscountFinal - 0.01f;
							condition = "-0.02";
						}
					}
				}
				System.out.println("*** " + condition
						+ " Added in CalculatedDiscountFinal hence DiscountFinal VS Discount shown in Application is: "
						+ roundoff.format(DiscountFinal).equals(roundoff.format(Discount)) + " ***");
				if (!roundoff.format(DiscountFinal).equals(roundoff.format(Discount))) {
					screenshotCapture("Discount not equal in VerifyDiscountByChangingTheTenderPrice");
					Assert.fail("Failed due to Discount not equal in VerifyDiscountByChangingTheTenderPrice: ");
				}
				istenderPrice = false;
			} else {
				/*
				 * Float TargetMinusMaintenance = TargetPrice - Firstmaintenance; Float
				 * TargetplusDiscount = TargetMinusMaintenance - (TargetMinusMaintenance *
				 * (Discount / 100)); Float TargetPriceFinal = TargetplusDiscount +
				 * Firstmaintenance;
				 */
				Float TenderPriceFinal = ((TargetPrice - Firstmaintenance) - ((TargetPrice - Firstmaintenance) * (Discount / 100)) + Firstmaintenance);
				System.out.println("TargetPrice:" + TargetPrice);
				System.out.println("Firstmaintenance:" + Firstmaintenance);
				System.out.println("Discount:" + Discount);
				System.out.println("*** TenderPriceFinal:" + roundoff.format(TenderPriceFinal)
						+ " / CurrentTenderPrice:" + TenderPrice + " ***");
//				System.out.println("is TenderPriceFinal VS ApplicationTenderPrice Equal: " + roundoff.format(TenderPriceFinal).equals(roundoff.format(TenderPrice))+" ***");
				String condition = null;
				if (!roundoff.format(TenderPriceFinal).equals(roundoff.format(TenderPrice))) { // if(!roundoff.format(TenderPriceFinal).equals(roundoff.format(TenderPrice)))
																								// {
					Float difference = TenderPriceFinal - TenderPrice;

					/*
					 * String[] diff = String.valueOf(difference).split("\\.", 2);//.split("\\.");
					 * System.out.println(roundoff.format(difference %
					 * 1)+"====="+diff[0]+"/"+diff[1]); if(diff[0].contains("-")) { Float
					 * a=Float.valueOf(diff[1]); System.out.println(roundoff.format(a));
					 * if(difference<=0.02f) { TenderPriceFinal=TenderPriceFinal-difference;
					 * System.out.println(TenderPriceFinal); }
					 * 
					 * } else { if(difference<=0.02f) {
					 * TenderPriceFinal=TenderPriceFinal-difference;
					 * System.out.println(TenderPriceFinal); } }
					 */
//				System.out.println(roundoff.format(difference)+"=="+difference+"=="+TenderPriceFinal);
					if (roundoff.format(difference).equals(roundoff.format(-.00f)) || roundoff.format(difference).equals(roundoff.format(-.01f))) {
						TenderPriceFinal = TenderPriceFinal + 0.01f;
						condition = "+0.01";
//				System.out.println(roundoff.format(TenderPriceFinal));
					} else if (roundoff.format(difference).equals(roundoff.format(-.02f))) {
						TenderPriceFinal = TenderPriceFinal + 0.02f;
						condition = "+0.02";
//				System.out.println(roundoff.format(TenderPriceFinal));
					} else {
						if (roundoff.format(difference).equals(roundoff.format(.00f))) {
							TenderPriceFinal = TenderPriceFinal - difference;
							condition = "-"+difference;
//				System.out.println("HI="+TenderPriceFinal);
						} else {
						TenderPriceFinal = TenderPriceFinal - 0.01f;
//				System.out.println(roundoff.format(TenderPriceFinal));
						if (roundoff.format(TenderPriceFinal).equals(roundoff.format(TenderPrice))) {
							condition = "-0.01";
						} else {
							TenderPriceFinal = TenderPriceFinal - 0.01f;
							condition = "-0.02";
						}
					}
					}
				}
				System.out.println("*** " + condition
						+ " Added in TenderPrice hence TenderPriceFinal VS TenderPrice shown in Application is: "
						+ roundoff.format(TenderPriceFinal).equals(roundoff.format(TenderPrice)) + " ***");
				if (!roundoff.format(TenderPriceFinal).equals(roundoff.format(TenderPrice))) {
					screenshotCapture("Discount not equal in CheckTenderPriceAfterDiscountUpdate");
					Assert.fail("Failed due to Discount not equal in CheckTenderPriceAfterDiscountUpdate Failed");
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Checking TargetPrice Failed due to: " + e);
		}
	}
	
	/**
	 ** Reuse method, it will click on pencile to edit FirstMaintenance and enter FirstMaintenance price if FirstMaintenance price is zero PriceOverview tab
	 * @author CON_SVIJAY02
	 */
	public int countforenteringFirstMaintenanceValue = 1;
	public void enteringFirstMaintenanceValue() throws Exception {
		try {
			By btn_FirstMaintenancePencil = By.xpath("//*[@data-ctcname='FirstMaintenance_Pencil_I']");
			By txt_editFirstMaintenancePrice  = By.xpath("//*[@data-ctcwgtname='TMP_FreeMaintenanceBase']");
//			By txt_editFirstMaintenanceDuration  = By.xpath("//*[@data-ctcwgtname='TMP_FreeMaintenancePeriod']");
			By btn_ok  = By.xpath("//*[@data-ctcwgtname='pbOK']");
			wait.until(ExpectedConditions.visibilityOf(gettingWebElement(btn_FirstMaintenancePencil)));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(btn_FirstMaintenancePencil);
			wait.until(ExpectedConditions.visibilityOf(gettingWebElement(txt_editFirstMaintenancePrice)));
			WebElement element_editFirstMaintenancePrice = gettingWebElement(txt_editFirstMaintenancePrice);
			waitForElementToBeClickable(txt_editFirstMaintenancePrice);
			waitForinvisibilityOfElementLocated(elementtoInvisible);
//			enteringValues(txt_editFirstMaintenancePrice, isFirstMaintenancetoEdit);
			element_editFirstMaintenancePrice.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			element_editFirstMaintenancePrice.sendKeys(isFirstMaintenancetoEdit);
			System.out.println("First maintenance 'Price per Equipment' entered: "+isFirstMaintenancetoEdit);
			wait.until(ExpectedConditions.visibilityOf(gettingWebElement(btn_ok)));
			waitForinvisibilityOfElementLocated(elementtoInvisible);
			clickonButton(btn_ok);
			waitForVisibilityOfElementLocated(By.xpath("//*[@data-ctcwgtname='FreeMaintenanceBase' and @value='" + isFirstMaintenancetoEdit + "']"));
			if(countforenteringFirstMaintenanceValue == 1) {
				isFirstMaintenancetoEdit = isFirstMaintenancetoEdittoChange1;
			} else if(countforenteringFirstMaintenanceValue == 2) {
				isFirstMaintenancetoEdit = isFirstMaintenancetoEdittoChange2;
			}
			countforenteringFirstMaintenanceValue++;
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Entering FirstMaintenance Value Failed due to: " + e);
		}
	}

	private Float getFloat(WebElement rowElement) {
		String value = "";
		if (rowElement.getTagName().equalsIgnoreCase("input")) {
			value = rowElement.getAttribute("value").trim();
		} else if (rowElement.getTagName().equalsIgnoreCase("div")) {
			value = rowElement.getText().trim();
		}
		if ("".equals(value) || null == value || value.isEmpty()) {
			return 0.0F;
		} else {
			return getFloatFromString(value);
		}
	}

	private Float getFloatFromString(String value) {
		String replaced = value;
		if (replaced.contains("�")) {
			replaced = replaced.replaceAll("[.]", "").replaceAll(",", "."); // hd it wk
		}
		replaced = replaced.replaceAll("[^0-9.]", "");
		if (replaced.isEmpty()) {
			return 0.0F;
		} else {
			return Float.parseFloat(replaced);
		}
	}

	public void checkingTargetPriceFullGrid1() throws Exception {
		try {
			waitForVisibilityOfElementLocated(grid_discount);
			HashMap<String, Map<String, Float>> hm_mappingRowData = new HashMap<String, Map<String, Float>>();
			List<String> ls_checkingTargetPriceFullGridHeader = new LinkedList<>();
			ls_checkingTargetPriceFullGridHeader.add("Target Price");
			ls_checkingTargetPriceFullGridHeader.add("Discount");
			ls_checkingTargetPriceFullGridHeader.add("Tender Price");
			ls_checkingTargetPriceFullGridHeader.add("First Maintenance");
			List<WebElement> Elements_Description = gettingWebElementsfromList(
					By.xpath("//div[@data-ctctype='ComponentTree']/div/div[div/div/text()='Project']/div"));// hd it wk
			wait.until(ExpectedConditions.visibilityOfAllElements(Elements_Description));
			for (WebElement Element_Description : Elements_Description) {
				if (Element_Description.getText().trim().equalsIgnoreCase("")) {
					continue;
				}
				Map<String, Float> rowData = new HashMap<String, Float>();
				List<WebElement> rowElement = Element_Description.findElements(By.xpath("./*"));
				rowElement.sort(new Comparator<WebElement>() {
					@Override
					public int compare(WebElement o1, WebElement o2) {
						String idStr1 = o1.getAttribute("id");
						String idStr2 = o2.getAttribute("id");
						Integer id1 = idStr1 == null ? 0
								: Integer.parseInt(idStr1.substring(0, idStr1.indexOf("_")).replaceAll("[^0-9]", ""));
						Integer id2 = idStr2 == null ? 0
								: Integer.parseInt(idStr2.substring(0, idStr2.indexOf("_")).replaceAll("[^0-9]", ""));
						return id1.compareTo(id2);
					}
				});
				String Desc = rowElement.get(0).getText();
				System.out.println(Desc);
				if (null == Desc || Desc.isEmpty())
					continue;
				int counter = 1;
				int rowCounter = 1;
				if (rowElement.get(rowElement.size() - counter).getTagName().equalsIgnoreCase("input")) {
					rowData.put(
							ls_checkingTargetPriceFullGridHeader
									.get(ls_checkingTargetPriceFullGridHeader.size() - counter),
							getFloat(rowElement.get(rowElement.size() - rowCounter)));
					rowCounter++;
				}
				counter++;
				rowData.put(
						ls_checkingTargetPriceFullGridHeader.get(ls_checkingTargetPriceFullGridHeader.size() - counter),
						getFloat(rowElement.get(rowElement.size() - rowCounter)));
				counter++;
				rowCounter++;
				rowData.put(
						ls_checkingTargetPriceFullGridHeader.get(ls_checkingTargetPriceFullGridHeader.size() - counter),
						getFloat(rowElement.get(rowElement.size() - rowCounter)));
				counter++;
				rowCounter++;
				rowData.put(
						ls_checkingTargetPriceFullGridHeader.get(ls_checkingTargetPriceFullGridHeader.size() - counter),
						getFloat(rowElement.get(rowElement.size() - rowCounter)));
				hm_mappingRowData.put(Desc, rowData);
			}
			for (String key : hm_mappingRowData.keySet()) {
				for (String title : hm_mappingRowData.get(key).keySet()) {
					System.out.println("Key:" + key + " ### title:" + title + " ###Value:"
							+ hm_mappingRowData.get(key).get(title));
				}
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Checking TargetPrice Failed due to: " + e);
		}
	}

	/**
	 ** Reuse method, it will get all row value and check TenderPrice & Discount in
	 * PriceOverview tab
	 * 
	 * @throws Exception
	 * @author CON_SVIJAY02
	 */
	public void checkingTargetPriceFullGrid() throws Exception {
		try {
			waitForVisibilityOfElementLocated(grid_discount);
			HashMap<String, Float> hm_checkingTargetPriceFullGridData = new HashMap<String, Float>();
			List<String> ls_rowHeader = new LinkedList<>();
			List<String> ls_checkingTargetPriceFullGridHeader = new LinkedList<>();
//		List<Float> ls_checkingTargetPriceFullGridValue=new LinkedList<>();
//			ls_checkingTargetPriceFullGridHeader.add("Description");
			ls_checkingTargetPriceFullGridHeader.add("Tender Price");
			ls_checkingTargetPriceFullGridHeader.add("Target Price");
			ls_checkingTargetPriceFullGridHeader.add("Discount");
			ls_checkingTargetPriceFullGridHeader.add("First Maintenance");
			List<Float> ls_checkingTargetPriceFullGridValue = null;
//			int DescriptiontoIterate=0;
			List<WebElement> Elements_Description = gettingWebElementsfromList(grid_allRowallValues);
			wait.until(ExpectedConditions.visibilityOfAllElements(Elements_Description));

			for (WebElement Element_Description : Elements_Description) {
				if (!Element_Description.getText().isEmpty() && !Element_Description.getText().contains("€")
						&& !Element_Description.getText().contains("$")
						&& !Element_Description.getText().contains("%")) {
//			System.out.println(Element_Description.getText());
					ls_rowHeader.add(Element_Description.getText());
//				DescriptiontoIterate++;
				}
			}
//		System.out.println("DescriptiontoIterate:"+DescriptiontoIterate);
			Float arrary[] = new Float[2];
			Float TargetPrice = null, Firstmaintenance = null, Discount = null;
			ls_checkingTargetPriceFullGridValue = new LinkedList<>();
			List<WebElement> Elements_PriceOverview = gettingWebElementsfromList(
					By.xpath("//*[text()='Project']/../../div"));
			int indexvalue = 0;
			for (WebElement Element : Elements_PriceOverview) {
//		System.out.println(Element.getAttribute("id"));
//				if(isSiblingPresent(Element, By.xpath("./*/following-sibling::*"))==false) continue;
				if (!Element.getAttribute("id").contains("ccf") && !Element.getAttribute("id").contains("srp")) {
					List<WebElement> Element1 = Element.findElements(By.xpath("./*"));
//				System.out.println("Element:"+Element1.size());
					if (Element1.size() > 0) {
						for (WebElement Element2 : Element1) {
//					System.out.println(Element.getAttribute("id")+"_/_"+Element2.getAttribute("id"));
							if (!Element2.getText().isEmpty() || Element2.getAttribute("value") != null) {
								if (Element2.getAttribute("value") == null) {
									if (!ls_rowHeader.contains(Element2.getText())) {
										if (!Element2.getText().contains("%")) {
											String element_readTenderPrice = Element2.getText().replaceAll("[€  $]",
													"");
//					System.out.println("element_readTenderPrice:"+Element2.getAttribute("id")+"/"+Element2.getText());
											if (frontlineAssigned.equals("FRANCE")) { //|| frontlineAssigned.equals("CANADA")
												element_readTenderPrice = element_readTenderPrice.replace(".", "");
												element_readTenderPrice = element_readTenderPrice.replace(",", ".");
											} else if (frontlineAssigned.equals("AUSTRALIA") || frontlineAssigned.equals("CANADA")) {
												element_readTenderPrice = element_readTenderPrice.replace(",", "");
											}
											TenderPrice = Float.valueOf(element_readTenderPrice);
											ls_checkingTargetPriceFullGridValue.add(TenderPrice);
											hm_checkingTargetPriceFullGridData.put(ls_rowHeader.get(indexvalue) + "_"
													+ ls_checkingTargetPriceFullGridHeader.get(0), TenderPrice);
//					System.out.println("****TenderPrice:" + TenderPrice);
										} else {
											String element_readDiscount = Element2.getText().replaceAll("[% ]", "");
											Discount = Float.valueOf(element_readDiscount);
											ls_checkingTargetPriceFullGridValue.add(Discount);
											hm_checkingTargetPriceFullGridData.put(ls_rowHeader.get(indexvalue) + "_"
													+ ls_checkingTargetPriceFullGridHeader.get(2), Discount);
//					System.out.println("****Discount:" + Discount);
										}
									}
								} else {
//				    System.out.println("****Attribute==>"+Element2.getAttribute("value")+"/id==>"+Element2.getAttribute("id"));
									String element_read = Element2.getAttribute("value").replaceAll("[€  $]", "");
									if (frontlineAssigned.equals("FRANCE")) { // || frontlineAssigned.equals("CANADA")
										element_read = element_read.replace(".", "");
										element_read = element_read.replace(",", ".");
									} else if (frontlineAssigned.equals("AUSTRALIA") || frontlineAssigned.equals("CANADA")) {
										element_read = element_read.replace(",", "");
									}
									Float converted = Float.valueOf(element_read);
//					System.out.println("converted: "+converted);
									if (arrary[0] == null) {
										arrary[0] = converted;
										/*
										 * System.out.println("ARRAY 1"); System.out.println(arrary[0] = converted);
										 */
									} else {
										arrary[1] = converted;
										/*
										 * System.out.println("ARRAY 2"); System.out.println(arrary[1] = converted);
										 */
									}
									if (firstMaintenance.equals("0")) { // if(withoutFirstMaintenance.equals("0")) {
										if (arrary[1] == null) {
											arrary[1] = 0f;
										}
									}
									if (arrary[0] > arrary[1]) {
										TargetPrice = arrary[0];
										Firstmaintenance = arrary[1];
										ls_checkingTargetPriceFullGridValue.add(TargetPrice);
										ls_checkingTargetPriceFullGridValue.add(Firstmaintenance);
										hm_checkingTargetPriceFullGridData.put(ls_rowHeader.get(indexvalue) + "_"
												+ ls_checkingTargetPriceFullGridHeader.get(1), TargetPrice);
										hm_checkingTargetPriceFullGridData.put(
												ls_rowHeader.get(indexvalue) + "_"
														+ ls_checkingTargetPriceFullGridHeader.get(3),
												Firstmaintenance);
//					System.out.println("CONDTION_1 TargetPrice: "+TargetPrice+", Firstmaintenance:"+Firstmaintenance);
									} else {
										TargetPrice = arrary[1];
										Firstmaintenance = arrary[0];
										hm_checkingTargetPriceFullGridData.put(ls_rowHeader.get(indexvalue) + "_"
												+ ls_checkingTargetPriceFullGridHeader.get(1), TargetPrice);
										hm_checkingTargetPriceFullGridData.put(
												ls_rowHeader.get(indexvalue) + "_"
														+ ls_checkingTargetPriceFullGridHeader.get(3),
												Firstmaintenance);
//					System.out.println("CONDTION_2 TargetPrice: "+TargetPrice+", Firstmaintenance:"+Firstmaintenance);
									}
									arrary[0] = null;
								}
							}
						}
					}
//	System.out.println("istenderPrice=>"+istenderPrice);			
					if (istenderPrice) {
						Float DiscountFinal = ((((TargetPrice - Firstmaintenance) - (TenderPrice - Firstmaintenance))
								/ (TargetPrice - Firstmaintenance)) * 100);
						System.out.println(ls_rowHeader.get(indexvalue) + ":- TargetPrice:" + TargetPrice);
						System.out.println(ls_rowHeader.get(indexvalue) + ":- Firstmaintenance:" + Firstmaintenance);
						System.out.println(ls_rowHeader.get(indexvalue) + ":- TenderPrice:" + TenderPrice);
						System.out.println(ls_rowHeader.get(indexvalue) + ":- DiscountFinal:"
								+ roundoff.format(DiscountFinal) + " / Current Discount:" + Discount);
						System.out.println("*** " + ls_rowHeader.get(indexvalue)
								+ ":- is FinalDiscount VS ApplicationDiscount Equal:"
								+ roundoff.format(DiscountFinal).equals(roundoff.format(Discount)) + " ***");
						if (!roundoff.format(DiscountFinal).equals(roundoff.format(Discount))) {
							screenshotCapture("Discount not equal in VerifyDiscountByChangingTheTenderPrice");
							Assert.fail("Checking Target Price in FullGrid Failed");
						}
						istenderPrice = false;
					} else {
						/*
						 * Float TargetMinusMaintenance = TargetPrice - Firstmaintenance; Float
						 * TargetplusDiscount = TargetMinusMaintenance - (TargetMinusMaintenance *
						 * (Discount / 100)); Float TargetPriceFinal = TargetplusDiscount +
						 * Firstmaintenance;
						 */
						Float TenderPriceFinal = ((TargetPrice - Firstmaintenance)
								- ((TargetPrice - Firstmaintenance) * (Discount / 100)) + Firstmaintenance);
						System.out.println(ls_rowHeader.get(indexvalue) + ":- TargetPrice:" + TargetPrice);
						System.out.println(ls_rowHeader.get(indexvalue) + ":- Firstmaintenance:" + Firstmaintenance);
						System.out.println(ls_rowHeader.get(indexvalue) + ":- Discount:" + Discount);
						System.out.println(ls_rowHeader.get(indexvalue) + ":- TenderPriceFinal:"
								+ roundoff.format(TenderPriceFinal) + " / CurrentTenderPrice:" + TenderPrice + " ***");
						System.out.println("*** " + ls_rowHeader.get(indexvalue)
								+ ":- is TenderPriceFinal VS ApplicationTenderPrice Equal: "
								+ roundoff.format(TenderPriceFinal).equals(roundoff.format(TenderPrice)) + " ***");
						if (!roundoff.format(TenderPriceFinal).equals(roundoff.format(TenderPrice))) {
							screenshotCapture("Discount not equal in CheckTenderPriceAfterDiscountUpdate");
							Assert.fail("Checking Target Price in FullGrid Failed");
						}
					}
				}
				indexvalue++;
			}
		} catch (Exception e) {
//			e.printStackTrace();
			Assert.fail("Checking Target Price in FullGrid Failed");
		}
	}
	
	public void maximumDiscountLimitExceedPopup() {
		try {
			By maximumDiscountorDiscountPencil = By.xpath("//*[contains(text(),'Maximum discount limit exceeded.') or text()='" + discountformaximumDiscountLimitExceeded + ".00 %']");
			waitForVisibilityOfElementLocated(maximumDiscountorDiscountPencil); //header_maximumDiscountLimitExceedPopup
//			System.out.println(gettingWebElement(maximumDiscountorDiscountPencil).getAttribute("id")+"_"+gettingWebElement(maximumDiscountorDiscountPencil).getText());
				if(gettingWebElement(maximumDiscountorDiscountPencil).getText().contains("Maximum discount limit exceeded.")) {
		//			waitForinvisibilityOfElementLocated(elementtoInvisible);
					clickonButton(btn_maximumDiscountLimitExceedPopup_YES);
					waitForVisibilityOfElementLocated(btn_maximumDiscountLimitExceed_SEND);
		//			System.out.println(gettingWebElement(message_maximumDiscountLimitExceedPopup_Body).getAttribute("value"));
					if(gettingWebElement(message_maximumDiscountLimitExceedPopup_Body).getAttribute("value").contains("Pricing Approval request for")) {
						String mailBodyContent=gettingWebElement(message_maximumDiscountLimitExceedPopup_Body).getAttribute("value").substring(gettingWebElement(message_maximumDiscountLimitExceedPopup_Body).getAttribute("value").indexOf("Account :"));
		//				System.out.println("mailBodyContent="+mailBodyContent);
						String a[] = mailBodyContent.split("Project pricing:");
		//				System.out.println("a0="+a[0]);
		//				System.out.println("a1="+a[1]);
						String b[] = a[1].split("Discount :");
						String projectPricingString = null;
						if (frontlineAssigned.equals("CANADA") || frontlineAssigned.equals("FRANCE")) {
							projectPricingString = b[0].replaceAll("[€ . $]", "");
							projectPricingString = projectPricingString.replaceAll(",", ".");
						} else {
							projectPricingString = b[0].replaceAll("[€ , $]", "");
							projectPricingString = projectPricingString.replaceAll(",", ".");
						}
						Float projectPricing = Float.valueOf(projectPricingString.trim());
						System.out.println("*** Project Pricing = "+projectPricing);
		//				System.out.println("b1"+b[1]);
						String c[] = b[1].split("Target price:");
						String discountString = c[0].replaceAll("[ %]", "");
						Float discount = Float.valueOf(discountString.trim());
						System.out.println("*** Discount = "+discount);
						String d[] = c[1].split("Main details of project:");
						String targetPriceString = null;
						if (frontlineAssigned.equals("CANADA") || frontlineAssigned.equals("FRANCE")) {
							targetPriceString = d[0].replaceAll("[€ .]", "");
							targetPriceString = targetPriceString.replaceAll(",", ".");
						} else {
							targetPriceString = d[0].replaceAll("[€ , $]", "");
							targetPriceString = targetPriceString.replaceAll(",", ".");
						}
						Float targetPrice = Float.valueOf(targetPriceString.trim());
						System.out.println("*** Target Price = "+targetPrice);
						String e[] = d[1].split("Billing Plan");
						String billingPlan = e[1].replaceAll("[ :]", "");
						billingPlan = billingPlan.trim();
						System.out.println("*** Billing Plan = "+billingPlan);
					}
		//			waitForinvisibilityOfElementLocated(elementtoInvisible);			
					scrollIntoView_Javascript(gettingWebElement(btn_maximumDiscountLimitExceed_SEND));
		//			waitForinvisibilityOfElementLocated(elementtoInvisible);
					clickonButton(btn_maximumDiscountLimitExceed_SEND);
					waitForVisibilityOfElementLocated(header_maximumDiscountLimitExceedConfirmation);
		//			waitForinvisibilityOfElementLocated(elementtoInvisible);
					clickonButton(btn_maximumDiscountLimitExceedConfirmation_Ok);
			} else {
				System.out.println("*** Maximum Discount LimitExceed Pop-up didn't appeared ***");
			}
			waitForElementToBeClickable(lnk_toWord);
		} catch (Exception e) {
//				e.printStackTrace();
				Assert.fail("Maximum Discount Limit Exceed Failed");
		}
	}

	public static String GetAdditionalDiscountGridTargetPriceBaseValues(String TreeValue) {
		int Linevalue = 0;
		switch (TreeValue) {
		case "Project":
			Linevalue = 1;
			break;
		case "Building":
			Linevalue = 2;
			break;
		case "Group":
			Linevalue = 3;
			break;
		case "EquipmentId":
			Linevalue = 4;
			break;
		case "Solution1":
			Linevalue = 5;
			break;
		case "Solution2":
			Linevalue = 6;
			break;
		case "EquipmentName":
			Linevalue = 7;
			break;
		}
		element = driver.findElement(
				By.xpath("//div[text()='Project']/parent::div/parent::div/div[" + Linevalue + "]/input[1]"));
		return element.getAttribute("value");
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
	public static int TestStatus = 1;
	public static String MandatoryField = null;
	public static String KTOCSalesPrice;
	public static String SalesForceSalesPrice;
	public static WebElement element;

	// Logon to Salesforce
	public static void LogonToSalesforce() throws Exception {
		TestStatus = 1;
		if (TestStatus == 1) {
			try {
				// call driver
				driver.manage().window().maximize();
				driver.get(LogOnPage.SalesForceURL);
				driver.manage().timeouts().implicitlyWait(TIME, TimeUnit.SECONDS);

				// Assign username and Password

				/*
				 * String UserName = Xls_Reader.getData("LoginData", "LogOnToKTOC", "UserName");
				 * String Password = Xls_Reader.getData("LoginData", "LogOnToKTOC", "Password");
				 */
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
				TestStatus = 0;
				TakeSnapShot("SalesForceLoginPage");
				Assert.fail("Login Interrupted " + e);
			}
		} else {
			Assert.fail("Test Skipped: Browser Launch Failed");
		}
	}

	// Change User Settings with SalesOrg
	public void ChangeSalesOrg() {
		TestStatus = 1;
		if (TestStatus == 1) {
			try {
				// Change Sales Organization From SF User Settings Page
				ClickOnElement("xpath", SalesForceData.LoggedInUserName);
				ClickOnElement("xpath", SalesForceData.MySettings);
				ClickOnElement("xpath", SalesForceData.PersonalInfo);
				ClickOnElement("xpath", SalesForceData.AdvancedUserDetails);
				ClickOnElement("xpath", SalesForceData.UserEditButton);
				ClickOnElement("xpath", SalesForceData.UserEditButton);
				ScrollUptoElement("xpath", SalesForceData.AdditionalInformationSection);
				SelectDropDownValues("xpath", SalesForceData.SalesOrgField, "220");

			} catch (Exception e) {
				TestStatus = 0;
				try {
					TakeSnapShot("SalesForceLoginPage");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Assert.fail("Login Interrupted" + e);
			}
		} else {
			Assert.fail("Test Skipped: Browser Launch Failed");
		}
	}

	// Logon to KTOC NEB
	public void LogonToKTOCNEB() throws Exception {
		TestStatus = 1;
		if (TestStatus == 1) {
			try {
				driver.manage().window().maximize();
				driver.get(LogOnPage.SalesForceURL);
				driver.manage().timeouts().implicitlyWait(TIME, TimeUnit.SECONDS);
				WaitTillElementToBeClickable("xpath", CreateProductPage.ProductPlatformField);
				// Thread.sleep(50000);
			} catch (Exception e) {
				TestStatus = 0;
				TakeSnapShot("KTOCNEBLoginPage");
				Assert.fail("Login interrupted");
			}
		} else {
			Assert.fail("Test Skipped: Browser Launch Failed");
		}
	}

	// Generate Random Opportunity Number
	public static int RandomNumber() {
		Random rand = new Random();
		return rand.nextInt(10000) + 1;
	}

	// Create Opportunity
	public static String CreateOpportunity() throws Exception {
		KTOCTRBUtils KTOCTRBUtils = new KTOCTRBUtils();
		if (TestStatus == 1) {
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
			// Thread.sleep(10000);
			WaitTillClickable("xpath", SalesForceData.BusinessType);
			SelectDropDownValues("xpath", SalesForceData.BusinessType, "Modernization (TRB)");
			WaitTillClickable("xpath", SalesForceData.OpportunityName);
			Thread.sleep(5000);
			EnterTextbyChar("xpath", SalesForceData.OpportunityName, OpportunityName, 1);
			// WaitTillElementToBeClickable("xpath", SalesForceData.AccountNameField);
//			EnterTextbyChar("xpath", SalesForceData.AccountNameField, AccountName, 1);

			By txt_accountName = By.xpath("//*[text()='Account Name']/../..//span/input");
			By dd_accountName = By.xpath("//*[text()='" + AccountName + "']");
			KTOCTRBUtils.enteringValues(txt_accountName, AccountName);
			KTOCTRBUtils.waitForVisibilityOfElementLocated(dd_accountName);
			KTOCTRBUtils.clickonButton(dd_accountName);

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
			SelectDropDownText("xpath", SalesForceData.ProjectComplexity, "Small");
			ScrollUptoElement("xpath", SalesForceData.PageDescription);
			WaitAndClickOnElement("xpath", SalesForceData.SaveButton);
			WaitTillElementToBeDisplayed("xpath", SalesForceData.OpportunityDesc);
			WebElement OpportunityDescription = driver.findElement(By.xpath(SalesForceData.OpportunityDesc));
			String OppoName = OpportunityDescription.getText();
			if (OppoName.equalsIgnoreCase(OpportunityName)) {
			} else {
				TestStatus = 0;
				Assert.fail("Opportunity creation failed");
			}
		} else {
			Assert.fail("Test Skipped: Failed Logon To Salesforce");
		}
		return OpportunityName;
	}

	public void MapContactWithOpportunity() throws Exception {
		if (TestStatus == 1) {
			try {
				Thread.sleep(4000);
				WaitAndClickOnElement("xpath", SalesForceData.ContactsLink);
				WaitAndClickOnElement("xpath", SalesForceData.ContactSectionNewButton);
				WaitAndClickOnElement("xpath", SalesForceData.ContactOption);
				SelectDropDownText("xpath", SalesForceData.ContactRole, "Decision Maker");
				WaitAndClickOnElement("xpath", SalesForceData.SaveButton);
			} catch (Exception e) {
				TestStatus = 0;
				Assert.fail("Mapping contact with opportunity fails");
			}
		} else {
			Assert.fail("Test Skipped: Opportunity Creation Failed");
		}
	}

	// Map Opportunity to FLTenders - FLTenders Tab
	public void MapOpportunityWithFLTenders() throws Exception {
		if (TestStatus == 1) {
			try {
				WaitAndClickOnElement("xpath", SalesForceData.FLTendersLink);
				WaitAndClickOnElement("xpath", SalesForceData.FLTendersNewButton);
				/*
				 * Thread.sleep(10000); //MouseHover("xpath", SalesForceData.ReleaseWindow);
				 * MouseHover("xpath", SalesForceData.FocusRelease); ScrollUptoElement("xpath",
				 * SalesForceData.ScrollDownRelease); ScrollAndClickOnElement("xpath",
				 * SalesForceData.Release); WaitAndClickOnElement("xpath",
				 * SalesForceData.ReleaseOkButton);
				 */
			} catch (Exception e) {
				TestStatus = 0;
				Assert.fail("Failed mapping opportunity with tender.");
			}
		} else {
			Assert.fail("Test Skipped: Opportunity Creation Failed");
		}
	}
	/*
	 * //Map Opportunity to FLTenders - FLTenders Tab public void
	 * MapOpportunityWithFLTenders() { if(TestStatus==1) { try { Thread.sleep(4000);
	 * MouseHover("xpath", SalesForceData.FLTendersTab);
	 * WaitAndClickOnElement("xpath", SalesForceData.FLTendersTab);
	 * WaitAndClickOnElement("xpath", SalesForceData.NewButton);
	 * WaitAndClickOnElement("xpath", SalesForceData.ContinueButton);
	 * Thread.sleep(5000); MouseHover("xpath", SalesForceData.RequiredInput);
	 * EnterValues("xpath", SalesForceData.RequiredInput, OpportunityName);
	 * WaitAndClickOnElement("xpath", SalesForceData.SaveButton);
	 * WaitAndClickOnElement("xpath", SalesForceData.VersionCheckBox);
	 * List<WebElement> elements= driver.findElements(By.css("css")); element =
	 * elements.get(list.size() - 1); //"div#s11:last-child"
	 * ScrollAndClickOnElement("xpath", SalesForceData.Release);
	 * WaitAndClickOnElement("xpath", SalesForceData.ReleaseOkButton); }
	 * catch(Exception e) { TestStatus=0;
	 * Assert.fail("Failed mapping Opportunity with FL Tender " + e); } } else {
	 * Assert.fail("Test Skipped: Failed Mapping The Opportunity"); } }
	 */

	// To move to KTOC iFrame window
	public void SwitchToKTOCApp() {
		if (TestStatus == 1) {
			try {

				SwitchToFramebyID(CreateProductPage.KTOCFrameId);
				// Thread.sleep(5000);
			} catch (Exception e) {
				TestStatus = 0;
				Assert.fail("Failed switching to KTOC" + e);
			}
		} else {
			Assert.fail("Test Skipped: ");
		}
	}

	// Select Release Version
	public void ChooseReleaseVersionAndClickOk(String ReleaseNo) // String ReleaseNo
	{
		if (TestStatus == 1) {
			try {

				SwitchToFramebyID(CreateProductPage.KTOCFrameId);
				ScrollUptoElement("xpath", VersionPage.ReleaseOkButton);
				MouseHover("xpath", VersionPage.ReleaseVersionWindow);
				element = driver.findElement(By.xpath(
						"//div[@data-ctcname='KNB_Major_Version_Selector']//div[@data-ctcname='KNB_Major_Version_Selector']/child::div[3]//div[text()='"
								+ ReleaseNo + "']/preceding-sibling::img"));
				// element =
				// driver.findElement(By.xpath("//div[@data-ctcname='KNB_Major_Version_Selector']//div[@data-ctcname='KNB_Major_Version_Selector']/child::div[3]//div[text()='Release
				// 18.1']/preceding-sibling::img"));
				WaitAndClickElementDirectly(element);
				// element.click();
				WaitTillElementToBeDisplayed("xpath", VersionPage.ReleaseVersionWindow);
				ClickOkButton();
				// $x("//div[@data-ctcname='KNB_Major_Version_Selector']//div[@data-ctcname='KNB_Major_Version_Selector']/child::div[3]/div/div/../text()='Release
				// 12.1'")
			} catch (Exception e) {
				TestStatus = 0;
				Assert.fail("Failed switching to KTOC" + e);
			}
		} else {
			Assert.fail("Test Skipped: ");
		}
	}

	public void ClickOkButton() {
		try {
			Thread.sleep(4000);
			WaitAndClick("xpath", VersionPage.ReleaseOkButton);
		} catch (Exception e) {
			TestStatus = 0;
			Assert.fail("Failed Clicking Ok Button" + e);
		}
	}

	// Select First Product From The Product Dropdown
	public String SelectProductPlatform() throws Exception {
		String ProductName = null;
		// TestStatus=1;
		if (TestStatus == 1) {
			try {
				// SwitchToFrame(CreateProductPage.KTOCFrameId);
				// Thread.sleep(5000);
				WaitTillClickable("xpath", CreateProductPage.ProductPlatformField);
				ProductName = SelectFirstTextFromDropdown("xpath", CreateProductPage.ProductPlatformField, "xpath",
						CreateProductPage.ProductPlatformValue);
				// SelectTextValueFromDropdown("xpath",CreateProductPage.ProductPlatformField,"div",ProductName);
				WaitTillClickable("xpath", CreateProductPage.SalesOfficeField);
				Thread.sleep(7000);
				/*
				 * WaitTillClickable("xpath",CreateProductPage.ProductPlatformField);
				 * WaitAndClickOnElement("xpath",CreateProductPage.ProductPlatformField);
				 * Thread.sleep(5000);
				 * MouseHover("xpath",CreateProductPage.ProductPlatformValue);
				 * WaitAndClickOnElement("xpath",CreateProductPage.ProductPlatformValue);
				 */
			} catch (Exception e) {
				TestStatus = 0;
				Assert.fail("Failed selecting the product platform." + e);
			}
		} else {
			Assert.fail("Test Skipped: ");
		}

		return ProductName;
	}

	// Select Product Platform With The Given Product Name
	public void SelectProductPlatformWithTheGivenProduct(String ProductName) throws Exception {
		if (TestStatus == 1) {
			try {
				// SwitchToFrame(CreateProductPage.KTOCFrameId);
				// Thread.sleep(5000);
				WaitTillClickable("xpath", CreateProductPage.ProductPlatformField);
				SelectTextValueFromDropdown("xpath", CreateProductPage.ProductPlatformField, "div", ProductName);
				WaitTillClickable("xpath", CreateProductPage.SalesOfficeField);
				Thread.sleep(7000);
				/*
				 * WaitTillClickable("xpath",CreateProductPage.ProductPlatformField);
				 * WaitAndClickOnElement("xpath",CreateProductPage.ProductPlatformField);
				 * Thread.sleep(5000);
				 * MouseHover("xpath",CreateProductPage.ProductPlatformValue);
				 * WaitAndClickOnElement("xpath",CreateProductPage.ProductPlatformValue);
				 */
			} catch (Exception e) {
				TestStatus = 0;
				Assert.fail("Failed selecting the product platform." + e);
			}
		} else {
			Assert.fail("Test Skipped: ");
		}
	}

	// Select Template
	public void SelectTemplate() {
		if (TestStatus == 1) {
			try {
				Thread.sleep(3000);
				// WaitTillElementDisplayed("xpath",CreateProductPage.CreateProductButton);
				// ScrollUptoElement("xpath",CreateProductPage.TemplateExpand);
				MouseHover("xpath", CreateProductPage.TemplateExpand);
				WaitAndClickOnElement("xpath", CreateProductPage.TemplateExpand);
				WaitAndClickOnElement("xpath", CreateProductPage.AutomationTestingTemplate);
			} catch (Exception e) {
				TestStatus = 0;
				Assert.fail("Failed selecting the template." + e);
			}
		} else {
			Assert.fail("Test Skipped: Selecting Product Platform Failed.");
		}
	}

	// Select Sales Office
	public void SelectSalesOffice() throws Exception {
		if (TestStatus == 1) {
			try {
				// Thread.sleep(10000);
				// WaitTillClickable("xpath",CreateProductPage.SalesOfficeField);
				SelectTextValueFromDropdown("xpath", CreateProductPage.SalesOfficeField, CreateProductPage.DivNode,
						CreateProductPage.SalesOfficeValue);
			} catch (Exception e) {
				TestStatus = 0;
				Assert.fail("Failed selecting the sales office." + e);
			}
		} else {
			Assert.fail("Test Skipped: Selecting Product Platform Failed.");
		}
	}

	public void ClickCreateProductButton() throws Exception {
		if (TestStatus == 1) {
			try {
				ScrollUptoElement("xpath", CreateProductPage.SalesOfficeField);
				WaitAndClickOnElement("xpath", CreateProductPage.CreateProductButton);
				WaitTillVisible("xpath", TenderPage.SaveCloseIcon);
			} catch (Exception e) {
				TestStatus = 0;
				TakeSnapShot("CreateProductPage");
				Assert.fail("Failed clicking Create Product button.");
			}
		} else {
			Assert.fail("Test Skipped: Selecting Product Platform Failed.");
		}
	}

	// Click Create Product Button Having Wrong Locator
	public void ClickWrongButton() throws Exception {
		if (TestStatus == 1) {
			try {
				ScrollUptoElement("xpath", CreateProductPage.SalesOfficeField);
				WaitAndClickOnElement("xpath", CreateProductPage.WrongCreateProductButton);
			} catch (Exception e) {
				TestStatus = 0;
				TakeSnapShot("CreateProductPage");
				Assert.fail("Failed clicking Create Product button.");
			}
		} else {
			Assert.fail("Test Skipped: Selecting Product Platform Failed.");
		}
	}

	// Get Tender No.
	public String GetTenderNo() {
		String Tender = null;
		if (TestStatus == 1) {
			try {
				element = FindTheElement("xpath", CreateProductPage.TenderNo);
				Tender = element.getAttribute("value");
				Thread.sleep(20000);
				WaitTillClickable("xpath", TenderPage.SaveCloseIcon);
			} catch (Exception e) {
				TestStatus = 0;
				// TakeSnapShot(Frontline,"SectionC");
				Assert.fail("Failed: ");
			}
		} else {
			Assert.fail("Test Skipped: ");
		}
		return Tender;
	}

	// Get Total Sales Price
	public String GetSalesPriceFromTenderPage() {
		if (TestStatus == 1) {
			try {
				String SalesPrice = GetTextOf("xpath", TenderPage.TotalSalesPrice);
				String[] SplitPrice = SalesPrice.split("INR ", 2);
				KTOCSalesPrice = SplitPrice[1];
			} catch (Exception e) {
				TestStatus = 0;
				// TakeSnapShot(Frontline,"SectionC");
				Assert.fail("Failed Getting KTOC Sales Price " + e);
			}
		} else {
			Assert.fail("Test Skipped: ");
		}
		return KTOCSalesPrice;
	}

	// Get Sales Price From Opportunity Page
	public String GetSalesPriceFromSalesForce() {
		if (TestStatus == 1) {
			try {
				WaitAndClickOnElement("xpath", TenderPage.TenderPageOpportunity);
				ScrollUptoElement("xpath", SalesForceData.ProductList);
				String SalesPrice = GetTextOf("xpath", SalesForceData.TotalPrice);
				String[] SplitPrice = SalesPrice.split("EUR ", 2);
				SalesForceSalesPrice = SplitPrice[1];
			} catch (Exception e) {
				TestStatus = 0;
				// TakeSnapShot(Frontline,"SectionC");
				Assert.fail("Failed Getting SalesForce Sales Price" + e);
			}
		} else {
			Assert.fail("Test Skipped: ");
		}
		return SalesForceSalesPrice;
	}

	// Verify And Compare Total Prices
	public String CompareTotalSalesPrice() {
		if (TestStatus == 1) {
			try {
				if (KTOCSalesPrice.equalsIgnoreCase(SalesForceSalesPrice)) {
					System.out.println("The Sales Price Matching");
				} else {
					Assert.fail("\n" + "  KTOC Sales Price: " + KTOCSalesPrice + "\n" + "SalesForce Sales Price: "
							+ SalesForceSalesPrice);
				}
			} catch (Exception e) {
				TestStatus = 0;
				// TakeSnapShot(Frontline,"SectionC");
				Assert.fail("The Total Price from KTOC is not matching with salesforce " + e);
			}
		} else {
			Assert.fail("Test Skipped: ");
		}
		return "EUR " + KTOCSalesPrice;
	}

	// Save Product
	public void SaveProduct() throws Exception {
		if (TestStatus == 1) {
			try {
				Thread.sleep(5000);
				WaitTillVisible("xpath", TenderPage.SaveIcon);
				WaitAndClickOnElement("xpath", TenderPage.SaveIcon);
				WaitAndClickOnElement("xpath", TenderPage.SaveOKButton);
			} catch (Exception e) {
				TestStatus = 0;
				Assert.fail("Failed saving the product.");
			}
		} else {
			Assert.fail("Test Skipped: Product Creation Failed.");
		}
	}

	// Save And Exit The Page In One Click
	public void SaveAndExitKTOC() {
		if (TestStatus == 1) {
			try {
				WaitTillClickable("xpath", TenderPage.SaveCloseIcon);
				WaitAndClick("xpath", TenderPage.SaveCloseIcon);
				MouseHover("xpath", TenderPage.SaveCloseYesButton);
				WaitAndClickOnElement("xpath", TenderPage.SaveCloseYesButton);
				WaitTillVisible("xpath", TenderPage.CloseOKButton);
				WaitAndClick("xpath", TenderPage.CloseOKButton);
			} catch (Exception e) {
				TestStatus = 0;
				Assert.fail("Failed closing KTOC." + e);
			}
		} else {
			Assert.fail("Test Skipped: Product Creation Failed.");
		}
	}

	public void CloseBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			Assert.fail("Failed closing KTOC.");
		}
	}

	// Method Template
	public void Template() {
		if (TestStatus == 1) {
			try {

			} catch (Exception e) {
				TestStatus = 0;
				// TakeSnapShot(Frontline,"SectionC");
				Assert.fail("Failed: ");
			}
		} else {
			Assert.fail("Test Skipped: ");
		}
	}

}
