Feature: Searching functionality with multiple data

  Background:
    Given User is on home page

  Scenario Outline: Home page car registration number search with valid Registrations
    When User search with specific car registration number "<carRegistration>" and mileage "<mileage>"
    Then I click button car valuation
    Then Search page is populated with car details which contains brand "<manufacturer>" "<model>" and "<year>"

    Examples:
      |carRegistration|mileage|manufacturer|       model                                                        |year|
      |AD58 VNF 	  | 50000 | BMW        |1 SERIES DIESEL COUPE - 120d M Sport 2dr                            | 2008|
      |BW57 BOF       | 50000 | TOYOTA     |YARIS HATCHBACK - 1.0 VVT-i T2 3dr                                  | 2008|
      |KT17 DLX       | 50000 | SKODA     |SUPERB DIESEL ESTATE - 2.0 TDI CR 190 Sport Line 5dr DSG             | 2017|

  Scenario Outline: Home page car registration number search with Invalid Registrations
    When User search with specific car registration number "<carRegistration>" and mileage "<mileage>"
    Then I click button car valuation
    Then Search page is populated with car details not found "<noRegistrationFound>"

    Examples:
      |carRegistration|mileage|noRegistrationFound|
      |BW57 BOW 	  | 50000 |Sorry, we couldn't find your car|
      |SG18 HTN        | 50000 |Sorry, we couldn't find your car|