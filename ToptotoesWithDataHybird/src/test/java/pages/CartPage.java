package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private WebDriver driver;

    @FindBy(xpath = "//*[@id='mini-cart']/div[1]/span[1]")
    public WebElement iconCart;

    @FindBy(xpath = "//*[@id='content']/article/div/div/div/p[1]")
    public WebElement msgCartEmpty;

    @FindBy(xpath = "//*[@id='content']/article/div/div/div/p[2]/a")
    public WebElement LinkComebackHome;

    @FindBy(xpath = "//div[@class='quantity buttons_added']/input[@type='number']")
    public List<WebElement> AmountAProduct;

    @FindBy(xpath = "//td[@product-price]//span[@class='woocommerce-Price-amount amount']")
    public List<WebElement> PriceAProduct;

    @FindBy(xpath = "//td[@class='product-subtotal text-center text-md-right']//span[@class='woocommerce-Price-amount amount']")
    public List<WebElement> TotalPriceAProduct;

    @FindBy(xpath = "//div[@class='quantity buttons_added']/button[@value='-']")
    public List<WebElement> AmountAProductwithIconSub;

    @FindBy(xpath = "//div[@class='quantity buttons_added']/button[@value='+']")
    public List<WebElement> AmountAProductwithIconAdd;

    @FindBy(xpath = "//*[@id='panel-cart-total']/div/div/a")
    public WebElement Pay;

    @FindBy(xpath = "//*[@id='panel-cart-discount']/button")
    public WebElement UpdateCart;

    @FindBy(css = ".remove")
    public List<WebElement> deleteProduct;
 
    @FindBy(xpath = "//div[@class='add-links clearfix']//a")
    public List<WebElement> AddProductAtHome;
    @FindBy(xpath = "//div[@class='inner']/img[@class='wp-post-image']")
    public List<WebElement>imgProduct;
    @FindBy(css = ".woofc-inner.woofc-cart-area")
    public WebElement divCart;
    @FindBy(xpath = "//div[@class='woofc-action-left']/a[@class='woofc-action-cart']")
    public WebElement btnComeBackCart;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
