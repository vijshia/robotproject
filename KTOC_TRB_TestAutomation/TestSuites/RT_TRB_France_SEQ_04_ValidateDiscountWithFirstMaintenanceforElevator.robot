*** Settings ***
Library    com.KTOC.TRB.testautomation.Keywords.Keywords    
Suite Setup  Launch Browser    france    \\TestData\\KTOCTRB_AutomationTestData.xlsx
Suite Teardown    Close Browser    
*** Variable ***

*** Test Cases ***
1.Logon To Salesforce
    Loginto Salesforce
2.Verify Creating Opportunity and Mapping It With FLTender
    Create Opportunity OR Search Opportunity
3.Verify Tender Created Successfully for Elevator
    Navigateto KTOCTRB
    Add Equipment ID Elevator
    Select Template To Be Uploaded
    Check Hand Over Date Is Greater Than Installation Date
    Check Sales Officeis Selected
    Select Supervisor
    Select Equipment In Service
    Verify Tender Consistency
    Get Tender Number
    Pricing Icon Click
4.Validate Discount With FirstMaintenance for Elevator
    Verify Discount By Changing The Tender Price    3200    6
5.Check Discount With FirstMaintenance for Elevator
    Get Target Price
    
6.Change and Validate Discount With FirstMaintenance for Elevator    
    Verify Discount By Changing The Tender Price    3800    3
7.Check Changed Discount With FirstMaintenance for Elevator    
    Get Target Price
8.Check TenderLetter Is generated Correctly With All The components
    Go To Documents Taband Click The Tender
    Verify Successful Message Displayed
9.Close KTOC
    Click Saveand Close Button
10.Verify Total SalesPrice With SF Product Information
    Compare Sales Pricebetween Tender Pageand Salesforce
    Hand Shake
#23.VerifyOrderCreatedSuccessfullyForElevator