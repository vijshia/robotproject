*** Settings ***
Library    com.KTOC.TRB.testautomation.Keywords.Keywords    
Suite Setup  Launch Browser    france    \\TestData\\KTOCTRB_AutomationTestData.xlsx
*** Variable ***

*** Test Cases ***
1.Logon To Salesforce
    Loginto Salesforce
2.Verify Creating Opportunity and Mapping It With FLTender
    Create Opportunity OR Search Opportunity
3.Verify Tender Created Successfully for Elevator
    Navigateto KTOCTRB
    Add Equipment ID Elevator
    Check Hand Over Date Is Greater Than Installation Date
    Check Sales Officeis Selected
    Additionalfieldsin Project Overviewfor Canada
    Select Supervisor
    Select Equipment In Service
    Select Template To Be Uploaded
    Verify Tender Consistency
    Get Tender Number
4.Validate TenderPrice and Discount Without FirstMaintenance for Elevator
    Pricing Icon Click
    Check Tender Price After Discount Update    10    0
    Get Target Price
    Verify Discount By Changing The Tender Price    1001    0
    Get Target Price
5.Validate TenderPrice and Discount With FirstMaintenance for Elevator
    Check Tender Price After Discount Update    11    3
    Get Target Price
    Verify Discount By Changing The Tender Price    1004    3
    Get Target Price
    Verify Discount By Changing The Tender Price    1005    6
    Get Target Price
6.CheckRegionalFactorAtSalesOfficeLevel for Elevator
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    0
7.CheckRegionalFactorWhenSalesOfficeIsChanged for Elevator
    Goto Configuration Pageand Change The Sales Office
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    0
8 & 9 Verify ITEFactorValue and LaborRateValue IsTaken From SalesOffice for Elevator
    Goto Configuration Pageand Change The Primary Sales Office    france    \\TestData\\KTOCTRB_AutomationTestData.xlsx
    Validate Detail Breakdown Tab
    Goto Configuration Pageand Change The Sales Office
    Validate Detail Breakdown Tab
10.CheckCostAndPriceCalculatedCorrectlyWhenTheTenderCurrencyIsDifferentFromSLCurrency for Elevator
    Verify Cost Calculated Successfully
    VerifyPriceCalculatedSuccessfully TobecheckedinFrance
11.TestData for Escalator
    Read Test Data    france    \\TestData\\KTOCTRB_AutomationTestData.xlsx
    Add Multiple Equipments
12.Verify Tender Created Successfully for Escalator
    Add Equipment ID Elevator
    Check Hand Over Date Is Greater Than Installation Date
    Check Sales Officeis Selected
    Additionalfieldsin Project Overviewfor Canada
    Select Supervisor
    Select Equipment In Service
    Select Template To Be Uploaded
    Verify Tender Consistency
    Get Tender Number
13.Validate TenderPrice and Discount Without FirstMaintenance for Escalator
    Pricing Icon Click
    Check Tender Price After Discount Update    10    0
    Get Target Price
    Verify Discount By Changing The Tender Price    1011    0
    Get Target Price
14.Validate TenderPrice and Discount With FirstMaintenance for Escalator
    Check Tender Price After Discount Update    11    3
    Get Target Price
    Verify Discount By Changing The Tender Price    1012    3
    Get Target Price
    Verify Discount By Changing The Tender Price    1013    6
    Get Target Price
15.CheckRegionalFactorAtSalesOfficeLevel for Escalator
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    0
16.CheckRegionalFactorWhenSalesOfficeIsChanged for Escalator
    Goto Configuration Pageand Change The Sales Office
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    0
8 & 9 Verify ITEFactorValue and LaborRateValue IsTaken From SalesOffice for Escalator
    Goto Configuration Pageand Change The Primary Sales Office    france    \\TestData\\KTOCTRB_AutomationTestData.xlsx
    Validate Detail Breakdown Tab
    Goto Configuration Pageand Change The Sales Office
    Validate Detail Breakdown Tab
18.CheckCostAndPriceCalculatedCorrectlyWhenTheTenderCurrencyIsDifferentFromSLCurrency for Escalator
    Verify Cost Calculated Successfully
    VerifyPriceCalculatedSuccessfully TobecheckedinFrance    
19.Check TenderLetter Is generated Correctly With All The components for Escalator
    Go To Documents Taband Click The Tender
    Verify Successful Message Displayed
20.Close KTOC
    Click Saveand Close Button
21.Verify Total SalesPrice With SF Product Information
    Compare Sales Pricebetween Tender Pageand Salesforce
    Hand Shake
# 14.VerifyOrderCreatedSuccessfullyForElevator