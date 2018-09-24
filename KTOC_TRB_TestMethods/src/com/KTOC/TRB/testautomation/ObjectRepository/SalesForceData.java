package com.KTOC.TRB.testautomation.ObjectRepository;

public class SalesForceData {
	public static String AccountString=null;
	  public static String OpportunityTab = "//li[@id='Opportunity_Tab']";
	  public static String NewButton = "//input[@class='btn' and @name='new']";//"input.btn";//"//td[@class='pbButton']";//
	  public static String ContinueButton = "//input[@class='btn' and @name='save' and @type='submit']";
	  public static String DeleteButton = "//input[@class='btn' and @name='del']";  //[type='button']
	  public static String EditButton = "//input[@class='btn' and @name='edit']";
	  public static String BusinessType = "//select[@id='00N20000000sJrZ']";
	  public static String OpportunityCategory = "//select[@id='00N20000000scK0']";
	  public static String OpportunityName = "//input[@id='opp3']";
	  public static String AccountNameField = "//input[@id='opp4']";
	  public static String AutoCompleteBox = "//div[@id='opp4_autoCompleteBoxId']";
	  public static String AutoCompleteItems="/div[contains(@id,'opp4_autoCompleteRowId']";
	  public static String AccountNameIcon = "//a[@id='opp4_lkwgt']";
	  public static String AccountNameSearchList = "//a[@class=' dataCell ' and text()= '" + AccountString + "']";
	  public static String MarketSegment = "//select[@id='00N20000000sJrU']";
	  public static String LeadSource = "//select[@id='opp6']";
	  public static String QuantityField = "//input[@id='TotalOpportunityQuantity']";
	  public static String AmountField = "//input[@id='opp7']";
	  public static String PriceDueDateField = "//input[@id='00N20000000sSJc']";
	  public static String CloseDateField = "//input[@id='opp9']";
	  public static String Stage = "//select[@id='opp11']";
	  public static String SiteCountryField = "//select[@id='00N20000000sJsD']";
	  public static String SiteCountyField = "//select[@id='00N20000000sJs8']";
	  public static String BranchOfficeField = "//select[@id='00N20000002CwJM']";
	  public static String StartOnSiteDateField = "//input[@id='00N20000000sJsN']";
	  public static String ProjectEndDateField = "//input[@id='00N20000000sJsA']";
	  public static String PageDescription = "//h2[@class='pageDescription']";
	  public static String SaveButton = "//input[@class = 'btn' and @name='save']";
	  public static String CancelledReason = "//select[@id='00Nw0000007pPJl']";
	  public static String WinningCompetitor = "00N20000000sSKL";
	  //public static String SaveButton = "input[class='btn'][name='save']"; //[tabindex='91']";
	  public static String PageTitle = "ptBody";
	  public static String OpportunityDesc = "//h2[@class='pageDescription']";
	  public static String UserNameHeader = "h1.currentStatusUserName";
	  public static String ProjectComplexity = "//select[@id='00Nw0000009EkOt']";//"//span[text()='Project Complexity']/following::td/span/select";

	  public static String AccountsTab = "Account_Tab";
	  public static String FirstOpportunity = "tr.dataRow.even.first";
	  public static String DataQualityHeader = "head_01B2000000SUdUs_ep";
	  public static String DataRow = "tr.dataRow";

	  public static String QuantityValue = "2";
	  public static String Amountvalue = "200";
	  public static String PriceDueDateValue = "26";
	  public static String CloseDateValue = "26";
	  public static String StartOnSiteDateValue = "26";
	  public static String ProjectEndDateValue = "26";

	  public static String SiteCountryValue = "Austria";
	  public static String SiteCountyValue = "Tyrol";
	  public static String BranchOfficeValue = "Chennai";
	  
	  //Accounts Page
	  public static String AccountNameFld=  "//input[contains(@id,'j_id37')]"; //"//input[@id='acc2']";
	  public static String AccountNameLocal= "//input[contains(@id,'j_id39')]"; //"//input[@id='acc29']";
	  public static String Street2Field= "//input[@id='00N20000000tWd3']";
	  public static String Street4Field= "//input[@id='00N20000000tWdI']";
	  public static String HouseNo= "//input[@id='00N20000001uQmz']";
	  public static String PostalNo= "//input[@id='00N20000001uUkM']";
	  public static String City= "//input[@id='00N20000001uUkC']";
	  public static String State= "//select[@id='00N20000000sdDu']";
	  public static String CustomerSegment= "//select[@id='00N20000000sJZ6']";
	  
	  //Accounts Page Data
	  public static String StateValue="GOA";
	  public static String CustomerSegmentValue="Builder";
	  
	  //Contacts
	  public static String ContactsLink="//a[contains(@id,'RelatedContactRoleList_link')]";
	  public static String ContactSectionNewButton="//div[contains(@class,'contactBlock')]//input";  //[@class='btn' and @name='new']";
	  public static String ContactOption= "//input[@id='primary0']";     //"//td[@class='cbindex radioCol']";
	  public static String ContactRole = "//td[@class='cbindex radioCol']/following::td[2]/select";
	  
	  //FLTenders
	  public static String FLTendersLink="//span[@class='listTitle' and text()='FL Tenders']";
	  public static String FLTendersNewButton="//div[contains(@class,'Custom42Block')]//input";
	  public static String FLTendersTab= "//li[@class='wt-Tender']";     //[@id='01r200000001D5e_Tab]"
	  public static String RequiredInput = "//div[@class='requiredInput']";
	  public static String VersionCheckBox = "//input[@type='checkbox' and @class='activationCheck']";
	  public static String ConfiguratorButton = "//input[@title='Configurator']";
	  public static String ReleaseWindow = "//div[@id='b11']";
	  public static String FocusRelease = "//div[@id='s10']";
	  public static String ScrollDownRelease = "//div[@id='x11_13']";
	  public static String Release = "//div[@id='x11_15']";
	  public static String ReleaseOkButton="//div[@id=s7]/button/button";
	  
	  //Opportunity Edit Page
	  public static String ProductList = "//h3[contains(@id,'_RelatedLineItemList_title')]";
	  public static String TotalPrice="//a[text()='KONE EcoSpace B3']/following::td[3]";
	  
	  //User Settings
	  public static String LoggedInUserName="//a[@id='globalHeaderNameMink']";
	  public static String MySettings="//a[text()='My Settings']";
	  public static String PersonalInfo="//div[@id='PersonalInfo']";
	  public static String AdvancedUserDetails="//span[text()='Advanced User Details']";
	  public static String UserEditButton="//input[@name='edit']";
	  public static String AdditionalInformationSection="//h3[text()='Additional Information']";
	  public static String SalesOrgField="//select[@id='00N20000000sJNK']";
	  public static String UserPageSaveButton = "//input[@name='save']";
}
