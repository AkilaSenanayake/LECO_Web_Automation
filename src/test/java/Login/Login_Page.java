package Login;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners(testNG.Listeners.LoginListeners.class)
public class Login_Page {

    WebDriver driver;

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
        WebElement loginBtn = driver.findElement();
        loginBtn.click();

        //Have to add waits
        WebElement validationMsg = driver.findElement();
        String actualMsg = validationMsg.getText();

        System.out.println("Validation message: "+actualMsg);

        driver.quit();
    }

    //Login with invalid inputs
    @Test(dataProvider = "LoginInfo",dataProviderClass = LoginInfo.class)
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
