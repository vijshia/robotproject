*** Settings ***
Default Tags    Regression Test
#Suite Setup   
#Suite Teardown    
Library  com.KTOC.TRB.testautomation.Keywords.Keywords
Resource  ../CustomKeywords/KTOCTRBCustomKeywords.robot
*** Test Cases ***
Verify Logon To Salesforce
  Logon To Salesforce
Verify Creating Opportunity and Mapping It With FLTender
  Create Opportunity
  Map Contact With Opportunity
  Map Opportunity With FL Tenders
# Verify Product Created Successfully
  # Switch To KTOC App
  # Select Product Platform
  # Select Sales Office
  # Select Template
  # Click Create Product Button
  # Get Tender Number
# Close KTOC
  # Save And Exit KTOC
# Verify Correct Product Information Stored In SalesForce
  # # Get Sales Price From KTOC Page
  # # Get Sales Price From Sales Force
  # Compare Total Sales Price