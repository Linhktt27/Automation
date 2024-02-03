Feature: Test place an order to buy even
  Background:
    Given I click on button Buy

  Scenario Outline: Place an order to buy even
    When ORD<STT>_I fill symbol <san>, price and even lot volume
    When ORD<STT>_I click on button Order
    When ORD<STT>_At popup Order confirmation, I click on button Confirm
    Then ORD<STT>_I place an order to buy even lot on <san> success, new order show on Daily Orders, amount blocked is correct
    Examples:
      | STT  | san   |
      | '01' | HSX   |
      | '02' | HNX   |
      | '03' | UPCOM |

  Scenario Outline: Place an order market price to buy even
    When ORD<STT>_I fill symbol <san>, price <price> and even lot volume
    When ORD<STT>_I click on button Order
    When ORD<STT>_At popup Order confirmation, I click on button Confirm
    Then ORD<STT>_I place an order to buy even lot on <san>, price <price> success, new order show on Daily Orders, amount blocked is correct
    Examples:
      | STT  | san | price |
      | '04' | HSX | MP    |
      | '05' | HSX | ATC   |
      | '06' | HNX | MTL   |
      | '07' | HNX | MOK   |
      | '08' | HNX | MAK   |
      | '09' | HNX | ATC   |
      | '10' | HNX | PLO   |