package api.purchaseOrder;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import jsonObjects.purchaseOrder.addSeals.AddSealsJsonObject;

import static io.restassured.RestAssured.given;

public class AddSealGroupRequest {
    public ValidatableResponse getResponseForAddSealRequest(String token, AddSealsJsonObject addSealsJsonObject, Integer purchaseOrderId) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(addSealsJsonObject)
                .when()
                .post(EndPoints.addSealsEndPoint(purchaseOrderId.toString()))
                .then()
                .log().all();
    }
}
