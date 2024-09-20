package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookPage {
	private WebDriver driver;
	// Khai báo các đối tượng trên trang sẽ làm việc
    @FindBy(xpath = "//a[@href='https://toptotoes.vn/dat-lich-soi-da/']")
    public WebElement BookNow;
    @FindBy(xpath = "//div[@class='elementor-tab-title elementor-tab-desktop-title elementor-active']")
    public WebElement BookHigh_Level;
    @FindBy(xpath = "//div[@class='wpforms-field-container']")
    public WebElement containerBookHigh_Level;
    @FindBy(xpath = "//*[@id=\"wpforms-3687-field_3\"]")
    public WebElement dropdownSkinType;
    @FindBy(xpath = "//*[@id=\"wpforms-3687-field_4\"]")
    public WebElement dropdownProblemWorry;
    @FindBy(xpath = "//*[@id=\"wpforms-3687-field_5\"]")
    public WebElement dropdownChooseShowRoom;
//    sau khi chọn địa chỉ soi mới xuất hiện chọn ngày
    @FindBy(xpath = "//*[@id=\"wpforms-3687-field_7\"]")
    public WebElement ChooseDateShowRoom;
//  sau khi chọn ngày xuất hiện chọn thời gian
    @FindBy(xpath = "//*[@id=\"wpforms-3687-field_12\"]")
    public WebElement dropdownChooseTimeShowRoom;
    @FindBy(xpath = "//*[@id=\"wpforms-3687-field_9\"]")
    public WebElement txtName;
    @FindBy(xpath = "//*[@id=\"wpforms-3687-field_10\"]")
    public WebElement txtPhone;
    @FindBy(xpath = "//*[@id=\"wpforms-3687-field_11\"]")
    public WebElement txtEmail;
  
    @FindBy(xpath = "//*[@id=\"wpforms-submit-3687\"]")
    public WebElement btnBook;
  
    public BookPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
