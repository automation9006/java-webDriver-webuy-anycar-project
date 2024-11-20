package base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.lang.System.getProperty;

public class DriverSetup {
    public String browser;
    public String envName;
    public String appName;

    public String url = getProperty("url");
    public String configFile = CurrentDirectory() + "/config.properties";
    public String configProperty;
    /**handle to the webdriver */
    protected  static WebDriver driver = null;

    // Wait for AJAX if needed
    protected int secondsToWait = 1000;

    /**
     *  Get the driver with devices
     */
    public WebDriver getDriver() {
        browser = ReadPropertiesFile("browser");
        envName = System.getProperty("envName","local");
        appName = System.getProperty("appName","we buy any car");
        if (null != driver) {
            return driver;
        }

        if (envName.equalsIgnoreCase("local")) {
            String driverDirectory = System.getProperty("user.dir")+"\\drivers\\";
            String driverLocation = System.getProperty("driverLocation",driverDirectory);
            String machineType = System.getProperty("machineType","");

            if (browser.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", getProperty("user.dir") + "/drivers/chromedriver");
                /* Chrome Local */
                if (machineType.equalsIgnoreCase("mac")) {
                    System.setProperty("webdriver.chrome.driver", getProperty("user.dir") + "/drivers/chromedriver");
                }
                else {
                    System.setProperty("webdriver.chrome.driver", driverLocation + "chromedriver.exe");
                }
                driver = new ChromeDriver();

            } else if (browser.equalsIgnoreCase("ie")) {

                /* IE Local */
                System.setProperty("webdriver.ie.driver", driverLocation + "IEDriverServer.exe");
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                ieCapabilities.setCapability("ignoreZoomSetting", true);
                driver = new InternetExplorerDriver(ieCapabilities);

            } else if (browser.equalsIgnoreCase("safari")) {
                /* Safari Driver */
                driver = new SafariDriver();
            } else if (browser.equalsIgnoreCase("firefox")) {
                /* Firefox */
                driver = new FirefoxDriver();
            }
        } else {
            // default chrome
            DesiredCapabilities capabillities = DesiredCapabilities.chrome();
            /* Remote Testing Bot */
            /* All supporting browsers: https://api.testingbot.com/v1/browsers*/

            if (browser.equalsIgnoreCase("ie8")) {
                // iexplorer 8
                capabillities = DesiredCapabilities.internetExplorer();
                capabillities.setCapability("version", "8");
                capabillities.setCapability("platform", Platform.WINDOWS);
                capabillities.setCapability("name", appName + " - Automated Testing (IE8)");
            } else if (browser.equalsIgnoreCase("ie9")) {
                // iexplorer 9
                capabillities = DesiredCapabilities.internetExplorer();
                capabillities.setCapability("version", "9");
                capabillities.setCapability("platform", Platform.WINDOWS);
                capabillities.setCapability("name", appName + " - Automated Testing (IE9)");
            } else if (browser.equalsIgnoreCase("ie10")) {
                // iexplorer 10
                capabillities = DesiredCapabilities.internetExplorer();
                capabillities.setCapability("version", "10");
                capabillities.setCapability("platform", Platform.WINDOWS);
                capabillities.setCapability("name", appName + " - Automated Testing (IE10)");
            } else if (browser.equalsIgnoreCase("firefox")) {
                // Windows FireFox
                capabillities = DesiredCapabilities.firefox();
                capabillities.setCapability("version", "31");
                capabillities.setCapability("platform", Platform.WINDOWS);
                capabillities.setCapability("name", appName + " - Automated Testing (Firefox)");
            } else if (browser.equalsIgnoreCase("safari")) {
                // safari
                capabillities = DesiredCapabilities.safari();
                capabillities.setCapability("version", "6");
                capabillities.setCapability("platform", Platform.MAC);
                capabillities.setCapability("name", appName + " - Automated Testing (Safari)");
            } else if (browser.equalsIgnoreCase("iphone")) {
                // iphone
                capabillities = DesiredCapabilities.iphone();
                capabillities.setCapability("platform", Platform.MAC);
                capabillities.setCapability("name", appName + " - Automated Testing (iPhone)");
            } else if (browser.equalsIgnoreCase("ipad")) {
                // ipad
                capabillities = DesiredCapabilities.ipad();
                capabillities.setCapability("platform", Platform.MAC);
                capabillities.setCapability("name", appName + " - Automated Testing (iPad)");
            } else {
                // Windows Google Chrome
                capabillities = DesiredCapabilities.chrome();
                capabillities.setCapability("platform", Platform.WINDOWS);
                capabillities.setCapability("name", appName + " - Automated Testing (Chrome)");
            }
            try {
                String remoteWebDriver = getProperty("remoteWebDriver");
                driver = new RemoteWebDriver(new URL(remoteWebDriver), capabillities);
                // http://www.seleniumhq.org/docs/04_webdriver_advanced.jsp#implicit-waits
                // Wait for DOM to avoid `stale element reference: element is not attached to the page document`

            } catch (Exception e) {

            }
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    public String getSiteBaseUrl() {
        return url;
    }

    /**
     * Close Driver: delete all cookies for new session, tearDownClass() will handle closing the driver
     */
    public static void closeDriver() {
        if (null != driver) {
            driver.manage().deleteAllCookies();
        }
    }
    /**
     * Quit Driver: tearDownClass() will handle closing the driver
     */
    public static void quitDriver() {
        if (null != driver) {
            driver.quit();
        }
    }

    public  String CurrentDirectory() {
        String CurDir = System.getProperty("user.dir");
        return CurDir;
    }
    public  String getConfigFile() {
        return configFile;
    }
    public  String getProperty(String Property) {
        if (System.getProperty(Property) == null ) {
            configProperty = ReadPropertiesFile(Property);
        } else {
            configProperty = SetPropertiesFile(getConfigFile(), Property, System.getProperty(Property).toLowerCase());
        }
        return configProperty;
    }
    public  String ReadPropertiesFile(String key) {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(CurrentDirectory() + "/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
    public  String SetPropertiesFile(String fileName, String key, String value) {
        Properties prop = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prop.setProperty(key, value);
        return prop.getProperty(key);
    }

}
