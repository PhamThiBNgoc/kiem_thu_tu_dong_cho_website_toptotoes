package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private WebDriver driver;

    // Khai báo các đối tượng trên trang sẽ làm việc
    @FindBy(id = "reg_email")
    public WebElement txtUser;

    @FindBy(id = "reg_password")
    public WebElement txtPass;
    
    @FindBy(name = "register")
    public WebElement btnRegister;
    
    @FindBy(css = ".mb-4")
    @CacheLookup
    public WebElement msgRegisterPass;
  
    @FindBy(css = ".woocommerce-error")
    @CacheLookup
    public WebElement msgRegisterFail;
    
    // Replace compound class names with cssSelector
    @FindBy(css = ".woocommerce-password-strength")
    @CacheLookup
    public WebElement msgRegisterPassStrength;
    
    @FindBy(css = ".woocommerce-password-hint")
    @CacheLookup
    public WebElement msgNote;
    
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
