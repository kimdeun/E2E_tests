package api.workOrder;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import jsonObjects.workOrder.createWorkOrder.CreateWorkOrderJsonObject;

import static io.restassured.RestAssured.given;

public class CreateWorkOrderRequest {
    public ValidatableResponse getResponseForCreatingWorkOrder(String token, CreateWorkOrderJsonObject createWorkOrderJsonObject) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(createWorkOrderJsonObject)
                .when()
                .post(EndPoints.CREATE_WORK_ORDER_END_POINT)
                .then()
                .log().all();
    }
}
