*** Settings ***
Library    com.KTOC.TRB.testautomation.Keywords.Keywords    
Suite Setup  Launch Browser    france    &{TESTDATA_FRANCE}                        #\\TestData\\KTOCTRB_AutomationTestData.xlsx
Suite Teardown    Close Browser    
Variables    ../TestData/KTOCTRB_AutomationTestData_PY.py
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
5.Check TenderPrice Without FirstMaintenance for Elevator    
    Get Target Price
6.Validate Discount Without FirstMaintenance for Elevator    
    Verify Discount By Changing The Tender Price    3600    0
7.Check Discount Without FirstMaintenance for Elevator    
    Get Target Price
8.Validate TenderPrice and Discount With FirstMaintenance for Elevator
    Check Tender Price After Discount Update    11    3
9.Check TenderPrice With FirstMaintenance for Elevator    
    Get Target Price
10.Validate Discount With FirstMaintenance for Elevator    
    Verify Discount By Changing The Tender Price    4101    4
11.Check Discount With FirstMaintenance for Elevator    
    Get Target Price
12.Change and Validate Discount With FirstMaintenance for Elevator    
    Verify Discount By Changing The Tender Price    4051    3
13.Check Changed Discount With FirstMaintenance for Elevator    
    Get Target Price
14.Check Regional Factor At SalesOffice Level for Elevator
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    
15.Check Regional Factor When SalesOffice Is Changed for Elevator
    Goto Configuration Pageand Change The Sales Office
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    
16.Verify ITEFactorValue and LaborRateValue IsTaken From SalesOffice for Elevator
    Goto Configuration Pageand Change The Primary Sales Office    france    \\TestData\\KTOCTRB_AutomationTestData.xlsx
    Validate Detail Breakdown Tab
17. Verify ITEFactorValue and LaborRateValue IsTaken From Changed SalesOffice for Elevator    
    Goto Configuration Pageand Change The Sales Office
    Validate Detail Breakdown Tab
18.Check Cost Calculated Correctly When The Tender Currency Is Different From SLCurrency for Elevator
    Verify Cost Calculated Successfully
19.Check Price Calculated Correctly When The Tender Currency Is Different From SLCurrency for Elevator    
    VerifyPriceCalculatedSuccessfully TobecheckedinFrance
20.TestData for Escalator
    Read Test Data    france    \\TestData\\KTOCTRB_AutomationTestData.xlsx
    Add Multiple Equipments
21.Verify Tender Created Successfully for Escalator
    Add Equipment ID Elevator
    Check Hand Over Date Is Greater Than Installation Date
    Check Sales Officeis Selected
    Additionalfieldsin Project Overviewfor Canada
    Select Supervisor
    Select Equipment In Service
    Select Template To Be Uploaded
    Verify Tender Consistency
    Get Tender Number
22.Validate TenderPrice Without FirstMaintenance for Escalator
    Pricing Icon Click
    Check Tender Price After Discount Update    6    0
23.Check TenderPrice Without FirstMaintenance for Escalator  
    Get Target Price
24.Validate Discount Without FirstMaintenance for Escalator   
    Verify Discount By Changing The Tender Price    23000    0
25.Check Discount Without FirstMaintenance for Escalator 
    Get Target Price
26.Validate TenderPrice With FirstMaintenance for Escalator
    Check Tender Price After Discount Update    7    3
27.Check TenderPrice With FirstMaintenance for Escalator    
    Get Target Price
28.Validate Discount With FirstMaintenance for Escalator    
    Verify Discount By Changing The Tender Price    22500    4
29.Check Discount With FirstMaintenance for Escalator        
    Get Target Price
30.Change and Validate Discount With FirstMaintenance for Escalator 
    Verify Discount By Changing The Tender Price    22250    3
31.Check Changed Discount With FirstMaintenance for Escalator
    Get Target Price
32.CheckRegionalFactorAtSalesOfficeLevel for Escalator
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    
33.CheckRegionalFactorWhenSalesOfficeIsChanged for Escalator
    Goto Configuration Pageand Change The Sales Office
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    
34.Verify ITEFactorValue and LaborRateValue IsTaken From SalesOffice for Escalator
    Goto Configuration Pageand Change The Primary Sales Office    france    \\TestData\\KTOCTRB_AutomationTestData.xlsx
    Validate Detail Breakdown Tab
35. Verify ITEFactorValue and LaborRateValue IsTaken From Changed SalesOffice for Escalator  
    Goto Configuration Pageand Change The Sales Office
    Validate Detail Breakdown Tab
36.Check Cost Calculated Correctly When The Tender Currency Is Different From SLCurrency for Escalator
    Verify Cost Calculated Successfully
37.Check Price Calculated Correctly When The Tender Currency Is Different From SLCurrency for Escalator
    VerifyPriceCalculatedSuccessfully TobecheckedinFrance    
38.Check TenderLetter Is generated Correctly With All The components for Escalator
    Go To Documents Taband Click The Tender
    Verify Successful Message Displayed
39.Close KTOC
    Click Saveand Close Button
40.Verify Total SalesPrice With SF Product Information
    Compare Sales Pricebetween Tender Pageand Salesforce
    Hand Shake
#41.VerifyOrderCreatedSuccessfullyForElevator