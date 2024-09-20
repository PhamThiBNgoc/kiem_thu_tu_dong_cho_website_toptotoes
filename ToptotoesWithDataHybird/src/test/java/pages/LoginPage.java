package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;

    // Khai báo các đối tượng trên trang sẽ làm việc
    @FindBy(id = "username")
    @CacheLookup
    public WebElement txtUser;

    @FindBy(id = "password")
    @CacheLookup
    public WebElement txtPass;
    
    @FindBy(name = "login")
    @CacheLookup
    public WebElement btnLogin;
    
    @FindBy(css = ".mb-4")
    @CacheLookup
    public WebElement msgLoginPass;
  
    @FindBy(css = ".woocommerce-error")
    @CacheLookup
    public WebElement msgLoginFail;
    
    @FindBy(xpath = "//a[.='Quên mật khẩu?']")
    @CacheLookup
    public WebElement linkForgot;
    
    @FindBy(xpath = "//label[@class='porto-control-label no-radius']")
    @CacheLookup
    public WebElement checkBoxPass;
    
    @FindBy(xpath = "//li[@class='woocommerce-MyAccount-navigation-link woocommerce-MyAccount-navigation-link--customer-logout']/a[.='Thoát']")
    @CacheLookup
    public WebElement Exit;
  
    @FindBy(xpath = "//*[@id=\"customer_login\"]/div[1]/form/div[3]/a[1]")
    @CacheLookup
    public WebElement LoginWithFacebook;
    
    @FindBy(xpath = "//*[@id=\"customer_login\"]/div[1]/form/div[3]/a[2]")
    @CacheLookup
    public WebElement LoginWithGoogle;
    
    @FindBy(xpath = "//*[@id=\"header\"]/div[1]/div/div[3]/ul/li/a")
    @CacheLookup
    public WebElement CheckAccountExistAfterCloseWeb;
    // Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong class này đọc


	public LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
        PageFactory.initElements(driver, this);
	}

}
