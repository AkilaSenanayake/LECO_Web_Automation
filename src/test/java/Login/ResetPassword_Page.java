package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.N;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ResetPassword_Page {
    WebDriver driver;

    @BeforeMethod
    @Parameters({"Email", "NewOTP"})
    public void test1(String Email, String NEWOTP){
        WebDriverManager.chromedriver().setup(); //Auto download correct driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("Add web link here");  //Weblink to access

        WebElement fpwBtn = driver.findElement();
        fpwBtn.click();

        WebElement email = driver.findElement();
        email.sendKeys(Email);

        WebElement sendOtpBtn = driver.findElement();
        sendOtpBtn.click();

        WebElement OtpInput = driver.findElement();
        OtpInput.sendKeys(NEWOTP);

        WebElement continueBtn = driver.findElement();
        continueBtn.click();
    }

    //Click reset without inputs
    @Test
    public void LECO_WEB_018(){
        WebElement ResetPWBtn = driver.findElement();
        ResetPWBtn.click();

        driver.quit();
    }

    //weak password
    @Test(dataProvider = "weakPasswords", dataProviderClass = PasswordInfo.class)
    public void LECO_LP_WEB_019(String weakPassword) throws InterruptedException {
        WebElement NewPassword = driver.findElement();
        NewPassword.click();
        NewPassword.clear();
        NewPassword.sendKeys(weakPassword);

        WebElement ConfirmPassword = driver.findElement();
        ConfirmPassword.click();
        ConfirmPassword.clear();
        ConfirmPassword.sendKeys(weakPassword);

        WebElement resetPWBtn = driver.findElement();
        resetPWBtn.click();

        Thread.sleep(1000);

        WebElement errorMsg = driver.findElement();

        Assert.assertTrue(
                errorMsg.isDisplayed(),
                "Weak password accepted: "+weakPassword
        );

        System.out.println("Weak password '"+weakPassword+"'correctly rejected with message: "+errorMsg.getText());

        driver.quit();

    }

    //Reset with two different passwords
    @Test
    @Parameters({"NewPW","ConfirmDiffPW"})
    public void LECO_LP_WEB_020(String NewPW, String ConfirmDiffPW) throws InterruptedException {
        WebElement newPW = driver.findElement();
        newPW.click();
        newPW.sendKeys(NewPW);

        WebElement confirmDiffPW = driver.findElement();
        confirmDiffPW.click();
        confirmDiffPW.sendKeys(ConfirmDiffPW);

        WebElement resetPWBtn = driver.findElement();
        resetPWBtn.click();

        Thread.sleep(1000);

        WebElement errMsg = driver.findElement();
        System.out.println("Error ");


        // Assert that the error message is correct
        Assert.assertTrue(
                errMsg.getText().contains("Passwords do not match"),
                "❌ Expected 'passwords do not match' message not displayed!"
        );

        System.out.println("Test Passed — Correct error message shown for mismatched passwords.");

        driver.quit();
    }

    //Reset with valid password
    @Test
    @Parameters({"NewValidPW","ConfirmValidPW"})
    public void LECO_LP_WEB_021(String NewPW, String ConfirmPW){
        WebElement newPWInput = driver.findElement();
        newPWInput.sendKeys(NewPW);

        WebElement confirmPWInput = driver.findElement();
        confirmPWInput.sendKeys(ConfirmPW);

        WebElement resetPWBtn = driver.findElement();
        resetPWBtn.click();

        driver.quit();
    }


    //Login with old password after reset password
    @Test
    @Parameters({"Email", "OldPW"})
    public void LECO_WEB_023(String Email, String OldPW){
        WebElement eMail = driver.findElement();
        eMail.sendKeys(Email);

        WebElement pssword = driver.findElement();
        pssword.sendKeys(OldPW);

        WebElement loginBtn = driver.findElement();
        loginBtn.click();

        driver.quit();

    }
}
