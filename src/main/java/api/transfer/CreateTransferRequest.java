package api.transfer;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import jsonObjects.warehouse.CreateTransferJsonObject;

import static io.restassured.RestAssured.given;

public class CreateTransferRequest {
    public ValidatableResponse getResponseForCreatingTransfer(String token, CreateTransferJsonObject createTransferJsonObject) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(createTransferJsonObject)
                .when()
                .post(EndPoints.CREATE_TRANSFER_IN_PRODUCTION_SET)
                .then()
                .log().all();
    }
}
