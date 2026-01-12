package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Logout {

    WebDriver driver;

    @BeforeMethod
    @Parameters({"Email","Password"})
    public void Beforemethod(String Email, String Password){
        WebDriverManager.chromedriver().setup(); //Auto download correct driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("Add web link here");  //Weblink to access

        WebElement email = driver.findElement();
        email.sendKeys(Email);

        WebElement password = driver.findElement();
        password.sendKeys(Password);

        WebElement loginBtn = driver.findElement();
        loginBtn.click();
    }

    @Test
    public void LECO_LP_WEB_007(){
        WebElement logOutBtn = driver.findElement();
        logOutBtn.click();
    }
}
