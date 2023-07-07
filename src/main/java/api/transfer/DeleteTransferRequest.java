package api.transfer;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import jsonObjects.warehouse.CreateTransferJsonObject;

import static io.restassured.RestAssured.given;

public class DeleteTransferRequest {
    public ValidatableResponse getResponseForDeletingTransfer(String token, String transferId) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(EndPoints.getDeleteTransferEndPoint(transferId))
                .then();
    }
}
