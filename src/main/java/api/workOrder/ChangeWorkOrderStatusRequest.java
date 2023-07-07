package api.workOrder;

import constants.EndPoints;
import constants.Json;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ChangeWorkOrderStatusRequest {
    public ChangeWorkOrderStatusRequest getResponseForChangingWOStatusToConfirmed(String token, Integer workOrderId) {
        given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(Json.CHANGE_WORK_ORDER_STATE_TO_CONFIRMED)
                .when()
                .put(EndPoints.changeWorkOrderStateEndPoint(workOrderId.toString()))
                .then()
                .log().all();
        return this;
    }

    public void getResponseForChangingWOStatusToProduced(String token, Integer workOrderId) {
        given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(Json.CHANGE_WORK_ORDER_STATE_TO_PRODUCED)
                .when()
                .put(EndPoints.changeWorkOrderStateEndPoint(workOrderId.toString()))
                .then()
                .log().all();
    }
}
