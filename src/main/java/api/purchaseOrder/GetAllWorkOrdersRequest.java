package api.purchaseOrder;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetAllWorkOrdersRequest {
    public ValidatableResponse getResponseWithAllPurchaseOrders(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.GET_ALL_WORK_ORDERS)
                .then();
    }
}
