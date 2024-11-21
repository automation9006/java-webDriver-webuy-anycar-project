package common;

import static base.DriverSetup.*;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber-html-report","json:target/cucumber.json"},
//        format = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        features = {"src/test/resources/"},
        glue = { },
        tags = { }
)

public class RunCukesTest {

        @AfterClass
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