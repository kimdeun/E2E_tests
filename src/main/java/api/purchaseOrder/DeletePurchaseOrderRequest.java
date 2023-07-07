package api.purchaseOrder;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class DeletePurchaseOrderRequest {
    public ValidatableResponse getResponseForDeletingPurchaseOrder(String token, Integer purchaseOrderId) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .when()
                .delete(EndPoints.deletePurchaseOrderEndPoint(purchaseOrderId.toString()))
                .then()
                .log().all();
    }
}
