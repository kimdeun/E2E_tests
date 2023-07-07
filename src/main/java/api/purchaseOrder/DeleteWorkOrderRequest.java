package api.purchaseOrder;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class DeleteWorkOrderRequest {
    public ValidatableResponse getResponseForDeletingWorkOrder(String token, Integer workOrderId) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .when()
                .delete(EndPoints.deleteWorkOrderEndPoint(workOrderId.toString()))
                .then()
                .log().all();
    }
}
