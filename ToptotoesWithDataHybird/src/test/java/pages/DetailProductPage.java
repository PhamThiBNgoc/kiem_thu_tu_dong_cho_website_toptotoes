package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DetailProductPage {
	private WebDriver driver;
	
    // Khai báo các đối tượng trên trang sẽ làm việc
    
	
    @FindBy(css = ".wp-post-image")
    public List<WebElement>imgProduct;
    @FindBy(xpath = "//button[@name='add-to-cart']")
    public WebElement btnCartPageDetailProduct;
    @FindBy(css = ".woofc-inner.woofc-cart-area")
    public WebElement divCart;
    @FindBy(xpath = "//div[@class='woofc-action-left']/a[@class='woofc-action-cart']")
    public WebElement btnComeBackCart;
    public DetailProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
