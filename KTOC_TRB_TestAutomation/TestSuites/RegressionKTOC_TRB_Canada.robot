*** Settings ***
Library    com.KTOC.TRB.testautomation.Keywords.Keywords

Suite Setup    Launch Browser    canada    \\TestData\\KTOCTRB_AutomationTestData.xlsx
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
4.Validate TenderPrice Without FirstMaintenance for Elevator
    Pricing Icon Click
    Check Tender Price After Discount Update    10    0
5.Check TenderPrice Without FirstMaintenance for Elevator    
    Get Target Price
6.Validate Discount Without FirstMaintenance for Elevator    
    Verify Discount By Changing The Tender Price    2001    0
7.Check Discount Without FirstMaintenance for Elevator        
    Get Target Price
8.Validate TenderPrice With FirstMaintenance for Elevator
    Check Tender Price After Discount Update    11    3
9.Check TenderPrice With FirstMaintenance for Elevator
    Get Target Price
10.Validate Discount With FirstMaintenance for Elevator
    Verify Discount By Changing The Tender Price    1002    3
11.Check Discount With FirstMaintenance for Elevator
    Get Target Price
12.Change and Validate Discount With FirstMaintenance for Elevator    
    Verify Discount By Changing The Tender Price    1003    6
13.Check Changed Discount With FirstMaintenance for Elevator    
    Get Target Price
14.Check Regional Factor At SalesOffice Level for Elevator
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    0
15.Check Regional Factor When SalesOffice Is Changed for Elevator
    Goto Configuration Pageand Change The Sales Office
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    0
16 Verify ITEFactorValue and LaborRateValue IsTaken From SalesOffice for Elevator
    Goto Configuration Pageand Change The Primary Sales Office    canada    \\TestData\\KTOCTRB_AutomationTestData.xlsx
    Validate Detail Breakdown Tab
17 Verify ITEFactorValue and LaborRateValue IsTaken From Changed SalesOffice for Elevator
    Goto Configuration Pageand Change The Sales Office
    Validate Detail Breakdown Tab
18.Check Cost Calculated Correctly When The Tender Currency Is Different From SLCurrency for Elevator
    Verify Cost Calculated Successfully
19.Check Price Calculated Correctly When The Tender Currency Is Different From SLCurrency for Elevator    
    VerifyPriceCalculatedSuccessfully TobecheckedinFrance
20.Check TenderLetter Is generated Correctly With All The components
    Go To Documents Taband Click The Tender
    Verify Successful Message Displayed
21.Close KTOC
    Click Saveand Close Button
22.Verify Total SalesPrice With SF Product Information
    Compare Sales Pricebetween Tender Pageand Salesforce
    Hand Shake
#23.VerifyOrderCreatedSuccessfullyForElevator