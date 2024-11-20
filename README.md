package common;

import static base.DriverSetup.*;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
features = {"src/test/resources/"},
format = {"pretty", "html:target/cucumber", "json:target/cucumber.json"}
)
public class RunCukesTest {

    @After
    public static void tearDownClass() {
        // Close web driver
        closeDriver();
    }

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            // Start a new webdriver to call quit on
            // For IE this will terminate all webdriver sessions
            quitDriver();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }
}
