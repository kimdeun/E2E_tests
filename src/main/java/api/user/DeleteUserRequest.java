package api.user;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import jsonObjects.user.CreateUserJsonObject;

import static io.restassured.RestAssured.given;

public class DeleteUserRequest {
    public ValidatableResponse getResponseForDeletingUserRequest(String token, String userId) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(EndPoints.getDeleteUserEndPoint(userId))
                .then();
    }
}
