package api.purchaseOrder;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetAllPurchaseOrdersRequest {
    public ValidatableResponse getResponseWithAllPurchaseOrders(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.GET_ALL_PURCHASE_ORDERS)
                .then();
    }
}