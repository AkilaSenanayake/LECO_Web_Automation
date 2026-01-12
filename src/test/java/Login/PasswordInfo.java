package Login;

import org.testng.annotations.DataProvider;

public class PasswordInfo {
    // 🔹 DataProvider — supply all weak passwords
    @DataProvider(name = "")
    public Object[][] weakPasswords() {
        return new Object[][]{
                {"abc"},         // too short
                {"abcdefgh"},    // only lowercase
                {"ABCDEFGH"},    // only uppercase
                {"12345678"},    // only numbers
                {"abc12345"},    // missing uppercase
                {"ABC12345"},    // missing lowercase
                {"Abcdefgh"},    // missing numbers
                {"Ab1"}          // too short
        };
    }
}
