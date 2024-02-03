Feature: Test Money Tranfer
  Background:

  Scenario: TF01_Transfer internal
    When Internal_I access to Money Transfer
    When Internal_I choose a bank internal and fill account number, amount
    When Internal_I click Continue two times
    When Internal_I fill otp and click Continue
    Then Internal_I transfer internal success

  Scenario: TF02_Transfer napas
    When Napas_I access to Money Transfer
    When Napas_choose a bank napas and fill account number, amount
    When Napas_I click Continue two times
    When Napas_I fill otp and click Continue
    Then Napas_I transfer napas success


  Scenario: TF03_Transfer citad
    When Citad_I access to Money Transfer
    When Citad_I choose a bank citad and fill account number, amount
    When Citad_I click Continue two times
    When Citad_I fill otp and click Continue
    Then Citad_I transfer citad success

  Scenario: TF04_Transfer To other’s VPS trading accounts
    When CKNB_I access to Money Transfer
    When CKNB_I choose tab To internal VPS's account
    When CKNB_I choose option To other’s VPS trading accounts
    When CKNB_I fill Account number and Transfer amount
    When CKNB_I click Continue two times
    When CKNB_I fill otp and click Continue
    Then CKNB_I transfer to other’s VPS trading accounts success


  Scenario: TF05_Transfer Between your sub-accounts
    When CKSTK_I access to Money Transfer
    When CKSTK_I choose tab To internal VPS's account
    When CKSTK_I choose option Between your sub-accounts
    When CKSTK_I choose Account number and Transfer amount
    When CKSTK_I click Continue two times
    Then CKSTK_I transfer between your sub-accounts success