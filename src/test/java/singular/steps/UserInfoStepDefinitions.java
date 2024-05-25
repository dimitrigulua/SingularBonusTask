package singular.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import singular.helpers.TestData;
import singular.helpers.URLs;
import singular.helpers.Utils;
import singular.models.responseModels.AuthorizeResponseModel;
import singular.models.responseModels.GetUserInfoResponseModel;

import static org.hamcrest.Matchers.notNullValue;


public class UserInfoStepDefinitions {
    private RequestSpecification request;
    private String token;
    private GetUserInfoResponseModel userInfoResponseModel;

    @Given("Setup RestAssured common parameters")
    public void useEndpointInfo() {
        RestAssured.baseURI = URLs.baseURL;
        this.request = RestAssured
                .given()
                .contentType(ContentType.JSON);
    }

    @Given("Token extracted from POST request to authorize endpoint")
    public void tokenExtractedFromPOSTRequestToAuthorizeEndpoint() {
        AuthorizeResponseModel responseModel = request
                .basePath(URLs.authorize_user)
                .body(Utils.toJsonString(TestData.USER_CREDENTIALS))
                .post()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .as(AuthorizeResponseModel.class);

        token = responseModel.getToken();
    }

    @When("I retrieve user information")
    public void iRetrieveUserInformation() {
        request.header("Authorization", token);
        userInfoResponseModel = request
                .basePath(URLs.info_user)
                .get()
                .then()
                .extract()
                .as(GetUserInfoResponseModel.class);
    }

    @Then("The system returns status code of {int}")
    public void theSystemReturnsStatusCodeOf(int statusCode) {
        Assert.assertEquals(statusCode, userInfoResponseModel.getCode(), String.format("Status should be equal to %d;", statusCode));
    }

    @And("I should view authorized user information")
    public void iShouldViewAuthorizedUserInformation() {
        Assert.assertEquals(userInfoResponseModel, TestData.USER_INFO);
    }

    @Given("Invalid authorization token")
    public void invalidAuthorizationToken() {
        token = "invalid_token";
    }

    @And("The system returns an error message")
    public void theSystemReturnsAnErrorMessage() {
        Assert.assertEquals(userInfoResponseModel.getMessage(), "FAILURE");
    }
}
