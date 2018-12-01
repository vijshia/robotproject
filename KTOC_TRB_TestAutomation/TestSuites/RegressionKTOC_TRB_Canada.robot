*** Settings ***
Library    com.KTOC.TRB.testautomation.Keywords.Keywords

Suite Setup    Launch Browser    canada    C:\\Users\\con_svijay02\\KTOC-TRB-Automation\\KTOC_TRB_TestAutomation\\TestData\\KTOCTRB_AutomationTestData.xlsx
*** Variable ***
*** Test Cases ***
1.Logon To Salesforce
    Loginto Salesforce
2.Verify Creating Opportunity and Mapping It With FLTender
    Create Opportunity OR Search Opportunity
3.Verify Tender Created Successfully
    Add Equipment ID Elevator
    Check Hand Over Date Is Greater Than Installation Date
    Check Sales Officeis Selected
    Additionalfieldsin Project Overviewfor Canada
    Select Supervisor
    Select Equipment In Service
    Select Template To Be Uploaded
    Verify Tender Consistency
    Get Tender Number
4.Validate TenderPrice and Discount Without FirstMaintenance
    Pricing Icon Click
    Check Tender Price After Discount Update    10    0
    Get Target Price
    Verify Discount By Changing The Tender Price    2001    0
    Get Target Price
5.Validate TenderPrice and Discount With FirstMaintenance
    Check Tender Price After Discount Update    11    3
    Get Target Price
    Verify Discount By Changing The Tender Price    2002    3
    Get Target Price
    Verify Discount By Changing The Tender Price    2003    6
    Get Target Price
6.CheckRegionalFactorAtSalesOfficeLevel
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    0
7.CheckRegionalFactorWhenSalesOfficeIsChanged
    Goto Configuration Pageand Change The Sales Office
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    0
8 & 9 Verify ITEFactorValue and LaborRateValue IsTaken From SalesOffice
    Validate Detail Breakdown Tab
    Goto Configuration Pageand Change The Sales Office
    Validate Detail Breakdown Tab
10.CheckCostAndPriceCalculatedCorrectlyWhenTheTenderCurrencyIsDifferentFromSLCurrency
    Verify Cost Calculated Successfully
    VerifyPriceCalculatedSuccessfully TobecheckedinFrance
11.Check TenderLetter Is generated Correctly With All The components
    Go To Documents Taband Click The Tender
    Verify Successful Message Displayed
12.Close KTOC
    Click Saveand Close Button
13.Verify Total SalesPrice With SF Product Information
    Compare Sales Pricebetween Tender Pageand Salesforce
    Hand Shake
    # 14.VerifyOrderCreatedSuccessfullyForElevator