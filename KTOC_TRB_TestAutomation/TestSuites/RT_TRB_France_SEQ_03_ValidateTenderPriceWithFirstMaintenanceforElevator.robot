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
4.Validate TenderPrice With FirstMaintenance for Elevator
    Check Tender Price After Discount Update    7    3
5.Check TenderPrice With FirstMaintenance for Elevator
    Get Target Price
6.Check TenderLetter Is generated Correctly With All The components
    Go To Documents Taband Click The Tender
    Verify Successful Message Displayed
7.Close KTOC
    Click Saveand Close Button
8.Verify Total SalesPrice With SF Product Information
    Compare Sales Pricebetween Tender Pageand Salesforce
    Hand Shake
#23.VerifyOrderCreatedSuccessfullyForElevator