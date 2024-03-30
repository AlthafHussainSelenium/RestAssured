package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class Animals {
    public Response response;
    public ResponseBody body;
    public String accessToken;
    public String types;
    public JsonPath jsonpath;
    @Given("I am an authenticated user")
    public void i_am_an_authenticated_user() {
        RestAssured.baseURI = "https://api.petfinder.com/v2/";
        response = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; chareset=utf-8")
                .formParam("grant_type", "Client_Credentials")
                .formParam("Client_Id", "pass the id here")
                .formParam("Client_Secret", "paas the secret id here")
                .when()
                .post(RestAssured.baseURI+"/oauth2/token");
        body = response.getBody();
        String responseBody =   body.asString();
        System.out.println("Body is "+body);
        System.out.println("responseBody is "+responseBody);
        jsonpath = response.jsonPath();
        accessToken = jsonpath.getJsonObject("access_token").toString();
    }

    @When("I hit the get animals api url")
    public void i_hit_the_get_animals_api_url() {
        response = RestAssured.given()
                .header("Authorization", "Bearer "+ accessToken)
                .when()
                .get(RestAssured.baseURI+"/types");
        jsonpath = response.jsonPath();
        types = jsonpath.getJsonObject("types").toString();
        System.out.println("types is "+types);
    }

    @Then("I get animals in the response of the API")
    public void i_get_animals_in_the_response_of_the_api() {
    }

    @Given("I am un authenticated user")
    public void i_am_un_authenticated_user() {
    }

    @Then("I do not get animals in the response of the API")
    public void i_do_not_get_animals_in_the_response_of_the_api() {
    }
}
