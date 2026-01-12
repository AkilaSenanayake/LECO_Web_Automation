package Login;

import org.testng.annotations.DataProvider;

public class LoginInfo {
    @DataProvider(name = "LoginInfo")
    public Object[][] getData() {
        Object[][] data = {{"abc@gmail.com", "abc123"}, {"abc@gmail.com", "123"}, {"def@gmail.com", "def"}};
        return data;
    }
}