package api.auth;

import constants.EndPoints;
import constants.Json;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class AuthRequest {
    public ValidatableResponse getResponseForUserAuthorization() {
        return given()
                .header("Content-type", "application/json")
                .body(Json.LOGIN_JSON)
                .when()
                .post(EndPoints.LOGIN_END_POINT)
                .then();
    }

    public String getToken() {
        return getResponseForUserAuthorization()
                .extract()
                .body()
                .path("content.token");
    }
}
