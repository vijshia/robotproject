*** Settings ***
Library    com.KTOC.TRB.testautomation.Keywords.Keywords     
Test Setup       Launch Browser    ${country}    ${excelpath}
Test Teardown    Close Browser  
*** Variable ***
${country}    australia
${excelpath}    \\TestData\\KTOCTRB_AutomationTestData.xlsx
${withoutfirstmaintenance}    0
&{withfirstmaintenance}    firstmaintenance_1=3    firstmaintenance_2=6    firstmaintenance_3=3
&{discount}    discount_1=5    discount_2=7    discount_3=25
&{tenderprice}    tenderprice_1=2900    tenderprice_2=3200    tenderprice_3=3800

*** Test Cases ***
01. Validate TenderPrice Without FirstMaintenance for Elevator
    01_Logon To Salesforce
    02_Verify Creating Opportunity and Mapping It With FLTender
    03_Verify Tender Created Successfully for Elevator
    04_Validate TenderPrice Without FirstMaintenance for Elevator
    05_Check TenderPrice Without FirstMaintenance for Elevator
    21_Check TenderLetter Is generated Correctly With All The components
    22_Close KTOC
    23_Verify Total SalesPrice With SF Product Information
02. Validate Discount Without FirstMaintenance for Elevator
    01_Logon To Salesforce
    02_Verify Creating Opportunity and Mapping It With FLTender
    03_Verify Tender Created Successfully for Elevator
    06_Validate Discount Without FirstMaintenance for Elevator
    07_Check Discount Without FirstMaintenance for Elevator
    21_Check TenderLetter Is generated Correctly With All The components
    22_Close KTOC
    23_Verify Total SalesPrice With SF Product Information
03. Validate TenderPrice With FirstMaintenance for Elevator
    01_Logon To Salesforce
    02_Verify Creating Opportunity and Mapping It With FLTender
    03_Verify Tender Created Successfully for Elevator
    08_Validate TenderPrice With FirstMaintenance for Elevator
    09_Check TenderPrice With FirstMaintenance for Elevator
    21_Check TenderLetter Is generated Correctly With All The components
    22_Close KTOC
    23_Verify Total SalesPrice With SF Product Information    
04. Validate Discount With FirstMaintenance for Elevator
    01_Logon To Salesforce
    02_Verify Creating Opportunity and Mapping It With FLTender
    03_Verify Tender Created Successfully for Elevator
    10_Validate Discount With FirstMaintenance for Elevator
    11_Check Discount With FirstMaintenance for Elevator
    12_Change and Validate Discount With FirstMaintenance for Elevator
    13_Check Changed Discount With FirstMaintenance for Elevator
    21_Check TenderLetter Is generated Correctly With All The components
    22_Close KTOC
    23_Verify Total SalesPrice With SF Product Information    
05. Check RegionalFactor at SalesOffice Level for Elevator
    01_Logon To Salesforce
    02_Verify Creating Opportunity and Mapping It With FLTender
    03_Verify Tender Created Successfully for Elevator
    14_Check Regional Factor At SalesOffice Level for Elevator
    15_Check Regional Factor When SalesOffice Is Changed for Elevator
    21_Check TenderLetter Is generated Correctly With All The components
    22_Close KTOC
    23_Verify Total SalesPrice With SF Product Information  
06. Verify ITEFactor value and LaborRate value is taken from SalesOffice for Elevator
    01_Logon To Salesforce
    02_Verify Creating Opportunity and Mapping It With FLTender
    03_Verify Tender Created Successfully for Elevator
    16_Verify ITEFactorValue and LaborRateValue IsTaken From SalesOffice for Elevator
    17_Verify ITEFactorValue and LaborRateValue IsTaken From Changed SalesOffice for Elevator
    21_Check TenderLetter Is generated Correctly With All The components
    22_Close KTOC
    23_Verify Total SalesPrice With SF Product Information 
07. Check Price Calculated Correctly when the Tender Currency is different from SLCurrency for Elevator
    01_Logon To Salesforce
    02_Verify Creating Opportunity and Mapping It With FLTender
    03_Verify Tender Created Successfully for Elevator
    18_Check Cost Calculated Correctly When The Tender Currency Is Different From SLCurrency for Elevator
    19_Check Price Calculated Correctly When The Tender Currency Is Different From SLCurrency for Elevator
    21_Check TenderLetter Is generated Correctly With All The components
    22_Close KTOC
    23_Verify Total SalesPrice With SF Product Information
