package baseTests;

import api.auth.AuthRequest;
import api.purchaseOrder.CreatePurchaseOrderRequest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import constants.Credentials;
import constants.URLs;
import io.restassured.RestAssured;
import jsonObjects.purchaseOrder.createPurchaseOrder.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObject.LoginPage;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected LoginPage loginPage;
    public Faker faker = new Faker();
    public String token;
    public AuthRequest authRequest = new AuthRequest();

    @BeforeEach
    public void setUp() {
        Configuration.browserSize = Credentials.BROWSER_SIZE_1920_1080;
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
