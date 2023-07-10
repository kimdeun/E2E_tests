package baseTests;

import api.auth.AuthRequest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Entities;
import constants.URLs;
import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pageObject.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected LoginPage loginPage;
    public Faker faker = new Faker();
    public String token;
    public AuthRequest authRequest = new AuthRequest();

    @BeforeEach
    public void setUp() {
        Configuration.browserSize = Entities.BROWSER_SIZE_1920_1080;
        loginPage = open(URLs.STAGE_URL, LoginPage.class);
        RestAssured.baseURI = "https://staging1.stm.redflag.cc";

        //вытаскиваем токен
        token = authRequest.getResponseForUserAuthorization()
                .extract()
                .body()
                .path("content.token");

    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWindow();
    }
}
