package api.company;

import constants.EndPoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class DeleteCompanyRequest {
    public ValidatableResponse getResponseForDeletingCompanyRequest(String token, String companyId) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(EndPoints.getDeleteCompanyEndPoint(companyId))
                .then();
    }
}
