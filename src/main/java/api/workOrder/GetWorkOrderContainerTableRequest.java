package api.workOrder;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class GetWorkOrderContainerTableRequest {
    public ValidatableResponse getWorkOrderContainerTableWithSkids(String token, Integer workOrderId) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.getSkidsInWorkOrderContainerTableEndPoint(workOrderId.toString()))
                .then();
    }

    public ValidatableResponse getWorkOrderContainerTableWithUnfoldedContainer(String token, Integer workOrderId, Integer skidId) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.getBoxesInWorkOrderContainerTableEndPoint(workOrderId.toString(), skidId.toString()))
                .then()
                .log().all();
    }
}
