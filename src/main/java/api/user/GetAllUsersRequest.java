package api.user;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetAllUsersRequest {
    public ValidatableResponse getResponseWithAllUsers(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then();
    }
}
