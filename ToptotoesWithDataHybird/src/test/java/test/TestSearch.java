package test;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Report.Extend_Report;
import base.BaseTest;
import pages.SearchFuntionActions;
import utils.Excel_Util;
import utils.ScreenShotUtil;

public class TestSearch extends BaseTest{

      @DataProvider(name = "searchData")
      public Object[][] getSearchData() throws IOException, InvalidFormatException {
          Excel_Util excel = new Excel_Util("src/test/resources/Project4_Toptotoes.xlsx", "Search");
          int rowCount = excel.getRowCount();
          Object[][] data = new Object[rowCount - 1][3];
          for (int i = 1; i < rowCount; i++) {
              data[i - 1][0] = excel.getCellData(i, "Search");
              data[i - 1][1] = excel.getCellData(i, "Result");
              data[i - 1][2] = excel.getCellData(i, "Url");
          }
          return data;
      
      	}
	@Test(dataProvider = "searchData")
      public void testSearch (String search, String expectedResult, String Url) throws Exception {
          Extend_Report.startTest("Search Test - " + search);

          try {
              Excel_Util excelSteps = new Excel_Util("src/test/resources/Project4_toptotoes_Key.xlsx", "Search");
              int rowCount = excelSteps.getRowCount();

              SearchFuntionActions searchActions = new SearchFuntionActions(driver);

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
                      case "search":
                    	  searchActions.searchProductByEnterButton(search);                          break;
                      case "istextpresent":
                    	  searchActions.correctSearchPageDisplayed(expectedResult);
                    	  break;
                      case "isresultaftersearch":
                          searchActions.listProductSearched(search);
                          break;
                      case "verify url":
                    	  boolean verifyurrl = searchActions.verifyUrl(Url);
                    	  Thread.sleep(1000);
                    	  if (verifyurrl) {
                    		  String screenshotPath = ScreenShotUtil.captureScreenshot(driver, "testSearch_Exception", "SearchTest");
                              Extend_Report.attachScreenshot(screenshotPath);
                              Extend_Report.logPass("Search test passed for: " + search);
                          } 
                          break;
                      case "close browser":
                          break;
                      default:
                          throw new IllegalArgumentException("Unknown action: " + action);
                  }
                  
              }

          } catch (Exception e) {
              String screenshotPath = ScreenShotUtil.captureScreenshot(driver, "testSearch_Exception", "SearchTest");
              Extend_Report.attachScreenshot(screenshotPath);
              Extend_Report.logFail("Search test encountered an exception for: " + search + ". Exception: " + e.getMessage());
              throw e;
          } finally {
              Extend_Report.endReport();
          }
      }
      
      
	  

}
