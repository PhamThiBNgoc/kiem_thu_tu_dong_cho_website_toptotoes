package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchFunctionPage {
	private WebDriver driver;
	// Khai báo các đối tượng trên trang sẽ làm việc
    @FindBy(xpath = "//div[@class='header-col header-right hidden-for-sm']//a[.='Search']")
    public WebElement iconSearch;
    
    @FindBy(xpath = "//div[@class='header-col header-right hidden-for-sm']//input[@name='s']")
    public WebElement txtSearch;
    
    @FindBy(css = ".btn btn-special")
    public WebElement btnSearch;
  
    @FindBy(xpath = "//span[@class='breadcrumb_last']")
    public WebElement labelProductSearch;
  
    @FindBy(xpath = "//ul[@class='products']/li[contains(@class, 'product-col')]")
    public List<WebElement> listProductSearched;
    
    @FindBy(css = ".woocommerce-loop-product__title")
    public List<WebElement> listLabelProduct;
    
    @FindBy(xpath = "//*[@id=\"content\"]/div[2]/p")
    public WebElement notificationNotFindProduct;
  
  
//    @FindBy(name = "//*[@id=\"nav-menu-item-2796\"]/a")
//    public WebElement categoryProduct;
//    
//    @FindBy(name = "//*[@id=\"nav-menu-item-11517\"]")
//    public WebElement productForHair;
    
  
//    @FindBy(name = "orderby")
//    public WebElement dropdownSortAfterSearch;
    
	public SearchFunctionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	

}
