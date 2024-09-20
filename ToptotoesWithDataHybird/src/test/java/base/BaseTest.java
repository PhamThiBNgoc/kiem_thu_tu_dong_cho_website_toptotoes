package base;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import Report.Extend_Report;
import utils.ConfigUtil;

public class BaseTest {
    protected WebDriver driver;
    String configFile = "src/test/resources/config.properties";

    @BeforeSuite
    public void startReport() {
        Extend_Report.startReport();
    }

    @BeforeMethod
    public void setUp() throws IOException {
        ConfigUtil config = new ConfigUtil(configFile);
        DriverManager driverManager = new DriverManager();
        String browser = config.getProperty("browser");
        driver = driverManager.initDriver(browser);
    }

    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    @AfterSuite
    public void endReport() {
        Extend_Report.endReport();
    }
}
