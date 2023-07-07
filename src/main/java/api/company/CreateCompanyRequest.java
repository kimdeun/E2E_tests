package api.company;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;
import jsonObjects.company.createCompany.CreateCompanyJsonObject;

import static io.restassured.RestAssured.given;

public class CreateCompanyRequest {
    public ValidatableResponse getResponseForCreatingCompanyRequest(String token, CreateCompanyJsonObject createCompanyJsonObject) {
        return given()
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/json")
                .body(createCompanyJsonObject)
                .when()
                .post(EndPoints.CREATE_COMPANY)
                .then();
    }
}
