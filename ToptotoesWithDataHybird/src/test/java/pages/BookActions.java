package pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookActions {
    private WebDriver driver;
    private BookPage bookPage;

    public BookActions(WebDriver driver) {
        this.driver = driver;
        this.bookPage = new BookPage(driver); 
    }

    // Phương thức chọn ngẫu nhiên từ bất kỳ dropdown nào
    public void selectRandomOptionFromDropdown(WebElement dropdown) throws InterruptedException {
        if (dropdown != null) {
            Select select = new Select(dropdown);
            List<WebElement> options = select.getOptions();

            if (options.size() > 0) {
                Random random = new Random();
                int randomIndex = random.nextInt(options.size());
                select.selectByIndex(randomIndex);
                System.out.println("Selected Option: " + options.get(randomIndex).getText());
                Thread.sleep(1000);
            } else {
                System.out.println("No options available to select.");
            }
        } else {
            System.out.println("Dropdown element is null.");
        }
    }

    // Kiểm tra nếu một phần tử được hiển thị trong thời gian nhất định
    private boolean isElementDisplayed(WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Phương thức để xóa giá trị của trường ChooseDateShowRoom
    public void clearDatePicker() {
        bookPage.ChooseDateShowRoom.clear();
    }

    // Phương thức để thiết lập giá trị ngày tháng cho trường ChooseDateShowRoom
    public void setDatePickerValue(String dateValue) {
        clearDatePicker(); // Xóa giá trị cũ trước khi thiết lập giá trị mới
        bookPage.ChooseDateShowRoom.sendKeys(dateValue);
    }

    // Sử dụng phương thức chọn ngẫu nhiên cho dropdown SkinType
    public void selectRandomSortOptionSkinType() throws InterruptedException {
        selectRandomOptionFromDropdown(bookPage.dropdownSkinType);
    }

    // Sử dụng phương thức chọn ngẫu nhiên cho dropdown ProblemWorry
    public void selectRandomSortOptionProblemWorry() throws InterruptedException {
        selectRandomOptionFromDropdown(bookPage.dropdownProblemWorry);
    }

    // Sử dụng phương thức chọn ngẫu nhiên cho dropdown Showroom và ChooseDateShowRoom và dropdownChooseTimeShowRoom
    public void selectRandomSortOptionShowRoom(String dateValue) throws InterruptedException {
        selectRandomOptionFromDropdown(bookPage.dropdownChooseShowRoom);
        if (isElementDisplayed(bookPage.ChooseDateShowRoom, 10)) {
            setDatePickerValue(dateValue);
            if (isElementDisplayed(bookPage.dropdownChooseTimeShowRoom, 10)) {
                selectRandomOptionFromDropdown(bookPage.dropdownChooseTimeShowRoom);
            } 
        }
    }

    // Phương thức sử dụng sendKeys để nhập liệu vào các trường thông tin
    public void Book(String name, String phone, String email, String dateValue) throws InterruptedException {
    	bookPage.BookNow.click();
    	bookPage.BookHigh_Level.click();
    	selectRandomSortOptionSkinType();
    	selectRandomSortOptionProblemWorry();
    	selectRandomSortOptionShowRoom(dateValue);
        bookPage.txtName.sendKeys(name);
        Thread.sleep(1000);
        bookPage.txtPhone.sendKeys(phone);
        Thread.sleep(1000);
        bookPage.txtEmail.sendKeys(email);
        Thread.sleep(1000);
        bookPage.btnBook.click();
    }
}
