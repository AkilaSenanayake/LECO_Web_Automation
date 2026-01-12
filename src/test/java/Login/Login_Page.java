package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners(testNG.Listeners.LoginListeners.class)
public class Login_Page {

    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void Beforemethod(){
        WebDriverManager.chromedriver().setup(); //Auto download correct driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("Add web link here");  //Weblink to access
    }

    //Test login with empty fields
    @Test
    public void LECO_LP_WEB_001(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Click Login button without entering data
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginBtn")));
        loginBtn.click();

        // Wait until validation email message is visible
        WebElement validationMsg1 = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(""))); //Email field element

        String actualMsg1 = validationMsg1.getText();
        String expectedValue1 = "E-mail is required";
        softAssert.assertEquals(actualMsg1,expectedValue1,"Email validation miss matched");

        // Wait until validation password message is visible
        WebElement validationMsg2 = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath(""))); //Email field element

        String actualMsg2 = validationMsg2.getText();
        String expectedValue2 = "E-mail is required";
        softAssert.assertEquals(actualMsg1,expectedValue1,"Email validation miss matched");

        System.out.println("Validation message: " + actualMsg1);
    }

    //Login with invalid inputs
    @Test
    public void LECO_LP_WEB_002__003__004(String email, String password){
        WebElement eMail = driver.findElement();
        eMail.sendKeys(email);

        WebElement pssword = driver.findElement();
        pssword.sendKeys(password);

        WebElement loginBtn = driver.findElement();
        loginBtn.click();

        driver.quit();
    }

    //Login with valid inputs
    @Test
    @Parameters({"Email", "Password"})
    public void LECO_LP_WEB_005(String Email, String Password){
        WebElement email = driver.findElement();
        email.sendKeys(Email);

        WebElement password = driver.findElement();
        password.sendKeys(Password);

        WebElement loginBtn = driver.findElement();
        loginBtn.click();

        //Have to add waits
        WebElement validationMsg = driver.findElement();
        String actualMsg = validationMsg.getText();

        driver.quit();
    }

    //forgot pw button test
    @Test
    public void LECO_LP_WEB_006(){
        WebElement fpwBtn = driver.findElement();
        fpwBtn.click();

        driver.quit();
    }



}
