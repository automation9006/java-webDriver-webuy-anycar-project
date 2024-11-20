package selectors;

import org.openqa.selenium.By;

public class HomePageSelectors {
    public  static final By  INPUT_SEARCH_CAR_REG =By.cssSelector("#vehicleReg");
    public  static final By  INPUT_SEARCH_MILEAGE =By.cssSelector("#Mileage");
    public  static final By  BUTTON_SEARCH =By.cssSelector("#btn-go");
    public  static final By TEXT_MANUFACTURER_NAME =By.xpath("//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[1]/div[2]");
    public  static final By TEXT_MODEL_NAME =By.xpath("//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[2]/div[2]");
    public  static final By TEXT_YEAR = By.xpath("//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[3]/div[2]");
    public  static final By TEXT_NO_REGISTRATION_FOUND = By.xpath("/html/body/div[1]/wbac-app/div[1]/div/div/vehicle-registration-check/section[1]/div/div[1]/div/div[1]/h1");
    public static final  By ALERT = By.cssSelector("#onetrust-button-group #onetrust-accept-btn-handler");
}
