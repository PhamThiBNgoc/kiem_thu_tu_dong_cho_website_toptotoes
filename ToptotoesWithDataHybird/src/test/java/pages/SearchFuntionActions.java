package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class SearchFuntionActions {
	private WebDriver driver;
	Actions actions;
    private SearchFunctionPage searchFunctionPage;

	public SearchFuntionActions(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
        this.searchFunctionPage = new SearchFunctionPage(driver);
	}
//	Thực hiện tìm kiếm sản phẩm bằng button
	public void searchProduct(String product) throws InterruptedException {
		searchFunctionPage.iconSearch.click();
		searchFunctionPage.txtSearch.clear();
        searchFunctionPage.txtSearch.sendKeys(product);
        Thread.sleep(1000);
        searchFunctionPage.btnSearch.click();
    }
//	Thực hiện tìm kiếm sản phẩm bằng enter
	public void searchProductByEnterButton(String product) throws InterruptedException {
		searchFunctionPage.iconSearch.click();
        searchFunctionPage.txtSearch.clear();
//        searchFunctionPage.txtSearch.sendKeys(product);
     // Use JavaScript to set the value of the input field
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='" + product + "';", searchFunctionPage.txtSearch);
        Thread.sleep(1000);
        searchFunctionPage.txtSearch.sendKeys(Keys.ENTER);
    }
//	Kiểm tra kết quả tìm kiếm có đúng không
	
	public boolean correctSearchPageDisplayed(String expectedText) throws InterruptedException {
	    Thread.sleep(1200);
	    String getLabelProduct = searchFunctionPage.labelProductSearch.getText();
	    return getLabelProduct.equals(expectedText);
	}
//	kiểm tra xem sản phẩm tìm kiếm trả về
	public void listProductSearched(String search) throws InterruptedException {
	    if (searchFunctionPage.listProductSearched.size() > 0) {
	        boolean allContainSearch = true;
	        for (WebElement result : searchFunctionPage.listProductSearched) {
	            if (!searchFunctionPage.labelProductSearch.getText().toLowerCase().contains(search.toLowerCase())) {
	                allContainSearch = false;
	                break;
	            }
	        }
	        Assert.assertTrue(allContainSearch, "Not all search results contain the search term.");
	    } else {
	        try {
	            String notificationText = searchFunctionPage.notificationNotFindProduct.getText();
	            Assert.assertTrue(notificationText.contains("Không tìm thấy sản phẩm nào khớp với lựa chọn của bạn."),
	                "Notification message mismatch.");
	        } catch (Exception e) {
	            // If no notification is found, assume we are on a product detail page
	            String getLabelProduct = searchFunctionPage.labelProductSearch.getText();
	            Assert.assertTrue(getLabelProduct.toLowerCase().contains(search.toLowerCase()),
	                "The product name on the detail page does not match the search term.");
	        }
	    }
	}

	// Method to verify URL
    public boolean verifyUrl(String Url) {
       
        return driver.getCurrentUrl().equals(Url);
    }
//	Chọn cách sắp xếp danh sách sản phẩm sau khi tìm kiếm và thực hiện so sánh Url sau khi chọn option
//	public void SortListProductAfterSearch(String option) throws InterruptedException {
//		actions.moveToElement(searchFunctionPage.categoryProduct).perform();
//		searchFunctionPage.productForHair.click();
//		Thread.sleep(3000);
//		Select select = new Select(searchFunctionPage.dropdownSortAfterSearch);
//        select.selectByVisibleText(option);
//
//	}
	

}
