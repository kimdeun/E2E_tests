package api.user;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import jsonObjects.purchaseOrder.addSeals.AddSealsJsonObject;
import jsonObjects.user.CreateUserJsonObject;

import static io.restassured.RestAssured.given;

public class CreateUserRequest {
    public ValidatableResponse getResponseForCreatingUserRequest(String token, CreateUserJsonObject createUserJsonObject) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(createUserJsonObject)
                .when()
                .post(EndPoints.CREATE_USER)
                .then()
                .log().all();
    }
}
