*** Settings ***
Library    com.KTOC.TRB.testautomation.test_Run.testRun_KTOCTRB
Suite Setup    Launch Browser    windows    ff
*** Variable ***
${browser}    ff
${opportunity}    KOFCOL TRB SFA
${ProductRelease}    1723
${equipmentid}    10503512
${equipmentADDorChange}    change
${salesoffice}    VB FRPP
${MS5HODatetoChange}    1
${equipmentinService}    DOC Door
${supervisorResponsiblePerson}    06200428
${template}    Automation_Template_002
${FirstMaintenance}    0
${discount}    10
${tenderPrice}    2002
${FreezePrintedVersion}    No
${SaveandClose}    Yes
${StageProbabilityStage}    Budget Price
${StageProbabilityDescription}    Automation Test Description
${StageProbabilityProbability}    22
*** Test Cases ***
1.Logon To Salesforce
    Logon To Salesforce    s.vijay@kone.com.qa    Vijay1234
2.Verify Creating Opportunity and Mapping It With FLTender    
    Create Opportunity OR Search Opportunity    KOFCOL TRB SFA
3.Verify Tender Created Successfully    
    Add Equipment ID Elevator    1723    change
    Check Hand Over Date Is Greater Than Installation Date
    Check Sales Officeis Selected    VB FRRW
    Select Supervisor    06114080
    Select Equipment In Service
    Select Template To Be Uploaded    Automation_Template_002
    Verify Tender Consistency
    Get Tender No
4.Validate TenderPrice and Discount Without FirstMaintenance    
    Pricing Icon Click
    Check Tender Price After Discount Update    10    0
    Get Target Price
    Verify Discount By Changing The Tender Price    2001    0
    Get Target Price
5.Validate TenderPrice and Discount With FirstMaintenance    
    Check Tender Price After Discount Update    11    3
    Get Target Price
    Verify Discount By Changing The Tender Price    3000    3
    Get Target Price    
    Verify Discount By Changing The Tender Price    2500    6
    Get Target Price    
# 6.CheckRegionalFactorAtSalesOfficeLevel

# 7.CheckRegionalFactorWhenSalesOfficeIsChanged
    
8 & 9 Verify ITEFactorValue and LaborRateValue IsTaken From SalesOffice    
    Validate Detail Breakdown Tab
    Goto Configuration Pageand Change The Sales Office    VB FRPP
    Validate Detail Breakdown Tab
    
# 10.CheckCostAndPriceCalculatedCorrectlyWhenTheTenderCurrencyIsDifferentFromSLCurrency
    
11.Check TenderLetter Is generated Correctly With All The components
    Go To Documents Taband Click The Tender
    Verify Successful Message Displayed
12.Close KTOC
    Click Saveand Close Button    Automation Test Description    22
13.Verify Total SalesPrice With SF Product Information
    Get Sales Price From Sales Force
    Hand Shake
# 14.VerifyOrderCreatedSuccessfullyForElevator