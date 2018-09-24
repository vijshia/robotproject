package com.KTOC.TRB.testautomation.Keywords;

//import java.awt.event.KeyEvent;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.Wait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.python.icu.impl.Assert;

import com.KTOC.TRB.testautomation.Utilities.KTOCTRBUtils;
import com.KTOC.TRB.testautomation.ObjectRepository.*;
/*import com.KTOC.testautomation.ObjectRepository.LogOnPage;
import com.KTOC.testautomation.ObjectRepository.SalesForceData;
import com.KTOC.testautomation.ObjectRepository.TenderPage;*/
import com.KTOC.TRB.testautomation.Utilities.KTOCTRBUtils;
import com.KTOC.TRB.testautomation.Utilities.Xls_Reader;
import com.KTOC.TRB.testautomation.TestData.*;


public class Keywords extends KTOCTRBUtils{
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
			SelectDropDownValues("xpath", SalesForceData.BusinessType, "New Equipment (NEB)");
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
		
		SwitchToFrame(CreateProductPage.KTOCFrameId);
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
		
		SwitchToFrame(CreateProductPage.KTOCFrameId);
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
