#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Login to OrangeHRM
  Verify Login Scenario for all possible Scenarios.

@tag1
Scenario: Verifying Login Scenarion for Positive scenario
    Given I am on OrangeHRM application
    When I enter UserName "Admin" and Password "admin123"
    And I click on login button
    Then I validate the outcomes

