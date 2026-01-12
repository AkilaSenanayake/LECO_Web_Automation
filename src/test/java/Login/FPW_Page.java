package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FPW_Page {

    WebDriver driver;

    @BeforeMethod
    public void BeforeMethod(){
        WebDriverManager.chromedriver().setup(); //Auto download correct driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("Add web link here");  //Weblink to access

        WebElement fpwBtn = driver.findElement();
        fpwBtn.click();
    }

    //Sign in back button
    @Test
    public void LECO_LP_WEB_008(){
        WebElement signBackBtn = driver.findElement();
        signBackBtn.click();
        driver.quit();
    }


    //Continue with valid email
    @Test
    @Parameters({"Email"})
    public void LECO_LP_WEB_009(String Email){
        WebElement email = driver.findElement();
        email.sendKeys(Email);

        WebElement sendOtpBtn = driver.findElement();
        sendOtpBtn.click();

        driver.quit();
    }

    //Test with invalid email
    @Test
    public void LECO_LP_WEB_010(){
        WebElement email = driver.findElement();
        email.sendKeys("123");

        WebElement sendOtpBtn = driver.findElement();
        sendOtpBtn.click();

        driver.quit();
    }

    //Click button without enter email
    @Test
    public void LECO_LP_WEB_025(){
        WebElement sendOtpBtn = driver.findElement();
        sendOtpBtn.click();

        driver.quit();
    }

}