08. Checking Maximum Discount Limit Exceeded
    01_Logon To Salesforce
    02_Verify Creating Opportunity and Mapping It With FLTender
    03_Verify Tender Created Successfully for Elevator
    20_Checking Maximum DiscountLimit Exceeded
    21_Check TenderLetter Is generated Correctly With All The components
    22_Close KTOC
    
*** Keywords ***
01_Logon To Salesforce
    Loginto Salesforce
02_Verify Creating Opportunity and Mapping It With FLTender
    Create Opportunity OR Search Opportunity
03_Verify Tender Created Successfully for Elevator
    Navigateto KTOCTRB
    Add Equipment ID Elevator
    Select Template To Be Uploaded
    Check Hand Over Date Is Greater Than Installation Date
    Check Sales Officeis Selected
    Additionalfieldsin Project Overviewfor Canada
    Select Supervisor
    Select Equipment In Service
    Verify Tender Consistency
    Get Tender Number
    Pricing Icon Click
04_Validate TenderPrice Without FirstMaintenance for Elevator
    Check Tender Price After Discount Update    &{discount}[discount_1]    ${withoutfirstmaintenance}
05_Check TenderPrice Without FirstMaintenance for Elevator    
    Get Target Price
06_Validate Discount Without FirstMaintenance for Elevator    
    Verify Discount By Changing The Tender Price    &{tenderprice}[tenderprice_1]    ${withoutfirstmaintenance}
07_Check Discount Without FirstMaintenance for Elevator        
    Get Target Price
08_Validate TenderPrice With FirstMaintenance for Elevator
    Check Tender Price After Discount Update    &{discount}[discount_2]    &{withfirstmaintenance}[firstmaintenance_1]
09_Check TenderPrice With FirstMaintenance for Elevator
    Get Target Price
10_Validate Discount With FirstMaintenance for Elevator
    Verify Discount By Changing The Tender Price    &{tenderprice}[tenderprice_2]    &{withfirstmaintenance}[firstmaintenance_2]
11_Check Discount With FirstMaintenance for Elevator
    Get Target Price
12_Change and Validate Discount With FirstMaintenance for Elevator    
    Verify Discount By Changing The Tender Price    &{tenderprice}[tenderprice_3]    &{withfirstmaintenance}[firstmaintenance_3]
13_Check Changed Discount With FirstMaintenance for Elevator    
    Get Target Price
14_Check Regional Factor At SalesOffice Level for Elevator
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    
15_Check Regional Factor When SalesOffice Is Changed for Elevator
    Goto Configuration Pageand Change The Sales Office
    Verify Regional Discount Displayed Correctly
    Verify Target Price Displayed Correctly    
16_Verify ITEFactorValue and LaborRateValue IsTaken From SalesOffice for Elevator
    # Goto Configuration Pageand Change The Primary Sales Office     ${country}    ${excelpath}
    Validate Detail Breakdown Tab
17_Verify ITEFactorValue and LaborRateValue IsTaken From Changed SalesOffice for Elevator
    Goto Configuration Pageand Change The Sales Office
    Validate Detail Breakdown Tab
18_Check Cost Calculated Correctly When The Tender Currency Is Different From SLCurrency for Elevator
    Verify Cost Calculated Successfully
19_Check Price Calculated Correctly When The Tender Currency Is Different From SLCurrency for Elevator    
    VerifyPriceCalculatedSuccessfully TobecheckedinFrance
20_Checking Maximum DiscountLimit Exceeded    
    Checking Maximum Discount Limit Exceeded    &{discount}[discount_3]
21_Check TenderLetter Is generated Correctly With All The components
    Go To Documents Taband Click The Tender
    Verify Successful Message Displayed
22_Close KTOC
    Click Saveand Close Button
23_Verify Total SalesPrice With SF Product Information
    Compare Sales Pricebetween Tender Pageand Salesforce
    Hand Shake
#23.VerifyOrderCreatedSuccessfullyForElevator
