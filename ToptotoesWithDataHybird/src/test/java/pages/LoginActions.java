package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginActions {
    private WebDriver driver;
    private LoginPage loginPage;

    public LoginActions(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public void LoginAction(String User, String Pass) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(loginPage.txtUser));
        loginPage.txtUser.clear();
        loginPage.txtPass.clear();
        loginPage.txtUser.sendKeys(User);
        loginPage.txtPass.sendKeys(Pass);
        loginPage.btnLogin.click();
        Thread.sleep(1000);
    }

    public boolean isNotificationMessagePresent(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement notificationElement = null;
        try {
            notificationElement = wait.until(ExpectedConditions.visibilityOf(loginPage.msgLoginPass));
        } catch (Exception e) {
            notificationElement = wait.until(ExpectedConditions.visibilityOf(loginPage.msgLoginFail));
        }

        String actualText = notificationElement.getText();
        
        return actualText.equals(expectedText);
        
    }

    public void forgotPasswordLinkVisibleOrNot() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(loginPage.linkForgot));
        loginPage.linkForgot.click();
    }

    public boolean checkBoxPassVisibleOrNot() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(loginPage.checkBoxPass));
        return loginPage.checkBoxPass.isDisplayed();
    }

    public boolean checkBoxPassFunctionality(String User, String Pass) throws InterruptedException {
        loginPage.txtUser.clear();
        loginPage.txtUser.sendKeys(User);
        loginPage.txtPass.clear();
        loginPage.txtPass.sendKeys(Pass);
        if (loginPage.checkBoxPass.isDisplayed()) {
            loginPage.checkBoxPass.click();
        }
        loginPage.btnLogin.click();
        Thread.sleep(1000);
        loginPage.Exit.click();
        Thread.sleep(1000);
        return !loginPage.txtPass.getAttribute("value").isEmpty();
    }

    public boolean clickFacebookLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(loginPage.LoginWithFacebook));
        loginPage.LoginWithFacebook.click();
        return ExpectedConditions.urlContains("facebook.com").apply(driver) != null;
    }

    public boolean clickGoogleLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(loginPage.LoginWithGoogle));
        loginPage.LoginWithGoogle.click();
        return ExpectedConditions.urlContains("accounts.google.com").apply(driver) != null;
    }

    public boolean isLoggedInAfterReopenBrowser(String User, String Pass) throws InterruptedException {
        LoginAction(User, Pass);
        driver.quit();
        Thread.sleep(2000);
        driver = new ChromeDriver();
        driver.get("https://toptotoes.vn/");
        return ExpectedConditions.visibilityOf(loginPage.CheckAccountExistAfterCloseWeb).apply(driver) != null;
    }
}
