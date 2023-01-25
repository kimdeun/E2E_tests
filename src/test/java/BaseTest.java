import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.URLs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pageObject.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        Configuration.browserSize = "1920x1080";
        loginPage = open(URLs.STAGE_URL, LoginPage.class);
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWindow();
    }
}
