package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EnterOTP_Page {
    WebDriver driver;

    @BeforeMethod
    @Parameters({"Email"})
    public void test1(String Email){
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
    }

    //Click resend button after 1 min
    @Test
    public void LECO_WEB_011__012() throws InterruptedException {
        WebElement ResendBtn = driver.findElement();
        boolean isBeforeEnabled = ResendBtn.isEnabled();
        if(isBeforeEnabled){
            Assert.fail("Resend button is enabled before 1 minute — should be disabled!");
        } else{
            System.out.println("Resend button is correctly disabled before 1 minute.");
        }

        Thread.sleep(60000);
        ResendBtn = driver.findElement();
        boolean isAfterEnabled = ResendBtn.isEnabled();
        Assert.assertTrue(isAfterEnabled,"Resend button did not enable after 1 minute!");
        System.out.println("Resend button is enabled after 1 minute — Test Passed.");

        driver.quit();
    }

    //Try with expired OTP
    @Test
    @Parameters({"PreOTP"})
    public void LECO_WEB_013(String PREOTP){
        WebElement OtpInput = driver.findElement();
        OtpInput.sendKeys(PREOTP);

        WebElement continueBtn = driver.findElement();
        continueBtn.click();

        driver.quit();
    }

    //Check the back button
    @Test
    public void LECO_LP_WEB_026(){
        WebElement backBtn = driver.findElement();
        backBtn.click();

        driver.quit();
    }



    //Tey with invalid OTP
    @Test
    public void LECO_WEB_014(){
        WebElement OtpInput = driver.findElement();
        OtpInput.sendKeys("0000");

        WebElement continueBtn = driver.findElement();
        continueBtn.click();

        driver.quit();
    }

    //OTP activation timer check (1 min)
    @Test
    public void LECO_LP_WEB_015() throws InterruptedException{
        WebElement timer = driver.findElement();

        String initialValueText = timer.getText().replaceAll("[^0-9]", ""); //extract numbers
        int initialValue = Integer.parseInt(initialValueText);
        System.out.println("initial timer value: " + initialValue);

        // Expecting timer starts around 60
        Assert.assertTrue(initialValue >= 58 && initialValue <= 60,
                "❌ Timer did not start near 60 seconds. Found: " + initialValue);

        // Wait 5 seconds
        Thread.sleep(5000);

        // Get updated timer value
        String laterValueText = timer.getText().replaceAll("[^0-9]", "");
        int laterValue = Integer.parseInt(laterValueText);
        System.out.println("Timer value after 5 seconds: " + laterValue);

        // Verify timer decreased
        Assert.assertTrue(laterValue < initialValue,
                "❌ Timer is not counting down properly. Expected to decrease from " + initialValue + " but got " + laterValue);

        driver.quit();
    }

    //Try to continue without OTP
    @Test
    public void LECO_WEB_016(){
        WebElement continueBtn = driver.findElement();
        continueBtn.click();

        driver.quit();
    }

    //Try with valid OTP
    @Test
    @Parameters({"NewOTP"})
    public void LECO_WEB_013(String NEWOTP){
        WebElement OtpInput = driver.findElement();
        OtpInput.sendKeys(NEWOTP);

        WebElement continueBtn = driver.findElement();
        continueBtn.click();

        driver.quit();
    }

}
