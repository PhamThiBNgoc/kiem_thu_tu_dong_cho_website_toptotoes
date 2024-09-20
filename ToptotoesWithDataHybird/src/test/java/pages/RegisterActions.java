package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterActions {
    private WebDriver driver;
    private RegisterPage registerPage;

    public RegisterActions(WebDriver driver) {
        this.driver = driver;
        this.registerPage = new RegisterPage(driver);
    }

    // Register action
    public void RegisterAction(String user, String pass) throws InterruptedException {
        registerPage.txtUser.clear();
        registerPage.txtPass.clear();
        registerPage.txtUser.sendKeys(user);
        Thread.sleep(1000);
        registerPage.txtPass.sendKeys(pass);
        Thread.sleep(1000);
        checkPassStrength();
        
    }
//lấy thông báo và so sánh
    public boolean isNotificationMessagePresentEmailClickButton(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        String notificationElement = null;
        String validationMessage = registerPage.txtUser.getAttribute("validationMessage");

        if (wait.until(ExpectedConditions.visibilityOf(registerPage.msgRegisterPass)) != null) {
            notificationElement = registerPage.msgRegisterPass.getText();
        } else if (wait.until(ExpectedConditions.visibilityOf(registerPage.msgRegisterFail)) != null) {
            notificationElement = registerPage.msgRegisterFail.getText();
        } else if (validationMessage != null && !validationMessage.isEmpty()) {
            notificationElement = validationMessage;
        }else if (wait.until(ExpectedConditions.visibilityOf(registerPage.msgRegisterPassStrength)) != null) {
            notificationElement = registerPage.msgRegisterPassStrength.getText();
        }
        
        return notificationElement.equals(expectedText);
    }
//check độ mạnh yếu
    public void checkPassStrength() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        if (wait.until(ExpectedConditions.visibilityOf(registerPage.txtPass)) != null) {
            String passText = registerPage.txtPass.getAttribute("value");
            if (passText.isEmpty()) {
                System.out.println("Password is empty. Skipping strength check.");
                registerPage.btnRegister.click();
                Thread.sleep(1000);
            }else {
            	String passStrengthText = registerPage.msgRegisterPassStrength.getText();
                boolean isButtonDisabled = registerPage.btnRegister.getAttribute("disabled") != null;

                if (passText.length() >= 1 && passText.length() <= 3) {
                    if (passStrengthText.contains("Rất yếu") && isButtonDisabled) {
                        if (wait.until(ExpectedConditions.visibilityOf(registerPage.msgNote)) != null) {
                            System.out.println("Password strength: Rất yếu");
                        }
                    }
                } else if (passText.length() >= 4 && passText.length() <= 8) {
                    if (passStrengthText.contains("Yếu") && isButtonDisabled) {
                        if (wait.until(ExpectedConditions.visibilityOf(registerPage.msgNote)) != null) {
                            System.out.println("Password strength: Yếu");
                        }
                    }
                } else if (passText.length() >= 9 && passText.length() <= 10) {
                    if (passStrengthText.contains("Trung bình") && !isButtonDisabled) {
                        System.out.println("Password strength: Trung bình");
                        registerPage.btnRegister.click();
                        Thread.sleep(1000);
                    }
                } else if (passText.length() >= 11) {
                    if (passStrengthText.contains("Mạnh") && !isButtonDisabled) {
                        System.out.println("Password strength: Mạnh");
                        registerPage.btnRegister.click();
                        Thread.sleep(1000);
                    }
                }
            }
            
        }
    }
}
