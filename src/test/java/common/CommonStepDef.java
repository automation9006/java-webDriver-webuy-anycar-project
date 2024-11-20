package common;

import base.DriverSetup;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import selectors.HomePageSelectors;

import static org.junit.Assert.assertTrue;

public class CommonStepDef extends DriverSetup {
    WebDriver driver = getDriver();
    CommonFunctions commonMethods = new CommonFunctions();

    @Given("^User is on home page$")
    public void userIsOnHomePage() throws Exception {
        driver.get(getSiteBaseUrl());
        if(!driver.findElements(HomePageSelectors.ALERT).isEmpty()){
            // Then click on the submit button
            commonMethods.clickOnElement(HomePageSelectors.ALERT);
        }else{
            // Do something else as submit button is not there
            System.out.println("****:: Alert not visible ::****");
        }
        assertTrue(driver.getTitle().contains("Sell your car in under an hour | Buy my car | webuyanycar"));
    }

    @When("^User search with specific car registration number \"([^\"]*)\" and mileage \"([^\"]*)\"$")
    public void userSearchWithSpecificCarRegistrationNumberAndMileage(String carReg, String mileage) throws Throwable {
        commonMethods.sendKeys(HomePageSelectors.INPUT_SEARCH_CAR_REG,carReg);
        commonMethods.sendKeys(HomePageSelectors.INPUT_SEARCH_MILEAGE,mileage);
    }

    @Then("^I click button car valuation$")
    public void iClickButtonCarValuation() throws Exception {
        commonMethods.clickOnElement(HomePageSelectors.BUTTON_SEARCH);
    }

    @Then("^Search page is populated with car details which contains brand \"([^\"]*)\" \"([^\"]*)\" and \"([^\"]*)\"$")
    public void searchPageIsPopulatedWithCarDetailsWhichContainsBrandAnd(String manufacturer, String model, String year) throws Throwable {
        commonMethods.assertTextPresent(HomePageSelectors.TEXT_MANUFACTURER_NAME,manufacturer);
        commonMethods.assertTextPresent(HomePageSelectors.TEXT_MODEL_NAME,model);
        commonMethods.assertTextPresent(HomePageSelectors.TEXT_YEAR,year);
    }

    @Then("^Search page is populated with car details not found \"([^\"]*)\"$")
    public void searchPageIsPopulatedWithCarDetailsNotFound(String noRegistrationFound) throws Throwable {
        commonMethods.assertTextPresent(HomePageSelectors.TEXT_NO_REGISTRATION_FOUND,noRegistrationFound);
    }
}
