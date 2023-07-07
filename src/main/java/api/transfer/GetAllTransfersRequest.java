package api.transfer;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetAllTransfersRequest {
    public ValidatableResponse getResponseWithAllTransfer(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.GET_ALL_TRANSFERS)
                .then();
    }
}
