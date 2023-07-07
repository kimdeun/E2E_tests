package api.purchaseOrder;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import jsonObjects.purchaseOrder.createPurchaseOrder.CreatePurchaseOrderJsonObject;

import static io.restassured.RestAssured.given;

public class CreatePurchaseOrderRequest {
    public ValidatableResponse getResponseForCreatingPurchaseOrder(String token, CreatePurchaseOrderJsonObject createPurchaseOrderJsonObject) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(createPurchaseOrderJsonObject)
                .when()
                .post(EndPoints.CREATE_PURCHASE_ORDER_END_POINT)
                .then()
                .log().all();
    }
}
