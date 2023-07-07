package api.company;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import jsonObjects.company.createCompany.CreateCompanyJsonObject;
import jsonObjects.company.createLocation.CreateLocationJsonObject;

import static io.restassured.RestAssured.given;

public class CreateLocationRequest {
    public ValidatableResponse getResponseForCreatingLocationRequest(String token, CreateLocationJsonObject createLocationJsonObject) {
        return given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(createLocationJsonObject)
                .when()
                .post(EndPoints.CREATE_LOCATION)
                .then()
                .log().all();
    }
}
