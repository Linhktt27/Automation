Feature: Test Login
  Background:

  Scenario: Login with account no
    When I fill username is account no
    When I fill password of account no
    When I click on button Login by account no
    Then I login success by account no

  Scenario: Login with smartid
    When I fill username is smartid
    When I fill password of smartid
    When I click on button Login by smartid
    Then I login success by smartid

  Scenario: Login fail
    When I fill username invalid
    When I fill password
    When I click on button Login
    Then I login fail