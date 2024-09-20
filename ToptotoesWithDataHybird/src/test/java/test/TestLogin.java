package test;

import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Report.Extend_Report;
import base.BaseTest;
import pages.LoginActions;
import utils.Excel_Util;
import utils.ScreenShotUtil;

public class TestLogin extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException, InvalidFormatException {
        Excel_Util excel = new Excel_Util("src/test/resources/Project4_Toptotoes.xlsx", "Login");
        int rowCount = excel.getRowCount();
        Object[][] data = new Object[rowCount - 1][3];
        for (int i = 1; i < rowCount; i++) {
            data[i - 1][0] = excel.getCellData(i, "Email");
            data[i - 1][1] = excel.getCellData(i, "Password");
            data[i - 1][2] = excel.getCellData(i, "Result");
        }
        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String email, String password, String expectedResult) throws Exception {
        Extend_Report.startTest("Login Test - " + email);

        try {
            Excel_Util excelSteps = new Excel_Util("src/test/resources/Project4_toptotoes_Key.xlsx", "Login");
            int rowCount = excelSteps.getRowCount();

            LoginActions loginActions = new LoginActions(driver);

            for (int i = 1; i < rowCount; i++) {
                String action = excelSteps.getCellData(i, "Action Keyword");
                String page = excelSteps.getCellData(i, "Page");
                String testData = excelSteps.getCellData(i, "Test Data");

                switch (action.toLowerCase()) {
                    case "open browser":
                        break;
                    case "navigate":
                        driver.get(testData);
                        break;
                    case "login":
                        loginActions.LoginAction(email, password);
                        break;
                    case "istextpresent":
                        boolean resultValid = loginActions.isNotificationMessagePresent(expectedResult);
                        Thread.sleep(1000);
                        if (resultValid) {
                            Extend_Report.logPass("Login test passed for: " + email);
                        } 
                        break;
                    case "close browser":
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown action: " + action);
                }
            }

        } catch (Exception e) {
            String screenshotPath = ScreenShotUtil.captureScreenshot(driver, "testLogin_Exception", "LoginTest");
            Extend_Report.attachScreenshot(screenshotPath);
            Extend_Report.logFail("Login test encountered an exception for: " + email + ". Exception: " + e.getMessage());
            throw e;
        } finally {
            Extend_Report.endReport();
        }
    }
}
