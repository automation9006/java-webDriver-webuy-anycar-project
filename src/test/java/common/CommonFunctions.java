package common;

import base.DriverSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class CommonFunctions extends DriverSetup {
    WebDriver driver = getDriver();

    /**
     * Select an element from drop list
     *
     * @param locator
     * @param option
     */
    public  void selectElement(By locator, String option) throws Exception {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(option);
    }

    /**
     * @param locator
     * @return
     */
    public  Boolean isElementEnabled(By locator) {
        return Boolean.valueOf(driver.findElement(locator).isEnabled());
    }
    /**
     * Method to refresh the current Page
     */
    public  void refreshPage() {
        Actions actionObject = new Actions(driver);
        actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
    }
    /**
     * Method to click on an Element
     *
     * @param locator
     */
    public  void clickOnElement(By locator) throws Exception {

        if(!driver.findElements(locator).isEmpty()){
            driver.findElement(locator).click();
        }
    }
    /**
     * Method to Send Keys
     *
     * @param locator
     * @param text
     */
    public void sendKeys(By locator, String text) throws Exception {
        if(!driver.findElements(locator).isEmpty()) {
            driver.findElement(locator).sendKeys(text);
        }
    }

    /**
     * getCss
     *
     * @return WebElement
     * @throws InterruptedException
     * @paramCss
     */
    public WebElement getCss(String Csskey) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.findElement(By.cssSelector(Csskey));
    }

    /**
     * Drag and Drop
     *
     * @param startpoint
     * @param endpoint
     * @throws InterruptedException
     */
    public void dragAndDrop(String startpoint, String endpoint) throws InterruptedException {
        WebElement startPoint = getCss(startpoint);
        WebElement endPoint = getCss(endpoint);

        Actions actionMan = new Actions(driver);
        actionMan.clickAndHold(startPoint).moveToElement(endPoint).perform();

        Thread.sleep(1000);
        actionMan.release().perform();
    }

    /**
     * double Click
     * @param elementToClick
     */
    public void doubleClickonElement(By elementToClick){
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(elementToClick)).doubleClick().build().perform();
    }

    /**
     * switch to iframe
     * @param locator
     */
    public void switchToIframe(String locator) {
        WebElement webelement = getCss(locator);
        driver.switchTo().frame(webelement);
    }
    /**
     * swithc to default content from iFrame
     */
    public void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }
    /**
     * Waits for alert to appear
     * @throws Exception
     */
    public void waitForAlert(int timer) throws Exception {
        {
            int i=0;
            while(i++<6)
            {
                try
                {
                    Alert alert = driver.switchTo().alert();
                    alert.accept();
                    break;
                }
                catch(NoAlertPresentException e)
                {
                    Thread.sleep(timer);
                    continue;
                }
            }
        }
    }

    /**
     * dismiss alert
     * @param timer
     * @throws Exception
     */
    public void waitAndDismissAlert(int timer) throws Exception {
        {
            int i=0;
            while(i++<30)
            {
                try
                {
                    Alert alert = driver.switchTo().alert();
                    alert.dismiss();
                    break;
                }
                catch(NoAlertPresentException e)
                {
                    Thread.sleep(timer);
                    continue;
                }
            }
        }
    }

    /**
     * assert Text Present
     *
     * @param locator
     * @param textToBeFound
     * @throws Exception
     */
    public void assertTextPresent(By locator, String textToBeFound) throws Exception {
        if(!driver.findElements(locator).isEmpty()) {
            assertTrue(driver.findElement(locator).getText().contains(textToBeFound));
        }
    }

    /**
     *
     * @param amount
     * @return
     */
    public boolean checkIfDecimal(String amount){
        return amount.matches("[0-9.]*");
    }

    /**
     * js.executeScript("window.scrollBy(0, 1000);");
     * @param x
     * @param y
     */
    public void jsExecutor(int x , int y){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        // Scroll down the page by 1000 pixels vertically
        js.executeScript("window.scrollBy("+x, y+");");
    }

    /**
     * waitForElementToBeClickable
     * @param locator
     */
     public void  waitForElementToBeClickable(By locator){
         WebDriverWait wait = new WebDriverWait(
         driver, 20); // Maximum wait time of 10 seconds
         wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

     }
}
