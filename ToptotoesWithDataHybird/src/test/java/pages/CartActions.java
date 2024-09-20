package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartActions {
	private WebDriver driver;
    private CartPage cartPage;

    public CartActions(WebDriver driver) {
        this.driver = driver;
        this.cartPage = new CartPage(driver);
    }
 // Thêm sản phẩm vào giỏ hàng từ trang chi tiết sản phẩm
 // Thêm sản phẩm vào giỏ hàng từ trang chi tiết sản phẩm
    public void AddToCartPageProductDetails() throws InterruptedException {
        // Tạo đối tượng Actions để di chuột
        Actions action = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Cuộn đến sản phẩm tại vị trí thứ 0 trong danh sách
        WebElement productElement = cartPage.imgProduct.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productElement);

        // Di chuột đến sản phẩm
        action.moveToElement(productElement).perform();

        // Chờ cho nút "Thêm vào giỏ hàng" xuất hiện
        wait.until(ExpectedConditions.visibilityOf(cartPage.AddProductAtHome.get(0)));

        // Thực hiện click vào nút "Thêm vào giỏ hàng"
        cartPage.AddProductAtHome.get(0).click();

        // Đợi cho giỏ hàng có sản phẩm xuất hiện
        wait.until(ExpectedConditions.visibilityOf(cartPage.divCart));

        // Trở về giỏ hàng
        cartPage.btnComeBackCart.click();
    }
//        Kiểm tra nút iconCart có xuất hiện
     
    public boolean iconCartDisplayed() throws InterruptedException {
    	SearchFuntionActions SearchFuntion = new SearchFuntionActions(driver);
    	SearchFuntion.searchProduct("dưỡng ẩm");
        return cartPage.iconCart.isDisplayed();
    }
//    Click giỏ hàng và trả về thông báo giỏ hàng rỗng
    public Object[] emptyCartClick() throws InterruptedException {
        Thread.sleep(1000);
        cartPage.iconCart.click();
        Thread.sleep(1000);
        
        String emptyCartMessage = cartPage.msgCartEmpty.getText();
        boolean comebackButtonDisplayed = cartPage.LinkComebackHome.isDisplayed();

        // Trả về một mảng Object chứa cả hai giá trị
        return new Object[]{emptyCartMessage, comebackButtonDisplayed};
    }

//  thực hiện nhập số lượng sản phẩm
//    - Lấy số lượng tối đa
    public int getMaxQuantity(int productIndex) {
        String maxQuantity = cartPage.AmountAProduct.get(productIndex).getAttribute("max");
        return Integer.parseInt(maxQuantity);
        
    }
//  - Lấy số lượng tối đa
  public int getMinQuantity(int productIndex) {
      String minQuantity = cartPage.AmountAProduct.get(productIndex).getAttribute("min");
      return Integer.parseInt(minQuantity);
      
  }
//    - Thiết lập số lượng sản phẩm với tham số đầu vào: chỉ số sản phẩm, số lượng sản phẩm
    public void setProductQuantity(int productIndex, int quantity) {
        WebElement quantityInput = cartPage.AmountAProduct.get(productIndex);
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
//        so sánh xem số lượng có được cập nhập
        String newQuantityValue = quantityInput.getAttribute("value");
        Assert.assertEquals(newQuantityValue, String.valueOf(quantity));

    }
 // 	-Kiểm tra giá tiền và số lượng
    public void verifyQuantityAndPriceUpdate(int productIndex, int quantity) {
        // Get the current quantity and price before update
        String oldQuantity = cartPage.AmountAProduct.get(productIndex).getAttribute("value");
        double Price = Double.parseDouble(cartPage.PriceAProduct.get(productIndex).getText().replaceAll("[^\\d.]", ""));

        // Set the new quantity
        setProductQuantity(productIndex, quantity);

        // Get the updated quantity and price
        String updatedQuantity = cartPage.AmountAProduct.get(productIndex).getAttribute("value");
        double updatedPrice = Double.parseDouble(cartPage.TotalPriceAProduct.get(productIndex).getText().replaceAll("[^\\d.]", ""));

        // Verify the quantity is updated correctly
        Assert.assertEquals(Integer.parseInt(updatedQuantity), quantity);

        // Verify the price is updated correctly
        double expectedPrice = Price * Integer.parseInt(updatedQuantity);
        Assert.assertEquals(updatedPrice, expectedPrice, "The total price is not updated correctly.");
    }
    public void ImportAmountProduct(int productIndex, int quantity){
		int maxQuantity = getMaxQuantity(productIndex);
		int minQuantity = getMinQuantity(productIndex);
        setProductQuantity(productIndex,quantity);
        WebElement quantityInput = cartPage.AmountAProduct.get(productIndex);
    	if(quantity > maxQuantity) {
    		String validationMessage = quantityInput.getAttribute("validationMessage");
            Assert.assertTrue(validationMessage.contains("Value must be less than or equal to " + maxQuantity));
    		
    	}
    	else if(quantity < minQuantity) {
    		String validationMessage = quantityInput.getAttribute("validationMessage");
            Assert.assertTrue(validationMessage.contains("Value must be greater than or equal to " + minQuantity));
    		
    	}
    	else if(quantity == minQuantity) {
    		boolean productExists = cartPage.AmountAProduct.size() > productIndex;
            Assert.assertFalse(productExists, "Product should be removed from the cart.");
    	}
    	else {
    		verifyQuantityAndPriceUpdate(productIndex, quantity);
    	}
    	
    }
    



  
//  thực hiện nhấn tăng giảm 
//  thực hiện nhập số lượng
//    kiểm tra xem nút tiến hành thanh toán có xuất hiện
    public boolean verifyProceedToCheckOutButton(){
        return cartPage.Pay.isDisplayed();
    }
    public boolean verifyProceedToUpdateCartButton(){
        return cartPage.UpdateCart.isDisplayed();
    }

    
//    xoá sản phẩm ra khỏi giỏ hàng
    public void removingItemsFromCart() throws InterruptedException {
        Thread.sleep(1200);
        cartPage.deleteProduct.get(0).click();
        Thread.sleep(1200);
        
    }
  

}
