package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import Report.Extend_Report;
import base.BaseTest;
import pages.CartActions;
import pages.DetailProductActions;
import utils.Excel_Util;
import utils.ScreenShotUtil;

public class TestCart extends BaseTest {
    private DetailProductActions detailProductActions;
    private CartActions cartActions;

    // DataProvider cung cấp dữ liệu cho các test
    @DataProvider(name = "CartData")
    public Object[][] getCartData() {
        // Khởi tạo đối tượng cartActions trong phương thức này không hợp lý
        // Bạn cần khởi tạo cartActions trong phương thức testAddProductToCart
        // Nhưng nếu bạn đã có dữ liệu về số lượng tối đa, bạn có thể bỏ qua bước khởi tạo này ở đây

        // Lấy maxQuantity từ cartActions
        int productIndex = 0;
        int maxQuantity = cartActions.getMaxQuantity(productIndex);

        return new Object[][] {
            {-1}, {0}, {1}, {maxQuantity - 1}, {maxQuantity}, {maxQuantity + 1}
        };
    }

    // Test để thêm sản phẩm vào giỏ hàng
    @Test
    public void testAddProductToCart() throws Exception {
        detailProductActions = new DetailProductActions(driver);
        cartActions = new CartActions(driver); // Khởi tạo cartActions ở đây để sử dụng trong getCartData
        Extend_Report.startTest("Add Product to Cart Test");

        try {
            driver.get("https://toptotoes.vn/");
            detailProductActions.AddToCartPageProductDetails(); // Thực hiện thêm sản phẩm vào giỏ hàng
        } catch (Exception e) {
            String screenshotPath = ScreenShotUtil.captureScreenshot(driver, "testAddProductToCart_Exception", "CartTest");
            Extend_Report.attachScreenshot(screenshotPath);
            Extend_Report.logFail("Add product to cart test encountered an exception. Exception: " + e.getMessage());
            throw e;
        } finally {
            Extend_Report.endReport();
        }
    }

    // Test để kiểm tra số lượng sản phẩm trong giỏ hàng
    @Test(dataProvider = "CartData", dependsOnMethods = "testAddProductToCart")
    public void testCart(int quantity) throws Exception {
        Extend_Report.startTest("Cart Test - Quantity: " + quantity);
        try {
            cartActions.ImportAmountProduct(0, quantity); // Thực hiện hành động với số lượng
            cartActions.verifyQuantityAndPriceUpdate(0, quantity); // Kiểm tra số lượng và giá cả

        } catch (Exception e) {
            String screenshotPath = ScreenShotUtil.captureScreenshot(driver, "testCart_Exception", "CartTest");
            Extend_Report.attachScreenshot(screenshotPath);
            Extend_Report.logFail("Cart test encountered an exception for quantity: " + quantity + ". Exception: " + e.getMessage());
            throw e;
        } finally {
            Extend_Report.endReport();
        }
    }
}
