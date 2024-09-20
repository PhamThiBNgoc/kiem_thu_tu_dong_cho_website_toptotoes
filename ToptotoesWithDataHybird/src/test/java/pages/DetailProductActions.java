package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailProductActions {
    private WebDriver driver;
    private DetailProductPage detailProductPage;
    private SearchFuntionActions searchFuntionActions; // Added here

    // Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong class này đọc
    public DetailProductActions(WebDriver driver) {
        this.driver = driver;
        this.detailProductPage = new DetailProductPage(driver);
        this.searchFuntionActions = new SearchFuntionActions(driver); // Initialize here
    }

    // Thêm sản phẩm vào giỏ hàng từ trang chi tiết sản phẩm
    public void AddToCartPageProductDetails() throws InterruptedException {
        // Tìm kiếm sản phẩm
        searchFuntionActions.searchProductByEnterButton("dưỡng ẩm");
        
        // Đợi để sản phẩm xuất hiện sau khi tìm kiếm
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(detailProductPage.imgProduct));
        
        // Di chuột đến sản phẩm tại vị trí thứ 0 trong danh sách
        Actions action = new Actions(driver);
        action.moveToElement(detailProductPage.imgProduct.get(0)).perform();
        
        // Thực hiện click vào ảnh đã chỉ định để chuyển đến trang chi tiết sản phẩm
        detailProductPage.imgProduct.get(0).click();
        
        // Đợi trang chi tiết sản phẩm được tải hoàn toàn
        wait.until(ExpectedConditions.visibilityOf(detailProductPage.btnCartPageDetailProduct));
        // Click vào button thêm sản phẩm
        action.moveToElement(detailProductPage.imgProduct.get(0)).perform();
        detailProductPage.btnCartPageDetailProduct.click();
        Thread.sleep(5000);
        // Đợi cho giỏ hàng có sản phẩm xuất hiện
        wait.until(ExpectedConditions.visibilityOf(detailProductPage.divCart));
        
        // Trở về giỏ hàng
        detailProductPage.btnComeBackCart.click();
    }
}
